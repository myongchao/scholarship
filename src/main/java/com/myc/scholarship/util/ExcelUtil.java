package com.myc.scholarship.util;


import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.Cell.*;
/**
* FileName: ExcelUtil.java
* 类的详细说明
*
* @author 马勇超
* @date 2019/1/24 10:06
* @version 1.0
*/

public class ExcelUtil {
    /**
     * @description  根据字段名读取Excel表中的内容
     * @author 马勇超
     * @date 2018/11/19 10:10
     * @param sheets
     * @param fields
     * @return List<Map<String,Object>>
     */
    public static List<Map<String,Object>> readFilds(Workbook sheets, List<String> fields){
        Sheet sheet = sheets.getSheetAt(0);
        List<Map<String,Object>> keyValueList = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();

        int fieldLength = fields.size();
        for (int i = 1;i<=lastRowNum;i++) {

            Map<String,Object> map = new HashMap<>();
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            for (int j = 0;j<fieldLength;j++) {
                map.put(fields.get(j),getValue(row.getCell(j)));
            }
            keyValueList.add(map);
        }
        return keyValueList;
    }

    /**
     * 从不同版本的Excel读出workbook
     * @return Workbook
     */
    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if(filename.endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
       return  workbook;
    }

    private static Object getValue(Cell cell) {
        if (cell == null){
            return null;
        }
        Object obj = null;
        NumberFormat nf = NumberFormat.getInstance();
        switch (cell.getCellType()) {
            case CELL_TYPE_BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case CELL_TYPE_ERROR:
                obj = cell.getErrorCellValue();
                break;
            case CELL_TYPE_NUMERIC:
                String numStr =nf.format(cell.getNumericCellValue());
                if(numStr.indexOf(",")>=8){
                    numStr=numStr. replace(",","");
                }
                if(numStr.indexOf('.')>-1){
                    obj=new BigDecimal(numStr);
                }else{
                    obj=new Long(numStr);
                }
                break;
            case CELL_TYPE_STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

    /**
     * @description 解析Excel
     * @author 郭广明
     * @date 2018/11/28 10:33
     * @param sheets      表格
     * @param fieldSize   字段数目
     * @return
    */
    public static List readSheet(Workbook sheets,Integer fieldSize) {
        Sheet sheet = sheets.getSheetAt(0);
        List<List<Object>> objectList = new ArrayList<>();
        for(int i=1; i<=sheet.getLastRowNum(); i++) {
            List<Object> fieldlist = new ArrayList<>();
            Row row = sheet.getRow(i);
            if(null == row) {
                continue;
            }
            for(int j=0; j<fieldSize; j++) {
                Cell cell = row.getCell(j);
                if(cell == null) {
                    fieldlist.add(null);
                    continue;
                }
                NumberFormat nf = NumberFormat.getInstance();
                switch (cell.getCellType()) {
                    case CELL_TYPE_BOOLEAN:
                        fieldlist.add(cell.getBooleanCellValue());
                        break;
                    case CELL_TYPE_ERROR:
                        fieldlist.add(cell.getErrorCellValue());
                        break;
                    case CELL_TYPE_NUMERIC:
                        if(HSSFDateUtil.isCellDateFormatted(cell)) {
                            fieldlist.add(cell.getDateCellValue());
                            break;
                        }
                        String numStr =nf.format(cell.getNumericCellValue());
                        if(numStr.indexOf(",")!=-1){
                            numStr=numStr. replace(",","");
                        }
                        fieldlist.add(numStr);
                        break;
                    case CELL_TYPE_STRING:
                        fieldlist.add(cell.getStringCellValue());
                        break;
                }
            }
            objectList.add(fieldlist);
        }
        return objectList;
    }
}
