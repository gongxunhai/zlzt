package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.Entrust;

@Mapper
public interface EntrustDao {

    @Select("select t.*,m.email as username,n.val as typeName" +
            " from entrust t " +
            "left join zlzt_user m on t.userId = m.id " +
            "left join t_dict n on t.type = n.k and n.type = 'entrustType'" +
            " where t.id = #{id}")
    Entrust getById(Long id);

    @Delete("delete from entrust where id = #{id}")
    int delete(Long id);

    int update(Entrust entrust);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into entrust(contactMan, contactWay, content, createTime, file, status, type, updateTime, url, userId) values(#{contactMan}, #{contactWay}, #{content}, #{createTime}, #{file}, #{status}, #{type}, #{updateTime}, #{url}, #{userId})")
    int save(Entrust entrust);
    
    int count(@Param("params") Map<String, Object> params);

    List<Entrust> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
