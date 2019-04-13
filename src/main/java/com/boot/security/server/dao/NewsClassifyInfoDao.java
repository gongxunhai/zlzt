package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.NewsClassifyinfo;

@Mapper
public interface NewsClassifyinfoDao {

    @Select("select * from news_classifyinfo t where t.id = #{id}")
    NewsClassifyinfo getById(Long id);

    @Delete("delete from news_classifyinfo where id = #{id}")
    int delete(Long id);

    int update(NewsClassifyinfo newsClassifyinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into news_classifyinfo(name, parentId, type, createTime, updateTime) values(#{name}, #{parentId}, #{type}, #{createTime}, #{updateTime})")
    int save(NewsClassifyinfo newsClassifyinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<NewsClassifyinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from news_classifyinfo t where t.parentId = #{parentId}")
    List<NewsClassifyinfo> getParentClassifyInfo(@Param("parentId") int intValue);

    @Select("select * from news_classifyinfo t order by type")
    List<NewsClassifyinfo> listAll();
}
