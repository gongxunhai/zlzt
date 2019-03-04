package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.dao.PjRequireClassifyInfoDao;
import com.boot.security.server.model.PjRequireClassifyInfo;
import com.boot.security.server.utils.ExcelUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.PjRequireDao;
import com.boot.security.server.model.PjRequire;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "项目需求管理")
@RestController
@RequestMapping("/pjRequires")
public class PjRequireController {

    @Autowired
    private PjRequireDao pjRequireDao;
    @Autowired
    private ExcelUtil excelUtil;
    @Autowired
    private PjRequireClassifyInfoDao pjRequireClassifyInfoDao;

    private List<?> listData;

    public List<?> getListData() {
        return listData;
    }

    public void setListData(List<?> listData) {
        this.listData = listData;
    }

    @PostMapping
    @ApiOperation(value = "保存")
    public PjRequire save(@RequestBody PjRequire pjRequire) {
        pjRequireDao.save(pjRequire);

        return pjRequire;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public PjRequire get(@PathVariable Long id) {
        return pjRequireDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public PjRequire update(@RequestBody PjRequire pjRequire) {
        pjRequireDao.update(pjRequire);

        return pjRequire;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return pjRequireDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<PjRequire> list(PageTableRequest request) {
                return pjRequireDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        pjRequireDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "Excel上传")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        excelUtil.getExcelByPjRequire(file);
        json.put("msg","success");
        return json;
    }

    @PostMapping("/changeSelect")
    @ApiOperation(value = "根据模块名称获取数据")
    public PjRequireController changeSelect(){
        List<Map<String, Object>> listMap = Lists.newArrayList();
        LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("name","所属行业");
        map.put("cWays","合作方式");
        map.put("xfArea","细分领域");
        map.put("place","所属区域");
        for (Map.Entry<String, Object> entry : map.entrySet()){
            Map<String,Object> map1 = new HashMap<String,Object>();
            if(entry.getKey() == "name"){
                List<PjRequireClassifyInfo> list = pjRequireClassifyInfoDao.listData((long)0);
                for (int i =0;i<list.size();i++){
                    List<PjRequireClassifyInfo> list2 =  pjRequireClassifyInfoDao.listData(list.get(i).getId());
                    if (list2.size()>0){
                        list.get(i).setChildren(list2);
                    }
                }
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
                listMap.add(map1);
            }else {
                List<String> list = pjRequireDao.selectListData(entry.getKey());
                map1.put("name",entry.getValue());
                map1.put("value",entry.getKey());
                map1.put("children",list);
                listMap.add(map1);
            }
        }
        this.setListData(listMap);
        return this;
    }
}
