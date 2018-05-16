package com.rosydawn.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 2018-05-11.
 * 典型的 CSV 文件有以下特征：
 * 纯文本，使用某个字符集，比如ASCII、Unicode、EBCDIC或GB2312；
 * 由记录（record）组成（典型的是每行一条记录）；
 * 每条记录被分隔符分隔为字段（典型分隔符有逗号、分号或制表符；有时分隔符可以包括可选的空格）；
 * 每条记录都有同样的字段序列。
 * <p>
 * CSV 文件规则如下：
 * 开头是不留空，以行为单位。
 * 可含或不含列名，含列名则居文件第一行。
 * 一行数据不跨行，无空行。
 * 以半角逗号（即,）作分隔符，列为空也要表达其存在。
 * 列内容如存在半角引号（即"），替换成半角双引号（""）转义，即用半角引号（即""）将该字段值包含起来。
 * 文件读写时引号，逗号操作规则互逆。
 * 内码格式不限，可为 ASCII、Unicode 或者其他。
 * 不支持数字。
 * 不支持特殊字符。
 */
public class CSVUtils {
    /**
     * 从指定的CSV文件中获取记录集合。
     *
     * @param csvFilePath 要获取数据的CSV文件。
     * @return 获得的记录集合。如果没有获得记录，则对应的集合大小为0。
     */
    public static List<CSVRecord> getRecordList(String csvFilePath) {
        List<CSVRecord> recordList = null;
        try {
            Reader fileReader = new FileReader(CSVUtils.class.getClass().getResource(csvFilePath).getFile());
            /* 使用CSVFormat指定CSV文件的处理格式
            - DEFAULT：Standard comma separated format, as for RFC4180 but allowing empty lines.
            - EXCEL：Excel file format (using a comma as the value delimiter).
            - MYSQL：Default MySQL format used by the SELECT INTO OUTFILE and LOAD DATA INFILE operations.
            - RFC4180：Comma separated format as defined by RFC 4180.
            - TDF：Tab-delimited format.
            - INFORMIX_UNLOAD：Default Informix CSV UNLOAD format used by the UNLOAD TO file_name operation.
            - INFORMIX_UNLOAD_CSV：Default Informix CSV UNLOAD format used by the UNLOAD TO file_name operation
            (escaping is disabled.)
            */
            // 可以使用withXxx系列方法扩展指定的格式
            CSVParser parser = CSVFormat.DEFAULT
                    // 以“N/A”表示空单元格
                    // .withNullString("N/A")
                    // 忽略字段之间多余的空格
                    // .withIgnoreSurroundingSpaces(true)
                    // 为记录的各列指定Header
                    // .withHeader("Name", "Gender", "Phone", "Address")
                    // 设置Header之后相当于在CSV最上方添加了一行Header的记录，可以忽略该Header行。
                    // 然后可以像操作数据库表一样通过CSVRecord对象的get(headerName)方法获取对应的单元值
                    // .withSkipHeaderRecord(true)
                    .parse(fileReader);
            recordList = parser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordList;
    }

    /**
     * 从指定的CSV文件中获取特定条件的记录集合。
     *
     * @param csvFilePath 要获取数据的CSV文件。
     * @param column      要筛选的索引值（从0开始）。
     * @param headReg     指定索引值满足的正则表达式。
     * @return 获得的记录集合。如果没有获得记录，则对应的集合大小为0。
     */
    public static List<CSVRecord> getRecordList(String csvFilePath, int column, String headReg) {
        List<CSVRecord> recordList = null;
        List<CSVRecord> sourceRecordList = getRecordList(csvFilePath);
        if (sourceRecordList != null) {
            recordList = new ArrayList<CSVRecord>();
            for (CSVRecord csvRecord : sourceRecordList) {
                if (csvRecord.get(column).matches(headReg)) {
                    recordList.add(csvRecord);
                }
            }
        }
        return recordList;
    }

    /**
     * 将记录写入指定的CSV文件中，返回写入的记录数。
     *
     * @param targetCSVFilePath 目标CSV文件路径名。
     * @param recordList        要写入的记录集合。
     * @return 写入的记录数，写入失败返回null。
     */
    public static Long writeRecord2SCVFile(String targetCSVFilePath, List<CSVRecord> recordList) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;

        try {
            fileWriter = new FileWriter(targetCSVFilePath);
            csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            csvPrinter.printRecords(recordList);
            return (long) recordList.size();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvPrinter != null) {
                try {
                    csvPrinter.flush();
                    csvPrinter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
