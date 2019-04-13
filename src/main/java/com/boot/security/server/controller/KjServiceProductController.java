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
import com.boot.security.server.dao.KjServiceProductDao;
import com.boot.security.server.model.KjServiceProduct;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kjServiceProducts")
public class KjServiceProductController {

    @Autowired
    private KjServiceProductDao kjServiceProductDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjServiceProduct save(@RequestBody KjServiceProduct kjServiceProduct) {
        kjServiceProductDao.save(kjServiceProduct);

        return kjServiceProduct;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjServiceProduct get(@PathVariable Long id) {
        return kjServiceProductDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjServiceProduct update(@RequestBody KjServiceProduct kjServiceProduct) {
        kjServiceProductDao.update(kjServiceProduct);

        return kjServiceProduct;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjServiceProductDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjServiceProduct> list(PageTableRequest request) {
                return kjServiceProductDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjServiceProductDao.delete(id);
    }
}
