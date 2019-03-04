package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjZlSuperClassifyInfo;

@Mapper
public interface KjZlSuperClassifyInfoDao {

    @Select("select * from kj_zlSuper_classifyInfo t where t.id = #{id}")
    KjZlSuperClassifyInfo getById(Long id);

    @Delete("delete from kj_zlSuper_classifyInfo where id = #{id}")
    int delete(Long id);

    int update(KjZlSuperClassifyInfo kjZlSuperClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_zlSuper_classifyInfo(parentId,type,createTime, name) values(#{parentId},#{type},#{createTime}, #{name})")
    int save(KjZlSuperClassifyInfo kjZlSuperClassifyInfo);


    int count(@Param("params") Map<String, Object> params);

    List<KjZlSuperClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from kj_zlSuper_classifyInfo where name = #{cellToString}")
    int selectIdByName(@Param("cellToString") String cellToString);

    @Select("select * from kj_zlSuper_classifyInfo where parentId = #{parentId}")
    List<KjZlSuperClassifyInfo> listData(@Param("parentId") Long parentId);
}
