package com.boot.security.server.controller;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.server.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.boot.security.server.annotation.LogAnnotation;
import com.boot.security.server.dao.FileInfoDao;
import com.boot.security.server.dto.LayuiFile;
import com.boot.security.server.dto.LayuiFile.LayuiFileData;
import com.boot.security.server.model.FileInfo;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServlet;

@Api(tags = "文件")
@RestController
@RequestMapping("/files")
public class FileController extends HttpServlet {

    private static final long serialVersionUID = 564190060577130813L;
    @Autowired
	private FileService fileService;
	@Autowired
	private FileInfoDao fileInfoDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "文件上传")
	public FileInfo uploadFile(MultipartFile file) throws IOException {
		return fileService.save(file);
}

	/**
	 * layui富文本文件自定义上传
	 * 
	 * @param file
	 * @param domain
	 * @return
	 * @throws IOException
	 */
	@LogAnnotation
	@PostMapping("/layui")
	@ApiOperation(value = "layui富文本文件自定义上传")
	public LayuiFile uploadLayuiFile(MultipartFile file, String domain) throws IOException {
		FileInfo fileInfo = fileService.save(file);

		LayuiFile layuiFile = new LayuiFile();
		layuiFile.setCode(0);
		LayuiFileData data = new LayuiFileData();
		layuiFile.setData(data);
		data.setSrc(domain + "/statics" + fileInfo.getUrl());
		data.setTitle(file.getOriginalFilename());

		return layuiFile;
	}

	@GetMapping
	@ApiOperation(value = "文件查询")
	@PreAuthorize("hasAuthority('sys:file:query')")
	public PageTableResponse listFiles(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return fileInfoDao.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<FileInfo> list(PageTableRequest request) {
				List<FileInfo> list = fileInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "文件删除")
	@PreAuthorize("hasAuthority('sys:file:del')")
	public void delete(@PathVariable String id) {
		fileService.delete(id);
	}

	@PostMapping("/upZip")
    @ApiOperation(value = "上传压缩包解析图片")
	public List<FileInfo> upImage(MultipartFile file) throws IOException{
        return fileService.saveByZip(file);
    }
}
