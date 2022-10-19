package com.tecknobit.googlemanager.gmail.messages;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.BatchDeleteMessagesRequest;
import com.google.api.services.gmail.model.BatchModifyMessagesRequest;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.ModifyMessageRequest;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.messages.records.Messages;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.googlemanager.gmail.GmailManager.ResponseFormat.METADATA_FORMAT;
import static java.util.Arrays.stream;

/**
 * The {@code GmailMessagesManager} class is useful to manage all Gmail's messages endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages">Gmail messages</a>
 **/
public class GmailMessagesManager extends GmailManager {

    /**
     * {@code messages} is the instance for {@link Gmail.Users.Messages}'s service
     **/
    private final Gmail.Users.Messages messages = gmail.messages();

    /**
     * Constructor to init a {@link GmailMessagesManager}
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
    public GmailMessagesManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailMessagesManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request have been go wrong
     **/
    public GmailMessagesManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, applicationName);
    }

    /**
     * Constructor to init a {@link GmailMessagesManager}
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
    public GmailMessagesManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailMessagesManager}
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
    public GmailMessagesManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailMessagesManager}
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
    public GmailMessagesManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    public boolean batchDelete(String[] idsToDelete) {
        return batchDelete(stream(idsToDelete).toList());
    }

    public boolean batchDelete(Collection<String> idsToDelete) {
        try {
            messages.batchDelete(userId, new BatchDeleteMessagesRequest().setIds(idsToDelete.stream().toList())).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean batchModify(String[] idsToModify, String[] labelIdsToAdd, String[] labelIdsToRemove) {
        return batchModify(stream(idsToModify).toList(), stream(labelIdsToAdd).toList(), stream(labelIdsToRemove).toList());
    }

    public boolean batchModify(Collection<String> idsToModify, Collection<String> labelIdsToAdd,
                               Collection<String> labelIdsToRemove) {
        try {
            messages.batchModify(userId, new BatchModifyMessagesRequest().setIds(idsToModify.stream().toList())
                    .setAddLabelIds(labelIdsToAdd.stream().toList())
                    .setRemoveLabelIds(labelIdsToRemove.stream().toList())).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean deleteMessage(Message messageToDelete) {
        return deleteMessage(messageToDelete.getId());
    }

    public boolean deleteMessage(String messageIdToDelete) {
        try {
            messages.delete(userId, messageIdToDelete).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Message getMessage(String messageId) throws IOException {
        return getMessage(messageId, LIBRARY_OBJECT);
    }

    public <T> T getMessage(String messageId, ReturnFormat format) throws IOException {
        return getMessage(messages.get(userId, messageId).execute(), format);
    }

    public Message getMessage(String messageId, ResponseFormat responseFormat) throws IOException {
        return getMessage(messageId, responseFormat, LIBRARY_OBJECT);
    }

    public <T> T getMessage(String messageId, ResponseFormat responseFormat, ReturnFormat format) throws IOException {
        return getMessage(messages.get(userId, messageId).setFormat(responseFormat.toString()).execute(), format);
    }

    public Message getMessage(String messageId, String[] metadataHeaders) throws IOException {
        return getMessage(messageId, stream(metadataHeaders).toList(), LIBRARY_OBJECT);
    }

    public <T> T getMessage(String messageId, String[] metadataHeaders, ReturnFormat format) throws IOException {
        return getMessage(messageId, stream(metadataHeaders).toList(), format);
    }

    public Message getMessage(String messageId, Collection<String> metadataHeaders) throws IOException {
        return getMessage(messageId, metadataHeaders, LIBRARY_OBJECT);
    }

    public <T> T getMessage(String messageId, Collection<String> metadataHeaders, ReturnFormat format) throws IOException {
        return getMessage(messages.get(userId, messageId).setFormat(METADATA_FORMAT.toString())
                .setMetadataHeaders(metadataHeaders.stream().toList())
                .execute(), format);
    }

    private <T> T getMessage(com.google.api.services.gmail.model.Message message, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(message);
            case LIBRARY_OBJECT:
                return (T) new Message(new JSONObject(message));
            default:
                return (T) message.toString();
        }
    }

    public Message importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                 boolean processForCalendar, boolean deleted) throws Exception {
        return importMessage(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                LIBRARY_OBJECT);
    }

    public <T> T importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                               boolean processForCalendar, boolean deleted, ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    public Message importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                 boolean processForCalendar, boolean deleted,
                                 InternalDateSource internalDateSource) throws Exception {
        return importMessage(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                internalDateSource, LIBRARY_OBJECT);
    }

    public <T> T importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                               boolean processForCalendar, boolean deleted, InternalDateSource internalDateSource,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .setInternalDateSource(internalDateSource.toString())
                .execute(), format);
    }

    public Message importMessageWithFile(String toEmailAddress, String subject, String contentMessage,
                                         boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                         File file) throws Exception {
        return importMessageWithFile(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                file, LIBRARY_OBJECT);
    }

    public <T> T importMessageWithFile(String toEmailAddress, String subject, String contentMessage,
                                       boolean neverMarkSpam, boolean processForCalendar, boolean deleted, File file,
                                       ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createSimpleMessageWithFile(toEmailAddress, subject,
                        contentMessage, file, TEXT_PLAIN_MIME_TYPE))
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    public Message importMessageWithFile(String toEmailAddress, String subject, String contentMessage,
                                         boolean neverMarkSpam, boolean processForCalendar, boolean deleted, File file,
                                         InternalDateSource internalDateSource) throws Exception {
        return importMessageWithFile(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                file, internalDateSource, LIBRARY_OBJECT);
    }

    public <T> T importMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                       boolean processForCalendar, boolean deleted, File file,
                                       InternalDateSource internalDateSource, ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createSimpleMessageWithFile(toEmailAddress, subject,
                        contentMessage, file, TEXT_PLAIN_MIME_TYPE))
                .setInternalDateSource(internalDateSource.toString())
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                          boolean processForCalendar, boolean deleted, File[] files) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files, LIBRARY_OBJECT);
    }

    public <T> T importMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                        boolean processForCalendar, boolean deleted, File[] files,
                                        ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createMessageWithFiles(toEmailAddress, subject, contentMessage,
                        files, TEXT_PLAIN_MIME_TYPE))
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                          boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                          File[] files, InternalDateSource internalDateSource) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files, internalDateSource, LIBRARY_OBJECT);
    }

    public <T> T importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                        boolean neverMarkSpam, boolean processForCalendar, boolean deleted, File[] files,
                                        InternalDateSource internalDateSource, ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createMessageWithFiles(toEmailAddress, subject, contentMessage,
                        files, TEXT_PLAIN_MIME_TYPE))
                .setInternalDateSource(internalDateSource.toString())
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                          boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                          Collection<File> files) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), LIBRARY_OBJECT);
    }

    public <T> T importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                        boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                        Collection<File> files, ReturnFormat format) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), format);
    }

    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                          boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                          Collection<File> files, InternalDateSource internalDateSource) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), internalDateSource, LIBRARY_OBJECT);
    }

    public <T> T importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                        boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                        Collection<File> files, InternalDateSource internalDateSource,
                                        ReturnFormat format) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), internalDateSource, format);
    }

    public Message insertMessage(String toEmailAddress, String subject, String contentMessage,
                                 boolean deleted) throws Exception {
        return insertMessage(toEmailAddress, subject, contentMessage, deleted, LIBRARY_OBJECT);
    }

    public <T> T insertMessage(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setDeleted(deleted).execute(), format);
    }

    public Message insertMessage(String toEmailAddress, String subject, String contentMessage,
                                 boolean deleted, InternalDateSource internalDateSource) throws Exception {
        return insertMessage(toEmailAddress, subject, contentMessage, deleted, internalDateSource, LIBRARY_OBJECT);
    }

    public <T> T insertMessage(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                               InternalDateSource internalDateSource, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setDeleted(deleted).setInternalDateSource(internalDateSource.toString()).execute(), format);
    }

    public Message insertMessageWithFile(String toEmailAddress, String subject, String contentMessage,
                                         boolean deleted, File file) throws Exception {
        return insertMessageWithFile(toEmailAddress, subject, contentMessage, deleted, file, LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                       File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessageWithFile(toEmailAddress, subject, contentMessage, file,
                TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).execute(), format);
    }

    public Message insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                         InternalDateSource internalDateSource, File file) throws Exception {
        return insertMessageWithFile(toEmailAddress, subject, contentMessage, deleted, internalDateSource, file,
                LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                       InternalDateSource internalDateSource, File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessageWithFile(toEmailAddress, subject, contentMessage, file,
                        TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).setInternalDateSource(internalDateSource.toString()).execute(),
                format);
    }

    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          File[] files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, files, LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        File[] files, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createMessageWithFiles(toEmailAddress, subject, contentMessage, files,
                TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).execute(), format);
    }

    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          InternalDateSource internalDateSource, File[] files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, internalDateSource, files,
                LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        InternalDateSource internalDateSource, File[] files,
                                        ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createMessageWithFiles(toEmailAddress, subject, contentMessage, files,
                        TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).setInternalDateSource(internalDateSource.toString())
                .execute(), format);
    }

    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          Collection<File> files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, files.toArray(new File[0]),
                LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        Collection<File> files, ReturnFormat format) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, files.toArray(new File[0]), format);
    }

    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          InternalDateSource internalDateSource, Collection<File> files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, internalDateSource,
                files.toArray(new File[0]), LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        InternalDateSource internalDateSource, Collection<File> files,
                                        ReturnFormat format) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, internalDateSource,
                files.toArray(new File[0]), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash) throws IOException {
        return getMessagesList(includeSpamTrash, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String pageToken) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken).execute(), format);
    }

    public Messages getMessagesList(String q, boolean includeSpamTrash) throws IOException {
        return getMessagesList(q, includeSpamTrash, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(String q, boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String[] labelIds, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String q, int maxResults) throws IOException {
        return getMessagesList(includeSpamTrash, q, maxResults, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String q, int maxResults,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken,
                                    String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                    String q) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, q, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                 String q, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds,
                                    String q) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, q, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String q) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, q, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String pageToken,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String q,
                                    String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String q, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String q,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String q, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, String[] labelIds, String q) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, q, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, String[] labelIds, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, Collection<String> labelIds, String q) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, q, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, Collection<String> labelIds, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                    String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                 String[] labelIds, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList())
                .execute(), format);
    }

    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                 Collection<String> labelIds, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList())
                .execute(), format);
    }

    private <T> T getMessagesList(ListMessagesResponse listMessagesResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(listMessagesResponse);
            case LIBRARY_OBJECT:
                return (T) new Messages(new JSONObject(listMessagesResponse));
            default:
                return (T) listMessagesResponse.toString();
        }
    }

    public Message modifyAddLabelsIds(String messageId, String[] addLabelIds) throws IOException {
        return modifyAddLabelsIds(messageId, addLabelIds, LIBRARY_OBJECT);
    }

    public <T> T modifyAddLabelsIds(String messageId, String[] addLabelIds, ReturnFormat format) throws IOException {
        return modify(messages.modify(userId, messageId, new ModifyMessageRequest()
                        .setAddLabelIds(stream(addLabelIds).toList()).setRemoveLabelIds(null))
                .execute(), format);
    }

    public Message modifyAddLabelsIds(String messageId, Collection<String> addLabelIds) throws IOException {
        return modifyAddLabelsIds(messageId, addLabelIds, LIBRARY_OBJECT);
    }

    public <T> T modifyAddLabelsIds(String messageId, Collection<String> addLabelIds,
                                    ReturnFormat format) throws IOException {
        return modify(messages.modify(userId, messageId, new ModifyMessageRequest()
                        .setAddLabelIds(addLabelIds.stream().toList()).setRemoveLabelIds(null))
                .execute(), format);
    }

    public Message modifyRemoveLabelsIds(String messageId, String[] removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(messageId, removeLabelIds, LIBRARY_OBJECT);
    }

    public <T> T modifyRemoveLabelsIds(String messageId, String[] removeLabelIds,
                                       ReturnFormat format) throws IOException {
        return modify(messages.modify(userId, messageId, new ModifyMessageRequest().setAddLabelIds(null)
                        .setRemoveLabelIds(stream(removeLabelIds).toList()))
                .execute(), format);
    }

    public Message modifyRemoveLabelsIds(String messageId, Collection<String> removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(messageId, removeLabelIds, LIBRARY_OBJECT);
    }

    public <T> T modifyRemoveLabelsIds(String messageId, Collection<String> removeLabelIds,
                                       ReturnFormat format) throws IOException {
        return modify(messages.modify(userId, messageId, new ModifyMessageRequest().setAddLabelIds(null)
                        .setRemoveLabelIds(removeLabelIds.stream().toList()))
                .execute(), format);
    }

    public Message modify(String messageId, String[] addLabelIds, String[] removeLabelIds) throws IOException {
        return modify(messageId, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    public <T> T modify(String messageId, String[] addLabelIds, String[] removeLabelIds,
                        ReturnFormat format) throws IOException {
        return modify(messages.modify(userId, messageId, new ModifyMessageRequest()
                        .setAddLabelIds(stream(addLabelIds).toList())
                        .setRemoveLabelIds(stream(removeLabelIds).toList()))
                .execute(), format);
    }

    public Message modify(String messageId, Collection<String> addLabelIds,
                          Collection<String> removeLabelIds) throws IOException {
        return modify(messageId, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    public <T> T modify(String messageId, Collection<String> addLabelIds, Collection<String> removeLabelIds,
                        ReturnFormat format) throws IOException {
        return modify(messages.modify(userId, messageId, new ModifyMessageRequest()
                        .setAddLabelIds(addLabelIds.stream().toList())
                        .setRemoveLabelIds(removeLabelIds.stream().toList()))
                .execute(), format);
    }

    private <T> T modify(com.google.api.services.gmail.model.Message message, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(message);
            case LIBRARY_OBJECT:
                return (T) new Message(new JSONObject(message));
            default:
                return (T) message.toString();
        }
    }

    public Message send(String toEmailAddress, String subject, String messageText) throws Exception {
        return send(toEmailAddress, subject, messageText, LIBRARY_OBJECT);
    }

    public <T> T send(String toEmailAddress, String subject, String messageText, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createSimpleMessage(toEmailAddress, subject, messageText)).execute(),
                format);
    }

    public Message sendWithCc(String toEmailAddress, String subject, String Cc, String messageText) throws Exception {
        return sendWithCc(toEmailAddress, subject, Cc, messageText, LIBRARY_OBJECT);
    }

    public <T> T sendWithCc(String toEmailAddress, String subject, String Cc, String messageText,
                            ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessage(toEmailAddress, subject, Cc, messageText)).execute(),
                format);
    }

    public Message sendWithMultipleCc(String toEmailAddress, String subject, String messageText,
                                      String... Cc) throws Exception {
        return sendWithMultipleCc(toEmailAddress, subject, messageText, LIBRARY_OBJECT, Cc);
    }

    public <T> T sendWithMultipleCc(String toEmailAddress, String subject, String messageText,
                                    ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessage(toEmailAddress, subject, Arrays.toString(Cc),
                messageText)).execute(), format);
    }

    public Message sendWithBcc(String toEmailAddress, String subject, String Bcc, String messageText) throws Exception {
        return sendWithBcc(toEmailAddress, subject, Bcc, messageText, LIBRARY_OBJECT);
    }

    public <T> T sendWithBcc(String toEmailAddress, String subject, String Bcc, String messageText,
                             ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessage(toEmailAddress, subject, Bcc, messageText)).execute(),
                format);
    }

    public Message sendWithMultipleBcc(String toEmailAddress, String subject, String messageText,
                                       String... Bcc) throws Exception {
        return sendWithMultipleBcc(toEmailAddress, subject, messageText, LIBRARY_OBJECT, Bcc);
    }

    public <T> T sendWithMultipleBcc(String toEmailAddress, String subject, String messageText,
                                     ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessage(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText)).execute(), format);
    }

    public Message sendCompleteMessage(String toEmailAddress, String subject, String Cc, String Bcc,
                                       String messageText) throws Exception {
        return sendCompleteMessage(toEmailAddress, subject, Cc, Bcc, messageText, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessage(String toEmailAddress, String subject, String Cc, String Bcc, String messageText,
                                     ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessage(toEmailAddress, subject, Cc, Bcc,
                messageText)).execute(), format);
    }

    public Message sendCompleteMessage(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                       String messageText) throws Exception {
        return sendCompleteMessage(toEmailAddress, subject, Cc, Bcc, messageText, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessage(String toEmailAddress, String subject, String[] Cc, String[] Bcc, String messageText,
                                     ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessage(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText)).execute(), format);
    }

    public Message sendCompleteMessage(String toEmailAddress, String subject, Collection<String> Cc,
                                       Collection<String> Bcc, String messageText) throws Exception {
        return sendCompleteMessage(toEmailAddress, subject, Cc, Bcc, messageText, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessage(String toEmailAddress, String subject, Collection<String> Cc,
                                     Collection<String> Bcc, String messageText, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessage(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText)).execute(), format);
    }

    public Message sendWithFile(String toEmailAddress, String subject, String messageText, File file) throws Exception {
        return sendWithFile(toEmailAddress, subject, messageText, file, LIBRARY_OBJECT);
    }

    public <T> T sendWithFile(String toEmailAddress, String subject, String messageText, File file,
                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createSimpleMessageWithFile(toEmailAddress, subject, messageText,
                file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCcWithFile(String toEmailAddress, String subject, String Cc, String messageText,
                                  File file) throws Exception {
        return sendCcWithFile(toEmailAddress, subject, Cc, messageText, file, LIBRARY_OBJECT);
    }

    public <T> T sendCcWithFile(String toEmailAddress, String subject, String Cc, String messageText, File file,
                                ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFile(toEmailAddress, subject, Cc, messageText,
                file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendMultipleCcWithFile(String toEmailAddress, String subject, String messageText, File file,
                                          String... Cc) throws Exception {
        return sendMultipleCcWithFile(toEmailAddress, subject, messageText, file, LIBRARY_OBJECT, Cc);
    }

    public <T> T sendMultipleCcWithFile(String toEmailAddress, String subject, String messageText, File file,
                                        ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendBccWithFile(String toEmailAddress, String subject, String Bcc, String messageText,
                                   File file) throws Exception {
        return sendBccWithFile(toEmailAddress, subject, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    public <T> T sendBccWithFile(String toEmailAddress, String subject, String Bcc, String messageText, File file,
                                 ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFile(toEmailAddress, subject, Bcc, messageText,
                file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendMultipleBccWithFile(String toEmailAddress, String subject, String messageText, File file,
                                           String... Bcc) throws Exception {
        return sendMultipleBccWithFile(toEmailAddress, subject, messageText, file, LIBRARY_OBJECT, Bcc);
    }

    public <T> T sendMultipleBccWithFile(String toEmailAddress, String subject, String messageText, File file,
                                         ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFile(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, String Cc, String Bcc,
                                               String messageText, File file) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, String Cc, String Bcc,
                                             String messageText, File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc,
                messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                               String messageText, File file) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                             String messageText, File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, Collection<String> Cc,
                                               Collection<String> Bcc, String messageText, File file) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, Collection<String> Cc,
                                             Collection<String> Bcc, String messageText, File file,
                                             ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, file,
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendWithFiles(String toEmailAddress, String subject, String messageText, File[] files) throws Exception {
        return sendWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createMessageWithFiles(toEmailAddress, subject, messageText,
                files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                   File[] files) throws Exception {
        return sendCcWithFiles(toEmailAddress, subject, Cc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText, File[] files,
                                 ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Cc, messageText,
                files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                           String... Cc) throws Exception {
        return sendMultipleCcWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Cc);
    }

    public <T> T sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                         ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                    File[] files) throws Exception {
        return sendBccWithFiles(toEmailAddress, subject, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText, File[] files,
                                  ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Bcc, messageText,
                files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                            String... Bcc) throws Exception {
        return sendMultipleBccWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Bcc);
    }

    public <T> T sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                          ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                                String messageText, File[] files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                              String messageText, File[] files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject, Cc, Bcc,
                messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                                String messageText, File[] files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                              String messageText, File[] files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                                Collection<String> Bcc, String messageText, File[] files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                              Collection<String> Bcc, String messageText, File[] files,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, files,
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files) throws Exception {
        return sendWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createMessageWithFiles(toEmailAddress, subject, messageText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                   Collection<File> files) throws Exception {
        return sendCcWithFiles(toEmailAddress, subject, Cc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                 Collection<File> files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Cc, messageText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText,
                                           Collection<File> files, String... Cc) throws Exception {
        return sendMultipleCcWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Cc);
    }

    public <T> T sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText,
                                         Collection<File> files, ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                    Collection<File> files) throws Exception {
        return sendBccWithFiles(toEmailAddress, subject, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                  Collection<File> files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Bcc, messageText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText,
                                            Collection<File> files, String... Bcc) throws Exception {
        return sendMultipleBccWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Bcc);
    }

    public <T> T sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText,
                                          Collection<File> files, ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                                String messageText, Collection<File> files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                              String messageText, Collection<File> files,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject, Cc, Bcc,
                messageText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                                String messageText, Collection<File> files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                              String messageText, Collection<File> files,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                                Collection<String> Bcc, String messageText,
                                                Collection<File> files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                              Collection<String> Bcc, String messageText, Collection<File> files,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    public Message trash(String messageIdToTrash) throws IOException {
        return trash(messageIdToTrash, LIBRARY_OBJECT);
    }

    public <T> T trash(String messageIdToTrash, ReturnFormat format) throws IOException {
        return returnMessage(messages.trash(userId, messageIdToTrash).execute(), format);
    }

    public Message untrash(String messageIdToUntrash) throws IOException {
        return untrash(messageIdToUntrash, LIBRARY_OBJECT);
    }

    public <T> T untrash(String messageIdToUntrash, ReturnFormat format) throws IOException {
        return returnMessage(messages.untrash(userId, messageIdToUntrash).execute(), format);
    }

    private <T> T returnMessage(com.google.api.services.gmail.model.Message message, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(message);
            case LIBRARY_OBJECT:
                return (T) new Message(new JSONObject(message));
            default:
                return (T) message.toString();
        }
    }

    public enum InternalDateSource {

        receivedTime("receivedTime"),
        dateHeader("dateHeader");

        private final String internalDataSource;

        InternalDateSource(final String internalDataSource) {
            this.internalDataSource = internalDataSource;
        }

        @Override
        public String toString() {
            return internalDataSource;
        }

    }

}
