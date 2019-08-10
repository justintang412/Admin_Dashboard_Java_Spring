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
import com.yczx.domain.Material;
import com.yczx.domain.MaterialType;
import com.yczx.service.BidFileService;
import com.yczx.service.MaterialService;
import com.yczx.service.MaterialTypeService;
import com.yczx.service.SysUserService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;
import com.yczx.support.PageParam;

@Controller
public class MaterialsController {
	@Autowired
	MaterialService materialService;
	@Autowired
	MaterialTypeService materialTypeService;
	@Autowired
	private Environment env;
	@Autowired
	private SysUserService userservice;
	@Autowired
	BidFileService bidFileService;

	@PreAuthorize("hasAuthority('materials')")
	@RequestMapping("pages/material/materials.html")
	public String materials(String action, String id, Model model, Material material) {
		if (action == null) {
			return "pages/material/materials";
		}
		if (action.equals("view")) {
			model.addAttribute("materials", materialService.selectMaterialById(new Long(id)));
			return "pages/material/materialsView";
		}
		if (action.equals("create")) {
			material.clear();
			ArrayList<MaterialType> materialTypes = materialTypeService.selectMaterialTypes();
			model.addAttribute("materialTypes", materialTypes);
			return "pages/material/materialsCreate";
		}

		if (action.equals("edit")) {
			Material c = materialService.selectMaterialById(new Long(id));
			material.setDescription(c.getDescription());
			material.setCreateTime(c.getCreateTime());
			material.setId(c.getId());
			material.setName(c.getName());
			material.setType(c.getType());
			material.setValid(c.getValid());
			ArrayList<MaterialType> materialTypes = materialTypeService.selectMaterialTypes();
			model.addAttribute("materialTypes", materialTypes);
			return "pages/material/materialsCreate";
		}

		if (action.equals("delete")) {
			Long materialid = new Long(id);
			materialService.deleteMaterial(materialid);
			return "pages/material/materials";
		}
		return "pages/material/materials";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('materials')")
	@RequestMapping(value = "pages/material/materialList.html")
	public DataTable<Map<String, Object>> userList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<Material> materials = materialService.selectMaterialsByPageParam(search, min, max, pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Material material : materials) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", material.getId());
			map.put("description", material.getDescription());
			map.put("createTime", material.getCreateTime());
			if (material.getFileId() != null) {
				BidFile bidFile = bidFileService.selectBidFileById(material.getFileId());
				map.put("fileName", bidFile.getName());
				map.put("filePath", bidFile.getPath());
				map.put("uploader", userservice.getUserById(bidFile.getUploader()).getSalt());
			} else {
				map.put("fileName", "");
				map.put("filePath", "");
				map.put("uploader", "");
			}

			map.put("name", material.getName());
			map.put("type", materialTypeService.selectMaterialTypeById(material.getType()).getTitle());

			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(materialService.getMaterialTotalCount(search).longValue());
		return data;
	}

	@PreAuthorize("hasAuthority('materials')")
	@RequestMapping("pages/material/materialsDownload.html")
	public void materialsDownload(HttpServletResponse response, Long id) {
		Material c = materialService.selectMaterialById(id);
		if (c != null) {
			BidFile bidFile = bidFileService.selectBidFileById(c.getFileId());
			if (bidFile != null) {
				BidUtils.downloadBidFile(bidFile, response, env.getProperty("filePath"));
			}
		}
	}

	@PreAuthorize("hasAuthority('materials')")
	@RequestMapping("pages/material/materialsUpload.html")
	public String materials(HttpServletResponse response, Material material, @RequestParam("file") MultipartFile file) {
		if (file != null) {
			BidFile newBidFile = BidUtils.uploadBidFile(env.getProperty("filePath"),
					userservice.getUserByName(BidUtils.getLoginedUsername()).getId(), file, bidFileService, "素材文件");
			if (newBidFile != null) {
				if (material.getId() != null && material.getFileId() != null) {
					BidFile oldFile = bidFileService.selectBidFileById(material.getFileId());
					bidFileService.updateBidFileToDeleted(oldFile);
				}
				material.setFileId(newBidFile.getId());
				materialService.insertOrUpdateMaterial(material);
			}
		}

		return "redirect:materials.html";
	}
}
