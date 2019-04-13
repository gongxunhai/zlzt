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

import com.boot.security.server.model.ZlztDataDetail;

@Mapper
public interface ZlztDataDetailDao {

    @Select("select t.*,a.name as fIdName,b.name as sIdName,c.name as tIdName,d.name as cIdName,m.* from zlzt_data_detail t\n" +
            "\t\tinner join zlzt_classifyinfo a on t.fId = a.id\n" +
            "\t\tinner join zlzt_classifyinfo b on t.sId = b.id\n" +
            "\t\tinner join zlzt_classifyinfo c on t.tId = c.id\n" +
            "\t\tinner join zlzt_classifyinfo d on t.cId = d.id" +
            "    inner join zlzt_datainfo m on t.dataId = m.id where t.id = #{id}")
    ZlztDatainfo getAllData(Long id);

    @Select("select * from zlzt_data_detail t where t.id = #{id}")
    ZlztDataDetail getById(Long id);

    @Delete("delete from zlzt_data_detail where id = #{id}")
    int delete(Long id);

    @Delete("delete from zlzt_data_detail where dataId = #{id}")
    int deleteByDataId(Long id);

    int update(ZlztDataDetail zlztDataDetail);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_data_detail(dataId, fId, sId, tId, cId, createTime, updateTime) values(#{dataId}, #{fId}, #{sId}, #{tId}, #{cId}, #{createTime}, #{updateTime})")
    int save(ZlztDataDetail zlztDataDetail);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztDataDetail> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

//    @Select("select ifnull(max(id),-1) from zlzt_data_detail where dataId = #{dataId} and fId = #{fId} and sId = #{sId} and tId = #{tId} and cId = #{cId}")
//    int selectIdByDetail(ZlztDataDetail zlztDataDetail);

    @Select("select ifnull(max(id),-1) from zlzt_data_detail where dataId = #{dataId}")
    int selectIdByDataId(ZlztDataDetail zlztDataDetail);

    void danjiUpdate(ZlztDatainfo zlztDatainfo);
}
