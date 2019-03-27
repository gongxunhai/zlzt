package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.PjRequireClassifyInfo;

@Mapper
public interface PjRequireClassifyInfoDao {

    @Select("select * from pj_require_classifyInfo t where t.id = #{id}")
    PjRequireClassifyInfo getById(Long id);

    @Delete("delete from pj_require_classifyInfo where id = #{id}")
    int delete(Long id);

    int update(PjRequireClassifyInfo pjRequireClassifyInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into pj_require_classifyInfo(createTime, name, parentId, type) values(#{createTime}, #{name}, #{parentId}, #{type})")
    int save(PjRequireClassifyInfo pjRequireClassifyInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<PjRequireClassifyInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select ifnull(max(id),-1) from pj_require_classifyInfo where name = #{cellToString}")
    int selectIdByName(@Param("cellToString") String cellToString);

    @Select("select ifnull(max(id),-1) from pj_require_classifyInfo where name = #{cellToString} and type = #{type}")
    int selectIdByNameAndType(@Param("cellToString") String cellToString, @Param("type") int i);

    @Select("select * from pj_require_classifyInfo where parentId = #{parentId}")
    List<PjRequireClassifyInfo> listData(@Param("parentId") Long parentId);

    @Select("select ifnull(max(id),-1) from pj_require_classifyInfo where parentId = #{fid} and name = '其它' ")
    int selectSidByFid(@Param("fid") int fid);
}
