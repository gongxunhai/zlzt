package com.boot.security.server.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.ZlztUserDao;
import com.boot.security.server.model.ZlztUser;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zlztUsers")
@Api(tags = "会员中心")
public class ZlztUserController {

    @Autowired
    private ZlztUserDao zlztUserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztUser save(@RequestBody ZlztUser zlztUser) {
        zlztUser.setPassword(passwordEncoder.encode(zlztUser.getPassword()));
        zlztUserDao.save(zlztUser);
        return zlztUser;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztUser get(@PathVariable Long id) {
        return zlztUserDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztUser update(@RequestBody ZlztUser zlztUser) {
        zlztUserDao.update(zlztUser);

        return zlztUser;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztUserDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztUser> list(PageTableRequest request) {
                return zlztUserDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztUserDao.delete(id);
    }

    @PostMapping("/selectKey")
    @ApiOperation(value = "手机和邮箱在数据库是否有重复")
    public JSONObject selectPhone(@RequestParam(value = "key") String key,@RequestParam(value = "value") String value){
        int i = zlztUserDao.selectParam(key,value);
        JSONObject json = new JSONObject();
        if (i == -1){
            json.put("msg","success");
        }else {
            json.put("msg","fail");
        }
        return json;
    }

    @PostMapping("/userLogin")
    @ApiOperation(value = "通过手机和邮箱获取密码")
    public JSONObject getPwdByPhoneOrEmail(@RequestParam(value = "phoneOrEmail") String phoneOrEmail,@RequestParam(value = "password") String password){
        JSONObject json = new JSONObject();
        json.put("msg","fail");
        ZlztUser zlztUser = zlztUserDao.getPwdByPhoneOrEmail(phoneOrEmail);
        if (zlztUser!=null){
            String oldPwd = zlztUser.getPassword();
            if (passwordEncoder.matches(password,oldPwd)) {
                json.put("msg","success");
                json.put("foreSession",zlztUser);
            }
        }else{
            json.put("msg","losePwd");
        }
        return json;
    }
}
