package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztUser;

@Mapper
public interface ZlztUserDao {

    @Select("select * from zlzt_user t where t.id = #{id}")
    ZlztUser getById(Long id);

    @Delete("delete from zlzt_user where id = #{id}")
    int delete(Long id);

    int update(ZlztUser zlztUser);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, inforAutA, inforAutB, formJob, interestJob, createTime, updateTime, userType) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, #{inforAutA}, #{inforAutB}, #{formJob}, #{interestJob}, #{createTime}, #{updateTime}, #{userType})")
    int save(ZlztUser zlztUser);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select IFNULL(MAX(id),-1) from zlzt_user where ${key} = #{phone} ")
    int selectParam(@Param("key") String key,@Param("phone") String phone);

    @Select("select password from zlzt_user where phone = #{phoneOrEmail} or email = #{phoneOrEmail}")
    String getPwdByPhoneOrEmail(@Param("phoneOrEmail") String phoneOrEmail);
}
