package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjResultClassifyInfo;

@Mapper
public interface KjResultClassifyInfoDao {

    @Select("select * from kj_result_classifyinfo t where t.id = #{id}")
    KjResultClassifyInfo getById(Long id);

    @Delete("delete from kj_result_classifyinfo where id = #{id}")
    int delete(Long id);

    int update(KjResultClassifyInfo kjResultClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_result_classifyinfo(parentId,type,createTime, name, updateTime) values(#{parentId},#{type},#{createTime}, #{name}, #{updateTime})")
    int save(KjResultClassifyInfo kjResultClassifyInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjResultClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);


    @Select("select ifnull(max(id),-1) from kj_result_classifyinfo where name = #{cellToString}")
    int selectIdByName(@Param("cellToString") String cellToString);

    @Select("select * from kj_result_classifyinfo where parentId = #{parentId}")
    List<KjResultClassifyInfo> listData(@Param("parentId") Long parentId);

    @Select("select ifnull(max(id),-1) from kj_result_classifyinfo where parentId = #{fid} and name = '其它' ")
    int selectSidByFid(@Param("fid") int fid);

    @Select("select * from kj_result_classifyinfo t  order by t.type")
    List<KjResultClassifyInfo> listAll();

    @Select("select * from kj_result_classifyinfo t where t.type = #{type} order by type ")
    List<KjResultClassifyInfo> getClassify(@Param("type") int type);;
}
