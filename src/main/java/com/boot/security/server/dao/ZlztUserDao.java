package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.boot.security.server.model.ZlztUser;

@Mapper
public interface ZlztUserDao {

    @Select("select * from zlzt_user t where t.id = #{id}")
    ZlztUser getById(Long id);

    @Delete("delete from zlzt_user where id = #{id}")
    int delete(Long id);

    int update(ZlztUser zlztUser);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, inforAutA, inforAutB, fromJob, interestJob, createTime, updateTime, userType) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, #{inforAutA}, #{inforAutB}, #{fromJob}, #{interestJob}, #{createTime}, #{updateTime}, #{userType})")
    int save(ZlztUser zlztUser);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select IFNULL(MAX(id),-1) from zlzt_user where ${key} = #{phone} ")
    int selectParam(@Param("key") String key,@Param("phone") String phone);

    @Select("select * from zlzt_user where phone = #{phoneOrEmail} or email = #{phoneOrEmail}")
    ZlztUser getPwdByPhoneOrEmail(@Param("phoneOrEmail") String phoneOrEmail);

    @Select("select * from zlzt_user where phone = #{phone} or email = #{Email}")
    ZlztUser getPwdByPhoneAndEmail(@Param("phone") String phone,@Param("Email") String Email);

    @Select("select * from zlzt_user t where t.username = #{username}")
    ZlztUser getUser(@Param("username") String username);

    @Update("update zlzt_user t set t.password = #{password} where t.id = #{id}")
    int changePassword(@Param("id") Long id, @Param("password") String password);
}
