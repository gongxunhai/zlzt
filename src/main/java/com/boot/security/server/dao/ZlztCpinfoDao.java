package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztCpinfo;

@Mapper
public interface ZlztCpinfoDao {

    @Select("select * from zlzt_cpinfo t where t.id = #{id}")
    ZlztCpinfo getById(Long id);

    @Select("select name from zlzt_cpinfo t where t.cpId = #{cpId}")
    String getBycpId(@Param("cpId") String cpId);

    @Delete("delete from zlzt_cpinfo where id = #{id}")
    int delete(Long id);

    int update(ZlztCpinfo zlztCpinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_cpinfo(cpId, name) values(#{cpId}, #{name})")
    int save(ZlztCpinfo zlztCpinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztCpinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
