package com.boot.security.server.service.impl;

import com.boot.security.server.dao.ZlztClassifyinfoDao;
import com.boot.security.server.dao.ZlztCpinfoDao;
import com.boot.security.server.dao.ZlztDatainfoDao;
import com.boot.security.server.dao.ZlztIpcinfoDao;
import com.boot.security.server.model.SysUser;
import com.boot.security.server.model.ZlztClassifyinfo;
import com.boot.security.server.model.ZlztDatainfo;
import com.boot.security.server.service.ZlztDatainfoService;
import com.boot.security.server.utils.ExcelUtil;
import com.boot.security.server.utils.FileUtil;
import com.boot.security.server.utils.UserUtil;
import com.google.common.collect.Lists;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class ZlztDatainfoServiceImpl implements ZlztDatainfoService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private ZlztDatainfoDao zlztDatainfoDao;
    @Autowired
    private ExcelUtil excelUtil;

    @Override
    public void save(MultipartFile file,Object object) throws  IOException,ParseException {
        excelUtil.getDataFromExcel(file,object);
    }

  /*  public void getDataFromExcel(MultipartFile file) throws IOException, ParseException {
        //文件的md5值
        md5 = FileUtil.fileMd5(file.getInputStream());
        //获取文件的后缀
        fileName = file.getOriginalFilename();
        //校验文件
        excelUtil.checkfile(file);
        // checkfile(file);
        Workbook workbook = excelUtil.getWorkBook(file);
        //获取excel的图片
        Map<String, PictureData> maplist = ExcelUtil.getmaplist(workbook);
        Sheet sheet = workbook.getSheetAt(0);
        //输出图片并且返回图片输出路径
        ExcelUtil e = new ExcelUtil();
        Map<Object,String> map =  excelUtil.printImg(maplist);

        int lastNum = sheet.getLastRowNum(); //获取Excel最后一行索引
        int colNum = sheet.getRow(0).getLastCellNum();//获取Excel列数
        int exceptionNum = 0;
        int losezlzt = 0;
        int loseelse = 0;
        int successNum = 0;

        for(int r=1;r<=lastNum;r++) {//读取每一行，第一行为标题，从第二行开始
            Row row = sheet.getRow(r);
            ZlztDatainfo zldata = new ZlztDatainfo();
            int i = 0;
            int j = 0;
            for (int m = 0; m < colNum; m++) {//读取每一行的每一列
                if (m==0){
                    if (row.getCell(m)==null || row.getCell(m).equals("")){
                        losezlzt ++;
                        break;
                    }
                    i = checkClassify(row.getCell(m));
                    zldata.setfId(i);
                    if (i==-1){
                        loseelse ++;
                        break;
                    }
                }else if (m==1){
                    if (row.getCell(m)==null || row.getCell(m).equals("")){
                        loseelse ++;
                        break;
                    }
                    j =zlztClassifyinfoDao.getParentIdByName(row.getCell(m).toString(),i);
                    if ( j==-1){
                        loseelse ++;
                        break;
                    }
                    if (i!=j){
                        loseelse ++;
                        break;
                    }
                    i = checkClassify(row.getCell(m));
                    zldata.setsId(i);
                }else if (m==2){
                    i = getClassifyId(row.getCell(m),i,3);
                    zldata.settId(i);
                }else if (m==3){
                    i = getClassifyId(row.getCell(m),i,4);
                    zldata.setcId(i);
                }else if (m==22){
                    if (row.getCell(m)==null || row.getCell(m).equals("")){
                        loseelse ++;
                        break;
                    }
                  String ipc =  row.getCell(m).toString();
                  String ipcname = zlztIpcinfoDao.getByIpcId(ipc.substring(0,4));
                  if (ipcname==null || ipcname.equals("")){
                      loseelse ++;
                      break;
                  }
                  zldata.setIpcMCFy(ipcname);
                }else if (m==24){
                    if (row.getCell(m)==null || row.getCell(m).equals("")){
                        loseelse ++;
                        break;
                    }
                    String ipc = row.getCell(m).toString();
                    if (ipc.indexOf(";")!= -1){
                       String[] ipcArray = ipc.split(";");
                       String ipcAllName = clearContains(ipcArray);
                        if (ipcAllName==null || ipcAllName.equals("")){
                            loseelse ++;
                            break;
                        }
                       zldata.setIpcFy(ipcAllName);
                    }
                }else if (m==26){
                    if (row.getCell(m)==null || row.getCell(m).equals("")){
                        loseelse ++;
                        break;
                    }
                    String cp = row.getCell(m).toString();
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
                    if (m == 6) {
                        //excel为日期格式
                        if (cell.getCellTypeEnum().equals("NUMERIC")){
                            zldata.setOpenDay(cell.getDateCellValue());
                            //excel为常规类型
                        }else if (cell.getCellTypeEnum().equals("STRING")){
                            zldata.setOpenDay(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue()));
                        }
                    }
                    if (m == 8) {
                        if (cell.getCellTypeEnum().equals("NUMERIC")){
                            zldata.setApplyDay(cell.getDateCellValue());
                        }else if (cell.getCellTypeEnum().equals("STRING")){
                            zldata.setApplyDay(new SimpleDateFormat("yyyy-MM-dd").parse(cell.getStringCellValue()));
                        }
                    }
                    System.out.println("cellType============="+cell.getCellTypeEnum());
                    System.out.println("第"+r+"行-"+"第"+m+"列:"+cell.toString());
                    switch (m){
                        case 4 : zldata.setCountry(cell.getStringCellValue()); break;
                        case 5 : zldata.setOpenId(cell.getStringCellValue()); break;
                        case 7 : zldata.setApplyId(cell.getStringCellValue()); break;
                        case 9 : zldata.setTitle(cell.getStringCellValue()); break;
                        case 10 : zldata.setTitleFy(cell.getStringCellValue()); break;
                        case 11 : zldata.setZy(cell.getStringCellValue()); break;
                        case 12 : zldata.setZyFy(cell.getStringCellValue()); break;
                        case 13 : zldata.setPowerAsk(cell.getStringCellValue()); break;
                        case 14 : zldata.setPowerAFy(cell.getStringCellValue()); break;
                        case 15 : zldata.setPowerAN((int) cell.getNumericCellValue()); break;
                        case 16 : zldata.setApplyMan(cell.getStringCellValue()); break;
                        case 17 : zldata.setApplyPlace(cell.getStringCellValue()); break;
                        case 18 : zldata.setCreateMan(cell.getStringCellValue()); break;
                        case 19 : zldata.setLawS(cell.getStringCellValue()); break;
                        case 20 : zldata.setNowLawS(cell.getStringCellValue()); break;
                        case 21 : zldata.setZlType(cell.getStringCellValue()); break;
                        case 22 : zldata.setIpcMC(cell.getStringCellValue()); break;
                        case 24 : zldata.setIpc(cell.getStringCellValue()); break;
                        case 26 : zldata.setCpec(cell.getStringCellValue()); break;
                        case 28 : zldata.setBeUsedNum(cell.getStringCellValue()); break;
                        case 29 : zldata.setValue(cell.getStringCellValue()); break;
                        case 30 : zldata.setZlImage(cell.getStringCellValue()); break;
                        case 31 : zldata.setZlText(cell.getStringCellValue()); break;
                    }
                }
            }
            if (zldata.getcId()!=null && !zldata.getcId().equals("") && zldata.getIpcFy()!=null &&!zldata.getIpcFy().equals("")){
//                successNum += zlztDatainfoDao.save(zldata);
            }
        }
        SysUser sysUser  = UserUtil.getLoginUser();
        exceptionNum = losezlzt + loseelse;
        sysLogService.save(sysUser.getId(),"excel导入",true,"成功导入:"+successNum+"条,失败导入:"+exceptionNum+"条(其中缺失专利专题的为:"+losezlzt+"条,其它的为:"+loseelse+"条)");

     //   for (Map.Entry<String, PictureData> entry : maplist.entrySet()) {
     //       System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
     //   }
    }

    *//**
     *   拼接ipc分类翻译
     * @param ipcArray
     * @return
     *//*
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

    *//**
     *  类别管理
     * @param cell
     *//*
    public int getClassifyId(Cell cell,int i,int t){
        ZlztClassifyinfo zlztClassifyinfo = new ZlztClassifyinfo();
        if (cell !=null && !cell.equals("") ){
            //获取上级id
            int j =zlztClassifyinfoDao.getParentIdByName(cell.toString(),i);
            if (j == i){
                //if上级id相等，就取自身id返回
                i = checkClassify(cell,i);
            }else if (j == -1){
                String cellValue =cell.toString();
                zlztClassifyinfo.setName(cellValue);
                zlztClassifyinfo.setParentId(i);
                zlztClassifyinfo.setType(t);
                zlztClassifyinfoDao.save(zlztClassifyinfo);
                i = zlztClassifyinfo.getId().intValue();
                //上级ID为空，且数据为空就放入到其它中
            }
            //当前行为空，但是存在1级以及2级，就加入到其它中
        }else {
            //查询2级中是否已经存在其它
            int k = zlztClassifyinfoDao.getByNameAndParentId("其它",i);
            if (k!= -1){
                i = k;
            }else {
                zlztClassifyinfo.setName("其它");
                zlztClassifyinfo.setParentId(i);
                zlztClassifyinfo.setType(t);
                zlztClassifyinfoDao.save(zlztClassifyinfo);
                i = zlztClassifyinfo.getId().intValue();
            }
        }
        return i;
    }
    *//**
     *  类别管理
     * @param cell
     *//*
    public int checkClassify(Cell cell){
        String celltostring = cell.toString();
        int i = 0;
        if (celltostring!=null && !celltostring.equals("")){
            i = zlztClassifyinfoDao.getIdByName(celltostring);
        }
        return i;
    }

    public int checkClassify(Cell cell,int i){
        String celltostring = cell.toString();
        if (celltostring!=null && !celltostring.equals("")){
            i = zlztClassifyinfoDao.getByNameAndParentId(celltostring,i);
        }
        return i;
    }

    *//**
     * 判断上传文件的格式
     *//*
    public static void checkfile(MultipartFile file) throws  IOException{
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
    *//**
     * 判断上传文件的格式并获取workbook对象
     *//*
    public static Workbook getWorkBook(MultipartFile file) throws IOException {
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
    }*/

    /*public static Map<String, PictureData> getmaplist(Workbook workbook) throws IOException {
        Map<String, PictureData> maplist= null;
        Sheet sheet = workbook.getSheetAt(0);
        // 判断用07还是03的方法获取图片
        if (fileName.endsWith(xls)) {
            maplist = getPictures1((HSSFSheet) sheet);
        } else if(fileName.endsWith(xlsx)){
            maplist = getPictures2((XSSFSheet) sheet);
        }
        System.out.println(maplist);
        return maplist;
    }

    *//**
     * 获取图片和位置 (xls)
     * @param sheet
     * @return
     * @throws IOException
     *//*
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

    *//**
     * 获取图片和位置 (xlsx)
     * @param //sheet
     * @return
     * @throws IOException
     *//*
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
    }*/
    /*//图片写出
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
    }*/

    @Override
    public List<ZlztDatainfo> getAllcount(ZlztDatainfo zlztDatainfo,String fromTable) {
        List<ZlztDatainfo> list = Lists.newArrayList();
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put("country","国家");
        map.put("applyDay","申请年");
        map.put("openDay","公开年");
        map.put("applyMan","申请人");
        map.put("createMan","发明人");
        map.put("nowLawS","当前法律状态");
        map.put("zlType","专利类型");
        map.put("ipc","IPC分类");
        map.put("cpec","国名经济");
        int type = zlztDatainfo.getType();
        int id = zlztDatainfo.getClassifyId();
        if (type==1){
            zlztDatainfo.setfId(id);
        }else if (type==2){
            zlztDatainfo.setsId(id);
        }else if (type==3){
            zlztDatainfo.settId(id);
        }else if (type==4){
            zlztDatainfo.setcId(id);
        }
        for (Map.Entry<String, String> entry : map.entrySet()){
            ZlztDatainfo zlztDatainfo1 = new ZlztDatainfo();
            zlztDatainfo1.setName(entry.getValue());
            zlztDatainfo1.setKeyname(entry.getKey());
//            if (entry.getKey() =="applyDay"){
//
//            }
            zlztDatainfo.setName(entry.getValue());
            zlztDatainfo.setKeyname(entry.getKey());
            zlztDatainfo.setFromTable(fromTable);
            List<ZlztDatainfo> zlztDatainfoList = zlztDatainfoDao.getChildren(zlztDatainfo);
            if (zlztDatainfoList.size()>0 && zlztDatainfoList.get(0).getKeyname()!=null){
                if(entry.getKey() == "createMan" || entry.getKey() == "nowLawS"  ){
                    //切割并统计
                    zlztDatainfoList = getlistinfo(zlztDatainfoList);
                    //排序
                    zlztDatainfoList = sortByCount(zlztDatainfoList);
                }else if(entry.getKey() == "ipc"){
                    // ipc数据处理
                    zlztDatainfoList = getlistinfo1(zlztDatainfoList);
                    zlztDatainfoList = sortByCount(zlztDatainfoList);
                }else if(entry.getKey() == "applyMan" || entry.getKey() == "cpec" ){
                    //清理空数据
                    zlztDatainfoList = clearnull(zlztDatainfoList);
                    //进行排序
                    zlztDatainfoList = sortByCount(zlztDatainfoList);
                }
            }else {
                zlztDatainfoList = Lists.newArrayList();
            }
            zlztDatainfo1.setChildren(zlztDatainfoList);
            list.add(zlztDatainfo1);
        }
        return list;
    }

    //清楚空数据
    public List<ZlztDatainfo> clearnull(List<ZlztDatainfo> zlztDatainfoList){
        List<ZlztDatainfo> zlztDatainfos = Lists.newArrayList();
        for (int i=0;i<zlztDatainfoList.size();i++) {
            ZlztDatainfo zlztDatainfo1 = zlztDatainfoList.get(i);
            if (zlztDatainfo1.getKeyname()!=null && !zlztDatainfo1.getKeyname().equals("")){
                zlztDatainfos.add(zlztDatainfo1);
            }
        }
        return zlztDatainfos;
    }
    //数据排序处理
    public List<ZlztDatainfo> sortByCount(List<ZlztDatainfo> zlztDatainfoList){
        for (int i=0;i<zlztDatainfoList.size();i++) {
            for (int j = i + 1; j < zlztDatainfoList.size(); j++) {
                ZlztDatainfo zlztDatainfo1 = zlztDatainfoList.get(i);
                int number = zlztDatainfo1.getCount();
                ZlztDatainfo zlztDatainfo2 = null;
                ZlztDatainfo zlztDatainfo3 = zlztDatainfoList.get(j);
                if(zlztDatainfo1.getKeyname()!=null && ! zlztDatainfo1.getKeyname().equals("") && zlztDatainfo3.getKeyname()!=null && ! zlztDatainfo3.getKeyname().equals("")){
                    int number1 = zlztDatainfo3.getCount();
                    if (number < number1) {
                        zlztDatainfo2 = zlztDatainfo1;
                        zlztDatainfoList.set(i, zlztDatainfoList.get(j));
                        zlztDatainfoList.set(j, zlztDatainfo2);
                    }
                }

            }
        }
        return zlztDatainfoList;
    }
    // createMan数据处理
    public List<ZlztDatainfo> getlistinfo(List<ZlztDatainfo> zlztDatainfoList){
        List<ZlztDatainfo> zlztDatainfos = Lists.newArrayList();
        Map<String,Integer> classmap = new HashMap<String,Integer>();
        for (int i =0;i<zlztDatainfoList.size();i++){
            ZlztDatainfo zlztDatainfo1 = zlztDatainfoList.get(i);
            if (zlztDatainfo1.getKeyname()!="" && !zlztDatainfo1.getKeyname().equals("")){
                //取出字符串中的空" ";
                zlztDatainfo1.setKeyname(zlztDatainfo1.getKeyname().replace(" ",""));
                //判断是否是多条用分号判断
                if (zlztDatainfo1.getKeyname().indexOf(";")!=-1 || zlztDatainfo1.getKeyname().indexOf("|")!=-1 ){
                    String[] arr = null;
                    if (zlztDatainfo1.getKeyname().indexOf(";")!=-1){
                         arr = zlztDatainfo1.getKeyname().split(";");
                    }else if (zlztDatainfo1.getKeyname().indexOf("|")!=-1 ){
                        arr = zlztDatainfo1.getKeyname().split("\\|");
                    }
                    if (arr!=null){
                        //数组接收字符串的切割
                        for (int j=0;j<arr.length;j++){
                            //初始的map是否为空
                            if (classmap.size()!=0){
                                //遍历map
                                for (Map.Entry<String,Integer> mapclass : classmap.entrySet()){
                                    //找到相同的+1
                                    if (mapclass.getKey().equals(arr[j])){
                                        classmap.put(mapclass.getKey(),mapclass.getValue()+1);
                                        break;
                                        //未找到相同的就添加
                                    }else if (!classmap.containsKey(arr[j])){
                                        classmap.put(arr[j],zlztDatainfo1.getCount());
                                        break;
                                    }
                                }
                                //map为空就直接添加数据
                            }else {
                                classmap.put(arr[j],zlztDatainfo1.getCount());
                            }
                        }
                    }
                    //如果不包含分号就代表只有一条.
                }else {
                    if (classmap.size()!=0) {
                        for (Map.Entry<String, Integer> mapclass : classmap.entrySet()) {
                            if (mapclass.getKey().equals(zlztDatainfo1.getKeyname())) {
                                classmap.put(mapclass.getKey(), mapclass.getValue() + zlztDatainfo1.getCount());
                                break;
                            } else if (!classmap.containsKey(zlztDatainfo1.getKeyname())){
                                classmap.put(zlztDatainfo1.getKeyname(), zlztDatainfo1.getCount());
                                break;
                            }
                        }
                    }else {
                        classmap.put(zlztDatainfo1.getKeyname(),zlztDatainfo1.getCount());
                    }
                }
            }
        }
        //map 对应 javabean的赋值。
        for (Map.Entry<String,Integer> mapclass : classmap.entrySet()){
            ZlztDatainfo zlztDatainfo1 = new ZlztDatainfo();
            zlztDatainfo1.setKeyname(mapclass.getKey());
            zlztDatainfo1.setCount(mapclass.getValue());
            zlztDatainfos.add(zlztDatainfo1);
        }
        return zlztDatainfos;
    }
    // ipc数据处理
    public List<ZlztDatainfo> getlistinfo1(List<ZlztDatainfo> zlztDatainfoList){
        List<ZlztDatainfo> zlztDatainfos = Lists.newArrayList();
        Map<String,Integer> classmap = new HashMap<String,Integer>();
        for (int i =0;i<zlztDatainfoList.size();i++){
            ZlztDatainfo zlztDatainfo1 = zlztDatainfoList.get(i);
            //取出字符串中的空" ";
            if (zlztDatainfo1.getKeyname()!=null && zlztDatainfo1.getKeyname()!="" &&  !zlztDatainfo1.getKeyname().equals("")){
                zlztDatainfo1.setKeyname(zlztDatainfo1.getKeyname().replace(" ",""));
                //判断是否是多条用分号判断
                if (zlztDatainfo1.getKeyname().indexOf(";")!=-1){
                    //数组接收字符串的切割
                    String arr[] = zlztDatainfo1.getKeyname().split(";");
                    for (int j=0;j<arr.length;j++){
                        String arrString = arr[j].substring(0,4);
                        //初始的map是否为空
                        if (classmap.size()!=0){
                            //遍历map
                            for (Map.Entry<String,Integer> mapclass : classmap.entrySet()){
                                //找到相同的+1
                                if (mapclass.getKey().equals(arrString)){
                                    classmap.put(mapclass.getKey(),mapclass.getValue()+zlztDatainfo1.getCount());
                                    break;
                                    //未找到相同的就添加
                                }else if (!classmap.containsKey(arrString)){
                                    classmap.put(arrString,zlztDatainfo1.getCount());
                                    break;
                                }
                            }
                            //map为空就直接添加数据
                        }else {
                            classmap.put(arrString,zlztDatainfo1.getCount());
                        }
                    }
                    //如果不包含分号就代表只有一条.
                }else {
                    String arrString1 = zlztDatainfo1.getKeyname().substring(0,4);
                    if (classmap.size()!=0) {
                        for (Map.Entry<String, Integer> mapclass : classmap.entrySet()) {
                            if (mapclass.getKey().equals(arrString1)) {
                                classmap.put(mapclass.getKey(), mapclass.getValue() + zlztDatainfo1.getCount());
                                break;
                            } else if (!classmap.containsKey(arrString1)){
                                classmap.put(zlztDatainfo1.getKeyname().substring(0,4), zlztDatainfo1.getCount());
                                break;
                            }
                        }
                    }else {
                        classmap.put(arrString1,zlztDatainfo1.getCount());
                    }
                }
            }
        }
        //map 对应 javabean的赋值。
        for (Map.Entry<String,Integer> mapclass : classmap.entrySet()){
            ZlztDatainfo zlztDatainfo1 = new ZlztDatainfo();
            zlztDatainfo1.setKeyname(mapclass.getKey());
            zlztDatainfo1.setCount(mapclass.getValue());
            zlztDatainfos.add(zlztDatainfo1);
        }
        return zlztDatainfos;
    }
}
