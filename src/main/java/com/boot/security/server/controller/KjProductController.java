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
import com.boot.security.server.dao.KjProductDao;
import com.boot.security.server.model.KjProduct;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "科技产品管理")
@RestController
@RequestMapping("/kjProducts")
public class KjProductController {

    @Autowired
    private KjProductDao kjProductDao;
    @Autowired
    private ExcelUtil excelUtil;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjProduct save(@RequestBody KjProduct kjProduct) {
        kjProductDao.save(kjProduct);

        return kjProduct;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjProduct get(@PathVariable Long id) {
        return kjProductDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjProduct update(@RequestBody KjProduct kjProduct) {
        kjProductDao.update(kjProduct);

        return kjProduct;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjProductDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjProduct> list(PageTableRequest request) {
                return kjProductDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/app")
    @ApiOperation(value = "列表")
    public PageTableResponse list1(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjProductDao.count1(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjProduct> list(PageTableRequest request) {
                return kjProductDao.list1(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjProductDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "上传excel")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        excelUtil.getExcelBykjProduct(file);
        json.put("msg","success");
        return json ;
    }
}
