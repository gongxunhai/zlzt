package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztIpcinfo;

@Mapper
public interface ZlztIpcinfoDao {

    @Select("select * from zlzt_ipcinfo t where t.id = #{id}")
    ZlztIpcinfo getById(Long id);

    @Select("select name from zlzt_ipcinfo t where t.ipcId = #{ipcId}")
    String getByIpcId(String ipcId);

    @Delete("delete from zlzt_ipcinfo where id = #{id}")
    int delete(Long id);

    int update(ZlztIpcinfo zlztIpcinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_ipcinfo(ipcId, name) values(#{ipcId}, #{name})")
    int save(ZlztIpcinfo zlztIpcinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztIpcinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
