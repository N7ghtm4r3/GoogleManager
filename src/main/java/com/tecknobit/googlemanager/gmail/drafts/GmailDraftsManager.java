package com.tecknobit.googlemanager.gmail.drafts;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListDraftsResponse;
import com.tecknobit.apimanager.annotations.RequestPath;
import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.apimanager.annotations.WrappedRequest;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.drafts.records.Draft;
import com.tecknobit.googlemanager.gmail.drafts.records.Drafts;
import com.tecknobit.googlemanager.gmail.messages.GmailMessagesManager;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GmailDraftsManager} class is useful to manage all Gmail's drafts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts">Gmail drafts</a>
 **/
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
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
     * @throws IOException when auth request has been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager} <br>
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
    public GmailDraftsManager() throws IOException {
        super();
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraft(String toEmailAddress, String subject, String emailText, ReturnFormat format) throws Exception {
        return createDraft(createSimpleMessage(toEmailAddress, subject, emailText), format, true);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithCc(String toEmailAddress, String subject, String emailText, String... Cc) throws Exception {
        return createDraftWithCc(toEmailAddress, subject, emailText, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithCc(String toEmailAddress, String subject, String emailText, ReturnFormat format,
                                   String... Cc) throws Exception {
        return createDraft(createCcMessage(toEmailAddress, subject, Arrays.toString(Cc), emailText), format, true);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithBcc(String toEmailAddress, String subject, String emailText, String... Bcc) throws Exception {
        return createDraftWithBcc(toEmailAddress, subject, emailText, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithBcc(String toEmailAddress, String subject, String emailText, ReturnFormat format,
                                    String... Bcc) throws Exception {
        return createDraft(createBccMessage(toEmailAddress, subject, Arrays.toString(Bcc), emailText), format, true);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraft(String toEmailAddress, String subject, String emailText, String[] Cc,
                             String[] Bcc) throws Exception {
        return createDraft(toEmailAddress, subject, emailText, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraft(String toEmailAddress, String subject, String emailText, String[] Cc, String[] Bcc,
                             ReturnFormat format) throws Exception {
        return createDraft(createCompleteMessage(toEmailAddress, subject, Arrays.toString(Cc), Arrays.toString(Bcc),
                emailText), format, true);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraft(String toEmailAddress, String subject, String emailText, Collection<String> Cc,
                             Collection<String> Bcc) throws Exception {
        return createDraft(toEmailAddress, subject, emailText, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraft(String toEmailAddress, String subject, String emailText, Collection<String> Cc,
                             Collection<String> Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompleteMessage(toEmailAddress, subject, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), emailText), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     ReturnFormat format) throws Exception {
        return createDraft(createSimpleMessageWithFile(toEmailAddress, subject, emailText, file, TEXT_PLAIN_MIME_TYPE),
                format, true);
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file, String mimeType,
                                     ReturnFormat format) throws Exception {
        return createDraft(createSimpleMessageWithFile(toEmailAddress, subject, emailText, file, mimeType), format,
                true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createCcDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                       String... Cc) throws Exception {
        return createCcDraftWithFile(toEmailAddress, subject, emailText, file, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createCcDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                       ReturnFormat format, String... Cc) throws Exception {
        return createDraft(createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc), emailText, file,
                TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createCcDraftWithFile(String toEmailAddress, String subject, String emailText, String mimeType,
                                       File file, String... Cc) throws Exception {
        return createCcDraftWithFile(toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT, Cc);
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
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createCcDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                       String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return createDraft(createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc), emailText, file,
                mimeType), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createBccDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                        String... Bcc) throws Exception {
        return createBccDraftWithFile(toEmailAddress, subject, emailText, file, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createBccDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                        ReturnFormat format, String... Bcc) throws Exception {
        return createDraft(createBccMessageWithFile(toEmailAddress, subject, Arrays.toString(Bcc), emailText, file,
                TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompleteMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                Arrays.toString(Bcc), file, TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, String mimeType) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, Cc, Bcc, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, String mimeType, ReturnFormat format) throws Exception {
        return createDraft(createCompleteMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                Arrays.toString(Bcc), file, mimeType), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompleteMessageWithFile(toEmailAddress, subject, emailText, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), file, TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, String mimeType) throws Exception {
        return createDraftWithFile(toEmailAddress, subject, emailText, file, Cc, Bcc, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a file as attachment
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFile(String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, String mimeType,
                                     ReturnFormat format) throws Exception {
        return createDraft(createCompleteMessageWithFile(toEmailAddress, subject, emailText, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), file, mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      ReturnFormat format) throws Exception {
        return createDraft(createMessageWithFiles(toEmailAddress, subject, emailText, files, TEXT_PLAIN_MIME_TYPE),
                format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createCcDraftWithFiles(String toEmailAddress, String subject, String emailText,
                                        File[] files, String... Cc) throws Exception {
        return createCcDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createCcDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                        ReturnFormat format, String... Cc) throws Exception {
        return createDraft(createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText, files,
                TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createBccDraftWithFiles(String toEmailAddress, String subject, String emailText,
                                         File[] files, String... Bcc) throws Exception {
        return createBccDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createBccDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                         ReturnFormat format, String... Bcc) throws Exception {
        return createDraft(createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc), emailText, files,
                TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String[] Cc, String[] Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                Arrays.toString(Bcc), files, TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, emailText, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), files, TEXT_PLAIN_MIME_TYPE), format, true);
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return createDraft(createMessageWithFiles(toEmailAddress, subject, emailText, files, mimeType), format,
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
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createCcDraftWithFiles(String toEmailAddress, String subject, String emailText, String mimeType,
                                        File[] files, String... Cc) throws Exception {
        return createCcDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Cc);
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
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createCcDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                        String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return createDraft(createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText, files,
                mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createBccDraftWithFiles(String toEmailAddress, String subject, String emailText, String mimeType,
                                         File[] files, String... Bcc) throws Exception {
        return createBccDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Bcc);
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
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createBccDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                         String mimeType, ReturnFormat format, String... Bcc) throws Exception {
        return createDraft(createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc), emailText, files,
                mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, String[] Cc, String[] Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                Arrays.toString(Bcc), files, mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, Collection<String> Cc, Collection<String> Bcc,
                                      ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, emailText, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), files, mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      ReturnFormat format) throws Exception {
        return createDraft(createMessageWithFiles(toEmailAddress, subject, emailText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createCcDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                        String... Cc) throws Exception {
        return createCcDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createCcDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                        ReturnFormat format, String... Cc) throws Exception {
        return createDraft(createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createBccDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                         String... Bcc) throws Exception {
        return createBccDraftWithFiles(toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createBccDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                         ReturnFormat format, String... Bcc) throws Exception {
        return createDraft(createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc), emailText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String[] Cc, String[] Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                Arrays.toString(Bcc), files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, emailText, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format, true);
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
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
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return createDraft(createMessageWithFiles(toEmailAddress, subject, emailText, files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createCcDraftWithFiles(String toEmailAddress, String subject, String emailText, String mimeType,
                                        Collection<File> files, String... Cc) throws Exception {
        return createCcDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Cc);
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
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createCcDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                        String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return createDraft(createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                files.toArray(new File[0]), mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createBccDraftWithFiles(String toEmailAddress, String subject, String mimeType, String emailText,
                                         Collection<File> files, String... Bcc) throws Exception {
        return createBccDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Bcc);
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
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createBccDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                         String mimeType, ReturnFormat format, String... Bcc) throws Exception {
        return createDraft(createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc), emailText,
                files.toArray(new File[0]), mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String mimeType, String[] Cc, String[] Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String mimeType, String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                Arrays.toString(Bcc), files.toArray(new File[0]), mimeType), format, true);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public Draft createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String mimeType, Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return createDraftWithFiles(toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to create a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts")
    public <T> T createDraftWithFiles(String toEmailAddress, String subject, String emailText, Collection<File> files,
                                      String mimeType, Collection<String> Cc, Collection<String> Bcc,
                                      ReturnFormat format) throws Exception {
        return createDraft(createCompletedMessageWithFiles(toEmailAddress, subject, emailText, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), files.toArray(new File[0]), mimeType), format, true);
    }

    /**
     * Method to create a draft
     *
     * @param message:            message created
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @param sendCreateResponse: flag to send or not request to create a new draft
     * @return draft as {@code "format"} defines
     **/
    @Returner
    private <T> T createDraft(com.google.api.services.gmail.model.Message message, ReturnFormat format,
                              boolean sendCreateResponse) throws Exception {
        com.google.api.services.gmail.model.Draft draft = new com.google.api.services.gmail.model.Draft().setMessage(message);
        if (sendCreateResponse)
            return returnDraft(drafts.create(userId, draft).execute(), format);
        return (T) draft;
    }

    /**
     * Method to delete a draft
     *
     * @param draft: draft to delete
     * @return result of the deletion -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/delete">
     * users.drafts.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts/{id}")
    public boolean deleteDraft(Draft draft) {
        return deleteDraft(draft.getId());
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
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts/{id}")
    public boolean deleteDraft(String draftId) {
        try {
            drafts.delete(userId, draftId).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get a draft
     *
     * @param draftId: identifier of the draft to get
     * @return draft requested as {@link Draft} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/get">
     * users.drafts.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts/{id}")
    public Draft getDraft(String draftId) throws IOException {
        return getDraft(draftId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a draft
     *
     * @param draftId: identifier of the draft to get
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws IOException when the request has been go wrong
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/get">
     * users.drafts.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts/{id}")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/get">
     * users.drafts.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T getDraft(String draftId, ResponseFormat responseFormat, ReturnFormat format) throws IOException {
        Gmail.Users.Drafts.Get getDraft = drafts.get(userId, draftId);
        if (responseFormat != null)
            getDraft.setFormat(responseFormat.toString());
        return returnDraft(getDraft.execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @return drafts list requested as {@link Drafts} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public Drafts getDraftsList(boolean includeSpamTrash) throws IOException {
        return getDraftsList(includeSpamTrash, LIBRARY_OBJECT);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return drafts list as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash).execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param maxResults:       maximum number of drafts to return. This field defaults to 100. The maximum allowed value for this field is 500
     * @return drafts list requested as {@link Drafts} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
                .setMaxResults((long) maxResults)
                .execute(), format);
    }

    /**
     * Method to get a drafts list
     *
     * @param includeSpamTrash: flag to include or not drafts from {@code "SPAM"} and {@code "THRASH"} in the results
     * @param pageToken:        page token to retrieve a specific page of results in the list
     * @return drafts list requested as {@link Drafts} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(boolean includeSpamTrash, String pageToken, ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId)
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(String q, boolean includeSpamTrash, ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId)
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                               ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(boolean includeSpamTrash, String q, int maxResults,
                               ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(boolean includeSpamTrash, String pageToken, String q,
                               ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
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
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">
     * users.drafts.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "https://gmail.googleapis.com/gmail/v1/users/{userId}/drafts")
    public <T> T getDraftsList(boolean includeSpamTrash, int maxResults, String pageToken,
                               String q, ReturnFormat format) throws IOException {
        return returnDraftsList(drafts.list(userId).setIncludeSpamTrash(includeSpamTrash)
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
    @Returner
    private <T> T returnDraftsList(ListDraftsResponse drafts, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(drafts);
            case LIBRARY_OBJECT:
                return (T) new Drafts(new JSONObject(drafts));
            default:
                return (T) drafts.toString();
        }
    }

    /**
     * Method to send a draft
     *
     * @param draft: draft to send
     * @return message of the response as {@link Message} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/send")
    public Message sendDraft(Draft draft) throws IOException {
        return sendDraft(draft.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to send a draft
     *
     * @param draft:  draft to send
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/send")
    public <T> T sendDraft(Draft draft, ReturnFormat format) throws IOException {
        return sendDraft(draft.getId(), format);
    }

    /**
     * Method to send a draft
     *
     * @param draftId: identifier ot the draft to send
     * @return message of the response as {@link Message} custom object
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/send")
    public Message sendDraft(String draftId) throws IOException {
        return sendDraft(draftId, LIBRARY_OBJECT);
    }

    /**
     * Method to send a draft
     *
     * @param draftId: identifier ot the draft to send
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return message as {@code "format"} defines
     * @throws IOException when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/send">
     * users.drafts.send</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/send")
    public <T> T sendDraft(String draftId, ReturnFormat format) throws IOException {
        return GmailMessagesManager.returnMessage(drafts.send(userId, drafts.get(userId, draftId).execute()).execute(),
                format);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraft(Draft draft, String toEmailAddress, String subject, String emailText) throws Exception {
        return updateDraft(draft.getId(), toEmailAddress, subject, emailText, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraft(Draft draft, String toEmailAddress, String subject, String emailText,
                             ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createSimpleMessage(toEmailAddress, subject, emailText), format);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraft(String draftId, String toEmailAddress, String subject, String emailText) throws Exception {
        return updateDraft(draftId, toEmailAddress, subject, emailText, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraft(String draftId, String toEmailAddress, String subject, String emailText,
                             ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createSimpleMessage(toEmailAddress, subject, emailText), format);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithCc(Draft draft, String toEmailAddress, String subject, String emailText,
                                   String... Cc) throws Exception {
        return updateDraftWithCc(draft.getId(), toEmailAddress, subject, emailText, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithCc(Draft draft, String toEmailAddress, String subject, String emailText,
                                   ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draft.getId(), createCcMessage(toEmailAddress, subject, Arrays.toString(Cc), emailText),
                format);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithCc(String draftId, String toEmailAddress, String subject, String emailText,
                                   String... Cc) throws Exception {
        return updateDraftWithCc(draftId, toEmailAddress, subject, emailText, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithCc(String draftId, String toEmailAddress, String subject, String emailText,
                                   ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draftId, createCcMessage(toEmailAddress, subject, Arrays.toString(Cc), emailText),
                format);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithBcc(Draft draft, String toEmailAddress, String subject, String emailText,
                                    String... Bcc) throws Exception {
        return updateDraftWithBcc(draft.getId(), toEmailAddress, subject, emailText, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithBcc(Draft draft, String toEmailAddress, String subject, String emailText,
                                    ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draft.getId(), createBccMessage(toEmailAddress, subject, Arrays.toString(Bcc), emailText),
                format);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithBcc(String draftId, String toEmailAddress, String subject, String emailText,
                                    String... Bcc) throws Exception {
        return updateDraftWithBcc(draftId, toEmailAddress, subject, emailText, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithBcc(String draftId, String toEmailAddress, String subject, String emailText,
                                    ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draftId, createBccMessage(toEmailAddress, subject, Arrays.toString(Bcc), emailText),
                format);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraft(Draft draft, String toEmailAddress, String subject, String emailText, String[] Cc,
                             String[] Bcc) throws Exception {
        return updateDraft(draft.getId(), toEmailAddress, subject, emailText, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraft(Draft draft, String toEmailAddress, String subject, String emailText, String[] Cc,
                             String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompleteMessage(toEmailAddress, subject, Arrays.toString(Cc),
                Arrays.toString(Bcc), emailText), format);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraft(String draftId, String toEmailAddress, String subject, String emailText, String[] Cc,
                             String[] Bcc) throws Exception {
        return updateDraft(draftId, toEmailAddress, subject, emailText, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraft(String draftId, String toEmailAddress, String subject, String emailText, String[] Cc,
                             String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompleteMessage(toEmailAddress, subject, Arrays.toString(Cc),
                Arrays.toString(Bcc), emailText), format);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraft(Draft draft, String toEmailAddress, String subject, String emailText,
                             Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraft(draft.getId(), toEmailAddress, subject, emailText, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraft(Draft draft, String toEmailAddress, String subject, String emailText,
                             Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompleteMessage(toEmailAddress, subject, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), emailText), format);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraft(String draftId, String toEmailAddress, String subject, String emailText,
                             Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraft(draftId, toEmailAddress, subject, emailText, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraft(String draftId, String toEmailAddress, String subject, String emailText,
                             Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompleteMessage(toEmailAddress, subject, Arrays.toString(Cc.toArray()),
                Arrays.toString(Bcc.toArray()), emailText), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText,
                                     File file) throws Exception {
        return updateDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createSimpleMessageWithFile(toEmailAddress, subject, emailText, file,
                TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText,
                                     File file) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createSimpleMessageWithFile(toEmailAddress, subject, emailText, file,
                TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType) throws Exception {
        return updateDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createSimpleMessageWithFile(toEmailAddress, subject, emailText, file,
                mimeType), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createSimpleMessageWithFile(toEmailAddress, subject, emailText, file, mimeType),
                format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                       String... Cc) throws Exception {
        return updateCcDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                       ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draft.getId(), createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                       String... Cc) throws Exception {
        return updateCcDraftWithFile(draftId, toEmailAddress, subject, emailText, file, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                       ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draftId, createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText,
                                       String mimeType, File file, String... Cc) throws Exception {
        return updateCcDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT,
                Cc);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                       String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draft.getId(), createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, file, mimeType), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText,
                                       String mimeType, File file, String... Cc) throws Exception {
        return updateCcDraftWithFile(draftId, toEmailAddress, subject, emailText, file, mimeType, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                       String mimeType, ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draftId, createCcMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                file, mimeType), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                        String... Bcc) throws Exception {
        return updateBccDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                        ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draft.getId(), createBccMessageWithFile(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                        String... Bcc) throws Exception {
        return updateBccDraftWithFile(draftId, toEmailAddress, subject, emailText, file, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                        ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draftId, createBccMessageWithFile(toEmailAddress, subject, Arrays.toString(Bcc), emailText,
                file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompleteMessageWithFile(toEmailAddress, subject,
                Arrays.toString(Cc), emailText, Arrays.toString(Bcc), file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompleteMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, String mimeType) throws Exception {
        return updateDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, Cc, Bcc, mimeType,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompleteMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), file, mimeType), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, String mimeType) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, Cc, Bcc, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     String[] Cc, String[] Bcc, String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompleteMessageWithFile(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), file, mimeType), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompleteMessageWithFile(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompleteMessageWithFile(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), file, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, String mimeType) throws Exception {
        return updateDraftWithFile(draft.getId(), toEmailAddress, subject, emailText, file, Cc, Bcc, mimeType,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(Draft draft, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, String mimeType,
                                     ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompleteMessageWithFile(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), file, mimeType), format);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, String mimeType) throws Exception {
        return updateDraftWithFile(draftId, toEmailAddress, subject, emailText, file, Cc, Bcc, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a file as attachment
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param file:           attachment file to sent with draft
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFile(String draftId, String toEmailAddress, String subject, String emailText, File file,
                                     Collection<String> Cc, Collection<String> Bcc, String mimeType,
                                     ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompleteMessageWithFile(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), file, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      File[] files) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      File[] files, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createMessageWithFiles(toEmailAddress, subject, emailText, files,
                TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createMessageWithFiles(toEmailAddress, subject, emailText, files,
                TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        File[] files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        File[] files, ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draft.getId(), createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        File[] files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        File[] files, ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draftId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                         File[] files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                         File[] files, ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draft.getId(), createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                         File[] files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                         File[] files, ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draftId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc), emailText,
                files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      File[] files, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      Collection<String> Cc, Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files, TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createMessageWithFiles(toEmailAddress, subject, emailText, files,
                mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createMessageWithFiles(toEmailAddress, subject, emailText, files, mimeType),
                format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        String mimeType, File[] files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT,
                Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        File[] files, String mimeType, ReturnFormat format,
                                        String... Cc) throws Exception {
        return executeDraftUpdate(draft.getId(), createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        String mimeType, File[] files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        File[] files, String mimeType, ReturnFormat format,
                                        String... Cc) throws Exception {
        return executeDraftUpdate(draftId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                         String mimeType, File[] files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT,
                Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                         File[] files, String mimeType, ReturnFormat format,
                                         String... Bcc) throws Exception {
        return executeDraftUpdate(draft.getId(), createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                         String mimeType, File[] files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                         File[] files, String mimeType, ReturnFormat format,
                                         String... Bcc) throws Exception {
        return executeDraftUpdate(draftId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), emailText, Arrays.toString(Bcc), files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, String[] Cc, String[] Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      File[] files, String mimeType, Collection<String> Cc, Collection<String> Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText, File[] files,
                                      String mimeType, Collection<String> Cc, Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as array of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      File[] files, String mimeType, Collection<String> Cc, Collection<String> Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files, mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createMessageWithFiles(toEmailAddress, subject, emailText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createMessageWithFiles(toEmailAddress, subject, emailText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        Collection<File> files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        Collection<File> files, ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draft.getId(), createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        Collection<File> files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        Collection<File> files, ReturnFormat format, String... Cc) throws Exception {
        return executeDraftUpdate(draftId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc), emailText,
                files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                         Collection<File> files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                         Collection<File> files, ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draft.getId(), createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                         Collection<File> files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                         Collection<File> files, ReturnFormat format, String... Bcc) throws Exception {
        return executeDraftUpdate(draftId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String[] Cc, String[] Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject,
                        Arrays.toString(Cc), emailText, Arrays.toString(Bcc), files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE),
                format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String[] Cc, String[] Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), files.toArray(new File[0]), TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, Collection<String> Cc,
                                      Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, Collection<String> Cc, Collection<String> Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, Collection<String> Cc,
                                      Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, Collection<String> Cc, Collection<String> Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files.toArray(new File[0]),
                TEXT_PLAIN_MIME_TYPE), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      String mimeType, Collection<File> files) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createMessageWithFiles(toEmailAddress, subject, emailText,
                files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      String mimeType, Collection<File> files) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createMessageWithFiles(toEmailAddress, subject, emailText,
                files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        String mimeType, Collection<File> files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT,
                Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                        Collection<File> files, String mimeType, ReturnFormat format,
                                        String... Cc) throws Exception {
        return executeDraftUpdate(draft.getId(), createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        String mimeType, Collection<File> files, String... Cc) throws Exception {
        return updateCcDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Cc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Cc:             carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateCcDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                        Collection<File> files, String mimeType, ReturnFormat format,
                                        String... Cc) throws Exception {
        return executeDraftUpdate(draftId, createCcMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String mimeType,
                                         String emailText, Collection<File> files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT,
                Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                         Collection<File> files, String mimeType, ReturnFormat format,
                                         String... Bcc) throws Exception {
        return executeDraftUpdate(draft.getId(), createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Bcc:            blind carbon copy value
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String mimeType,
                                         String emailText, Collection<File> files, String... Bcc) throws Exception {
        return updateBccDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, LIBRARY_OBJECT, Bcc);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @param Bcc:            blind carbon copy value
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateBccDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                         Collection<File> files, String mimeType, ReturnFormat format,
                                         String... Bcc) throws Exception {
        return executeDraftUpdate(draftId, createBccMessageWithFiles(toEmailAddress, subject, Arrays.toString(Bcc),
                emailText, files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, String[] Cc, String[] Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject,
                Arrays.toString(Cc), emailText, Arrays.toString(Bcc), files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, String[] Cc, String[] Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in array of {@link String} format
     * @param Bcc:            blind carbon copy values in array of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/create">
     * users.drafts.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, String[] Cc, String[] Bcc,
                                      ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, Arrays.toString(Cc),
                emailText, Arrays.toString(Bcc), files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, Collection<String> Cc,
                                      Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draft.getId(), toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc,
                LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draft:          draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(Draft draft, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, Collection<String> Cc,
                                      Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draft.getId(), createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                        Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files.toArray(new File[0]), mimeType),
                format);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @return draft response as {@link Draft} custom object
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public Draft updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, Collection<String> Cc,
                                      Collection<String> Bcc) throws Exception {
        return updateDraftWithFiles(draftId, toEmailAddress, subject, emailText, files, mimeType, Cc, Bcc, LIBRARY_OBJECT);
    }

    /**
     * Method to update a draft with a different files as attachments
     *
     * @param draftId:        identifier of the draft to update
     * @param toEmailAddress: recipient of the email message
     * @param subject:        subject of the email message
     * @param emailText:      email content message
     * @param files:          attachments files to sent with draft as {@link Collection} of {@link File}
     * @param mimeType:       type of mime -> constants available at {@link GmailManager}
     * @param Cc:             carbon copy values in {@link Collection} of {@link String} format
     * @param Bcc:            blind carbon copy values in {@link Collection} of {@link String} format
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     * @throws Exception when the request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/update">
     * users.drafts.update</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    @WrappedRequest
    @RequestPath(path = "/gmail/v1/users/{userId}/drafts/{id}")
    public <T> T updateDraftWithFiles(String draftId, String toEmailAddress, String subject, String emailText,
                                      Collection<File> files, String mimeType, Collection<String> Cc,
                                      Collection<String> Bcc, ReturnFormat format) throws Exception {
        return executeDraftUpdate(draftId, createCompletedMessageWithFiles(toEmailAddress, subject, emailText,
                Arrays.toString(Cc.toArray()), Arrays.toString(Bcc.toArray()), files.toArray(new File[0]), mimeType), format);
    }

    /**
     * Method to execute the update of the draft assembled before invocation of this method
     *
     * @param message: message updated to send as draft update
     * @param draftId: identifier of the draft to update
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     **/
    @Returner
    private <T> T executeDraftUpdate(String draftId, com.google.api.services.gmail.model.Message message,
                                     ReturnFormat format) throws Exception {
        return returnDraft(drafts.update(userId, draftId, createDraft(message, null, false))
                .execute(), format);
    }

    /**
     * Method to create a draft object
     *
     * @param draft:  draft o obtained from Google's response
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return draft as {@code "format"} defines
     **/
    @Returner
    private <T> T returnDraft(com.google.api.services.gmail.model.Draft draft, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(draft);
            case LIBRARY_OBJECT:
                return (T) new Draft(new JSONObject(draft));
            default:
                return (T) draft.toString();
        }
    }

}
