package com.boot.security.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.boot.security.server.model.GfClassifyInfo;
import com.boot.security.server.model.ZTreeModel;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.YfClassifyInfoDao;
import com.boot.security.server.model.YfClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "承研单位分类管理")
@RestController
@RequestMapping("/yfClassifyInfos")
public class YfClassifyInfoController {

    @Autowired
    private YfClassifyInfoDao yfClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public YfClassifyInfo save(@RequestBody YfClassifyInfo yfClassifyInfo) {
        yfClassifyInfoDao.save(yfClassifyInfo);

        return yfClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public YfClassifyInfo get(@PathVariable Long id) {
        return yfClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public YfClassifyInfo update(@RequestBody YfClassifyInfo yfClassifyInfo) {
        yfClassifyInfoDao.update(yfClassifyInfo);

        return yfClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return yfClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<YfClassifyInfo> list(PageTableRequest request) {
                return yfClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        yfClassifyInfoDao.delete(id);
    }

    @GetMapping("/getYfInfo")
    @ApiOperation(value = "获取1,2级单位名称")
    public List<YfClassifyInfo> getGfInfo(){
        List<YfClassifyInfo> yfClassifyInfoList = yfClassifyInfoDao.getParentClassifyInfo(0);
        if (yfClassifyInfoList.size()>0){
            for (int i =0; i<yfClassifyInfoList.size();i++){
                YfClassifyInfo yfClassifyInfo = yfClassifyInfoList.get(i);
                List<YfClassifyInfo> yfClassifyInfoList2 = yfClassifyInfoDao.getParentClassifyInfo(yfClassifyInfo.getId().intValue());
                yfClassifyInfo.setChildren(yfClassifyInfoList2);
            }
        }
        return yfClassifyInfoList;
    }

    @GetMapping("/treetable/{id}/{type}")
    @ApiOperation(value = "菜单列表")
    public YfClassifyInfo treetable(@PathVariable Long id,@PathVariable Long type) {

        YfClassifyInfo yfClassifyInfo = new YfClassifyInfo();

        List<YfClassifyInfo> yfClassifyInfoListAll = yfClassifyInfoDao.listAll();
        List<YfClassifyInfo> list = Lists.newArrayList();
        if (type==1){
            yfClassifyInfo = yfClassifyInfoDao.getById(id);
            setTreeTableList(id, yfClassifyInfoListAll, list);
        }else{
            yfClassifyInfo = yfClassifyInfoDao.getById((long)yfClassifyInfoDao.getParentId(id));
            setTreeTableList((long)yfClassifyInfoDao.getParentId(id), yfClassifyInfoListAll, list);
        }
        yfClassifyInfo.setChildren(list);
        return yfClassifyInfo;
    }

    private void setTreeTableList(Long pId, List<YfClassifyInfo> yfClassifyInfoListAll, List<YfClassifyInfo> list) {
        for (YfClassifyInfo gf : yfClassifyInfoListAll) {
            if (pId.equals(gf.getParentId().longValue())) {
                list.add(gf);
                if (yfClassifyInfoListAll.stream().filter(p -> p.getParentId().equals(gf.getId())).findAny() != null) {
                    setTreeTableList(gf.getId(), yfClassifyInfoListAll, list);
                }
            }
        }
    }

    @GetMapping("/getNowPlace/{id}/{type}")
    @ApiOperation(value = "获取当前位置")
    public List<YfClassifyInfo> getNowPlace(@PathVariable Long id,@PathVariable Long type){
        List<YfClassifyInfo> yfClassifyInfoList = yfClassifyInfoDao.getParentInfo(id);
        return yfClassifyInfoList;
    }

    @GetMapping("/getParentClassifyInfo/{id}")
    @ApiOperation(value = "根据parentid获取二级")
    public List<YfClassifyInfo> getClassifyByParentId(@PathVariable Long id){
        return yfClassifyInfoDao.getParentClassifyInfo(id.intValue());
    }

    @GetMapping("/parentsTree")
    @ApiOperation(value = "一级菜单")
    public List<ZTreeModel> parentsTree() {
        List<ZTreeModel> parentsTree=new ArrayList<>();
        List<YfClassifyInfo> parents = yfClassifyInfoDao.getClassify(1);
//        List<GfClassifyInfo> parents = gfClassifyInfoDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级菜单",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),parents.get(i).getParentId().longValue(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }

    @GetMapping("/treeTable")
    @ApiOperation(value = "列表")
    public List<YfClassifyInfo> list(@RequestParam(value = "name") String name) {
        List<YfClassifyInfo> deptAll = yfClassifyInfoDao.listAllByName(name);
        List<YfClassifyInfo> list = Lists.newArrayList();
        if (name!=null && !name.equals("")){
            list = deptAll;
        }else{
            setTreeTableList(0L, deptAll, list);
        }
        return list;
    }

}
