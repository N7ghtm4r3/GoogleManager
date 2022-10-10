package com.tecknobit.googlemanager;

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
import java.util.List;

import static com.google.api.client.http.ByteArrayContent.fromString;

public abstract class GoogleManager {

    public static final String DEFAULT_CALLBACK_PATH = "/Callback";
    public static final String OFFLINE_ACCESS_TYPE = "offline";
    public static final String FORCE_APPROVAL_PROMPT = "force";
    public static final String ONLINE_ACCESS_TYPE = "online";
    public static final String AUTO_APPROVAL_PROMPT = "auto";
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = -1;
    protected final NetHttpTransport netHttpTransport = new NetHttpTransport();
    protected final GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
    protected GoogleAuthorizationCodeFlow authCodeFlow;
    protected Credential credential;
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
        if (!changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath))
            throw new IOException();
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

    public String sendGetRequest(String endpoint) throws IOException {
        GenericUrl requestUrl = new GenericUrl(endpoint);
        HttpRequest request = netHttpTransport.createRequestFactory(credential).buildGetRequest(requestUrl);
        request.getHeaders().setContentType("application/json");
        return request.execute().parseAsString();
    }

    public String sendPostRequest(String endpoint, String bodyParams) throws IOException {
        GenericUrl requestUrl = new GenericUrl(endpoint);
        if (bodyParams == null)
            bodyParams = "";
        HttpRequest request = netHttpTransport.createRequestFactory(credential).buildPostRequest(requestUrl,
                fromString(null, bodyParams));
        request.getHeaders().setContentType("application/json");
        return request.execute().parseAsString();
    }

    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, int port, String host, String callBackPath) {
        try {
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
                    List.of(GmailScopes.MAIL_GOOGLE_COM))
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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, DEFAULT_PORT, DEFAULT_HOST,
                DEFAULT_CALLBACK_PATH);
    }

    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, int port) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST,
                DEFAULT_CALLBACK_PATH);
    }

    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, int port, String callBackPath) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, callBackPath);
    }

    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, String host, int port) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, DEFAULT_CALLBACK_PATH);
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getApprovalPrompt() {
        return approvalPrompt;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getCallBackPath() {
        return callBackPath;
    }

    public enum ReturnFormat {
        STRING,
        JSON,
        LIBRARY_OBJECT
    }

}
