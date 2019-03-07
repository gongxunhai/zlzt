package com.boot.security.server.controller;

import java.util.List;

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
import com.boot.security.server.dao.ZlztUserDao;
import com.boot.security.server.model.ZlztUser;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zlztUsers")
public class ZlztUserController {

    @Autowired
    private ZlztUserDao zlztUserDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztUser save(@RequestBody ZlztUser zlztUser) {
        zlztUserDao.save(zlztUser);

        return zlztUser;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztUser get(@PathVariable Long id) {
        return zlztUserDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztUser update(@RequestBody ZlztUser zlztUser) {
        zlztUserDao.update(zlztUser);

        return zlztUser;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztUserDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztUser> list(PageTableRequest request) {
                return zlztUserDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztUserDao.delete(id);
    }
}
