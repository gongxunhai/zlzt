    package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.dao.ZlztClassifyinfoDao;
import com.boot.security.server.dao.ZlztDatainfoDao;
import com.boot.security.server.model.ZlztClassifyinfo;
import com.boot.security.server.model.ZlztDatainfo;
import com.boot.security.server.service.ZlztDatainfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.ZlztDataDetailDao;
import com.boot.security.server.model.ZlztDataDetail;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "专利专题管理")
@RestController
@RequestMapping("/zlztDataDetails")
public class ZlztDataDetailController {

    @Autowired
    private ZlztDataDetailDao zlztDataDetailDao;
    @Autowired
    private ZlztDatainfoService zlztDatainfoService;
    @Autowired
    private ZlztDatainfoDao zlztDatainfoDao;
    @Autowired
    private ZlztClassifyinfoDao zlztClassifyinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztDataDetail save(@RequestBody ZlztDataDetail zlztDataDetail) {
        if (zlztDataDetail.getTId() == null && zlztDataDetail.getTId().equals("")){
            int i = zlztClassifyinfoDao.getByNameAndParentId(zlztDataDetail.gettIdName(),zlztDataDetail.getSId());
            if ( i == -1){
                ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
                zlztClassifyinfo.setName(zlztDataDetail.gettIdName());
                zlztClassifyinfo.setParentId(zlztDataDetail.getSId());
                zlztClassifyinfo.setType(3);
                zlztClassifyinfoDao.save(zlztClassifyinfo);
                i = zlztClassifyinfo.getId().intValue();
            }
            zlztDataDetail.setTId(i);
        }
        if (zlztDataDetail.getTId() == null && zlztDataDetail.getTId().equals("")){
            int j = zlztClassifyinfoDao.getByNameAndParentId(zlztDataDetail.getcIdName(),zlztDataDetail.getTId());
            if ( j == -1){
                ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
                zlztClassifyinfo.setName(zlztDataDetail.getcIdName());
                zlztClassifyinfo.setParentId(zlztDataDetail.getTId());
                zlztClassifyinfo.setType(4);
                zlztClassifyinfoDao.save(zlztClassifyinfo);
                j = zlztClassifyinfo.getId().intValue();
            }
            zlztDataDetail.setCId(j);
        }
        zlztDataDetailDao.save(zlztDataDetail);
        return zlztDataDetail;
    }

    @PostMapping("/danjiSave")
    @ApiOperation(value = "单机版保存")
    public ZlztDatainfo danjiSave(@RequestBody ZlztDatainfo zlztDatainfo){
        //主数据的保存
        zlztDatainfoDao.save(zlztDatainfo);
        saveDetail(zlztDatainfo);
        return zlztDatainfo ;
    }

