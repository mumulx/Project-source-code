package com.mumulx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@SpringBootTest
class SpringbootTaskApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    //简单邮件
    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");
        message.setTo("17512080612@163.com");
        message.setFrom("534096094@qq.com");

        mailSender.send(message);
    }

    @Test
    public void test02() throws Exception {
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会</b>", true);

        helper.setTo("17512080612@163.com");
        helper.setFrom("534096094@qq.com");

        //上传文件
        //helper.addAttachment("1.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\1.jpg"));
        //helper.addAttachment("2.jpg",new File("C:\\Users\\lfy\\Pictures\\Saved Pictures\\2.jpg"));

        mailSender.send(mimeMessage);

    }
}
