package com.boot.security.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.dao.ZlztClassifyinfoDao;
import com.boot.security.server.service.ZlztDatainfoService;
import com.boot.security.server.utils.ExcelAndImgUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.ZlztDatainfoDao;
import com.boot.security.server.model.ZlztDatainfo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "数据管理")
@RestController
@RequestMapping("/zlztDatainfos")
public class ZlztDatainfoController {

    @Autowired
    private ZlztDatainfoDao zlztDatainfoDao;
    @Autowired
    private ZlztClassifyinfoDao zlztClassifyinfoDao;
    @Autowired
    private ZlztDatainfoService zlztDatainfoService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ExcelAndImgUtil excelAndImgUtil;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZlztDatainfo save(@RequestBody ZlztDatainfo zlztDatainfo) {
        zlztDatainfoDao.save(zlztDatainfo);

        return zlztDatainfo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZlztDatainfo get(@PathVariable Long id) {
        return zlztDatainfoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZlztDatainfo update(@RequestBody ZlztDatainfo zlztDatainfo) {
        zlztDatainfoDao.update(zlztDatainfo);

        return zlztDatainfo;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zlztDatainfoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZlztDatainfo> list(PageTableRequest request) {
                return zlztDatainfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zlztDatainfoDao.delete(id);
    }

    @PostMapping("/upexcel")
    @ApiOperation(value = "上传Excel")
    public JSONObject upexcel(MultipartFile file) throws IOException, ParseException {
        JSONObject json = new JSONObject();
        zlztDatainfoService.save(file,zlztClassifyinfoDao);
        json.put("msg","success");
        return json ;
    }

    /*@PostMapping("/getallcount")
    public List<ZlztDatainfo> getAllcount(@RequestBody ZlztDatainfo zlztDatainfo){
        return zlztDatainfoService.getAllcount(zlztDatainfo,"");
    }*/

// excel导出
    @RequestMapping(value="/downloadExcel")
    @ApiOperation(value = "excel导出")
    public void downloadExcel(@RequestParam(value="jsonString") String[] id, @RequestParam(value = "fromTable") String fromTable ,String fileName, HttpServletResponse response) throws IOException {
        String tableClassift = "";
        if (fromTable.equals("view_gfdata")){
            tableClassift = "gf_classifyinfo";
        }else if (fromTable.equals("view_zlztdata")){
            tableClassift = "zlzt_classifyinfo";
        }
        String sql ="SELECT\n" +
                "\tb.name '所属专题',\n" +
                "\tc.name '二级专题',\n" +
                "\td.name '一级分类',\n" +
                "\te.name '二级分类',\n" +
                "\tcountry '国家',\n" +
                "\topenId '公开号',\n" +
                "\topenDay '公开日',\n" +
                "\tapplyId '申请号',\n" +
                "\tapplyDay '申请日',\n" +
                "\ttitle '标题',\n" +
                "\ttitleFy '标题-翻译',\n" +
                "\tzy '摘要',\n" +
                "\tzyFy '摘要-翻译',\n" +
                "\tpowerAsk '权利要求',\n" +
                "\tpowerAFy '权利要求翻译',\n" +
                "\tpowerAN '权利要求数',\n" +
                "\tapplyMan '申请人',\n" +
                "\tapplyPlace '申请人地址',\n" +
                "\tcreateMan '发明人',\n" +
                "\tlawS '法律状态',\n" +
                "\tnowLawS '当前法律状态',\n" +
                "\tzlType '专利类型',\n" +
                "\tipcMC 'IPC主分类',\n" +
                "\tipcMCFy '主分类（翻译）',\n" +
                "\tipc 'IPC分类',\n" +
                "\tipcFy 'IPC分类（翻译）',\n" +
                "\tcpec '国民经济分类',\n" +
                "\tcpecFy '国民经济分类（翻译）',\n" +
                "\tbeUsedNum '被引证次数',\n" +
                "\t a.value '价值度',\n" +
                "\tzlImage '专利附图',\n" +
                "\tzlText '专利全文',\n" +
                "\thomeImage '首页附图'\n" +
                "FROM\n" + fromTable  +" a\n"+
                " INNER JOIN " +tableClassift+ " b\n" +
                "on a.fId = b.id\n" +
                "INNER JOIN " +tableClassift+ " c\n" +
                "on a.sId = c.id\n" +
                "INNER JOIN " +tableClassift+ " d\n" +
                "on a.tId = d.id\n" +
                "INNER JOIN " +tableClassift+ " e\n" +
                "on a.cId = e.id";
        if (id.length>0){
            sql += " where a.id in (";
            for (int i =0;i<id.length;i++){
                if (i==0){
                    sql += id[i];
                }else{
                    sql += ","+id[i];
                }
            }
            sql+=")";
        }
//        System.out.println("sql="+sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (!CollectionUtils.isEmpty(list)) {
            Map<String, Object> map = list.get(0);

            String[] headers = new String[map.size()];
            int i = 0;
            for (String key : map.keySet()) {
                headers[i++] = key;
            }

            List<Object[]> datas = new ArrayList<>(list.size());

            for (Map<String, Object> mapList : list) {
                int length = headers.length;
                Object[] object = new Object[length];
                for (int j = 0; j < length; j++) {
                    object[j] = mapList.get(headers[j]);
                }
                datas.add(object);
            }
            excelAndImgUtil.excelExport(
                    fileName == null || fileName.trim().length() <= 0 ? DigestUtils.md5Hex(sql) : fileName, headers,
                    datas, response);
        }
    }

    @PostMapping("/getListDetail")
    @ApiOperation(value = "上下翻页")
    public List<ZlztDatainfo> getListDetail(@RequestParam(value = "params") String json,@RequestParam(value = "id") String id){
        JSONObject params = JSONObject.parseObject(json);
//        System.out.println(params);
//        System.out.println(id);
        List<ZlztDatainfo> list = Lists.newArrayList();
        params.put("fType",1);
        ZlztDatainfo zlztDatainfo = zlztDatainfoDao.selectListByParams(params,id);
        if (zlztDatainfo == null ){
            zlztDatainfo = new ZlztDatainfo();
        }
        list.add(zlztDatainfo);
        ZlztDatainfo zlztDatainfo1 = zlztDatainfoDao.getById(Long.valueOf(id));
        if (zlztDatainfo1 == null ){
            zlztDatainfo1 = new ZlztDatainfo();
        }
        list.add(zlztDatainfo1);
        params.put("fType",2);
        ZlztDatainfo zlztDatainfo2 = zlztDatainfoDao.selectListByParams(params,id);
        if (zlztDatainfo2 == null ){
            zlztDatainfo2 = new ZlztDatainfo();
        }
        list.add(zlztDatainfo2);
        return list;
    }

}
