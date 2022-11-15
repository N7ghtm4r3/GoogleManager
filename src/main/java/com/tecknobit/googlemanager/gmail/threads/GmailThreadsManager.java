package com.tecknobit.googlemanager.gmail.threads;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.ModifyThreadRequest;
import com.google.api.services.gmail.model.Thread;
import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.threads.records.GmailThread;
import com.tecknobit.googlemanager.gmail.threads.records.GmailThreads;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.googlemanager.gmail.GmailManager.ResponseFormat.METADATA_FORMAT;
import static java.util.Arrays.stream;

/**
 * The {@code GmailThreadsManager} class is useful to manage all Gmail's threads endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads">Gmail threads</a>
 **/
public class GmailThreadsManager extends GmailManager {

    /**
     * {@code threads} is the instance for {@link Gmail.Users.Threads}'s service
     **/
    private final Gmail.Users.Threads threads = gmail.threads();

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

    /**
     * Method to immediately and permanently deletes the specified thread.
     * Any messages that belong to the thread are also deleted. This operation cannot be undone. <br>
     * Prefer {@link #trashThread(GmailThread)} or {@link #trashThread(String)} instead
     *
     * @param threadToDelete: thrash to delete
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/delete">
     * users.threads.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public boolean deleteThread(GmailThread threadToDelete) {
        return deleteThread(threadToDelete.getId());
    }

    /**
     * Method to immediately and permanently deletes the specified thread.
     * Any messages that belong to the thread are also deleted. This operation cannot be undone. <br>
     * Prefer {@link #trashThread(GmailThread)} or {@link #trashThread(String)} instead
     *
     * @param threadIdToDelete: {@code "ID"} of the Thread to delete
     * @return result of change -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/delete">
     * users.threads.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public boolean deleteThread(String threadIdToDelete) {
        try {
            threads.delete(userId, threadIdToDelete).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet: the {@code "ID"} of the message containing the attachment
     * @return thread requested as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public GmailThread getThread(String threadIdToGet) throws IOException {
        return getThread(threadIdToGet, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet: the {@code "ID"} of the message containing the attachment
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return thread requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public <T> T getThread(String threadIdToGet, ReturnFormat format) throws IOException {
        return returnThread(threads.get(userId, threadIdToGet).execute(), format);
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet:  the {@code "ID"} of the message containing the attachment
     * @param responseFormat: the format to return the thread in -> constants available at {@link ResponseFormat}
     * @return thread requested as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public GmailThread getThread(String threadIdToGet, ResponseFormat responseFormat) throws IOException {
        return getThread(threadIdToGet, responseFormat, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet:  the {@code "ID"} of the message containing the attachment
     * @param responseFormat: the format to return the thread in -> constants available at {@link ResponseFormat}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public <T> T getThread(String threadIdToGet, ResponseFormat responseFormat, ReturnFormat format) throws IOException {
        return returnThread(threads.get(userId, threadIdToGet).setFormat(responseFormat.name()).execute(), format);
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet:   the {@code "ID"} of the message containing the attachment
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in array of {@link String} format
     * @return thread requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public GmailThread getThread(String threadIdToGet, String[] metadataHeaders) throws IOException {
        return getThread(threadIdToGet, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet:   the {@code "ID"} of the message containing the attachment
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in array of {@link String} format
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return thread requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public <T> T getThread(String threadIdToGet, String[] metadataHeaders, ReturnFormat format) throws IOException {
        return returnThread(threads.get(userId, threadIdToGet).setFormat(METADATA_FORMAT.name())
                .setMetadataHeaders(stream(metadataHeaders).toList()).execute(), format);
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet:   the {@code "ID"} of the message containing the attachment
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in {@link Collection} of {@link String} format
     * @return thread requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public GmailThread getThread(String threadIdToGet, Collection<String> metadataHeaders) throws IOException {
        return getThread(threadIdToGet, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified thread
     *
     * @param threadIdToGet:   the {@code "ID"} of the message containing the attachment
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in {@link Collection} of {@link String} format
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return thread requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/get">
     * users.threads.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}")
    public <T> T getThread(String threadIdToGet, Collection<String> metadataHeaders, ReturnFormat format) throws IOException {
        return returnThread(threads.get(userId, threadIdToGet).setFormat(METADATA_FORMAT.name())
                .setMetadataHeaders(metadataHeaders.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash) throws IOException {
        return getThreadsList(includeSpamTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String pageToken) throws IOException {
        return getThreadsList(includeSpamTrash, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String pageToken, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(String q, boolean includeSpamTrash) throws IOException {
        return getThreadsList(q, includeSpamTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(String q, boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String[] labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String[] labelIds, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, Collection<String> labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, Collection<String> labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String q, int maxResults) throws IOException {
        return getThreadsList(includeSpamTrash, q, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String q, int maxResults,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, String[] labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults,
                                       Collection<String> labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken,
                                       String[] labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken, String[] labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken,
                                       Collection<String> labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken, Collection<String> labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                       String q) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                String q, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds,
                                       String q) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds, String q,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String pageToken, String q) throws IOException {
        return getThreadsList(includeSpamTrash, pageToken, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String pageToken, String q,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String pageToken, String[] labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String pageToken, String[] labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String pageToken,
                                       Collection<String> labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String pageToken, Collection<String> labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String pageToken, String q,
                                       String[] labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String pageToken, String q, String[] labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String pageToken, String q,
                                       Collection<String> labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String pageToken, String q, Collection<String> labelIds,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, String[] labelIds, String q) throws IOException {
        return getThreadsList(includeSpamTrash, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, String[] labelIds, String q,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, Collection<String> labelIds, String q) throws IOException {
        return getThreadsList(includeSpamTrash, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, Collection<String> labelIds, String q,
                                ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                       String[] labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return threads with labels that match all the specified label IDs in array of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                String[] labelIds, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList())
                .execute(), format);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return threads matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return threads list requested as {@link GmailThreads} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public GmailThreads getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                       Collection<String> labelIds) throws IOException {
        return getThreadsList(includeSpamTrash, maxResults, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of threads in the user's mailbox
     *
     * @param includeSpamTrash: include threads from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of threads to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft threads matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return threads with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">
     * users.threads.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads")
    public <T> T getThreadsList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                Collection<String> labelIds, ReturnFormat format) throws IOException {
        return getThreadsList(threads.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList())
                .execute(), format);
    }

    /**
     * Method to get list of the threads in the user's mailbox
     *
     * @param listThreadsResponse: list obtained from Google's response
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return threads list as {@code "format"} defines
     **/
    @Returner
    private <T> T getThreadsList(ListThreadsResponse listThreadsResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(listThreadsResponse);
            case LIBRARY_OBJECT:
                return (T) new GmailThreads(new JSONObject(listThreadsResponse));
            default:
                return (T) listThreadsResponse.toString();
        }
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:      the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyAddLabelsIds(GmailThread thread, String[] addLabelIds) throws IOException {
        return modifyAddLabelsIds(thread, addLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:      the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyAddLabelsIds(GmailThread thread, String[] addLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, thread.getId(), new ModifyThreadRequest()
                .setAddLabelIds(stream(addLabelIds).toList()).setRemoveLabelIds(null)).execute(), format);
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:    the {@code "ID"} of the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyAddLabelsIds(String threadId, String[] addLabelIds) throws IOException {
        return modifyAddLabelsIds(threadId, addLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:    the {@code "ID"} of the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyAddLabelsIds(String threadId, String[] addLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, threadId, new ModifyThreadRequest()
                .setAddLabelIds(stream(addLabelIds).toList()).setRemoveLabelIds(null)).execute(), format);
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:      the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyAddLabelsIds(GmailThread thread, Collection<String> addLabelIds) throws IOException {
        return modifyAddLabelsIds(thread, addLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:      the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyAddLabelsIds(GmailThread thread, Collection<String> addLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, thread.getId(), new ModifyThreadRequest()
                .setAddLabelIds(addLabelIds.stream().toList()).setRemoveLabelIds(null)).execute(), format);
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:    the {@code "ID"} of the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyAddLabelsIds(String threadId, Collection<String> addLabelIds) throws IOException {
        return modifyAddLabelsIds(threadId, addLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the add labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:    the {@code "ID"} of the thread to modify
     * @param addLabelIds: list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyAddLabelsIds(String threadId, Collection<String> addLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, threadId, new ModifyThreadRequest()
                .setAddLabelIds(addLabelIds.stream().toList()).setRemoveLabelIds(null)).execute(), format);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyRemoveLabelsIds(GmailThread thread, String[] removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(thread, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyRemoveLabelsIds(GmailThread thread, String[] removeLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, thread.getId(), new ModifyThreadRequest().setAddLabelIds(null)
                .setRemoveLabelIds(stream(removeLabelIds).toList())).execute(), format);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyRemoveLabelsIds(String threadId, String[] removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(threadId, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyRemoveLabelsIds(String threadId, String[] removeLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, threadId, new ModifyThreadRequest().setAddLabelIds(null)
                .setRemoveLabelIds(stream(removeLabelIds).toList())).execute(), format);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyRemoveLabelsIds(GmailThread thread, Collection<String> removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(thread, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyRemoveLabelsIds(GmailThread thread, Collection<String> removeLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, thread.getId(), new ModifyThreadRequest().setAddLabelIds(null)
                .setRemoveLabelIds(removeLabelIds.stream().toList())).execute(), format);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modifyRemoveLabelsIds(String threadId, Collection<String> removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(threadId, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the remove the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modifyRemoveLabelsIds(String threadId, Collection<String> removeLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, threadId, new ModifyThreadRequest().setAddLabelIds(null)
                .setRemoveLabelIds(removeLabelIds.stream().toList())).execute(), format);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modify(GmailThread thread, String[] addLabelIds, String[] removeLabelIds) throws IOException {
        return modify(thread, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modify(GmailThread thread, String[] addLabelIds, String[] removeLabelIds,
                        ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, thread.getId(), new ModifyThreadRequest()
                .setAddLabelIds(stream(addLabelIds).toList())
                .setRemoveLabelIds(stream(removeLabelIds).toList())).execute(), format);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modify(String threadId, String[] addLabelIds, String[] removeLabelIds) throws IOException {
        return modify(threadId, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in array of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modify(String threadId, String[] addLabelIds, String[] removeLabelIds, ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, threadId, new ModifyThreadRequest()
                .setAddLabelIds(stream(addLabelIds).toList())
                .setRemoveLabelIds(stream(removeLabelIds).toList())).execute(), format);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modify(GmailThread thread, Collection<String> addLabelIds,
                              Collection<String> removeLabelIds) throws IOException {
        return modify(thread, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param thread:         the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modify(GmailThread thread, Collection<String> addLabelIds, Collection<String> removeLabelIds,
                        ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, thread.getId(), new ModifyThreadRequest()
                .setAddLabelIds(addLabelIds.stream().toList())
                .setRemoveLabelIds(removeLabelIds.stream().toList())).execute(), format);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in array of {@link String} format
     * @return thread modified as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public GmailThread modify(String threadId, Collection<String> addLabelIds, Collection<String> removeLabelIds) throws IOException {
        return modify(threadId, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify the labels applied to the thread. This applies to all messages in the thread
     *
     * @param threadId:       the {@code "ID"} of the thread to modify
     * @param addLabelIds:    list of IDs of labels to add to this thread. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this thread. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return thread modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/modify">
     * users.threads.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/modify")
    public <T> T modify(String threadId, Collection<String> addLabelIds, Collection<String> removeLabelIds,
                        ReturnFormat format) throws IOException {
        return returnThread(threads.modify(userId, threadId, new ModifyThreadRequest()
                .setAddLabelIds(addLabelIds.stream().toList())
                .setRemoveLabelIds(removeLabelIds.stream().toList())).execute(), format);
    }

    /**
     * Method to move the specified thread to the trash. Any messages that belong to the thread are also moved to the trash
     *
     * @param threadToTrash: thread to Trash
     * @return thread trashed as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/trash">
     * users.threads.trash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/trash")
    public GmailThread trashThread(GmailThread threadToTrash) throws IOException {
        return trashThread(threadToTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to move the specified thread to the trash. Any messages that belong to the thread are also moved to the trash
     *
     * @param threadToTrash: thread to Trash
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return thread trashed as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/trash">
     * users.threads.trash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/trash")
    public <T> T trashThread(GmailThread threadToTrash, ReturnFormat format) throws IOException {
        return returnThread(threads.trash(userId, threadToTrash.getId()).execute(), format);
    }

    /**
     * Method to move the specified thread to the trash. Any messages that belong to the thread are also moved to the trash
     *
     * @param threadIdToTrash: the {@code "ID"} of the thread to Trash
     * @return thread trashed as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/trash">
     * users.threads.trash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/trash")
    public GmailThread trashThread(String threadIdToTrash) throws IOException {
        return trashThread(threadIdToTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to move the specified thread to the trash. Any messages that belong to the thread are also moved to the trash
     *
     * @param threadIdToTrash: the {@code "ID"} of the thread to Trash
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return thread trashed as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/trash">
     * users.threads.trash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/trash")
    public <T> T trashThread(String threadIdToTrash, ReturnFormat format) throws IOException {
        return returnThread(threads.trash(userId, threadIdToTrash).execute(), format);
    }

    /**
     * Method to remove the specified thread from the trash. Any messages that belong to the thread are also removed from the trash
     *
     * @param threadToUntrash: thread to remove from Trash
     * @return thread untrashed as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/untrash">
     * users.threads.untrash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/untrash")
    public GmailThread untrashThread(GmailThread threadToUntrash) throws IOException {
        return untrashThread(threadToUntrash, LIBRARY_OBJECT);
    }

    /**
     * Method to remove the specified thread from the trash. Any messages that belong to the thread are also removed from the trash
     *
     * @param threadToUntrash: thread to remove from Trash
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return thread untrashed as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/untrash">
     * users.threads.untrash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/untrash")
    public <T> T untrashThread(GmailThread threadToUntrash, ReturnFormat format) throws IOException {
        return returnThread(threads.untrash(userId, threadToUntrash.getId()).execute(), format);
    }

    /**
     * Method to remove the specified thread from the trash. Any messages that belong to the thread are also removed from the trash
     *
     * @param threadIdToUntrash: the {@code "ID"} of the thread to remove from Trash
     * @return thread untrashed as {@link GmailThread} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/untrash">
     * users.threads.untrash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/untrash")
    public GmailThread untrashThread(String threadIdToUntrash) throws IOException {
        return untrashThread(threadIdToUntrash, LIBRARY_OBJECT);
    }

    /**
     * Method to remove the specified thread from the trash. Any messages that belong to the thread are also removed from the trash
     *
     * @param threadIdToUntrash: the {@code "ID"} of the thread to remove from Trash
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return thread untrashed as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/untrash">
     * users.threads.untrash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/threads/{id}/untrash")
    public <T> T untrashThread(String threadIdToUntrash, ReturnFormat format) throws IOException {
        return returnThread(threads.untrash(userId, threadIdToUntrash).execute(), format);
    }

    /**
     * Method to create a thread object
     *
     * @param thread: thread obtained from Google's response
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return thread as {@code "format"} defines
     **/
    @Returner
    private <T> T returnThread(Thread thread, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(thread);
            case LIBRARY_OBJECT:
                return (T) new GmailThread(new JSONObject(thread));
            default:
                return (T) thread.toString();
        }
    }

}
