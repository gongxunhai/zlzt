package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjService;

@Mapper
public interface KjServiceDao {

    @Select("select * from kj_service t where t.id = #{id}")
    KjService getById(Long id);

    @Delete("delete from kj_service where id = #{id}")
    int delete(Long id);

    int update(KjService kjService);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_service(descript, type, serviceWay, price, introduction, flow, record, commentId, createTime, name, image, flowImageA, flowImageB) values(#{descript}, #{type}, #{serviceWay}, #{price}, #{introduction}, #{flow}, #{record}, #{commentId}, #{createTime}, #{name}, #{image}, #{flowImageA}, #{flowImageB})")
    int save(KjService kjService);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjService> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
