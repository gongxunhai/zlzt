package com.boot.security.server.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.ZlztPointAgreeDao;
import com.boot.security.server.model.ZlztPointAgree;

import io.swagger.annotations.ApiOperation;

@Api(tags = "点赞")
@RestController
@RequestMapping("/zlztPointAgrees")
public class ZlztPointAgreeController {

    @Autowired
    private ZlztPointAgreeDao zlztPointAgreeDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztPointAgree save(@RequestBody ZlztPointAgree zlztPointAgree) {
        zlztPointAgreeDao.save(zlztPointAgree);

        return zlztPointAgree;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztPointAgree get(@PathVariable Long id) {
        return zlztPointAgreeDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztPointAgree update(@RequestBody ZlztPointAgree zlztPointAgree) {
        zlztPointAgreeDao.update(zlztPointAgree);

        return zlztPointAgree;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztPointAgreeDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztPointAgree> list(PageTableRequest request) {
                return zlztPointAgreeDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/foreRequest")
    @ApiOperation(value = "列表")
    public PageTableResponse list1(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztPointAgreeDao.count1(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztPointAgree> list(PageTableRequest request) {
                return zlztPointAgreeDao.list1(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }



    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztPointAgreeDao.delete(id);
    }

    @PostMapping("/addPoint")
    @ApiOperation(value = "添加点赞")
    public JSONObject addPoint(@RequestBody ZlztPointAgree zlztPointAgree) {
        JSONObject json = new JSONObject();
        int i = zlztPointAgreeDao.selectSameData(zlztPointAgree);
        if (i == -1){
            zlztPointAgreeDao.save(zlztPointAgree);
            //数据表点赞字段+1
            zlztPointAgreeDao.addNumByData(zlztPointAgree);
            int pointNum = zlztPointAgreeDao.selectPointNum(zlztPointAgree);
            if (pointNum!=-1){
                json.put("pointNum",pointNum);
            }
            json.put("msg","success");
        }else {
            json.put("msg","exist");
        }
        return json;
    }

    @DeleteMapping("/deletePoint")
    @ApiOperation(value = "删除点赞")
    public JSONObject deletePoint(@RequestBody ZlztPointAgree zlztPointAgree){
        JSONObject json = new JSONObject();
        zlztPointAgreeDao.deletePoint(zlztPointAgree);
        zlztPointAgreeDao.deleteNumByData(zlztPointAgree);
        int pointNum = zlztPointAgreeDao.selectPointNum(zlztPointAgree);
        if (pointNum!=-1){
            json.put("pointNum",pointNum);
        }
        return json;
    }

    @GetMapping("/getTitle")
    public JSONObject getTitle(@RequestParam(value = "table") String table,@RequestParam(value = "agreeId")  int agreeId){
        JSONObject json = new JSONObject();
        if (table != null && ! table.equals("") && agreeId != 0) {
            String colum ="name";
            if (table.equals("view_zlztdata") || table.equals("view_gfdata") || table.equals("view_yfdata")  ){
                colum = "title";
            }
            String title = zlztPointAgreeDao.getTitle(table,colum,agreeId);
            json.put("msg",title);
        }else{
            json.put("msg","fail");
        }
        return json;
    }
}
