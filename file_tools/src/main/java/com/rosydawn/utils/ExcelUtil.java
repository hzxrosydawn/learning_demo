package com.rosydawn.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author Vincent
 */
public class ExcelUtil {

    public static HSSFWorkbook readHSSFWorkbook(InputStream inputStream) throws Exception {
        return new HSSFWorkbook(inputStream);
    }

    public static HSSFWorkbook readHSSFWorkbook(String filePath) throws Exception {
        return new HSSFWorkbook(new FileInputStream(filePath));
    }

    public static XSSFWorkbook readXSSFWorkbook(InputStream inputStream) throws Exception {
        return new XSSFWorkbook(inputStream);
    }

    public static XSSFWorkbook readXSSFWorkbook(String filePath) throws Exception {
        return new XSSFWorkbook(new File(filePath));
    }

    public static String getCellValueAsString(XSSFCell cell) throws Exception {
        DecimalFormat df = new DecimalFormat("#");
        String cellValue = null;
        if (cell == null) {
            throw new RuntimeException("单元格对象为null");
        }

        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                    break;
                }
                cellValue = df.format(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = null;
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                cellValue = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                throw new RuntimeException("单元格的值为未知类型");
        }

        if (cellValue != null && cellValue.trim().length() <= 0) {
            System.out.println("单元格值去除空白后为空");
            return null;
        }

        return cellValue;
    }
}