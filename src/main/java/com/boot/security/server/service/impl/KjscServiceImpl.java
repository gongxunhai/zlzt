package com.boot.security.server.service.impl;

import com.boot.security.server.dao.*;
import com.boot.security.server.model.KjProductClassifyInfo;
import com.boot.security.server.model.KjResultClassifyInfo;
import com.boot.security.server.model.KjZlSuperClassifyInfo;
import com.boot.security.server.service.KjscService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KjscServiceImpl implements KjscService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private KjResultDao kjResultDao;
    @Autowired
    private KjProductDao kjProductDao;
    @Autowired
    private KjZlsuperDao kjZlsuperDao;
    @Autowired
    private KjProductClassifyInfoDao kjProductClassifyInfoDao;
    @Autowired
    private KjZlSuperClassifyInfoDao kjZlSuperClassifyInfoDao;
    @Autowired
    private KjResultClassifyInfoDao kjResultClassifyInfoDao;

    @Override
    public List<Map<String, Object>> getResultInfo(String tableName, String tableName2) {
        List<Map<String, Object>> listMap = Lists.newArrayList();
        LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("name","所属行业");
        map.put("source","技术来源");
        map.put("tlevel","技术水平");
        map.put("cWays","合作方式");
        map.put("teMaturity","技术成熟度");
        map.put("area","区域");
        for (Map.Entry<String, Object> entry : map.entrySet()){
            if(entry.getKey() == "name"){
                Map<String,Object> map1 = new HashMap<String,Object>();
                List<KjResultClassifyInfo> list = kjResultClassifyInfoDao.listData((long)0);
                for (int i =0;i<list.size();i++){
                    List<KjResultClassifyInfo> list2 =  kjResultClassifyInfoDao.listData(list.get(i).getId());
                    if (list2.size()>0){
                        list.get(i).setChildren(list2);
                    }
                }
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
               listMap.add(map1);
            }else {
                Map<String,Object> map1 = new HashMap<String,Object>();
                List<String> list = kjResultDao.selectListData(entry.getKey());
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
                listMap.add(map1);
            }
        }
        return listMap;
    }

    @Override
    public List<Map<String, Object>> getProductInfo(String tableName, String tableName2) {
        List<Map<String, Object>> listMap = Lists.newArrayList();
        LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("name","所属行业");
        map.put("place","地点");
        for (Map.Entry<String, Object> entry : map.entrySet()){
            if(entry.getKey() == "name"){
                Map<String,Object> map1 = new HashMap<String,Object>();
                List<KjProductClassifyInfo> list = kjProductClassifyInfoDao.listData((long)0);
                for (int i =0;i<list.size();i++){
                    List<KjProductClassifyInfo> list2 =  kjProductClassifyInfoDao.listData(list.get(i).getId());
                    if (list2.size()>0){
                        list.get(i).setChildren(list2);
                    }
                }
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
                listMap.add(map1);
            }else {
                Map<String,Object> map1 = new HashMap<String,Object>();
                List<String> list = kjProductDao.selectListData(entry.getKey());
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
                listMap.add(map1);
            }
        }
        return listMap;
    }

    @Override
    public List<Map<String, Object>> getZlsuperInfo(String tableName, String tableName2) {

        List<Map<String, Object>> listMap = Lists.newArrayList();
        LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("name","所属行业");
        map.put("lawS","法律状态");
        map.put("dealWay","交易方式");
        map.put("zlType","专利类型");
        for (Map.Entry<String, Object> entry : map.entrySet()){
            if(entry.getKey() == "name"){
                Map<String,Object> map1 = new HashMap<String,Object>();
                List<KjZlSuperClassifyInfo> list = kjZlSuperClassifyInfoDao.listData((long)0);
                for (int i =0;i<list.size();i++){
                    List<KjZlSuperClassifyInfo> list2 =  kjZlSuperClassifyInfoDao.listData(list.get(i).getId());
                    if (list2.size()>0){
                        list.get(i).setChildren(list2);
                    }
                }
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
                listMap.add(map1);
            }else {
                Map<String,Object> map1 = new HashMap<String,Object>();
                List<String> list = kjZlsuperDao.selectListData(entry.getKey());
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
                listMap.add(map1);
            }
        }
        return listMap;
    }
}
