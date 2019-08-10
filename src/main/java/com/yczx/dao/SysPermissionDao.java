package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.SysModule;
import com.yczx.domain.SysPermission;

@Mapper
public interface SysPermissionDao {
	SysPermission selectPermissionById(Long id);

	SysPermission selectPermissionByUrl(String url);

	ArrayList<SysPermission> selectPermissionByModuleAndUserName(@Param("username") String username, @Param("modulename") String module);

	ArrayList<SysModule> selectModulesByUserName(String userName);
	
	ArrayList<SysModule> selectModules();
	
	ArrayList<SysPermission> selectPermissionByModule(String module);
	
	ArrayList<SysPermission> selectPermissions();
}
