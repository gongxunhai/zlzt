package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.dao.GfClassifyInfoDao;
import com.boot.security.server.dao.ZlztDatainfoDao;
import com.boot.security.server.model.ZlztDatainfo;
import com.boot.security.server.service.ZlztDatainfoService;
import com.boot.security.server.utils.ExcelUtil;
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
import com.boot.security.server.dao.GfDatainfoDao;
import com.boot.security.server.model.GfDatainfo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "高分数据管理")
@RestController
@RequestMapping("/gfDatainfos")
public class GfDatainfoController {

    @Autowired
    private GfDatainfoDao gfDatainfoDao;
    @Autowired
    private GfClassifyInfoDao gfClassifyInfoDao;
    @Autowired
    private ZlztDatainfoService zlztDatainfoService;

    @PostMapping
    @ApiOperation(value = "保存")
    public GfDatainfo save(@RequestBody GfDatainfo gfDatainfo) {
        gfDatainfoDao.save(gfDatainfo);

        return gfDatainfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public GfDatainfo get(@PathVariable Long id) {
        return gfDatainfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public GfDatainfo update(@RequestBody GfDatainfo gfDatainfo) {
        gfDatainfoDao.update(gfDatainfo);

        return gfDatainfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return gfDatainfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<GfDatainfo> list(PageTableRequest request) {
                return gfDatainfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        gfDatainfoDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "上传excel")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        zlztDatainfoService.save(file,gfClassifyInfoDao);
        json.put("msg","success");
        return json ;
    }
    @PostMapping("/getallcount")
    @ApiOperation(value = "获取统计信息")
    public List<ZlztDatainfo> getAllcount(@RequestBody ZlztDatainfo zlztDatainfo){
        String fromTable = "view_gfdata" ;
        return zlztDatainfoService.getAllcount(zlztDatainfo,fromTable);
    }
}