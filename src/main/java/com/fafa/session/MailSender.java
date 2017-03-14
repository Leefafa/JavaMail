package com.fafa.session;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import com.fafa.simplemail.MailAuthenticator;

/**
 * ���ʼ����������ɵ�����Ⱥ��
 * @Author Stark
 * @Date 2017��3��14�� ����12:41:50
 * @File MailSender.java
 */
public class MailSender {
	
	/**
	 * �����ʼ���props�ļ�
	 */
	private final transient Properties props = System.getProperties();
	
	/**
	 * �ʼ���������¼��֤
	 */
	private transient MailAuthenticator authenticator;
	
	/**
	 * ����session
	 */
	private transient Session session;

	/**
	 * ��ʼ���ʼ�������
	 * @param smtpHostName	SMTP�ʼ���������ַ
	 * @param username	�����ʼ����û���(��ַ)
	 * @param password	�����ʼ�������
	 */
	public MailSender(final String smtpHostName, final String username, final String password) {
		init(username, password, smtpHostName);
	}
	
	/**
	 * ��ʼ���ʼ�������
	 * @param username	�����ʼ����û���(��ַ)�����Դ˽���SMTP��������ַ
	 * @param password	�����ʼ�������
	 */
	public MailSender(final String username, final String password) {
		//ͨ�������ַ������smtp���������Դ�������䶼����
		final String smtpHostName = "mail." + username.split("@")[1];
		init(username, password, smtpHostName);
	}
	
	/**
	 * ��ʼ��
	 * @param username	�����ʼ����û���(��ַ)
	 * @param password	����
	 * @param smtpHostName	SMTP������ַ
	 */
	private void init(String username, String password, String smtpHostName) {
		// ��ʼ��props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
		//��֤
		authenticator = new MailAuthenticator(username, password);
		//����session
		session = Session.getInstance(props, authenticator);
	}

	/**
	 * �����ʼ�
	 * @param recipient	�ռ��������ַ
	 * @param subject	�ʼ�����
	 * @param content	�ʼ�����
	 */
	public void send(String recipient, String subject, Object content) {
		//����mime�����ʼ�
		final MimeMessage message = new MimeMessage(session);
		try {
			//���÷�����
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			//�����ռ���
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			//��������
			message.setSubject(subject);
			//�����ʼ�����
			message.setContent(content.toString(), "text/html;charset=utf-8");
			//����
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ⱥ���ʼ�
	 * @param recipients
	 * @param subject
	 * @param content
	 */
	public void send(List<String> recipients, String subject, Object content){
		//����mime�����ʼ�
		final MimeMessage message = new MimeMessage(session);
		try {
			//���÷�����
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			//�����ռ�����
			final int num = recipients.size();
			InternetAddress[] addresses = new InternetAddress[num];
			for (int i = 0; i < num; i++) {
				addresses[i] = new InternetAddress(recipients.get(i));
			}
			message.setRecipients(RecipientType.TO, addresses);
			//��������
			message.setSubject(subject);
			//�����ʼ�����
			message.setContent(content.toString(), "text/html;charset=utf-8");
			//����
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	public void send(String recipient, SimpleMail mail) throws AddressException, MessagingException {
		send(recipient, mail.getSubject(), mail.getContent());
	}
	
	public void send(List<String> recipients, SimpleMail mail) throws AddressException, MessagingException {
		send(recipients, mail.getSubject(), mail.getContent());
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
