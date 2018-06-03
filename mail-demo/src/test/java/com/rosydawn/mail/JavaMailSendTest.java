package com.rosydawn.mail;

import org.junit.Before;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * JavaMail邮件发送测试。
 *
 * @author Vincent
 **/
public class JavaMailSendTest {
    private MimeMessage mimeMessage;
    private Transport transport;

    @Before
    public void setup() throws Exception {
        String from = "hzxrosydawn@qq.com";
        String to = "hzxrosydawn@gmail.com";
        String cc = "2498918774@qq.com";

        // 创建Session
        Properties properties = new Properties();
        properties.setProperty("mail.transfer.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        // 设置使用SSL加密的属性（QQ邮箱必须使用加密传输）465端口是QQ邮箱的smtp服务的SSL连接端口
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getInstance(properties);

        // 根据Session创建Message
        mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(from);
        mimeMessage.setRecipients(Message.RecipientType.TO, to);
        mimeMessage.setRecipients(Message.RecipientType.CC, cc);

        // 获取Transport对象
        transport = session.getTransport();
    }

    /**
     * 测试发送正文仅含文本的邮件。
     */
    @Test
    public void testSendTextMail() throws Exception {
        String subject = "测试文本邮件的主题";
        String body = "测试邮件正文";

        // 设置邮件主题内容
        mimeMessage.setSubject(subject, "UTF-8");
        // 设置简单的文本正文
        mimeMessage.setText(body);
        // 保存并生成最终的邮件内容
        mimeMessage.saveChanges();

        // 使用Transport对象连接邮件服务器发送邮件
        send(transport);
    }

    /**
     * 测试发送Html正文（内嵌图片）的邮件。
     */
    @Test
    public void testSendHtmlEmail() throws Exception {
        String body = "<h3>你好，这个图片好看吗</h3><img src=\"cid:Apache_logo.gif\">";
        // 创建正文中的html文本
        MimeBodyPart htmlBodyPart = new MimeBodyPart();
        htmlBodyPart.setContent(body, "text/html;charset=gb2312");

        // 创建正文中的图片
        MimeBodyPart pictureBodyPart = new MimeBodyPart();
        pictureBodyPart.setDataHandler(new DataHandler(new FileDataSource("src/test/resources/Apache_logo.gif")));
        pictureBodyPart.setContentID("Apache_logo.gif");
        pictureBodyPart.setFileName("pictureFileName.jpg");

        // 组合邮件正文中的html文本和图片
        MimeMultipart mimeMultipart = new MimeMultipart("related");
        mimeMultipart.addBodyPart(htmlBodyPart);
        mimeMultipart.addBodyPart(pictureBodyPart);

        // 设置邮件内容
        String subject = "测试Html邮件的主题";
        mimeMessage.setSubject(subject, "UTF-8");
        mimeMessage.setContent(mimeMultipart);
        mimeMessage.saveChanges();

        // 使用Transport对象连接邮件服务器发送邮件
        send(transport);
    }

    /**
     * 测试发送正文含有内嵌图片的邮件。
     */
    @Test
    public void testSendPictureEmail() throws Exception {
        String subject = "测试内嵌图片的邮件的主题";
        mimeMessage.setSubject(subject, "UTF-8");

        // 这里的图片地址引用了后面创建的Content-ID
        String body = "<h3>你好，这个图片好看吗</h3><img src=\"cid:Apache_logo.gif\">";

        // 创建一个子类型为“related”的MultipartBody对象，用于组合Mime消息
        MimeMultipart multipart = new MimeMultipart("related");

        // 创建一个表示Html正文的MimeBodyPart对象，并将其加入前面创建的子类型为“related”的MultipartBody对象
        MimeBodyPart htmlBodyPart = new MimeBodyPart();
        htmlBodyPart.setContent(body, "text/html;charset=gb2312");

        // 创建一个表示图片内容的MimeBodyPart对象，并将其加入前面创建的子类型为“related”的MultipartBody对象
        MimeBodyPart pictureBodyPart = new MimeBodyPart();
        // JAF框架在设置文件数据的同时还记录了数据的MIME类型
        pictureBodyPart.setDataHandler(new DataHandler(new FileDataSource("src/test/resources/Apache_logo.gif")));
        pictureBodyPart.setContentID("Apache_logo.gif");
        pictureBodyPart.setFileName("fileName.jpg");

        multipart.addBodyPart(htmlBodyPart);
        multipart.addBodyPart(pictureBodyPart);

        // 将MultipartBody对象设置为整封邮件的内容
        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();

        // 使用Transport对象连接邮件服务器发送邮件
        send(transport);
    }

    @Test
    public void testSendComplexEmail() throws Exception {
        String subject = "测试含有内嵌图片和其他附件的邮件的主题";
        // 设置邮件主题内容（如果主题内容含有非ASCII码的字符，则该方法会对主题内容进行BASE64或Quote-Printable编码）
        mimeMessage.setSubject(subject, "UTF-8");
        // 这里的图片地址引用了后面创建的Content-ID
        String body = "<h3>你好，这个图片好看吗</h3><img src=\"cid:Apache_logo.gif\">";

        // 创建代表邮件正文MimeBodyPart对象
        MimeBodyPart contentPart = createContent(body, "src/test/resources/Apache_logo.gif");
        // 创建代表附件的MimeBodyPart对象
        MimeBodyPart attachmentBodyPart01 =
                createAttachment("src/test/resources/Test-Driven Development By Example.pdf",
                        "测试驱动开发实例.pdf");
        MimeBodyPart attachmentBodyPart02 = createAttachment("src/test/resources/JavaMail-1.5.pdf",
                "JavaMail-1.5.pdf");

        // 创建一个用于组合邮件正文和附件、类型为“mixed”的MultipartBody对象
        MimeMultipart allMultipart = new MimeMultipart("mixed");
        allMultipart.addBodyPart(contentPart);
        allMultipart.addBodyPart(attachmentBodyPart01);
        allMultipart.addBodyPart(attachmentBodyPart02);

        // 设置整个邮件内容为最终组合出的MimeMultipart对象
        mimeMessage.setContent(allMultipart);
        mimeMessage.saveChanges();
        // 将邮件内容写入本地，发现图片的二进制流使用了BASE64编码
        mimeMessage.writeTo(new FileOutputStream("target/complexMail.eml"));

        // 使用Transport对象连接邮件服务器发送邮件
        send(transport);
    }

    /**
     * 使用Transport对象连接邮件服务器发送邮件
     *
     * @param transport 连接邮件服务器发送邮件的Transport对象
     * @throws Exception 创建出现问题时的异常
     */
    private void send(Transport transport) throws Exception {
        String server = "smtp.qq.com";
        String user = "hzxrosydawn";
        String password = "ulwjlxfwryjxdhja";
        // 使用Transport对象连接邮件服务器发送邮件
        transport.connect(server, user, password);
        // 发送给所有收件人（包括to、cc和bcc）
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }

    /**
     * 创建代表正文的MIME消息，其中的内容包含html格式的正文和内嵌的图片。
     *
     * @param body            html格式的正文内容
     * @param contentFilePath 正文内嵌图片的本地路径
     * @return 代表正文的MIME消息
     * @throws Exception 创建出现问题时的异常
     */
    private MimeBodyPart createContent(String body, String contentFilePath) throws Exception {
        // 创建保存MimeMultipart对象的MimeBodyPart对象
        MimeBodyPart contentBodyPart = new MimeBodyPart();

        // 创建代表组合MIME消息的MimeMultipart对象（正文与内嵌图片的关系为related）
        MimeMultipart contentMultiPart = new MimeMultipart("related");

        // 创建代表被组合正文中Html文本的MimeBodyPart对象
        MimeBodyPart htmlBodyPart = new MimeBodyPart();
        htmlBodyPart.setContent(body, "text/html;charset=gb2312");

        // 创建代表被组合正文中内嵌图片的MimeBodyPart对象
        MimeBodyPart pictureBodyPart = new MimeBodyPart();
        pictureBodyPart.setDataHandler(new DataHandler(new FileDataSource(contentFilePath)));
        pictureBodyPart.setContentID("Apache_logo.gif");
        pictureBodyPart.setFileName("fileName.jpg");

        // 组合正文和内嵌图片
        contentMultiPart.addBodyPart(htmlBodyPart);
        contentMultiPart.addBodyPart(pictureBodyPart);
        contentBodyPart.setContent(contentMultiPart);

        return contentBodyPart;
    }

    /**
     * 创建代表附件的MIME消息。
     *
     * @param filePath 附件的本地路径
     * @param fileName 显示在邮件中的附件名称。
     * @return 代表附件的MIME消息
     * @throws Exception 创建出现问题时的异常
     */
    private MimeBodyPart createAttachment(String filePath, String fileName) throws Exception {
        // 创建保存附件的 MimeBodyPart 对象
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        // 添加附件内容和附件的文件名（在邮件中显示为附件的名称）
        attachmentBodyPart.setDataHandler(new DataHandler(new FileDataSource(filePath)));
        attachmentBodyPart.setFileName(fileName);

        return attachmentBodyPart;
    }
}
