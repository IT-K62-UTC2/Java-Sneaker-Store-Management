package utc2.itk62.store.common;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import utc2.itk62.store.models.Invoice;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.Properties;

public class MailConfig {
    public static final String HOST_NAME = Config.properties.getProperty("HOST_NAME");

    public static final int TSL_PORT = Integer.parseInt(Config.properties.getProperty("TSL_PORT")); // Port for TLS/STARTTLS

    public static final String APP_EMAIL = Config.properties.getProperty("APP_EMAIL"); // your email

    public static final String APP_PASSWORD = Config.properties.getProperty("APP_PASSWORD"); // your password

    public static void sendInvoiceToCustomer(String recipientEmail, JasperPrint jasperPrint, Invoice invoice) {
        new Thread(() -> {
            // Cấu hình các thuộc tính cho phiên làm việc (session)
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", HOST_NAME);
            props.put("mail.smtp.port", TSL_PORT);

            // Tạo một phiên làm việc (session)
            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
                        }
                    });

            try {
                // Tạo message
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(APP_EMAIL));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                message.setSubject("Invoice UTC2 Store #"+invoice.getId()+"-"+System.currentTimeMillis());

                // Tạo đối tượng Multipart để chứa các phần của email
                Multipart multipart = new MimeMultipart();

                // Đính kèm tệp PDF vào email
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
                byte[] bytes = baos.toByteArray();
                DataSource source = new ByteArrayDataSource(bytes, "application/pdf");
                BodyPart pdfPart = new MimeBodyPart();
                pdfPart .setDataHandler(new DataHandler(source));
                pdfPart .setFileName("invoice.pdf");
                multipart.addBodyPart(pdfPart);

                // Tạo file tạm thời để xuất JasperPrint ra file HTML
                File tempHtmlFile = File.createTempFile("temp", ".html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint, tempHtmlFile.getAbsolutePath());
                // Đọc nội dung của file HTML vào bộ đệm
                StringBuilder htmlContent = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(tempHtmlFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        htmlContent.append(line);
                    }
                }
                BodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent(htmlContent.toString(), "text/html; charset=utf-8");
                multipart.addBodyPart(htmlPart);
                // Xóa file HTML tạm thời
                tempHtmlFile.delete();


                message.setContent(multipart);
                // Gửi thư điện tử
                Transport.send(message);
                System.out.println("Send mail successfully");
            } catch (JRException | MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
