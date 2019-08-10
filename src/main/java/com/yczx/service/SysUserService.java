package com.yczx.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.MessageDao;
import com.yczx.dao.PositionDao;
import com.yczx.dao.SysPermissionDao;
import com.yczx.dao.SysRoleDao;
import com.yczx.dao.SysUserDao;
import com.yczx.domain.Message;
import com.yczx.domain.Position;
import com.yczx.domain.SysModule;
import com.yczx.domain.SysPermission;
import com.yczx.domain.SysRole;
import com.yczx.domain.SysUser;
import com.yczx.form.RoleForm;
import com.yczx.support.AuthorizedModule;
import com.yczx.support.OperationResult;

@Service
public class SysUserService {
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private SysPermissionDao permissionDao;
	@Autowired
	private MessageDao messageDao;

	public SysUser getUserByName(String userName) {
		return userDao.selectUserByName(userName);
	}

	public SysRole selectRoleById(Long id) {
		return sysRoleDao.selectRoleById(id);
	}

	public SysUser getUserById(Long id) {
		return userDao.selectUserById(id);
	}

	public ArrayList<SysUser> getUses() {
		return userDao.selectUsers();
	}

	public ArrayList<SysUser> selectUsersByPageParam(String search, Integer min, Integer max, Integer pageSize) {
		return userDao.selectUsersByPageParam(search, min, max, pageSize);
	}

	public Integer getTotalUserCount(String search) {
		return userDao.getTotalCount(search);
	}

	public ArrayList<SysRole> selectRoles() {
		return sysRoleDao.selectRoles();
	}

	public Position selectPositionById(Long id) {
		return positionDao.selectPositionById(id);
	}

	public ArrayList<Position> selectPositions() {
		return positionDao.selectPositions();
	}

	public ArrayList<Position> selectPositionByPageParam(String search, Integer min, Integer max, Integer pageSize) {
		return positionDao.selectByPageParam(search, min, max, pageSize);
	}

	public Integer getTotalPositionCount(String search) {
		return positionDao.getTotalCount(search);
	}

	public ArrayList<SysRole> selectSysRoleByPageParam(String search, Integer min, Integer max, Integer pageSize) {
		return sysRoleDao.selectRolesByPageParam(search, min, max, pageSize);
	}

	public Integer getTotalSysRoleCount(String search) {
		return sysRoleDao.getTotalRoleCount(search);
	}

	public ArrayList<Message> selectMessageByPageParam(String search, Integer min, Integer max, Integer pageSize,
			Long myUserId) {
		return messageDao.selectMessagesByPageParam(search, min, max, pageSize, myUserId);
	}

	public Integer getTotalMessageCount(String search, Integer min, Integer max, Integer pageSize, Long myUserId) {
		return messageDao.getTotalCount(search, min, max, pageSize, myUserId);
	}

	public ArrayList<SysModule> getModuleByUserName(String userName) {
		return permissionDao.selectModulesByUserName(userName);
	}

	public ArrayList<SysPermission> selectPermissionByModuleAndUserName(String userName, String module) {
		return permissionDao.selectPermissionByModuleAndUserName(userName, module);
	}

	public ArrayList<AuthorizedModule> selectAuthorizedModule() {
		ArrayList<SysModule> modules = permissionDao.selectModules();
		ArrayList<AuthorizedModule> ams = new ArrayList<AuthorizedModule>();
		for (SysModule module : modules) {
			AuthorizedModule am = new AuthorizedModule();
			am.setCode(module.getCode());
			am.setModule(module.getModule());
			am.setPermissions(permissionDao.selectPermissionByModule(am.getModule()));
			ams.add(am);
		}
		return ams;
	}

	public OperationResult<Position> insertOrUpdatePosition(Position position) {
		OperationResult<Position> result = new OperationResult<Position>();
		result.setData(position);
		if (position.getId() != null) {
			positionDao.updatePosition(position);
		} else {
			positionDao.insertPosition(position);
		}
		return result;
	}

	public OperationResult<SysUser> insertOrUpdateSysUser(SysUser user) {
		OperationResult<SysUser> result = new OperationResult<SysUser>();
		result.setData(user);
		if (user.getId() != null) {
			userDao.updateSysUser(user);
		} else {
			String username = user.getUsername();
			if (userDao.selectUserByName(username) != null) {
				result.setError(true);
				result.setMessage("用户名重复，请重新选择");
				return result;
			} else {
				userDao.insertSysUser(user);
				result.setMessage("新增成功");
			}
		}
		return result;
	}

