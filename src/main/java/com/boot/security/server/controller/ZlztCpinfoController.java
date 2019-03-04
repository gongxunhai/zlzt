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
import com.boot.security.server.dao.ZlztCpinfoDao;
import com.boot.security.server.model.ZlztCpinfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "专利专题国民经济管理")
@RestController
@RequestMapping("/zlztCpinfos")
public class ZlztCpinfoController {

    @Autowired
    private ZlztCpinfoDao zlztCpinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztCpinfo save(@RequestBody ZlztCpinfo zlztCpinfo) {
        zlztCpinfoDao.save(zlztCpinfo);

        return zlztCpinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztCpinfo get(@PathVariable Long id) {
        return zlztCpinfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztCpinfo update(@RequestBody ZlztCpinfo zlztCpinfo) {
        zlztCpinfoDao.update(zlztCpinfo);

        return zlztCpinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztCpinfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztCpinfo> list(PageTableRequest request) {
                return zlztCpinfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztCpinfoDao.delete(id);
    }
}
