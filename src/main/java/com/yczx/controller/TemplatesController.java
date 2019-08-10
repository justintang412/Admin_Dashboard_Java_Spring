package com.yczx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yczx.domain.BidFile;
import com.yczx.domain.Template;
import com.yczx.domain.TemplateChapter;
import com.yczx.domain.TemplateType;
import com.yczx.form.TemplateAndChapterForm;
import com.yczx.service.BidFileService;
import com.yczx.service.SysUserService;
import com.yczx.service.TemplateChapterService;
import com.yczx.service.TemplateService;
import com.yczx.service.TemplateTypeService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class TemplatesController {
	@Autowired
	TemplateService templateService;
	@Autowired
	TemplateTypeService templateTypeService;
	@Autowired
	Environment env;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	BidFileService bidFileService;
	@Autowired
	TemplateChapterService templateChapterService;

	@PreAuthorize("hasAuthority('templates')")
	@RequestMapping("pages/template/templates.html")
	public String templates(String action, String id, Model model, TemplateAndChapterForm templateAndChapterForm) {
		if (action == null) {
			return "pages/template/templates";
		}
		if (action.equals("view")) {
			Template t = templateService.selectTemplateById(new Long(id));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("templateId", t.getId());
			map.put("templateDescription", t.getDescription());
			map.put("templateName", t.getName());
			map.put("templateType", templateTypeService.selectTemplateTypeById(t.getTemplateType()).getTitle());
			map.put("templateFileId", t.getFileId());
			model.addAttribute("templates", map);
			BidFile bf = bidFileService.selectBidFileById(t.getId());
			model.addAttribute("bidFile", bf);
			Boolean canChange = templateService.getTemplateReferenceCount(t.getId()) > 0 ? false
					: true;
			model.addAttribute("canChange", canChange);
			
			ArrayList<TemplateChapter> tcs = templateChapterService.selectTemplateChaptersByTemplateId(t.getId());
			ArrayList<Map<String, Object>> templateChapters = new ArrayList<Map<String, Object>>();
			for (TemplateChapter tc : tcs) {
				Map<String, Object> templateChapter = new HashMap<String, Object>();
				templateChapter.put("id", tc.getId());
				templateChapter.put("fileId", tc.getFileId());
				templateChapter.put("sort", tc.getSort());
				templateChapter.put("name", tc.getName());
				templateChapter.put("description", tc.getDescription());
				templateChapters.add(templateChapter);
			}
			model.addAttribute("templateChapters", templateChapters);

			return "pages/template/templatesView";
		}
		if (action.equals("create")) {
			templateAndChapterForm.clear();
			ArrayList<TemplateType> templateTypes = templateTypeService.selectTemplateTypes();
			model.addAttribute("templateTypes", templateTypes);
			return "pages/template/templatesCreate";
		}

		if (action.equals("edit")) {
			Template c = templateService.selectTemplateById(new Long(id));
			templateAndChapterForm.clear();
			templateAndChapterForm.setTemplateId(c.getId());
			templateAndChapterForm.setTemplateDescription(c.getDescription());
			templateAndChapterForm.setTemplateName(c.getName());
			templateAndChapterForm.setTemplateFileId(c.getFileId());
			templateAndChapterForm.setTemplateType(c.getTemplateType());
			ArrayList<TemplateType> templateTypes = templateTypeService.selectTemplateTypes();
			model.addAttribute("templateTypes", templateTypes);
			return "pages/template/templatesCreate";
		}

		if (action.equals("createChapter")) {
			templateAndChapterForm.clear();
			templateAndChapterForm.setTemplateId(new Long(id));

			ArrayList<TemplateChapter> templateChapters = templateChapterService
					.selectTemplateChaptersByTemplateId(new Long(id));
			model.addAttribute("templateChapters", templateChapters);

			return "pages/template/templateChaptersCreate";
		}
		if (action.equals("editChapter")) {
			TemplateChapter c = templateChapterService.selectTemplateChapterById(new Long(id));
			templateAndChapterForm.clear();
			templateAndChapterForm.setTemplateId(c.getTemplateId());
			templateAndChapterForm.setChapterId(c.getId());
			templateAndChapterForm.setChapterName(c.getName());
			templateAndChapterForm.setChapterDescription(c.getDescription());
			templateAndChapterForm.setChapterSort(c.getSort() - 1);
			ArrayList<TemplateChapter> templateChapters = templateChapterService
					.selectTemplateChaptersByTemplateId(c.getTemplateId());
			model.addAttribute("templateChapters", templateChapters);

			return "pages/template/templateChaptersCreate";
		}
		if (action.equals("deleteChapter")) {
			TemplateChapter c = templateChapterService.selectTemplateChapterById(new Long(id));
			Long templateId = c.getTemplateId();
			templateChapterService.deleteTemplateChapter(c.getId());

			return this.templates("view", templateId.toString(), model, templateAndChapterForm);
		}
		if (action.equals("delete")) {
			Long templateid = new Long(id);
			OperationResult<Long> result = templateService.deleteTemplate(templateid);
			if (result.isError()) {
				model.addAttribute("result", result);
			}
			return "pages/template/templates";
		}
		return "pages/template/templates";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('templates')")
	@RequestMapping(value = "pages/template/templateList.html")
	public DataTable<Map<String, Object>> templateList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<Template> templates = templateService.selectTemplatesByPageParam(search, min, max, pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Template template : templates) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", template.getId());
			map.put("description", template.getDescription());
			map.put("name", template.getName());
			map.put("canBeChanged", templateService.getTemplateReferenceCount(template.getId()) > 0 ? false : true);
			map.put("type", templateTypeService.selectTemplateTypeById(template.getTemplateType()).getTitle());
			map.put("creator", sysUserService.getUserById(template.getCreator()).getSalt());
			map.put("createTime", template.getCreateTime());

			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(templateService.getTemplateTotalCount(search).longValue());
		return data;
	}

	@PreAuthorize("hasAuthority('templates')")
	@RequestMapping("pages/template/templatesDownload.html")
	public void templatesDownload(HttpServletResponse response, Long id) {
		BidFile bf = bidFileService.selectBidFileById(id);
		if (bf == null) {
			return;
		}
		BidUtils.downloadBidFile(bf, response, env.getProperty("filePath"));
	}

	@PreAuthorize("hasAuthority('templates')")
	@RequestMapping("pages/template/templateChaptersDownload.html")
	public void templateChaptersDownload(HttpServletResponse response, Long id) {
		TemplateChapter c = templateChapterService.selectTemplateChapterById(id);
		if (c == null || c.getFileId() == null) {
			return;
		}
		BidFile bf = bidFileService.selectBidFileById(c.getFileId());
		if (bf == null) {
			return;
		}
		BidUtils.downloadBidFile(bf, response, env.getProperty("filePath"));
	}

	@PreAuthorize("hasAuthority('templates')")
	@RequestMapping("pages/template/templatesUpload.html")
	public String templatesUpload(HttpServletRequest request, HttpServletResponse response, Model model,
			TemplateAndChapterForm templateAndChapterForm, @RequestParam("file") MultipartFile file) {

		if (templateAndChapterForm.getTemplateId() == null && file.isEmpty()) {
			OperationResult<Template> operationResult = new OperationResult<Template>();
			operationResult.setError(true);
			operationResult.setMessage("请选择模板文件。");
			ArrayList<TemplateType> templateTypes = templateTypeService.selectTemplateTypes();
			model.addAttribute("templateTypes", templateTypes);
			model.addAttribute("result", operationResult);
			return "pages/template/templatesCreate";
		}
		Long userid = sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId();
		Long fileId = null;
		Template oldTemplate = null;
		if (templateAndChapterForm.getTemplateId() != null) {
			oldTemplate = templateService.selectTemplateById(templateAndChapterForm.getTemplateId());
			fileId = oldTemplate.getFileId();
		}
		// begin of file uploading
		if (!file.isEmpty()) {
			BidFile uploadedFile = BidUtils.uploadBidFile(env.getProperty("filePath"), userid, file, bidFileService,
					"模板文件");

			fileId = uploadedFile.getId();
		}
		// end of file uploading
		// if the file is uploaded successfully
		if (fileId == null) {
			OperationResult<Template> operationResult = new OperationResult<Template>();
			operationResult.setError(true);
			operationResult.setMessage("上传文件出错，请重试。");
			ArrayList<TemplateType> templateTypes = templateTypeService.selectTemplateTypes();
			model.addAttribute("templateTypes", templateTypes);
			model.addAttribute("result", operationResult);
			return "pages/template/templatesCreate";
		}
		if (oldTemplate == null) {
			oldTemplate = new Template();
		}
		oldTemplate.setDescription(templateAndChapterForm.getTemplateDescription());
		oldTemplate.setName(templateAndChapterForm.getTemplateName());
		oldTemplate.setTemplateType(templateAndChapterForm.getTemplateType());
		oldTemplate.setFileId(fileId);
		oldTemplate.setCreator(userid);
		templateService.insertOrUpdateTemplate(oldTemplate);

		return "redirect:templates.html";
	}

	@PreAuthorize("hasAuthority('templates')")
	@RequestMapping("pages/template/templateChaptersUpload.html")
	public String templateChaptersUpload(HttpServletRequest request, HttpServletResponse response, Model model,
			TemplateAndChapterForm templateAndChapterForm, @RequestParam("file") MultipartFile file) {
		if (templateAndChapterForm.getTemplateId() == null) {
			OperationResult<Template> operationResult = new OperationResult<Template>();
			operationResult.setError(true);
			operationResult.setMessage("请选择模板文件。");

			model.addAttribute("result", operationResult);
			return "pages/template/templates";
		}
		if (file.isEmpty()) {
			OperationResult<Template> operationResult = new OperationResult<Template>();
			operationResult.setError(true);
			operationResult.setMessage("上传文件出错，请重试。");
			model.addAttribute("result", operationResult);
			return "pages/template/templates";
		}

		Long userid = sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId();

		// delete old chapter file in case exists
		TemplateChapter templateChapter = null;

		// update
		if (templateAndChapterForm.getChapterId() != null) {
			TemplateChapter oldTemplateChapter = templateChapterService
					.selectTemplateChapterById(templateAndChapterForm.getChapterId());
			bidFileService.updateBidFileToDeletedById(oldTemplateChapter.getFileId());
			templateChapter = oldTemplateChapter;
		}
		if (templateChapter == null) {
			templateChapter = new TemplateChapter();
			templateChapter.setTemplateId(templateAndChapterForm.getTemplateId());
		}

		// begin of file uploading
		String filePath = env.getProperty("filePath");
		BidFile uploadedBidFile = BidUtils.uploadBidFile(filePath, userid, file, bidFileService, "模板章节文件");

		// end of file uploading
		// if the file is uploaded successfully
		if (uploadedBidFile == null) {
			OperationResult<Template> operationResult = new OperationResult<Template>();
			operationResult.setError(true);
			operationResult.setMessage("上传文件出错，请重试。");
			model.addAttribute("result", operationResult);
			return "pages/template/templates";
		}
		templateChapter.setFileId(uploadedBidFile.getId());
		templateChapter.setName(templateAndChapterForm.getChapterName());
		templateChapter.setDescription(templateAndChapterForm.getChapterDescription());
		templateChapter.setSort(templateAndChapterForm.getChapterSort());
		templateChapterService.insertOrUpdateTemplateChapter(templateChapter);

		return "redirect:templates.html?action=view&id=" + templateChapter.getTemplateId();
	}
}
