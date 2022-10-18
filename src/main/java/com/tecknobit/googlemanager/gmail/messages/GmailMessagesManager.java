package com.tecknobit.googlemanager.gmail.messages;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.BatchDeleteMessagesRequest;
import com.google.api.services.gmail.model.BatchModifyMessagesRequest;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
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
        return returnMessage(messages.gmailImport(userId, createMessage(toEmailAddress, subject, contentMessage))
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
        return returnMessage(messages.gmailImport(userId, createMessage(toEmailAddress, subject, contentMessage))
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
        return returnMessage(messages.gmailImport(userId, createMessageWithFile(toEmailAddress, subject, contentMessage,
                        file, TEXT_PLAIN_MIME_TYPE))
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
        return returnMessage(messages.gmailImport(userId, createMessageWithFile(toEmailAddress, subject, contentMessage,
                        file, TEXT_PLAIN_MIME_TYPE))
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
        return returnMessage(messages.insert(userId, createMessage(toEmailAddress, subject, contentMessage))
                .setDeleted(deleted).execute(), format);
    }

    public Message insertMessage(String toEmailAddress, String subject, String contentMessage,
                                 boolean deleted, InternalDateSource internalDateSource) throws Exception {
        return insertMessage(toEmailAddress, subject, contentMessage, deleted, internalDateSource, LIBRARY_OBJECT);
    }

    public <T> T insertMessage(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                               InternalDateSource internalDateSource, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createMessage(toEmailAddress, subject, contentMessage))
                .setDeleted(deleted).setInternalDateSource(internalDateSource.toString()).execute(), format);
    }

    public Message insertMessageWithFile(String toEmailAddress, String subject, String contentMessage,
                                         boolean deleted, File file) throws Exception {
        return insertMessageWithFile(toEmailAddress, subject, contentMessage, deleted, file, LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                       File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createMessageWithFile(toEmailAddress, subject, contentMessage, file,
                TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).execute(), format);
    }

    public Message insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                         InternalDateSource internalDateSource, File file) throws Exception {
        return insertMessageWithFile(toEmailAddress, subject, contentMessage, deleted, internalDateSource, file,
                LIBRARY_OBJECT);
    }

    public <T> T insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                       InternalDateSource internalDateSource, File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createMessageWithFile(toEmailAddress, subject, contentMessage, file,
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
