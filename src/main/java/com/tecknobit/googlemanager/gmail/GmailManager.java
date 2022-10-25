package com.tecknobit.googlemanager.gmail;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.tecknobit.googlemanager.GoogleManager;
import org.apache.commons.codec.binary.Base64;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static javax.mail.Message.RecipientType.*;

/**
 * The {@code GmailManager} class is useful to manage Gmail's API service
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest">Gmail API</a>
 **/
public class GmailManager extends GoogleManager {

    /**
     * {@code APPLICATION_ATOM_XML_MIME_TYPE} is a constant for application/atom+xml mime type
     **/
    public static final String APPLICATION_ATOM_XML_MIME_TYPE = "application/atom+xml";

    /**
     * {@code APPLICATION_FORM_URLENCODED_MIME_TYPE} is a constant for application/x-www-form-urlencoded mime type
     **/
    public static final String APPLICATION_FORM_URLENCODED_MIME_TYPE = "application/x-www-form-urlencoded";

    /**
     * {@code APPLICATION_JSON_MIME_TYPE} is a constant for application/json mime type
     **/
    public static final String APPLICATION_JSON_MIME_TYPE = "application/json";

    /**
     * {@code APPLICATION_OCTET_STREAM_MIME_TYPE} is a constant for application/octet-stream mime type
     **/
    public static final String APPLICATION_OCTET_STREAM_MIME_TYPE = "application/octet-stream";

    /**
     * {@code APPLICATION_SVG_XML_MIME_TYPE} is a constant for application/svg+xml mime type
     **/
    public static final String APPLICATION_SVG_XML_MIME_TYPE = "application/svg+xml";

    /**
     * {@code APPLICATION_XHTML_XML_MIME_TYPE} is a constant for application/xhtml+xml mime type
     **/
    public static final String APPLICATION_XHTML_XML_MIME_TYPE = "application/xhtml+xml";

    /**
     * {@code APPLICATION_XML_MIME_TYPE} is a constant for "application/xml mime type
     **/
    public static final String APPLICATION_XML_MIME_TYPE = "application/xml";

    /**
     * {@code MEDIA_TYPE_WILDCARD_MIME_TYPE} is a constant for * mime type
     **/
    public static final String MEDIA_TYPE_WILDCARD_MIME_TYPE = "*";

    /**
     * {@code MULTIPART_FORM_DATA_MIME_TYPE} is a constant for multipart/form-data mime type
     **/
    public static final String MULTIPART_FORM_DATA_MIME_TYPE = "multipart/form-data";

    /**
     * {@code TEXT_HTML_MIME_TYPE} is a constant for text/html mime type
     **/
    public static final String TEXT_HTML_MIME_TYPE = "text/html";

    /**
     * {@code TEXT_PLAIN_MIME_TYPE} is a constant for text/plain mime type
     **/
    public static final String TEXT_PLAIN_MIME_TYPE = "text/plain";

    /**
     * {@code TEXT_XML_MIME_TYPE} is a constant for text/xml mime type
     **/
    public static final String TEXT_XML_MIME_TYPE = "text/xml";

    /**
     * {@code WILDCARD_MIME_TYPE} is a constant for wildcard mime type
     **/
    public static final String WILDCARD_MIME_TYPE = "*/*";

    /**
     * {@code ResponseFormat} the list of formats available
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/Format">format</a>
     **/
    public enum ResponseFormat {

        /**
         * {@code MINIMAL_FORMAT} is a constant for minimal format type
         **/
        MINIMAL_FORMAT("minimal"),

        /**
         * {@code FULL_FORMAT} is a constant for full format type
         **/
        FULL_FORMAT("full"),

        /**
         * {@code RAW_FORMAT} is a constant for raw format type
         **/
        RAW_FORMAT("raw"),

        /**
         * {@code METADATA_FORMAT} is a constant for metadata format type
         **/
        METADATA_FORMAT("metadata");

        /**
         * {@code format} the format type for the responses
         **/
        private final String format;

        /**
         * Constructor to init a {@link ResponseFormat}
         *
         * @param format: the color represented as hex string
         **/
        ResponseFormat(final String format) {
            this.format = format;
        }

        /**
         * Method to get {@link #format} instance <br>
         * Any params required
         *
         * @return {@link #format} instance as hex {@link String}
         **/
        @Override
        public String toString() {
            return format;
        }

    }

