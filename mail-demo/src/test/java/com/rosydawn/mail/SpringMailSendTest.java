package com.rosydawn.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * Spring 发送邮件测试类。与Commons Email类似，Spring 框架的邮件发送功能也依赖于JavaMail API，只是Spring 框架提供了一些
 * 简化邮件操作的工具类，这些工具类位于org.springframework.mail包中。JavaMailSender、SimpleMailMessage 、MailException
 * <p>
 * 参考：https://docs.spring.io/spring/docs/4.3.3.RELEASE/spring-framework-reference/htmlsingle/#mail
 *
 * @author Vincent
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MailConfig.class})
public class SpringMailSendTest {
    private String from = "hzxrosydawn";
    private String to = "hzxrosydawn@gmail.com";
    private String cc = "2498918774@qq.com";

    /**
     * MailSender 接口对象只能发送仅含文本的 SimpleMailMessage 邮件对象，而JavaMailSender 接口对象可用于
     * 发送 MIME 格式的邮件内容。
     */
    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 测试发送正文仅含文本的邮件。
     */
    @Test
    public void testSendTextMail() {
        String subject = "测试文本邮件的主题";
        String body = "测试邮件正文";

        // SimpleMailMessage 表示的邮件只能使用纯文本正文。
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        // 设置邮件主题内容
        message.setSubject(subject);
        // 设置简单的文本正文
        message.setText(body);
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }

    /**
     * 测试使用 Spring 框架的 MimeMessagePreparator 创建 MimeMessage。
     */
    @Test
    public void testMimeMessagePreparator() {
        String subject = "测试文本邮件的主题";
        String body = "测试邮件正文";

        // 使用 MimeMessagePreparator 回调接口来创建原始的 MIME 消息对象。
        // lambda 表达式返回的是一个 MimeMessagePreparator 对象。
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            mimeMessage.setFrom(new InternetAddress(from));
            // 设置一般收件人（数组形式）
            mimeMessage.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
            // 设置抄送人（字符串形式）.也可以仿照以上步骤设置密送人
            mimeMessage.setRecipients(Message.RecipientType.CC, new InternetAddress[]{new InternetAddress(cc)});
            // 设置发送日期（说明邮件客户端看到的日期不一定是实际发送日期，但是邮件服务器上的收件日期是发送者无法更改的）
            mimeMessage.setSentDate(new Date());
            // 设置邮件主题内容（如果主题内容含有非ASCII码的字符，则该方法会对主题内容进行BASE64或Quote-Printable编码）
            mimeMessage.setSubject(subject, "UTF-8");
            // 设置简单的文本正文
            mimeMessage.setText(body);
            // 保存并生成最终的邮件内容
            mimeMessage.saveChanges();
        };
        javaMailSender.send(mimeMessagePreparator);
        System.out.println("邮件发送完毕");
    }

    /**
     * 测试使用 Spring 框架的 MimeMessageHelper 创建 MimeMessage。
     */
    @Test
    public void testMimeMessageHelper() throws Exception {
        String subject = "测试文本邮件的主题";
        // 这里使用html格式的正文，其中图片引用后面添加的 inline 资源。
        String body = "<h3>你好，这个图片好看吗</h3><img src=\"cid:apache_logo.gif\">";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setCc(cc);
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setSubject(subject);
        // mimeMessageHelper.setText(body);
        // 应用html格式的正文。
        mimeMessageHelper.setText(body, true);
        mimeMessageHelper.addAttachment("apache_logo_file_name.gif",
                new FileSystemResource(new File("src/test/resources/Apache_logo.gif")));
        mimeMessageHelper.addAttachment("测试驱动开发实例.pdf",
                new File("src/test/resources/Test-Driven Development By Example.pdf"));
        // inline 资源类型默认通过文件名推断，也可以显式指定
        // mimeMessageHelper.addInline("apache_logo", new File("src/test/resources/Apache_logo.gif"));
        mimeMessageHelper.addInline("apache_logo",
                new FileSystemResource(new File("src/test/resources/Apache_logo.gif")),
                "image/gif");
        javaMailSender.send(mimeMessage);
        System.out.println("邮件发送完毕");
    }

}
