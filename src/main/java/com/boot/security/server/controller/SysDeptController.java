package com.boot.security.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.boot.security.server.model.Permission;
import com.boot.security.server.model.ZTreeModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.security.server.dao.SysDeptDao;
import com.boot.security.server.model.SysDept;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sysDepts")
public class SysDeptController {

    @Autowired
    private SysDeptDao sysDeptDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public SysDept save(@RequestBody SysDept sysDept) {
        sysDeptDao.save(sysDept);

        return sysDept;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public SysDept get(@PathVariable Long id) {
        return sysDeptDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public SysDept update(@RequestBody SysDept sysDept) {
        sysDeptDao.update(sysDept);

        return sysDept;
    }


    /**
     * 部门列表
     * @param pId
     * @param deptAll
     * @param list
     */
    private void setList(Long pId, List<SysDept> deptAll, List<SysDept> list) {
        for (SysDept per : deptAll) {
            if (per.getParentId().equals(pId)) {
                list.add(per);
                if (deptAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    setList(per.getId(), deptAll, list);
                }
            }
        }
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public List<SysDept> list() {
        List<SysDept> deptAll = sysDeptDao.listAll();
        List<SysDept> list = Lists.newArrayList();
        setList(0L, deptAll, list);
        return list;
    }

    @GetMapping("/parentsTree")
    @ApiOperation(value = "一级菜单")
    public List<ZTreeModel> parentsTree() {
        List<ZTreeModel> parentsTree=new ArrayList<>();
        List<SysDept> parents = sysDeptDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级部门",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),parents.get(i).getParentId(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }

    @GetMapping("/deptTree")
    @ApiOperation(value = "一级菜单")
    public List<ZTreeModel> deptTree() {
        List<ZTreeModel> deptTree=new ArrayList<>();
        List<SysDept> parents = sysDeptDao.listAll();
        for(int i=0;i<parents.size();i++){
            deptTree.add(new ZTreeModel(parents.get(i).getId(),parents.get(i).getParentId(),parents.get(i).getName(),true,true));
        }
        return deptTree;
    }

//    @GetMapping
//    @ApiOperation(value = "列表")
//    public PageTableResponse list(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
//
//            @Override
//            public int count(PageTableRequest request) {
//                return sysDeptDao.count(request.getParams());
//            }
//        }, new ListHandler() {
//
//            @Override
//            public List<SysDept> list(PageTableRequest request) {
//                return sysDeptDao.list(request.getParams(), request.getOffset(), request.getLimit());
//            }
//        }).handle(request);
//    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {

        List<SysDept> sysDepts= sysDeptDao.getSubById(id);
        if (sysDepts!=null && sysDepts.size()>0){
            for (int i=0;i<sysDepts.size();i++){
                this.delete(sysDepts.get(i).getId());
            }
        }
        sysDeptDao.delete(id);
    }
}
