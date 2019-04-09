package com.boot.security.server.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
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
    @ApiOperation(value = "科技商城添加点赞")
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
    @ApiOperation(value = "删除")
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
}
