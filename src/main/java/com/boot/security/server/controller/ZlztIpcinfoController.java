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
import com.boot.security.server.dao.ZlztIpcinfoDao;
import com.boot.security.server.model.ZlztIpcinfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "专利专题ipc管理")
@RestController
@RequestMapping("/zlztIpcinfos")
public class ZlztIpcinfoController {

    @Autowired
    private ZlztIpcinfoDao zlztIpcinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztIpcinfo save(@RequestBody ZlztIpcinfo zlztIpcinfo) {
        zlztIpcinfoDao.save(zlztIpcinfo);

        return zlztIpcinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztIpcinfo get(@PathVariable Long id) {
        return zlztIpcinfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztIpcinfo update(@RequestBody ZlztIpcinfo zlztIpcinfo) {
        zlztIpcinfoDao.update(zlztIpcinfo);

        return zlztIpcinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztIpcinfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztIpcinfo> list(PageTableRequest request) {
                return zlztIpcinfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztIpcinfoDao.delete(id);
    }
}
