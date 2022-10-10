package com.tecknobit.googlemanager.gmail.drafts;

import com.tecknobit.googlemanager.gmail.GmailManager;

import java.io.IOException;

public class GmailDraftsManager extends GmailManager {

    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port, String host, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
    }

    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
    }

    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
    }

    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
    }

    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, String host, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
    }

}
