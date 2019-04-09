package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.dao.YfClassifyInfoDao;
import com.boot.security.server.model.YfClassifyInfo;
import com.boot.security.server.model.ZlztDatainfo;
import com.boot.security.server.service.ZlztDatainfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.YfCompanyDao;
import com.boot.security.server.model.YfCompany;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "承研单位管理")
@RestController
@RequestMapping("/yfCompanys")
public class YfCompanyController {

    @Autowired
    private YfCompanyDao yfCompanyDao;
    @Autowired
    private ZlztDatainfoService zlztDatainfoService;
    @Autowired
    private YfClassifyInfoDao yfClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public YfCompany save(@RequestBody YfCompany yfCompany) {
        if (yfCompany.getTId() == null && yfCompany.getTId().equals("")){
            int i = yfClassifyInfoDao.getByNameAndParentId(yfCompany.gettIdName(),yfCompany.getSId());
            if ( i == -1){
                YfClassifyInfo yfClassifyInfo = new YfClassifyInfo();
                yfClassifyInfo.setName(yfCompany.gettIdName());
                yfClassifyInfo.setParentId(yfCompany.getSId());
                yfClassifyInfo.setType(3);
                yfClassifyInfoDao.save(yfClassifyInfo);
                i = yfClassifyInfo.getId().intValue();
            }
            yfCompany.setTId(i);
        }
        if (yfCompany.getTId() == null && yfCompany.getTId().equals("")){
            int j = yfClassifyInfoDao.getByNameAndParentId(yfCompany.getcIdName(),yfCompany.getTId());
            if ( j == -1){
                YfClassifyInfo yfClassifyInfo = new YfClassifyInfo();
                yfClassifyInfo.setName(yfCompany.getcIdName());
                yfClassifyInfo.setParentId(yfCompany.getTId());
                yfClassifyInfo.setType(4);
                yfClassifyInfoDao.save(yfClassifyInfo);
                j = yfClassifyInfo.getId().intValue();
            }
            yfCompany.setCId(j);
        }
        yfCompanyDao.save(yfCompany);
        return yfCompany;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public YfCompany get(@PathVariable Long id) {
        return yfCompanyDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public YfCompany update(@RequestBody YfCompany yfCompany) {
        if (yfCompany.getCId() == null && yfCompany.getCId().equals("")){
            int i = yfClassifyInfoDao.getByNameAndParentId(yfCompany.gettIdName(),yfCompany.getsId());
            if ( i == -1){
                YfClassifyInfo yfClassifyInfo = new YfClassifyInfo();
                yfClassifyInfo.setName(yfCompany.gettIdName());
                yfClassifyInfo.setParentId(yfCompany.getsId());
                yfClassifyInfo.setType(3);
                yfClassifyInfoDao.save(yfClassifyInfo);
                i = yfClassifyInfo.getId().intValue();
            }
            yfCompany.setcId(i);
        }
        if (yfCompany.getTId() == null && yfCompany.getTId().equals("")){
            int j = yfClassifyInfoDao.getByNameAndParentId(yfCompany.getcIdName(),yfCompany.getTId());
            if ( j == -1){
                YfClassifyInfo yfClassifyInfo = new YfClassifyInfo();
                yfClassifyInfo.setName(yfCompany.getcIdName());
                yfClassifyInfo.setParentId(yfCompany.getTId());
                yfClassifyInfo.setType(4);
                yfClassifyInfoDao.save(yfClassifyInfo);
                j = yfClassifyInfo.getId().intValue();
            }
            yfCompany.settId(j);
        }
        yfCompanyDao.update(yfCompany);

        return yfCompany;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return yfCompanyDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<YfCompany> list(PageTableRequest request) {
                return yfCompanyDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        yfCompanyDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "上传excel")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        zlztDatainfoService.save(file,yfClassifyInfoDao);
        json.put("msg","success");
        return json ;
    }


    @PostMapping("/getallcount")
    @ApiOperation(value = "获取统计信息")
    public List<ZlztDatainfo> getAllcount(@RequestBody ZlztDatainfo zlztDatainfo){
        String fromTable = "view_yfdata" ;
        return zlztDatainfoService.getAllcount(zlztDatainfo,fromTable);
    }

    @GetMapping("/getAllData/{id}")
    @ApiOperation(value = "根据id获取")
    public YfCompany getAllData(@PathVariable Long id) {
        return yfCompanyDao.getAllData(id);
    }
}
