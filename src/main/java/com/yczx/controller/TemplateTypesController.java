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

import com.yczx.domain.TemplateType;
import com.yczx.service.TemplateTypeService;
import com.yczx.support.DataTable;
import com.yczx.support.PageParam;

@Controller
public class TemplateTypesController {
	@Autowired
	TemplateTypeService templateTypeservice;

	@PreAuthorize("hasAuthority('templateTypes')")
	@RequestMapping("pages/template/templateTypes.html")
	public String templateTypes(String action, String id, Model model, TemplateType templateType) {
		if (action == null) {
			return "pages/template/templateTypes";
		}
		if (action.equals("view")) {
			model.addAttribute("templateTypes", templateTypeservice.selectTemplateTypeById(new Long(id)));
			return "pages/template/templateTypesView";
		}
		if (action.equals("create")) {
			templateType.clear();
			return "pages/template/templateTypesCreate";
		}
		if (action.equals("save")) {
			templateTypeservice.insertTemplateType(templateType);

			return "pages/template/templateTypes";
		}
		if (action.equals("edit")) {
			TemplateType t = templateTypeservice.selectTemplateTypeById(new Long(id));
			templateType.setId(t.getId());
			templateType.setTitle(t.getTitle());
			templateType.setDescription(t.getDescription());
			return "pages/template/templateTypesCreate";
		}

		if (action.equals("delete")) {
			templateTypeservice.deleteTemplateType(new Long(id));

			return "pages/template/templateTypes";
		}
		return "pages/template/templateTypes";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('templateTypes')")
	@RequestMapping(value = "pages/template/templateTypeList.html")
	public DataTable<Map<String, Object>> templateTypeList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<TemplateType> templateTypes = templateTypeservice.selectTemplateTypesByPageParam(search, min, max,
				pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (TemplateType t : templateTypes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", t.getId());
			map.put("title", t.getTitle());
			map.put("description", t.getDescription());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(templateTypeservice.getTemplateTypeTotalCount(search).longValue());
		return data;
	}
}
