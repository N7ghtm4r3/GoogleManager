package com.tecknobit.googlemanager.gmail.drafts;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListDraftsResponse;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.drafts.records.Draft;
import com.tecknobit.googlemanager.gmail.drafts.records.Drafts;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.googlemanager.gmail.GmailManager.ResponseFormat.FULL_FORMAT;
import static com.tecknobit.googlemanager.gmail.records.Message.Header.SUBJECT;
import static com.tecknobit.googlemanager.gmail.records.Message.Header.To;

/**
 * The {@code GmailDraftsManager} class is useful to manage all Gmail's drafts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts">Gmail drafts</a>
 **/
// TODO: 15/10/2022 WHEN ATTACHMENTS ENDPOINT IS CREATED IMPLEMENT UPDATE WITH AUTO FILE FETCHER
public class GmailDraftsManager extends GmailManager {

    /**
     * {@code drafts} is the instance for {@link Gmail.Users.Drafts}'s service
     **/
    private final Gmail.Users.Drafts drafts = gmail.drafts();

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
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, applicationName);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
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
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
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
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
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
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @return draft response as {@link Draft} custom object
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft createDraft(String toEmailAddress, String subject, String emailText) throws Exception {
        return createDraft(toEmailAddress, subject, emailText, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDraft(String toEmailAddress, String subject, String emailText, ReturnFormat format) throws Exception {
        return createDraft(createMessage(toEmailAddress, subject, emailText), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @return draft response as {@link Draft} custom object
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText, File file) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     ReturnFormat format) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, TEXT_PLAIN_MIME_TYPE, format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file, String mimeType,
                                     ReturnFormat format) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, mimeType, format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText,
                                      File[] files) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, TEXT_PLAIN_MIME_TYPE, format,
                true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText,
                                      Collection<File> files) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE,
                format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, String mimeType,
                                      Collection<File> files) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when request have been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files.toArray(new File[0]), mimeType, format,
                true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress:     recipient of the email message
     * @param subject:            subject of the email message
     * @param emailText:          email content message
     * @param file:               attachment file to sent with draft
     * @param mimeType:           type of mime -> constants available at {@link GmailManager}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @param sendCreateResponse: flag to send or not request to create a new draft
     * @return draft as {@code "format"} defines
     **/
    private <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file, String mimeType,
                                      ReturnFormat format, boolean sendCreateResponse) throws Exception {
        return createDraft(createMessageWithFile(toEmailAddress, subject, emailText, file, mimeType), format,
                sendCreateResponse);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress:     recipient of the email message
     * @param subject:            subject of the email message
     * @param emailText:          email content message
     * @param files:              attachments files to sent with draft
     * @param mimeType:           type of mime -> constants available at {@link GmailManager}
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @param sendCreateResponse: flag to send or not request to create a new draft
     * @return draft as {@code "format"} defines
     **/
    private <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                       String mimeType, ReturnFormat format, boolean sendCreateResponse) throws Exception {
        return createDraft(createMessageWithFiles(toEmailAddress, subject, emailText, files, mimeType), format,
                sendCreateResponse);
    }

    /**
     * Method to create a draft
     *
     * @param message:            message created
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @param sendCreateResponse: flag to send or not request to create a new draft
     * @return draft as {@code "format"} defines
     **/
    private <T> T createDraft(com.google.api.services.gmail.model.Message message, ReturnFormat format,
                              boolean sendCreateResponse) throws Exception {
        com.google.api.services.gmail.model.Draft draft = new com.google.api.services.gmail.model.Draft().setMessage(message);
        if (sendCreateResponse) {
            draft = drafts.create(userId, draft).execute();
            switch (format) {
                case JSON:
                    return (T) new JSONObject(draft);
                case LIBRARY_OBJECT:
                    return (T) new Draft(new JSONObject(draft));
                default:
                    return (T) draft.toString();
            }
        }
        return (T) draft;
    }

    /**
     * Method to delete a draft
     *
     * @param draftId: identifier of the draft to delete
     * @return result of the deletion -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/delete">
     * users.drafts.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteDraft(String draftId) {
        try {
            drafts.delete(userId, draftId).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Method to get a draft
     *
     * @param draftId: identifier of the draft to get
     * @return draft requested as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/get">
     * users.drafts.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft getDraft(String draftId) throws IOException {
        return getDraft(draftId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a draft
     *
     * @param draftId: identifier of the draft to get
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/get">
     * users.drafts.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraft(String draftId, ReturnFormat format) throws IOException {
        return getDraft(draftId, null, format);
    }

    /**
     * Method to get a draft
     *
     * @param draftId:        identifier of the draft to get
     * @param responseFormat: format to receive as response to create the {@link Draft} object -> constants available at {@link GmailManager}
     * @return draft requested as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/get">
     * users.drafts.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft getDraft(String draftId, ResponseFormat responseFormat) throws IOException {
        return getDraft(draftId, responseFormat, LIBRARY_OBJECT);
    }

    /**
     * Method to get a draft
     *
     * @param draftId:        identifier of the draft to get
     * @param responseFormat: format to receive as response to create the {@link Draft} object -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/get">
     * users.drafts.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraft(String draftId, ResponseFormat responseFormat, ReturnFormat format) throws IOException {
        Gmail.Users.Drafts.Get getDraft = drafts.get(userId, draftId);
        if (responseFormat != null)
            getDraft.setFormat(responseFormat.toString());
        com.google.api.services.gmail.model.Draft draft = getDraft.execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(draft);
            case LIBRARY_OBJECT:
                return (T) new Draft(new JSONObject(draft));
            default:
                return (T) draft.toString();
        }
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(boolean includeSpamTrash) throws IOException {
        return getDraftsList(includeSpamTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash).execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(boolean includeSpamTrash, int maxResults) throws IOException {
        return getDraftsList(includeSpamTrash, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(boolean includeSpamTrash, String pageToken) throws IOException {
        return getDraftsList(includeSpamTrash, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(boolean includeSpamTrash, String pageToken, ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId)
                .setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(String q, boolean includeSpamTrash) throws IOException {
        return getDraftsList(q, includeSpamTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(String q, boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId)
                .setQ(q)
                .setIncludeSpamTrash(includeSpamTrash)
                .execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken) throws IOException {
        return getDraftsList(includeSpamTrash, maxResults, pageToken, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                               ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(boolean includeSpamTrash, String q, int maxResults) throws IOException {
        return getDraftsList(includeSpamTrash, q, maxResults, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(boolean includeSpamTrash, String q, int maxResults,
                               ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setMaxResults((long) maxResults)
                .execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(boolean includeSpamTrash, String pageToken, String q) throws IOException {
        return getDraftsList(includeSpamTrash, pageToken, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(boolean includeSpamTrash, String pageToken, String q,
                               ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @return drafts list requested as {@link Drafts} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Drafts getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                                String q) throws IOException {
        return getDraftsList(includeSpamTrash, maxResults, pageToken, q, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @param q:                Only return draft messages matching the specified query. Supports the same query format as the Gmail
     *                          search box. For example, "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                               String q, ReturnFormat format) throws IOException {
        return getDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .execute(), format);
    }

    /**
     * Method to create a drafts list
     *
     * @param drafts: list of drafts obtained from {@code "Google"} request
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return draft list as {@code "format"} defines
     **/
    private <T> T getDraftsList(ListDraftsResponse drafts, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONArray(drafts.getDrafts());
            case LIBRARY_OBJECT:
                return (T) new Drafts(new JSONObject(drafts.toString()));
            default:
                return (T) drafts.toString();
        }
    }

    /**
     * Method to send a draft
     *
     * @param draftId: identifier ot the draft to send
     * @return message of the response as {@link Message} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendDraft(String draftId) throws IOException {
        return sendDraft(draftId, LIBRARY_OBJECT);
    }

    /**
     * Method to send a draft
     *
     * @param draftId: identifier ot the draft to send
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendDraft(String draftId, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Draft draft = drafts.get(userId, draftId).execute();
        com.google.api.services.gmail.model.Message message = drafts.send(userId, draft).execute();
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
     * Method to send a draft
     *
     * @param draft: draft to send
     * @return message of the response as {@link Message} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Message sendDraft(Draft draft) throws IOException {
        return sendDraft(draft, LIBRARY_OBJECT);
    }

    /**
     * Method to send a draft
     *
     * @param draft:  draft to send
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T sendDraft(Draft draft, ReturnFormat format) throws IOException {
        return sendDraft(draft.getId(), format);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateDraft(String draftId, String toEmailAddress, String subject, String emailText) throws Exception {
        return updateDraft(draftId, toEmailAddress, subject, emailText, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateDraft(String draftId, String toEmailAddress, String subject, String emailText,
                             ReturnFormat format) throws Exception {
        return updateDraft(createDraft(createMessage(toEmailAddress, subject, emailText), null, false),
                draftId, format);
    }

    /**
     * Method to update only the to-email-address of a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateToEmailAddress(String draftId, String toEmailAddress, String emailText) throws Exception {
        return updateToEmailAddress(draftId, toEmailAddress, emailText, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the to-email-address of a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateToEmailAddress(String draftId, String toEmailAddress, String emailText,
                                      ReturnFormat format) throws Exception {
        return updateDraft(createDraft(createMessage(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText),
                null, false), draftId, format);
    }

    /**
     * Method to update only the subject of a draft
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateSubject(String draftId, String subject, String emailText) throws Exception {
        return updateSubject(draftId, subject, emailText, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the subject of a draft
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateSubject(String draftId, String subject, String emailText, ReturnFormat format) throws Exception {
        return updateDraft(createDraft(createMessage(getHeaderValue(draftId, To), subject, emailText), null, false),
                draftId, format);
    }

    /**
     * Method to update a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText,
                                     File file) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, subject, emailText, file, TEXT_PLAIN_MIME_TYPE, null,
                false), draftId, format);
    }

    /**
     * Method to update only the to-email-address of a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText,
                                              File file) throws Exception {
        return updateToEmailAddressWithFile(draftId, toEmailAddress, emailText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the to-email-address of a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText, File file,
                                              ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText,
                file, TEXT_PLAIN_MIME_TYPE, null, false), draftId, format);
    }

    /**
     * Method to update only the subject of a draft with an attachment
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param file:      file for the draft update (can also be the same old)
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateSubjectWithFile(String draftId, String subject, String emailText, File file) throws Exception {
        return updateSubjectWithFile(draftId, subject, emailText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the subject of a draft with an attachment
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param file:      file for the draft update (can also be the same old)
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateSubjectWithFile(String draftId, String subject, String emailText, File file,
                                       ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(getHeaderValue(draftId, To), subject, emailText, file, TEXT_PLAIN_MIME_TYPE,
                null, false), draftId, format);
    }

    /**
     * Method to update a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, subject, emailText, file, mimeType, null, false),
                draftId, format);
    }

    /**
     * Method to update only the to-email-address of a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText, File file,
                                              String mimeType) throws Exception {
        return updateToEmailAddressWithFile(draftId, toEmailAddress, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the to-email-address of a draft with an attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param file:           file for the draft update (can also be the same old)
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText, File file,
                                              String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText, file,
                mimeType, null, false), draftId, format);
    }

    /**
     * Method to update only the subject of a draft with an attachment
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param file:      file for the draft update (can also be the same old)
     * @param mimeType:  type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateSubjectWithFile(String draftId, String subject, String emailText, File file,
                                       String mimeType) throws Exception {
        return updateSubjectWithFile(draftId, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the subject of a draft with an attachment
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param file:      file for the draft update (can also be the same old)
     * @param mimeType:  type of mime -> constants available at {@link GmailManager}
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateSubjectWithFile(String draftId, String subject, String emailText, File file, String mimeType,
                                       ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(getHeaderValue(draftId, To), subject, emailText, file, mimeType, null,
                false), draftId, format);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, files, TEXT_PLAIN_MIME_TYPE, null,
                false), draftId, format);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText,
                                               File[] files) throws Exception {
        return updateToEmailAddressWithFiles(draftId, toEmailAddress, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText, File[] files,
                                               ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText, files,
                TEXT_PLAIN_MIME_TYPE, null, false), draftId, format);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as array of {@link File}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateSubjectWithFiles(String draftId, String subject, String emailText, File[] files) throws Exception {
        return updateSubjectWithFiles(draftId, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as array of {@link File}
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateSubjectWithFiles(String draftId, String subject, String emailText, File[] files,
                                        ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(getHeaderValue(draftId, To), subject, emailText, files,
                TEXT_PLAIN_MIME_TYPE, null, false), draftId, format);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files, String mimeType) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, null,
                false), draftId, format);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText, File[] files,
                                               String mimeType) throws Exception {
        return updateToEmailAddressWithFiles(draftId, toEmailAddress, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText, File[] files,
                                               String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText, files,
                mimeType, null, false), draftId, format);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as array of {@link File}
     * @param mimeType:  type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateSubjectWithFiles(String draftId, String subject, String emailText, File[] files,
                                        String mimeType) throws Exception {
        return updateSubjectWithFiles(draftId, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as array of {@link File}
     * @param mimeType:  type of mime -> constants available at {@link GmailManager}
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateSubjectWithFiles(String draftId, String subject, String emailText, File[] files, String mimeType,
                                        ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(getHeaderValue(draftId, To), subject, emailText, files, mimeType, null,
                false), draftId, format);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE, null, false), draftId, format);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText,
                                               Collection<File> files) throws Exception {
        return updateToEmailAddressWithFiles(draftId, toEmailAddress, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText,
                                               Collection<File> files, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE, null, false), draftId, format);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateSubjectWithFiles(String draftId, String subject, String emailText,
                                        Collection<File> files) throws Exception {
        return updateSubjectWithFiles(draftId, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateSubjectWithFiles(String draftId, String subject, String emailText, Collection<File> files,
                                        ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(getHeaderValue(draftId, To), subject, emailText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE, null, false), draftId, format);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param subject:        new subject for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, files.toArray(new File[0]), mimeType,
                null, false), draftId, format);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText,
                                               Collection<File> files, String mimeType) throws Exception {
        return updateToEmailAddressWithFiles(draftId, toEmailAddress, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the to-email-address of a draft with different attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: new to-email-address for the draft update
     * @param emailText:      email text for the draft update (can also be the same old)
     * @param files:          files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateToEmailAddressWithFiles(String draftId, String toEmailAddress, String emailText,
                                               Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText,
                files.toArray(new File[0]), mimeType, null, false), draftId, format);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param mimeType:  type of mime -> constants available at {@link GmailManager}
     * @return draft as {@link Draft} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Draft updateSubjectWithFiles(String draftId, String subject, String emailText,
                                        Collection<File> files, String mimeType) throws Exception {
        return updateSubjectWithFiles(draftId, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update only the subject of a draft with different attachments
     *
     * @param draftId:   identifier of the draft to update
     * @param subject:   new subject for the draft update
     * @param emailText: email text for the draft update (can also be the same old)
     * @param files:     files for the draft update (can also be the same olds) as {@link Collection} of {@link File}
     * @param mimeType:  type of mime -> constants available at {@link GmailManager}
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateSubjectWithFiles(String draftId, String subject, String emailText, Collection<File> files,
                                        String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(getHeaderValue(draftId, To), subject, emailText, files.toArray(new File[0]),
                mimeType, null, false), draftId, format);
    }

    /**
     * Method to get header from draft's headers
     *
     * @param draftId:           identifier of the draft to get header value
     * @param headerKeySearched: key of the header searched
     * @return header value as {@link String}
     **/
    private String getHeaderValue(String draftId, String headerKeySearched) throws IOException {
        com.google.api.services.gmail.model.Draft draft = drafts.get(userId, draftId)
                .setFormat(FULL_FORMAT.toString()).execute();
        for (MessagePartHeader messagePartHeader : draft.getMessage().getPayload().getHeaders())
            if (messagePartHeader.getName().equals(headerKeySearched))
                return messagePartHeader.getValue();
        return null;
    }

    /**
     * Method to update a draft
     * @param draftToReplace: draft object to replace
     * @param draftId: identifier of the draft to get header value
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     **/
    private <T> T updateDraft(com.google.api.services.gmail.model.Draft draftToReplace, String draftId,
                              ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Draft draftUpdated = drafts.update(userId, draftId,
                draftToReplace).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(draftUpdated);
            case LIBRARY_OBJECT:
                return (T) new Draft(new JSONObject(draftUpdated));
            default:
                return (T) draftUpdated.toString();
        }
    }

}
