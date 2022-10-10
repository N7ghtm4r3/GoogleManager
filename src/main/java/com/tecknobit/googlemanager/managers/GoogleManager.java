package com.tecknobit.googlemanager.managers;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.GmailScopes;

import java.io.IOException;
import java.util.Arrays;

public class GoogleManager {

    public static final String SERVICE_ENDPOINT = "https://gmail.googleapis.com";
    public static final String DEFAULT_CALLBACK_PATH = "/Callback";
    public static final String OFFLINE_ACCESS_TYPE = "offline";
    public static final String FORCE_APPROVAL_PROMPT = "force";
    public static final String ONLINE_ACCESS_TYPE = "online";
    public static final String AUTO_APPROVAL_PROMPT = "auto";
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = -1;
    protected final NetHttpTransport netHttpTransport = new NetHttpTransport();
    protected final GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
    protected final GoogleAuthorizationCodeFlow authCodeFlow;
    protected final Credential credential;
    protected String clientId;
    protected String clientSecret;
    protected String userId;
    protected String accessType;
    protected String approvalPrompt;
    protected int port;
    protected String host;
    protected String callBackPath;

    public GoogleManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                         int port, String host, String callBackPath) throws IOException {
        if (accessType != null && !accessType.equals(ONLINE_ACCESS_TYPE) && !accessType.equals(OFFLINE_ACCESS_TYPE))
            throw new IOException("Access type must be online, offline or null");
        if (approvalPrompt != null && !approvalPrompt.equals(FORCE_APPROVAL_PROMPT) &&
                !approvalPrompt.equals(AUTO_APPROVAL_PROMPT)) {
            throw new IOException("Approval prompt must be force, auto or null");
        }
        if (!callBackPath.contains("/"))
            callBackPath = "/" + callBackPath;
        // TODO: 09/10/2022 CHECK IF CAN BE TRANSFORMED ARRAYS.ASLIST() AFTER BUILD A BETA VERSION OF LIBRARY
        authCodeFlow = new GoogleAuthorizationCodeFlow.Builder(netHttpTransport, gsonFactory, clientId, clientSecret,
                Arrays.asList(GmailScopes.MAIL_GOOGLE_COM))
                .setAccessType(accessType)
                .setApprovalPrompt(approvalPrompt)
                .build();
        credential = new AuthorizationCodeInstalledApp(authCodeFlow, new LocalServerReceiver.Builder()
                .setPort(port).setHost(host).setCallbackPath(callBackPath).build()).authorize(userId);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.userId = userId;
        this.accessType = accessType;
        this.approvalPrompt = approvalPrompt;
        this.port = port;
        this.host = host;
        this.callBackPath = callBackPath;
    }

    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, DEFAULT_PORT, DEFAULT_HOST, DEFAULT_CALLBACK_PATH);
    }

    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt, int port) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, DEFAULT_CALLBACK_PATH);
    }

    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt, int port, String callBackPath) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, callBackPath);
    }

    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt, String host, int port) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, DEFAULT_CALLBACK_PATH);
    }

    public String sendRequest(String endpoint) throws IOException {
        GenericUrl requestUrl = new GenericUrl(SERVICE_ENDPOINT + endpoint);
        HttpRequest request = netHttpTransport.createRequestFactory(credential).buildGetRequest(requestUrl);
        request.getHeaders().setContentType("application/json");
        return request.execute().parseAsString();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getApprovalPrompt() {
        return approvalPrompt;
    }

    public void setApprovalPrompt(String approvalPrompt) {
        this.approvalPrompt = approvalPrompt;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCallBackPath() {
        return callBackPath;
    }

    public void setCallBackPath(String callBackPath) {
        this.callBackPath = callBackPath;
    }

    public enum ReturnFormat {
        STRING,
        JSON,
        LIBRARY_OBJECT
    }

}
