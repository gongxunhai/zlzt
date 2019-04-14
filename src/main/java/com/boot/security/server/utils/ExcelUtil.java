package com.boot.security.server.utils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.boot.security.server.dao.*;
import com.boot.security.server.model.*;
import com.boot.security.server.service.impl.SysLogServiceImpl;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel工具类
 * 
 * @author 小威老师
 *
 */
@Component
public class ExcelUtil {

	@Value("${files.path}")
	private String filesPath;
	private final static String xls = "xls";
	private final static String xlsx = "xlsx";
	private static String fileName = "";
	private static String md5 = "";
	@Autowired
	private ZlztIpcinfoDao zlztIpcinfoDao;
	@Autowired
	private ZlztCpinfoDao zlztCpinfoDao;
	@Autowired
	private SysLogServiceImpl sysLogService;
	@Autowired
	private ZlztDatainfoDao zlztDatainfoDao;
	@Autowired
	private ZlztDataDetailDao zlztDataDetailDao;
	@Autowired
	private GfDatainfoDao gfDatainfoDao;
	@Autowired
	private YfCompanyDao yfCompanyDao;
	@Autowired
	private ZlztClassifyinfoDao zlztClassifyinfoDao;
	@Autowired
	private GfClassifyInfoDao gfClassifyInfoDao;
	@Autowired
	private YfClassifyInfoDao yfClassifyInfoDao;
	@Autowired
	private KjResultClassifyInfoDao kjResultClassifyInfoDao;
	@Autowired
	private KjResultDao kjResultDao;
	@Autowired
	private KjZlSuperClassifyInfoDao kjZlSuperClassifyInfoDao;
	@Autowired
	private KjZlsuperDao kjZlsuperDao;
	@Autowired
	private KjProductClassifyInfoDao kjProductClassifyInfoDao;
	@Autowired
	private KjProductDao kjProductDao;
	@Autowired
	private PjRequireDao pjRequireDao;
	@Autowired
	private PjRequireClassifyInfoDao pjRequireClassifyInfoDao;

