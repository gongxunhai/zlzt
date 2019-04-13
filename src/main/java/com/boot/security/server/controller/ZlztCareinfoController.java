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
import com.boot.security.server.dao.ZlztCareinfoDao;
import com.boot.security.server.model.ZlztCareinfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "我的关注")
@RestController
@RequestMapping("/zlztCareinfos")
public class ZlztCareinfoController {

    @Autowired
    private ZlztCareinfoDao zlztCareinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztCareinfo save(@RequestBody ZlztCareinfo zlztCareinfo) {
        zlztCareinfoDao.save(zlztCareinfo);
        return zlztCareinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztCareinfo get(@PathVariable Long id) {
        return zlztCareinfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztCareinfo update(@RequestBody ZlztCareinfo zlztCareinfo) {
        zlztCareinfoDao.update(zlztCareinfo);

        return zlztCareinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztCareinfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztCareinfo> list(PageTableRequest request) {
                return zlztCareinfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/foreRequest")
    @ApiOperation(value = "列表")
    public PageTableResponse list1(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztCareinfoDao.count1(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztCareinfo> list(PageTableRequest request) {
                return zlztCareinfoDao.list1(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {

        zlztCareinfoDao.delete(id);
    }

    @PostMapping("/addCare")
    @ApiOperation(value = "添加收藏")
    public JSONObject addPoint(@RequestBody ZlztCareinfo zlztCareinfo) {
        JSONObject json = new JSONObject();
        int i = zlztCareinfoDao.selectSameData(zlztCareinfo);
        if (i == -1){
            zlztCareinfoDao.save(zlztCareinfo);
            //数据表点赞字段+1
            zlztCareinfoDao.addNumByDate(zlztCareinfo);
            int pointNum = zlztCareinfoDao.selectPointNum(zlztCareinfo);
            if (pointNum!=-1){
                json.put("pointNum",pointNum);
            }
            json.put("msg","success");
        }else {
            json.put("msg","exist");
        }
        return json;
    }

    @DeleteMapping("/deleteCare")
    @ApiOperation(value = "删除收藏")
    public JSONObject deletePoint(@RequestBody ZlztCareinfo zlztCareinfo){
        zlztCareinfoDao.deletePoint(zlztCareinfo);
        JSONObject json = new JSONObject();
        zlztCareinfoDao.deleteNumByData(zlztCareinfo);
        int pointNum = zlztCareinfoDao.selectPointNum(zlztCareinfo);
        if (pointNum!=-1){
            json.put("pointNum",pointNum);
        }
        return json;
    }
    @GetMapping("/getTitle")
    public JSONObject getTitle(@RequestParam(value = "table") String table, @RequestParam(value = "careId")  int careId){
        JSONObject json = new JSONObject();
        if (table != null && ! table.equals("") && careId != 0) {
            String colum ="name";
            if (table.equals("view_zlztdata") || table.equals("view_gfdata") || table.equals("view_yfdata")  ){
                colum = "title";
            }
            String title = zlztCareinfoDao.getTitle(table,colum,careId);
            json.put("msg",title);
        }else{
            json.put("msg","fail");
        }
        return json;
    }
}
