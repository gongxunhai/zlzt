package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.model.SqlLiteCeshi;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ZlztDatainfo;

@Mapper
public interface ZlztDatainfoDao {

    @Select("select * from zlzt_datainfo t where t.id = #{id}")
    ZlztDatainfo getById(Long id);

    @Delete("delete from zlzt_datainfo where id = #{id}")
    int delete(Long id);

    int update(ZlztDatainfo zlztDatainfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zlzt_datainfo(country, openId, openDay, applyId, applyDay, title, titleFy, zy, zyFy, powerAsk, powerAFy, powerAN, applyMan, applyPlace, createMan, lawS, nowLawS, zlType, ipcMC, ipcMCFy, ipc, ipcFy, cpec, cpecFy, beUsedNum, value, zlImage, zlText, homeImage, secret, createTime, updateTime) values(#{country}, #{openId}, #{openDay}, #{applyId}, #{applyDay}, #{title}, #{titleFy}, #{zy}, #{zyFy}, #{powerAsk}, #{powerAFy}, #{powerAN}, #{applyMan}, #{applyPlace}, #{createMan}, #{lawS}, #{nowLawS}, #{zlType}, #{ipcMC}, #{ipcMCFy}, #{ipc}, #{ipcFy}, #{cpec}, #{cpecFy}, #{beUsedNum}, #{value}, #{zlImage}, #{zlText}, #{homeImage}, #{secret}, #{createTime}, #{updateTime})")
    int save(ZlztDatainfo zlztDatainfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZlztDatainfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int count1(@Param("params") Map<String, Object> params);

    List<ZlztDatainfo> list1(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);


    ZlztDatainfo selectListByParams(@Param("params") JSONObject params, @Param("id") String id);

    List<ZlztDatainfo> getChildren(ZlztDatainfo zlztDatainfo);

    @Select("select * from zlzt_datainfo")
    List<Map<String, Object>> getAllList();

    @Select({
            "<script>",
            "select",
            "*",
            "from zlzt_datainfo",
            "where id in ",
            "<foreach item='item' index='index' collection='ids'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<Map<String, Object>> getSomeList(@Param("ids") Long[] ids);

    @Select("select ifnull(max(id),-1) from zlzt_datainfo where openId = #{openid}")
    int selectOpenid(@Param("openid") String openid);

    @Select("select * from ceshi")
    SqlLiteCeshi getSqlLiteCeshi();
}
