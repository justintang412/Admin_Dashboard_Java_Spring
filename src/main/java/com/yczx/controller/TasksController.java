package com.yczx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.yczx.domain.Bid;
import com.yczx.domain.BidFile;
import com.yczx.domain.BidTask;
import com.yczx.domain.SysUser;
import com.yczx.domain.Template;
import com.yczx.domain.TemplateChapter;
import com.yczx.form.BidForm;
import com.yczx.service.BidFileService;
import com.yczx.service.BidService;
import com.yczx.service.BidTaskService;
import com.yczx.service.SysUserService;
import com.yczx.service.TemplateChapterService;
import com.yczx.service.TemplateService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;
import com.yczx.support.PageParam;

@Controller
public class TasksController {
	@Autowired
	BidService bidService;
	@Autowired
	Environment env;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	BidFileService bidFileService;
	@Autowired
	TemplateService templateService;
	@Autowired
	BidTaskService bidTaskService;
	@Autowired
	TemplateChapterService templateChapterService;
	
	@PreAuthorize("hasAuthority('tasks')")
	@RequestMapping("pages/bid/tasks.html")
	public String tasks(String action, String id, Model model, BidForm bidForm) {
		if (action == null) {
			return "pages/bid/tasks";
		}
		if (action.equals("view")) {
			SysUser user = sysUserService.getUserByName(BidUtils.getLoginedUsername());
			Bid mbid = bidService.selectBidById(new Long(id));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", mbid.getId());
			map.put("description", mbid.getDescription());
			map.put("name", mbid.getName());
			String manager = "";
			ArrayList<SysUser> managers = sysUserService.selectUsersByUserIds(mbid.getManager());
			for (SysUser m : managers) {
				manager += m.getSalt() + ", ";
			}
			if (manager.length() > 0) {
				manager = manager.substring(0, manager.length() - 2);
			}
			map.put("manager", manager);
			map.put("templateName", templateService.selectTemplateById(mbid.getTemplateId()).getName());
			map.put("creator", sysUserService.getUserById(mbid.getCreator()).getSalt());
			map.put("startTime", mbid.getStartTime());
			map.put("endTime", mbid.getEndTime());
			map.put("createTime", mbid.getCreateTime());
			map.put("status", mbid.getStatus());

			model.addAttribute("bid", map);
			Template template = templateService.selectTemplateById(mbid.getTemplateId());
			BidFile templateFile = bidFileService.selectBidFileById(template.getFileId());

			model.addAttribute("templateFile", templateFile);
			model.addAttribute("managers", managers);

			ArrayList<BidTask> bidTasks = bidTaskService.selectBidTasksByBidAsEditor(mbid.getId(), user.getId());
			ArrayList<Map<String, Object>> bidTasksMaps = new ArrayList<Map<String, Object>>();
			for (BidTask task : bidTasks) {
				Map<String, Object> taskmap = new HashMap<String, Object>();
				TemplateChapter templateChapter = templateChapterService.selectTemplateChapterById(task.getChapterId());
				BidFile draftFile = null;
				if (task.getDraftFileId() != null) {
					draftFile = bidFileService.selectBidFileById(task.getDraftFileId());
				}

				taskmap.put("id", task.getId());
				taskmap.put("chapterTemplateName", templateChapter.getName());
				taskmap.put("chapterTemplateId", templateChapter.getId());
				taskmap.put("drafFileId", draftFile == null ? null : draftFile.getId());
				taskmap.put("drafFileName", draftFile == null ? null : draftFile.getName());
				if(draftFile!=null) {
					taskmap.put("drafFileAuthor", sysUserService.getUserById(draftFile.getUploader()).getSalt());
				}
				else {
					taskmap.put("drafFileAuthor", null);
				}
				
				taskmap.put("drafFileUpdateTime", draftFile == null ? null : draftFile.getUploadTime());
				taskmap.put("startTime", task.getStartTime());
				taskmap.put("endTime", task.getEndTime());
				if(task.getCheckedOut()==null|| task.getCheckedOut()==0) {
					taskmap.put("checkedOut", "正常");
					taskmap.put("canCheckIn", true);
					taskmap.put("canCheckOut", true);
					taskmap.put("canSubmit", true);
				}
				else {
					taskmap.put("checkedOut", "锁定");
					if(task.getChecker()==user.getId()) {
						taskmap.put("canCheckIn", true);
						taskmap.put("canCheckOut", false);
						taskmap.put("canSubmit", false);
					}
					else {
						taskmap.put("canCheckIn", false);
						taskmap.put("canCheckOut", false);
						taskmap.put("canSubmit", false);
					}
				}
				taskmap.put("checker",task.getChecker()==null?null:sysUserService.getUserById(task.getChecker()).getSalt());
				taskmap.put("checkTime",task.getCheckTime());
				
				taskmap.put("submitTime", task.getSubmitTime());
				taskmap.put("submitComment", task.getSubmitComment());
				taskmap.put("approver", task.getApprover());
				taskmap.put("approveStatus", task.getApproveStatus());
				taskmap.put("approveTime", task.getApproveTime());
				taskmap.put("approveComment", task.getApproveComment());
				taskmap.put("endTime", task.getEndTime());
				String mEditors = "";
				ArrayList<SysUser> editors = sysUserService.selectUsersByUserIds(task.getEditorIds());
				for (SysUser editor : editors) {
					mEditors += editor.getSalt() + ", ";
				}
				if (mEditors.length() > 0) {
					mEditors = mEditors.substring(0, mEditors.length() - 2);
				}
				taskmap.put("editors", mEditors);

				bidTasksMaps.add(taskmap);
			}
			model.addAttribute("bidTasksMaps", bidTasksMaps);
			return "pages/bid/tasksView";
		}

		if (action.equals("checkin")) {
			bidForm.clear();
			BidTask task  = bidTaskService.selectBidTaskById(new Long(id));
			TemplateChapter tc  = templateChapterService.selectTemplateChapterById(task.getChapterId());
			Bid bid  = bidService.selectBidById(task.getBidId());
			model.addAttribute("bidName", bid.getName());
			model.addAttribute("chapterName", tc.getName());
			bidForm.setTaskId(task.getId());
			bidForm.setBidId(task.getBidId());
			
			return "pages/bid/tasksCreateCheckin";
		}
		
		if (action.equals("submit")) {
			bidForm.clear();
			bidForm.setTaskId(new Long(id));
			BidTask task = bidTaskService.selectBidTaskById(new Long(id));
			bidForm.setBidId(task.getBidId());
			return "pages/bid/tasksCreateSubmit";
		}
		if (action.equals("submitsave")) {
			BidTask bidTask = bidTaskService.selectBidTaskById(bidForm.getTaskId());
			bidTask.setSubmitComment(bidForm.getSubmitDescription());
			bidTask.setSubmiter(sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId());
			
			bidTaskService.updateTaskSubmit(bidTask);
			
			return "redirect:./tasks.html?action=view&id="+bidTask.getBidId();
		}
		if (action.equals("checkout")) {
			BidTask bidTask = bidTaskService.selectBidTaskById(new Long(id));
			SysUser user = sysUserService.getUserByName(BidUtils.getLoginedUsername());

			bidTask.setCheckedOut(new Long(1));
			bidTask.setChecker(user.getId());
			bidTaskService.updateTaskCheckout(bidTask);
			return "redirect:./tasks.html?action=view&id="+bidTask.getBidId();
		}

		return "pages/bid/tasks";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('tasks')")
	@RequestMapping(value = "pages/bid/taskList.html")
	public DataTable<Map<String, Object>> taskList(PageParam pageParam) {
		SysUser user = sysUserService.getUserByName(BidUtils.getLoginedUsername());
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<Bid> bids = bidService.selectBidsByPageParamAsManager(search, min, max, pagesize, user.getId());
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Bid bid : bids) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", bid.getId());
			map.put("description", bid.getDescription());
			map.put("name", bid.getName());
			String manager = "";
			ArrayList<SysUser> managers = sysUserService.selectUsersByUserIds(bid.getManager());
			for (SysUser m : managers) {
				manager += m.getSalt() + ", ";
			}
			if (manager.length() > 0) {
				manager = manager.substring(0, manager.length() - 2);
			}
			map.put("manager", manager);
			map.put("templateName", templateService.selectTemplateById(bid.getTemplateId()).getName());
			map.put("creator", sysUserService.getUserById(bid.getCreator()).getSalt());
			map.put("startTime", bid.getStartTime());
			map.put("endTime", bid.getEndTime());
			map.put("createTime", bid.getCreateTime());
			map.put("status", bid.getStatus());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(bidService.getBidTotalCountAsManager(search, user.getId()).longValue());
		return data;
	}

