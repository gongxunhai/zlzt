package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZtReport;

@Mapper
public interface ZtReportDao {

    @Select("select t.*,b.name as fIdName,c.name as sIdName from zt_report t join zt_report_classifyinfo b on t.fId = b.id " +
            "join zt_report_classifyinfo c on t.sId = c.id  where t.id = #{id}")
    ZtReport getById(Long id);

    @Delete("delete from zt_report where id = #{id}")
    int delete(Long id);

    int update(ZtReport ztReport);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zt_report(author, catalog, createTime, descript, fId, image, name, pageView, pdfFile, sId) values(#{author}, #{catalog}, #{createTime}, #{descript}, #{fId}, #{image}, #{name}, #{pageView}, #{pdfFile}, #{sId})")
    int save(ZtReport ztReport);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZtReport> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zt_report where fId = #{fId} and sId = #{sId}")
    List<ZtReport> selectDataByFidAndSid(@Param("fId") int fId, @Param("sId") int sId);
}
