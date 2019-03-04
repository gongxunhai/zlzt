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
import com.boot.security.server.dao.KjProductClassifyInfoDao;
import com.boot.security.server.model.KjProductClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "科技产品分类管理")
@RestController
@RequestMapping("/kjProductClassifyInfos")
public class KjProductClassifyInfoController {

    @Autowired
    private KjProductClassifyInfoDao kjProductClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjProductClassifyInfo save(@RequestBody KjProductClassifyInfo kjProductClassifyInfo) {
        kjProductClassifyInfoDao.save(kjProductClassifyInfo);

        return kjProductClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjProductClassifyInfo get(@PathVariable Long id) {
        return kjProductClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjProductClassifyInfo update(@RequestBody KjProductClassifyInfo kjProductClassifyInfo) {
        kjProductClassifyInfoDao.update(kjProductClassifyInfo);

        return kjProductClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjProductClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjProductClassifyInfo> list(PageTableRequest request) {
                return kjProductClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjProductClassifyInfoDao.delete(id);
    }
}
