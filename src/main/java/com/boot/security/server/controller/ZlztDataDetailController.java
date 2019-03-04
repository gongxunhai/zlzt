    package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
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

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztDataDetail save(@RequestBody ZlztDataDetail zlztDataDetail) {
        zlztDataDetailDao.save(zlztDataDetail);

        return zlztDataDetail;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztDataDetail get(@PathVariable Long id) {
        return zlztDataDetailDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztDataDetail update(@RequestBody ZlztDataDetail zlztDataDetail) {
        zlztDataDetailDao.update(zlztDataDetail);

        return zlztDataDetail;
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

    @PostMapping("/getallcount")
    @ApiOperation(value = "获取统计信息")
    public List<ZlztDatainfo> getAllcount(@RequestBody ZlztDatainfo zlztDatainfo){
        String fromTable = "view_zlztdata" ;
        return zlztDatainfoService.getAllcount(zlztDatainfo,fromTable);
    }

}
