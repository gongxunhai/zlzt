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
import com.boot.security.server.dao.ZlztContactUsDao;
import com.boot.security.server.model.ZlztContactUs;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zlztContactUss")
public class ZlztContactUsController {

    @Autowired
    private ZlztContactUsDao zlztContactUsDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztContactUs save(@RequestBody ZlztContactUs zlztContactUs) {
        zlztContactUsDao.save(zlztContactUs);

        return zlztContactUs;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztContactUs get(@PathVariable Long id) {
        return zlztContactUsDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztContactUs update(@RequestBody ZlztContactUs zlztContactUs) {
        zlztContactUsDao.update(zlztContactUs);

        return zlztContactUs;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztContactUsDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztContactUs> list(PageTableRequest request) {
                return zlztContactUsDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztContactUsDao.delete(id);
    }
}
