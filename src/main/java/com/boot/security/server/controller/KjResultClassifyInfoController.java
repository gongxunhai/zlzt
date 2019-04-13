package com.boot.security.server.controller;

import java.util.ArrayList;
import java.util.List;

import com.boot.security.server.model.KjZlSuperClassifyInfo;
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
import com.boot.security.server.dao.KjResultClassifyInfoDao;
import com.boot.security.server.model.KjResultClassifyInfo;

import io.swagger.annotations.ApiOperation;

@Api(tags = "科技成果分类管理")
@RestController
@RequestMapping("/kjResultClassifyInfos")
public class KjResultClassifyInfoController {

    @Autowired
    private KjResultClassifyInfoDao kjResultClassifyInfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjResultClassifyInfo save(@RequestBody KjResultClassifyInfo kjResultClassifyInfo) {
        kjResultClassifyInfoDao.save(kjResultClassifyInfo);

        return kjResultClassifyInfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjResultClassifyInfo get(@PathVariable Long id) {
        return kjResultClassifyInfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjResultClassifyInfo update(@RequestBody KjResultClassifyInfo kjResultClassifyInfo) {
        kjResultClassifyInfoDao.update(kjResultClassifyInfo);

        return kjResultClassifyInfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjResultClassifyInfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjResultClassifyInfo> list(PageTableRequest request) {
                return kjResultClassifyInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjResultClassifyInfoDao.delete(id);
    }

    @GetMapping("/selectSidByFid")
    @ApiOperation(value = "获取二级id通过一级id")
    public int selectSidByFid(@RequestParam("fid") int fid ){
        int sId = kjResultClassifyInfoDao.selectSidByFid(fid);
        if (sId == -1){
            KjResultClassifyInfo kjResultClassifyInfo = new KjResultClassifyInfo();
            kjResultClassifyInfo.setParentId(fid);
            kjResultClassifyInfo.setName("其它");
            kjResultClassifyInfo.setType(2);
            kjResultClassifyInfoDao.save(kjResultClassifyInfo);
            sId = kjResultClassifyInfo.getId().intValue();
        }
        return sId;
    }

    @PostMapping("/changeFromJob")
    @ApiOperation(value = "获取用户中心发布新项目的所属行业")
    public List<KjResultClassifyInfo> changeFromJob(){
        return kjResultClassifyInfoDao.listData((long) 0);
    }

    @GetMapping("/getClassify/{id}")
    @ApiOperation(value = "获取1级分类")
    public List<KjResultClassifyInfo> getClassifyByParentId(@PathVariable Long id){
        return kjResultClassifyInfoDao.listData(id);
    }

    @GetMapping("/treeTable")
    @ApiOperation(value = "列表")
    public List<KjResultClassifyInfo> list() {
        List<KjResultClassifyInfo> deptAll = kjResultClassifyInfoDao.listAll();
        List<KjResultClassifyInfo> list = Lists.newArrayList();
        setTreeTableList(0L, deptAll, list);
       /* if (name!=null && !name.equals("")){
            list = deptAll;
        }else{
            setTreeTableList(0L, deptAll, list);
        }*/
        return list;
    }

    private void setTreeTableList(Long pId, List<KjResultClassifyInfo> kjProductClassifyInfoListAll, List<KjResultClassifyInfo> list) {
        for (KjResultClassifyInfo kj : kjProductClassifyInfoListAll) {
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
        List<KjResultClassifyInfo> parents = kjResultClassifyInfoDao.getClassify(1);
//        List<GfClassifyInfo> parents = gfClassifyInfoDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级菜单",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),(long) parents.get(i).getParentId(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }
}
