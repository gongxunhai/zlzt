package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztCareinfo;

@Mapper
public interface ZlztCareinfoDao {

    @Select("select * from zlzt_careinfo t where t.id = #{id}")
    ZlztCareinfo getById(Long id);

    @Delete("delete from zlzt_careinfo where id = #{id}")
    int delete(Long id);

    int update(ZlztCareinfo zlztCareinfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_careinfo(userId, careId, type, url, createTime, updateTime) values(#{userId}, #{careId}, #{type}, #{url}, #{createTime}, #{updateTime})")
    int save(ZlztCareinfo zlztCareinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztCareinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int count1(@Param("params") Map<String, Object> params);

    List<ZlztCareinfo> list1(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from zlzt_careinfo where type = #{type} and careId = #{careId} and userId = #{userId} ")
    int selectSameData(ZlztCareinfo zlztCareinfo);

    @Select("delete from zlzt_careinfo where type = #{type} and careId = #{careId} and userId = #{userId}")
    void deletePoint(ZlztCareinfo zlztCareinfo);

    @Select("update ${type} set careNum = IFNULL(careNum,0) + 1 where id = #{careId} ")
    void addNumByDate(ZlztCareinfo zlztCareinfo);

    @Select("update ${type} set careNum = careNum - 1 where id = #{careId} ")
    void deleteNumByData(ZlztCareinfo zlztCareinfo);

    @Select("select IFNULL(careNum,-1) from ${type} where id = #{careId} ")
    int selectPointNum(ZlztCareinfo zlztCareinfo);

    @Select("select ${colum} from ${table} where id = ${careId}")
    String getTitle(@Param("table") String table, @Param("colum") String colum, @Param("careId") int careId);
}
