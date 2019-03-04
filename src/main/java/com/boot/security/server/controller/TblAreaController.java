package com.boot.security.server.controller;

import java.util.List;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.TblAreaDao;
import com.boot.security.server.model.TblArea;

import io.swagger.annotations.ApiOperation;

@Api(tags = "区域管理")
@RestController
@RequestMapping("/tblAreas")
public class TblAreaController {

    @Autowired
    private TblAreaDao tblAreaDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public TblArea save(@RequestBody TblArea tblArea) {
        tblAreaDao.save(tblArea);

        return tblArea;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public TblArea get(@PathVariable Long id) {
        return tblAreaDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public TblArea update(@RequestBody TblArea tblArea) {
        tblAreaDao.update(tblArea);

        return tblArea;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return tblAreaDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<TblArea> list(PageTableRequest request) {
                return tblAreaDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        tblAreaDao.delete(id);
    }

    @GetMapping("/getData")
    public List<TblArea> getDataByParentId(@RequestParam(value = "parentId") String parentId){
        List<TblArea> list = Lists.newArrayList();
        if (parentId !=null && ! parentId.equals("")){
            list  = tblAreaDao.getDataByParentId(parentId);
        }
        return list;
    }
}
