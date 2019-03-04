package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.NewsInformation;

@Mapper
public interface NewsInformationDao {

    @Select("select * from news_information t where t.id = #{id}")
    NewsInformation getById(Long id);

    @Delete("delete from news_information where id = #{id}")
    int delete(Long id);

    int update(NewsInformation newsInformation);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into news_information(content, description, createTime, name, pageView, source, type, image, updateTime) values(#{content},#{description}, #{createTime}, #{name}, #{pageView}, #{source}, #{type}, #{image}, #{updateTime})")
    int save(NewsInformation newsInformation);
    
    int count(@Param("params") Map<String, Object> params);

    List<NewsInformation> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from news_information where id = (select max(id) from news_information where type = #{type} and id < #{id})")
    NewsInformation selectUpListByTypeAndId(@Param("type") int type, @Param("id") Long id);

    @Select("select * from news_information where id = (select min(id) from news_information where type = #{type} and id > #{id})")
    NewsInformation selectDownListByTypeAndId(@Param("type") int type, @Param("id") Long id);
}
