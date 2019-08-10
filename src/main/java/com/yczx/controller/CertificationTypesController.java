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

import com.yczx.domain.CertificationType;
import com.yczx.service.CertificationTypeService;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class CertificationTypesController {
	@Autowired
	CertificationTypeService certificationTypeservice;

	@PreAuthorize("hasAuthority('certificationTypes')")
	@RequestMapping("pages/certification/certificationTypes.html")
	public String certificationTypes(String action, String id, Model model, CertificationType certificationType) {
		if (action == null) {
			return "pages/certification/certificationTypes";
		}
		if (action.equals("view")) {
			model.addAttribute("certificationTypes", certificationTypeservice.selectCertificationTypeById(new Long(id)));
			return "pages/certification/certificationTypesView";
		}
		if (action.equals("create")) {
			certificationType.clear();
			return "pages/certification/certificationTypesCreate";
		}
		if (action.equals("save")) {
			OperationResult<CertificationType> result = certificationTypeservice.insertOrUpdatePosition(certificationType);
			model.addAttribute("result", result);
			if(result.isError()) {
				return "pages/certification/certificationTypesCreate";
			}
			return "pages/certification/certificationTypes";
		}
		if (action.equals("edit")) {
			CertificationType c = certificationTypeservice.selectCertificationTypeById(new Long(id));
			certificationType.setId(c.getId());
			certificationType.setTitle(c.getTitle());
			certificationType.setDescription(c.getDescription());
			certificationType.setValid(c.getValid());
			return "pages/certification/certificationsCreate";
		}
		
		if (action.equals("delete")) {
			Long positionid  = new Long(id);
			certificationTypeservice.deleteCertificationType(positionid);
			return "pages/certification/certificationTypes";
		}
		return "pages/certification/certificationTypes";
	}
	
	@ResponseBody
	@PreAuthorize("hasAuthority('certificationTypes')")
	@RequestMapping(value = "pages/certification/certificationTypeList.html")
	public DataTable<Map<String, Object>> certificationTypeList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<CertificationType> certificationTypes = certificationTypeservice.selectCertificationTypesByPageParam(search, min, max, pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (CertificationType c : certificationTypes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", c.getId());
			map.put("title", c.getTitle());
			map.put("description", c.getDescription());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(certificationTypeservice.getCertificationTypeTotalCount(search).longValue());
		return data;
	}
}
