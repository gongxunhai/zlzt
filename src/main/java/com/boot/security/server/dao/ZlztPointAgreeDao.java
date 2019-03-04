package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztPointAgree;

@Mapper
public interface ZlztPointAgreeDao {

    @Select("select * from zlzt_point_agree t where t.id = #{id}")
    ZlztPointAgree getById(Long id);

    @Delete("delete from zlzt_point_agree where id = #{id}")
    int delete(Long id);

    int update(ZlztPointAgree zlztPointAgree);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_point_agree(userid, agreeId, type, createTime, updateTime) values(#{userid}, #{agreeId}, #{type}, #{createTime}, #{updateTime})")
    int save(ZlztPointAgree zlztPointAgree);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztPointAgree> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
