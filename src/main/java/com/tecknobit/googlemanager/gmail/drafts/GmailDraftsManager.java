package com.tecknobit.googlemanager.gmail.drafts;

import com.tecknobit.googlemanager.gmail.GmailManager;

import java.io.IOException;

/**
 * The {@code GmailDraftsManager} class is useful to manage all Gmail's drafts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts">Gmail drafts</a>
 **/
public class GmailDraftsManager extends GmailManager {

    /**
     * Constructor to init a {@link GmailDraftsManager}
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
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port, String host, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
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
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
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
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
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
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, String host, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
    }

}
