package com.tecknobit.googlemanager.managers.gmail.users;

import com.tecknobit.gmailmanager.managers.gmail.users.records.Profile;
import com.tecknobit.googlemanager.managers.GoogleManager;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.googlemanager.constants.EndpointsList.USERS_ENDPOINT;
import static com.tecknobit.googlemanager.managers.GoogleManager.ReturnFormat.LIBRARY_OBJECT;

public class GmailUsersManager extends GoogleManager {

    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             int port, String host, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
    }

    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
    }

    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
    }

    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt, int port, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
    }

    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt, String host, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
    }

    public Profile getProfile() throws IOException {
        return getProfile(LIBRARY_OBJECT);
    }

    public <T> T getProfile(ReturnFormat returnFormat) throws IOException {
        String response = sendRequest(USERS_ENDPOINT + userId + "/profile");
        // TODO: 09/10/2022 CHECK IF CAN BE TRANSFORMED AFTER BUILD A BETA VERSION OF LIBRARY
        switch (returnFormat) {
            case STRING:
                return (T) response;
            case JSON:
                return (T) new JSONObject(response);
            case LIBRARY_OBJECT:
                return (T) new Profile(new JSONObject(response));
        }
        return null;
    }

}
