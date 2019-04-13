package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjServiceNavClassifyinfo;

@Mapper
public interface KjServiceNavClassifyinfoDao {

    @Select("select * from kj_service_nav_classifyinfo t where t.id = #{id}")
    KjServiceNavClassifyinfo getById(Long id);

    @Delete("delete from kj_service_nav_classifyinfo where id = #{id}")
    int delete(Long id);

    int update(KjServiceNavClassifyinfo kjServiceNavClassifyinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_service_nav_classifyinfo(name, type, parentId, createTime) values(#{name}, #{type}, #{parentId}, #{createTime})")
    int save(KjServiceNavClassifyinfo kjServiceNavClassifyinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjServiceNavClassifyinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from kj_service_nav_classifyinfo where parentId = #{parentId} ")
    List<KjServiceNavClassifyinfo> getClassifyByParentId(@Param("parentId") int parentId);

    @Select("select * from kj_service_nav_classifyinfo t order by t.type")
    List<KjServiceNavClassifyinfo> listAll();
}
