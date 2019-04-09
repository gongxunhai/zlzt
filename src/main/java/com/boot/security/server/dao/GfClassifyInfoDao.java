package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.GfClassifyInfo;

@Mapper
public interface GfClassifyInfoDao {

    @Select("select * from gf_classifyInfo t order by type")
    List<GfClassifyInfo> listAll();

    @Select("select * from gf_classifyInfo t where t.id = #{id}")
    GfClassifyInfo getById(Long id);

    @Delete("delete from gf_classifyInfo where id = #{id}")
    int delete(Long id);

    int update(GfClassifyInfo gfClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into gf_classifyInfo(parentId, type, name ,image) values(#{parentId}, #{type}, #{name} ,#{image})")
    int save(GfClassifyInfo gfClassifyInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<GfClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from gf_classifyinfo t where t.name = #{name} ")
    int getIdByName(@Param("name") String name);

    @Select("select ifnull(max(id),-1) from gf_classifyinfo t where t.name = #{name} and t.parentId=#{parentId}")
    int getByNameAndParentId(@Param("name") String name, @Param("parentId") int parentId);

    @Select("select ifnull(max(parentId),-1) as parentId from gf_classifyinfo t where t.name = #{name} and t.parentId= #{parentId}")
    int getParentIdByName(@Param("name") String name,@Param("parentId") int i);

    @Select("SELECT min(T2.id) as id\n" +
            "FROM ( \n" +
            "    SELECT \n" +
            "        @r AS _id, \n" +
            "        (SELECT @r := parentId FROM gf_classifyinfo WHERE id = _id) AS parent_id, \n" +
            "        @l := @l + 1 AS lvl \n" +
            "    FROM \n" +
            "        (SELECT @r := #{id}, \t@l := 0) vars, \n" +
            "        gf_classifyinfo h \n" +
            "    WHERE @r <> 0) T1 \n" +
            "JOIN gf_classifyinfo T2 \n" +
            "ON T1._id = T2.id \n" +
            "ORDER BY T1.lvl DESC;")
    int getParentId(Long id);

    //获取上级ID与name
    @Select("SELECT T2.id, T2.name ,T2.type\n" +
            "FROM ( \n" +
            "    SELECT \n" +
            "        @r AS _id, \n" +
            "        (SELECT @r := parentId FROM gf_classifyInfo WHERE id = _id) AS parent_id, \n" +
            "        @l := @l + 1 AS lvl \n" +
            "    FROM \n" +
            "        (SELECT @r := #{id}, \t@l := 0) vars, \n" +
            "        gf_classifyInfo h \n" +
            "    WHERE @r <> 0) T1 \n" +
            "JOIN gf_classifyInfo T2 \n" +
            "ON T1._id = T2.id \n" +
            "ORDER BY T1.lvl DESC;")
    List<GfClassifyInfo>  getParentInfo(@Param("id") Long id);

    @Select("select * from gf_classifyinfo t where t.type = #{type} ")
    List<GfClassifyInfo> getClassify(@Param("type") int type);

    @Select("select * from gf_classifyinfo t where t.parentId = #{parentId}")
    List<GfClassifyInfo> getParentClassifyInfo(@Param("parentId") int parentId);

}
