package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjResult;

@Mapper
public interface KjResultDao {

    @Select("select t.*,b.name as fIdName,c.name as sIdName from kj_result t " +
            "left join kj_result_classifyInfo b on t.fId = b.id left join kj_result_classifyInfo c on t.sId = c.id " +
            "where t.id = #{id}")
    KjResult getById(Long id);

    @Delete("delete from kj_result where id = #{id}")
    int delete(Long id);

    int update(KjResult kjResult);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_result(area, cMan, cPhone, cWays, fId, knowledge, mNeed, name, predict, pubTime, sId, source, tdetails, teMaturity, tIndex, tlevel, useRange, xfArea, userId, status, image, updateTime) values(#{area}, #{cMan}, #{cPhone}, #{cWays}, #{fId}, #{knowledge}, #{mNeed}, #{name}, #{predict}, #{pubTime}, #{sId}, #{source}, #{tdetails}, #{teMaturity}, #{tIndex}, #{tlevel}, #{useRange}, #{xfArea}, #{userId}, #{status}, #{image}, #{updateTime})")
    int save(KjResult kjResult);
    
    int count(@Param("params") Map<String, Object> params);

    int count1(@Param("params") Map<String, Object> params);

    List<KjResult> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Map<String,Object>> list1(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select DISTINCT ${key} from kj_result ")
    List<String> selectListData(@Param("key") String key);
}
