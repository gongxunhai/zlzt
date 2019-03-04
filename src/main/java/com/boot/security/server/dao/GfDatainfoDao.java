package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.ZlztDataDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.GfDatainfo;

@Mapper
public interface GfDatainfoDao {

    @Select("select * from gf_datainfo t where t.id = #{id}")
    GfDatainfo getById(Long id);

    @Delete("delete from gf_datainfo where id = #{id}")
    int delete(Long id);

    int update(GfDatainfo gfDatainfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into gf_datainfo(dataId, fId, sId, tId, cId, createTime, updateTime) values(#{dataId}, #{fId}, #{sId}, #{tId}, #{cId}, #{createTime}, #{updateTime})")
    int save(GfDatainfo gfDatainfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<GfDatainfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from gf_datainfo where dataId = #{dataId}")
    int selectIdByDataId(GfDatainfo gfDatainfo);
}
