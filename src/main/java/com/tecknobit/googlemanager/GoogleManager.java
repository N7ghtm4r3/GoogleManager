package com.tecknobit.googlemanager;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.GmailScopes;

import java.io.IOException;

import static java.util.Arrays.asList;

// TODO: 10/10/2022 SAVE OR SEE HOW TO STORE CREDS TO AVOID EVERYTIME THE LOGIN SCREEN AND SEE HOW TO REFRESH TOKEN EVENTUALLY SEE FOR A GET ERROR RESPONSE METHOD
// TODO: 12/10/2022 WARN USER ABOUT DEFAULT VALUE RETURNED IF NOT EXIST IN DOCU STRING FOR GETTERS

/**
 * The {@code GoogleManager} class is useful to manage all Google's API services giving basic methods
 * to authenticate a user or a <a href="https://console.cloud.google.com">Google API project</a> and making all needed
 * requests to use the different Google's API services
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://support.google.com/googleapi/answer/7037264?hl=en&ref_topic=7013279">
 * Manage APIs in the API Console</a>
 **/
public abstract class GoogleManager {

    /**
     * {@code DEFAULT_CALLBACK_PATH} is a constant for default callback path used in the auth operations
     * **/
    public static final String DEFAULT_CALLBACK_PATH = "/Callback";

    /**
     * {@code DEFAULT_HOST} is a constant for default host used in the auth operations
     * **/
    public static final String DEFAULT_HOST = "localhost";

    /**
     * {@code DEFAULT_PORT} is a constant for default port used in the auth operations, indicates unused port
     * **/
    public static final int DEFAULT_PORT = -1;

    /**
     * {@code OFFLINE_ACCESS_TYPE} is a constant for offline access type used in the auth operations
     * @apiNote from original Google's documentation,
     * {@code "offline"} is used to request offline access and is the default type for installed applications
     * **/
    public static final String OFFLINE_ACCESS_TYPE = "offline";

    /**
     * {@code ONLINE_ACCESS_TYPE} is a constant for online access type used in the auth operations
     * @apiNote from original Google's documentation,
     * {@code "online"} is used to request online access and is the default type for web applications
     * **/
    public static final String ONLINE_ACCESS_TYPE = "online";

    /**
     * {@code FORCE_APPROVAL_PROMPT} is a constant for force approval prompt type used in the auth operations
     * @apiNote from original Google's documentation,
     * {@code "force"} to force the approval UI to show and is the default for installed applications
     * **/
    public static final String FORCE_APPROVAL_PROMPT = "force";

    /**
     * {@code AUTO_APPROVAL_PROMPT} is a constant for auto approval prompt type used in the auth operations
     * @apiNote from original Google's documentation,
     * {@code "auto"} to request auto-approval and is the default for web applications
     * **/
    public static final String AUTO_APPROVAL_PROMPT = "auto";

    /**
     * {@code netHttpTransport} is an instance used to HTTP transport
     * **/
    protected final NetHttpTransport netHttpTransport = new NetHttpTransport();

    /**
     * {@code gsonFactory} is an instance used to JSON factory
     * **/
    protected final GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

    /**
     * {@code authCodeFlow} is an instance used to Google's authorization code flow operations
     * **/
    protected GoogleAuthorizationCodeFlow authCodeFlow;

    /**
     * {@code credential} is an instance used to accessing protected resources using an access token
     * **/
    protected Credential credential;

    /**
     * {@code clientId} is a local instance used to identifier a client
     * **/
    protected String clientId;

    /**
     * {@code clientSecret} is a local instance used to memorize the client secret
     * **/
    protected String clientSecret;

    /**
     * {@code userId} is a local instance used to identifier a user
     * **/
    protected String userId;

    /**
     * {@code accessType} is a local instance used to memorize access type used in the auth operations
     * **/
    protected String accessType;

    /**
     * {@code approvalPrompt} is a local instance used to memorize approval prompt type used in the auth operations
     * **/
    protected String approvalPrompt;

    /**
     * {@code port} is a local instance used to memorize port used in the auth operations
     * **/
    protected int port;

    /**
     * {@code host} is a local instance used to memorize host used in the auth operations
     * **/
    protected String host;

