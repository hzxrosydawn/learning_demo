package com.rosydawn.test;

import com.rosydawn.model.StudentModel;
import com.rosydawn.utils.CSVUtils;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

/**
 * CSVUtils的测试类
 *
 * @author Vincent
 * Created on 2018/05/15 9:21 PM
 **/
public class CSVUtilsTest {
    public static void main(String[] args) {
        // 测试从CSV文件读取记录
        List<CSVRecord> recordList = CSVUtils.getRecordList("/source.csv");
        System.out.println("读取所有的记录");
        for (CSVRecord csvRecord : recordList) {
            String name = csvRecord.get(0);
            String gender = csvRecord.get(1);
            String phone = csvRecord.get(2);
            String address = csvRecord.get(3);
            StudentModel studentModel = new StudentModel();
            studentModel.setName(name);
            studentModel.setGender(gender);
            studentModel.setPhone(phone);
            studentModel.setAddress(address);
            System.out.println(studentModel);
        }

        // 测试从CSV文件读取特定的记录
        recordList = CSVUtils.getRecordList("/source.csv", 1, "female");
        System.out.println("读取特定的记录");
        for (CSVRecord csvRecord : recordList) {
            String name = csvRecord.get(0);
            String gender = csvRecord.get(1);
            String phone = csvRecord.get(2);
            String address = csvRecord.get(3);
            StudentModel studentModel = new StudentModel();
            studentModel.setName(name);
            studentModel.setGender(gender);
            studentModel.setPhone(phone);
            studentModel.setAddress(address);
            System.out.println(studentModel);
        }

        // 测试将记录列表写入CSV文件
        System.out.println("在项目根目录创建CSV文件，并写入某些的记录");
        Long writeNumber = CSVUtils.writeRecord2SCVFile("target.csv", recordList);
        if (writeNumber != null) {
            System.out.println("将" + recordList.size() + "个记录写入CSV文件成功");
        } else {
            System.out.println("将" + recordList.size() + "个记录写入CSV文件失败");
        }
    }
}
