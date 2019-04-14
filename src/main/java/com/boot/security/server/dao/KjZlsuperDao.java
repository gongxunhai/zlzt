package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjZlsuper;

@Mapper
public interface KjZlsuperDao {

    @Select("select t.*,b.name as fIdName,c.name as sIdName from kj_zlsuper t " +
            "            left join kj_zlsuper_classifyInfo b on t.fId = b.id left join kj_zlsuper_classifyInfo c on t.sId = c.id "+
            "            where t.id = #{id}")
    KjZlsuper getById(Long id);

    @Delete("delete from kj_zlsuper where id = #{id}")
    int delete(Long id);

    int update(KjZlsuper kjZlsuper);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_zlsuper(transform, cMan, cPhone, dealMoney, dealWay, fId, lawS, name, pubTime, sId, useArea, xfArea, zlDetails, zlId, image, zlType, zlYear, userId, status, updateTime) values(#{transform}, #{cMan}, #{cPhone}, #{dealMoney}, #{dealWay}, #{fId}, #{lawS}, #{name}, #{pubTime}, #{sId}, #{useArea}, #{xfArea}, #{zlDetails}, #{zlId}, #{image}, #{zlType}, #{zlYear}, #{userId}, #{status}, #{updateTime})")
    int save(KjZlsuper kjZlsuper);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjZlsuper> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int count1(@Param("params") Map<String, Object> params);

    List<KjZlsuper> list1(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select DISTINCT ${key} from kj_zlsuper ")
    List<String> selectListData(@Param("key") String key);
}
