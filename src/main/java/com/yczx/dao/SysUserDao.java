package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.SysUser;

@Mapper
public interface SysUserDao {
	SysUser selectUserById(Long id);

	SysUser selectUserByName(String username);

	ArrayList<SysUser> selectUsersByUserIds(@Param("userIdList") ArrayList<Long> userIdList);

	ArrayList<SysUser> selectUsers();

	ArrayList<SysUser> selectUsersByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getTotalCount(@Param("search") String search);

	void insertSysUser(SysUser sysuser);

	void deleteSysUser(Long id);

	void updateSysUser(SysUser sysuser);

	Integer getUserCountByRoleId(@Param("id") Long id);
}
