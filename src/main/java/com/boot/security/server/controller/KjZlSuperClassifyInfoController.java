package com.boot.security.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.boot.security.server.model.KjResultClassifyInfo;
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
import com.boot.security.server.dao.KjZlSuperClassifyInfoDao;
import com.boot.security.server.model.KjZlSuperClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "专利超市分类管理")
@RestController
@RequestMapping("/kjZlSuperClassifyInfos")
public class KjZlSuperClassifyInfoController {

    @Autowired
    private KjZlSuperClassifyInfoDao kjZlSuperClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjZlSuperClassifyInfo save(@RequestBody KjZlSuperClassifyInfo kjZlSuperClassifyInfo) {
        kjZlSuperClassifyInfoDao.save(kjZlSuperClassifyInfo);

        return kjZlSuperClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjZlSuperClassifyInfo get(@PathVariable Long id) {
        return kjZlSuperClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjZlSuperClassifyInfo update(@RequestBody KjZlSuperClassifyInfo kjZlSuperClassifyInfo) {
        kjZlSuperClassifyInfoDao.update(kjZlSuperClassifyInfo);

        return kjZlSuperClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjZlSuperClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjZlSuperClassifyInfo> list(PageTableRequest request) {
                return kjZlSuperClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjZlSuperClassifyInfoDao.delete(id);
    }


    @GetMapping("/selectSidByFid")
    @ApiOperation(value = "通过所属行业id获取到二级行业的id")
    public int selectSidByFid(@RequestParam("fid") int fid ){
        int sId = kjZlSuperClassifyInfoDao.selectSidByFid(fid);
        if (sId == -1){
            KjZlSuperClassifyInfo kjZlSuperClassifyInfo = new KjZlSuperClassifyInfo();
            kjZlSuperClassifyInfo.setParentId(fid);
            kjZlSuperClassifyInfo.setName("其它");
            kjZlSuperClassifyInfo.setType(2);
            kjZlSuperClassifyInfoDao.save(kjZlSuperClassifyInfo);
            sId = kjZlSuperClassifyInfo.getId().intValue();
        }
        return sId;
    }

    @PostMapping("/changeFromJob")
    @ApiOperation(value = "获取用户中心发布新项目的所属行业")
    public List<KjZlSuperClassifyInfo> changeFromJob(){
        return kjZlSuperClassifyInfoDao.listData((long) 0);
    }

    @GetMapping("/treeTable")
    @ApiOperation(value = "列表")
    public List<KjZlSuperClassifyInfo> list() {
        List<KjZlSuperClassifyInfo> deptAll = kjZlSuperClassifyInfoDao.listAll();
        List<KjZlSuperClassifyInfo> list = Lists.newArrayList();
        setTreeTableList(0L, deptAll, list);
       /* if (name!=null && !name.equals("")){
            list = deptAll;
        }else{
            setTreeTableList(0L, deptAll, list);
        }*/
        return list;
    }

    private void setTreeTableList(Long pId, List<KjZlSuperClassifyInfo> kjProductClassifyInfoListAll, List<KjZlSuperClassifyInfo> list) {
        for (KjZlSuperClassifyInfo kj : kjProductClassifyInfoListAll) {
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
        List<KjZlSuperClassifyInfo> parents = kjZlSuperClassifyInfoDao.getClassify(1);
//        List<GfClassifyInfo> parents = gfClassifyInfoDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级菜单",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),(long) parents.get(i).getParentId(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }

    @GetMapping("/getClassify/{id}")
    @ApiOperation(value = "获取1级分类")
    public List<KjZlSuperClassifyInfo> getClassifyByParentId(@PathVariable Long id){
        return kjZlSuperClassifyInfoDao.listData(id);
    }
}
