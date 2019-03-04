package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
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
import com.boot.security.server.dao.KjZlsuperDao;
import com.boot.security.server.model.KjZlsuper;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "专利超市管理")
@RestController
@RequestMapping("/kjZlsupers")
public class KjZlsuperController {

    @Autowired
    private KjZlsuperDao kjZlsuperDao;
    @Autowired
    private ExcelUtil excelUtil;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjZlsuper save(@RequestBody KjZlsuper kjZlsuper) {
        kjZlsuperDao.save(kjZlsuper);

        return kjZlsuper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjZlsuper get(@PathVariable Long id) {
        return kjZlsuperDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjZlsuper update(@RequestBody KjZlsuper kjZlsuper) {
        kjZlsuperDao.update(kjZlsuper);

        return kjZlsuper;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjZlsuperDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjZlsuper> list(PageTableRequest request) {
                return kjZlsuperDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjZlsuperDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "上传excel")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        excelUtil.getExcelBykjSuper(file);
        json.put("msg","success");
        return json ;
    }
}
