package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztContactUs;

@Mapper
public interface ZlztContactUsDao {

    @Select("select * from zlzt_contact_us t where t.id = #{id}")
    ZlztContactUs getById(Long id);

    @Delete("delete from zlzt_contact_us where id = #{id}")
    int delete(Long id);

    int update(ZlztContactUs zlztContactUs);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_contact_us(phone, address, mail, createTime, updateTime) values(#{phone}, #{address}, #{mail}, #{createTime}, #{updateTime})")
    int save(ZlztContactUs zlztContactUs);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztContactUs> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