	@PreAuthorize("hasAuthority('tasks')")
	@RequestMapping("pages/bid/tasksCheckin.html")
	public String certifications(HttpServletResponse response, BidForm bidForm,
			@RequestParam("file") MultipartFile file) {
		String filePath = env.getProperty("filePath");
		String fileName = file.getOriginalFilename();
		Long bidId = null;
		String ext = "";
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex != -1) {
			ext = fileName.substring(dotIndex, fileName.length());
		}
		String uuid = UUID.randomUUID().toString();

		
		if (file != null) {
			InputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = file.getInputStream();
				fos = new FileOutputStream(new File(filePath + uuid + ext));
				byte[] buf = new byte[400 * 1024];
				int len = 0;
				while ((len = fis.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}
				fis.close();
				fis = null;
				fos.close();
				fos = null;
				BidFile bidFile = new BidFile();
				bidFile.setName(fileName);
				bidFile.setPath(uuid + ext);
				bidFile.setDescription(bidForm.getFileDescription());
				SysUser user = sysUserService.getUserByName(BidUtils.getLoginedUsername());
				bidFile.setUploader(user.getId());
				
				bidFileService.insertOrUpdateBidFile(bidFile);
				BidTask bidTask  = bidTaskService.selectBidTaskById(bidForm.getTaskId());
				bidTask.setDraftFileId(bidFile.getId());
				bidTask.setChecker(user.getId());
				
				bidTaskService.updateTaskCheckin(bidTask);
				bidId = bidTask.getBidId();
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return "redirect:./tasks.html?action=view&id="+bidId.toString();
	}
	
	@PreAuthorize("hasAuthority('tasks')")
	@RequestMapping("pages/bid/tasksDocumentDownload.html")
	public void tasksDocumentDownload(HttpServletResponse response, String type, Long id) {
		String filePath = "";
		String fileName = "";
		if(type.equals("template")) {
			TemplateChapter templateChapter = templateChapterService.selectTemplateChapterById(new Long(id));
			BidFile bidfile  = bidFileService.selectBidFileById(templateChapter.getFileId());
			fileName = bidfile.getName();
			filePath = bidfile.getPath();
		}
		if(type.equals("draft")) {
			BidFile bidfile  = bidFileService.selectBidFileById(new Long(id));
			fileName = bidfile.getName();
			filePath = bidfile.getPath();
		}
		
		
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		File file = new File(env.getProperty("filePath") + filePath);
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/octet-stream");
		OutputStream ous = null;
		FileInputStream fis = null;
		try {
			ous = response.getOutputStream();
			fis = new FileInputStream(file);
			byte[] buf = new byte[400 * 1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				ous.write(buf, 0, len);
			}
			ous.flush();
			ous.close();
			ous = null;
			fis.close();
			fis = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ous != null) {
				try {
					ous.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}