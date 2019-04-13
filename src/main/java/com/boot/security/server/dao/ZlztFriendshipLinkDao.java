package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztFriendshipLink;

@Mapper
public interface ZlztFriendshipLinkDao {

    @Select("select * from zlzt_friendship_link t where t.id = #{id}")
    ZlztFriendshipLink getById(Long id);

    @Delete("delete from zlzt_friendship_link where id = #{id}")
    int delete(Long id);

    int update(ZlztFriendshipLink zlztFriendshipLink);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_friendship_link(name, link, createTime, updateTime) values(#{name}, #{link}, #{createTime}, #{updateTime})")
    int save(ZlztFriendshipLink zlztFriendshipLink);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztFriendshipLink> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
