package com.rosydawn.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by Vincent on 2018-07-20.
 */
@Configuration
public class MailConfig {
    private String host = "smtp.163.com";
    private int port = 465;
    private String userName = "hzxrosydawn@163.com";
    private String password = "hzx920821";

    /**
     * 配置邮件发送器。
     *
     * @return 初始化之后的邮件发送器。
     */
    @Bean("mailSender")
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);
        return mailSender;
    }

    @Bean("javaMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setPort(465);
        mailSender.setUsername("hzxrosydawn@163.com");
        mailSender.setPassword("hzx920821");
        return mailSender;
    }

}
