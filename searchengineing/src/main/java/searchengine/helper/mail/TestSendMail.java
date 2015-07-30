package searchengine.helper.mail;

public class TestSendMail {
	public static void main(String[] args){  
        //这个类主要是设置邮件  
     MailInfo mailInfo = new MailInfo();   
     mailInfo.setMailServerHost("smtp.163.com");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("baseon_life@163.com");   
     mailInfo.setPassword("BaseOn_Story");//您的邮箱密码   
     mailInfo.setFromAddress("baseon_life@163.com");   
     mailInfo.setToAddress("923996152@qq.com");   
     mailInfo.setSubject("fpb");   
     mailInfo.setContent("通知：关于“2015届高校毕业生求职一次性补助”通知已上传至群文件。发放对象：有就业意愿并积极求职的城乡低保家庭高校毕业生和残疾高校毕业生。补贴标准北京地区高校毕业生一次性求职补贴标准为1000元/人。请同学们在假期期间准备好材料，于3月5日前交至1704");   
        //这个类主要来发送邮件  
     SimpleMailSender sms = new SimpleMailSender();  
         sms.sendTextMail(mailInfo);//发送文体格式   
        // sms.sendHtmlMail(mailInfo);//发送html格式  
   } 

}
