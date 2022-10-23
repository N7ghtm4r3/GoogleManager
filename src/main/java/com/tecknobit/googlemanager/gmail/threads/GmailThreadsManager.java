package com.tecknobit.googlemanager.gmail.threads;

import com.tecknobit.googlemanager.gmail.GmailManager;

import java.io.IOException;

/**
 * The {@code GmailThreadsManager} class is useful to manage all Gmail's threads endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads">Gmail threads</a>
 **/
public class GmailThreadsManager extends GmailManager {

    /**
     * Constructor to init a {@link GmailThreadsManager}
     *
     * @param clientId        :       client identifier value
     * @param clientSecret    :   client secret value
     * @param userId          :         used to identifier a user -> me to use an authenticated user
     * @param accessType      :     access type used in the auth operations
     * @param approvalPrompt  : approval prompt type used in the auth operations
     * @param port            :           port used in the auth operations
     * @param host            :           host used in the auth operations
     * @param callBackPath    :   callback path used in the auth operations
     * @param applicationName : name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailThreadsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailThreadsManager}
     *
     * @param clientId        :        client identifier value
     * @param clientSecret    :    client secret value
     * @param userId          :          used to identifier a user -> me to use an authenticated user
     * @param accessType      :      access type used in the auth operations
     * @param approvalPrompt  :  approval prompt type used in the auth operations
     * @param applicationName : name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailThreadsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, applicationName);
    }

    /**
     * Constructor to init a {@link GmailThreadsManager}
     *
     * @param clientId        :        client identifier value
     * @param clientSecret    :    client secret value
     * @param userId          :          used to identifier a user -> me to use an authenticated user
     * @param accessType      :      access type used in the auth operations
     * @param approvalPrompt  :  approval prompt type used in the auth operations
     * @param port            :            port used in the auth operations
     * @param applicationName : name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailThreadsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailThreadsManager}
     *
     * @param clientId        :        client identifier value
     * @param clientSecret    :    client secret value
     * @param userId          :          used to identifier a user -> me to use an authenticated user
     * @param accessType      :      access type used in the auth operations
     * @param approvalPrompt  :  approval prompt type used in the auth operations
     * @param port            :            port used in the auth operations
     * @param callBackPath    :    callback path used in the auth operations
     * @param applicationName : name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailThreadsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailThreadsManager}
     *
     * @param clientId        :        client identifier value
     * @param clientSecret    :    client secret value
     * @param userId          :          used to identifier a user -> me to use an authenticated user
     * @param accessType      :      access type used in the auth operations
     * @param approvalPrompt  :  approval prompt type used in the auth operations
     * @param host            :            host used in the auth operations
     * @param port            :            port used in the auth operations
     * @param applicationName : name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailThreadsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailThreadsManager} <br>
     * Any params required
     *
     * @apiNote this constructor is useful to instantiate a new {@link GmailManager}'s manager without reinsert
     * credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GmailManager firstManager = new GmailManager(CLIENT_ID, CLIENT_SECRET, "email@gmail.com",
     *                 ACCESS_TYPE, APPROVAL_PROMPT, port, "host", "callback_path", "application_name");
     *        //You don't need to insert all credentials to make manager work
     *        GmailManager secondManager = new GmailManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GmailThreadsManager() throws IOException {
        super();
    }

}
