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
import com.boot.security.server.dao.KjZlSuperClassifyInfoDao;
import com.boot.security.server.model.KjZlSuperClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "专利超市分类管理")
@RestController
@RequestMapping("/kjZlSuperClassifyInfos")
public class KjZlSuperClassifyInfoController {

    @Autowired
    private KjZlSuperClassifyInfoDao kjZlSuperClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjZlSuperClassifyInfo save(@RequestBody KjZlSuperClassifyInfo kjZlSuperClassifyInfo) {
        kjZlSuperClassifyInfoDao.save(kjZlSuperClassifyInfo);

        return kjZlSuperClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjZlSuperClassifyInfo get(@PathVariable Long id) {
        return kjZlSuperClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjZlSuperClassifyInfo update(@RequestBody KjZlSuperClassifyInfo kjZlSuperClassifyInfo) {
        kjZlSuperClassifyInfoDao.update(kjZlSuperClassifyInfo);

        return kjZlSuperClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjZlSuperClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjZlSuperClassifyInfo> list(PageTableRequest request) {
                return kjZlSuperClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjZlSuperClassifyInfoDao.delete(id);
    }
}
