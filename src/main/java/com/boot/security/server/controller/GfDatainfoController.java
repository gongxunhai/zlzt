package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.dao.GfClassifyInfoDao;
import com.boot.security.server.dao.ZlztDatainfoDao;
import com.boot.security.server.model.GfClassifyInfo;
import com.boot.security.server.model.ZlztDatainfo;
import com.boot.security.server.service.ZlztDatainfoService;
import com.boot.security.server.utils.ExcelUtil;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Select;
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
import com.boot.security.server.dao.GfDatainfoDao;
import com.boot.security.server.model.GfDatainfo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "高分数据管理")
@RestController
@RequestMapping("/gfDatainfos")
public class GfDatainfoController {

    @Autowired
    private GfDatainfoDao gfDatainfoDao;
    @Autowired
    private GfClassifyInfoDao gfClassifyInfoDao;
    @Autowired
    private ZlztDatainfoService zlztDatainfoService;
    @Autowired
    private ZlztDatainfoDao zlztDatainfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztDatainfo save(@RequestBody ZlztDatainfo zlztDatainfo) {
        zlztDatainfoDao.save(zlztDatainfo);
        saveDetail(zlztDatainfo);
        return zlztDatainfo;
    }

    private void saveDetail(ZlztDatainfo zlztDatainfo) {
        GfDatainfo gfDatainfo = new GfDatainfo();
        gfDatainfo.setDataId(zlztDatainfo.getId().intValue());
        gfDatainfo.setFId(zlztDatainfo.getfId());
        gfDatainfo.setSId(zlztDatainfo.getsId());
        if (zlztDatainfo.gettId() == null || zlztDatainfo.gettId().equals("")){
            int i = gfClassifyInfoDao.getByNameAndParentId(zlztDatainfo.gettIdName(),zlztDatainfo.getsId());
            if ( i == -1){
                GfClassifyInfo gfClassifyInfo = new GfClassifyInfo();
                gfClassifyInfo.setName(zlztDatainfo.gettIdName());
                gfClassifyInfo.setParentId(zlztDatainfo.getsId());
                gfClassifyInfo.setType(3);
                gfClassifyInfoDao.save(gfClassifyInfo);
                i = gfClassifyInfo.getId().intValue();
            }
            gfDatainfo.settId(i);
        }else{
            gfDatainfo.setTId(zlztDatainfo.gettId());
        }
        if (zlztDatainfo.getcId() == null || zlztDatainfo.getcId().equals("")){
            int j = gfClassifyInfoDao.getByNameAndParentId(zlztDatainfo.getcIdName(),zlztDatainfo.gettId());
            if ( j == -1){
                GfClassifyInfo gfClassifyInfo = new GfClassifyInfo();
                gfClassifyInfo.setName(zlztDatainfo.getcIdName());
                gfClassifyInfo.setParentId(zlztDatainfo.gettId());
                gfClassifyInfo.setType(4);
                gfClassifyInfoDao.save(gfClassifyInfo);
                j = gfClassifyInfo.getId().intValue();
            }
            gfDatainfo.setcId(j);
        }else {
            gfDatainfo.setCId(zlztDatainfo.getcId());
        }
        gfDatainfoDao.save(gfDatainfo);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public GfDatainfo get(@PathVariable Long id) {
        return gfDatainfoDao.getById(id);
    }

    @GetMapping("/getAllData/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztDatainfo getAllData(@PathVariable Long id) {
        return gfDatainfoDao.getAllData(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztDatainfo update(@RequestBody ZlztDatainfo zlztDatainfo) {
        zlztDatainfo = updateZldata(zlztDatainfo);
        //专利专题数据的使用
        gfDatainfoDao.update(zlztDatainfo);
        return zlztDatainfo;
    }

    private ZlztDatainfo updateZldata(ZlztDatainfo zlztDatainfo) {
        if (zlztDatainfo.gettId() == null || zlztDatainfo.gettId().equals("")) {
            int i = gfClassifyInfoDao.getByNameAndParentId(zlztDatainfo.gettIdName(), zlztDatainfo.getsId());
            if (i == -1) {
                GfClassifyInfo gfClassifyInfo = new GfClassifyInfo();
                gfClassifyInfo.setName(zlztDatainfo.gettIdName());
                gfClassifyInfo.setParentId(zlztDatainfo.getsId());
                gfClassifyInfo.setType(3);
                gfClassifyInfoDao.save(gfClassifyInfo);
                i = gfClassifyInfo.getId().intValue();
            }
            zlztDatainfo.settId(i);
        }
        if (zlztDatainfo.getcId() == null || zlztDatainfo.getcId().equals("")) {
            int j = gfClassifyInfoDao.getByNameAndParentId(zlztDatainfo.getcIdName(), zlztDatainfo.gettId());
            if (j == -1) {
                GfClassifyInfo gfClassifyInfo = new GfClassifyInfo();
                gfClassifyInfo.setName(zlztDatainfo.getcIdName());
                gfClassifyInfo.setParentId(zlztDatainfo.gettId());
                gfClassifyInfo.setType(4);
                gfClassifyInfoDao.save(gfClassifyInfo);
                j = gfClassifyInfo.getId().intValue();
            }
            zlztDatainfo.setcId(j);
        }
        return zlztDatainfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return gfDatainfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<GfDatainfo> list(PageTableRequest request) {
                return gfDatainfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        gfDatainfoDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "上传excel")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        zlztDatainfoService.save(file,gfClassifyInfoDao);
        json.put("msg","success");
        return json ;
    }
    @PostMapping("/getallcount")
    @ApiOperation(value = "获取统计信息")
    public List<ZlztDatainfo> getAllcount(@RequestBody ZlztDatainfo zlztDatainfo){
        String fromTable = "view_gfdata" ;
        return zlztDatainfoService.getAllcount(zlztDatainfo,fromTable);
    }

}