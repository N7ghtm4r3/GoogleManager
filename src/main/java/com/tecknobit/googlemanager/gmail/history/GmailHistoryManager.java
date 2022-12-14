package com.tecknobit.googlemanager.gmail.history;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListHistoryResponse;
import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.apimanager.annotations.Wrapper;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.history.records.HistoryList;
import com.tecknobit.googlemanager.gmail.labels.records.Label;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.tecknobit.apimanager.apis.APIRequest.RequestMethod.GET;
import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static java.math.BigInteger.valueOf;

/**
 * The {@code GmailUsersManager} class is useful to manage all Gmail's history endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history">Gmail history</a>
 **/
public class GmailHistoryManager extends GmailManager {

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId) throws IOException {
        return getHistoryList(startHistoryId, LIBRARY_OBJECT);
    }

    /**
     * {@code history} is the instance for {@link Gmail.Users.History}'s service
     **/
    private final Gmail.Users.History history = gmail.history();

    /**
     * Constructor to init a {@link GmailHistoryManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param host:            host used in the auth operations
     * @param callBackPath:    callback path used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailHistoryManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailHistoryManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailHistoryManager(String clientId, String clientSecret, String userId, String accessType,
                               String approvalPrompt, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, applicationName);
    }

    /**
     * Constructor to init a {@link GmailHistoryManager}
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
    public GmailHistoryManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailHistoryManager}
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
    public GmailHistoryManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailHistoryManager}
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
    public GmailHistoryManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailHistoryManager} <br>
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
    public GmailHistoryManager() throws IOException {
        super();
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, ReturnFormat format) throws IOException {
        return returnHistoryList(history.list(userId).setStartHistoryId(valueOf(startHistoryId)).execute(), format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults) throws IOException {
        return getHistoryList(startHistoryId, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, String pageToken) throws IOException {
        return getHistoryList(startHistoryId, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, String pageToken, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param label:          only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(Label label, long startHistoryId) throws IOException {
        return getHistoryList(label.getId(), startHistoryId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param label:          only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(Label label, long startHistoryId, ReturnFormat format) throws IOException {
        return getHistoryList(label.getId(), startHistoryId, format);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(String labelId, long startHistoryId) throws IOException {
        return getHistoryList(labelId, startHistoryId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(String labelId, long startHistoryId, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setLabelId(labelId)
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, HistoryType[] historyTypes) throws IOException {
        return getHistoryList(startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, HistoryType[] historyTypes, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, Collection<HistoryType> historyTypes) throws IOException {
        return getHistoryList(startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, Collection<HistoryType> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String pageToken) throws IOException {
        return getHistoryList(startHistoryId, maxResults, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param label:          only return messages with a label matching the ID
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, Label label, int maxResults) throws IOException {
        return getHistoryList(startHistoryId, label.getId(), maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param label:          only return messages with a label matching the ID
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, Label label, int maxResults,
                                ReturnFormat format) throws IOException {
        return getHistoryList(startHistoryId, label.getId(), maxResults, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param labelId:        only return messages with a label matching the ID
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, String labelId, int maxResults) throws IOException {
        return getHistoryList(startHistoryId, labelId, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param labelId:        only return messages with a label matching the ID
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, String labelId, int maxResults,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setLabelId(labelId)
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults, HistoryType[] historyTypes) throws IOException {
        return getHistoryList(startHistoryId, maxResults, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, HistoryType[] historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults,
                                      Collection<HistoryType> historyTypes) throws IOException {
        return getHistoryList(startHistoryId, maxResults, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, Collection<HistoryType> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param label:          only return messages with a label matching the ID
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, String pageToken, Label label) throws IOException {
        return getHistoryList(startHistoryId, pageToken, label.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param label:          only return messages with a label matching the ID
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, String pageToken, Label label,
                                ReturnFormat format) throws IOException {
        return getHistoryList(startHistoryId, pageToken, label.getId(), format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param labelId:        only return messages with a label matching the ID
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, String pageToken, String labelId) throws IOException {
        return getHistoryList(startHistoryId, pageToken, labelId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param labelId:        only return messages with a label matching the ID
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, String pageToken, String labelId,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .setLabelId(labelId)
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, String pageToken, HistoryType[] historyTypes) throws IOException {
        return getHistoryList(startHistoryId, pageToken, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, String pageToken, HistoryType[] historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, String pageToken,
                                      Collection<HistoryType> historyTypes) throws IOException {
        return getHistoryList(startHistoryId, pageToken, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, String pageToken, Collection<HistoryType> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param label:          only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(Label label, long startHistoryId, HistoryType[] historyTypes) throws IOException {
        return getHistoryList(label.getId(), startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param label:          only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(Label label, long startHistoryId, HistoryType[] historyTypes,
                                ReturnFormat format) throws IOException {
        return getHistoryList(label.getId(), startHistoryId, historyTypes, format);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(String labelId, long startHistoryId, HistoryType[] historyTypes) throws IOException {
        return getHistoryList(labelId, startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(String labelId, long startHistoryId, HistoryType[] historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setLabelId(labelId)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param label:          only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(Label label, long startHistoryId, Collection<HistoryType> historyTypes) throws IOException {
        return getHistoryList(label.getId(), startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param label:          only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(Label label, long startHistoryId, Collection<HistoryType> historyTypes,
                                ReturnFormat format) throws IOException {
        return getHistoryList(label.getId(), startHistoryId, historyTypes, format);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(String labelId, long startHistoryId, Collection<HistoryType> historyTypes) throws IOException {
        return getHistoryList(labelId, startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(String labelId, long startHistoryId, Collection<HistoryType> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setLabelId(labelId)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param label:          only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String pageToken, Label label,
                                      HistoryType[] historyTypes) throws IOException {
        return getHistoryList(startHistoryId, maxResults, pageToken, label.getId(), historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param label:          only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken, Label label,
                                HistoryType[] historyTypes, ReturnFormat format) throws IOException {
        return getHistoryList(startHistoryId, maxResults, pageToken, label.getId(), historyTypes, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param labelId:        only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                      HistoryType[] historyTypes) throws IOException {
        return getHistoryList(startHistoryId, maxResults, pageToken, labelId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param labelId:        only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                HistoryType[] historyTypes, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelId(labelId)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param label:          only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String pageToken, Label label,
                                      Collection<HistoryType> historyTypes) throws IOException {
        return getHistoryList(startHistoryId, maxResults, pageToken, label.getId(), historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param label:          only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken, Label label,
                                Collection<HistoryType> historyTypes, ReturnFormat format) throws IOException {
        return getHistoryList(startHistoryId, maxResults, pageToken, label.getId(), historyTypes, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param labelId:        only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @Wrapper
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                      Collection<HistoryType> historyTypes) throws IOException {
        return getHistoryList(startHistoryId, maxResults, pageToken, labelId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param labelId:        only return messages with a label matching the ID
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(method = GET, path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/history")
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                Collection<HistoryType> historyTypes, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelId(labelId)
                .setHistoryTypes(returnHistoryTypesList(historyTypes))
                .execute();
        return returnHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history types list
     *
     * @param types: history types list
     * @return history types list as {@link List} of {@link String}
     **/
    @Returner
    private <T> List<String> returnHistoryTypesList(T types) {
        try {
            ArrayList<String> mTypes = new ArrayList<>();
            if (types instanceof Collection<?>)
                types = (T) ((Collection<?>) types).toArray(new Object[0]);
            for (Object type : (Object[]) types)
                mTypes.add(((HistoryType) type).name());
            return mTypes;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to get a history list
     *
     * @param listHistoryResponse: history list obtained as response by {@code Google}'s API service
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     **/
    @Returner
    private <T> T returnHistoryList(ListHistoryResponse listHistoryResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(listHistoryResponse);
            case LIBRARY_OBJECT:
                return (T) new HistoryList(new JSONObject(listHistoryResponse.toString()));
            default:
                return (T) listHistoryResponse.toString();
        }
    }

    /**
     * {@code HistoryType} list of available history types
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#HistoryType">
     * history types</a>
     **/
    public enum HistoryType {

        /**
         * {@code "messageAdded"} history type
         **/
        messageAdded,

        /**
         * {@code "messageDeleted"} history type
         **/
        messageDeleted,

        /**
         * {@code "labelAdded"} history type
         **/
        labelAdded,

        /**
         * {@code "labelRemoved"} history type
         **/
        labelRemoved,

    }

}
