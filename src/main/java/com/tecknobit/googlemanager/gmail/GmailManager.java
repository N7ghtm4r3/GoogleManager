package com.tecknobit.googlemanager.gmail;

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
     * {@code GMAIL_BASE_ENDPOINT} is a constant that indicates base {@code Gmail}'s endpoint to work for the API requests
     **/
    public static final String GMAIL_BASE_ENDPOINT = "https://gmail.googleapis.com";

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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String host, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
    }

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType,
                        String approvalPrompt) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
    }

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
    }

    /**
     * Constructor to init a {@link GmailManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param callBackPath:   callback path used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
    }

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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        String host, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
    }

    /**
     * Method to send a GET request
     *
     * @param endpoint: endpoint to make the request
     * @return response of the request in JSON format as {@link String}
     * @throws IOException when request have been go wrong
     **/
    @Override
    public String sendGetRequest(String endpoint) throws IOException {
        return super.sendGetRequest(GMAIL_BASE_ENDPOINT + endpoint);
    }

    /**
     * Method to send a POST request
     *
     * @param endpoint:   endpoint to make the request
     * @param bodyParams: body payload for the POST request
     * @return response of the request in JSON format as {@link String}
     * @throws IOException when request have been go wrong
     **/
    @Override
    public String sendPostRequest(String endpoint, String bodyParams) throws IOException {
        return super.sendPostRequest(GMAIL_BASE_ENDPOINT + endpoint, bodyParams);
    }

}