	public void deleteUser(Long userid) {
		userDao.deleteSysUser(userid);
	}

	public OperationResult<Position> deletePositionById(Long id) {
		OperationResult<Position> result = new OperationResult<Position>();
		positionDao.deletePosition(id);
		return result;
	}

	public OperationResult<SysRole> deleteRoleByRoleId(Long id) {
		OperationResult<SysRole> result = new OperationResult<SysRole>();
		Integer usercound = userDao.getUserCountByRoleId(id);
		if (usercound > 0) {
			result.setError(true);
			result.setMessage("该角色被引用，无法删除。");
			return result;
		}
		sysRoleDao.deleteSysRole(id);
		sysRoleDao.deletePermissionsOfRole(id);
		return result;
	}

	public OperationResult<SysRole> insertOrUpdateSysRole(RoleForm roleForm) {
		SysRole role = new SysRole();
		role.setId(roleForm.getId());
		role.setRoleCode(roleForm.getRoleCode());
		role.setRoleName(roleForm.getRoleName());
		OperationResult<SysRole> result = new OperationResult<SysRole>();
		result.setData(role);

		if (roleForm.getPermissionIds() == null || roleForm.getPermissionIds().size() == 0) {
			result.setError(true);
			result.setMessage("该角色没有赋予权限，请重新选择");
			return result;
		}

		if (role.getId() != null) {
			sysRoleDao.updateSysRole(role);
		} else {
			sysRoleDao.insertSysRole(role);
		}

		Long id = role.getId();
		sysRoleDao.deletePermissionsOfRole(id);
		for (Long pid : roleForm.getPermissionIds()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", id);
			map.put("permissionId", pid);
			sysRoleDao.insertSysRoleAndPermission(map);
		}
		return result;
	}

	public ArrayList<SysPermission> selectPermissions() {
		return permissionDao.selectPermissions();
	}

	public Message selectMessageById(Long id) {
		return messageDao.selectMessageById(id);
	}

	public OperationResult<Message> insertOrUpdateMessage(Message message) {
		if (message.getToPosition() == null || message.getToPosition().longValue() == -1) {
			message.setToPosition(null);
		}
		if (message.getToUser() == null || message.getToUser().longValue() == -1) {
			message.setToUser(null);
		}
		if (message.getId() != null) {
			messageDao.updateMessage(message);
		} else {
			messageDao.insertMessage(message);
		}

		OperationResult<Message> result = new OperationResult<Message>();
		result.setData(message);
		return result;
	}

	public OperationResult<Long> deleteMessageById(Long messageid) {
		messageDao.deleteMessage(messageid);
		messageDao.deleteMessageRead(messageid);

		OperationResult<Long> result = new OperationResult<Long>();
		result.setData(messageid);
		return result;
	}

	public Integer getMessageReadCount(Long readerId) {
		return messageDao.getReadCount(readerId);
	}

	public ArrayList<Message> getMyUnReadMessages(Long readerId) {
		ArrayList<Message> unReadMessages = new ArrayList<Message>();
		ArrayList<Message> myMessages = messageDao.selectMessagesSentToMe(readerId);
		for (Message message : myMessages) {
			if (!isThisMessageRead(message.getId(), readerId)) {
				unReadMessages.add(message);
			}
		}

		return unReadMessages;
	}

	public void updateMessageReadStatus(Long messageId, Long myUserId) {
		if (messageDao.isThisMessageRead(messageId, myUserId) == 0) {
			messageDao.updateMessageReadStatus(myUserId, messageId);
		}
	}

	public Boolean isThisMessageRead(Long messageId, Long readerId) {
		if (messageDao.isThisMessageRead(messageId, readerId) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<SysUser> selectUsersByUserIds(String userIds){
		ArrayList<Long> userIdList = new ArrayList<Long>();
		if(userIds!=null && userIds.length()>0) {
			if(userIds.indexOf(",")!=-1) {
				String[] ses = userIds.split(",");
				for(String s : ses) {
					s = s.trim();
					userIdList.add(new Long(s));
				}
			}
			else {
				userIdList.add(new Long(userIds.trim()));
			}
		}
		
		return userDao.selectUsersByUserIds(userIdList);
	}
}
