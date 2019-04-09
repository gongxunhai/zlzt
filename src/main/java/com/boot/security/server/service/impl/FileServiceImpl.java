package com.boot.security.server.service.impl;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.boot.security.server.dao.FileInfoDao;
import com.boot.security.server.model.FileInfo;
import com.boot.security.server.service.FileService;
import com.boot.security.server.utils.FileUtil;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Value("${files.path}")
	private String filesPath;
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	public FileInfo save(MultipartFile file) throws IOException {
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains(".")) {
			throw new IllegalArgumentException("缺少后缀名");
		}

		String md5 = FileUtil.fileMd5(file.getInputStream());
		FileInfo fileInfo = fileInfoDao.getById(md5);
		if (fileInfo != null) {
			fileInfoDao.update(fileInfo);
			return fileInfo;
		}

		String fileLastName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
		String pathname = FileUtil.getPath() + md5 + fileLastName;
		String fullPath = filesPath + pathname;
		FileUtil.saveFile(file, fullPath);

		long size = file.getSize();
		String contentType = file.getContentType();

		fileInfo = new FileInfo();
		fileInfo.setId(md5);
		fileInfo.setContentType(contentType);
		fileInfo.setSize(size);
		fileInfo.setPath(fullPath);
		fileInfo.setUrl(pathname);
		fileInfo.setName(fileOrigName);
		fileInfo.setType(contentType.startsWith("image/") ? 1 : 0);

		fileInfoDao.save(fileInfo);

		log.debug("上传文件{}", fullPath);

		return fileInfo;

	}

	@Override
	public void delete(String id) {
		FileInfo fileInfo = fileInfoDao.getById(id);
		if (fileInfo != null) {
			String fullPath = fileInfo.getPath();
			FileUtil.deleteFile(fullPath);

			fileInfoDao.delete(id);
			log.debug("删除文件：{}", fileInfo.getPath());
		}
	}

    @Override
    public List<FileInfo> saveByZip(MultipartFile file) throws IOException {
        // 判断文件是否为zip文件
        String filename = file.getOriginalFilename();
        //获取md5值
        String md5 = FileUtil.fileMd5(file.getInputStream());
        String allPath = "";
        if (!filename.endsWith("zip")) {
            throw new IllegalArgumentException("传入文件格式不是zip文件" + filename);
        }
        List<FileInfo> list = new ArrayList<FileInfo>();
        String zipFileName = null;
        ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream(), Charset.forName("GBK"));
        BufferedInputStream bs = new BufferedInputStream(zipInputStream);
        ZipEntry zipEntry = null;
        byte[] bytes = null;

        while ((zipEntry = zipInputStream.getNextEntry()) != null) { // 获取zip包中的每一个zip file entry
            if (zipEntry.getSize() > 0 ){
                //获取文件名称
                zipFileName = zipEntry.getName();
                Assert.notNull(zipFileName, "压缩文件中子文件的名字格式不正确");

                FileInfo fileInfo = new FileInfo();

                bytes = new byte[(int) zipEntry.getSize()];  //读一个文件大小
                bs.read(bytes, 0, (int) zipEntry.getSize());
                InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                InputStream byteArrayInputStream1 = new ByteArrayInputStream(bytes);

                //检查子文件夹中是否含有md5相同的数据
                String entrtMd5 = FileUtil.fileMd5(byteArrayInputStream1);
                FileInfo fileInfo1 = fileInfoDao.getById(entrtMd5);
                if (fileInfo1 != null) {
                    fileInfoDao.update(fileInfo1);
                    list.add(fileInfo1);
                    continue;
                }
                fileInfo.setId(entrtMd5);
                // 文件路径
                String pathname = FileUtil.getPath() + entrtMd5 + "/"+zipFileName;
                fileInfo.setUrl(pathname);
                allPath = allPath + pathname ;
                String fullPath = filesPath + pathname;
                fileInfo.setPath(fullPath);
                File targetFile = new File(fullPath);
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                //获取文件名称
                String name = targetFile.getName();
                fileInfo.setName(name);
                //获取文件大小
                long size = zipEntry.getSize();
                fileInfo.setSize(size);
                String type = Files.probeContentType(new File(fullPath).toPath());
                fileInfo.setContentType(type);
                fileInfo.setType(type.startsWith("image/") ? 1 : 0);

                FileOutputStream fos = new FileOutputStream(fullPath);
                byte[] b = new byte[1024];
                int length = 0;
                while( (length= byteArrayInputStream.read(b)) != -1){
                    fos.write(b,0,length);
                }
                byteArrayInputStream.close();
                fos.close();
                fileInfoDao.save(fileInfo);
                list.add(fileInfo);
            }
        }
        return list ;
    }

}
