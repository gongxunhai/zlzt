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
import com.boot.security.server.dao.EntrustDao;
import com.boot.security.server.model.Entrust;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/entrusts")
public class EntrustController {

    @Autowired
    private EntrustDao entrustDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Entrust save(@RequestBody Entrust entrust) {
        entrustDao.save(entrust);

        return entrust;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Entrust get(@PathVariable Long id) {
        return entrustDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Entrust update(@RequestBody Entrust entrust) {
        entrustDao.update(entrust);

        return entrust;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return entrustDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Entrust> list(PageTableRequest request) {
                return entrustDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        entrustDao.delete(id);
    }
}
