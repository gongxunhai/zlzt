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
import com.boot.security.server.dao.NewsClassifyInfoDao;
import com.boot.security.server.model.NewsClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "新闻动态分类")
@RestController
@RequestMapping("/newsClassifyInfos")
public class NewsClassifyInfoController {

    @Autowired
    private NewsClassifyInfoDao newsClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public NewsClassifyInfo save(@RequestBody NewsClassifyInfo newsClassifyInfo) {
        newsClassifyInfoDao.save(newsClassifyInfo);

        return newsClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public NewsClassifyInfo get(@PathVariable Long id) {
        return newsClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public NewsClassifyInfo update(@RequestBody NewsClassifyInfo newsClassifyInfo) {
        newsClassifyInfoDao.update(newsClassifyInfo);

        return newsClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return newsClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<NewsClassifyInfo> list(PageTableRequest request) {
                return newsClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        newsClassifyInfoDao.delete(id);
    }

}
