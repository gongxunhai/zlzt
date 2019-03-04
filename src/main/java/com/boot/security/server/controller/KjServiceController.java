package com.boot.security.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.KjServiceDao;
import com.boot.security.server.model.KjService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kjServices")
public class KjServiceController {

    @Autowired
    private KjServiceDao kjServiceDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjService save(@RequestBody KjService kjService) {
        kjServiceDao.save(kjService);

        return kjService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjService get(@PathVariable Long id) {
        return kjServiceDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjService update(@RequestBody KjService kjService) {
        kjServiceDao.update(kjService);

        return kjService;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjServiceDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjService> list(PageTableRequest request) {
                return kjServiceDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjServiceDao.delete(id);
    }

}