    /**
     * {@code gmail} is the instance for {@link Gmail}'s service
     **/
    protected final Gmail.Users gmail;

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param host:           host used in the auth operations
     * @param callBackPath:   callback path used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, GmailScopes.all());
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credentials).setApplicationName(applicationName)
                .build().users();
        properties.setProperty("application_name", applicationName);
    }

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType,
                        String approvalPrompt, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, GmailScopes.all());
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credentials).setApplicationName(applicationName)
                .build().users();
        properties.setProperty("application_name", applicationName);
    }

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, GmailScopes.all());
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credentials).setApplicationName(applicationName)
                .build().users();
        properties.setProperty("application_name", applicationName);
    }

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param callBackPath:    callback path used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, GmailScopes.all());
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credentials).setApplicationName(applicationName)
                .build().users();
        properties.setProperty("application_name", applicationName);
    }

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param host:            host used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, GmailScopes.all());
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credentials).setApplicationName(applicationName)
                .build().users();
        properties.setProperty("application_name", applicationName);
    }

    /**
     * Constructor to init a {@link GmailManager} <br>
     * Any params required
     *
     * @apiNote this constructor is useful to instantiate a new {@link GmailManager}'s manager without reinsert
     * credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GmailManager firstManager = new GmailManager(CLIENT_ID, CLIENT_SECRET, "email@gmail.com",
     *                 ACCESS_TYPE, APPROVAL_PROMPT, port, "host", "callback_path", "application_name");
     *        //You don't need to insert all credentials to make manager work
     *        GmailManager secondManager = new GmailManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GmailManager() throws IOException {
        super(GmailScopes.all());
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credentials).setApplicationName(properties
                .getProperty("application_name")).build().users();
    }

    /**
     * Method to create a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @return message as {@link Message}
     **/
    protected Message createSimpleMessage(String toEmailAddress, String subject, String messageText) throws Exception {
        return createCompleteMessage(toEmailAddress, subject, null, null, messageText);
    }

    /**
     * Method to create a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @return message as {@link Message}
     **/
    protected Message createCcMessage(String toEmailAddress, String subject, String Cc, String messageText) throws Exception {
        return createCompleteMessage(toEmailAddress, subject, Cc, null, messageText);
    }

    /**
     * Method to create a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param Bcc:            blind carbon copy value
     * @return message as {@link Message}
     **/
    protected Message createBccMessage(String toEmailAddress, String subject, String Bcc,
                                       String messageText) throws Exception {
        return createCompleteMessage(toEmailAddress, subject, null, Bcc, messageText);
    }

    /**
     * Method to create a message
     *
     * @param toEmailAddress : recipient of the message
     * @param subject        :        subject of the message
     * @param Cc             : carbon copy value
     * @param Bcc            : blind carbon copy value
     * @param messageText    : content message
     * @return message as {@link Message}
     **/
    protected Message createCompleteMessage(String toEmailAddress, String subject, String Cc, String Bcc,
                                            String messageText) throws Exception {
        MimeMessage mimeMessage = createMime(toEmailAddress, subject, Cc, Bcc);
        mimeMessage.setText(messageText);
        return createMessage(mimeMessage);
    }

    /**
     * Method to create a message with a file as attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content of the message
     * @param file:           attachment file to sent with message
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createSimpleMessageWithFile(String toEmailAddress, String subject, String messageText, File file,
                                                  String mimeType) throws Exception {
        return createMessage(messageText, file, createMime(toEmailAddress, subject, null, null), mimeType);
    }

    /**
     * Method to create a message with a file as attachment
     *
     * @param toEmailAddress : recipient of the message
     * @param subject        :        subject of the message
     * @param Cc             : carbon copy value
     * @param messageText    :    content of the message
     * @param file           :           attachment file to sent with message
     * @param mimeType       :       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createCcMessageWithFile(String toEmailAddress, String subject, String Cc, String messageText,
                                              File file, String mimeType) throws Exception {
        return createMessage(messageText, file, createMime(toEmailAddress, subject, Cc, null), mimeType);
    }

    /**
     * Method to create a message with a file as attachment
     *
     * @param toEmailAddress : recipient of the message
     * @param subject        :        subject of the message
     * @param Bcc            : blind carbon copy value
     * @param messageText    :    content of the message
     * @param file           :           attachment file to sent with message
     * @param mimeType       :       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createBccMessageWithFile(String toEmailAddress, String subject, String Bcc, String messageText,
                                               File file, String mimeType) throws Exception {
        return createMessage(messageText, file, createMime(toEmailAddress, subject, null, Bcc), mimeType);
    }

    /**
     * Method to create a message with a file as attachment
     *
     * @param toEmailAddress : recipient of the message
     * @param subject        :        subject of the message
     * @param Cc             : carbon copy value
     * @param Bcc            : blind carbon copy value
     * @param messageText    :    content of the message
     * @param file           :           attachment file to sent with message
     * @param mimeType       :       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createCompleteMessageWithFile(String toEmailAddress, String subject, String Cc, String Bcc,
                                                    String messageText, File file, String mimeType) throws Exception {
        return createMessage(messageText, file, createMime(toEmailAddress, subject, Cc, Bcc), mimeType);
    }

    /**
     * Method to create a message with a file as attachment
     *
     * @param messageText: content of the message
     * @param file:        attachment file to sent with message
     * @param mime:        mime message used for the message
     * @param mimeType:    type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createMessage(String messageText, File file, MimeMessage mime, String mimeType) throws Exception {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(messageText, mimeType);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
        mimeBodyPart.setFileName(file.getName());
        multipart.addBodyPart(mimeBodyPart);
        mime.setContent(multipart);
        return createMessage(mime);
    }

    /**
     * Method to create a message with a different files as attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content of the message
     * @param files:          attachments files to sent with message
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createMessageWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                             String mimeType) throws Exception {
        return createMessage(messageText, files, createMime(toEmailAddress, subject, null, null), mimeType);
    }

    /**
     * Method to create a message with a different files as attachments
     *
     * @param toEmailAddress : recipient of the message
     * @param subject        :        subject of the message
     * @param Cc             : carbon copy value
     * @param messageText    :    content of the message
     * @param files          :          attachments files to sent with message
     * @param mimeType       :       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createCcMessageWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                               File[] files, String mimeType) throws Exception {
        return createMessage(messageText, files, createMime(toEmailAddress, subject, Cc, null), mimeType);
    }

    /**
     * Method to create a message with a different files as attachments
     *
     * @param toEmailAddress : recipient of the message
     * @param subject        :        subject of the message
     * @param Bcc            : blind carbon copy value
     * @param messageText    :    content of the message
     * @param files          :          attachments files to sent with message
     * @param mimeType       :       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createBccMessageWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                                File[] files, String mimeType) throws Exception {
        return createMessage(messageText, files, createMime(toEmailAddress, subject, null, Bcc), mimeType);
    }

    /**
     * Method to create a message with a different files as attachments
     *
     * @param toEmailAddress : recipient of the message
     * @param subject        :        subject of the message
     * @param Cc             : carbon copy value
     * @param Bcc            : blind carbon copy value
     * @param messageText    :    content of the message
     * @param files          :          attachments files to sent with message
     * @param mimeType       :       type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createCompletedMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                                      String messageText, File[] files, String mimeType) throws Exception {
        return createMessage(messageText, files, createMime(toEmailAddress, subject, Cc, Bcc), mimeType);
    }

    /**
     * Method to create a message with a different files as attachments
     *
     * @param messageText: content of the message
     * @param files:       attachments files to sent with message
     * @param mime:        mime message used for the message
     * @param mimeType:    type of mime -> constants available at {@link GmailManager}
     * @return message as {@link MimeMessage}
     **/
    protected Message createMessage(String messageText, File[] files, MimeMessage mime, String mimeType) throws Exception {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(messageText, mimeType);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        for (File file : files) {
            mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
            mimeBodyPart.setFileName(file.getName());
            multipart.addBodyPart(mimeBodyPart);
        }
        mime.setContent(multipart);
        return createMessage(mime);
    }

    /**
     * Method to create a message
     *
     * @param messageDetails: message details created with {@link MimeMessage}
     * @return message as {@link Message}
     **/
    protected Message createMessage(MimeMessage messageDetails) throws Exception {
        Message message = new Message();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        messageDetails.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        message.setRaw(encodedEmail);
        return message;
    }

    /**
     * Method to create a {@link MimeMessage} object
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @return mime message as {@link MimeMessage}
     **/
    protected MimeMessage createMime(String toEmailAddress, String subject, String Cc, String Bcc) throws Exception {
        MimeMessage mime = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        mime.setFrom(new InternetAddress(userId));
        mime.addRecipient(TO, new InternetAddress(toEmailAddress));
        mime.setSubject(subject);
        if (Cc != null) {
            if (Cc.contains("["))
                Cc = Cc.replace(" ", "").substring(1, Cc.length() - 2);
            mime.setRecipients(CC, Cc);
        }
        if (Bcc != null) {
            if (Bcc.contains("["))
                Bcc = Bcc.replace(" ", "").substring(1, Bcc.length() - 2);
            mime.setRecipients(BCC, Bcc);
        }
        return mime;
    }

}
