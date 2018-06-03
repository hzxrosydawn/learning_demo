package com.rosydawn.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * Commons Email发送邮件测试。代码参考：http://commons.apache.org/proper/commons-email/userguide.html
 *
 * @author Vincent
 **/
public class CommonsEmailSendTest {
    private String host = "smtp.163.com";
    private int port = 465;
    private String userName = "hzxrosydawn@163.com";
    private String password = "hzx920821";
    private String to = "hzxrosydawn@gmail.com";
    private String cc = "2498918774@qq.com";

    /**
     * 测试发送仅含纯文本邮件
     */
    @Test
    public void testSendTextEmail() throws Exception {
        String subject = "正文仅含纯文本邮件的主题";

        Email email = new SimpleEmail();
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(userName, password));
        email.setSSLOnConnect(true);
        email.setSubject(subject);

        email.setFrom(userName);
        email.addTo(to);
        email.addCc(cc);

        email.setMsg("This is a test mail ... :-)");

        email.send();
    }

    /**
     * 测试发送正文内嵌图片的邮件
     */
    @Test
    public void testSendHtmlMessage() throws Exception {
        String subject = "测试内嵌图片的邮件的主题";
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(userName, password));
        email.setSSLOnConnect(true);
        email.setSubject(subject);

        email.setFrom(userName);
        email.addTo(to);
        email.addCc(cc);

        // embed the image and get the content id
        String cid = email.embed(new File("src/test/resources/Apache_logo.gif"), "Apache logo");
        // set the html message
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
        // set the alternative message（如果不支持html则显示下面的内容）
        email.setTextMsg("Your email client does not support HTML messages");

        // send the email
        email.send();
    }

    /**
     * 测试发送正文内嵌图片、包含附件的邮件
     */
    @Test
    public void testSendComplexEmail() throws Exception {
        String subject = "测试含有内嵌图片和其他附件的邮件的主题";

        // Create the email message
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(userName, password));
        email.setSSLOnConnect(true);
        email.setSubject(subject);

        email.setFrom(userName);
        email.addTo(to);
        email.addCc(cc);

        // load your HTML email template
        String htmlEmailTemplate = "Apache Logo <br> <img src=\"http://www.apache.org/images/feather.gif\">";
        // define you base URL to resolve relative resource locations
        URL url = new URL("http://www.apache.org");
        email.setDataSourceResolver(new DataSourceUrlResolver(url));

        // set the html message
        email.setHtmlMsg(htmlEmailTemplate);
        // set the alternative message（如果不支持html则显示下面的内容）
        email.setTextMsg("Your email client does not support HTML messages");

        // Create the attachment
        EmailAttachment pdfAttachment = new EmailAttachment();
        pdfAttachment.setPath("src/test/resources/JavaMail-1.5.pdf");
        pdfAttachment.setDisposition(EmailAttachment.ATTACHMENT);
        pdfAttachment.setDescription("JavaMail-1.5规范");
        pdfAttachment.setName("JavaMail-1.5规范.pdf");

        // Create another attachment
        EmailAttachment gifAttachment = new EmailAttachment();
        gifAttachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
        gifAttachment.setDisposition(EmailAttachment.ATTACHMENT);
        gifAttachment.setDescription("Apache的Logo");
        gifAttachment.setName("Apache logo name.gif");

        // add the attachments
        email.attach(pdfAttachment);
        email.attach(gifAttachment);

        // send the email
        email.send();
    }
}
