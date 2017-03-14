package com.fafa.testmail;

import org.apache.log4j.Logger;
import com.fafa.session.MailSender;

public class testmail {
	/**
     * 服务邮箱
     */
    private static MailSender serviceSms = null;
    private static Logger logger = Logger.getLogger(testmail.class);
    		
	public static void main(String[] args) {
		if (serviceSms == null) {
			serviceSms = new MailSender("mailAddress", "password");
			serviceSms.send("mailAddress", "JavaMailTest", "This is a JavaMailTest From fafa!");
			logger.info("发送成功");
		}
	}
    
}
