package com.tecknobit.googlemanager;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

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
     *
     * @apiNote from original Google's documentation,
     * {@code "auto"} to request auto-approval and is the default for web applications
     **/
    public static final String AUTO_APPROVAL_PROMPT = "auto";

    /**
     * {@code properties} is a local instance used to instantiate a new {@link GoogleManager}'s manager without re-insert
     * credentials
     **/
    protected static final Properties properties = new Properties();

    /**
     * {@code credentialDataStore} is a local instance used to memorize credentials for {@code "Google"}'s api usage
     **/
    private static final DataStore<StoredCredential> credentialDataStore;

    static {
        try {
            credentialDataStore = new FileDataStoreFactory(new File("/.oauth-credentials/"))
                    .getDataStore("credentialDatastore");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code netHttpTransport} is an instance used to HTTP transport
     **/
    protected final NetHttpTransport netHttpTransport = new NetHttpTransport();

    /**
     * {@code gsonFactory} is an instance used to JSON factory
     **/
    protected final GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

    /**
     * {@code authCodeFlow} is an instance used to Google's authorization code flow operations
     * **/
    protected GoogleAuthorizationCodeFlow authCodeFlow;

    /**
     * {@code credentials} is an instance used to accessing protected resources using an access token
     * **/
    protected Credential credentials;

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
     **/
    protected String host;

    /**
     * {@code callBackPath} is a local instance used to memorize callback path used in the auth operations
     **/
    protected String callBackPath;

    /**
     * {@code callBackPath} is a local instance to store collections of {@link String} for scopes
     **/
    private Collection<String> scopes;

    /**
     * {@code tokenAutoRefreshing} is a local instance to enable or not the auto refreshing routine of {@link #credentials}'s token
     *
     * @apiNote by default is {@code "false"}
     **/
    private volatile boolean tokenAutoRefreshing;

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port: port used in the auth operations
     * @param host: host used in the auth operations
     * @param callBackPath: callback path used in the auth operations
     * @param scopes: collection of {@link String} for scopes
     * @throws IOException when auth request has been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                         int port, String host, String callBackPath, Collection<String> scopes) throws IOException {
        if (!changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, scopes))
            throw new IOException();
    }

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param scopes: collection of {@link String} for scopes
     * @throws IOException when auth request has been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                         Collection<String> scopes) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, DEFAULT_PORT, DEFAULT_HOST, DEFAULT_CALLBACK_PATH,
                scopes);
    }

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port: port used in the auth operations
     * @param scopes: collection of {@link String} for scopes
     * @throws IOException when auth request has been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                         int port, Collection<String> scopes) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, DEFAULT_CALLBACK_PATH, scopes);
    }

    /** Constructor to init a {@link GoogleManager}
     * @param clientId: client identifier value
     * @param clientSecret: client secret value
     * @param userId: used to identifier a user -> me to use an authenticated user
     * @param accessType: access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port: port used in the auth operations
     * @param callBackPath: callback path used in the auth operations
     * @param scopes: collection of {@link String} for scopes
     * @throws IOException when auth request has been go wrong
     * **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                         int port, String callBackPath, Collection<String> scopes) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, callBackPath, scopes);
    }

    /**
     * Constructor to init a {@link GoogleManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param host:           host used in the auth operations
     * @param port:           port used in the auth operations
     * @param scopes:         collection of {@link String} for scopes
     * @throws IOException when auth request has been go wrong
     **/
    public GoogleManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                         String host, int port, Collection<String> scopes) throws IOException {
        this(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, DEFAULT_CALLBACK_PATH, scopes);
    }

    /**
     * Constructor to init a {@link GoogleManager}
     *
     * @param scopes: collection of {@link String} for scopes
     * @throws IOException when auth request has been go wrong
     * @apiNote this constructor is useful to instantiate a new {@link GoogleManager}'s manager without inserted
     * credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GoogleManager firstManager = new GoogleManager(CLIENT_ID, CLIENT_SECRET, "email@gmail.com",
     *                 ACCESS_TYPE, APPROVAL_PROMPT, port, "host", "callback_path");
     *        //You don't need to insert all credentials to make manager work
     *        GoogleManager secondManager = new GoogleManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GoogleManager(Collection<String> scopes) throws IOException {
        clientId = properties.getProperty("clientId");
        if (clientId == null)
            throw new IOException("You need to call a parameterized constructor first of this or invalid clientId inserted");
        if (!changeProject(clientId, properties.getProperty("clientSecret"), properties.getProperty("userId"),
                properties.getProperty("accessType"), properties.getProperty("approvalPrompt"),
                parseInt(properties.getProperty("port")), properties.getProperty("host"),
                properties.getProperty("callBackPath"), scopes)) {
            throw new IOException();
        }
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
     * @param scopes:         collection of {@link String} for scopes
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request has been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType,
                                 String approvalPrompt, int port, String host, String callBackPath,
                                 Collection<String> scopes) {
        try {
            if (accessType != null && !accessType.equals(ONLINE_ACCESS_TYPE) && !accessType.equals(OFFLINE_ACCESS_TYPE))
                throw new IOException("Access type must be online, offline or null");
            if (approvalPrompt != null && !approvalPrompt.equals(FORCE_APPROVAL_PROMPT) &&
                    !approvalPrompt.equals(AUTO_APPROVAL_PROMPT)) {
                throw new IOException("Approval prompt must be force, auto or null");
            }
            if (!callBackPath.contains("/"))
                callBackPath = "/" + callBackPath;
            this.accessType = accessType;
            this.approvalPrompt = approvalPrompt;
            this.scopes = scopes;
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.userId = userId;
            this.port = port;
            this.host = host;
            this.callBackPath = callBackPath;
            try {
                createAuthCodeFlow();
                credentials = new AuthorizationCodeInstalledApp(authCodeFlow, new LocalServerReceiver.Builder()
                        .setPort(port).setHost(host).setCallbackPath(callBackPath).build()).authorize(userId);
            } catch (Exception e) {
                String errorMessage = e.getLocalizedMessage();
                if (errorMessage.contains("invalid_grant") || errorMessage.contains("Token has been revoked")) {
                    credentialDataStore.clear();
                    return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, host,
                            callBackPath, scopes);
                }
            }
            properties.setProperty("clientId", clientId);
            properties.setProperty("clientSecret", clientSecret);
            properties.setProperty("userId", userId);
            properties.setProperty("accessType", accessType);
            properties.setProperty("approvalPrompt", approvalPrompt);
            properties.setProperty("port", String.valueOf(port));
            properties.setProperty("host", host);
            properties.setProperty("callBackPath", callBackPath);
        } catch (IOException e) {
            e.printStackTrace();
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
     * @param scopes:         collection of {@link String} for scopes
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request has been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                 Collection<String> scopes) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, DEFAULT_PORT, DEFAULT_HOST,
                DEFAULT_CALLBACK_PATH, scopes);
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
     * @param scopes:         collection of {@link String} for scopes
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request has been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                 int port, Collection<String> scopes) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST,
                DEFAULT_CALLBACK_PATH, scopes);
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
     * @param scopes:         collection of {@link String} for scopes
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request has been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                 int port, String callBackPath, Collection<String> scopes) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, DEFAULT_HOST, callBackPath,
                scopes);
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
     * @param scopes:         collection of {@link String} for scopes
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote throws {@link IOException} when some params are not correct or auth request has been go wrong
     **/
    public boolean changeProject(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                 String host, int port, Collection<String> scopes) {
        return changeProject(clientId, clientSecret, userId, accessType, approvalPrompt, port, host,
                DEFAULT_CALLBACK_PATH, scopes);
    }

    /**
     * Method to instantiate {@link #authCodeFlow} <br>
     * Any params required
     **/
    private void createAuthCodeFlow() {
        authCodeFlow = new Builder(netHttpTransport, gsonFactory, clientId, clientSecret, scopes)
                .setAccessType(accessType)
                .setApprovalPrompt(approvalPrompt)
                .setCredentialDataStore(credentialDataStore)
                .build();
    }

    /**
     * Method to enable the auto refreshing of refresh token for {@link #credentials} instance <br>
     * Any params required
     *
     * @apiNote useful for example for {@code "backend"} use, because the token expires and with this method
     * it will be automatically refreshed allowing to continue the workflow with {@code "Google"}'s
     * services, {@link #accessType} will be set as {@link #OFFLINE_ACCESS_TYPE} and {@link #approvalPrompt} will be set
     * as {@link #FORCE_APPROVAL_PROMPT} to perform the auto refresh
     **/
    public void enableTokenAutoRefreshing() {
        if (!tokenAutoRefreshing) {
            if (authCodeFlow.getAccessType().equals(ONLINE_ACCESS_TYPE) ||
                    authCodeFlow.getApprovalPrompt().equals(AUTO_APPROVAL_PROMPT)) {
                accessType = OFFLINE_ACCESS_TYPE;
                approvalPrompt = FORCE_APPROVAL_PROMPT;
                createAuthCodeFlow();
            }
            tokenAutoRefreshing = true;
            newSingleThreadExecutor().execute(() -> {
                while (tokenAutoRefreshing) {
                    try {
                        if (System.currentTimeMillis() >= credentials.getExpirationTimeMilliseconds()) {
                            credentials.refreshToken();
                            credentials.setAccessToken(new GoogleRefreshTokenRequest(netHttpTransport, gsonFactory,
                                    credentials.getRefreshToken(), clientId, clientSecret).setScopes(scopes)
                                    .setGrantType("refresh_token").execute().getAccessToken());
                        }
                        sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * Method to disable the auto refreshing of refresh token for {@link #credentials} instance <br>
     * Any params required
     **/
    public void disableTokenAutoRefreshing() {
        if (tokenAutoRefreshing)
            tokenAutoRefreshing = false;
    }

    /**
     * Method to get {@link #credentials} instance <br>
     * Any params required
     *
     * @return {@link #credentials} instance as {@link Credential}
     **/
    public Credential getCredentials() {
        return credentials;
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
     * JSON -> return response formatted as JSON ({@link org.json.JSONObject} or {@link org.json.JSONArray}}
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
