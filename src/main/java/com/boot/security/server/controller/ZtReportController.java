package com.boot.security.server.controller;

import java.util.List;
import java.util.Map;

import com.boot.security.server.dao.ZtReportClassifyinfoDao;
import com.boot.security.server.model.ZtReportClassifyinfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.ZtReportDao;
import com.boot.security.server.model.ZtReport;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ztReports")
@Api(tags = "专题报告")
public class ZtReportController {

    @Autowired
    private ZtReportDao ztReportDao;
    @Autowired
    private ZtReportClassifyinfoDao ztReportClassifyinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZtReport save(@RequestBody ZtReport ztReport) {
        ztReportDao.save(ztReport);
        return ztReport;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZtReport get(@PathVariable Long id) {
        return ztReportDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZtReport update(@RequestBody ZtReport ztReport) {
        ztReportDao.update(ztReport);

        return ztReport;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return ztReportDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZtReport> list(PageTableRequest request) {
                return ztReportDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        ztReportDao.delete(id);
    }


    @GetMapping("/listAllData")
    @ApiOperation(value = "列出一级二级数据")
    public List<ZtReportClassifyinfo> listAllData(){
        List<ZtReportClassifyinfo> list =  ztReportClassifyinfoDao.getClassifyByParentId(0);
        for( int i = 0 ; i < list.size() ; i++) {  //第一级
            List<ZtReportClassifyinfo> childrenList =  ztReportClassifyinfoDao.getClassifyByParentId(list.get(i).getId().intValue());
            for( int j = 0 ; j < childrenList.size() ; j++) { //第二级 4，5，6，7
                List<ZtReport> ztReportList = ztReportDao.selectDataByFidAndSid(list.get(i).getId().intValue(),childrenList.get(j).getId().intValue());
                childrenList.get(j).setChildren(ztReportList);
            }
            list.get(i).setChildren(childrenList);
        }
        return list;
    }

    @PostMapping("/addPageView")
    @ApiOperation(value = "浏览量增加")
    public void addPageView(@RequestParam(value = "number") int i, @RequestParam(value = "id") Long id){
        ZtReport ztReport = ztReportDao.getById(id);
        if (ztReport.getPageView()!=null && ! ztReport.getPageView().equals("")){
            int pageView = ztReport.getPageView();
            pageView += i;
            ztReport.setPageView(pageView);
        }else {
            ztReport.setPageView(i);
        }
        ztReportDao.update(ztReport);
    }
}
