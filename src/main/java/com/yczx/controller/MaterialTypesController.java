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

import com.yczx.domain.MaterialType;
import com.yczx.service.MaterialTypeService;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class MaterialTypesController {
	@Autowired
	MaterialTypeService materialTypeService;

	@PreAuthorize("hasAuthority('materialTypes')")
	@RequestMapping("pages/material/materialTypes.html")
	public String materialTypes(String action, String id, Model model, MaterialType materialType) {
		if (action == null) {
			return "pages/material/materialTypes";
		}
		if (action.equals("view")) {
			model.addAttribute("materialTypes", materialTypeService.selectMaterialTypeById(new Long(id)));
			return "pages/material/materialTypesView";
		}
		if (action.equals("create")) {
			materialType.clear();
			return "pages/material/materialTypesCreate";
		}
		if (action.equals("save")) {
			OperationResult<MaterialType> result = materialTypeService.insertOrUpdatePosition(materialType);
			model.addAttribute("result", result);
			if(result.isError()) {
				return "pages/material/materialTypesCreate";
			}
			return "pages/material/materialTypes";
		}
		if (action.equals("edit")) {
			MaterialType m = materialTypeService.selectMaterialTypeById(new Long(id));
			materialType.setId(m.getId());
			materialType.setTitle(m.getTitle());
			materialType.setDescription(m.getDescription());
			materialType.setType(m.getType());
			return "pages/material/materialTypesCreate";
		}
		
		if (action.equals("delete")) {
			Long positionid  = new Long(id);
			materialTypeService.deleteMaterialType(positionid);
			return "pages/material/materialTypes";
		}
		return "pages/material/materialTypes";
	}
	
	@ResponseBody
	@PreAuthorize("hasAuthority('materialTypes')")
	@RequestMapping(value = "pages/material/materialTypeList.html")
	public DataTable<Map<String, Object>> materialTypeList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pageSize = pageParam.getPagesize();
		ArrayList<MaterialType> materialTypes = materialTypeService.selectMaterialTypesByPageParam(search, min, max, pageSize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (MaterialType m : materialTypes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", m.getId());
			map.put("title", m.getTitle());
			map.put("type", m.getType());
			map.put("description", m.getDescription());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(materialTypeService.getMaterialTypeTotalCount(search).longValue());
		return data;
	}
}
