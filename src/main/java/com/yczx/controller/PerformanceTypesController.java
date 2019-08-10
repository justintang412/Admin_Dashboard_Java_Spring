package com.yczx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yczx.domain.PerformanceType;
import com.yczx.service.PerformanceTypeService;
import com.yczx.support.DataTable;
import com.yczx.support.PageParam;

@Controller
public class PerformanceTypesController {
	@Autowired
	PerformanceTypeService performanceTypeService;

	@PreAuthorize("hasAuthority('performanceTypes')")
	@RequestMapping("pages/performance/performanceTypes.html")
	public String performanceTypes(String action, String id, Model model, PerformanceType performanceType) {
		if (action == null) {
			return "pages/performance/performanceTypes";
		}
		if (action.equals("view")) {
			model.addAttribute("performanceTypes", performanceTypeService.selectPerformanceTypeById(new Long(id)));
			return "pages/performance/performanceTypesView";
		}
		if (action.equals("create")) {
			performanceType.clear();
			return "pages/performance/performanceTypesCreate";
		}
		if (action.equals("save")) {
			performanceTypeService.insertPerformanceType(performanceType);

			return "pages/performance/performanceTypes";
		}
		if (action.equals("edit")) {
			PerformanceType p = performanceTypeService.selectPerformanceTypeById(new Long(id));
			performanceType.setId(p.getId());
			performanceType.setTitle(p.getTitle());
			performanceType.setDescription(p.getDescription());
			return "pages/performance/performanceTypesCreate";
		}

		if (action.equals("delete")) {
			performanceTypeService.deletePerformanceType(new Long(id));
			return "pages/performance/performanceTypes";
		}
		return "pages/performance/performanceTypes";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('performanceTypes')")
	@RequestMapping(value = "pages/performance/performanceTypeList.html")
	public DataTable<Map<String, Object>> performanceTypeList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pageSize = pageParam.getPagesize();
		ArrayList<PerformanceType> performanceTypes = performanceTypeService.selectPerformanceTypesByPageParam(search,
				min, max, pageSize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (PerformanceType performanceType : performanceTypes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", performanceType.getId());
			map.put("title", performanceType.getTitle());
			map.put("description", performanceType.getDescription());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(performanceTypeService.getPerformanceTypeTotalCount(search).longValue());
		return data;
	}
}
