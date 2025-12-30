package com.salma.Ecommerce.Service;


import com.salma.Ecommerce.Entity.VerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${email.url}")
    private String url;
    @Value("${email.from}")
    private String fromEmail;
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    private SimpleMailMessage simpleMailMessage(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        return simpleMailMessage;
    }
    private void sendEmail(VerificationToken verificationToken){
        SimpleMailMessage simpleMailMessage=simpleMailMessage();
        simpleMailMessage.setTo(verificationToken.getUser().getEmail());
        simpleMailMessage.setSubject("Verification Token");
        simpleMailMessage.setText("Please click on the link to verify your email. \n"
                +url+"/auth/verify?token="+verificationToken.getToken());
        try{
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

}
