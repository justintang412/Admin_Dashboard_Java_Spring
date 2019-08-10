package com.yczx.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yczx.domain.Message;
import com.yczx.domain.SysUser;
import com.yczx.service.SysUserService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;

@Component
public class MyFilter implements Filter {

	SysUserService userservice;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (userservice == null) {
			ServletContext servletContext = request.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			userservice = webApplicationContext.getBean(SysUserService.class);
		}
		SysUser loginedUser = userservice.getUserByName(BidUtils.getLoginedUsername());

		ArrayList<Message> messages = userservice.getMyUnReadMessages(loginedUser.getId());
		List<Map<String, Object>> rowsList = new ArrayList<>();

		for (Message message : messages) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", message.getId());
			map.put("title", message.getTitle());
			map.put("message", message.getMessage());
			map.put("creator", userservice.getUserById(message.getCreator()).getSalt());
			map.put("createTime", message.getCreateTime());

			if (message.getToPosition() != null) {
				map.put("toPosition", userservice.selectPositionById(message.getToPosition()).getPositionName());

			} else {
				map.put("toPosition", "-");
			}
			if (message.getToUser() != null) {
				map.put("toUser", userservice.getUserById(message.getToUser()).getSalt());

			} else {
				map.put("toUser", "-");
			}

			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setTotalCount(rowsList.size());

		((HttpServletRequest) request).getSession(true).setAttribute("unReadMessageDataTable", data);

		chain.doFilter(request, response);
		System.out.println("------------------------------" + ((HttpServletRequest) request).getRequestURL()
				+ "----------------" + data.getTotalCount());
	}

}
