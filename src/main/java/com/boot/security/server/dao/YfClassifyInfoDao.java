package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.YfClassifyInfo;

@Mapper
public interface YfClassifyInfoDao {

    @Select("select * from yf_classifyInfo t where t.id = #{id}")
    YfClassifyInfo getById(Long id);

    @Delete("delete from yf_classifyInfo where id = #{id}")
    int delete(Long id);

    int update(YfClassifyInfo yfClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into yf_classifyInfo(parentId, type, name) values(#{parentId}, #{type}, #{name})")
    int save(YfClassifyInfo yfClassifyInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<YfClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(parentId),-1) as parentId from yf_classifyinfo t where t.name = #{name} and t.parentId= #{parentId}")
    int getParentIdByName(@Param("name") String name,@Param("parentId") int i);

    @Select("select ifnull(max(id),-1) from yf_classifyinfo t where t.name = #{name} and t.parentId=#{parentId}")
    int getByNameAndParentId(@Param("name") String name, @Param("parentId") int parentId);

    @Select("select ifnull(max(id),-1) from yf_classifyinfo t where t.name = #{name} ")
    int getIdByName(@Param("name") String name);

    @Select("select * from yf_classifyinfo t where t.parentId = #{parentId}")
    List<YfClassifyInfo> getParentClassifyInfo(@Param("parentId") int parentId);

    @Select("select * from yf_classifyinfo t order by type")
    List<YfClassifyInfo> listAll();


    @Select("SELECT min(T2.id) as id\n" +
            "FROM ( \n" +
            "    SELECT \n" +
            "        @r AS _id, \n" +
            "        (SELECT @r := parentId FROM yf_classifyinfo WHERE id = _id) AS parent_id, \n" +
            "        @l := @l + 1 AS lvl \n" +
            "    FROM \n" +
            "        (SELECT @r := #{id}, \t@l := 0) vars, \n" +
            "        yf_classifyinfo h \n" +
            "    WHERE @r <> 0) T1 \n" +
            "JOIN yf_classifyinfo T2 \n" +
            "ON T1._id = T2.id \n" +
            "ORDER BY T1.lvl DESC;")
    int getParentId(Long id);

    //获取上级ID与name
    @Select("SELECT T2.id, T2.name ,T2.type\n" +
            "FROM ( \n" +
            "    SELECT \n" +
            "        @r AS _id, \n" +
            "        (SELECT @r := parentId FROM yf_classifyinfo WHERE id = _id) AS parent_id, \n" +
            "        @l := @l + 1 AS lvl \n" +
            "    FROM \n" +
            "        (SELECT @r := #{id}, \t@l := 0) vars, \n" +
            "        yf_classifyinfo h \n" +
            "    WHERE @r <> 0) T1 \n" +
            "JOIN yf_classifyinfo T2 \n" +
            "ON T1._id = T2.id \n" +
            "ORDER BY T1.lvl DESC;")
    List<YfClassifyInfo>  getParentInfo(@Param("id") Long id);
}
