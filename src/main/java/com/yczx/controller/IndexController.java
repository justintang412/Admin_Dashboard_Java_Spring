package com.yczx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yczx.domain.BidFile;
import com.yczx.service.BidFileService;
import com.yczx.service.BidTaskService;
import com.yczx.service.SysUserService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;
import com.yczx.support.PageParam;

@Controller
public class IndexController {
	@Autowired
	BidTaskService bidTaskService;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	Environment env;
	@Autowired
	BidFileService bidFileService;
	
	@RequestMapping("pages/common/index.html")
	public String index(Model model) {
		Map<String, Object> map = bidTaskService
				.taskStatistics(sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId());
		model.addAttribute("delayedTaskCount", map.get("delayedTaskCount"));
		model.addAttribute("doneTaskCount", map.get("doneTaskCount"));
		model.addAttribute("undergoingTaskCount", map.get("undergoingTaskCount"));
		return "pages/common/index";
	}
	
	@RequestMapping("pages/common/download.html")
	public void download(HttpServletResponse response, Long id) {
		BidFile bf = bidFileService.selectBidFileById(id);
		if (bf == null) {
			return;
		}
		BidUtils.downloadBidFile(bf, response, env.getProperty("filePath"));
	}


	@RequestMapping("index.html")
	public String initIindex(Model model) {
		Map<String, Object> map = bidTaskService
				.taskStatistics(sysUserService.getUserByName(BidUtils.getLoginedUsername()).getId());
		model.addAttribute("delayedTaskCount", map.get("delayedTaskCount"));
		model.addAttribute("doneTaskCount", map.get("doneTaskCount"));
		model.addAttribute("undergoingTaskCount", map.get("undergoingTaskCount"));
		return "pages/common/index";
	}

	@ResponseBody
	@RequestMapping(value = "pages/common/fileList.html")
	public DataTable<Map<String, Object>> bidList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
			List<Map<String, Object>> rowsList = new ArrayList<>();
			data.setData(rowsList);
			data.setDraw(pageParam.getDraw());
			data.setTotalCount(rowsList.size());
			return data;
		}
		else {
			return querySolr(pageParam, search);
		}
	}

	private DataTable<Map<String, Object>> querySolr(PageParam pageParam, String search) {
		HttpSolrClient.Builder builder = new HttpSolrClient.Builder();
		builder.withBaseSolrUrl(env.getProperty("solr.host.bid"));
		HttpSolrClient solrClient = builder.build();

		SolrQuery query = new SolrQuery();
	
		int min = pageParam.getStart();
		int pagesize = pageParam.getPagesize();
		query.setStart(min);
		query.setRows(pagesize);
		
		query.set("q", search);
		
		QueryResponse rsp = null;
		try {
		   rsp = solrClient.query( query );
		} catch (Exception e) {
		   e.printStackTrace();
		}
		SolrDocumentList results = rsp.getResults();
		
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (SolrDocument doc : results) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", doc.getFieldValue("id"));
			BidFile bidFile  = bidFileService.selectBidFileByPath(doc.getFieldValue("id").toString());
			if(bidFile==null)
				continue;
			else {
				map.put("type", bidFile.getDescription());
				map.put("fileId", bidFile.getId());
				map.put("creator", sysUserService.getUserById(bidFile.getUploader()).getSalt());
				map.put("uploadTime", bidFile.getUploadTime());
				map.put("name", bidFile.getName());
			}
			
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(results.getNumFound());
		return data;
	}
}
