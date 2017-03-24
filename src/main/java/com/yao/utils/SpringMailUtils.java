package com.yao.utils;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送HTML类型邮件工具类
 * 
 * @author yaoyuxiao
 * @createDate 2016年8月2日 下午6:03:09
 */
public class SpringMailUtils {
	
	private static final Logger logger=LoggerFactory.getLogger(SpringMailUtils.class);
	
	/**
	 * 发送电子邮件工具类
	 * @param toMailUrl 对方邮箱
	 * @param username 注册的用户名
	 * @param activeCode 生产的激活码
	 * @return
	 * @throws MessagingException
	 */
	public static String sendMail(String toMailUrl,String username,String activeCode) throws MessagingException {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
		  
        // 设定mail server  
        senderImpl.setHost("smtp.163.com");  
  
        // 建立邮件消息,发送简单邮件和html邮件的区别  
        MimeMessage mailMessage = senderImpl.createMimeMessage();  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true, "utf-8");  

        // 设置收件人，寄件人  
        messageHelper.setTo(toMailUrl);  
        messageHelper.setFrom("yaoyuxiao1992@163.com");  
        messageHelper.setSubject("欢迎注册OurDemo");  
        // true 表示启动HTML格式的邮件  
        messageHelper  
                .setText("<html><head></head><body><pre>亲爱的用户"+username+"：您好！"+
                		"<br/>感谢您注册ourdemo，您只需要点击下面链接，激活您的帐户，您便可以享受ourdemo各项服务。"+
                		"<br/><a href='"+ConfUtils.getProjectUrl()+"/register.html?username="+username+"&amp;activeCode="+activeCode+"'>"+
                		ConfUtils.getProjectUrl()+"/register.html?username="+username+"&amp;activeCode="+activeCode+"</a>"+
                		"<br/>(如果无法点击该URL链接地址，请将它复制并粘帖到浏览器的地址输入框，然后单击回车即可。该链接使用后将立即失效。)"+
                		"<br/>注意:重复发送激活码，则历史激活码失效。请您在收到邮件24小时(2016-08-03 10:43:04前)进行激活，否则该激活码将会失效。7天后您的帐户将会失效。"+
                		"<br/>重新发送激活码："+ConfUtils.getProjectUrl()+"/toregister.html?action=resendActiveEmail&amp;username="+username+
                		"</pre></body></html>",  
                        true);  
  
        senderImpl.setUsername("yaoyuxiao1992@163.com"); // 根据自己的情况,设置username  
        senderImpl.setPassword("113210yy"); // 根据自己的情况, 设置password  
        senderImpl.setPort(25);
        Properties prop = new Properties();  
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
        prop.put("mail.smtp.timeout", "5000"); 
        prop.setProperty("mail.smtp.host", "smtp.163.com");
        prop.setProperty("mail.smtp.port", "25");
        senderImpl.setJavaMailProperties(prop);  
        // 发送邮件  
        senderImpl.send(mailMessage);  
  
        logger.info("邮件发送成功..");  
		return "邮件发送成功..";
	}
	public static void main(String[] args) throws MessagingException {
		sendMail("531416635@qq.com","usernameio","activecodenn");
	}
}