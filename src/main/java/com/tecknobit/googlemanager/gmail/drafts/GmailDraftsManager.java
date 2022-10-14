package com.tecknobit.googlemanager.gmail.drafts;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListDraftsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.drafts.records.Draft;
import com.tecknobit.googlemanager.gmail.drafts.records.Drafts;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.googlemanager.gmail.drafts.records.Message.Header.SUBJECT;
import static com.tecknobit.googlemanager.gmail.drafts.records.Message.Header.To;

/**
 * The {@code GmailDraftsManager} class is useful to manage all Gmail's drafts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts">Gmail drafts</a>
 **/
public class GmailDraftsManager extends GmailManager {

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

    public Draft createDraft(String toEmailAddress, String subject, String emailText) throws Exception {
        return createDraft(toEmailAddress, subject, emailText, LIBRARY_OBJECT);
    }

    public <T> T createDraft(String toEmailAddress, String subject, String emailText, ReturnFormat format) throws Exception {
        MimeMessage email = createMimeMessage(toEmailAddress, subject);
        email.setText(emailText);
        return createDraft(email, format, true);
    }

    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText,
                                     File file) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, LIBRARY_OBJECT);
    }

    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     ReturnFormat format) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, TEXT_PLAIN_MIME_TYPE, file, format,
                true);
    }

    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText, String mimeType,
                                     File file) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file, String mimeType,
                                     ReturnFormat format) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, mimeType, file, format, true);
    }

    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText,
                                      File[] files) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, TEXT_PLAIN_MIME_TYPE, files, format,
                true);
    }

    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, String mimeType,
                                      File[] files) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, mimeType, files, format, true);
    }

    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText,
                                      Collection<File> files) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, TEXT_PLAIN_MIME_TYPE, files.toArray(new File[0]),
                format, true);
    }

    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, String mimeType,
                                      Collection<File> files) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, mimeType, files.toArray(new File[0]), format,
                true);
    }

    private MimeMessage createMimeMessage(String toEmailAddress, String subject) throws Exception {
        MimeMessage mime = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        mime.setFrom(new InternetAddress(userId));
        mime.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toEmailAddress));
        mime.setSubject(subject);
        return mime;
    }

    private <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, String mimeType,
                                      File file, ReturnFormat format, boolean sendCreateResponse) throws Exception {
        MimeMessage email = createMimeMessage(toEmailAddress, subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(emailText, mimeType);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
        mimeBodyPart.setFileName(file.getName());
        multipart.addBodyPart(mimeBodyPart);
        email.setContent(multipart);
        return createDraft(email, format, sendCreateResponse);
    }

    private <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, String mimeType,
                                       File[] files, ReturnFormat format, boolean sendCreateResponse) throws Exception {
        MimeMessage email = createMimeMessage(toEmailAddress, subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(emailText, mimeType);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        for (File file : files) {
            mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
            mimeBodyPart.setFileName(file.getName());
            multipart.addBodyPart(mimeBodyPart);
        }
        email.setContent(multipart);
        return createDraft(email, format, sendCreateResponse);
    }

    private <T> T createDraft(MimeMessage email, ReturnFormat format, boolean sendCreateResponse) throws Exception {
        com.google.api.services.gmail.model.Draft draft = new com.google.api.services.gmail.model.Draft();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        draft.setMessage(message);
        if (sendCreateResponse) {
            draft = gmail.drafts().create(userId, draft).execute();
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

    public boolean deleteDraft(String draftId) {
        try {
            gmail.drafts().delete(userId, draftId).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Draft getDraft(String draftId) throws IOException {
        return getDraft(draftId, LIBRARY_OBJECT);
    }

    public <T> T getDraft(String draftId, ReturnFormat format) throws IOException {
        return getDraft(draftId, null, format);
    }

    public Draft getDraft(String draftId, String responseFormat) throws IOException {
        return getDraft(draftId, responseFormat, LIBRARY_OBJECT);
    }

    public <T> T getDraft(String draftId, String responseFormat, ReturnFormat format) throws IOException {
        Gmail.Users.Drafts.Get getDraft = gmail.drafts().get(userId, draftId);
        if (responseFormat != null)
            getDraft.setFormat(responseFormat);
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

    public Drafts getDraftsList(boolean includeSpamTrash) throws IOException {
        return getDraftsList(includeSpamTrash, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId).setIncludeSpamTrash(includeSpamTrash).execute(), format);
    }

    public Drafts getDraftsList(boolean includeSpamTrash, int maxResults) throws IOException {
        return getDraftsList(includeSpamTrash, maxResults, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .execute(), format);
    }

    public Drafts getDraftsList(boolean includeSpamTrash, String pageToken) throws IOException {
        return getDraftsList(includeSpamTrash, pageToken, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(boolean includeSpamTrash, String pageToken, ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId)
                .setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .execute(), format);
    }

    public Drafts getDraftsList(String q, boolean includeSpamTrash) throws IOException {
        return getDraftsList(q, includeSpamTrash, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(String q, boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId)
                .setQ(q)
                .setIncludeSpamTrash(includeSpamTrash)
                .execute(), format);
    }

    public Drafts getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken) throws IOException {
        return getDraftsList(includeSpamTrash, maxResults, pageToken, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                               ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .execute(), format);
    }

    public Drafts getDraftsList(boolean includeSpamTrash, String q, int maxResults) throws IOException {
        return getDraftsList(includeSpamTrash, q, maxResults, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(boolean includeSpamTrash, String q, int maxResults,
                               ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setQ(q)
                .setMaxResults((long) maxResults)
                .execute(), format);
    }

    public Drafts getDraftsList(boolean includeSpamTrash, String pageToken, String q) throws IOException {
        return getDraftsList(includeSpamTrash, pageToken, q, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(boolean includeSpamTrash, String pageToken, String q,
                               ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setPageToken(pageToken)
                .setQ(q)
                .execute(), format);
    }

    public Drafts getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                                String q) throws IOException {
        return getDraftsList(includeSpamTrash, maxResults, pageToken, q, LIBRARY_OBJECT);
    }

    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                               String q, ReturnFormat format) throws IOException {
        return getDraftsList(gmail.drafts().list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .setPageToken(pageToken)
                .setQ(q)
                .execute(), format);
    }

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

    public com.tecknobit.googlemanager.gmail.drafts.records.Message sendDraft(String draftId) throws IOException {
        return sendDraft(draftId, LIBRARY_OBJECT);
    }

    public <T> T sendDraft(String draftId, ReturnFormat format) throws IOException {
        Gmail.Users.Drafts drafts = gmail.drafts();
        com.google.api.services.gmail.model.Draft draft = drafts.get(userId, draftId).execute();
        Message message = drafts.send(userId, draft).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(message);
            case LIBRARY_OBJECT:
                return (T) new com.tecknobit.googlemanager.gmail.drafts.records.Message(new JSONObject(message));
            default:
                return (T) message.toString();
        }
    }

    public com.tecknobit.googlemanager.gmail.drafts.records.Message sendDraft(Draft draft) throws IOException {
        return sendDraft(draft, LIBRARY_OBJECT);
    }

    public <T> T sendDraft(Draft draft, ReturnFormat format) throws IOException {
        return sendDraft(draft.getId(), format);
    }

    public Draft updateDraft(String draftId, String toEmailAddress, String subject, String emailText) throws Exception {
        return updateDraft(draftId, toEmailAddress, subject, emailText, LIBRARY_OBJECT);
    }

    public <T> T updateDraft(String draftId, String toEmailAddress, String subject, String emailText,
                             ReturnFormat format) throws Exception {
        MimeMessage mimeMessage = createMimeMessage(toEmailAddress, subject);
        mimeMessage.setText(emailText);
        return updateDraft(createDraft(mimeMessage, null, false), draftId, format);
    }

    public Draft updateToEmailAddress(String draftId, String toEmailAddress, String emailText) throws Exception {
        return updateToEmailAddress(draftId, toEmailAddress, emailText, LIBRARY_OBJECT);
    }

    public <T> T updateToEmailAddress(String draftId, String toEmailAddress, String emailText,
                                      ReturnFormat format) throws Exception {
        MimeMessage mimeMessage = createMimeMessage(toEmailAddress, getHeaderValue(draftId, SUBJECT));
        mimeMessage.setText(emailText);
        return updateDraft(createDraft(mimeMessage, null, false), draftId, format);
    }

    public Draft updateSubject(String draftId, String subject, String emailText) throws Exception {
        return updateSubject(draftId, subject, emailText, LIBRARY_OBJECT);
    }

    public <T> T updateSubject(String draftId, String subject, String emailText, ReturnFormat format) throws Exception {
        MimeMessage mimeMessage = createMimeMessage(getHeaderValue(draftId, To), subject);
        mimeMessage.setText(emailText);
        return updateDraft(createDraft(mimeMessage, null, false), draftId, format);
    }

    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText,
                                     File file) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, LIBRARY_OBJECT);
    }

    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, subject, emailText, TEXT_PLAIN_MIME_TYPE, file,
                null, false), draftId, format);
    }

    public Draft updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText,
                                              File file) throws Exception {
        return updateToEmailAddressWithFile(draftId, toEmailAddress, emailText, file, LIBRARY_OBJECT);
    }

    public <T> T updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText, File file,
                                              ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText,
                TEXT_PLAIN_MIME_TYPE, file, null, false), draftId, format);
    }

    public Draft updateSubjectWithFile(String draftId, String subject, String emailText, File file) throws Exception {
        return updateSubjectWithFile(draftId, subject, emailText, file, LIBRARY_OBJECT);
    }

    public <T> T updateSubjectWithFile(String draftId, String subject, String emailText, File file,
                                       ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(getHeaderValue(draftId, To), subject, emailText, TEXT_PLAIN_MIME_TYPE,
                file, null, false), draftId, format);
    }

    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, subject, emailText, mimeType, file, null,
                false), draftId, format);
    }

    public Draft updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText, File file,
                                              String mimeType) throws Exception {
        return updateToEmailAddressWithFile(draftId, toEmailAddress, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    public <T> T updateToEmailAddressWithFile(String draftId, String toEmailAddress, String emailText, File file,
                                              String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(toEmailAddress, getHeaderValue(draftId, SUBJECT), emailText, mimeType,
                file, null, false), draftId, format);
    }

    public Draft updateSubjectWithFile(String draftId, String subject, String emailText, File file,
                                       String mimeType) throws Exception {
        return updateSubjectWithFile(draftId, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    public <T> T updateSubjectWithFile(String draftId, String subject, String emailText, File file, String mimeType,
                                       ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFile(getHeaderValue(draftId, To), subject, emailText, mimeType, file,
                null, false), draftId, format);
    }

    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, TEXT_PLAIN_MIME_TYPE, files,
                null, false), draftId, format);
    }

    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      String mimeType, File[] files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files, String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, mimeType, files, null,
                false), draftId, format);
    }

    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, TEXT_PLAIN_MIME_TYPE,
                files.toArray(new File[0]), null, false), draftId, format);
    }

    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      String mimeType, Collection<File> files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return updateDraft(createDraftWithFiles(toEmailAddress, subject, emailText, mimeType, files.toArray(new File[0]),
                null, false), draftId, format);
    }

    private String getHeaderValue(String draftId, String headerKeySearched) throws IOException {
        com.google.api.services.gmail.model.Draft draft = gmail.drafts().get(userId, draftId)
                .setFormat(FULL_FORMAT).execute();
        for (MessagePartHeader messagePartHeader : draft.getMessage().getPayload().getHeaders())
            if (messagePartHeader.getName().equals(headerKeySearched))
                return messagePartHeader.getValue();
        return null;
    }

    private <T> T updateDraft(com.google.api.services.gmail.model.Draft draftToReplace, String draftId,
                              ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Draft draftUpdated = gmail.drafts().update(userId, draftId,
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
