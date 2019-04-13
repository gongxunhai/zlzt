package com.boot.security.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
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
import com.boot.security.server.dao.ZlztClassifyinfoDao;
import com.boot.security.server.model.ZlztClassifyinfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "专利专题类别管理")
@RestController
@RequestMapping("/zlztClassifyinfos")
public class ZlztClassifyinfoController {

    @Autowired
    private ZlztClassifyinfoDao zlztClassifyinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztClassifyinfo save(@RequestBody ZlztClassifyinfo zlztClassifyinfo) {
        zlztClassifyinfoDao.save(zlztClassifyinfo);

        return zlztClassifyinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztClassifyinfo get(@PathVariable Long id) {
        return zlztClassifyinfoDao.getById(id);
    }

    @GetMapping("/getClassify")
    @ApiOperation(value = "获取1级分类")
    public List<ZlztClassifyinfo> getClassify(){
        return zlztClassifyinfoDao.getClassify();
    }

    @GetMapping("/getClassify/{id}")
    @ApiOperation(value = "获取1级分类")
    public List<ZlztClassifyinfo> getClassifyByParentId(@PathVariable Long id){
        return zlztClassifyinfoDao.listData(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztClassifyinfo update(@RequestBody ZlztClassifyinfo zlztClassifyinfo) {
        zlztClassifyinfoDao.update(zlztClassifyinfo);

        return zlztClassifyinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztClassifyinfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztClassifyinfo> list(PageTableRequest request) {
                return zlztClassifyinfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztClassifyinfoDao.delete(id);
    }

    //获取列表1级和2级
    @GetMapping("/listdata")
    @ApiOperation(value = "获取列表1级和2级")
    public List<ZlztClassifyinfo> getListData(){
        List<ZlztClassifyinfo> zlztClassifyinfos = new ArrayList<ZlztClassifyinfo>();
        List<ZlztClassifyinfo> list = zlztClassifyinfoDao.listData((long)0);
        for (int i =0;i<list.size();i++){
            List<ZlztClassifyinfo> list2 =  zlztClassifyinfoDao.listData(list.get(i).getId());
            if (list2.size()>0){
                list.get(i).setChildren(list2);
            }
        }
        return list;
    }

    @GetMapping("/listTable/{id}/{type}")
    @ApiOperation(value = "测试")
    public @ResponseBody ZlztClassifyinfo select(@PathVariable Long id,@PathVariable Long type){
        ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
        //类型为1代表最上级直接取数
        if (type==1){
             zlztClassifyinfo = classifyinfoList(id);
        }else if (type ==2){//类型为2代表第2级，先取上级id再取数
            zlztClassifyinfo = classifyinfoList((long)zlztClassifyinfoDao.getParentId(id));
        }
        return zlztClassifyinfo;
    }

    /***
     * 取树
     * 1.先取当前id,name
     * 2.chidren通过递归遍历
     * @param id
     * @return
     */
    public ZlztClassifyinfo classifyinfoList(Long id){
        ZlztClassifyinfo zlztClassifyinfo = zlztClassifyinfoDao.getById(id);
        zlztClassifyinfo.setChildren(setClassifyinfoList(id));
        return zlztClassifyinfo;
    }

    private List<ZlztClassifyinfo> setClassifyinfoList(long id){
        List<ZlztClassifyinfo> list = Lists.newArrayList();
        //通过parentId获取子分类
        List<ZlztClassifyinfo> list1 = zlztClassifyinfoDao.listData(id);
        //遍历子分类
        for (int i=0; i<list1.size();i++){
            ZlztClassifyinfo zlztClassifyinfo =list1.get(i);
            //递归，如果存在下级就一直执行，直到为空
            if (zlztClassifyinfo.getId()!=null && !zlztClassifyinfo.equals("")){
                List<ZlztClassifyinfo> children = setClassifyinfoList(zlztClassifyinfo.getId());
                if (children.size()!=0){
                    zlztClassifyinfo.setChildren(children);
                }
            }
            list.add(zlztClassifyinfo);
        }
        return list;
    }

    @GetMapping("/treetable/{id}/{type}")
    @ApiOperation(value = "菜单列表")
    public ZlztClassifyinfo treetable(@PathVariable Long id,@PathVariable Long type) {

        ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();

        List<ZlztClassifyinfo> zlztClassifyinfoAll = zlztClassifyinfoDao.listAll();
        List<ZlztClassifyinfo> list = Lists.newArrayList();
        if (type==1){
            zlztClassifyinfo = zlztClassifyinfoDao.getById(id);
            setTreeTableList(id, zlztClassifyinfoAll, list);
        }else{//类型为4代表第4级，先取上级id再取数
            zlztClassifyinfo = zlztClassifyinfoDao.getById((long)zlztClassifyinfoDao.getParentId(id));
            setTreeTableList((long)zlztClassifyinfoDao.getParentId(id), zlztClassifyinfoAll, list);
        }
        zlztClassifyinfo.setChildren(list);
        return zlztClassifyinfo;
    }

    @GetMapping("/danjiTreetable")
    @ApiOperation(value = "单机版菜单列表")
    public List<ZlztClassifyinfo> treetable() {
        List<ZlztClassifyinfo> zlztClassifyinfoList = Lists.newArrayList();
        ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();

        List<ZlztClassifyinfo> zlztClassifyinfoAll = zlztClassifyinfoDao.listAll();
        List<ZlztClassifyinfo> list = zlztClassifyinfoDao.getClassify();
        for (int i = 0;i<list.size();i++){
            List<ZlztClassifyinfo> list1 = Lists.newArrayList();
            zlztClassifyinfo = zlztClassifyinfoDao.getById(list.get(i).getId());
            setTreeTableList(list.get(i).getId(), zlztClassifyinfoAll, list1);
            zlztClassifyinfo.setChildren(list1);
            zlztClassifyinfoList.add(zlztClassifyinfo);
        }
       /* if (type==1){


        }else{//类型为4代表第4级，先取上级id再取数
            zlztClassifyinfo = zlztClassifyinfoDao.getById((long)zlztClassifyinfoDao.getParentId(id));
            setTreeTableList((long)zlztClassifyinfoDao.getParentId(id), zlztClassifyinfoAll, list);
        }*/

        return zlztClassifyinfoList;
    }

    private void setTreeTableList(Long pId, List<ZlztClassifyinfo> zlztClassifyinfoAll, List<ZlztClassifyinfo> list) {
        for (ZlztClassifyinfo zlzt : zlztClassifyinfoAll) {
            if (pId.equals(zlzt.getParentId().longValue())) {
                list.add(zlzt);
                if (zlztClassifyinfoAll.stream().filter(p -> p.getParentId().equals(zlzt.getId())).findAny() != null) {
                    setTreeTableList(zlzt.getId(), zlztClassifyinfoAll, list);
                }
            }
        }
    }

    @GetMapping("/getNowPlace/{id}/{type}")
    @ApiOperation(value = "获取当前位置")
    public List<ZlztClassifyinfo> getNowPlace(@PathVariable Long id,@PathVariable Long type){
        List<ZlztClassifyinfo> zlztClassifyinfo1 =zlztClassifyinfoDao.getParentInfo(id);
        return zlztClassifyinfo1;
    }

    @GetMapping("/treeTable")
    @ApiOperation(value = "列表")
    public List<ZlztClassifyinfo> list(@RequestParam(value = "name") String name) {
        List<ZlztClassifyinfo> deptAll = zlztClassifyinfoDao.listAllByName(name);
        List<ZlztClassifyinfo> list = Lists.newArrayList();
        if (name!=null && !name.equals("")){
            list = deptAll;
        }else{
            setTreeTableList(0L, deptAll, list);
        }
        return list;
    }

    @GetMapping("/parentsTree")
    @ApiOperation(value = "一级菜单")
    public List<ZTreeModel> parentsTree() {
        List<ZTreeModel> parentsTree=new ArrayList<>();
        List<ZlztClassifyinfo> parents = zlztClassifyinfoDao.getClassify();
//        List<GfClassifyInfo> parents = gfClassifyInfoDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级菜单",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),parents.get(i).getParentId().longValue(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }

}
