package com.rosydawn.test;

import com.rosydawn.utils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilTest {
    public static void main(String[] args) throws Exception {
        // 指定要读取的 Excel 文件路径（可以为 xls 和 xlsx 格式）
        String filePath = "/result.xlsx";
        XSSFWorkbook xssfWorkbook = ExcelUtil.readXSSFWorkbook(filePath);
        int numberOfSheets = xssfWorkbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
            System.out.println("sheetName：" + xssfSheet.getSheetName());
            int lastRowNum = xssfSheet.getLastRowNum();
            for (int j = 0; j < lastRowNum; j++) {
                XSSFRow xssfRow = xssfSheet.getRow(j);
                short lastCellNum = xssfRow.getLastCellNum();
                for (int k = 0; k < lastCellNum; k++) {
                    XSSFCell xssfCell = xssfRow.getCell(k);
                    String cellValue = ExcelUtil.getCellValueAsString(xssfCell);
                    System.out.println("第" + j + "行" + k + "列单元格的值：" + cellValue);
                }
            }
        }
    }
}
