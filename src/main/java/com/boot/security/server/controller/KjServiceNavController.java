package com.boot.security.server.controller;

import java.util.List;

import com.boot.security.server.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.KjServiceNavDao;
import com.boot.security.server.model.KjServiceNav;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/kjServiceNavs")
public class KjServiceNavController {

    @Autowired
    private KjServiceNavDao kjServiceNavDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public KjServiceNav save(@RequestBody KjServiceNav kjServiceNav) {
        kjServiceNavDao.save(kjServiceNav);

        return kjServiceNav;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public KjServiceNav get(@PathVariable Long id) {
        return kjServiceNavDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public KjServiceNav update(@RequestBody KjServiceNav kjServiceNav) {
        kjServiceNavDao.update(kjServiceNav);

        return kjServiceNav;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return kjServiceNavDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<KjServiceNav> list(PageTableRequest request) {
                return kjServiceNavDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        kjServiceNavDao.delete(id);
    }

    @PostMapping("/getDataByFidAndSid")
    public List<KjServiceNav> getDataByFidAndSid(@RequestParam(value = "fid") int fid, @RequestParam(value = "sid") int sid){
        return kjServiceNavDao.getDataByFidAndSid(fid,sid);
    }

}
