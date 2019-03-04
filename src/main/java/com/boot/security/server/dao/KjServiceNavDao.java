package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.Dict;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.KjServiceNav;

@Mapper
public interface KjServiceNavDao {

    @Select("select * from kj_service_nav t where t.id = #{id}")
    KjServiceNav getById(Long id);

    @Delete("delete from kj_service_nav where id = #{id}")
    int delete(Long id);

    int update(KjServiceNav kjServiceNav);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into kj_service_nav(fId, sId, name, href, createTime) values(#{fId}, #{sId}, #{name}, #{href}, #{createTime})")
    int save(KjServiceNav kjServiceNav);
    
    int count(@Param("params") Map<String, Object> params);

    List<KjServiceNav> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from kj_service_nav where fId = #{fid} and sId = #{sid} ")
    List<KjServiceNav> getDataByFidAndSid(@Param("fid") int fid, @Param("sid") int sid);
}
