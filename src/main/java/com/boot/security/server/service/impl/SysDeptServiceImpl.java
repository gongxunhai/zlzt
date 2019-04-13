package com.boot.security.server.service.impl;

import com.boot.security.server.dao.PermissionDao;
import com.boot.security.server.dao.SysDeptDao;
import com.boot.security.server.model.Permission;
import com.boot.security.server.model.SysDept;
import com.boot.security.server.service.PermissionService;
import com.boot.security.server.service.SysDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SysDeptDao sysDeptDao;


	@Override
	@Transactional
	public void delete(Long id) {
		List<SysDept> sysDepts= sysDeptDao.getSubById(id);
		if (sysDepts!=null && sysDepts.size()>0){
			for (int i=0;i<sysDepts.size();i++){
				this.delete(sysDepts.get(i).getId());
			}
		}
		sysDeptDao.delete(id);
		log.debug("删除部门id:{}", id);
	}

}
