package com.tecknobit.googlemanager.gmail.users;

import com.tecknobit.apimanager.Manager.APIRequest;
import com.tecknobit.gmailmanager.managers.gmail.users.records.Profile;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.users.records.PushNotificationWatch;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static java.util.Arrays.asList;

public class GmailUsersManager extends GmailManager {

    public static final String USERS_ENDPOINT = "/gmail/v1/users/";

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

    public <T> T getProfile(ReturnFormat format) throws IOException {
        String response = sendGetRequest("profile");
        switch (format) {
            case JSON:
                return (T) new JSONObject(response);
            case LIBRARY_OBJECT:
                return (T) new Profile(new JSONObject(response));
            default:
                return (T) response;
        }
    }

    public boolean stop() {
        try {
            sendPostRequest("stop", null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PushNotificationWatch watch(Collection<String> labelIds, String labelFilterAction,
                                       String topicName) throws IOException {
        return watch(labelIds, labelFilterAction, topicName, LIBRARY_OBJECT);
    }

    public <T> T watch(Collection<String> labelIds, String labelFilterAction, String topicName,
                       ReturnFormat format) throws IOException {
        APIRequest.Params body = new APIRequest.Params();
        body.addParam("labelIds", new JSONArray(labelIds));
        body.addParam("labelFilterAction", labelFilterAction);
        body.addParam("topicName", topicName);
        String response = sendPostRequest("watch", body.createJSONPayload().toString());
        switch (format) {
            case JSON:
                return (T) new JSONObject(response);
            case LIBRARY_OBJECT:
                return (T) new PushNotificationWatch(new JSONObject(response));
            default:
                return (T) response;
        }
    }

    public PushNotificationWatch watch(String[] labelIds, String labelFilterAction, String topicName) throws IOException {
        return watch(labelIds, labelFilterAction, topicName, LIBRARY_OBJECT);
    }

    public <T> T watch(String[] labelIds, String labelFilterAction, String topicName,
                       ReturnFormat format) throws IOException {
        return watch(asList(labelIds), labelFilterAction, topicName, format);
    }

    @Override
    public String sendGetRequest(String endpoint) throws IOException {
        return super.sendGetRequest(USERS_ENDPOINT + userId + "/" + endpoint);
    }

    @Override
    public String sendPostRequest(String endpoint, String requestBody) throws IOException {
        return super.sendPostRequest(USERS_ENDPOINT + userId + "/" + endpoint, requestBody);
    }

}
