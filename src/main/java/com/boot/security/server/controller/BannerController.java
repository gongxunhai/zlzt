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
import com.boot.security.server.dao.BannerDao;
import com.boot.security.server.model.Banner;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banners")
public class BannerController {

    @Autowired
    private BannerDao bannerDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Banner save(@RequestBody Banner banner) {
        bannerDao.save(banner);

        return banner;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Banner get(@PathVariable Long id) {
        return bannerDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Banner update(@RequestBody Banner banner) {
        bannerDao.update(banner);

        return banner;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return bannerDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Banner> list(PageTableRequest request) {
                return bannerDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        bannerDao.delete(id);
    }
}
