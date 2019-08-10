package com.yczx.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.SysRole;

@Mapper
public interface SysRoleDao {
	SysRole selectRoleById(Long id);

	ArrayList<SysRole> selectRoles();

	void insertSysRole(SysRole sysrole);

	void updateSysRole(SysRole sysrole);

	void deleteSysRole(Long roleid);
	
	void insertSysRoleAndPermission(Map<String, Object> map);
	
	ArrayList<SysRole> selectRolesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getTotalRoleCount(@Param("search") String search);
	
	void deletePermissionsOfRole(Long roleid);
}
