package com.boot.security.server.utils;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * excel工具类
 * 
 * @author 小威老师
 *
 */
@Component
public class ExcelAndImgUtil {

	@Value("${files.path}")
	private String filesPath;

	public void excelLocal(String path, String fileName, String[] headers, List<Object[]> datas) throws  IOException {
		ByteArrayOutputStream byteArrayOutputStream = null;
		Workbook workbook = getWorkbook(headers, datas,32);
		if (workbook != null) {
//			ByteArrayOutputStream byteArrayOutputStream = null;
			FileOutputStream fileOutputStream = null;
			try {
				byteArrayOutputStream = new ByteArrayOutputStream();
				workbook.write(byteArrayOutputStream);

				String suffix = ".xls";
				File file = new File(path + File.separator + fileName + suffix);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(byteArrayOutputStream.toByteArray());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fileOutputStream != null) {
						fileOutputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (byteArrayOutputStream != null) {
						byteArrayOutputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 导出excel
	 * 
	 * @param fileName
	 * @param headers
	 * @param datas
	 * @param response
	 */
	public void excelExport(String fileName, String[] headers, List<Object[]> datas,
			HttpServletResponse response ,int imageNum) throws  IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		//获取workbook对象;
		Workbook workbook = getWorkbook(headers, datas,imageNum);
		if (workbook != null) {
			try {
				//workbook写入字节数组输出流
				workbook.write(byteArrayOutputStream);

				//文件出输出位置及格式
				String lastname = ".xls";
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + new String((fileName + lastname).getBytes(), "iso-8859-1"));

				OutputStream outputStream = response.getOutputStream();
				//输出流输出
				outputStream.write(byteArrayOutputStream.toByteArray());
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (byteArrayOutputStream != null) {
						byteArrayOutputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param headers
	 *            列头
	 * @param datas
	 *            数据
	 * @return
	 */
	public Workbook getWorkbook(String[] headers, List<Object[]> datas ,int imageNum) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		CellStyle style1 = workbook.createCellStyle();
		//style1.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		//设置自动换行
		style1.setWrapText(true);
		//垂直居中
		style1.setVerticalAlignment(VerticalAlignment.CENTER);

		Font font = workbook.createFont();

		int line = 0, maxColumn = 0;
		if (headers != null && headers.length > 0) {// 设置列头
			row = sheet.createRow(line++);
		//	row.setHeightInPoints(25);
			//加粗
			font.setBold(true);
			font.setFontHeightInPoints((short) 13);
			style.setFont(font);

			maxColumn = headers.length;
			for (int i = 0; i < maxColumn; i++) {
				cell = row.createCell(i);
				//赋值
				cell.setCellValue(headers[i]);
				cell.setCellStyle(style);
			}
		}

		if (datas != null && datas.size() > 0) {// 渲染数据
			for (int index = 0, size = datas.size(); index < size; index++) {

				Object[] data = datas.get(index);
				if (data != null && data.length > 0) {
					row = sheet.createRow(line++);
				//	row.setHeightInPoints(20);

					int length = data.length;
					if (length > maxColumn) {
						maxColumn = length;
					}

					for (int i = 0; i < length; i++) {
						cell = row.createCell(i);
						if(i==imageNum && data[i]!=null && !data[i].equals("")){
							ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
							//绘图工具
							HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
							//ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
							String fileImage = filesPath+ data[i].toString();
							//文件名后缀
							String lastname = data[i].toString().substring(data[i].toString().indexOf(".")+1);
							//ImageIo读取图片路径
							BufferedImage bufferImg = ImageIO.read(new File(fileImage));
							//设置图片插入excel的位置(前四个参数为dx1 dy1 起始单元格中的x,y坐标.dx2 dy2 结束单元格中的x,y坐标
							//col1行,row1 列指定起始的单元格，下标从0开始，col2,row2 指定结束的单元格 ，下标从0开始
							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) i, index+1,(short) (i+1), (index+2));
							if (lastname.equals("png")){
								//图片插入到excel中
								ImageIO.write(bufferImg,"png",byteArrayOutputStream);
								patriarch.createPicture(anchor,workbook.addPicture(byteArrayOutputStream.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));
							}else if (lastname.equals("jpeg")){
								ImageIO.write(bufferImg,"jpg",byteArrayOutputStream);
								patriarch.createPicture(anchor,workbook.addPicture(byteArrayOutputStream.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));
							}
						}else {
							cell.setCellStyle(style1);
							cell.setCellValue(data[i] == null ? null : data[i].toString());
						}
					}
				}
			}
		}

		for (int i = 0; i < maxColumn; i++) {
			//设置列宽
			if (i==32){
				sheet.setColumnWidth(i,40*256);
			}else {
				//sheet.autoSizeColumn(i);
				sheet.setColumnWidth(i, 15 * 256);
			}
		}
		return workbook;
	}
}