    /**
     * {@code callBackPath} is a local instance used to memorize callback path used in the auth operations
     * **/
    protected String callBackPath;

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port: port used in the auth operations
     * @param host: host used in the auth operations
     * @param callBackPath: callback path used in the auth operations
     * @throws IOException when auth request have been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                         int port, String host, String callBackPath) throws IOException {
        if (!changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath))
            throw new IOException();
    }

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @throws IOException when auth request have been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, DEFAULT_PORT, DEFAULT_HOST, DEFAULT_CALLBACK_PATH);
    }

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port: port used in the auth operations
     * @throws IOException when auth request have been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt, int port) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, DEFAULT_CALLBACK_PATH);
    }

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port: port used in the auth operations
     * @param callBackPath: callback path used in the auth operations
     * @throws IOException when auth request have been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt, int port, String callBackPath) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, callBackPath);
    }

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param host: host used in the auth operations
     * @param port: port used in the auth operations
     * @throws IOException when auth request have been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType,
                         String approvalPrompt, String host, int port) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, DEFAULT_CALLBACK_PATH);
    }

    /**
     * Method to change a project to work on with {@code GoogleManager}'s library during runtime usage.
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param host:           host used in the auth operations
     * @param callBackPath:   callback path used in the auth operations
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request have been go wrong
     **/
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
            authCodeFlow = new GoogleAuthorizationCodeFlow.Builder(netHttpTransport, gsonFactory, clientId, clientSecret,
                    asList(GmailScopes.MAIL_GOOGLE_COM))
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
            return false;
        }
        return true;
    }

    /**
     * Method to change a project to work on with {@code GoogleManager}'s library during runtime usage.
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request have been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, DEFAULT_PORT, DEFAULT_HOST,
                DEFAULT_CALLBACK_PATH);
    }

    /**
     * Method to change a project to work on with {@code GoogleManager}'s library during runtime usage.
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request have been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, int port) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST,
                DEFAULT_CALLBACK_PATH);
    }

    /**
     * Method to change a project to work on with {@code GoogleManager}'s library during runtime usage.
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param callBackPath:   callback path used in the auth operations
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request have been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, int port, String callBackPath) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, callBackPath);
    }

    /**
     * Method to change a project to work on with {@code GoogleManager}'s library during runtime usage.
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param host:           host used in the auth operations
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request have been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, String host, int port) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, DEFAULT_CALLBACK_PATH);
    }

    /**
     * Method to get {@link #credential} instance <br>
     * Any params required
     *
     * @return {@link #credential} instance as {@link Credential}
     **/
    public Credential getCredential() {
        return credential;
    }

    /**
     * Method to get {@link #clientId} instance <br>
     * Any params required
     *
     * @return {@link #clientId} instance as {@link String}
     **/
    public String getClientId() {
        return clientId;
    }

    /**
     * Method to get {@link #clientSecret} instance <br>
     * Any params required
     *
     * @return {@link #clientSecret} instance as {@link String}
     **/
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Method to get {@link #userId} instance <br>
     * Any params required
     *
     * @return {@link #userId} instance as {@link String}
     **/
    public String getUserId() {
        return userId;
    }

    /**
     * Method to get {@link #accessType} instance <br>
     * Any params required
     *
     * @return {@link #accessType} instance as {@link String}
     **/
    public String getAccessType() {
        return accessType;
    }

    /**
     * Method to get {@link #approvalPrompt} instance <br>
     * Any params required
     *
     * @return {@link #approvalPrompt} instance as {@link String}
     **/
    public String getApprovalPrompt() {
        return approvalPrompt;
    }

    /**
     * Method to get {@link #port} instance <br>
     * Any params required
     *
     * @return {@link #port} instance as integer
     **/
    public int getPort() {
        return port;
    }

    /**
     * Method to get {@link #host} instance <br>
     * Any params required
     *
     * @return {@link #host} instance as {@link String}
     **/
    public String getHost() {
        return host;
    }

    /**
     * Method to get {@link #callBackPath} instance <br>
     * Any params required
     *
     * @return {@link #callBackPath} instance as {@link String}
     **/
    public String getCallBackPath() {
        return callBackPath;
    }

    /**
     * BaseList of return format types offered by library to format the responses as user wants
     *
     * @apiNote <ul>
     * <li>
     * STRING -> return response formatted as {@link String}
     * </li>
     * <li>
     * JSON -> return response formatted in JSON ({@link org.json.JSONObject} or {@link org.json.JSONArray}}
     * </li>
     * <li>
     * LIBRARY_OBJECT -> return response formatted as custom object offered by {@code GoogleManager}'s library
     * </li>
     * </ul>
     **/
    public enum ReturnFormat {
        STRING,
        JSON,
        LIBRARY_OBJECT
    }

}
