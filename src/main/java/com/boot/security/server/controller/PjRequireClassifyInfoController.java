package com.boot.security.server.controller;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.PjRequireClassifyInfoDao;
import com.boot.security.server.model.PjRequireClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "项目需求分类管理")
@RestController
@RequestMapping("/pjRequireClassifyInfos")
public class PjRequireClassifyInfoController {

    @Autowired
    private PjRequireClassifyInfoDao pjRequireClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public PjRequireClassifyInfo save(@RequestBody PjRequireClassifyInfo pjRequireClassifyInfo) {
        pjRequireClassifyInfoDao.save(pjRequireClassifyInfo);

        return pjRequireClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public PjRequireClassifyInfo get(@PathVariable Long id) {
        return pjRequireClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public PjRequireClassifyInfo update(@RequestBody PjRequireClassifyInfo pjRequireClassifyInfo) {
        pjRequireClassifyInfoDao.update(pjRequireClassifyInfo);

        return pjRequireClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return pjRequireClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<PjRequireClassifyInfo> list(PageTableRequest request) {
                return pjRequireClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        pjRequireClassifyInfoDao.delete(id);
    }

    @PostMapping("/changeFromJob")
    @ApiOperation(value = "获取用户中心发布新项目的所属行业")
    public List<PjRequireClassifyInfo> changeFromJob(){
        return pjRequireClassifyInfoDao.listData((long) 0);
    }

    @GetMapping("/selectSidByFid")
    public int selectSidByFid(@RequestParam("fid") int fid ){
        int sId = pjRequireClassifyInfoDao.selectSidByFid(fid);
        if (sId == -1){
            PjRequireClassifyInfo pjRequireClassifyInfo = new PjRequireClassifyInfo();
            pjRequireClassifyInfo.setParentId(fid);
            pjRequireClassifyInfo.setName("其它");
            pjRequireClassifyInfo.setType(2);
            pjRequireClassifyInfoDao.save(pjRequireClassifyInfo);
            sId = pjRequireClassifyInfo.getId().intValue();
        }
        return sId;
    }
}
