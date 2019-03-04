package com.boot.security.server.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.NewsInformationDao;
import com.boot.security.server.model.NewsInformation;

import io.swagger.annotations.ApiOperation;

@Api(tags = "新闻动态管理")
@RestController
@RequestMapping("/newsInformations")
public class NewsInformationController {

    @Autowired
    private NewsInformationDao newsInformationDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public NewsInformation save(@RequestBody NewsInformation newsInformation) {
        newsInformationDao.save(newsInformation);

        return newsInformation;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public NewsInformation get(@PathVariable Long id) {
        return newsInformationDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public NewsInformation update(@RequestBody NewsInformation newsInformation) {
        newsInformationDao.update(newsInformation);

        return newsInformation;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return newsInformationDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<NewsInformation> list(PageTableRequest request) {
                return newsInformationDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        newsInformationDao.delete(id);
    }

    @PostMapping("/addPageView")
    @ApiOperation(value = "浏览量增加")
    public void addPageView(@RequestParam(value = "number") int i,@RequestParam(value = "id") Long id){
        NewsInformation newsInformation = newsInformationDao.getById(id);
        if (newsInformation.getPageView()!=null && ! newsInformation.getPageView().equals("")){
            int pageView = newsInformation.getPageView();
            pageView += i;
            newsInformation.setPageView(pageView);
        }else {
            newsInformation.setPageView(i);
        }
        newsInformationDao.update(newsInformation);
    }

    @PostMapping("/getUpDownPage")
    @ApiOperation(value = "上下翻页")
    public List<NewsInformation> getListDetail(@RequestParam(value = "type") int type,@RequestParam(value = "id") Long id){
//        System.out.println(params);
//        System.out.println(id);
        List<NewsInformation> list = Lists.newArrayList();
        NewsInformation upPage = newsInformationDao.selectUpListByTypeAndId(type,id);
        if (upPage == null ){
            upPage = new NewsInformation();
        }
        list.add(upPage);
        NewsInformation newsInformation = newsInformationDao.getById(id);
        if (newsInformation == null ){
            newsInformation = new NewsInformation();
        }
        list.add(newsInformation);
        NewsInformation downPage = newsInformationDao.selectDownListByTypeAndId(type,id);
        if (downPage == null ){
            downPage = new NewsInformation();
        }
        list.add(downPage);
        return list;
    }
}
