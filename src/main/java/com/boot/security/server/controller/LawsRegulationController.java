package com.boot.security.server.controller;

import java.util.List;

import com.boot.security.server.dao.LawsRegulationClassifyInfoDao;
import com.boot.security.server.model.LawsRegulationClassifyInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.LawsRegulationDao;
import com.boot.security.server.model.LawsRegulation;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lawsRegulations")
public class LawsRegulationController {

    @Autowired
    private LawsRegulationDao lawsRegulationDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public LawsRegulation save(@RequestBody LawsRegulation lawsRegulation) {
        lawsRegulationDao.save(lawsRegulation);

        return lawsRegulation;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public LawsRegulation get(@PathVariable Long id) {
        return lawsRegulationDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public LawsRegulation update(@RequestBody LawsRegulation lawsRegulation) {
        lawsRegulationDao.update(lawsRegulation);

        return lawsRegulation;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return lawsRegulationDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<LawsRegulation> list(PageTableRequest request) {
                return lawsRegulationDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        lawsRegulationDao.delete(id);
    }

    @PostMapping("/getCodeBycustomaryId")
    @ApiOperation(value = "获取code通过id")
    public String getCodeBycustomaryId(@RequestParam(value = "customaryId") String customaryId){
        String a = lawsRegulationDao.getCodeBycustomaryId(customaryId);
        return a;
    }

    @PostMapping("/addPageView")
    @ApiOperation(value = "浏览量增加")
    public void addPageView(@RequestParam(value = "number") int i,@RequestParam(value = "id") Long id){
        LawsRegulation lawsRegulation = lawsRegulationDao.getById(id);
        if (lawsRegulation.getPageView()!=null && ! lawsRegulation.getPageView().equals("")){
            int pageView = lawsRegulation.getPageView();
            pageView += i;
            lawsRegulation.setPageView(pageView);
        }else {
            lawsRegulation.setPageView(i);
        }
        lawsRegulationDao.update(lawsRegulation);
    }

    @PostMapping("/getUpDownPage")
    @ApiOperation(value = "上下翻页")
    public List<LawsRegulation> getListDetail(@RequestParam(value = "id") Long id){
//        System.out.println(params);
//        System.out.println(id);
        List<LawsRegulation> list = Lists.newArrayList();
        LawsRegulation upPage = lawsRegulationDao.selectUpListByTypeAndId(id);
        if (upPage == null ){
            upPage = new LawsRegulation();
        }
        list.add(upPage);
        LawsRegulation newsInformation = lawsRegulationDao.getById(id);
        if (newsInformation == null ){
            newsInformation = new LawsRegulation();
        }
        list.add(newsInformation);
        LawsRegulation downPage = lawsRegulationDao.selectDownListByTypeAndId(id);
        if (downPage == null ){
            downPage = new LawsRegulation();
        }
        list.add(downPage);
        return list;
    }
}
