package com.viewshine.omp.utils;

import com.google.common.base.Strings;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shixj on 2017-04-17.
 */
public class ExcelUtils {
    private static String EXTENSION_XLS = "xls";
    private static String EXTENSION_XLSX = "xlsx";
    private static SimpleDateFormat SDF = new SimpleDateFormat("yy/MM/dd HH:mm:ss");//小写的mm表示的是分钟

    /**
     * excel转换成对象list
     * @param rowStart
     * @param fileExtension
     * @param inputStream
     * @param map
     * @param clas
     * @return
     * @throws IOException
     */
    public static List transferExcelToEntity(int rowStart, String fileExtension, InputStream inputStream, Map<String, Integer> map, Class clas) throws IOException {
        // 获取workbook对象
        Workbook workbook = null;
        List<Object> resList = new ArrayList<>();
        //获取成员变量
        Field[] fields = clas.getDeclaredFields();
        //获取成员变量Map<name,type>
        Map<String, Class> fieldMap = new HashMap();
        for (int i = 0; i < fields.length; i++) {
            fieldMap.put(fields[i].getName(), fields[i].getType());
        }
        try {
            //构造excel数据对象
            if (EXTENSION_XLS.equals(fileExtension.toLowerCase())) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (EXTENSION_XLSX.equals(fileExtension.toLowerCase())) {
                workbook = new XSSFWorkbook(inputStream);
            }
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                int firstRowIndex = sheet.getFirstRowNum();//初始行
                int lastRowIndex = sheet.getLastRowNum();//末行
                //int counter = sheet.getPhysicalNumberOfRows(); //获得总行数
                if (firstRowIndex == lastRowIndex)
                    continue;
                StringBuilder sb = new StringBuilder();//用于拼接方法名
                for (int rowIndex = rowStart; rowIndex <= lastRowIndex; rowIndex++) {
                    Row currentRow = sheet.getRow(rowIndex);// 当前行
                    //空行跳过
                    if (currentRow == null) {
                        continue;
                    }
                    Object entity = clas.newInstance();//存储实体
                    if (Strings.isNullOrEmpty(getCellValue(currentRow.getCell(1), true)))
                        continue;
                    for (String key : map.keySet()) {
                        //首字母大写
                        char[] cs = key.toCharArray();
                        cs[0] -= 32;
                        //获取set方法名
                        String setMethodName = String.valueOf(sb.append("set").append(String.valueOf(cs)));
                        sb.delete(0, sb.length());
                        Class fieldType = fieldMap.get(key);
                        Method setMethod = clas.getMethod(setMethodName.toString(), fieldType);
                        //获取参数类型
                        Object[] args = new Object[1];
                        args[0] = getValue(getCellValue(currentRow.getCell(map.get(key)), true), fieldType);
                        setMethod.invoke(entity, args[0]);
                    }

                    resList.add(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resList;
    }

    /**
     * 取单元格的值
     *
     * @param cell       单元格对象
     * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
     * @return
     */
    private static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }
        String cellValue = "";
        if (treatAsStr) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            DecimalFormat df = new DecimalFormat("0");
            cellValue = String.valueOf(df.format(cell.getNumericCellValue()));
        } else {
            cellValue = String.valueOf(cell.getStringCellValue());
        }
        if (cellValue == null)
            return "";
        else
            return cellValue.trim();
    }

    /**
     * 根据成员变量类型动态转换
     *
     * @param s
     * @param clas
     * @return
     */
    private static Object getValue(String s, Class clas) {
        try {
            if (clas.getName().equals("java.lang.Long")) {
                return Long.valueOf(s);
            }
            if (clas.getName().equals("java.lang.Integer")) {
                return Integer.valueOf(s);
            }
            if (clas.getName().equals("java.lang.Double")) {
                return Double.valueOf(s);
            }
            if (clas.getName().equals("java.lang.Boolean")) {
                return Boolean.valueOf(s);
            }
            if (clas.getName().equals("java.util.Date")) {
                return SDF.parse(s);
            }
            if (clas.getName().equals("java.lang.Byte")) {
                return Byte.valueOf(s);
            } else return Strings.nullToEmpty(s);
        } catch (ParseException p) {
          p.printStackTrace();
          return null;
        }
    }
}
