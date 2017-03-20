package com.fafa.testmail;

import org.apache.log4j.Logger;
import com.fafa.session.MailSession;

public class testmail {
	/**
     * ∑˛ŒÒ” œ‰
     */
    private static MailSession serviceSms = null;
    private static Logger logger = Logger.getLogger(testmail.class);

	public static void main(String[] args) {
		if (serviceSms == null) {
			serviceSms = new MailSession("zwfcode@163.com", "zwf1233");
//			String subject = serviceSms.receiveMail();
//			if (subject.toLowerCase().equals("stop")) {
//				logger.info("Õ£÷π∑¢ÀÕ°£°£°£");
//			}else {
				serviceSms.send("zwfcode@163.com", "JavaMailTest", "This is a JavaMailTest From fafa!");
//			}
		}
	}
    
}
