package com.fms.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author gauti
 *
 */
public interface EmailService {

	public void sendEmail(SimpleMailMessage email);

}
