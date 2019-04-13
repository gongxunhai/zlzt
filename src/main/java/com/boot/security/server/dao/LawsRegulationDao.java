package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.LawsRegulation;

@Mapper
public interface LawsRegulationDao {

    @Select("select t.*,b.name as fIdName,c.name as sIdName from laws_regulation t " +
            "left join laws_regulation_classifyInfo b on t.fid = b.id " +
            "left join laws_regulation_classifyInfo c on t.sid = c.id " +
            "where t.id = #{id}")
    LawsRegulation getById(Long id);

    @Delete("delete from laws_regulation where id = #{id}")
    int delete(Long id);

    int update(LawsRegulation lawsRegulation);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into laws_regulation(addFile, company, name, createTime, fId, sId, pageView, symbol, updateTime, content) values(#{addFile}, #{company}, #{name}, #{createTime}, #{fId}, #{sId}, #{pageView}, #{symbol},  #{updateTime}, #{content})")
    int save(LawsRegulation lawsRegulation);
    
    int count(@Param("params") Map<String, Object> params);

    List<LawsRegulation> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select code from tbl_area where customaryId = #{customaryId}")
    String getCodeBycustomaryId(@Param("customaryId") String customaryId);

    @Select("select * from laws_regulation where id = (select max(id) from laws_regulation where id < #{id})")
    LawsRegulation selectUpListByTypeAndId(@Param("id") Long id);

    @Select("select * from laws_regulation where id = (select min(id) from laws_regulation where id > #{id})")
    LawsRegulation selectDownListByTypeAndId(@Param("id") Long id);
}
