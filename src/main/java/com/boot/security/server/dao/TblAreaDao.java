package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.TblArea;

@Mapper
public interface TblAreaDao {

    @Select("select * from tbl_area t where t.id = #{id}")
    TblArea getById(Long id);

    @Delete("delete from tbl_area where id = #{id}")
    int delete(Long id);

    int update(TblArea tblArea);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tbl_area(code, createDate, customaryId, level, name, parentId, updateDate) values(#{code}, #{createDate}, #{customaryId}, #{level}, #{name}, #{parentId}, #{updateDate})")
    int save(TblArea tblArea);
    
    int count(@Param("params") Map<String, Object> params);

    List<TblArea> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from tbl_area where parentId = #{parentId}")
    List<TblArea> getDataByParentId(@Param("parentId") String parentId);
}
