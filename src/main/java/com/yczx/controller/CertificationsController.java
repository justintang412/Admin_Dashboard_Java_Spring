package com.yczx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yczx.domain.Certification;
import com.yczx.domain.CertificationType;
import com.yczx.service.BidFileService;
import com.yczx.service.CertificationService;
import com.yczx.service.CertificationTypeService;
import com.yczx.service.SysUserService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class CertificationsController {
	@Autowired
	CertificationService certificationService;
	@Autowired
	CertificationTypeService certificationTypeService;
	@Autowired
	private Environment env;
	@Autowired
	BidFileService bidFileService;
	@Autowired
	SysUserService sysUserService;

	@PreAuthorize("hasAuthority('certifications')")
	@RequestMapping("pages/certification/certifications.html")
	public String certifications(String action, String id, Model model, Certification certification) {
		if (action == null) {
			return "pages/certification/certifications";
		}
		if (action.equals("view")) {
			model.addAttribute("certifications", certificationService.selectCertificationById(new Long(id)));
			return "pages/certification/certificationsView";
		}
		if (action.equals("create")) {
			certification.clear();
			ArrayList<CertificationType> certificationTypes = certificationTypeService.selectCertificationTypes();
			model.addAttribute("certificationTypes", certificationTypes);
			return "pages/certification/certificationsCreate";
		}
		if (action.equals("save")) {
			OperationResult<Certification> result = certificationService.insertOrUpdateCertification(certification);
			model.addAttribute("result", result);
			if (result.isError()) {
				return "pages/certification/certificationsCreate";
			}
			return "pages/certification/certifications";
		}
		if (action.equals("edit")) {
			Certification c = certificationService.selectCertificationById(new Long(id));
			certification.setDescription(c.getDescription());
			certification.setExpireDate(c.getExpireDate());
			certification.setId(c.getId());
			certification.setName(c.getName());
			certification.setType(c.getType());
			certification.setValid(c.getValid());
			ArrayList<CertificationType> certificationTypes = certificationTypeService.selectCertificationTypes();
			model.addAttribute("certificationTypes", certificationTypes);
			return "pages/certification/certificationsCreate";
		}

		if (action.equals("delete")) {
			Long certificationid = new Long(id);
			certificationService.deleteCertification(certificationid);
			return "pages/certification/certifications";
		}
		return "pages/certification/certifications";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('certifications')")
	@RequestMapping(value = "pages/certification/certificationList.html")
	public DataTable<Map<String, Object>> userList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<Certification> certifications = certificationService.selectCertificationsByPageParam(search, min, max,
				pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Certification certification : certifications) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", certification.getId());
			map.put("description", certification.getDescription());
			map.put("expireDate", certification.getExpireDate());
			BidFile file = bidFileService.selectBidFileById(certification.getFileId());
			map.put("fileName", file.getName());
			map.put("filePath", file.getPath());
			map.put("name", certification.getName());
			map.put("type", certification.getType());
			map.put("valid", certification.getValid());

			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(certificationService.getCertificationTotalCount(search).longValue());
		return data;
	}

	@PreAuthorize("hasAuthority('certifications')")
	@RequestMapping("pages/certification/certificationsDownload.html")
	public void certificationsDownload(HttpServletResponse response, Long id) {
		Certification c = certificationService.selectCertificationById(id);
		if(c!=null) {
			BidFile bidFile = bidFileService.selectBidFileById(c.getFileId());
			if(bidFile!=null) {
				BidUtils.downloadBidFile(bidFile, response, env.getProperty("filePath"));
			}
		}
	}

	@PreAuthorize("hasAuthority('certifications')")
	@RequestMapping("pages/certification/certificationsUpload.html")
	public String certifications(HttpServletResponse response, Certification certification,
			@RequestParam("file") MultipartFile file) {
		
		if (file != null) {
			BidFile newBidFile = BidUtils.uploadBidFile(env.getProperty("filePath"), 
					sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId(), file, bidFileService, "资质文件");
			if(newBidFile!=null) {
				if (certification.getId() != null && certification.getFileId() != null) {
					BidFile oldFile = bidFileService.selectBidFileById(certification.getFileId());
					bidFileService.updateBidFileToDeleted(oldFile);
				}
				certification.setFileId(newBidFile.getId());
				certificationService.insertOrUpdateCertification(certification);
			}
		}
		return "redirect:certifications.html";
	}
}