    private void saveDetail(ZlztDatainfo zlztDatainfo) {
        ZlztDataDetail zlztDataDetail = new ZlztDataDetail();
        zlztDataDetail.setDataId(zlztDatainfo.getId().intValue());
        zlztDataDetail.setFId(zlztDatainfo.getfId());
        zlztDataDetail.setSId(zlztDatainfo.getsId());
        int i = zlztClassifyinfoDao.getByNameAndParentId(zlztDatainfo.gettIdName(),zlztDataDetail.getSId());
        if ( i == -1){
            ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
            zlztClassifyinfo.setName(zlztDatainfo.gettIdName());
            zlztClassifyinfo.setParentId(zlztDataDetail.getSId());
            zlztClassifyinfo.setType(3);
            zlztClassifyinfoDao.save(zlztClassifyinfo);
            i = zlztClassifyinfo.getId().intValue();
        }
        zlztDataDetail.setTId(i);
        int j = zlztClassifyinfoDao.getByNameAndParentId(zlztDatainfo.getcIdName(),i);
        if ( j == -1){
            ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
            zlztClassifyinfo.setName(zlztDatainfo.getcIdName());
            zlztClassifyinfo.setParentId(i);
            zlztClassifyinfo.setType(4);
            zlztClassifyinfoDao.save(zlztClassifyinfo);
            j = zlztClassifyinfo.getId().intValue();
        }
        zlztDataDetail.setCId(j);
        //专利专题数据的使用
        zlztDataDetailDao.save(zlztDataDetail);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztDataDetail get(@PathVariable Long id) {
        return zlztDataDetailDao.getById(id);
    }

    @GetMapping("/getAllData/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztDataDetail getAllData(@PathVariable Long id) {
        return zlztDataDetailDao.getAllData(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztDataDetail update(@RequestBody ZlztDataDetail zlztDataDetail) {
        if (zlztDataDetail.getCId() == null && zlztDataDetail.getCId().equals("")){
            int i = zlztClassifyinfoDao.getByNameAndParentId(zlztDataDetail.gettIdName(),zlztDataDetail.getsId());
            if ( i == -1){
                ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
                zlztClassifyinfo.setName(zlztDataDetail.gettIdName());
                zlztClassifyinfo.setParentId(zlztDataDetail.getsId());
                zlztClassifyinfo.setType(3);
                zlztClassifyinfoDao.save(zlztClassifyinfo);
                i = zlztClassifyinfo.getId().intValue();
            }
            zlztDataDetail.setcId(i);
        }
        if (zlztDataDetail.getTId() == null && zlztDataDetail.getTId().equals("")){
            int j = zlztClassifyinfoDao.getByNameAndParentId(zlztDataDetail.getcIdName(),zlztDataDetail.getTId());
            if ( j == -1){
                ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
                zlztClassifyinfo.setName(zlztDataDetail.getcIdName());
                zlztClassifyinfo.setParentId(zlztDataDetail.getTId());
                zlztClassifyinfo.setType(4);
                zlztClassifyinfoDao.save(zlztClassifyinfo);
                j = zlztClassifyinfo.getId().intValue();
            }
            zlztDataDetail.settId(j);
        }
        zlztDataDetailDao.update(zlztDataDetail);
        return zlztDataDetail;
    }

    @PutMapping("/danjiUpdate")
    @ApiOperation(value = "danji版修改")
    public void danjiUpdate(@RequestBody ZlztDatainfo zlztDatainfo) {
        zlztDatainfo = updateZldata(zlztDatainfo);
        //专利专题数据的使用
        zlztDataDetailDao.danjiUpdate(zlztDatainfo);
    }

    private ZlztDatainfo updateZldata(ZlztDatainfo zlztDatainfo) {
        int i = zlztClassifyinfoDao.getByNameAndParentId(zlztDatainfo.gettIdName(),zlztDatainfo.getsId());
        if ( i == -1){
            ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
            zlztClassifyinfo.setName(zlztDatainfo.gettIdName());
            zlztClassifyinfo.setParentId(zlztDatainfo.getsId());
            zlztClassifyinfo.setType(3);
            zlztClassifyinfoDao.save(zlztClassifyinfo);
            i = zlztClassifyinfo.getId().intValue();
        }
        zlztDatainfo.setcId(i);
        int j = zlztClassifyinfoDao.getByNameAndParentId(zlztDatainfo.getcIdName(),i);
        if ( j == -1){
            ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
            zlztClassifyinfo.setName(zlztDatainfo.getcIdName());
            zlztClassifyinfo.setParentId(i);
            zlztClassifyinfo.setType(4);
            zlztClassifyinfoDao.save(zlztClassifyinfo);
            j = zlztClassifyinfo.getId().intValue();
        }
        zlztDatainfo.settId(j);
        return zlztDatainfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztDataDetailDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztDataDetail> list(PageTableRequest request) {
                return zlztDataDetailDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztDataDetailDao.delete(id);
    }

    @DeleteMapping("/deleteByDataId/{id}")
    @ApiOperation(value = "删除")
    public void deleteByDataId(@PathVariable Long id) {
        zlztDataDetailDao.deleteByDataId(id);
    }

    @PostMapping("/getallcount")
    @ApiOperation(value = "获取统计信息")
    public List<ZlztDatainfo> getAllcount(@RequestBody ZlztDatainfo zlztDatainfo){
        String fromTable = "view_zlztdata" ;
        return zlztDatainfoService.getAllcount(zlztDatainfo,fromTable);
    }

}
