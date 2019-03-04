package com.boot.security.server.service;

import com.boot.security.server.model.ZlztDatainfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ZlztDatainfoService {

    void save(MultipartFile file,Object object) throws IOException, ParseException;

    List<ZlztDatainfo> getAllcount(ZlztDatainfo zlztDatainfo,String fromTable);
}
