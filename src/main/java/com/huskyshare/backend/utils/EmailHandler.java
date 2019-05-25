package com.huskyshare.backend.utils;

import com.huskyshare.backend.entity.Code;
import com.huskyshare.backend.service.ValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
@Scope(scopeName = "singleton")
public class EmailHandler {

    @Value("${spring.mail.username}")
    private String emailAccount;   //Email goes here
    @Value(("${spring.mail.password}"))
    private String emailPassword;  //Pwd goes here
    private final static String gmailHost = "smtp.gmail.com";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    ValidService validService;

    /**
     * BUGS!!! unknown error 530
     * @param address
     * @return
     * @throws Exception
     */
    public String sendVCode(String address) throws Exception {
        try{
            SimpleMailMessage message =  new SimpleMailMessage();
            message.setFrom(emailAccount);
            message.setTo(address);
            int code = (int)(Math.random()*10000);
            //redisHandler.store(address.toLowerCase(), code + "");
            Code c = new Code();
            c.setCode(""+code);
            c.setEmail(address);
            validService.save(c);
            message.setSentDate(new Date());
            message.setSubject("Welcome from HuskyShare!!");
            message.setText("Hello husky!\nHere is your code: " + code);
            mailSender.send(message);
        }catch (MailException e){
            e.printStackTrace();
            return "SEND_FAIL";
        }

        return "SEND_SUCCESS";
    }

    public boolean compareCode(String address, String code){ return validService.findCodebyEmail(address.toLowerCase()).equals(code);}

    /**
     *
     * @return
     */
    @Bean
    public EmailHandler getEmailHandler(){ return new EmailHandler();}

    /**
     *
     * @param address
     * @return
     * @throws Exception
     */
    public String sendCode(String address) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", gmailHost);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(emailAccount,emailPassword);
            }
        });
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(emailAccount, "HuskyShare@2019", "UTF-8"));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(address, "USER_CC", "UTF-8"));

        message.setSubject("Signing up for HuskyShare OwO");

        int code = (int)(Math.random()*10000);

        //redisHandler.store(address.toLowerCase(), code + "");
        Code c = new Code();
        c.setCode(""+code);
        c.setEmail(address);
        validService.save(c);

        message.setContent("Hello husky!\nHere is your code: " + code, "text/html;charset=UTF-8");

        message.setSentDate(new Date());

        //message.saveChanges();

        Transport.send(message);

        return "SEND_SUCCESS";
    }

    public void sendVerifiedEmail(String name){
        String content = "Congratulations!\n You can FREELY buy & share in huskyshare.com now! OwO";
    }

}
