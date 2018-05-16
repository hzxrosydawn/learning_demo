package com.rosydawn.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 读取写入资源文件实例
 */
public class FileSourceExample {
    public static void main(String[] args) {
        try {
            // 在文件系统中创建空文件，并向其中写入数据。filePath表示的file1.txt位于该项目中
            String filePath = "G:\\IDEA_Projects\\learning_demo\\spring-demo\\src\\test\\resources\\conf\\file1.txt";
            WritableResource res1 = new PathResource(filePath);
            OutputStream stream1 = res1.getOutputStream();
            stream1.write("欢迎光临\n小春论坛".getBytes());
            stream1.close();
            System.out.println("创建的文件的名称为:" + res1.getFilename());

            InputStream ins1 = res1.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while((i=ins1.read())!=-1){
                baos.write(i);
            }
            System.out.println("写入文件系统中的内容为:\n" + baos.toString());

            // 从类路径中读取文件数据
            Resource res2 = new ClassPathResource("conf/file1.txt");
            System.out.println("从类路径里获取的文件名：" + res2.getFilename());

            // 以指定的字节码读取资源文件，防止乱码
            Resource res = new ClassPathResource("conf/file1.txt");
            EncodedResource encRes = new EncodedResource(res,"UTF-8");
            String content  = FileCopyUtils.copyToString(encRes.getReader());
            System.out.println("以指定的字节码读取资源文件的内容：\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
