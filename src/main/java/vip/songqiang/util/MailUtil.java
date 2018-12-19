package vip.songqiang.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class MailUtil {

    @Autowired
    JavaMailSender mailSender;

    // 服务端口号
    @Value("${server.port}")
    private String port;


    public void sendSimpleEmail(String deliver, String[] receiver, String[] carbonCopy, String subject, String content) throws MessagingException {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(deliver);
            message.setTo(receiver);
            message.setCc(carbonCopy);
            message.setSubject(subject);
            String computerName = System.getenv("COMPUTERNAME");
            content = content + "\n\n\nComputerName:" + computerName + ".ServerPort:" + port + ".";
            message.setText(content);
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw e;
        }
    }
}