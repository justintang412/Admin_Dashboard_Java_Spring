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

import com.yczx.domain.Position;
import com.yczx.service.SysUserService;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class PositionsController {
	@Autowired
	SysUserService userservice;

	@PreAuthorize("hasAuthority('positions')")
	@RequestMapping("pages/system/positions.html")
	public String roles(String action, String id, Model model, Position position) {
		if (action == null) {
			return "pages/system/positions";
		}
		if (action.equals("view")) {
			model.addAttribute("position", userservice.selectPositionById(new Long(id)));
			return "pages/system/positionsView";
		}
		if (action.equals("create")) {
			position = new Position();
			return "pages/system/positionsCreate";
		}
		if (action.equals("save")) {
			OperationResult<Position> result = userservice.insertOrUpdatePosition(position);
			model.addAttribute("result", result);
			if(result.isError()) {
				return "pages/system/positionsCreate";
			}
			return "pages/system/positions";
		}
		if (action.equals("edit")) {
			Position p = userservice.selectPositionById(new Long(id));
			position.setDataStatus(p.getDataStatus());
			position.setId(p.getId());
			position.setPositionCode(p.getPositionCode());
			position.setPositionName(p.getPositionName());
			position.setPositionSort(p.getPositionSort());
			return "pages/system/positionsCreate";
		}
		
		if (action.equals("delete")) {
			Long positionid  = new Long(id);
			OperationResult<Position> result = userservice.deletePositionById(positionid);
			model.addAttribute("result", result);
			return "pages/system/positions";
		}
		return "pages/system/positions";
	}
	
	@ResponseBody
	@PreAuthorize("hasAuthority('positions')")
	@RequestMapping(value = "pages/system/positionList.html")
	public DataTable<Map<String, Object>> userList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<Position> positions = userservice.selectPositionByPageParam(search, min, max, pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Position position : positions) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", position.getId());
			map.put("positionCode", position.getPositionCode());
			map.put("positionName", position.getPositionName());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(userservice.getTotalSysRoleCount(search).longValue());
		return data;
	}
}
