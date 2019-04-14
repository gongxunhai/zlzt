package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.service.KjscService;
import com.boot.security.server.utils.ExcelUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.KjResultDao;
import com.boot.security.server.model.KjResult;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "科技成果管理")
@RestController
@RequestMapping("/kjResults")
public class KjResultController {

    @Autowired
    private KjResultDao kjResultDao;
    @Autowired
    private ExcelUtil excelUtil;
    @Autowired
    private KjscService kjscService;

    private List<?> listData;

    public List<?> getListData() {
        return listData;
    }

    public void setListData(List<?> listData) {
        this.listData = listData;
    }

    @PostMapping
    @ApiOperation(value = "保存")
    public KjResult save(@RequestBody KjResult kjResult) {
        kjResultDao.save(kjResult);

        return kjResult;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjResult get(@PathVariable Long id) {
        return kjResultDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjResult update(@RequestBody KjResult kjResult) {
        kjResultDao.update(kjResult);
        return kjResult;
    }

    @GetMapping
    @ApiOperation(value = "后台列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjResultDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjResult> list(PageTableRequest request) {
                return kjResultDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/app")
    @ApiOperation(value = "后台列表")
    public PageTableResponse list2(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjResultDao.count2(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjResult> list(PageTableRequest request) {
                return kjResultDao.list2(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/foreRequest")
    @ApiOperation(value = "前端请求列表")
    public PageTableResponse list1(PageTableRequest request) {
            return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjResultDao.count1(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Map<String,Object>> list(PageTableRequest request) {
                return kjResultDao.list1(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjResultDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "Excel上传")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        excelUtil.getExcelBykjReSult(file);
        json.put("msg","success");
        return json ;
    }

    @PostMapping("/changeSelect")
    @ApiOperation(value = "根据模块名称获取数据")
    public KjResultController changeSelect(@RequestParam(value = "tableName") String tableName, @RequestParam(value = "tableName2") String tableName2){
//        KjResultController kjResultController = new KjResultController();
        List<Map<String,Object>> list = Lists.newArrayList();
         switch (tableName) {
            case "kj_result":
                list = kjscService.getResultInfo(tableName,tableName2);
                break;
            case "kj_product":
                list = kjscService.getProductInfo(tableName,tableName2);
                break;
            case "kj_zlsuper":
                list = kjscService.getZlsuperInfo(tableName,tableName2);
                break;
        }
//        kjResultController.setListData(list);
         this.setListData(list);
        return this;
    }
}