	//excel导出
	public static void excelLocal(String path, String fileName, String[] headers, List<Object[]> datas) {
		Workbook workbook = getWorkbook(headers, datas);
		if (workbook != null) {
			ByteArrayOutputStream byteArrayOutputStream = null;
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
	public static void excelExport(String fileName, String[] headers, List<Object[]> datas,
			HttpServletResponse response) {
		Workbook workbook = getWorkbook(headers, datas);
		if (workbook != null) {
			ByteArrayOutputStream byteArrayOutputStream = null;
			try {
				byteArrayOutputStream = new ByteArrayOutputStream();
				workbook.write(byteArrayOutputStream);

				String suffix = ".xls";
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + new String((fileName + suffix).getBytes(), "iso-8859-1"));

				OutputStream outputStream = response.getOutputStream();
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
	public static Workbook getWorkbook(String[] headers, List<Object[]> datas) {
		Workbook workbook = new HSSFWorkbook();

		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		CellStyle style1 = workbook.createCellStyle();
		//style1.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		//	style1.setWrapText(true);
		//	style1.setVerticalAlignment(VerticalAlignment.CENTER);

		Font font = workbook.createFont();

		int line = 0, maxColumn = 0;
		if (headers != null && headers.length > 0) {// 设置列头
			row = sheet.createRow(line++);
			row.setHeightInPoints(25);
			font.setBold(true);
			font.setFontHeightInPoints((short) 13);
			style.setFont(font);

			maxColumn = headers.length;
			for (int i = 0; i < maxColumn; i++) {
				cell = row.createCell(i);
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
						cell.setCellStyle(style1);
						cell.setCellValue(data[i] == null ? null : data[i].toString());
					}
				}
			}
		}

		for (int i = 0; i < maxColumn; i++) {
			sheet.autoSizeColumn(i);
		}
		return workbook;
	}

	//校验file格式
	public static void checkfile(MultipartFile file) throws  IOException{
		String xls = "xls";
		String xlsx = "xlsx";

		if (file == null){
			throw new IllegalArgumentException("文件为空");
		}

		if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
			throw new IOException(fileName + "不是excel文件");
		}

		if (!fileName.contains(".")) {
			throw new IllegalArgumentException("缺少后缀名");
		}
	}
	/**
	 * 判断上传文件的格式并获取workbook对象
	 */
	public static Workbook getWorkBook(MultipartFile file) throws IOException {
		fileName = file.getOriginalFilename();
		boolean isExcel2003 = true;
		//创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		//获取excel文件的io流
		InputStream is = file.getInputStream();
		if (fileName.endsWith(xls)) {// 当excel是2003时,创建excel2003
			workbook = new HSSFWorkbook(is);
		} else if(fileName.endsWith(xlsx)) {// 当excel是2007时,创建excel2007
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	public static Map<String, PictureData> getmaplist(Workbook workbook) throws IOException {
		Map<String, PictureData> maplist= null;
		Sheet sheet = workbook.getSheetAt(0);
		// 判断用07还是03的方法获取图片
		if (fileName.endsWith(xls)) {
			maplist = getPictures1((HSSFSheet) sheet);
		} else if(fileName.endsWith(xlsx)){
			maplist = getPictures2((XSSFSheet) sheet);
		}
//		System.out.println(maplist);
		return maplist;
	}

	/**
	 * 获取图片和位置 (xls)
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public static Map<String, PictureData> getPictures1 (HSSFSheet sheet) throws IOException {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
		for (HSSFShape shape : list) {
			if (shape instanceof HSSFPicture) {
				HSSFPicture picture = (HSSFPicture) shape;
				HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
				PictureData pdata = picture.getPictureData();
				String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
				map.put(key, pdata);
			}
		}
		return map;
	}

	/**
	 * 获取图片和位置 (xlsx)
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public static Map<String, PictureData> getPictures2 (XSSFSheet sheet) throws IOException {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		List<POIXMLDocumentPart> list = sheet.getRelations();
		for (POIXMLDocumentPart part : list) {
			if (part instanceof XSSFDrawing) {
				XSSFDrawing drawing = (XSSFDrawing) part;
				List<XSSFShape> shapes = drawing.getShapes();
				for (XSSFShape shape : shapes) {
					XSSFPicture picture = (XSSFPicture) shape;
					XSSFClientAnchor anchor = picture.getPreferredSize();
					CTMarker marker = anchor.getFrom();
					String key = marker.getRow() + "-" + marker.getCol();
					map.put(key, picture.getPictureData());
				}
			}
		}
		return map;
	}

	//图片写出
	public Map<Object, String> printImg(Map<String, PictureData> sheetList) throws IOException {

		//for (Map<String, PictureData> map : sheetList) {
		Map<Object, String> map =new HashMap<Object, String>();
		Object key[] = sheetList.keySet().toArray();
		for (int i = 0; i < sheetList.size(); i++) {
			// 获取图片流
			PictureData pic = sheetList.get(key[i]);
			// 获取图片索引
			String picName = key[i].toString();
			// 获取图片格式
			String ext = pic.suggestFileExtension();

			byte[] data = pic.getData();

			String pathname = getPath()+picName + "." + ext;
			String fullpath = filesPath + pathname;

			File file = new File(fullpath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			map.put(key[i],pathname);
			//图片保存路径
			FileOutputStream out = new FileOutputStream(fullpath);
			out.write(data);
			out.close();
		}
		// }

		return map;
	}

	public static String getPath() {
		return "/" + LocalDate.now().toString().replace("-", "/") + "/"+md5+"/";
	}
	//excel导入
	public void getDataFromExcel(MultipartFile file,Object object) throws IOException, ParseException {
		//文件的md5值
		md5 = FileUtil.fileMd5(file.getInputStream());
		//获取文件的后缀
		fileName = file.getOriginalFilename();
		//校验文件
		checkfile(file);
		//校验文件
		Workbook workbook = getWorkBook(file);
		//获取excel的图片
		Map<String, PictureData> maplist = ExcelUtil.getmaplist(workbook);
		Sheet sheet = workbook.getSheetAt(0);
		//输出图片并且返回图片输出路径
		ExcelUtil e = new ExcelUtil();
		Map<Object,String> map = printImg(maplist);

		int lastNum = sheet.getLastRowNum(); //获取Excel最后一行索引
		int colNum = sheet.getRow(0).getLastCellNum();//获取Excel列数
		int exceptionNum = 0;
		int losezlzt = 0;
		int loseelse = 0;
		int successNum = 0;

		for(int r=1;r<=lastNum;r++) {//读取每一行，第一行为标题，从第二行开始
			Row row = sheet.getRow(r);
			ZlztDatainfo zldata = new ZlztDatainfo();
			ZlztDataDetail zlztDataDetail = new ZlztDataDetail();
			GfDatainfo gfDatainfo = new GfDatainfo();
			YfCompany yfCompany = new YfCompany();
			int i = 0;
			int j = 0;
			String openid = row.getCell(10).getStringCellValue();
			if (row.getCell(10) != null && !row.getCell(10).toString().equals("")){
                int openIdNum = zlztDatainfoDao.selectOpenid(openid);
                if (openIdNum==-1){
                    for (int m = 0; m < colNum; m++) {//读取每一行的每一列
                        if (m==0){
                            if (row.getCell(m)==null || row.getCell(m).toString().equals("")){
                                losezlzt ++;
                                break;
                            }
                            i = selectIdByClassify(row.getCell(m),object);
                            if (object instanceof ZlztClassifyinfoDao){
                                zlztDataDetail.setFId(i);
                            }else if (object instanceof GfClassifyInfoDao){
                                gfDatainfo.setFId(i);
                            }else if (object instanceof  YfClassifyInfoDao){
                                yfCompany.setFId(i);
                            }
                            //zldata.setfId(i);
                            if (i==-1){
                                loseelse ++;
                                break;
                            }
                        }else if (m==1){
                            if (row.getCell(m)==null || row.getCell(m).toString().equals("")){
                                loseelse ++;
                                break;
                            }
                            if (object instanceof ZlztClassifyinfoDao){
                                j = zlztClassifyinfoDao.getParentIdByName(row.getCell(m).toString(),i);
                            }else if (object instanceof GfClassifyInfoDao){
                                j = gfClassifyInfoDao.getParentIdByName(row.getCell(m).toString(),i);
                            }else if (object instanceof  YfClassifyInfoDao){
                                j = yfClassifyInfoDao.getParentIdByName(row.getCell(m).toString(),i);
                            }
                            if ( j==-1){
                                loseelse ++;
                                break;
                            }
                            if (i!=j){
                                loseelse ++;
                                break;
                            }
                            i = selectIdByClassify(row.getCell(m),object);
                            if (object instanceof ZlztClassifyinfoDao){
                                zlztDataDetail.setSId(i);
                            }else if (object instanceof GfClassifyInfoDao){
                                gfDatainfo.setSId(i);
                            }else if (object instanceof  YfClassifyInfoDao){
                                yfCompany.setSId(i);
                            }
                            //					zldata.setsId(i);
                        }else if (m==2){
                            i = getClassifyId(row.getCell(m),i,3,object);
                            if (object instanceof ZlztClassifyinfoDao){
                                zlztDataDetail.setTId(i);
                            }else if (object instanceof GfClassifyInfoDao){
                                gfDatainfo.setTId(i);
                            }else if (object instanceof  YfClassifyInfoDao){
                                yfCompany.setTId(i);
                            }
                            //					zldata.settId(i);
                        }else if (m==3){
                            i = getClassifyId(row.getCell(m),i,4,object);
                            if (object instanceof ZlztClassifyinfoDao){
                                zlztDataDetail.setCId(i);
                            }else if (object instanceof GfClassifyInfoDao){
                                gfDatainfo.setCId(i);
                            }else if (object instanceof  YfClassifyInfoDao){
                                yfCompany.setCId(i);
                            }
                            //					zldata.setcId(i);
                        }else if (m==5){

                        }

                        if (m==23){
                            if (row.getCell(m)==null || row.getCell(m).toString().equals("")){
                                loseelse ++;
                                break;
                            }
                            String ipc =  row.getCell(m).toString();
                            zldata.setIpcMC(ipc);
                            String ipcname = zlztIpcinfoDao.getByIpcId(ipc.substring(0,4));
                            if (ipcname==null || ipcname.equals("")){
                                loseelse ++;
                                break;
                            }
                            zldata.setIpcMCFy(ipcname);
                        }else if (m==24){
                            if (row.getCell(m)==null || row.getCell(m).toString().equals("")){
                                loseelse ++;
                                break;
                            }
                            String ipc = row.getCell(m).toString();
                            zldata.setIpc(ipc);

                            if (ipc.indexOf(";")!= -1){
                                String[] ipcArray = ipc.split(";");
                                String ipcAllName = clearContains(ipcArray);
                                if (ipcAllName==null || ipcAllName.equals("")){
                                    loseelse ++;
                                    break;
                                }
                                zldata.setIpcFy(ipcAllName);
                            }
                            else {
                                String ipcAllName = zlztIpcinfoDao.getByIpcId(ipc.substring(0,4));
                                zldata.setIpcFy(ipcAllName);
                            }
                        }else if (m==25){
                            if (row.getCell(m)==null || row.getCell(m).toString().equals("")){
                                loseelse ++;
                                break;
                            }
                            String cp = row.getCell(m).toString();
                            zldata.setCpec(cp);
                            String cpName = zlztCpinfoDao.getBycpId(cp);
                            if (cpName==null || cpName.equals("")){
                                loseelse ++;
                                break;
                            }
                            zldata.setCpecFy(cpName);
                        }
                        for (Map.Entry<Object,String> entry : map.entrySet()){
                            String entrykey = entry.getKey().toString();
                            int key1 = Integer.parseInt(entrykey.substring(0,entrykey.indexOf("-")));
                            int key2 = Integer.parseInt(entrykey.substring(entrykey.indexOf("-")+1,entrykey.length()));
                            if (key1 == r && key2 == m){
                                zldata.setHomeImage(entry.getValue());
                            }
                            //   System.out.println("key="+entry.getKey()+", value="+entry.getValue());
                        }
                        Cell cell = row.getCell(m);
                        if (cell!=null && !cell.equals("")){
                            //日期格式转化
                            if (m == 11) {
                                //excel为日期格式
                                switch (cell.getCellTypeEnum()){
                                    case NUMERIC: zldata.setOpenDay(cell.getDateCellValue());break;
                                    case STRING: zldata.setOpenDay(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue())); break;
                                }
                            }
                            if (m == 13) {
                                switch (cell.getCellTypeEnum()){
                                    case NUMERIC: zldata.setApplyDay(cell.getDateCellValue());break;
                                    case STRING: zldata.setApplyDay(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue())); break;
                                }
                            }
                            System.out.println("cellType============="+cell.getCellTypeEnum());
                            System.out.println("第"+r+"行-"+"第"+m+"列:"+cell.toString());
                            switch (m){
                                case 4 : zldata.setTitle(cell.getStringCellValue()); break;
                                case 5 : zldata.setTitleFy(cell.getStringCellValue()); break;
                                case 6 : zldata.setZy(cell.getStringCellValue()); break;
                                case 7 : zldata.setZyFy(cell.getStringCellValue()); break;
                                case 9 : zldata.setValue(String.valueOf(cell.getNumericCellValue())); break;
                                case 10 : zldata.setOpenId(cell.getStringCellValue()); break;
                                case 12 : zldata.setApplyId(cell.getStringCellValue()); break;
                                case 14 : zldata.setApplyMan(cell.getStringCellValue()); break;
                                case 15 : zldata.setCountry(cell.getStringCellValue()); break;
                                case 16 : zldata.setApplyPlace(cell.getStringCellValue()); break;
                                case 17 : zldata.setPowerAsk(cell.getStringCellValue()); break;
                                case 18 : zldata.setPowerAN((int) cell.getNumericCellValue()); break;
                                case 19 : zldata.setLawS(cell.getStringCellValue()); break;
                                case 20 : zldata.setNowLawS(cell.getStringCellValue()); break;
                                case 21 : zldata.setZlType(cell.getStringCellValue()); break;
                                case 22 : zldata.setCreateMan(cell.getStringCellValue()); break;
                                case 26 : zldata.setBeUsedNum(String.valueOf(cell.getNumericCellValue())); break;
                                case 29 : zldata.setPowerAFy(cell.getStringCellValue()); break;
                            }
                        }
                    }
                }else {
                    for (int m = 0; m < 4; m++) {//读取每一行的每一列
                        if (m == 0) {
                            if (row.getCell(m) == null || row.getCell(m).toString().equals("")) {
                                losezlzt++;
                                break;
                            }
                            i = selectIdByClassify(row.getCell(m), object);
                            if (object instanceof ZlztClassifyinfoDao) {
                                zlztDataDetail.setFId(i);
                            } else if (object instanceof GfClassifyInfoDao) {
                                gfDatainfo.setFId(i);
                            } else if (object instanceof YfClassifyInfoDao) {
                                yfCompany.setFId(i);
                            }
                            if (i == -1) {
                                loseelse++;
                                break;
                            }
                        } else if (m == 1) {
                            if (row.getCell(m) == null || row.getCell(m).toString().equals("")) {
                                loseelse++;
                                break;
                            }
                            if (object instanceof ZlztClassifyinfoDao) {
                                j = zlztClassifyinfoDao.getParentIdByName(row.getCell(m).toString(), i);
                            } else if (object instanceof GfClassifyInfoDao) {
                                j = gfClassifyInfoDao.getParentIdByName(row.getCell(m).toString(), i);
                            } else if (object instanceof YfClassifyInfoDao) {
                                j = yfClassifyInfoDao.getParentIdByName(row.getCell(m).toString(), i);
                            }
                            if (j == -1) {
                                loseelse++;
                                break;
                            }
                            if (i != j) {
                                loseelse++;
                                break;
                            }
                            i = selectIdByClassify(row.getCell(m), object);
                            if (object instanceof ZlztClassifyinfoDao) {
                                zlztDataDetail.setSId(i);
                            } else if (object instanceof GfClassifyInfoDao) {
                                gfDatainfo.setSId(i);
                            } else if (object instanceof YfClassifyInfoDao) {
                                yfCompany.setSId(i);
                            }
                        } else if (m == 2) {
                            i = getClassifyId(row.getCell(m), i, 3, object);
                            if (object instanceof ZlztClassifyinfoDao) {
                                zlztDataDetail.setTId(i);
                            } else if (object instanceof GfClassifyInfoDao) {
                                gfDatainfo.setTId(i);
                            } else if (object instanceof YfClassifyInfoDao) {
                                yfCompany.setTId(i);
                            }
                        } else if (m == 3) {
                            i = getClassifyId(row.getCell(m), i, 4, object);
                            if (object instanceof ZlztClassifyinfoDao) {
                                zlztDataDetail.setCId(i);
                            } else if (object instanceof GfClassifyInfoDao) {
                                gfDatainfo.setCId(i);
                            } else if (object instanceof YfClassifyInfoDao) {
                                yfCompany.setCId(i);
                            }
                        }
                    }
                    if (object instanceof ZlztClassifyinfoDao){
                        zlztDataDetail.setDataId(openIdNum);
                        int k = zlztDataDetailDao.selectIdByDataId(zlztDataDetail);
                        if ( k==-1){
                            zlztDataDetailDao.save(zlztDataDetail);
                        }else {
                            loseelse++;
                            continue;
                        }
                    }else if (object instanceof GfClassifyInfoDao){
                        gfDatainfo.setDataId(openIdNum);
                        int k = gfDatainfoDao.selectIdByDataId(gfDatainfo);
                        if ( k==-1){
                            gfDatainfoDao.save(gfDatainfo);
                        }else {
                            loseelse++;
                            continue;
                        }
                    }else if (object instanceof  YfClassifyInfoDao){
                        yfCompany.setDataId(openIdNum);
                        int k = yfCompanyDao.selectIdByDataId(yfCompany);
                        if ( k==-1){
                            yfCompanyDao.save(yfCompany);
                        }else {
                            loseelse++;
                            continue;
                        }
                    }
//				break;
                }
                if (zldata.getOpenId()!=null && !zldata.getOpenId().equals("")){
                    successNum += zlztDatainfoDao.save(zldata);
                    long zlztid = zldata.getId();
                    if (object instanceof ZlztClassifyinfoDao){
                        zlztDataDetail.setDataId((int)zlztid);
                        zlztDataDetailDao.save(zlztDataDetail);
                    }else if (object instanceof GfClassifyInfoDao){
                        gfDatainfo.setDataId((int) zlztid);
                        gfDatainfoDao.save(gfDatainfo);
                    }else if (object instanceof  YfClassifyInfoDao){
                        yfCompany.setDataId((int) zlztid);
                        yfCompanyDao.save(yfCompany);
                    }
                }
            }
        }
		SysUser sysUser  = UserUtil.getLoginUser();
		if(UserUtil.getLoginUser()!=null){
            exceptionNum = losezlzt + loseelse;
            sysLogService.save(sysUser.getId(),"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失专利专题的为:"+losezlzt+"条,其它的为:"+loseelse+"条)");
        }else {
            sysLogService.save((long) 1,"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失专利专题的为:"+losezlzt+"条,其它的为:"+loseelse+"条)");
        }

		//   for (Map.Entry<String, PictureData> entry : maplist.entrySet()) {
		//       System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		//   }
	}

/*
*
*   拼接ipc分类翻译
* @param ipcArray
* @return
*/
   public static void selectFourClassify(){

   }
	private String clearContains(String[] ipcArray) {
		List<String> list = new ArrayList<String>();
		String ipcAllName ="";
		for (int i=0; i<ipcArray.length; i++) {
			ipcArray[i] =ipcArray[i].substring(0,4) ;
			if(!list.contains(ipcArray[i])) {
				list.add(ipcArray[i]);
			}
		}
		for (int j=0;j<list.size();j++){
			String zlztlist = zlztIpcinfoDao.getByIpcId(list.get(j));
			if (j==0 && !zlztlist.equals("") && zlztlist!=null){
				ipcAllName += zlztIpcinfoDao.getByIpcId(list.get(j));
			}else if(!zlztlist.equals("") && zlztlist!=null){
				ipcAllName += ";"+ zlztIpcinfoDao.getByIpcId(list.get(j));
			}

		}
		return ipcAllName;
	}

	/**
	 *  类别管理
	 * @param cell
    */
	public int getClassifyId(Cell cell,int i,int t,Object object){
		ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
		GfClassifyInfo gfClassifyInfo = new GfClassifyInfo();
		YfClassifyInfo yfClassifyInfo = new YfClassifyInfo();
		if (cell !=null && !cell.toString().equals("") ){
			int j=0;
			String cellValue =cell.toString();
			if (object instanceof ZlztClassifyinfoDao){
				//获取上级id
				j =zlztClassifyinfoDao.getParentIdByName(cell.toString(),i);
				if (j == i){
					//if上级id相等，就取自身id返回
					i = checkClassify(cell,i,object);
				}else if (j == -1){
					zlztClassifyinfo.setName(cellValue);
					zlztClassifyinfo.setParentId(i);
					zlztClassifyinfo.setType(t);
					zlztClassifyinfoDao.save(zlztClassifyinfo);
					i = zlztClassifyinfo.getId().intValue();
					//上级ID为空，且数据为空就放入到其它中
				}
			}else if (object instanceof GfClassifyInfoDao){
				j =gfClassifyInfoDao.getParentIdByName(cell.toString(),i);
				if (j == i){
					//if上级id相等，就取自身id返回
					i = checkClassify(cell,i,object);
				}else if (j == -1){
					gfClassifyInfo.setName(cellValue);
					gfClassifyInfo.setParentId(i);
					gfClassifyInfo.setType(t);
					gfClassifyInfoDao.save(gfClassifyInfo);
					i = gfClassifyInfo.getId().intValue();
					//上级ID为空，且数据为空就放入到其它中
				}
			}else if (object instanceof  YfClassifyInfoDao){
				j =yfClassifyInfoDao.getParentIdByName(cell.toString(),i);
				if (j == i){
					//if上级id相等，就取自身id返回
					i = checkClassify(cell,i,object);
				}else if (j == -1){
					yfClassifyInfo.setName(cellValue);
					yfClassifyInfo.setParentId(i);
					yfClassifyInfo.setType(t);
					yfClassifyInfoDao.save(yfClassifyInfo);
					i = yfClassifyInfo.getId().intValue();
					//上级ID为空，且数据为空就放入到其它中
				}
			}
			//当前行为空，但是存在1级以及2级，就加入到其它中
		}else {
			int k =0;
			if (object instanceof ZlztClassifyinfoDao){
				//查询2级中是否已经存在其它
				k = zlztClassifyinfoDao.getByNameAndParentId("其它",i);
				if (k!= -1){
					i = k;
				}else {
					zlztClassifyinfo.setName("其它");
					zlztClassifyinfo.setParentId(i);
					zlztClassifyinfo.setType(t);
					zlztClassifyinfoDao.save(zlztClassifyinfo);
					i = zlztClassifyinfo.getId().intValue();
				}
			}else if (object instanceof GfClassifyInfoDao){
				k = gfClassifyInfoDao.getByNameAndParentId("其它",i);
				if (k!= -1){
					i = k;
				}else {
					gfClassifyInfo.setName("其它");
					gfClassifyInfo.setParentId(i);
					gfClassifyInfo.setType(t);
					gfClassifyInfoDao.save(gfClassifyInfo);
					i = gfClassifyInfo.getId().intValue();
				}
			}else if (object instanceof YfClassifyInfoDao){
				k = yfClassifyInfoDao.getByNameAndParentId("其它",i);
				if (k!= -1){
					i = k;
				}else {
					yfClassifyInfo.setName("其它");
					yfClassifyInfo.setParentId(i);
					yfClassifyInfo.setType(t);
					yfClassifyInfoDao.save(yfClassifyInfo);
					i = yfClassifyInfo.getId().intValue();
				}
			}
		}
		return i;
	}
/**
 *  类别管理
 * @param cell
 */
	public int selectIdByClassify(Cell cell,Object object){
		String celltostring = cell.toString();
		int i = 0;
		if (cell!=null && !cell.toString().equals("")){
			if (object instanceof ZlztClassifyinfoDao){
				i = zlztClassifyinfoDao.getIdByName(celltostring);
			}else if (object instanceof GfClassifyInfoDao){
				i = gfClassifyInfoDao.getIdByName(celltostring);
			}else if (object instanceof YfClassifyInfoDao){
				i = yfClassifyInfoDao.getIdByName(celltostring);
			}
		}
		return i;
	}

	public int checkClassify(Cell cell,int i,Object object){
		String celltostring = cell.toString();
		if (cell!=null && ! cell.toString().equals("")){
			if (object instanceof ZlztClassifyinfoDao){
				i = zlztClassifyinfoDao.getByNameAndParentId(celltostring,i);
			}else if (object instanceof GfClassifyInfoDao){
				i = gfClassifyInfoDao.getByNameAndParentId(celltostring,i);
			}else if (object instanceof YfClassifyInfoDao){
				i = yfClassifyInfoDao.getByNameAndParentId(celltostring,i);
			}
		}
		return i;
	}

	public void getExcelBykjReSult(MultipartFile file) throws IOException, ParseException {
		//文件的md5值
		md5 = FileUtil.fileMd5(file.getInputStream());
		//获取文件的后缀
		fileName = file.getOriginalFilename();
		//校验文件
		checkfile(file);
		//校验文件
		Workbook workbook = getWorkBook(file);
		//获取excel的图片
		Map<String, PictureData> maplist = ExcelUtil.getmaplist(workbook);
		Sheet sheet = workbook.getSheetAt(0);
		//输出图片并且返回图片输出路径
		ExcelUtil e = new ExcelUtil();
		Map<Object,String> map = printImg(maplist);

		//数据为num类型格式化
        DecimalFormat df = new DecimalFormat("0");

		int lastNum = sheet.getLastRowNum(); //获取Excel最后一行索引
		int colNum = sheet.getRow(0).getLastCellNum();//获取Excel列数
		int exceptionNum = 0;
		int losefId = 0;
		int loseelse = 0;
		int successNum = 0;

		for(int r=1;r<=lastNum;r++) {//读取每一行，第一行为标题，从第二行开始
			Row row = sheet.getRow(r);
			KjResult kjResult = new KjResult();
			int i = 0;
			for (int m = 0; m < colNum; m++) {
				if (m == 1){
					if (row.getCell(m)!=null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						i = kjResultClassifyInfoDao.selectIdByNameAndParentId(cellToString,0);
						if (i == -1){
						    losefId++;
							break;
						}
						kjResult.setfId(i);
					}else {
                        losefId++;
                        break;
                    }
				}
				if ( m == 2 ){
					if (row.getCell(m)!=null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						int j  = kjResultClassifyInfoDao.selectIdByNameAndParentId(cellToString,i);
						if(j ==-1){
							KjResultClassifyInfo kjResultClassifyInfo = new KjResultClassifyInfo();
							kjResultClassifyInfo.setParentId(i);
							kjResultClassifyInfo.setName(cellToString);
							kjResultClassifyInfo.setType(2);
							kjResultClassifyInfoDao.save(kjResultClassifyInfo);
                            j = kjResultClassifyInfo.getId().intValue();
						}
						kjResult.setSId(j);
					}else {
                        int j = kjResultClassifyInfoDao.selectIdByNameAndParentId("其它",i);
                        if (j == -1){
                            KjResultClassifyInfo kjResultClassifyInfo = new KjResultClassifyInfo();
                            kjResultClassifyInfo.setParentId(i);
                            kjResultClassifyInfo.setName("其它");
                            kjResultClassifyInfo.setType(2);
                            kjResultClassifyInfoDao.save(kjResultClassifyInfo);
                            j = kjResultClassifyInfo.getId().intValue();
                        }
                        kjResult.setSId(j);
					}
				}
				for (Map.Entry<Object, String> entry : map.entrySet()) {
					String entrykey = entry.getKey().toString();
					//字符串截取
					int key1 = Integer.parseInt(entry.getKey().toString().substring(0, entrykey.indexOf("-")));
					int key2 = Integer.parseInt(entry.getKey().toString().substring(entrykey.indexOf("-") + 1, entrykey.length()));
					if (key1 == r && key2 == m) {
						kjResult.setImage(entry.getValue());
					}
					//   System.out.println("key="+entry.getKey()+", value="+entry.getValue());
				}
				Cell cell = row.getCell(m);
				if (cell!=null && !cell.toString().equals("")){
					if (m == 0) {
						//excel为日期格式
						switch (cell.getCellTypeEnum()){
							case NUMERIC: kjResult.setPubTime(cell.getDateCellValue());break;
							case STRING: kjResult.setPubTime(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue())); break;
						}
					}
					switch (m){
						case 3 : kjResult.setXfArea(cell.getStringCellValue()); break;
						case 4 : kjResult.setName(cell.getStringCellValue()); break;
						case 5 : kjResult.setSource(cell.getStringCellValue()); break;
						case 6 : kjResult.setTlevel(cell.getStringCellValue()); break;
						case 7 : kjResult.setUseRange(cell.getStringCellValue()); break;
						case 8 : kjResult.setCWays(cell.getStringCellValue()); break;
						case 9 : kjResult.setmNeed(cell.getStringCellValue()); break;
						case 10 : kjResult.setTeMaturity(cell.getStringCellValue()); break;
						case 11 : kjResult.setArea(cell.getStringCellValue()); break;
						case 12 : kjResult.setTdetails(cell.getStringCellValue()); break;
						case 13 : kjResult.setTIndex(cell.getStringCellValue()); break;
						case 14 : kjResult.setKnowledge(cell.getStringCellValue()); break;
						case 15 : kjResult.setPredict(cell.getStringCellValue()); break;
						case 16 : kjResult.setCMan(cell.getStringCellValue()); break;
						case 17 : kjResult.setCPhone(df.format(cell.getNumericCellValue())); break;
						case 23 : kjResult.setUpdateTime(new Date());break;
					}
				}
			}
			if (kjResult.getFId()!=null && ! kjResult.getFId().equals("")){
			    kjResult.setStatus(1);
				kjResultDao.save(kjResult);
                successNum++;
			}
		}
        SysUser sysUser  = UserUtil.getLoginUser();
        if(UserUtil.getLoginUser()!=null){
            exceptionNum = losefId + loseelse;
            sysLogService.save(sysUser.getId(),"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }else {
            sysLogService.save((long) 1,"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }
	}

	public void getExcelBykjSuper(MultipartFile file) throws IOException, ParseException {
		//文件的md5值
		md5 = FileUtil.fileMd5(file.getInputStream());
		//获取文件的后缀
		fileName = file.getOriginalFilename();
		//校验文件
		checkfile(file);
		//校验文件
		Workbook workbook = getWorkBook(file);
		//获取excel的图片
		Map<String, PictureData> maplist = ExcelUtil.getmaplist(workbook);
		Sheet sheet = workbook.getSheetAt(0);
		//输出图片并且返回图片输出路径
		ExcelUtil e = new ExcelUtil();
		Map<Object,String> map = printImg(maplist);
        //数据为num类型格式化
        DecimalFormat df = new DecimalFormat("0");


		int lastNum = sheet.getLastRowNum(); //获取Excel最后一行索引
		int colNum = sheet.getRow(0).getLastCellNum();//获取Excel列数
		int exceptionNum = 0;
		int losefId = 0;
		int loseelse = 0;
		int successNum = 0;

		for(int r=1;r<=lastNum;r++) {//读取每一行，第一行为标题，从第二行开始
			Row row = sheet.getRow(r);
			KjZlsuper kjZlsuper = new KjZlsuper();
			int i = 0;
			for (int m = 0; m < colNum; m++) {
				if (m == 0){
					if (row.getCell(m)!=null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						i = kjZlSuperClassifyInfoDao.selectIdByNameAndParentId(cellToString,0);
						if (i==-1){
                            losefId++;
							break;
						}
						kjZlsuper.setfId(i);
					}else {
                        losefId++;
                        break;
					}
				}
				if ( m == 1 ){
					if (row.getCell(m)!=null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						int j = kjZlSuperClassifyInfoDao.selectIdByNameAndParentId(cellToString,i);
						if(j == -1) {
                            KjZlSuperClassifyInfo kjZlSuperClassifyInfo = new KjZlSuperClassifyInfo();
                            kjZlSuperClassifyInfo.setParentId(i);
                            kjZlSuperClassifyInfo.setName(cellToString);
                            kjZlSuperClassifyInfo.setType(2);
                            kjZlSuperClassifyInfoDao.save(kjZlSuperClassifyInfo);
                            j = kjZlSuperClassifyInfo.getId().intValue();
                        }
                        kjZlsuper.setSId(j);
					}else {
						int j = kjZlSuperClassifyInfoDao.selectIdByNameAndParentId("其它",i);
						if (j == -1){
                            KjZlSuperClassifyInfo kjZlSuperClassifyInfo = new KjZlSuperClassifyInfo();
                            kjZlSuperClassifyInfo.setParentId(i);
                            kjZlSuperClassifyInfo.setName("其它");
                            kjZlSuperClassifyInfo.setType(2);
                            kjZlSuperClassifyInfoDao.save(kjZlSuperClassifyInfo);
                            j = kjZlSuperClassifyInfo.getId().intValue();
                        }
                        kjZlsuper.setSId(j);
					}
				}
				for (Map.Entry<Object, String> entry : map.entrySet()) {
					String entrykey = entry.getKey().toString();
					//字符串截取
					int key1 = Integer.parseInt(entry.getKey().toString().substring(0, entrykey.indexOf("-")));
					int key2 = Integer.parseInt(entry.getKey().toString().substring(entrykey.indexOf("-") + 1, entrykey.length()));
					if (key1 == r && key2 == m) {
						kjZlsuper.setImage(entry.getValue());
					}
					//   System.out.println("key="+entry.getKey()+", value="+entry.getValue());
				}
				Cell cell = row.getCell(m);
				if (cell!=null && !cell.toString().equals("")){
					if (m == 17) {
						//excel为日期格式
						switch (cell.getCellTypeEnum()){
							case NUMERIC: kjZlsuper.setPubTime(cell.getDateCellValue());break;
							case STRING: kjZlsuper.setPubTime(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue())); break;
						}
					}
					if (m == 15) {
						//excel为日期格式
						switch (cell.getCellTypeEnum()){
							case NUMERIC: kjZlsuper.setCPhone(df.format(cell.getNumericCellValue()));break;
							case STRING: kjZlsuper.setCPhone(cell.getStringCellValue()); break;
						}
					}
					switch (m){
						case 2 : kjZlsuper.setXfArea(cell.getStringCellValue()); break;
						case 3 : kjZlsuper.setName(cell.getStringCellValue()); break;
						case 4 : kjZlsuper.setZlId(cell.getStringCellValue()); break;
						case 5 : kjZlsuper.setZlYear(cell.getStringCellValue()); break;
						case 6 : kjZlsuper.setZlType(cell.getStringCellValue()); break;
						case 7 : kjZlsuper.setLawS(cell.getStringCellValue()); break;
						case 8 : kjZlsuper.setZlDetails(cell.getStringCellValue()); break;
						case 9 : kjZlsuper.setUseArea(cell.getStringCellValue()); break;
						case 10 : kjZlsuper.setTransform(cell.getStringCellValue()); break;
						case 11 : kjZlsuper.setDealWay(cell.getStringCellValue()); break;
						case 12 : kjZlsuper.setDealMoney(cell.getStringCellValue()); break;
						case 14 : kjZlsuper.setCMan(cell.getStringCellValue()); break;
					}
				}
			}
			if (kjZlsuper.getFId()!=null && ! kjZlsuper.getFId().equals("")){
			    kjZlsuper.setStatus(1);
				kjZlsuperDao.save(kjZlsuper);
                successNum++;
			}
		}
        SysUser sysUser  = UserUtil.getLoginUser();
        if(UserUtil.getLoginUser()!=null){
            exceptionNum = losefId + loseelse;
            sysLogService.save(sysUser.getId(),"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }else {
            sysLogService.save((long) 1,"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }
	}

	public void getExcelBykjProduct(MultipartFile file) throws IOException, ParseException {
		//文件的md5值
		md5 = FileUtil.fileMd5(file.getInputStream());
		//获取文件的后缀
		fileName = file.getOriginalFilename();
		//校验文件
		checkfile(file);
		//校验文件
		Workbook workbook = getWorkBook(file);
		//获取excel的图片
		Map<String, PictureData> maplist = ExcelUtil.getmaplist(workbook);
		Sheet sheet = workbook.getSheetAt(0);
		//输出图片并且返回图片输出路径
		ExcelUtil e = new ExcelUtil();
		Map<Object,String> map = printImg(maplist);
        //数据为num类型格式化
        DecimalFormat df = new DecimalFormat("0");

		int lastNum = sheet.getLastRowNum(); //获取Excel最后一行索引
		int colNum = sheet.getRow(0).getLastCellNum();//获取Excel列数
		int exceptionNum = 0;
		int losefId = 0;
		int loseelse = 0;
		int successNum = 0;

		for(int r=1;r<=lastNum;r++) {//读取每一行，第一行为标题，从第二行开始
			Row row = sheet.getRow(r);
			KjProduct kjProduct = new KjProduct();
			int i = 0;
			for (int m = 0; m < colNum; m++) {
				if (m == 0){
					if (row.getCell(m)!=null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						i = kjProductClassifyInfoDao.selectIdByNameAndParentId(cellToString,0);
						if (i==-1){
                            losefId++;
							break;
						}
						kjProduct.setfId(i);
					}else {
                        losefId++;
                        break;
					}
				}
				if ( m == 1 ){
					if (row.getCell(m)!=null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						int j = kjProductClassifyInfoDao.selectIdByNameAndParentId(cellToString,i);
						if(j ==-1){
							KjProductClassifyInfo kjProductClassifyInfo = new KjProductClassifyInfo();
							kjProductClassifyInfo.setParentId(i);
							kjProductClassifyInfo.setName(cellToString);
							kjProductClassifyInfo.setType(2);
							kjProductClassifyInfoDao.save(kjProductClassifyInfo);
                            j = kjProductClassifyInfo.getId().intValue();
						}
						kjProduct.setfId(j);
					}else {
                        int j = kjProductClassifyInfoDao.selectIdByNameAndParentId("其它",i);
                        if (j == -1){
                            KjProductClassifyInfo kjProductClassifyInfo = new KjProductClassifyInfo();
                            kjProductClassifyInfo.setParentId(i);
                            kjProductClassifyInfo.setName("其它");
                            kjProductClassifyInfo.setType(2);
                            kjProductClassifyInfoDao.save(kjProductClassifyInfo);
                            j = kjProductClassifyInfo.getId().intValue();
                        }
                        kjProduct.setSId(j);
					}
				}
				for (Map.Entry<Object, String> entry : map.entrySet()) {
					String entrykey = entry.getKey().toString();
					//字符串截取
					int key1 = Integer.parseInt(entry.getKey().toString().substring(0, entrykey.indexOf("-")));
					int key2 = Integer.parseInt(entry.getKey().toString().substring(entrykey.indexOf("-") + 1, entrykey.length()));
					if (key1 == r && key2 == m) {
						kjProduct.setImage(entry.getValue());
					}
					//   System.out.println("key="+entry.getKey()+", value="+entry.getValue());
				}
				Cell cell = row.getCell(m);
				if (cell!=null && !cell.toString().equals("")){
					if (m == 16) {
						//excel为日期格式
						switch (cell.getCellTypeEnum()){
							case NUMERIC: kjProduct.setPubTime(cell.getDateCellValue());break;
							case STRING: kjProduct.setPubTime(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue())); break;
						}
					}
					switch (m){
						case 2 : kjProduct.setXfArea(cell.getStringCellValue()); break;
						case 3 : kjProduct.setName(cell.getStringCellValue()); break;
						case 4 : kjProduct.setPData(cell.getStringCellValue()); break;
						case 5 : kjProduct.setPlace(cell.getStringCellValue()); break;
						case 6 : kjProduct.setPrice(String.valueOf(cell.getNumericCellValue())); break;
						case 7 : kjProduct.setSalePrice(String.valueOf(cell.getNumericCellValue())); break;
						case 8 : kjProduct.setBuyNum(String.valueOf(cell.getNumericCellValue())); break;
						case 9 : kjProduct.setHaveNum(String.valueOf(cell.getNumericCellValue())); break;
						case 10 : kjProduct.setPDetail(cell.getStringCellValue()); break;
						case 12 : kjProduct.setTalk(cell.getStringCellValue()); break;
						case 13 : kjProduct.setHistory(cell.getStringCellValue()); break;
						case 14 : kjProduct.setCMan(cell.getStringCellValue()); break;
						case 15: kjProduct.setCPhone(df.format(cell.getNumericCellValue())); break;
					}
				}
			}
			if (kjProduct.getFId()!=null && ! kjProduct.getFId().equals("")){
				kjProduct.setStatus(1);
//				System.out.println("success");
				kjProductDao.save(kjProduct);
                successNum++;
			}
		}
        SysUser sysUser  = UserUtil.getLoginUser();
        if(UserUtil.getLoginUser()!=null){
            exceptionNum = losefId + loseelse;
            sysLogService.save(sysUser.getId(),"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }else {
            sysLogService.save((long) 1,"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }
	}

	public void getExcelByPjRequire(MultipartFile file) throws IOException, ParseException {

		//文件的md5值
		md5 = FileUtil.fileMd5(file.getInputStream());
		//获取文件的后缀
		fileName = file.getOriginalFilename();
		//校验文件
		checkfile(file);
		//校验文件
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);
        //数据为num类型格式化
        DecimalFormat df = new DecimalFormat("0");

		int lastNum = sheet.getLastRowNum(); //获取Excel最后一行索引
		int colNum = sheet.getRow(0).getLastCellNum();//获取Excel列数
		int exceptionNum = 0;
		int losefId = 0;
		int loseelse = 0;
		int successNum = 0;

		for(int r=1;r<=lastNum;r++) {//读取每一行，第一行为标题，从第二行开始
			Row row = sheet.getRow(r);
			PjRequire pjRequire = new PjRequire();
			int i = 0;
			for (int m = 0; m < colNum; m++) {
				if (m == 1){
					if (row.getCell(m)!=null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						i = pjRequireClassifyInfoDao.selectIdByName(cellToString);
						if (i==-1){
                            losefId++;
							break;
						}
					}else {
						i = pjRequireClassifyInfoDao.selectIdByNameAndType("其它",1);
						pjRequire.setFId(i);
					}
				}
				if ( m == 2 ){
					if (row.getCell(m)!= null && !row.getCell(m).toString().equals("")){
						String cellToString = row.getCell(m).toString();
						int j = pjRequireClassifyInfoDao.selectIdByName(cellToString);
						if(j ==-1){
							PjRequireClassifyInfo pjRequireClassifyInfo = new PjRequireClassifyInfo();
							pjRequireClassifyInfo.setParentId(i);
							pjRequireClassifyInfo.setName(cellToString);
							pjRequireClassifyInfo.setType(2);
							pjRequireClassifyInfoDao.save(pjRequireClassifyInfo);
							i = pjRequireClassifyInfo.getId().intValue();
						}
					}else {
						PjRequireClassifyInfo pjRequireClassifyInfo = new PjRequireClassifyInfo();
						pjRequireClassifyInfo.setParentId(i);
						pjRequireClassifyInfo.setName("其它");
						pjRequireClassifyInfo.setType(2);
						pjRequireClassifyInfoDao.save(pjRequireClassifyInfo);
						i = pjRequireClassifyInfo.getId().intValue();
						pjRequire.setSId(i);
					}
				}
				Cell cell = row.getCell(m);
				if (cell!=null && !cell.toString().equals("")){
					if (m == 9) {
						//excel为日期格式
						switch (cell.getCellTypeEnum()){
							case NUMERIC: pjRequire.setEndTime(cell.getDateCellValue());break;
							case STRING: pjRequire.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue())); break;
						}
					}
					switch (m){
						case 0 : pjRequire.setName(cell.getStringCellValue()); break;
						case 1 : pjRequire.setFId(i); break;
						case 2 : pjRequire.setSId(i); break;
						case 3 : pjRequire.setXfArea(cell.getStringCellValue()); break;
						case 4 : pjRequire.setCWays(cell.getStringCellValue()); break;
						case 5 : pjRequire.setCMoney(cell.getStringCellValue()); break;
						case 6 : pjRequire.setDescription(cell.getStringCellValue()); break;
						case 7 : pjRequire.setTarget(cell.getStringCellValue()); break;
						case 8 : pjRequire.setPlace(cell.getStringCellValue()); break;
						case 10 : pjRequire.setAddFile(cell.getStringCellValue()); break;
						case 11 : pjRequire.setUnit(cell.getStringCellValue()); break;
						case 12 : pjRequire.setMan(cell.getStringCellValue()); break;
						case 13 : pjRequire.setPhone(df.format(cell.getNumericCellValue())); break;
					}
				}
			}
			if (pjRequire.getFId()!=null && ! pjRequire.getFId().equals("")){
				pjRequireDao.save(pjRequire);
                successNum++;
			}
		}
        SysUser sysUser  = UserUtil.getLoginUser();
        if(UserUtil.getLoginUser()!=null){
            exceptionNum = losefId + loseelse;
            sysLogService.save(sysUser.getId(),"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }else {
            sysLogService.save((long) 1,"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失所属行业的为:"+losefId+"条,其它的为:"+loseelse+"条)");
        }
	}
}
