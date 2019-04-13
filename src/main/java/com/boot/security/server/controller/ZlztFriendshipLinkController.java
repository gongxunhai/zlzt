package com.boot.security.server.controller;

import java.util.List;

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
import com.boot.security.server.dao.ZlztFriendshipLinkDao;
import com.boot.security.server.model.ZlztFriendshipLink;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zlztFriendshipLinks")
public class ZlztFriendshipLinkController {

    @Autowired
    private ZlztFriendshipLinkDao zlztFriendshipLinkDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztFriendshipLink save(@RequestBody ZlztFriendshipLink zlztFriendshipLink) {
        zlztFriendshipLinkDao.save(zlztFriendshipLink);

        return zlztFriendshipLink;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztFriendshipLink get(@PathVariable Long id) {
        return zlztFriendshipLinkDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztFriendshipLink update(@RequestBody ZlztFriendshipLink zlztFriendshipLink) {
        zlztFriendshipLinkDao.update(zlztFriendshipLink);

        return zlztFriendshipLink;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztFriendshipLinkDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztFriendshipLink> list(PageTableRequest request) {
                return zlztFriendshipLinkDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztFriendshipLinkDao.delete(id);
    }
}
