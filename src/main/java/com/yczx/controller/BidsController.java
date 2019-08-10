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
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class BidsController {
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

	@PreAuthorize("hasAuthority('bids')")
	@RequestMapping("pages/bid/bids.html")
	public String bids(String action, String id, Model model, BidForm bidForm) {
		if (action == null) {
			return "pages/bid/bids";
		}
		if (action.equals("view")) {
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

			ArrayList<BidTask> bidTasks = bidTaskService.selectBidTasksByBid(mbid.getId());
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
				if (draftFile != null) {
					taskmap.put("drafFileAuthor", sysUserService.getUserById(draftFile.getUploader()).getSalt());
				} else {
					taskmap.put("drafFileAuthor", null);
				}
				taskmap.put("drafFileUpdateTime", draftFile == null ? null : draftFile.getUploadTime());
				taskmap.put("startTime", task.getStartTime());
				taskmap.put("endTime", task.getEndTime());
				if (task.getCheckedOut() == null || task.getCheckedOut() == 0) {
					taskmap.put("checkedOut", "正常");
				} else {
					taskmap.put("checkedOut", "锁定");
				}
				taskmap.put("submitTime", task.getSubmitTime());
				taskmap.put("submitComment", task.getSubmitComment());
				taskmap.put("approver", task.getApprover());
				taskmap.put("approveStatus", task.getApproveStatus());
				if( task.getApproveStatus()!=null &&  task.getApproveStatus().equals("通过")) {
					taskmap.put("isApproved", new Boolean(true));
				}
				else {
					taskmap.put("isApproved", new Boolean(false));
				}
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
			return "pages/bid/bidsView";
		}

		if (action.equals("create")) {
			bidForm.clear();
			ArrayList<Template> templates = templateService.selectTemplates();
			model.addAttribute("templates", templates);
			ArrayList<SysUser> managers = sysUserService.getUses();
			ArrayList<Map<String, Object>> managerList = new ArrayList<Map<String, Object>>();
			for (SysUser m : managers) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", m.getId());
				map.put("name", m.getSalt());
				map.put("position", m.getPosition().getPositionName());
				managerList.add(map);
			}
			model.addAttribute("managerList", managerList);

			return "pages/bid/bidsCreate";
		}

		if (action.equals("edit")) {
			Bid bid = bidService.selectBidById(new Long(id));
			bidForm.clear();
			bidForm.setBidId(bid.getId());
			bidForm.setBidName(bid.getName());
			bidForm.setBidDescription(bid.getDescription());
			bidForm.setBidStartTime(bid.getStartTime());
			bidForm.setBidEndTime(bid.getEndTime());

			bidForm.setBidTemplateId(bid.getTemplateId());
			ArrayList<Long> mgts = new ArrayList<Long>();
			ArrayList<SysUser> managerUsers = sysUserService.selectUsersByUserIds(bid.getManager());
			for (SysUser m : managerUsers) {
				mgts.add(m.getId());
			}
			bidForm.setBidManager(mgts);

			ArrayList<Template> templates = templateService.selectTemplates();
			model.addAttribute("templates", templates);
			ArrayList<SysUser> managers = sysUserService.getUses();
			ArrayList<Map<String, Object>> managerList = new ArrayList<Map<String, Object>>();
			for (SysUser m : managers) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", m.getId());
				map.put("name", m.getSalt());
				map.put("position", m.getPosition().getPositionName());
				managerList.add(map);
			}
			model.addAttribute("managerList", managerList);
			return "pages/bid/bidsCreate";
		}

		if (action.equals("save")) {
			Bid bid = new Bid();
			if (bidForm.getBidId() != null) {
				Bid oldBid = bidService.selectBidById(bidForm.getBidId());
				bid.setId(bidForm.getBidId());
				bid.setTemplateId(oldBid.getTemplateId());
			}
			bid.setDescription(bidForm.getBidDescription());
			bid.setStartTime(bidForm.getBidStartTime());
			bid.setEndTime(bidForm.getBidEndTime());
			String manager = "";
			for (Long managerId : bidForm.getBidManager()) {
				manager += managerId + ", ";
			}
			if (manager.length() > 0) {
				manager = manager.substring(0, manager.length() - 2);
			}
			bid.setManager(manager);
			bid.setName(bidForm.getBidName());
			bid.setTemplateId(bidForm.getBidTemplateId());
			OperationResult<Bid> result = bidService.insertOrUpdateBid(bid);
			model.addAttribute("result", result);
			if (result.isError()) {
				return "pages/bid/bidsCreate";
			}
			return "pages/bid/bids";
		}

		if (action.equals("taskEdit")) {
			BidTask t = bidTaskService.selectBidTaskById(new Long(id));
			ArrayList<Long> editors = new ArrayList<Long>();
			ArrayList<SysUser> editorUsers = sysUserService.selectUsersByUserIds(t.getEditorIds());
			for (SysUser m : editorUsers) {
				editors.add(m.getId());
			}
			bidForm.setTaskId(new Long(id));
			bidForm.setBidId(t.getBidId());
			bidForm.setTaskEditorIds(editors);
			bidForm.setTaskEndTime(t.getEndTime());
			bidForm.setTaskStartTime(t.getStartTime());

			ArrayList<SysUser> allUsers = sysUserService.getUses();
			ArrayList<Map<String, Object>> editorsList = new ArrayList<Map<String, Object>>();
			for (SysUser m : allUsers) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", m.getId());
				map.put("name", m.getSalt());
				map.put("position", m.getPosition().getPositionName());
				editorsList.add(map);
			}
			model.addAttribute("managerList", editorsList);
			return "pages/bid/bidTasksCreate";
		}
		if (action.equals("saveTask")) {
			BidTask bidTask = new BidTask();
			if (bidForm.getTaskId() != null) {
				bidTask.setId(bidForm.getTaskId());
			}

			bidTask.setStartTime(bidForm.getTaskStartTime());
			bidTask.setEndTime(bidForm.getTaskEndTime());
			String editor = "";
			for (Long editorId : bidForm.getTaskEditorIds()) {
				editor += editorId + ", ";
			}
			if (editor.length() > 0) {
				editor = editor.substring(0, editor.length() - 2);
			}
			bidTask.setEditorIds(editor);

			OperationResult<BidTask> result = bidTaskService.updateTaskbyAdmin(bidTask);
			model.addAttribute("result", result);
			if (result.isError()) {
				ArrayList<SysUser> allUsers = sysUserService.getUses();
				ArrayList<Map<String, Object>> editorsList = new ArrayList<Map<String, Object>>();
				for (SysUser m : allUsers) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", m.getId());
					map.put("name", m.getSalt());
					map.put("position", m.getPosition().getPositionName());
					editorsList.add(map);
				}
				model.addAttribute("managerList", editorsList);
				return "pages/bid/bidTasksCreate";
			}
			return bids("view", bidTaskService.selectBidTaskById(bidTask.getId()).getBidId().toString(), model,
					bidForm);
		}
		if (action.equals("approveTask")) {
			BidTask bidTask = bidTaskService.selectBidTaskById(new Long(id));
			bidForm.setTaskId(bidTask.getId());
			bidForm.setBidId(bidTask.getBidId());

			return "pages/bid/bidTasksApprove";
		}
		if (action.equals("approveTaskSave")) {
			BidTask bidTask = bidTaskService.selectBidTaskById(bidForm.getTaskId());
			bidTask.setApproveComment(bidForm.getTaskApproveComment());
			bidTask.setApproveStatus(bidForm.getTaskApproveStatus());
			bidTask.setApprover(sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId());
			bidTaskService.updateTaskApprove(bidTask);
			return "redirect:./bids.html?action=view&id=" + bidTask.getBidId();
		}
		if (action.equals("delete")) {
			Long bidid = new Long(id);
			bidService.deleteBid(bidid);
			return "pages/bid/bids";
		}

		return "pages/bid/bids";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('bids')")
	@RequestMapping(value = "pages/bid/bidList.html")
	public DataTable<Map<String, Object>> bidList(PageParam pageParam) {
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

	@PreAuthorize("hasAuthority('bids')")
	@RequestMapping("pages/bid/bidsDocumentDownload.html")
	public void bidsDocumentDownload(HttpServletResponse response, String type, Long id) {
		if (type.equals("template")) {
			TemplateChapter templateChapter = templateChapterService.selectTemplateChapterById(new Long(id));
			BidFile bidFile = bidFileService.selectBidFileById(templateChapter.getFileId());
			BidUtils.downloadBidFile(bidFile, response, env.getProperty("filePath"));
		}
		if (type.equals("draft")) {
			BidTask task = bidTaskService.selectBidTaskById(new Long(id));
			BidFile bidFile = bidFileService.selectBidFileById(task.getDraftFileId());
			BidUtils.downloadBidFile(bidFile, response, env.getProperty("filePath"));
		}
	}
}
