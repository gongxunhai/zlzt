package com.boot.security.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.security.server.dao.PermissionDao;
import com.boot.security.server.model.Permission;
import com.boot.security.server.service.PermissionService;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public void save(Permission permission) {
		permissionDao.save(permission);

		log.debug("新增菜单{}", permission.getName());
	}

	@Override
	public void update(Permission permission) {
		permissionDao.update(permission);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		List<Permission> permissions= permissionDao.getSubById(id);
		if (permissions!=null && permissions.size()>0){
			for (int i=0;i<permissions.size();i++){
				this.delete(permissions.get(i).getId());
			}
		}
		permissionDao.deleteRolePermission(id);
		permissionDao.delete(id);
		//		permissionDao.deleteByParentId(id);
		log.debug("删除菜单id:{}", id);
	}

}
