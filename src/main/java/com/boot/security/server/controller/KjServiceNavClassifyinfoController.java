package com.boot.security.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.boot.security.server.model.ZTreeModel;
import com.google.common.collect.Lists;
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
import com.boot.security.server.dao.KjServiceNavClassifyinfoDao;
import com.boot.security.server.model.KjServiceNavClassifyinfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kjServiceNavClassifyinfos")
public class KjServiceNavClassifyinfoController {

    @Autowired
    private KjServiceNavClassifyinfoDao kjServiceNavClassifyinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjServiceNavClassifyinfo save(@RequestBody KjServiceNavClassifyinfo kjServiceNavClassifyinfo) {
        kjServiceNavClassifyinfoDao.save(kjServiceNavClassifyinfo);

        return kjServiceNavClassifyinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjServiceNavClassifyinfo get(@PathVariable Long id) {
        return kjServiceNavClassifyinfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjServiceNavClassifyinfo update(@RequestBody KjServiceNavClassifyinfo kjServiceNavClassifyinfo) {
        kjServiceNavClassifyinfoDao.update(kjServiceNavClassifyinfo);

        return kjServiceNavClassifyinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjServiceNavClassifyinfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjServiceNavClassifyinfo> list(PageTableRequest request) {
                return kjServiceNavClassifyinfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjServiceNavClassifyinfoDao.delete(id);
    }

    @GetMapping("/getClassify/{id}")
    @ApiOperation(value = "获取分类")
    public List<KjServiceNavClassifyinfo> getClassifyByParentId(@PathVariable Long id){
        return kjServiceNavClassifyinfoDao.getClassifyByParentId(id.intValue());
    }

    @GetMapping("/getAllData")
    @ApiOperation(value = "获取1,2级分类")
    public List<KjServiceNavClassifyinfo> getAllData(){
        List<KjServiceNavClassifyinfo> list = kjServiceNavClassifyinfoDao.getClassifyByParentId(0);
        for (int i=0 ;i<list.size(); i++){
            list.get(i).setChildren(kjServiceNavClassifyinfoDao.getClassifyByParentId(list.get(i).getId().intValue()));
        }
        return list;
    }

    @GetMapping("/treeTable")
    @ApiOperation(value = "列表")
    public List<KjServiceNavClassifyinfo> list() {
        List<KjServiceNavClassifyinfo> deptAll = kjServiceNavClassifyinfoDao.listAll();
        List<KjServiceNavClassifyinfo> list = Lists.newArrayList();
        setTreeTableList(0L, deptAll, list);
       /* if (name!=null && !name.equals("")){
            list = deptAll;
        }else{
            setTreeTableList(0L, deptAll, list);
        }*/
        return list;
    }

    private void setTreeTableList(Long pId, List<KjServiceNavClassifyinfo> kjProductClassifyInfoListAll, List<KjServiceNavClassifyinfo> list) {
        for (KjServiceNavClassifyinfo kj : kjProductClassifyInfoListAll) {
            if (pId.equals((long) kj.getParentId())) {
                list.add(kj);
                if (kjProductClassifyInfoListAll.stream().filter(p -> new Integer(p.getParentId()).equals(kj.getId())).findAny() != null) {
                    setTreeTableList(kj.getId(), kjProductClassifyInfoListAll, list);
                }
            }
        }
    }


    @GetMapping("/parentsTree")
    @ApiOperation(value = "一级菜单")
    public List<ZTreeModel> parentsTree() {
        List<ZTreeModel> parentsTree=new ArrayList<>();
        List<KjServiceNavClassifyinfo> parents = kjServiceNavClassifyinfoDao.getClassifyByParentId(0);
//        List<GfClassifyInfo> parents = gfClassifyInfoDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级菜单",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),(long) parents.get(i).getParentId(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }
}