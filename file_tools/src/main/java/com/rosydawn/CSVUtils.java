package com.rosydawn;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Vincent on 2018-05-11.
 */
public class CSVUtils {

    public static void main(String[] args) {
        String csvFileDir = "D:\\jars\\csv";
        loadCSVData(csvFileDir);
    }


    public static List<String> loadCSVData(String csvFileDir) {
        List<String> audioIdList = new ArrayList<>();
        File csvDir = new File(csvFileDir);
        if (csvDir.isDirectory()) {
            File[] csvFileArray = csvDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".csv");
                }
            });
            for (File csvFilePath : csvFileArray) {
                try {
                    Reader fileReader = new FileReader(csvFilePath);
                    Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(fileReader);
                    for (CSVRecord record : records) {
                        String audioId = record.get(1);
                        audioIdList.add(audioId);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return audioIdList;
    }
}
