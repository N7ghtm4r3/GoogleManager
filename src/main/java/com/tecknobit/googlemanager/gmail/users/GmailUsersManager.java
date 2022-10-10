package com.tecknobit.googlemanager.gmail.users;

import com.tecknobit.apimanager.Manager.APIRequest;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.users.records.Profile;
import com.tecknobit.googlemanager.gmail.users.records.PushNotificationWatch;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static java.util.Arrays.asList;

/**
 * The {@code GmailUsersManager} class is useful to manage all Gmail's users endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users">Gmail users</a>
 **/
public class GmailUsersManager extends GmailManager {

    /**
     * {@code USERS_ENDPOINT} is a constant that indicates base {@code Gmail}'s users endpoint to work for the API requests
     **/
    public static final String USERS_ENDPOINT = "/gmail/v1/users/";

    /**
     * Constructor to init a {@link GmailUsersManager}
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
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             int port, String host, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
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
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt, int port, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
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
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType,
                             String approvalPrompt, String host, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
    }

    /**
     * Method to get profile <br>
     * Any params required
     *
     * @return profile as {@link Profile} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/getProfile">
     * users.getProfile</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Profile getProfile() throws IOException {
        return getProfile(LIBRARY_OBJECT);
    }

    /**
     * Method to get profile
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return profile as {@code "format"} defines
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/getProfile">
     * users.getProfile</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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

    /**
     * Method to stop receiving push notifications for the given user mailbox <br>
     * Any params required
     *
     * @return result of the stop request -> {@code true} is successful, {@code false} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/stop">
     * users.stop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean stop() {
        try {
            sendPostRequest("stop", null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to set up or update a push notification watch on the given user mailbox
     *
     * @param labelIds:          list of labelIds to restrict notifications about as {@link Collection} of {@link String}
     * @param labelFilterAction: filtering behavior of labelIds list specified ->
     *                           ({@link PushNotificationWatch#INCLUDE_LABEL_FILTER_ACTION} or {@link PushNotificationWatch#EXCLUDE_LABEL_FILTER_ACTION})
     * @param topicName:         a fully qualified Google Cloud Pub/Sub API topic name to publish the events to
     * @return push notification watch as {@link PushNotificationWatch} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
    public PushNotificationWatch watch(Collection<String> labelIds, String labelFilterAction,
                                       String topicName) throws IOException {
        return watch(labelIds, labelFilterAction, topicName, LIBRARY_OBJECT);
    }

    /**
     * Method to set up or update a push notification watch on the given user mailbox
     *
     * @param labelIds:          list of labelIds to restrict notifications about as {@link Collection} of {@link String}
     * @param labelFilterAction: filtering behavior of labelIds list specified ->
     *                           ({@link PushNotificationWatch#INCLUDE_LABEL_FILTER_ACTION} or {@link PushNotificationWatch#EXCLUDE_LABEL_FILTER_ACTION})
     * @param topicName:         a fully qualified Google Cloud Pub/Sub API topic name to publish the events to
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return push notification watch as {@code "format"} defines
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
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

    /**
     * Method to set up or update a push notification watch on the given user mailbox
     *
     * @param labelIds:          list of labelIds to restrict notifications about as {@link String} array
     * @param labelFilterAction: filtering behavior of labelIds list specified ->
     *                           ({@link PushNotificationWatch#INCLUDE_LABEL_FILTER_ACTION} or {@link PushNotificationWatch#EXCLUDE_LABEL_FILTER_ACTION})
     * @param topicName:         a fully qualified Google Cloud Pub/Sub API topic name to publish the events to
     * @return push notification watch as {@link PushNotificationWatch} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
    public PushNotificationWatch watch(String[] labelIds, String labelFilterAction, String topicName) throws IOException {
        return watch(labelIds, labelFilterAction, topicName, LIBRARY_OBJECT);
    }

    /**
     * Method to set up or update a push notification watch on the given user mailbox
     *
     * @param labelIds:          list of labelIds to restrict notifications about as {@link String} array
     * @param labelFilterAction: filtering behavior of labelIds list specified ->
     *                           ({@link PushNotificationWatch#INCLUDE_LABEL_FILTER_ACTION} or {@link PushNotificationWatch#EXCLUDE_LABEL_FILTER_ACTION})
     * @param topicName:         a fully qualified Google Cloud Pub/Sub API topic name to publish the events to
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return push notification watch as {@code "format"} defines
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
    public <T> T watch(String[] labelIds, String labelFilterAction, String topicName,
                       ReturnFormat format) throws IOException {
        return watch(asList(labelIds), labelFilterAction, topicName, format);
    }

    /**
     * Method to send a GET request
     *
     * @param endpoint: endpoint to make the request
     * @return response of the request in JSON format as {@link String}
     * @throws IOException when request have been go wrong
     **/
    @Override
    public String sendGetRequest(String endpoint) throws IOException {
        return super.sendGetRequest(USERS_ENDPOINT + userId + "/" + endpoint);
    }

    /**
     * Method to send a POST request
     *
     * @param endpoint:   endpoint to make the request
     * @param bodyParams: body payload for the POST request
     * @return response of the request in JSON format as {@link String}
     * @throws IOException when request have been go wrong
     **/
    @Override
    public String sendPostRequest(String endpoint, String bodyParams) throws IOException {
        return super.sendPostRequest(USERS_ENDPOINT + userId + "/" + endpoint, bodyParams);
    }

}
