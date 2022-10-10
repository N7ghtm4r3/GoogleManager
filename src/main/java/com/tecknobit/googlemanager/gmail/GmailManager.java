package com.tecknobit.googlemanager.gmail;

import com.tecknobit.googlemanager.GoogleManager;

import java.io.IOException;

public class GmailManager extends GoogleManager {

    public static final String GMAIL_BASE_ENDPOINT = "https://gmail.googleapis.com";

    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String host, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
    }

    public GmailManager(String clientId, String clientSecret, String userId, String accessType,
                        String approvalPrompt) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
    }

    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
    }

    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        int port, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
    }

    public GmailManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                        String host, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
    }

    @Override
    public String sendGetRequest(String endpoint) throws IOException {
        return super.sendGetRequest(GMAIL_BASE_ENDPOINT + endpoint);
    }

    @Override
    public String sendPostRequest(String endpoint, String requestBody) throws IOException {
        return super.sendPostRequest(GMAIL_BASE_ENDPOINT + endpoint, requestBody);
    }

}
