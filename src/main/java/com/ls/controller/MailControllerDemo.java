package com.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailControllerDemo {
    @Autowired
    JavaMailSenderImpl javaMailSender;
    @RequestMapping("/mail")
    public String sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("邮件主题");
        message.setText("邮件内容");
        message.setTo("1539690306@qq.com");
        message.setFrom("1539690306@qq.com");
        javaMailSender.send(message);
        return "简单邮件发送成功！";
    }
}
