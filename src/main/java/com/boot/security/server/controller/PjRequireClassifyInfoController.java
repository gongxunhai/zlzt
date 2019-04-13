package com.boot.security.server.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.boot.security.server.dao.PjRequireClassifyInfoDao;
import com.boot.security.server.model.PjRequireClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "项目需求分类管理")
@RestController
@RequestMapping("/pjRequireClassifyInfos")
public class PjRequireClassifyInfoController {

    @Autowired
    private PjRequireClassifyInfoDao pjRequireClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public PjRequireClassifyInfo save(@RequestBody PjRequireClassifyInfo pjRequireClassifyInfo) {
        pjRequireClassifyInfoDao.save(pjRequireClassifyInfo);

        return pjRequireClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public PjRequireClassifyInfo get(@PathVariable Long id) {
        return pjRequireClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public PjRequireClassifyInfo update(@RequestBody PjRequireClassifyInfo pjRequireClassifyInfo) {
        pjRequireClassifyInfoDao.update(pjRequireClassifyInfo);

        return pjRequireClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return pjRequireClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<PjRequireClassifyInfo> list(PageTableRequest request) {
                return pjRequireClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        pjRequireClassifyInfoDao.delete(id);
    }

    @PostMapping("/changeFromJob")
    @ApiOperation(value = "获取用户中心发布新项目的所属行业")
    public List<PjRequireClassifyInfo> changeFromJob(){
        return pjRequireClassifyInfoDao.listData((long) 0);
    }

    @GetMapping("/selectSidByFid")
    public int selectSidByFid(@RequestParam("fid") int fid ){
        int sId = pjRequireClassifyInfoDao.selectSidByFid(fid);
        if (sId == -1){
            PjRequireClassifyInfo pjRequireClassifyInfo = new PjRequireClassifyInfo();
            pjRequireClassifyInfo.setParentId(fid);
            pjRequireClassifyInfo.setName("其它");
            pjRequireClassifyInfo.setType(2);
            pjRequireClassifyInfoDao.save(pjRequireClassifyInfo);
            sId = pjRequireClassifyInfo.getId().intValue();
        }
        return sId;
    }

    @GetMapping("/getParentClassifyInfo/{id}")
    @ApiOperation(value = "根据parentid获取二级")
    public List<PjRequireClassifyInfo> getClassifyByParentId(@PathVariable Long id){
        return pjRequireClassifyInfoDao.getParentClassifyInfo(id.intValue());
    }

    @GetMapping("/treeTable")
    @ApiOperation(value = "列表")
    public List<PjRequireClassifyInfo> list() {
        List<PjRequireClassifyInfo> deptAll = pjRequireClassifyInfoDao.listAll();
        List<PjRequireClassifyInfo> list = Lists.newArrayList();
        setTreeTableList(0L, deptAll, list);
        /*if (name!=null && !name.equals("")){
            list = deptAll;
        }else{
            setTreeTableList(0L, deptAll, list);
        }*/
        return list;
    }

    private void setTreeTableList(Long pId, List<PjRequireClassifyInfo> gfClassifyInfoListAll, List<PjRequireClassifyInfo> list) {
        for (PjRequireClassifyInfo gf : gfClassifyInfoListAll) {
            if (pId.equals(gf.getParentId().longValue())) {
                list.add(gf);
                if (gfClassifyInfoListAll.stream().filter(p -> p.getParentId().equals(gf.getId())).findAny() != null) {
                    setTreeTableList(gf.getId(), gfClassifyInfoListAll, list);
                }
            }
        }
    }

    @GetMapping("/parentsTree")
    @ApiOperation(value = "一级菜单")
    public List<ZTreeModel> parentsTree() {
        List<ZTreeModel> parentsTree=new ArrayList<>();
        List<PjRequireClassifyInfo> parents = pjRequireClassifyInfoDao.getParentClassifyInfo(0);
//        List<GfClassifyInfo> parents = gfClassifyInfoDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级菜单",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),parents.get(i).getParentId().longValue(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }

}
