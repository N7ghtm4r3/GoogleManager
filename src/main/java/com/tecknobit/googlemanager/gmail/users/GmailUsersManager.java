package com.tecknobit.googlemanager.gmail.users;

import com.google.api.services.gmail.model.WatchRequest;
import com.google.api.services.gmail.model.WatchResponse;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.users.records.Profile;
import com.tecknobit.googlemanager.gmail.users.records.PushNotificationWatch;
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailUsersManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                             String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
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
        com.google.api.services.gmail.model.Profile profile = gmail.users().getProfile(userId).execute();
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
     * @return result of the stop request -> {@code true} is successful, {@code false} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/stop">
     * users.stop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean stop() {
        try {
            gmail.users().stop(userId);
            return true;
        } catch (IOException e) {
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
        WatchRequest watchRequest = new WatchRequest();
        watchRequest.setLabelIds(labelIds.stream().toList());
        watchRequest.setLabelFilterAction(labelFilterAction);
        watchRequest.setTopicName(topicName);
        WatchResponse watch = gmail.users().watch(userId, watchRequest).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(watch);
            case LIBRARY_OBJECT:
                return (T) new PushNotificationWatch(watch.getHistoryId(), watch.getExpiration());
            default:
                return (T) watch.toString();
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

}
