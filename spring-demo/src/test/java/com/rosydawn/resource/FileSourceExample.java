package com.rosydawn.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSourceExample {
    public static void main(String[] args) {
        try {
            // 向文件系统中的空文件写入数据
            String filePath = "D:\\IDEA_Projects\\learning_demo\\spring-demo\\src\\test\\resources\\conf\\file1.txt";
            WritableResource res1 = new PathResource(filePath);
            OutputStream stream1 = res1.getOutputStream();
            stream1.write("欢迎光临\n小春论坛".getBytes());
            stream1.close();

            res1.getOutputStream();

            System.out.println("写入文件系统中的内容为:" + baos.toString());
            System.out.println("写入文件系统中的内容为:" + res1.getFilename());


            // 从类路径中读取文件数据
            Resource res2 = new ClassPathResource("conf/file1.txt");


            System.out.println("res2:" + res2.getFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
