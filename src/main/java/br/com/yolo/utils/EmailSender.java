package br.com.yolo.utils;

import static br.com.yolo.utils.UrlController.URL_DOMAIN;

import java.text.SimpleDateFormat;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.yolo.user.UserEntity;

/**
* @author  Chrisitan Chiconato
* @since   2018-03-15
*/

public class EmailSender {
	
	
	//TODO: MAYBE CHANGE TEXT MSG
	public static void sendEmailForUserConfirmation(UserEntity userEntity) {
		try {
			
			final String linkForVerification = URL_DOMAIN + "/api/public/user/activate/"
					+ userEntity.getToken();

			HtmlEmail email = new HtmlEmail();
			email.setStartTLSRequired(true);
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setStartTLSRequired(true);
			email.setSSLOnConnect(true);
			email.setAuthenticator(new DefaultAuthenticator("USER_TBD", "PASSWORD_TBD"));

			email.setFrom("TBD");
			email.setSubject("Thanks for signing up");
			email.setHtmlMsg(getHtmlMessage(userEntity, linkForVerification));
			email.setTextMsg("Click the link below to active your account: " + linkForVerification);
			email.addTo(userEntity.getUsername());
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	private static String getHtmlMessage(UserEntity userEntity, final String linkForVerification) {
		StringBuilder html = new StringBuilder();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
		html.append("<html><body>");
		html.append("<h1>Thanks for signing up!</h1>");
		html.append("<table rules='all' style='border-color: #666;' cellpadding='10'>");
		html.append("<tr style='background: #eee;'><td><strong>Usuário :</strong> </td><td>"+userEntity.getUsername()+"</td></tr>");
		html.append("<tr><td><strong>Registration data :</strong> </td><td>"+dt.format(userEntity.getRegistrationDate())+"</td></tr>");
		html.append("</table>");
		html.append("<h3>Click the link below to active your account: </h3>");
		html.append("<a href='"+linkForVerification+"'>"+linkForVerification+"</a>");
		html.append("</body></html>");
		return html.toString();
	}
}
