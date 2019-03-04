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
import com.boot.security.server.dao.LawsRegulationClassifyInfoDao;
import com.boot.security.server.model.LawsRegulationClassifyInfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lawsRegulationClassifyInfos")
public class LawsRegulationClassifyInfoController {

    @Autowired
    private LawsRegulationClassifyInfoDao lawsRegulationClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public LawsRegulationClassifyInfo save(@RequestBody LawsRegulationClassifyInfo lawsRegulationClassifyInfo) {
        lawsRegulationClassifyInfoDao.save(lawsRegulationClassifyInfo);

        return lawsRegulationClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public LawsRegulationClassifyInfo get(@PathVariable Long id) {
        return lawsRegulationClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public LawsRegulationClassifyInfo update(@RequestBody LawsRegulationClassifyInfo lawsRegulationClassifyInfo) {
        lawsRegulationClassifyInfoDao.update(lawsRegulationClassifyInfo);

        return lawsRegulationClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return lawsRegulationClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<LawsRegulationClassifyInfo> list(PageTableRequest request) {
                return lawsRegulationClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        lawsRegulationClassifyInfoDao.delete(id);
    }

    @GetMapping("/getClassify")
    @ApiOperation(value = "获取1级分类")
    public List<LawsRegulationClassifyInfo> getClassify(){
        return lawsRegulationClassifyInfoDao.getClassify();
    }

    @PostMapping("/changeSelect")
    @ApiOperation(value = "根据模块名称获取数据")
    public List<LawsRegulationClassifyInfo> changeSelect(){
        List<LawsRegulationClassifyInfo> list = lawsRegulationClassifyInfoDao.listData((long)0);
        for (int i =0;i<list.size();i++){
            List<LawsRegulationClassifyInfo> list2 =  lawsRegulationClassifyInfoDao.listData(list.get(i).getId());
            if (list2.size()>0){
                list.get(i).setChildren(list2);
            }
        }
        return list;
    }
}
