package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.ZlztDatainfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.YfCompany;

@Mapper
public interface YfCompanyDao {


    @Select("select t.*,a.name as fIdName,b.name as sIdName,c.name as tIdName,d.name as cIdName,m.* from yf_company t\n" +
            "\t\tinner join yf_classifyinfo a on t.fId = a.id\n" +
            "\t\tinner join yf_classifyinfo b on t.sId = b.id\n" +
            "\t\tinner join yf_classifyinfo c on t.tId = c.id\n" +
            "\t\tinner join yf_classifyinfo d on t.cId = d.id" +
            "    inner join zlzt_datainfo m on t.dataId = m.id where t.id = #{id}")
    ZlztDatainfo getAllData(Long id);

    @Select("select * from yf_company t where t.id = #{id}")
    YfCompany getById(Long id);

    @Delete("delete from yf_company where id = #{id}")
    int delete(Long id);

    int update(ZlztDatainfo zlztDatainfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into yf_company(dataId, fId, sId, tId, cId, createTime, updateTime) values(#{dataId}, #{fId}, #{sId}, #{tId}, #{cId}, #{createTime}, #{updateTime})")
    int save(YfCompany yfCompany);
    
    int count(@Param("params") Map<String, Object> params);

    List<YfCompany> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from yf_company where dataId = #{dataId}")
    int selectIdByDataId(YfCompany yfCompany);
}
