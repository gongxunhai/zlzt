package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztCareinfo;

@Mapper
public interface ZlztCareinfoDao {

    @Select("select * from zlzt_careinfo t where t.id = #{id}")
    ZlztCareinfo getById(Long id);

    @Delete("delete from zlzt_careinfo where id = #{id}")
    int delete(Long id);

    int update(ZlztCareinfo zlztCareinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_careinfo(userid, careId, type, createTime, updateTime) values(#{userid}, #{careId}, #{type}, #{createTime}, #{updateTime})")
    int save(ZlztCareinfo zlztCareinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztCareinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
