package com.yczx.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {
	@Bean
	public FilterRegistrationBean<MyFilter> filter1RegistrationBean() {
		FilterRegistrationBean<MyFilter> frb = new FilterRegistrationBean<MyFilter>();
		frb.setFilter(new MyFilter());
		frb.addUrlPatterns("/pages/common/index.html", "/pages/system/*", "/pages/bid/*", "/pages/certification/*",
				"/pages/material/*", "/pages/performance/*", "/pages/share/*", "/pages/template/*");
		return frb;
	}
}
