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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName)
                .build().users();
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
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName)
                .build().users();
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
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName)
                .build().users();
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
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName)
                .build().users();
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
        gmail = new Gmail.Builder(netHttpTransport, gsonFactory, credential).setApplicationName(applicationName)
                .build().users();
    }

}
