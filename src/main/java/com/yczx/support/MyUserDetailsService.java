package com.yczx.support;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yczx.domain.SysModule;
import com.yczx.domain.SysPermission;
import com.yczx.domain.SysUser;
import com.yczx.service.SysUserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = userService.getUserByName(username);
		if (null == sysUser) {
			throw new UsernameNotFoundException(username);
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		ArrayList<SysModule> modules = userService.getModuleByUserName(username);

		ArrayList<AuthorizedModule> amodules = new ArrayList<AuthorizedModule>();

		if (modules != null) {
			for (SysModule module : modules) {

				ArrayList<SysPermission> permissions = userService.selectPermissionByModuleAndUserName(username,
						module.getModule());
				if (permissions != null) {
					AuthorizedModule amodule = new AuthorizedModule();
					amodule.setModule(module.getModule());
					amodule.setCode(module.getCode());
					amodule.setPermissions(permissions);
					amodules.add(amodule);

					for (SysPermission permission : permissions) {
						if (permission.getCode() != null) {
							authorities.add(new SimpleGrantedAuthority(permission.getCode()));
						}
					}
				}
			}
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("mySysUser", sysUser);
		session.setAttribute("amodules", amodules);

		return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
	}
}
