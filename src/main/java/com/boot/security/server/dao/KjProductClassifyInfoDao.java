package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjProductClassifyInfo;

@Mapper
public interface KjProductClassifyInfoDao {

    @Select("select * from kj_product_classifyInfo t where t.id = #{id}")
    KjProductClassifyInfo getById(Long id);

    @Delete("delete from kj_product_classifyInfo where id = #{id}")
    int delete(Long id);

    int update(KjProductClassifyInfo kjProductClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_product_classifyInfo(createTime, name, parentId, type, updateTime) values(#{createTime}, #{name}, #{parentId}, #{type}, #{updateTime})")
    int save(KjProductClassifyInfo kjProductClassifyInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjProductClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from kj_product_classifyInfo where name = #{cellToString}")
    int selectIdByName(@Param("cellToString") String cellToString);

    @Select("select ifnull(max(id),-1) from kj_product_classifyInfo where name = #{cellToString} and parentId= #{parentId}")
    int selectIdByNameAndParentId(@Param("cellToString") String cellToString, @Param("parentId") int parentId);

    @Select("select * from kj_product_classifyInfo where parentId = #{parentId}")
    List<KjProductClassifyInfo> listData(@Param("parentId") Long parentId);

    @Select("select ifnull(max(id),-1) from kj_product_classifyInfo where parentId = #{fid} and name = '其它' ")
    int selectSidByFid(@Param("fid") int fid);

    @Select("select * from kj_product_classifyInfo t  order by t.type")
    List<KjProductClassifyInfo> listAll();

    @Select("select * from kj_product_classifyInfo t where t.type = #{type} order by type ")
    List<KjProductClassifyInfo> getClassify(@Param("type") int type);
}
