package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjProduct;

@Mapper
public interface KjProductDao {

    @Select("select t.*,b.name as fIdName,c.name as sIdName from kj_product t "  +
            "            left join kj_product_classifyInfo b on t.fId = b.id left join kj_product_classifyInfo c on t.sId = c.id " +
            "            where t.id = #{id}")
    KjProduct getById(Long id);

    @Delete("delete from kj_product where id = #{id}")
    int delete(Long id);

    int update(KjProduct kjProduct);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_product(buyNum, cMan, cPhone, fId, haveNum, history, name, pData, pDetail, image, place, price, pubTime, salePrice, sId, talk, xfArea, userId, status, updateTime, createTime) values(#{buyNum}, #{cMan}, #{cPhone}, #{fId}, #{haveNum}, #{history}, #{name}, #{pData}, #{pDetail}, #{image}, #{place}, #{price}, #{pubTime}, #{salePrice}, #{sId}, #{talk}, #{xfArea}, #{userId}, #{status}, #{updateTime}, #{createTime})")
    int save(KjProduct kjProduct);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjProduct> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select DISTINCT ${key} from kj_product ")
    List<String> selectListData(@Param("key") String key);
}
