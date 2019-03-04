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
import com.boot.security.server.dao.ZlztPointAgreeDao;
import com.boot.security.server.model.ZlztPointAgree;

import io.swagger.annotations.ApiOperation;

@Api(tags = "点赞")
@RestController
@RequestMapping("/zlztPointAgrees")
public class ZlztPointAgreeController {

    @Autowired
    private ZlztPointAgreeDao zlztPointAgreeDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztPointAgree save(@RequestBody ZlztPointAgree zlztPointAgree) {
        zlztPointAgreeDao.save(zlztPointAgree);

        return zlztPointAgree;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztPointAgree get(@PathVariable Long id) {
        return zlztPointAgreeDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztPointAgree update(@RequestBody ZlztPointAgree zlztPointAgree) {
        zlztPointAgreeDao.update(zlztPointAgree);

        return zlztPointAgree;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztPointAgreeDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztPointAgree> list(PageTableRequest request) {
                return zlztPointAgreeDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztPointAgreeDao.delete(id);
    }
}
