package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.LawsRegulationClassifyInfo;

@Mapper
public interface LawsRegulationClassifyInfoDao {

    @Select("select * from laws_regulation_classifyInfo t where t.id = #{id}")
    LawsRegulationClassifyInfo getById(Long id);

    @Delete("delete from laws_regulation_classifyInfo where id = #{id}")
    int delete(Long id);

    int update(LawsRegulationClassifyInfo lawsRegulationClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into laws_regulation_classifyInfo(createTime, name, parentId, type) values(#{createTime}, #{name}, #{parentId}, #{type})")
    int save(LawsRegulationClassifyInfo lawsRegulationClassifyInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<LawsRegulationClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from laws_regulation_classifyInfo t where t.type = 1 ")
    List<LawsRegulationClassifyInfo> getClassify();

    @Select("select * from laws_regulation_classifyInfo where parentId = #{parentId}")
    List<LawsRegulationClassifyInfo> listData(@Param("parentId") Long parentId);

    @Select("select * from laws_regulation_classifyInfo t order by t.type")
    List<LawsRegulationClassifyInfo> listAll();
}
