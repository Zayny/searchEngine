package searchengine.helper;

import searchengine.helper.mail.MailInfo;
import searchengine.helper.mail.SimpleMailSender;

public class MailHelper {
	public static boolean sendMail(String mailaddress, String subject, String content) {
		MailInfo mailInfo = getMailInfo();
		mailInfo.setToAddress(mailaddress);
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendTextMail(mailInfo);// 发送文体格式
	}
	public static boolean sendMailHtml(String mailaddress, String subject, String content) {
		MailInfo mailInfo = getMailInfo();
		mailInfo.setToAddress(mailaddress);
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		return SimpleMailSender.sendHtmlMail(mailInfo);
	}

	public static boolean sendMailForException(String content) {
		MailInfo mailInfo = getMailInfo();
		mailInfo.setSubject("Tail用户注册");
		mailInfo.setContent("错误信息如下 : " + content);
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendTextMail(mailInfo);// 发送文体格式
	}

	private static MailInfo getMailInfo() {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailServerHost("smtp.aliyun.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("lewesyang@aliyun.com");
		mailInfo.setPassword("1qaz3edc");// 您的邮箱密码
		mailInfo.setFromAddress("lewesyang@aliyun.com");
		return mailInfo;
	}

	public static void main(String[] args) {
		System.out.println(MailHelper.sendMail("baseon_life@163.com", "", ""));
	}
}
