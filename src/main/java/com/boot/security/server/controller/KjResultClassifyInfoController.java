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
import com.boot.security.server.dao.KjResultClassifyInfoDao;
import com.boot.security.server.model.KjResultClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "科技成果分类管理")
@RestController
@RequestMapping("/kjResultClassifyInfos")
public class KjResultClassifyInfoController {

    @Autowired
    private KjResultClassifyInfoDao kjResultClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjResultClassifyInfo save(@RequestBody KjResultClassifyInfo kjResultClassifyInfo) {
        kjResultClassifyInfoDao.save(kjResultClassifyInfo);

        return kjResultClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjResultClassifyInfo get(@PathVariable Long id) {
        return kjResultClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjResultClassifyInfo update(@RequestBody KjResultClassifyInfo kjResultClassifyInfo) {
        kjResultClassifyInfoDao.update(kjResultClassifyInfo);

        return kjResultClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjResultClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjResultClassifyInfo> list(PageTableRequest request) {
                return kjResultClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjResultClassifyInfoDao.delete(id);
    }
}
