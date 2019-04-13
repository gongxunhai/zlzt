package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.SysDept;

@Mapper
public interface SysDeptDao {

    @Select("select * from sys_dept t where t.id = #{id}")
    SysDept getById(Long id);

    @Select("select * from sys_dept t order by t.sort")
    List<SysDept> listAll();

    @Select("select * from sys_dept t where t.parentId = #{id}")
    List<SysDept> getSubById(Long id);

    @Delete("delete from sys_dept where id = #{id}")
    int delete(Long id);

    int update(SysDept sysDept);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_dept(parentId, name, address, firstMaster, type, phone, sort, areaId, code) values(#{parentId}, #{name}, #{address}, #{firstMaster}, #{type}, #{phone}, #{sort}, #{areaId}, #{code})")
    int save(SysDept sysDept);
    
    int count(@Param("params") Map<String, Object> params);

    List<SysDept> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
