package com.boot.security.server.controller;

import java.util.List;

import com.google.common.collect.Lists;
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
import com.boot.security.server.dao.GfClassifyInfoDao;
import com.boot.security.server.model.GfClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "高分类别管理")
@RestController
@RequestMapping("/gfClassifyInfos")
public class GfClassifyInfoController {

    @Autowired
    private GfClassifyInfoDao gfClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public GfClassifyInfo save(@RequestBody GfClassifyInfo gfClassifyInfo) {
        gfClassifyInfoDao.save(gfClassifyInfo);

        return gfClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public GfClassifyInfo get(@PathVariable Long id) {
        return gfClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public GfClassifyInfo update(@RequestBody GfClassifyInfo gfClassifyInfo) {
        gfClassifyInfoDao.update(gfClassifyInfo);

        return gfClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return gfClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<GfClassifyInfo> list(PageTableRequest request) {
                return gfClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        gfClassifyInfoDao.delete(id);
    }

    @GetMapping("/treetable/{id}/{type}")
    @ApiOperation(value = "菜单列表")
    public GfClassifyInfo treetable(@PathVariable Long id,@PathVariable Long type) {

        GfClassifyInfo gfClassifyInfo = new GfClassifyInfo();

        List<GfClassifyInfo> gfClassifyInfoListAll = gfClassifyInfoDao.listAll();
        List<GfClassifyInfo> list = Lists.newArrayList();
        if (type==1){
            gfClassifyInfo = gfClassifyInfoDao.getById(id);
            setTreeTableList(id, gfClassifyInfoListAll, list);
        }else{
            gfClassifyInfo = gfClassifyInfoDao.getById((long)gfClassifyInfoDao.getParentId(id));
            setTreeTableList((long)gfClassifyInfoDao.getParentId(id), gfClassifyInfoListAll, list);
        }
        gfClassifyInfo.setChildren(list);
        return gfClassifyInfo;
    }

    private void setTreeTableList(Long pId, List<GfClassifyInfo> gfClassifyInfoListAll, List<GfClassifyInfo> list) {
        for (GfClassifyInfo gf : gfClassifyInfoListAll) {
            if (pId.equals(gf.getParentId().longValue())) {
                list.add(gf);
                if (gfClassifyInfoListAll.stream().filter(p -> p.getParentId().equals(gf.getId())).findAny() != null) {
                    setTreeTableList(gf.getId(), gfClassifyInfoListAll, list);
                }
            }
        }
    }


    @GetMapping("/getNowPlace/{id}/{type}")
    @ApiOperation(value = "获取当前所在位置")
    public List<GfClassifyInfo> getNowPlace(@PathVariable Long id,@PathVariable Long type){
        List<GfClassifyInfo> gfClassifyInfoList = gfClassifyInfoDao.getParentInfo(id);
        return gfClassifyInfoList;
    }

    @GetMapping("/getClassify")
    @ApiOperation(value = "获取二级分类")
    public List<GfClassifyInfo> getClassify(){
        return gfClassifyInfoDao.getClassify(1);
    }

    @GetMapping("/getParentClassifyInfo/{id}")
    @ApiOperation(value = "根据parentid获取二级")
    public List<GfClassifyInfo> getClassifyByParentId(@PathVariable Long id){
        return gfClassifyInfoDao.getParentClassifyInfo(id.intValue());
    }


    @GetMapping("/getGfInfo")
    @ApiOperation(value = "获取一级和二级分类")
    public List<GfClassifyInfo> getGfInfo(){
        List<GfClassifyInfo> gfClassifyInfoList = gfClassifyInfoDao.getParentClassifyInfo(0);
        if (gfClassifyInfoList.size()>0){
            for (int i =0; i<gfClassifyInfoList.size();i++){
                GfClassifyInfo gfClassifyInfo = gfClassifyInfoList.get(i);
                List<GfClassifyInfo> gfClassifyInfoList2 = gfClassifyInfoDao.getParentClassifyInfo(gfClassifyInfo.getId().intValue());
                gfClassifyInfo.setChildren(gfClassifyInfoList2);
            }
        }
        return gfClassifyInfoList;
    }
}
