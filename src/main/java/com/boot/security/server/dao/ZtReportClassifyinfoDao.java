package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZtReportClassifyinfo;

@Mapper
public interface ZtReportClassifyinfoDao {

    @Select("select * from zt_report_classifyinfo t where t.id = #{id}")
    ZtReportClassifyinfo getById(Long id);

    @Delete("delete from zt_report_classifyinfo where id = #{id}")
    int delete(Long id);

    int update(ZtReportClassifyinfo ztReportClassifyinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zt_report_classifyinfo(createTime, name, parentId, type) values(#{createTime}, #{name}, #{parentId}, #{type})")
    int save(ZtReportClassifyinfo ztReportClassifyinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZtReportClassifyinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zt_report_classifyinfo where parentId = #{parentId} ")
    List<ZtReportClassifyinfo> getClassifyByParentId(@Param("parentId") int parentId);

    @Select("select * from zt_report_classifyinfo where id = #{id} ")
    ZtReportClassifyinfo getNowPlace(@Param("id") int id);
}
