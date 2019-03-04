package com.boot.security.server.controller;

import java.util.List;

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
import com.boot.security.server.dao.ZtReportClassifyinfoDao;
import com.boot.security.server.model.ZtReportClassifyinfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "专题报告分类")
@RestController
@RequestMapping("/ztReportClassifyinfos")
public class ZtReportClassifyinfoController {

    @Autowired
    private ZtReportClassifyinfoDao ztReportClassifyinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZtReportClassifyinfo save(@RequestBody ZtReportClassifyinfo ztReportClassifyinfo) {
        ztReportClassifyinfoDao.save(ztReportClassifyinfo);

        return ztReportClassifyinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZtReportClassifyinfo get(@PathVariable Long id) {
        return ztReportClassifyinfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZtReportClassifyinfo update(@RequestBody ZtReportClassifyinfo ztReportClassifyinfo) {
        ztReportClassifyinfoDao.update(ztReportClassifyinfo);

        return ztReportClassifyinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return ztReportClassifyinfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZtReportClassifyinfo> list(PageTableRequest request) {
                return ztReportClassifyinfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        ztReportClassifyinfoDao.delete(id);
    }

    @GetMapping("/getClassify/{id}")
    @ApiOperation(value = "获取分类")
    public List<ZtReportClassifyinfo> getClassifyByParentId(@PathVariable Long id){
        return ztReportClassifyinfoDao.getClassifyByParentId(id.intValue());
    }
    @GetMapping("/getNowPlace/{id}")
    @ApiOperation(value = "获取当前位置")
    public ZtReportClassifyinfo getNowPlace(@PathVariable Long id){
        return ztReportClassifyinfoDao.getNowPlace(id.intValue());
    }
}
