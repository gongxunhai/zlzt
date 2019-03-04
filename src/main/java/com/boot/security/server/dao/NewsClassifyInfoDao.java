package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.NewsClassifyInfo;

@Mapper
public interface NewsClassifyInfoDao {

    @Select("select * from news_classifyInfo t where t.id = #{id}")
    NewsClassifyInfo getById(Long id);

    @Delete("delete from news_classifyInfo where id = #{id}")
    int delete(Long id);

    int update(NewsClassifyInfo newsClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into news_classifyInfo(createTime, name, updateTime) values(#{createTime}, #{name}, #{updateTime})")
    int save(NewsClassifyInfo newsClassifyInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<NewsClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
