package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.ZlztDatainfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztClassifyinfo;

@Mapper
public interface ZlztClassifyinfoDao {

    @Select("select * from zlzt_classifyinfo t where t.id = #{id}")
    ZlztClassifyinfo getById(Long id);

    @Select("select * from zlzt_classifyinfo t where t.type = 1 ")
    List<ZlztClassifyinfo> getClassify();

    @Select("select * from zlzt_classifyinfo t order by type")
    List<ZlztClassifyinfo> listAll();

    @Select("select ifnull(max(id),-1) from zlzt_classifyinfo t where t.name = #{name} ")
    int getIdByName(@Param("name") String name);

    @Select("select ifnull(max(id),-1) from zlzt_classifyinfo t where t.name = #{name} and t.parentId=#{parentId}")
    int getByNameAndParentId(@Param("name") String name, @Param("parentId") int parentId);

    @Select("select ifnull(max(parentId),-1) as parentId from zlzt_classifyinfo t where t.name = #{name} and t.parentId= #{parentId}")
    int getParentIdByName(@Param("name") String name,@Param("parentId") int i);

    @Delete("delete from zlzt_classifyinfo where id = #{id}")
    int delete(Long id);

    int update(ZlztClassifyinfo zlztClassifyinfo);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_classifyinfo(parentId, type, name, translate) values(#{parentId}, #{type}, #{name}, #{translate})")
    int save(ZlztClassifyinfo zlztClassifyinfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztClassifyinfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zlzt_classifyinfo where parentId = #{parentId}")
    List<ZlztClassifyinfo> listData(@Param("parentId") Long parentId);

    @Select("SELECT min(T2.id) as id\n" +
            "FROM ( \n" +
            "    SELECT \n" +
            "        @r AS _id, \n" +
            "        (SELECT @r := parentId FROM zlzt_classifyinfo WHERE id = _id) AS parent_id, \n" +
            "        @l := @l + 1 AS lvl \n" +
            "    FROM \n" +
            "        (SELECT @r := #{id}, \t@l := 0) vars, \n" +
            "        zlzt_classifyinfo h \n" +
            "    WHERE @r <> 0) T1 \n" +
            "JOIN zlzt_classifyinfo T2 \n" +
            "ON T1._id = T2.id \n" +
            "ORDER BY T1.lvl DESC;")
    int getParentId(Long id);

    //获取上级ID与name
    @Select("SELECT T2.id, T2.name ,T2.type\n" +
            "FROM ( \n" +
            "    SELECT \n" +
            "        @r AS _id, \n" +
            "        (SELECT @r := parentId FROM zlzt_classifyinfo WHERE id = _id) AS parent_id, \n" +
            "        @l := @l + 1 AS lvl \n" +
            "    FROM \n" +
            "        (SELECT @r := #{id}, \t@l := 0) vars, \n" +
            "        zlzt_classifyinfo h \n" +
            "    WHERE @r <> 0) T1 \n" +
            "JOIN zlzt_classifyinfo T2 \n" +
            "ON T1._id = T2.id \n" +
            "ORDER BY T1.lvl DESC;")
    List<ZlztClassifyinfo>  getParentInfo(@Param("id") Long id);
}
