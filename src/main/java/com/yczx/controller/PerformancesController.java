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
import com.yczx.domain.Performance;
import com.yczx.domain.PerformanceType;
import com.yczx.service.BidFileService;
import com.yczx.service.PerformanceService;
import com.yczx.service.PerformanceTypeService;
import com.yczx.service.SysUserService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;
import com.yczx.support.PageParam;

@Controller
public class PerformancesController {
	@Autowired
	PerformanceService performanceService;
	@Autowired
	PerformanceTypeService performanceTypeService;
	@Autowired
	private Environment env;
	@Autowired
	BidFileService bidFileService;
	@Autowired
	SysUserService sysUserService;

	@PreAuthorize("hasAuthority('performances')")
	@RequestMapping("pages/performance/performances.html")
	public String performances(String action, String id, Model model, Performance performance) {
		if (action == null) {
			return "pages/performance/performances";
		}
		if (action.equals("view")) {
			model.addAttribute("performances", performanceService.selectPerformanceById(new Long(id)));
			return "pages/performance/performancesView";
		}
		if (action.equals("create")) {
			performance.clear();
			ArrayList<PerformanceType> performanceTypes = performanceTypeService.selectPerformanceTypes();
			model.addAttribute("performanceTypes", performanceTypes);
			return "pages/performance/performancesCreate";
		}

		if (action.equals("edit")) {
			Performance c = performanceService.selectPerformanceById(new Long(id));
			performance.setDescription(c.getDescription());
			performance.setCustomer(c.getCustomer());
			performance.setId(c.getId());
			performance.setName(c.getName());
			performance.setType(c.getType());
			performance.setValid(c.getValid());
			performance.setPrice(c.getPrice());
			performance.setBeginDate(c.getBeginDate());
			performance.setEndDate(c.getEndDate());
			performance.setType(c.getType());
			performance.setManager(c.getManager());
			ArrayList<PerformanceType> performanceTypes = performanceTypeService.selectPerformanceTypes();
			model.addAttribute("performanceTypes", performanceTypes);
			return "pages/performance/performancesCreate";
		}

		if (action.equals("delete")) {
			Long performanceid = new Long(id);
			performanceService.deletePerformance(performanceid);
			return "pages/performance/performances";
		}
		return "pages/performance/performances";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('performances')")
	@RequestMapping(value = "pages/performance/performanceList.html")
	public DataTable<Map<String, Object>> userList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<Performance> performances = performanceService.selectPerformancesByPageParam(search, min, max,
				pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Performance performance : performances) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", performance.getId());
			map.put("description", performance.getDescription());
			map.put("beginDate", performance.getBeginDate());
			map.put("endDate", performance.getEndDate());
			if (performance.getFileId() != null) {
				BidFile bidFile = bidFileService.selectBidFileById(performance.getFileId());
				map.put("fileName", bidFile.getName());
				map.put("filePath", bidFile.getPath());
			} else {
				map.put("fileName", "");
				map.put("filePath", "");
			}
			map.put("name", performance.getName());
			map.put("customer", performance.getCustomer());
			map.put("manager", performance.getManager());
			map.put("price", performance.getPrice());
			map.put("type", performanceTypeService.selectPerformanceTypeById(performance.getType()).getTitle());

			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(performanceService.getPerformanceTotalCount(search).longValue());
		return data;
	}

	@PreAuthorize("hasAuthority('performances')")
	@RequestMapping("pages/performance/performancesDownload.html")
	public void performancesDownload(HttpServletResponse response, Long id) {
		Performance c = performanceService.selectPerformanceById(id);
		if (c != null) {
			BidFile bidFile = bidFileService.selectBidFileById(c.getFileId());
			BidUtils.downloadBidFile(bidFile, response, env.getProperty("filePath"));
		}
	}

	@PreAuthorize("hasAuthority('performances')")
	@RequestMapping("pages/performance/performancesUpload.html")
	public String performances(HttpServletResponse response, Performance performance,
			@RequestParam("file") MultipartFile file) {
		BidFile bidFile = BidUtils.uploadBidFile(env.getProperty("filePath"),
				sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId(), file, bidFileService, "业绩文件");
		if(performance.getId()!=null) {
			Performance oldPerformance = performanceService.selectPerformanceById(performance.getId());
			if(oldPerformance!=null && oldPerformance.getFileId()!=null) {
				bidFileService.updateBidFileToDeletedById(oldPerformance.getFileId());
			}
		}
		
		performance.setFileId(bidFile.getId());
		performanceService.insertOrUpdatePerformance(performance);

		return "redirect:performances.html";
	}
}
