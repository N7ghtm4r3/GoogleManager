package com.tecknobit.googlemanager.gmail.history;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListHistoryResponse;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.history.records.HistoryList;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.stream;

/**
 * The {@code GmailUsersManager} class is useful to manage all Gmail's history endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history">Gmail history</a>
 **/
public class GmailHistoryManager extends GmailManager {

    /**
     * {@code MESSAGE_ADDED_HISTORY_TYPE} is a constant for message added history type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#HistoryType">history types</a>
     **/
    public static final String MESSAGE_ADDED_HISTORY_TYPE = "messageAdded";

    /**
     * {@code MESSAGE_DELETED_HISTORY_TYPE} is a constant for message deleted history type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#HistoryType">history types</a>
     **/
    public static final String MESSAGE_DELETED_HISTORY_TYPE = "messageDeleted";

    /**
     * {@code LABEL_ADDED_HISTORY_TYPE} is a constant for label added history type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#HistoryType">history types</a>
     **/
    public static final String LABEL_ADDED_HISTORY_TYPE = "labelAdded";

    /**
     * {@code LABEL_REMOVED_HISTORY_TYPE} is a constant for label removed history type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#HistoryType">history types</a>
     **/
    public static final String LABEL_REMOVED_HISTORY_TYPE = "labelRemoved";

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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailHistoryManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                               String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId) throws IOException {
        return getHistoryList(startHistoryId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, ReturnFormat format) throws IOException {
        return getHistoryList(history.list(userId).setStartHistoryId(valueOf(startHistoryId)).execute(), format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, int maxResults, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, String pageToken, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(String labelId, long startHistoryId, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setLabelId(labelId)
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, String[] historyTypes) throws IOException {
        return getHistoryList(startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, String[] historyTypes, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setHistoryTypes(stream(historyTypes).toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, Collection<String> historyTypes) throws IOException {
        return getHistoryList(startHistoryId, historyTypes, LIBRARY_OBJECT);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, Collection<String> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setHistoryTypes(historyTypes.stream().toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param labelId:        only return messages with a label matching the ID
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, String labelId, int maxResults,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setLabelId(labelId)
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String[] historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, int maxResults, String[] historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setHistoryTypes(stream(historyTypes).toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param maxResults:     maximum number of history records to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, int maxResults, Collection<String> historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, int maxResults, Collection<String> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setHistoryTypes(historyTypes.stream().toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param labelId:        only return messages with a label matching the ID
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, String pageToken, String labelId,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .setLabelId(labelId)
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, String pageToken, String[] historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, String pageToken, String[] historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .setHistoryTypes(stream(historyTypes).toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param startHistoryId: start history id to return history records after that specified
     * @param pageToken:      page token to retrieve a specific page of results in the list
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, String pageToken, Collection<String> historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, String pageToken, Collection<String> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setPageToken(pageToken)
                .setHistoryTypes(historyTypes.stream().toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in array of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(String labelId, long startHistoryId, String[] historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(String labelId, long startHistoryId, String[] historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setLabelId(labelId)
                .setHistoryTypes(stream(historyTypes).toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param labelId:        only return messages with a label matching the ID
     * @param startHistoryId: start history id to return history records after that specified
     * @param historyTypes:   history types to be returned by the function in {@link Collection} of {@link String} format
     * @return history list response as {@link HistoryList} custom object
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(String labelId, long startHistoryId, Collection<String> historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(String labelId, long startHistoryId, Collection<String> historyTypes,
                                ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setLabelId(labelId)
                .setHistoryTypes(historyTypes.stream().toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                      String[] historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                String[] historyTypes, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelId(labelId)
                .setHistoryTypes(stream(historyTypes).toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public HistoryList getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                      Collection<String> historyTypes) throws IOException {
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
     * @throws IOException when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list">
     * users.history.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getHistoryList(long startHistoryId, int maxResults, String pageToken, String labelId,
                                Collection<String> historyTypes, ReturnFormat format) throws IOException {
        ListHistoryResponse listHistoryResponse = history.list(userId).setStartHistoryId(valueOf(startHistoryId))
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelId(labelId)
                .setHistoryTypes(historyTypes.stream().toList())
                .execute();
        return getHistoryList(listHistoryResponse, format);
    }

    /**
     * Method to get a history list
     *
     * @param listHistoryResponse: history list obtained as response by {@code Google}'s API service
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return history list as {@code "format"} defines
     **/
    private <T> T getHistoryList(ListHistoryResponse listHistoryResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(listHistoryResponse);
            case LIBRARY_OBJECT:
                return (T) new HistoryList(new JSONObject(listHistoryResponse.toString()));
            default:
                return (T) listHistoryResponse.toString();
        }
    }

}
