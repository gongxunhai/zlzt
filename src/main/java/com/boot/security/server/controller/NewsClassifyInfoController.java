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
import com.boot.security.server.dao.NewsClassifyinfoDao;
import com.boot.security.server.model.NewsClassifyinfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/newsClassifyinfos")
public class NewsClassifyinfoController {

    @Autowired
    private NewsClassifyinfoDao newsClassifyinfoDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public NewsClassifyinfo save(@RequestBody NewsClassifyinfo newsClassifyinfo) {
        newsClassifyinfoDao.save(newsClassifyinfo);

        return newsClassifyinfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public NewsClassifyinfo get(@PathVariable Long id) {
        return newsClassifyinfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public NewsClassifyinfo update(@RequestBody NewsClassifyinfo newsClassifyinfo) {
        newsClassifyinfoDao.update(newsClassifyinfo);

        return newsClassifyinfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return newsClassifyinfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<NewsClassifyinfo> list(PageTableRequest request) {
                return newsClassifyinfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        newsClassifyinfoDao.delete(id);
    }


    @GetMapping("/treeTable")
    @ApiOperation(value = "列表")
    public List<NewsClassifyinfo> list() {
        List<NewsClassifyinfo> deptAll = newsClassifyinfoDao.listAll();
        List<NewsClassifyinfo> list = Lists.newArrayList();
        setTreeTableList(0L, deptAll, list);
        /*if (name!=null && !name.equals("")){
            list = deptAll;
        }else{
            setTreeTableList(0L, deptAll, list);
        }*/
        return list;
    }

    private void setTreeTableList(Long pId, List<NewsClassifyinfo> gfClassifyInfoListAll, List<NewsClassifyinfo> list) {
        for (NewsClassifyinfo gf : gfClassifyInfoListAll) {
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
        List<NewsClassifyinfo> parents = newsClassifyinfoDao.getParentClassifyInfo(0);
//        List<GfClassifyInfo> parents = gfClassifyInfoDao.listAll();
        parentsTree.add(new ZTreeModel(Long.parseLong("0") ,Long.parseLong("-1"),"根级菜单",true,true));
        for(int i=0;i<parents.size();i++){
            parentsTree.add(new ZTreeModel(parents.get(i).getId(),parents.get(i).getParentId().longValue(),parents.get(i).getName(),true,true));
        }
        return parentsTree;
    }

    @GetMapping("/getParentClassifyInfo/{id}")
    @ApiOperation(value = "根据parentid获取二级")
    public List<NewsClassifyinfo> getClassifyByParentId(@PathVariable Long id){
        return newsClassifyinfoDao.getParentClassifyInfo(id.intValue());
    }
}
