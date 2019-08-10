package com.yczx.config;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yczx.handler.MyAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 该方法所返回的对象的方法来配置请求级别的安全细节
				.antMatchers("/pages/common/login.html").permitAll() // 登录页面不拦截
				.antMatchers("/css/**").permitAll().antMatchers("/img/**").permitAll().antMatchers("/js/**").permitAll()
				.antMatchers("/scss/**").permitAll().antMatchers("/vendor/**").permitAll().antMatchers("/gulpfile.js")
				.permitAll().antMatchers(HttpMethod.POST, "/pages/common/checkLogin").permitAll().anyRequest()
				.authenticated()// 对于登录路径不进行拦截
				.and().formLogin()// 配置登录页面
				.loginPage("/pages/common/login.html")// 登录页面的访问路径;
				.loginProcessingUrl("/pages/common/checkLogin")// 登录页面下表单提交的路径
				.failureUrl("/pages/common/login.html?paramserror=true")// 登录失败后跳转的路径,为了给客户端提示
				.successHandler(new MyAuthenticationSuccessHandler()).and().logout()// 用户退出操作
				.logoutRequestMatcher(new AntPathRequestMatcher("/pages/common/logout", "POST"))// 用户退出所访问的路径，需要使用Post方式
				.permitAll().logoutSuccessUrl("/pages/common/login.html?logout=true")/// 退出成功所访问的路径
				.and().exceptionHandling().accessDeniedPage("/pages/common/error.html").and().csrf().disable().headers()
				.frameOptions()// 允许iframe内呈现。
				.sameOrigin().and().sessionManagement().maximumSessions(1)
				.expiredSessionStrategy(new SessionInformationExpiredStrategy() {
					@Override
					public void onExpiredSessionDetected(SessionInformationExpiredEvent event)
							throws IOException, ServletException {
						String header = event.getRequest().getHeader("X-Requested-With");
						if (header != null && header.equals("XMLHttpRequest")) {// 异步请求
							// 返回严格的json数据
							event.getResponse().getWriter()
									.write("{\"resultCode\":\"302\", \"redirectUrl\":\"login?expired=ture\"}");
						} else {
							event.getResponse().sendRedirect("/pages/common/login.html?expired=true");
						}

					}
				});
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
