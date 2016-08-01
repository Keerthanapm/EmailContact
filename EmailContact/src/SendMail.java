import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@ManagedBean(name = "sendMail")
@RequestScoped
public class SendMail {

	public void send(List<String> contacts, String mail, String subject,
			String category) {
		final String SMTP_HOST_NAME = "smtp.sendgrid.net";
		final String username = "keerthana.muralidharan";
		final String password = "keerthu#257";

		String msg;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			for (int i = 0; i < contacts.size(); i++) {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("keerthanapm257@gmail.com"));

				InternetAddress[] recipientAddress = new InternetAddress[contacts
						.size()];

				recipientAddress[i] = new InternetAddress(contacts.get(i));

				message.setRecipient(Message.RecipientType.TO,
						recipientAddress[i]);
				message.setSubject(subject);
				msg = mail
						.concat("\n<a href='http://localhost:8080/EmailContact/Unsubscribe.xhtml?emailId="+recipientAddress[i]+"&category="+category+"'>"
								+ "Click here to unsubscribe</a>");
				System.out.println(msg);
				message.setContent(msg, "text/html");
				Transport.send(message);
				System.out.println("Done");
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

