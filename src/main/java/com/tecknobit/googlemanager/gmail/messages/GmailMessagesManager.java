package com.tecknobit.googlemanager.gmail.messages;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.messages.records.Messages;
import com.tecknobit.googlemanager.gmail.records.Message;
import com.tecknobit.googlemanager.gmail.records.Message.MessageBody;
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
 * @implNote this class include also <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages.attachments/get">
 * users.messages.attachments.get</a> request, methods ->
 * <ul>
 *     <li>
 *         {@link #getAttachment(String, String)}
 *     </li>
 *     <li>
 *         {@link #getAttachment(String, String, ReturnFormat)}
 *     </li>
 * </ul>
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
     **/
    public GmailMessagesManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailMessagesManager} <br>
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
    public GmailMessagesManager() throws IOException {
        super();
    }

    /**
     * Method to delete many messages by message ID, provides no guarantees that messages were not already
     * deleted or even existed at all.
     *
     * @param idsToDelete: the IDs of the messages to delete in array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/batchDelete">
     * users.messages.batchDelete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean batchDelete(String[] idsToDelete) {
        try {
            messages.batchDelete(userId, new BatchDeleteMessagesRequest().setIds(stream(idsToDelete).toList())).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to delete many messages by message ID, provides no guarantees that messages were not already
     * deleted or even existed at all.
     *
     * @param idsToDelete: the IDs of the messages to delete in {@link Collection} of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/batchDelete">
     * users.messages.batchDelete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean batchDelete(Collection<String> idsToDelete) {
        return batchDelete(idsToDelete.toArray(new String[0]));
    }

    /**
     * Method to modifies the labels on the specified messages
     *
     * @param idsToModify:      the IDs of the messages to modify. There is a limit of 1000 ids per request in array of {@link String} format
     * @param labelIdsToAdd:    a list of label IDs to add to messages in array of {@link String} format
     * @param labelIdsToRemove: a list of label IDs to remove from messages in array of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/batchModify">
     * users.messages.batchModify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean batchModify(String[] idsToModify, String[] labelIdsToAdd, String[] labelIdsToRemove) {
        try {
            messages.batchModify(userId, new BatchModifyMessagesRequest().setIds(stream(idsToModify).toList())
                    .setAddLabelIds(stream(labelIdsToAdd).toList())
                    .setRemoveLabelIds(stream(labelIdsToRemove).toList())).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to modifies the labels on the specified messages
     *
     * @param idsToModify:      the IDs of the messages to modify. There is a limit of 1000 ids per request in {@link Collection} of {@link String} format
     * @param labelIdsToAdd:    a list of label IDs to add to messages in {@link Collection} of {@link String} format
     * @param labelIdsToRemove: a list of label IDs to remove from messages in {@link Collection} of {@link String} format
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/batchModify">
     * users.messages.batchModify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean batchModify(Collection<String> idsToModify, Collection<String> labelIdsToAdd,
                               Collection<String> labelIdsToRemove) {
        return batchModify(idsToModify.toArray(new String[0]), labelIdsToAdd.toArray(new String[0]),
                labelIdsToRemove.toArray(new String[0]));
    }

    /**
     * Method to immediately and permanently deletes the specified message. This operation cannot be undone.
     *
     * @param messageToDelete: message to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote prefer {@link #trash(String, ReturnFormat)} instead
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/delete">
     * users.messages.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteMessage(Message messageToDelete) {
        return deleteMessage(messageToDelete.getId());
    }

    /**
     * Method to immediately and permanently deletes the specified message. This operation cannot be undone.
     *
     * @param messageIdToDelete: id of the message to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @apiNote prefer {@link #trash(String, ReturnFormat)} instead
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/delete">
     * users.messages.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteMessage(String messageIdToDelete) {
        try {
            messages.delete(userId, messageIdToDelete).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get the specified message
     *
     * @param messageId: id of the message to get
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message getMessage(String messageId) throws IOException {
        return getMessage(messageId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId: id of the message to get
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessage(String messageId, ReturnFormat format) throws IOException {
        return returnMessage(messages.get(userId, messageId).execute(), format);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:      id of the message to get
     * @param responseFormat: the format to return the message in -> constants available at {@link ResponseFormat}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message getMessage(String messageId, ResponseFormat responseFormat) throws IOException {
        return getMessage(messageId, responseFormat, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:      id of the message to get
     * @param responseFormat: the format to return the message in -> constants available at {@link ResponseFormat}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessage(String messageId, ResponseFormat responseFormat, ReturnFormat format) throws IOException {
        return returnMessage(messages.get(userId, messageId).setFormat(responseFormat.toString()).execute(), format);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in array of {@link String} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message getMessage(String messageId, String[] metadataHeaders) throws IOException {
        return getMessage(messageId, metadataHeaders, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in array of {@link String} format
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessage(String messageId, String[] metadataHeaders, ReturnFormat format) throws IOException {
        return returnMessage(messages.get(userId, messageId).setFormat(METADATA_FORMAT.toString())
                .setMetadataHeaders(stream(metadataHeaders).toList())
                .execute(), format);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in array of {@link String} format
     * @param responseFormat:  the format to return the message in -> constants available at {@link ResponseFormat}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message getMessage(String messageId, String[] metadataHeaders, ResponseFormat responseFormat) throws IOException {
        return getMessage(messageId, metadataHeaders, responseFormat, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in array of {@link String} format
     * @param responseFormat:  the format to return the message in -> constants available at {@link ResponseFormat}
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessage(String messageId, String[] metadataHeaders, ResponseFormat responseFormat,
                            ReturnFormat format) throws IOException {
        return returnMessage(messages.get(userId, messageId).setFormat(METADATA_FORMAT.toString())
                .setMetadataHeaders(stream(metadataHeaders).toList())
                .setFormat(responseFormat.toString())
                .execute(), format);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in {@link Collection} of {@link String} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message getMessage(String messageId, Collection<String> metadataHeaders) throws IOException {
        return getMessage(messageId, metadataHeaders, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in {@link Collection} of {@link String} format
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessage(String messageId, Collection<String> metadataHeaders, ReturnFormat format) throws IOException {
        return returnMessage(messages.get(userId, messageId).setFormat(METADATA_FORMAT.toString())
                .setMetadataHeaders(metadataHeaders.stream().toList())
                .execute(), format);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in {@link Collection} of {@link String} format
     * @param responseFormat:  the format to return the message in -> constants available at {@link ResponseFormat}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message getMessage(String messageId, Collection<String> metadataHeaders,
                              ResponseFormat responseFormat) throws IOException {
        return getMessage(messageId, metadataHeaders, responseFormat, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified message
     *
     * @param messageId:       id of the message to get
     * @param metadataHeaders: when given and format is {@code "METADATA"}, only include headers specified in {@link Collection} of {@link String} format
     * @param responseFormat:  the format to return the message in -> constants available at {@link ResponseFormat}
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/get">
     * users.messages.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessage(String messageId, Collection<String> metadataHeaders, ResponseFormat responseFormat,
                            ReturnFormat format) throws IOException {
        return returnMessage(messages.get(userId, messageId).setFormat(METADATA_FORMAT.toString())
                .setMetadataHeaders(metadataHeaders.stream().toList())
                .setFormat(responseFormat.toString())
                .execute(), format);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                 boolean processForCalendar, boolean deleted) throws Exception {
        return importMessage(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                               boolean processForCalendar, boolean deleted, ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                 boolean processForCalendar, boolean deleted,
                                 InternalDateSource internalDateSource) throws Exception {
        return importMessage(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                internalDateSource, LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T importMessage(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                               boolean processForCalendar, boolean deleted, InternalDateSource internalDateSource,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .setInternalDateSource(internalDateSource.name())
                .execute(), format);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with attachment
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param file:               attachment file
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessageWithFile(String toEmailAddress, String subject, String contentMessage,
                                         boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                         File file) throws Exception {
        return importMessageWithFile(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                file, LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with attachment
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param file:               attachment file
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with attachment
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param file:               attachment file
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                         boolean processForCalendar, boolean deleted, File file,
                                         InternalDateSource internalDateSource) throws Exception {
        return importMessageWithFile(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                file, internalDateSource, LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with attachment
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param file:               attachment file
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T importMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                       boolean processForCalendar, boolean deleted, File file,
                                       InternalDateSource internalDateSource, ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createSimpleMessageWithFile(toEmailAddress, subject,
                        contentMessage, file, TEXT_PLAIN_MIME_TYPE))
                .setInternalDateSource(internalDateSource.name())
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in array of {@link File} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean neverMarkSpam,
                                          boolean processForCalendar, boolean deleted, File[] files) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files, LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in array of {@link File} format
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
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

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in array of {@link File} format
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                          boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                          File[] files, InternalDateSource internalDateSource) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files, internalDateSource, LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in array of {@link File} format
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                        boolean neverMarkSpam, boolean processForCalendar, boolean deleted, File[] files,
                                        InternalDateSource internalDateSource, ReturnFormat format) throws Exception {
        return returnMessage(messages.gmailImport(userId, createMessageWithFiles(toEmailAddress, subject, contentMessage,
                        files, TEXT_PLAIN_MIME_TYPE))
                .setInternalDateSource(internalDateSource.name())
                .setNeverMarkSpam(neverMarkSpam)
                .setProcessForCalendar(processForCalendar)
                .setDeleted(deleted)
                .execute(), format);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in {@link Collection} of {@link File} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                          boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                          Collection<File> files) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in {@link Collection} of {@link File} format
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                        boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                        Collection<File> files, ReturnFormat format) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), format);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in {@link Collection} of {@link File} format
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                          boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                          Collection<File> files, InternalDateSource internalDateSource) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), internalDateSource, LIBRARY_OBJECT);
    }

    /**
     * Method to import a message into only this user's mailbox, with standard email delivery scanning and classification similar to receiving via {@code "SMTP"}
     * with different attachments
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param neverMarkSpam:      ignore the Gmail spam classifier decision and never mark this email as {@code "SPAM"} in the mailbox
     * @param processForCalendar: process calendar invites in the email and add any extracted meetings to the Google Calendar for this user
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param files:              attachments files in {@link Collection} of {@link File} format
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/import">
     * users.messages.import</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T importMessageWithFiles(String toEmailAddress, String subject, String contentMessage,
                                        boolean neverMarkSpam, boolean processForCalendar, boolean deleted,
                                        Collection<File> files, InternalDateSource internalDateSource,
                                        ReturnFormat format) throws Exception {
        return importMessageWithFiles(toEmailAddress, subject, contentMessage, neverMarkSpam, processForCalendar, deleted,
                files.toArray(new File[0]), internalDateSource, format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessage(String toEmailAddress, String subject, String contentMessage,
                                 boolean deleted) throws Exception {
        return insertMessage(toEmailAddress, subject, contentMessage, deleted, LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessage(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setDeleted(deleted).execute(), format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessage(String toEmailAddress, String subject, String contentMessage,
                                 boolean deleted, InternalDateSource internalDateSource) throws Exception {
        return insertMessage(toEmailAddress, subject, contentMessage, deleted, internalDateSource, LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessage(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                               InternalDateSource internalDateSource, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessage(toEmailAddress, subject, contentMessage))
                .setDeleted(deleted).setInternalDateSource(internalDateSource.name()).execute(), format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with an attachment. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @param file:           attachment file
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessageWithFile(String toEmailAddress, String subject, String contentMessage,
                                         boolean deleted, File file) throws Exception {
        return insertMessageWithFile(toEmailAddress, subject, contentMessage, deleted, file, LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with an attachment. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                       File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessageWithFile(toEmailAddress, subject, contentMessage, file,
                TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).execute(), format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with an attachment. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param file:               attachment file
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                         InternalDateSource internalDateSource, File file) throws Exception {
        return insertMessageWithFile(toEmailAddress, subject, contentMessage, deleted, internalDateSource, file,
                LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with an attachment. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param file:               attachment file
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessageWithFile(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                       InternalDateSource internalDateSource, File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createSimpleMessageWithFile(toEmailAddress, subject, contentMessage, file,
                        TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).setInternalDateSource(internalDateSource.name()).execute(),
                format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @param files:          attachments files in array of {@link File} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          File[] files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, files, LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @param files:          attachments files in array of {@link File} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        File[] files, ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createMessageWithFiles(toEmailAddress, subject, contentMessage, files,
                TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).execute(), format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param files:              attachments files in array of {@link File} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          InternalDateSource internalDateSource, File[] files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, internalDateSource, files,
                LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param files:              attachments files in array of {@link File} format
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        InternalDateSource internalDateSource, File[] files,
                                        ReturnFormat format) throws Exception {
        return returnMessage(messages.insert(userId, createMessageWithFiles(toEmailAddress, subject, contentMessage, files,
                        TEXT_PLAIN_MIME_TYPE)).setDeleted(deleted).setInternalDateSource(internalDateSource.name())
                .execute(), format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @param files:          attachments files in {@link Collection} of {@link File} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          Collection<File> files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, files.toArray(new File[0]),
                LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param contentMessage: content message
     * @param deleted:        mark the email as permanently deleted (not TRASH) and only visible
     * @param files:          attachments files in {@link Collection} of {@link File} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        Collection<File> files, ReturnFormat format) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, files.toArray(new File[0]), format);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param files:              attachments files in {@link Collection} of {@link File} format
     * @return message requested as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                          InternalDateSource internalDateSource, Collection<File> files) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, internalDateSource,
                files.toArray(new File[0]), LIBRARY_OBJECT);
    }

    /**
     * Method to directly insert a message into only this user's mailbox similar to {@code "IMAP APPEND"},
     * bypassing most scanning and classification with different attachments. Does not send a message
     *
     * @param toEmailAddress:     recipient of the message
     * @param subject:            subject of the message
     * @param contentMessage:     content message
     * @param deleted:            mark the email as permanently deleted (not TRASH) and only visible
     * @param internalDateSource: source for Gmail's internal date of the message -> constants available at {@link InternalDateSource}
     * @param files:              attachments files in {@link Collection} of {@link File} format
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/insert">
     * users.messages.insert</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T insertMessageWithFiles(String toEmailAddress, String subject, String contentMessage, boolean deleted,
                                        InternalDateSource internalDateSource, Collection<File> files,
                                        ReturnFormat format) throws Exception {
        return insertMessageWithFiles(toEmailAddress, subject, contentMessage, deleted, internalDateSource,
                files.toArray(new File[0]), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash) throws IOException {
        return getMessagesList(includeSpamTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String pageToken) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(String q, boolean includeSpamTrash) throws IOException {
        return getMessagesList(q, includeSpamTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(String q, boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String[] labelIds, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String q, int maxResults) throws IOException {
        return getMessagesList(includeSpamTrash, q, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String q, int maxResults,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken,
                                    String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                    String q) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String[] labelIds,
                                 String q, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds,
                                    String q) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, Collection<String> labelIds, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String q) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String pageToken,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String q,
                                    String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String q, String[] labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String pageToken, String q,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String pageToken, String q, Collection<String> labelIds,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, String[] labelIds, String q) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, String[] labelIds, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, Collection<String> labelIds, String q) throws IOException {
        return getMessagesList(includeSpamTrash, labelIds, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, Collection<String> labelIds, String q,
                                 ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList()).execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                    String[] labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return messages with labels that match all the specified label IDs in array of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                 String[] labelIds, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(stream(labelIds).toList())
                .execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return messages list requested as {@link Messages} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Messages getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                    Collection<String> labelIds) throws IOException {
        return getMessagesList(includeSpamTrash, maxResults, pageToken, q, labelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param includeSpamTrash: include messages from {@code "SPAM"} and {@code "TRASH"} in the results
     * @param maxResults:       maximum number of messages to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return messages matching the specified query. Supports the same query format as the Gmail
     * @param labelIds:         only return messages with labels that match all the specified label IDs in {@link Collection} of {@link String} format
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">
     * users.messages.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getMessagesList(boolean includeSpamTrash, int maxResults, String pageToken, String q,
                                 Collection<String> labelIds, ReturnFormat format) throws IOException {
        return getMessagesList(messages.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .setLabelIds(labelIds.stream().toList())
                .execute(), format);
    }

    /**
     * Method to get list of the messages in the user's mailbox
     *
     * @param listMessagesResponse: list obtained from Google's response
     * @param format:               return type formatter -> {@link ReturnFormat}
     * @return messages list as {@code "format"} defines
     **/
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

    /**
     * Method to modify only the add labels on the specified message
     *
     * @param messageId:   the ID of the message to modify
     * @param addLabelIds: list of IDs of labels to add to this message. You can add up to 100 labels with each update in array of {@link String} format
     * @return message modified as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message modifyAddLabelsIds(String messageId, String[] addLabelIds) throws IOException {
        return modifyAddLabelsIds(messageId, addLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the add labels on the specified message
     *
     * @param messageId:   the ID of the message to modify
     * @param addLabelIds: list of IDs of labels to add to this message. You can add up to 100 labels with each update in array of {@link String} format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return message modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T modifyAddLabelsIds(String messageId, String[] addLabelIds, ReturnFormat format) throws IOException {
        return returnMessage(messages.modify(userId, messageId, new ModifyMessageRequest()
                .setAddLabelIds(stream(addLabelIds).toList()).setRemoveLabelIds(null)).execute(), format);
    }

    /**
     * Method to modify only the add labels on the specified message
     *
     * @param messageId:   the ID of the message to modify
     * @param addLabelIds: list of IDs of labels to add to this message. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @return message modified as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message modifyAddLabelsIds(String messageId, Collection<String> addLabelIds) throws IOException {
        return modifyAddLabelsIds(messageId, addLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the add labels on the specified message
     *
     * @param messageId:   the ID of the message to modify
     * @param addLabelIds: list of IDs of labels to add to this message. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return message modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T modifyAddLabelsIds(String messageId, Collection<String> addLabelIds, ReturnFormat format) throws IOException {
        return returnMessage(messages.modify(userId, messageId, new ModifyMessageRequest()
                .setAddLabelIds(addLabelIds.stream().toList()).setRemoveLabelIds(null)).execute(), format);
    }

    /**
     * Method to modify only the remove labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in array of {@link String} format
     * @return message modified as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message modifyRemoveLabelsIds(String messageId, String[] removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(messageId, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the remove labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T modifyRemoveLabelsIds(String messageId, String[] removeLabelIds, ReturnFormat format) throws IOException {
        return returnMessage(messages.modify(userId, messageId, new ModifyMessageRequest().setAddLabelIds(null)
                .setRemoveLabelIds(stream(removeLabelIds).toList())).execute(), format);
    }

    /**
     * Method to modify only the remove labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @return message modified as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message modifyRemoveLabelsIds(String messageId, Collection<String> removeLabelIds) throws IOException {
        return modifyRemoveLabelsIds(messageId, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify only the remove labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T modifyRemoveLabelsIds(String messageId, Collection<String> removeLabelIds, ReturnFormat format) throws IOException {
        return returnMessage(messages.modify(userId, messageId, new ModifyMessageRequest().setAddLabelIds(null)
                .setRemoveLabelIds(removeLabelIds.stream().toList())).execute(), format);
    }

    /**
     * Method to modify the labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param addLabelIds:    list of IDs of labels to add to this message. You can add up to 100 labels with each update in array of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in array of {@link String} format
     * @return message modified as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message modify(String messageId, String[] addLabelIds, String[] removeLabelIds) throws IOException {
        return modify(messageId, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify the labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param addLabelIds:    list of IDs of labels to add to this message. You can add up to 100 labels with each update in array of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T modify(String messageId, String[] addLabelIds, String[] removeLabelIds, ReturnFormat format) throws IOException {
        return returnMessage(messages.modify(userId, messageId, new ModifyMessageRequest()
                .setAddLabelIds(stream(addLabelIds).toList())
                .setRemoveLabelIds(stream(removeLabelIds).toList())).execute(), format);
    }

    /**
     * Method to modify the labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param addLabelIds:    list of IDs of labels to add to this message. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in array of {@link String} format
     * @return message modified as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message modify(String messageId, Collection<String> addLabelIds, Collection<String> removeLabelIds) throws IOException {
        return modify(messageId, addLabelIds, removeLabelIds, LIBRARY_OBJECT);
    }

    /**
     * Method to modify the labels on the specified message
     *
     * @param messageId:      the ID of the message to modify
     * @param addLabelIds:    list of IDs of labels to add to this message. You can add up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param removeLabelIds: list IDs of labels to remove from this message. You can remove up to 100 labels with each update in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message modified as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/modify">
     * users.messages.modify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T modify(String messageId, Collection<String> addLabelIds, Collection<String> removeLabelIds,
                        ReturnFormat format) throws IOException {
        return returnMessage(messages.modify(userId, messageId, new ModifyMessageRequest()
                .setAddLabelIds(addLabelIds.stream().toList())
                .setRemoveLabelIds(removeLabelIds.stream().toList())).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message send(String toEmailAddress, String subject, String messageText) throws Exception {
        return send(toEmailAddress, subject, messageText, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T send(String toEmailAddress, String subject, String messageText, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createSimpleMessage(toEmailAddress, subject, messageText)).execute(),
                format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithCc(String toEmailAddress, String subject, String Cc, String messageText) throws Exception {
        return sendWithCc(toEmailAddress, subject, Cc, messageText, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithCc(String toEmailAddress, String subject, String Cc, String messageText,
                            ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessage(toEmailAddress, subject, Cc, messageText)).execute(),
                format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithMultipleCc(String toEmailAddress, String subject, String messageText,
                                      String... Cc) throws Exception {
        return sendWithMultipleCc(toEmailAddress, subject, messageText, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithMultipleCc(String toEmailAddress, String subject, String messageText,
                                    ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessage(toEmailAddress, subject, Arrays.toString(Cc),
                messageText)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithBcc(String toEmailAddress, String subject, String Bcc, String messageText) throws Exception {
        return sendWithBcc(toEmailAddress, subject, Bcc, messageText, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithBcc(String toEmailAddress, String subject, String Bcc, String messageText,
                             ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessage(toEmailAddress, subject, Bcc, messageText)).execute(),
                format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithMultipleBcc(String toEmailAddress, String subject, String messageText,
                                       String... Bcc) throws Exception {
        return sendWithMultipleBcc(toEmailAddress, subject, messageText, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithMultipleBcc(String toEmailAddress, String subject, String messageText,
                                     ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessage(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessage(String toEmailAddress, String subject, String Cc, String Bcc,
                                       String messageText) throws Exception {
        return sendCompleteMessage(toEmailAddress, subject, Cc, Bcc, messageText, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessage(String toEmailAddress, String subject, String Cc, String Bcc, String messageText,
                                     ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessage(toEmailAddress, subject, Cc, Bcc,
                messageText)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessage(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                       String messageText) throws Exception {
        return sendCompleteMessage(toEmailAddress, subject, Cc, Bcc, messageText, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessage(String toEmailAddress, String subject, String[] Cc, String[] Bcc, String messageText,
                                     ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessage(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessage(String toEmailAddress, String subject, Collection<String> Cc,
                                       Collection<String> Bcc, String messageText) throws Exception {
        return sendCompleteMessage(toEmailAddress, subject, Cc, Bcc, messageText, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"}
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessage(String toEmailAddress, String subject, Collection<String> Cc,
                                     Collection<String> Bcc, String messageText, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessage(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithFile(String toEmailAddress, String subject, String messageText, File file) throws Exception {
        return sendWithFile(toEmailAddress, subject, messageText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithFile(String toEmailAddress, String subject, String messageText, File file,
                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createSimpleMessageWithFile(toEmailAddress, subject, messageText,
                file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCcWithFile(String toEmailAddress, String subject, String Cc, String messageText,
                                  File file) throws Exception {
        return sendCcWithFile(toEmailAddress, subject, Cc, messageText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCcWithFile(String toEmailAddress, String subject, String Cc, String messageText, File file,
                                ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFile(toEmailAddress, subject, Cc, messageText,
                file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleCcWithFile(String toEmailAddress, String subject, String messageText, File file,
                                          String... Cc) throws Exception {
        return sendMultipleCcWithFile(toEmailAddress, subject, messageText, file, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleCcWithFile(String toEmailAddress, String subject, String messageText, File file,
                                        ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendBccWithFile(String toEmailAddress, String subject, String Bcc, String messageText,
                                   File file) throws Exception {
        return sendBccWithFile(toEmailAddress, subject, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendBccWithFile(String toEmailAddress, String subject, String Bcc, String messageText, File file,
                                 ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFile(toEmailAddress, subject, Bcc, messageText,
                file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleBccWithFile(String toEmailAddress, String subject, String messageText, File file,
                                           String... Bcc) throws Exception {
        return sendMultipleBccWithFile(toEmailAddress, subject, messageText, file, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleBccWithFile(String toEmailAddress, String subject, String messageText, File file,
                                         ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFile(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, String Cc, String Bcc,
                                               String messageText, File file) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, String Cc, String Bcc,
                                             String messageText, File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc,
                messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                               String messageText, File file) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                             String messageText, File file, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, file, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, Collection<String> Cc,
                                               Collection<String> Bcc, String messageText, File file) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, Collection<String> Cc,
                                             Collection<String> Bcc, String messageText, File file,
                                             ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, file,
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithFile(String toEmailAddress, String subject, String messageText, File file,
                                String mimeType) throws Exception {
        return sendWithFile(toEmailAddress, subject, messageText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithFile(String toEmailAddress, String subject, String messageText, File file, String mimeType,
                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createSimpleMessageWithFile(toEmailAddress, subject, messageText,
                file, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCcWithFile(String toEmailAddress, String subject, String Cc, String messageText,
                                  File file, String mimeType) throws Exception {
        return sendCcWithFile(toEmailAddress, subject, Cc, messageText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCcWithFile(String toEmailAddress, String subject, String Cc, String messageText, File file,
                                String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFile(toEmailAddress, subject, Cc, messageText,
                file, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleCcWithFile(String toEmailAddress, String subject, String messageText, File file,
                                          String mimeType, String... Cc) throws Exception {
        return sendMultipleCcWithFile(toEmailAddress, subject, messageText, file, mimeType, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleCcWithFile(String toEmailAddress, String subject, String messageText, File file,
                                        String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, file, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendBccWithFile(String toEmailAddress, String subject, String Bcc, String messageText,
                                   File file, String mimeType) throws Exception {
        return sendBccWithFile(toEmailAddress, subject, Bcc, messageText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendBccWithFile(String toEmailAddress, String subject, String Bcc, String messageText, File file,
                                 String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFile(toEmailAddress, subject, Bcc, messageText,
                file, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleBccWithFile(String toEmailAddress, String subject, String messageText, File file,
                                           String mimeType, String... Bcc) throws Exception {
        return sendMultipleBccWithFile(toEmailAddress, subject, messageText, file, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleBccWithFile(String toEmailAddress, String subject, String messageText, File file,
                                         String mimeType, ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFile(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, file, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, String Cc, String Bcc,
                                               String messageText, File file, String mimeType) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, String Cc, String Bcc, String messageText,
                                             File file, String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc,
                messageText, file, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                               String messageText, File file, String mimeType) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                             String messageText, File file, String mimeType,
                                             ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, file, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFile(String toEmailAddress, String subject, Collection<String> Cc,
                                               Collection<String> Bcc, String messageText, File file,
                                               String mimeType) throws Exception {
        return sendCompleteMessageWithFile(toEmailAddress, subject, Cc, Bcc, messageText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with an attachment
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param file:           attachment file
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFile(String toEmailAddress, String subject, Collection<String> Cc,
                                             Collection<String> Bcc, String messageText, File file, String mimeType,
                                             ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompleteMessageWithFile(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, file,
                mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithFiles(String toEmailAddress, String subject, String messageText, File[] files) throws Exception {
        return sendWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createMessageWithFiles(toEmailAddress, subject, messageText,
                files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                   File[] files) throws Exception {
        return sendCcWithFiles(toEmailAddress, subject, Cc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText, File[] files,
                                 ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Cc, messageText,
                files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                           String... Cc) throws Exception {
        return sendMultipleCcWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                         ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                    File[] files) throws Exception {
        return sendBccWithFiles(toEmailAddress, subject, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText, File[] files,
                                  ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Bcc, messageText,
                files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                            String... Bcc) throws Exception {
        return sendMultipleBccWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                          ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                                String messageText, File[] files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                              String messageText, File[] files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject, Cc, Bcc,
                messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                                String messageText, File[] files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                              String messageText, File[] files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, files, TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                                Collection<String> Bcc, String messageText, File[] files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                              Collection<String> Bcc, String messageText, File[] files,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, files,
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link File} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files) throws Exception {
        return sendWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link File} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files,
                               ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createMessageWithFiles(toEmailAddress, subject, messageText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                   Collection<File> files) throws Exception {
        return sendCcWithFiles(toEmailAddress, subject, Cc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                 Collection<File> files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Cc, messageText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText,
                                           Collection<File> files, String... Cc) throws Exception {
        return sendMultipleCcWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText,
                                         Collection<File> files, ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                    Collection<File> files) throws Exception {
        return sendBccWithFiles(toEmailAddress, subject, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                  Collection<File> files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Bcc, messageText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText,
                                            Collection<File> files, String... Bcc) throws Exception {
        return sendMultipleBccWithFiles(toEmailAddress, subject, messageText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText,
                                          Collection<File> files, ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                                String messageText, Collection<File> files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                              String messageText, Collection<File> files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject, Cc, Bcc,
                messageText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                                String messageText, Collection<File> files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                              String messageText, Collection<File> files, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                                Collection<String> Bcc, String messageText,
                                                Collection<File> files) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                              Collection<String> Bcc, String messageText, Collection<File> files,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                 String mimeType) throws Exception {
        return sendWithFiles(toEmailAddress, subject, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                               String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createMessageWithFiles(toEmailAddress, subject, messageText,
                files, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                   String mimeType, File[] files) throws Exception {
        return sendCcWithFiles(toEmailAddress, subject, Cc, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText, File[] files,
                                 String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Cc, messageText,
                files, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                           String mimeType, String... Cc) throws Exception {
        return sendMultipleCcWithFiles(toEmailAddress, subject, messageText, files, mimeType, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                         String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, files, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                    File[] files, String mimeType) throws Exception {
        return sendBccWithFiles(toEmailAddress, subject, Bcc, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText, File[] files,
                                  String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Bcc, messageText,
                files, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                            String mimeType, String... Bcc) throws Exception {
        return sendMultipleBccWithFiles(toEmailAddress, subject, messageText, files, mimeType, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, File[] files,
                                          String mimeType, ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, files, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc,
                                                String messageText, File[] files, String mimeType) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, mimeType,
                LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc, String messageText,
                                              File[] files, String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject, Cc, Bcc,
                messageText, files, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                                String messageText, File[] files, String mimeType) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, mimeType,
                LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                              String messageText, File[] files, String mimeType,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, files, mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                                Collection<String> Bcc, String messageText, File[] files,
                                                String mimeType) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, mimeType,
                LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in array of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                              Collection<String> Bcc, String messageText, File[] files, String mimeType,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, files,
                mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link File} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files,
                                 String mimeType) throws Exception {
        return sendWithFiles(toEmailAddress, subject, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link File} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files,
                               String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createMessageWithFiles(toEmailAddress, subject, messageText,
                files.toArray(new File[0]), mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                   Collection<File> files, String mimeType) throws Exception {
        return sendCcWithFiles(toEmailAddress, subject, Cc, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCcWithFiles(String toEmailAddress, String subject, String Cc, String messageText,
                                 Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Cc, messageText,
                files.toArray(new File[0]), mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText,
                                           Collection<File> files, String mimeType, String... Cc) throws Exception {
        return sendMultipleCcWithFiles(toEmailAddress, subject, messageText, files, mimeType, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Cc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             multiple carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleCcWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files,
                                         String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return returnMessage(messages.send(userId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                messageText, files.toArray(new File[0]), mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                    Collection<File> files, String mimeType) throws Exception {
        return sendBccWithFiles(toEmailAddress, subject, Bcc, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendBccWithFiles(String toEmailAddress, String subject, String Bcc, String messageText,
                                  Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Bcc, messageText,
                files.toArray(new File[0]), mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files,
                                            String mimeType, String... Bcc) throws Exception {
        return sendMultipleBccWithFiles(toEmailAddress, subject, messageText, files, mimeType, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            multiple blind carbon copy value
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendMultipleBccWithFiles(String toEmailAddress, String subject, String messageText, Collection<File> files,
                                          String mimeType, ReturnFormat format, String... Bcc) throws Exception {
        return returnMessage(messages.send(userId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                messageText, files.toArray(new File[0]), mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc, String messageText,
                                                Collection<File> files, String mimeType) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             carbon copy value
     * @param Bcc:            blind carbon copy value
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String Cc, String Bcc, String messageText,
                                              Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject, Cc, Bcc,
                messageText, files.toArray(new File[0]), mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                                String messageText, Collection<File> files, String mimeType) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in array of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in array of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, String[] Cc, String[] Bcc,
                                              String messageText, Collection<File> files, String mimeType,
                                              ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), Arrays.toString(Bcc), messageText, files.toArray(new File[0]),
                mimeType)).execute(), format);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return message sent as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                                Collection<String> Bcc, String messageText, Collection<File> files,
                                                String mimeType) throws Exception {
        return sendCompleteMessageWithFiles(toEmailAddress, subject, Cc, Bcc, messageText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to send the specified message to the recipients in the {@code "To"}, {@code "Cc"} and {@code "Bcc"} with different attachments
     *
     * @param toEmailAddress: recipient of the message
     * @param subject:        subject of the message
     * @param Cc:             multiple carbon copy value in {@link Collection} of {@link String} format
     * @param Bcc:            multiple blind carbon copy value in {@link Collection} of {@link String} format
     * @param messageText:    content message
     * @param files:          attachments files in {@link Collection} of {@link String} format
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return message sent as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/send">
     * users.messages.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendCompleteMessageWithFiles(String toEmailAddress, String subject, Collection<String> Cc,
                                              Collection<String> Bcc, String messageText, Collection<File> files,
                                              String mimeType, ReturnFormat format) throws Exception {
        return returnMessage(messages.send(userId, createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), messageText, files.toArray(new File[0]),
                mimeType)).execute(), format);
    }

    /**
     * Method to move the specified message to the trash
     *
     * @param messageIdToTrash: the ID of the message to Trash
     * @return message trashed as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/trash">
     * users.messages.trash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message trash(String messageIdToTrash) throws IOException {
        return trash(messageIdToTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to move the specified message to the trash
     *
     * @param messageIdToTrash: the ID of the message to Trash
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return message trashed as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/trash">
     * users.messages.trash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T trash(String messageIdToTrash, ReturnFormat format) throws IOException {
        return returnMessage(messages.trash(userId, messageIdToTrash).execute(), format);
    }

    /**
     * Method to remove the specified message from the trash
     *
     * @param messageIdToUntrash: the ID of the message to remove from Trash
     * @return message untrashed as {@link Message} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/untrash">
     * users.messages.untrash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message untrash(String messageIdToUntrash) throws IOException {
        return untrash(messageIdToUntrash, LIBRARY_OBJECT);
    }

    /**
     * Method to remove the specified message from the trash
     *
     * @param messageIdToUntrash: the ID of the message to remove from Trash
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return message untrashed as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/untrash">
     * users.messages.untrash</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T untrash(String messageIdToUntrash, ReturnFormat format) throws IOException {
        return returnMessage(messages.untrash(userId, messageIdToUntrash).execute(), format);
    }

    /**
     * Method to create a message object
     *
     * @param message: message obtained from Google's response
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return messages as {@code "format"} defines
     **/
    public static <T> T returnMessage(com.google.api.services.gmail.model.Message message, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(message);
            case LIBRARY_OBJECT:
                return (T) new Message(new JSONObject(message));
            default:
                return (T) message.toString();
        }
    }

    /**
     * Method to get the specified message attachment
     *
     * @param messageId:    the ID of the message containing the attachment
     * @param attachmentId: the ID of the attachment
     * @return specified message attachment as {@link MessageBody} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages.attachments/get">
     * users.messages.attachments.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public MessageBody getAttachment(String messageId, String attachmentId) throws IOException {
        return getAttachment(messageId, attachmentId, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified message attachment
     *
     * @param messageId:    the ID of the message containing the attachment
     * @param attachmentId: the ID of the attachment
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return specified message attachment as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages.attachments/get">
     * users.messages.attachments.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getAttachment(String messageId, String attachmentId, ReturnFormat format) throws IOException {
        MessagePartBody messagePartBody = messages.attachments().get(userId, messageId, attachmentId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(messagePartBody);
            case LIBRARY_OBJECT:
                return (T) new MessageBody(new JSONObject(messagePartBody));
            default:
                return (T) messagePartBody.toString();
        }
    }

    /**
     * {@code InternalDateSource} the list of internal date source available
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/InternalDateSource">internal date source</a>
     **/
    public enum InternalDateSource {

        /**
         * {@code receivedTime} internal message date set to current time when received by Gmail
         **/
        receivedTime,

        /**
         * {@code dateHeader} internal message time based on 'Date' header in email, when valid
         **/
        dateHeader

    }

}
