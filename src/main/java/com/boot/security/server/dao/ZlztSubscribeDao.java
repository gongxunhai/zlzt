package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztSubscribe;

@Mapper
public interface ZlztSubscribeDao {

    @Select("select * from zlzt_subscribe t where t.id = #{id}")
    ZlztSubscribe getById(Long id);

    @Delete("delete from zlzt_subscribe where id = #{id}")
    int delete(Long id);

    int update(ZlztSubscribe zlztSubscribe);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_subscribe(userid, classifyId, createTime) values(#{userid}, #{classifyId}, #{createTime})")
    int save(ZlztSubscribe zlztSubscribe);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztSubscribe> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
