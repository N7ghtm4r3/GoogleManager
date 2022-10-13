package com.tecknobit.googlemanager.gmail;

import com.google.api.services.gmail.Gmail;
import com.tecknobit.googlemanager.GoogleManager;

import java.io.IOException;

/**
 * The {@code GmailManager} class is useful to manage Gmail's API service
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest">Gmail API</a>
 **/
public class GmailManager extends GoogleManager {

    public static final String APPLICATION_ATOM_XML_MIME_TYPE = "application/atom+xml";
    public static final String APPLICATION_FORM_URLENCODED_MIME_TYPE = "application/x-www-form-urlencoded";
    public static final String APPLICATION_JSON_MIME_TYPE = "application/json";
    public static final String APPLICATION_OCTET_STREAM_MIME_TYPE = "application/octet-stream";
    public static final String APPLICATION_SVG_XML_MIME_TYPE = "application/svg+xml";
    public static final String APPLICATION_XHTML_XML_MIME_TYPE = "application/xhtml+xml";
    public static final String APPLICATION_XML_MIME_TYPE = "application/xml";
    public static final String MEDIA_TYPE_WILDCARD_MIME_TYPE = "*";
    public static final String MULTIPART_FORM_DATA_MIME_TYPE = "multipart/form-data";
    public static final String TEXT_HTML_MIME_TYPE = "text/html";
    public static final String TEXT_PLAIN_MIME_TYPE = "text/plain";
    public static final String TEXT_XML_MIME_TYPE = "text/xml";
    public static final String WILDCARD_MIME_TYPE = "*/*";
    public static final String MINIMAL_FORMAT = "minimal";
    public static final String FULL_FORMAT = "full";
    public static final String RAW_FORMAT = "raw";
    public static final String METADATA_FORMAT = "metadata";

    /**
     * {@code gmail} is the instance for {@link Gmail}'s service
     **/
    protected final Gmail gmail;

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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName).build();
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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType,
                        String approvalPrompt, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName).build();
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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName).build();
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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName).build();
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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName).build();
    }

}
