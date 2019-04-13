package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjServiceProduct;

@Mapper
public interface KjServiceProductDao {

    @Select("select * from kj_service_product t where t.id = #{id}")
    KjServiceProduct getById(Long id);

    @Delete("delete from kj_service_product where id = #{id}")
    int delete(Long id);

    int update(KjServiceProduct kjServiceProduct);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_service_product(name, href, image, content, createTime) values(#{name}, #{href}, #{image}, #{content}, #{createTime})")
    int save(KjServiceProduct kjServiceProduct);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjServiceProduct> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
