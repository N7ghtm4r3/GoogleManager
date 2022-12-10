package com.tecknobit.googlemanager.gmail.users;

import com.google.api.services.gmail.model.WatchRequest;
import com.google.api.services.gmail.model.WatchResponse;
import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.users.records.Profile;
import com.tecknobit.googlemanager.gmail.users.records.PushNotificationWatch;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.POST;
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
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, applicationName);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param callBackPath:    callback path used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailUsersManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param host:            host used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailUsersManager} <br>
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
    public GmailUsersManager() throws IOException {
        super();
    }

    /**
     * Method to get profile <br>
     * Any params required
     *
     * @return profile as {@link Profile} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/getProfile">
     * users.getProfile</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/profile")
    public Profile getProfile() throws IOException {
        return getProfile(LIBRARY_OBJECT);
    }

    /**
     * Method to get profile
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return profile as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/getProfile">
     * users.getProfile</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Returner
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/profile")
    public <T> T getProfile(ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Profile profile = gmail.getProfile(userId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(profile);
            case LIBRARY_OBJECT:
                return (T) new Profile(profile.getEmailAddress(),
                        profile.getMessagesTotal(),
                        profile.getThreadsTotal(),
                        profile.getHistoryId()
                );
            default:
                return (T) profile.toString();
        }
    }

    /**
     * Method to stop receiving push notifications for the given user mailbox <br>
     * Any params required
     *
     * @return result of the stop request -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/stop">
     * users.stop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(method = POST, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/stop")
    public boolean stop() {
        try {
            gmail.stop(userId);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/watch")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
    @RequestPath(method = POST, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/watch")
    public <T> T watch(String[] labelIds, String labelFilterAction, String topicName,
                       ReturnFormat format) throws IOException {
        return watch(asList(labelIds), labelFilterAction, topicName, format);
    }

    /**
     * Method to set up or update a push notification watch on the given user mailbox
     *
     * @param labelIds:          list of labelIds to restrict notifications about as {@link Collection} of {@link String}
     * @param labelFilterAction: filtering behavior of labelIds list specified ->
     *                           ({@link PushNotificationWatch#INCLUDE_LABEL_FILTER_ACTION} or {@link PushNotificationWatch#EXCLUDE_LABEL_FILTER_ACTION})
     * @param topicName:         a fully qualified Google Cloud Pub/Sub API topic name to publish the events to
     * @return push notification watch as {@link PushNotificationWatch} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
    @Wrapper
    @RequestPath(method = POST, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/watch")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
     * users.watch</a>
     **/
    @Returner
    @RequestPath(method = POST, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/watch")
    public <T> T watch(Collection<String> labelIds, String labelFilterAction, String topicName,
                       ReturnFormat format) throws IOException {
        WatchRequest watchRequest = new WatchRequest();
        watchRequest.setLabelIds(labelIds.stream().toList());
        watchRequest.setLabelFilterAction(labelFilterAction);
        watchRequest.setTopicName(topicName);
        WatchResponse watch = gmail.watch(userId, watchRequest).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(watch);
            case LIBRARY_OBJECT:
                return (T) new PushNotificationWatch(watch.getHistoryId(), watch.getExpiration());
            default:
                return (T) watch.toString();
        }
    }

}
