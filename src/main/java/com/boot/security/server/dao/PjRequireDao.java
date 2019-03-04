package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.PjRequire;

@Mapper
public interface PjRequireDao {


    @Select("select t.*,b.name as fIdName,c.name as sIdName from pj_require t " +
            "left join pj_require_classifyInfo b on t.fId = b.id left join pj_require_classifyInfo c on t.sId = c.id " +
            "where t.id = #{id}")
    PjRequire getById(Long id);

    @Delete("delete from pj_require where id = #{id}")
    int delete(Long id);

    int update(PjRequire pjRequire);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into pj_require(addFile, cMoney, cWays, description, endTime, fId, man, name, phone, place, sId, target, unit, xfArea) values(#{addFile}, #{cMoney}, #{cWays}, #{description}, #{endTime}, #{fId}, #{man}, #{name}, #{phone}, #{place}, #{sId}, #{target}, #{unit}, #{xfArea})")
    int save(PjRequire pjRequire);
    
    int count(@Param("params") Map<String, Object> params);

    List<PjRequire> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select DISTINCT ${key} from pj_require ")
    List<String> selectListData(@Param("key") String key);
}
