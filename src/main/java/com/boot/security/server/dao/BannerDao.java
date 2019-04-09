package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.Banner;

@Mapper
public interface BannerDao {

    @Select("select * from banner t where t.id = #{id}")
    Banner getById(Long id);

    @Delete("delete from banner where id = #{id}")
    int delete(Long id);

    int update(Banner banner);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into banner(createTime, type, updateTime, url) values(#{createTime}, #{type}, #{updateTime}, #{url})")
    int save(Banner banner);
    
    int count(@Param("params") Map<String, Object> params);

    List<Banner> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
