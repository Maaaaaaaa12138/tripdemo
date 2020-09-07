package com.tripdemo.tool;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Properties;

public class Tool {
    // 获取密文
    public static String getCrpy(String data) {
        String salt = "强制加盐12138";
        String str = data + salt;
        String res;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(str.getBytes());
            res = new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            res = "error";
        }
        return res;
    }

    /**
     * 发送验证码
     * @param email: 接受者邮箱
     * @param verCode：验证码
     * @return 是否发送成功
     */
    public static boolean sendEmail(String email, String verCode){
        // 服务器地址:
        String smtp = "smtp.126.com";
        // 登录用户名:
        String username = "qunaer901@126.com";
        // 登录口令:
        String password = "WJHRHRHSPZEBOTEP";
        // 连接到SMTP服务器587端口:
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        //props.put("mail.smtp.port", "587"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        // 获取Session实例:
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // 设置debug模式便于调试:
        session.setDebug(false);

        try{
            MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
            message.setFrom(new InternetAddress("qunaer901@126.com"));
        // 设置接收方地址:
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 设置邮件主题:
            message.setSubject("验证码", "UTF-8");
        // 设置邮件正文:
            // 注册
            message.setText(String.format("【去哪儿……】您的验证码是%s，10分钟之内有效", verCode), "UTF-8");
        // 发送:
            Transport.send(message);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
