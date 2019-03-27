package com.boot.security.server.controller;

import java.util.List;

import com.boot.security.server.model.KjZlSuperClassifyInfo;
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
}
