package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztPointAgree;

@Mapper
public interface ZlztPointAgreeDao {

    @Select("select * from zlzt_point_agree t where t.id = #{id}")
    ZlztPointAgree getById(Long id);

    @Delete("delete from zlzt_point_agree where id = #{id}")
    int delete(Long id);

    int update(ZlztPointAgree zlztPointAgree);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_point_agree(userId, agreeId, type, url, createTime, updateTime) values(#{userId}, #{agreeId}, #{type}, #{url}, #{createTime}, #{updateTime})")
    int save(ZlztPointAgree zlztPointAgree);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztPointAgree> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int count1(@Param("params") Map<String, Object> params);

    List<ZlztPointAgree> list1(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from zlzt_point_agree where type = #{type} and agreeId = #{agreeId} and userId = #{userId} ")
    int selectSameData(ZlztPointAgree zlztPointAgree);

    @Select("delete from zlzt_point_agree where type = #{type} and agreeId = #{agreeId} and userId = #{userId}")
    void deletePoint(ZlztPointAgree zlztPointAgree);

    @Select("update ${type} set pointNum = IFNULL(pointNum,0) + 1 where id = #{agreeId} ")
    void addNumByData(ZlztPointAgree zlztPointAgree);

    @Select("update ${type} set pointNum = pointNum - 1 where id = #{agreeId} ")
    void deleteNumByData(ZlztPointAgree zlztPointAgree);

    @Select("select IFNULL(pointNum,-1) from ${type} where id = #{agreeId} ")
    int selectPointNum(ZlztPointAgree zlztPointAgree);

}
