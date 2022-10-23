package com.tecknobit.googlemanager.gmail.settings;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;
import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding;
import com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition;
import com.tecknobit.googlemanager.gmail.settings.records.Delegate;
import com.tecknobit.googlemanager.gmail.settings.records.Delegate.VerificationStatus;
import com.tecknobit.googlemanager.gmail.settings.records.Filter;
import com.tecknobit.googlemanager.gmail.settings.records.ForwardingAddress;
import com.tecknobit.googlemanager.gmail.settings.records.ImapSettings;
import com.tecknobit.googlemanager.gmail.settings.records.PopSettings;
import com.tecknobit.googlemanager.gmail.settings.records.PopSettings.AccessWindow;
import com.tecknobit.googlemanager.gmail.settings.records.SendAs;
import com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa.SecurityMode;
import com.tecknobit.googlemanager.gmail.settings.records.VacationSettings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.googlemanager.gmail.settings.records.ImapSettings.ExpungeBehavior;

/**
 * The {@code GmailSettingsManager} class is useful to manage all Gmail's settings endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote this class include also all {@code "settings"} sub-endpoints, see the officials documentations at:
 * <ul>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings">settings</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates">settings.delegates</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters">settings.filters</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses">settings.forwardingAddresses</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs">settings.sendAs</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs.smimeInfo">settings.sendAs.smimeInfo</a>
 *     </li>
 * </ul>
 **/
public class GmailSettingsManager extends GmailManager {

    /**
     * {@code settings} is the instance for {@link Gmail.Users.Settings}'s service
     **/
    protected final Gmail.Users.Settings settings = gmail.settings();

    /**
     * {@code delegates} is the instance for {@link Gmail.Users.Settings.Delegates}'s service
     **/
    protected final Gmail.Users.Settings.Delegates delegates = settings.delegates();

    /**
     * {@code filters} is the instance for {@link Gmail.Users.Settings.Filters}'s service
     **/
    protected final Gmail.Users.Settings.Filters filters = settings.filters();

    /**
     * {@code forwardingAddresses} is the instance for {@link Gmail.Users.Settings.ForwardingAddresses}'s service
     **/
    protected final Gmail.Users.Settings.ForwardingAddresses forwardingAddresses = settings.forwardingAddresses();

    /**
     * {@code sendAs} is the instance for {@link Gmail.Users.Settings.SendAs}'s service
     **/
    protected final Gmail.Users.Settings.SendAs sendAs = settings.sendAs();

    /**
     * {@code currentUsername} the username used for authentication with the {@code "SMTP"} service
     *
     * @apiNote this field is useful for this class to reinsert this value in {@link SendAs} object after {@code "Gmail"}'s response
     * because it will not return this field. <br>
     * <strong>This field will be not store in any parts if not during runtime of your program </strong>
     * @implNote it will be use in {@link #sendAs} operations
     **/
    private String currentUsername;

    /**
     * {@code currentPassword} the password used for authentication with the {@code "SMTP"} service
     *
     * @apiNote this field is useful for this class to reinsert this value in {@link SendAs} object after {@code "Gmail"}'s response
     * because it will not return this field. <br>
     * <strong>This field will be not store in any parts if not during runtime of your program </strong>
     * @implNote it will be use in {@link #sendAs} operations
     **/
    private String currentPassword;


    /**
     * Constructor to init a {@link GmailSettingsManager}
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
    public GmailSettingsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailSettingsManager}
     *
     * @param clientId        :        client identifier value
     * @param clientSecret    :    client secret value
     * @param userId          :          used to identifier a user -> me to use an authenticated user
     * @param accessType      :      access type used in the auth operations
     * @param approvalPrompt  :  approval prompt type used in the auth operations
     * @param applicationName : name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailSettingsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, applicationName);
    }

    /**
     * Constructor to init a {@link GmailSettingsManager}
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
    public GmailSettingsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailSettingsManager}
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
    public GmailSettingsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailSettingsManager}
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
    public GmailSettingsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                                String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailSettingsManager} <br>
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
    public GmailSettingsManager() throws IOException {
        super();
    }

    /**
     * Method to get the auto-forwarding setting for the specified account <br>
     * Any params required
     *
     * @return auto-forwarding setting as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getAutoForwarding">
     * users.settings.getAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding getAutoForwarding() throws IOException {
        return getAutoForwarding(LIBRARY_OBJECT);
    }

    /**
     * Method to get the auto-forwarding setting for the specified account
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getAutoForwarding">
     * users.settings.getAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getAutoForwarding(ReturnFormat format) throws IOException {
        return returnAutoForwarding(settings.getAutoForwarding(userId).execute(), format);
    }

    /**
     * Method to get {@code "IMAP"} settings <br>
     * Any params required
     *
     * @return {@code "IMAP"} settings as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getImap">
     * users.settings.getImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings getImap() throws IOException {
        return getImap(LIBRARY_OBJECT);
    }

    /**
     * Method to get {@code "IMAP"} settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getImap">
     * users.settings.getImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getImap(ReturnFormat format) throws IOException {
        return returnImap(settings.getImap(userId).execute(), format);
    }

    /**
     * Method to get language settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return language settings as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getLanguage">
     * users.settings.getLanguage</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getLanguage(ReturnFormat format) throws IOException {
        return returnLanguage(settings.getLanguage(userId).execute(), format);
    }

    /**
     * Method to get {@code "POP"} settings <br>
     * Any params required
     *
     * @return {@code "POP"} settings as {@link PopSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getPop">
     * users.settings.getPop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public PopSettings getPopSettings() throws IOException {
        return getPopSettings(LIBRARY_OBJECT);
    }

    /**
     * Method to get {@code "POP"} settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return {@code "POP"} settings as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getPop">
     * users.settings.getPop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getPopSettings(ReturnFormat format) throws IOException {
        return returnPopSettings(settings.getPop(userId).execute(), format);
    }

    /**
     * Method to get vacation responder settings <br>
     * Any params required
     *
     * @return vacation responder settings as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getVacation">
     * users.settings.getVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings getVacationSettings() throws IOException {
        return getVacationSettings(LIBRARY_OBJECT);
    }

    /**
     * Method to get vacation responder settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/getVacation">
     * users.settings.getVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getVacationSettings(ReturnFormat format) throws IOException {
        return returnVacationSettings(settings.getVacation(userId).execute(), format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update only {@code "enabled"} param without change all the current auto-forwarding setting
     *
     * @param enabled: whether all incoming mail is automatically forwarded to another address
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwardingEnabled(boolean enabled) throws IOException {
        return updateAutoForwardingEnabled(enabled, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update only {@code "enabled"} param without change all the current auto-forwarding setting
     *
     * @param enabled: whether all incoming mail is automatically forwarded to another address
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwardingEnabled(boolean enabled, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        if (enabled)
            actualAutoForwarding.enable();
        else
            actualAutoForwarding.disable();
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update only {@code "emailAddress"} param without change all the current auto-forwarding setting
     *
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwardingEmailAddress(String emailAddress) throws IOException {
        return updateAutoForwardingEmailAddress(emailAddress, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update only {@code "emailAddress"} param without change all the current auto-forwarding setting
     *
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwardingEmailAddress(String emailAddress, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        actualAutoForwarding.setEmailAddress(emailAddress);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update only {@code "disposition"} param without change all the current auto-forwarding setting
     *
     * @param disposition: the state that a message should be left in after it has been forwarded
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwardingDisposition(Disposition disposition) throws IOException {
        return updateAutoForwardingDisposition(disposition, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update only {@code "disposition"} param without change all the current auto-forwarding setting
     *
     * @param disposition: the state that a message should be left in after it has been forwarded
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwardingDisposition(Disposition disposition, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        actualAutoForwarding.setDisposition(disposition);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update {@code "enabled"} and {@code "emailAddress"} params without change all the current auto-forwarding setting
     *
     * @param enabled:      whether all incoming mail is automatically forwarded to another address
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwarding(boolean enabled, String emailAddress) throws IOException {
        return updateAutoForwarding(enabled, emailAddress, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update {@code "enabled"} and {@code "emailAddress"} params without change all the current auto-forwarding setting
     *
     * @param enabled:      whether all incoming mail is automatically forwarded to another address
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwarding(boolean enabled, String emailAddress, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        if (enabled)
            actualAutoForwarding.enable();
        else
            actualAutoForwarding.disable();
        actualAutoForwarding.setEmailAddress(emailAddress);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update {@code "enabled"} and {@code "emailAddress"} params without change all the current auto-forwarding setting
     *
     * @param enabled:     whether all incoming mail is automatically forwarded to another address
     * @param disposition: the state that a message should be left in after it has been forwarded
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwarding(boolean enabled, Disposition disposition) throws IOException {
        return updateAutoForwarding(enabled, disposition, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update {@code "enabled"} and {@code "emailAddress"} params without change all the current auto-forwarding setting
     *
     * @param enabled:     whether all incoming mail is automatically forwarded to another address
     * @param disposition: the state that a message should be left in after it has been forwarded
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwarding(boolean enabled, Disposition disposition, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        if (enabled)
            actualAutoForwarding.enable();
        else
            actualAutoForwarding.disable();
        actualAutoForwarding.setDisposition(disposition);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update {@code "emailAddress"} and {@code "disposition"} params without change all the current auto-forwarding setting
     *
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @param disposition:  the state that a message should be left in after it has been forwarded
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwarding(String emailAddress, Disposition disposition) throws IOException {
        return updateAutoForwarding(emailAddress, disposition, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority. <br>
     * This method allows to update {@code "emailAddress"} and {@code "disposition"} params without change all the current auto-forwarding setting
     *
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @param disposition:  the state that a message should be left in after it has been forwarded
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwarding(String emailAddress, Disposition disposition, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        actualAutoForwarding.setEmailAddress(emailAddress);
        actualAutoForwarding.setDisposition(disposition);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority.
     *
     * @param autoForwardingUpdated: auto-forwarding settings for an account
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwarding(AutoForwarding autoForwardingUpdated) throws IOException {
        return updateAutoForwarding(autoForwardingUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority.
     *
     * @param autoForwardingUpdated: auto-forwarding settings for an account
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwarding(AutoForwarding autoForwardingUpdated, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEnabled(autoForwardingUpdated.isEnabled());
        autoForwarding.setEmailAddress(autoForwardingUpdated.getEmailAddress());
        autoForwarding.setDisposition(autoForwardingUpdated.getDisposition().toString());
        return returnAutoForwarding(settings.updateAutoForwarding(userId, autoForwarding).execute(), format);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority.
     *
     * @param enabled:      whether all incoming mail is automatically forwarded to another address
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @param disposition:  the state that a message should be left in after it has been forwarded
     * @return auto-forwarding setting updated as {@link AutoForwarding} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public AutoForwarding updateAutoForwarding(boolean enabled, String emailAddress, Disposition disposition) throws IOException {
        return updateAutoForwarding(enabled, emailAddress, disposition, LIBRARY_OBJECT);
    }

    /**
     * Method to update the auto-forwarding setting for the specified account. A verified forwarding address must be specified
     * when auto-forwarding is enabled. This method is only available to service account clients that have been delegated
     * domain-wide authority.
     *
     * @param enabled:      whether all incoming mail is automatically forwarded to another address
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @param disposition:  the state that a message should be left in after it has been forwarded
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateAutoForwarding">
     * users.settings.updateAutoForwarding</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateAutoForwarding(boolean enabled, String emailAddress, Disposition disposition,
                                      ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEnabled(enabled);
        autoForwarding.setEmailAddress(emailAddress);
        autoForwarding.setDisposition(disposition.name());
        return returnAutoForwarding(settings.updateAutoForwarding(userId, autoForwarding).execute(), format);
    }

    /**
     * Method to create an auto-forwarding object
     *
     * @param autoForwarding: auto-forwarding obtained from Google's response
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return auto-forwarding setting as {@code "format"} defines
     **/
    private <T> T returnAutoForwarding(com.google.api.services.gmail.model.AutoForwarding autoForwarding,
                                       ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(autoForwarding);
            case LIBRARY_OBJECT:
                return (T) new AutoForwarding(new JSONObject(autoForwarding));
            default:
                return (T) autoForwarding.toString();
        }
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to enable {@code "IMAP"} settings without change all the current settings <br>
     * Any params required
     *
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings enableImap() throws IOException {
        return enableImap(LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to enable {@code "IMAP"} settings without change all the current settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T enableImap(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.enable();
        return updateImap(actualImap, format);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to disable {@code "IMAP"} settings without change all the current settings <br>
     * Any params required
     *
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings disableImap() throws IOException {
        return disableImap(LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to disable {@code "IMAP"} settings without change all the current settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T disableImap(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.disable();
        return updateImap(actualImap, format);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to enable {@code "autoExpunge"} setting without change all the current {@code "IMAP"} settings <br>
     * Any params required
     *
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings enableImapAutoExpunge() throws IOException {
        return enableImapAutoExpunge(LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to enable {@code "autoExpunge"} setting without change all the current {@code "IMAP"} settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T enableImapAutoExpunge(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.enableAutoExpunge();
        return updateImap(actualImap, format);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to disable {@code "autoExpunge"} setting without change all the current {@code "IMAP"} settings <br>
     * Any params required
     *
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings disableImapAutoExpunge() throws IOException {
        return disableImapAutoExpunge(LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to disable {@code "autoExpunge"} setting without change all the current {@code "IMAP"} settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T disableImapAutoExpunge(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.disableAutoExpunge();
        return updateImap(actualImap, format);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to update {@code "expungeBehavior"} setting without change all the current {@code "IMAP"} settings
     *
     * @param expungeBehavior: the action that will be executed on a message when it is marked as deleted and expunged from the last visible {@code "IMAP"} folder
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings updateImapExpungeBehavior(ExpungeBehavior expungeBehavior) throws IOException {
        return updateImapExpungeBehavior(expungeBehavior, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to update {@code "expungeBehavior"} setting without change all the current {@code "IMAP"} settings
     *
     * @param expungeBehavior: the action that will be executed on a message when it is marked as deleted and expunged from the last visible {@code "IMAP"} folder
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateImapExpungeBehavior(ExpungeBehavior expungeBehavior, ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.setExpungeBehavior(expungeBehavior);
        return updateImap(actualImap, format);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to update {@code "maxFolderSize"} setting without change all the current {@code "IMAP"} settings
     *
     * @param maxFolderSize: an optional limit on the number of messages that an {@code "IMAP"} folder may contain. Legal values are 0, 1000, 2000, 5000 or 10000. A value of zero is interpreted to mean that there is no limit
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings updateImapMaxFolderSize(int maxFolderSize) throws IOException {
        return updateImapMaxFolderSize(maxFolderSize, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to update {@code "maxFolderSize"} setting without change all the current {@code "IMAP"} settings
     *
     * @param maxFolderSize: an optional limit on the number of messages that an {@code "IMAP"} folder may contain. Legal values are 0, 1000, 2000, 5000 or 10000. A value of zero is interpreted to mean that there is no limit
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateImapMaxFolderSize(int maxFolderSize, ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.setMaxFolderSize(maxFolderSize);
        return updateImap(actualImap, format);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to update dynamically some settings without change all the current {@code "IMAP"} settings
     *
     * @param imapSettingsUpdated: {@code "IMAP"} settings to change
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec some rules to follow to use this method:
     * <ul>
     *     <li>To change {@code "enabled"} params must be insert in odd positions of the params list
     *     <pre>
     *         {@code
     *         updateImap(enabled_value, ..., ...) // it will be correctly changed
     *         updateImap(..., ..., enabled_value) // it will be correctly changed
     *         updateImap(..., enabled_value, ...) // it will not be correctly changed
     *          }
     *     </pre>
     *     </li>
     *     <li>To change {@code "autoExpunge"} params must be insert in even positions of the params list
     *     <pre>
     *         {@code
     *         updateImap(..., autoExpunge_value, ...) // it will be correctly changed
     *         updateImap(..., ..., ..., autoExpunge_value) // it will be correctly changed
     *         updateImap(..., ..., enabled_value) // it will not be correctly changed
     *           }
     *     </pre>
     *     </li>
     * </ul>
     **/
    @SafeVarargs
    public final <T> ImapSettings updateImap(T... imapSettingsUpdated) throws IOException {
        return (ImapSettings) updateImap(LIBRARY_OBJECT, imapSettingsUpdated);
    }

    /**
     * Method to update {@code "IMAP"} settings <br>
     * This method allows to update dynamically some settings without change all the current {@code "IMAP"} settings
     *
     * @param imapSettingsUpdated: {@code "IMAP"} settings to change
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec some rules to follow to use this method:
     * <ul>
     *     <li>To change {@code "enabled"} params must be insert in odd positions of the params list
     *     <pre>
     *         {@code
     *         updateImap(enabled_value, ..., ...) // it will be correctly changed
     *         updateImap(..., ..., enabled_value) // it will be correctly changed
     *         updateImap(..., enabled_value, ...) // it will not be correctly changed
     *          }
     *     </pre>
     *     </li>
     *     <li>To change {@code "autoExpunge"} params must be insert in even positions of the params list
     *     <pre>
     *         {@code
     *         updateImap(..., autoExpunge_value, ...) // it will be correctly changed
     *         updateImap(..., ..., ..., autoExpunge_value) // it will be correctly changed
     *         updateImap(..., ..., enabled_value) // it will not be correctly changed
     *           }
     *     </pre>
     *     </li>
     * </ul>
     **/
    @SafeVarargs
    public final <T> T updateImap(ReturnFormat format, T... imapSettingsUpdated) throws IOException {
        com.google.api.services.gmail.model.ImapSettings imapSettings = settings.getImap(userId).execute();
        for (int j = 0; j < imapSettingsUpdated.length; j++) {
            if (j % 2 == 0 && imapSettingsUpdated[j].getClass().equals(Boolean.class))
                imapSettings.setEnabled((boolean) imapSettingsUpdated[j]);
            else if (j % 2 != 0 && imapSettingsUpdated[j].getClass().equals(Boolean.class))
                imapSettings.setAutoExpunge((boolean) imapSettingsUpdated[j]);
            else if (imapSettingsUpdated[j].getClass().equals(ExpungeBehavior.class))
                imapSettings.setExpungeBehavior(imapSettingsUpdated[j].toString());
            else if (imapSettingsUpdated[j].getClass().equals(Integer.class))
                imapSettings.setMaxFolderSize((int) imapSettingsUpdated[j]);
        }
        return returnImap(settings.updateImap(userId, imapSettings).execute(), format);
    }

    /**
     * Method to update {@code "IMAP"} settings
     *
     * @param imapSettingsUpdated: {@code "IMAP"} settings for an account
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings updateImap(ImapSettings imapSettingsUpdated) throws IOException {
        return updateImap(imapSettingsUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings
     *
     * @param imapSettingsUpdated: {@code "IMAP"} settings for an account
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateImap(ImapSettings imapSettingsUpdated, ReturnFormat format) throws IOException {
        return returnImap(settings.updateImap(userId, new com.google.api.services.gmail.model.ImapSettings()
                .setEnabled(imapSettingsUpdated.isEnabled())
                .setAutoExpunge(imapSettingsUpdated.isAutoExpungeEnabled())
                .setExpungeBehavior(imapSettingsUpdated.getExpungeBehavior().toString())
                .setMaxFolderSize(imapSettingsUpdated.getMaxFolderSize())).execute(), format);
    }

    /**
     * Method to update {@code "IMAP"} settings
     *
     * @param enable:          whether {@code "IMAP"} is enabled for the account
     * @param autoExpunge:     if this value is true, {@code "Gmail"} will immediately expunge a message when it is marked as deleted in {@code "IMAP"}. Otherwise, {@code "Gmail"} will wait for an update from the client before expunging messages marked as deleted
     * @param expungeBehavior: the action that will be executed on a message when it is marked as deleted and expunged from the last visible {@code "IMAP"} folder
     * @param maxFolderSize:   an optional limit on the number of messages that an {@code "IMAP"} folder may contain. Legal values are 0, 1000, 2000, 5000 or 10000. A value of zero is interpreted to mean that there is no limit
     * @return {@code "IMAP"} settings updated as {@link ImapSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ImapSettings updateImap(boolean enable, boolean autoExpunge, ExpungeBehavior expungeBehavior,
                                   int maxFolderSize) throws IOException {
        return updateImap(enable, autoExpunge, expungeBehavior, maxFolderSize, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "IMAP"} settings
     *
     * @param enable:          whether {@code "IMAP"} is enabled for the account
     * @param autoExpunge:     if this value is true, {@code "Gmail"} will immediately expunge a message when it is marked as deleted in {@code "IMAP"}. Otherwise, {@code "Gmail"} will wait for an update from the client before expunging messages marked as deleted
     * @param expungeBehavior: the action that will be executed on a message when it is marked as deleted and expunged from the last visible {@code "IMAP"} folder
     * @param maxFolderSize:   an optional limit on the number of messages that an {@code "IMAP"} folder may contain. Legal values are 0, 1000, 2000, 5000 or 10000. A value of zero is interpreted to mean that there is no limit
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateImap">
     * users.settings.updateImap</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateImap(boolean enable, boolean autoExpunge, ExpungeBehavior expungeBehavior, int maxFolderSize,
                            ReturnFormat format) throws IOException {
        return returnImap(settings.updateImap(userId, new com.google.api.services.gmail.model.ImapSettings()
                .setEnabled(enable)
                .setAutoExpunge(autoExpunge)
                .setExpungeBehavior(expungeBehavior.name())
                .setMaxFolderSize(maxFolderSize)).execute(), format);
    }

    /**
     * Method to create an {@code "IMAP"} settings object
     *
     * @param imapSettings: {@code "IMAP"} settings obtained from Google's response
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return {@code "IMAP"} settings settings as {@code "format"} defines
     **/
    private <T> T returnImap(com.google.api.services.gmail.model.ImapSettings imapSettings, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(imapSettings);
            case LIBRARY_OBJECT:
                return (T) new ImapSettings(new JSONObject(imapSettings));
            default:
                return (T) imapSettings.toString();
        }
    }

    /**
     * Method to update language settings
     *
     * @param languageUpdated: Language settings for an account. These settings correspond to the <a href="https://support.google.com/mail/answer/17091">Language settings</a> feature in the web interface
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return language settings as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateLanguage">
     * users.settings.updateLanguage</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateLanguage(String languageUpdated, ReturnFormat format) throws IOException {
        return returnLanguage(settings.updateLanguage(userId, new LanguageSettings()
                .setDisplayLanguage(languageUpdated)).execute(), format);
    }

    /**
     * Method to create a language settings object
     *
     * @param languageSettings: language settings obtained from Google's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return language settings settings as {@code "format"} defines
     **/
    private <T> T returnLanguage(LanguageSettings languageSettings, ReturnFormat format) {
        JSONObject jLanguage = new JSONObject(languageSettings);
        switch (format) {
            case JSON:
            case LIBRARY_OBJECT:
                return (T) jLanguage;
            default:
                return (T) jLanguage.getString("displayLanguage");
        }
    }

    /**
     * Method to update {@code "POP"} settings <br>
     * This method allows to update {@code "accessWindow"} setting without change all the current {@code "POP"} settings
     *
     * @param accessWindow: the range of messages which are accessible via {@link "POP"}
     * @return {@code "POP"} settings updated as {@link PopSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public PopSettings updatePopAccessWindow(AccessWindow accessWindow) throws IOException {
        return updatePopAccessWindow(accessWindow, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "POP"} settings <br>
     * This method allows to update {@code "accessWindow"} setting without change all the current {@code "POP"} settings
     *
     * @param accessWindow: the range of messages which are accessible via {@link "POP"}
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return {@code "POP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updatePopAccessWindow(AccessWindow accessWindow, ReturnFormat format) throws IOException {
        PopSettings actualPopSettings = getPopSettings();
        actualPopSettings.setAccessWindow(accessWindow);
        return updatePopSettings(actualPopSettings, format);
    }

    /**
     * Method to update {@code "POP"} settings <br>
     * This method allows to update {@code "disposition"} setting without change all the current {@code "POP"} settings
     *
     * @param disposition: the action that will be executed on a message after it has been fetched via {@link "POP"}
     * @return {@code "POP"} settings updated as {@link PopSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public PopSettings updatePopDisposition(Disposition disposition) throws IOException {
        return updatePopDisposition(disposition, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "POP"} settings <br>
     * This method allows to update {@code "disposition"} setting without change all the current {@code "POP"} settings
     *
     * @param disposition: the action that will be executed on a message after it has been fetched via {@link "POP"}
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return {@code "POP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updatePopDisposition(Disposition disposition, ReturnFormat format) throws IOException {
        PopSettings actualPopSettings = getPopSettings();
        actualPopSettings.setDisposition(disposition);
        return updatePopSettings(actualPopSettings, format);
    }

    /**
     * Method to update {@code "POP"} settings <br>
     *
     * @param popSettingsUpdated: {@code "POP"} settings for an account
     * @return {@code "POP"} settings updated as {@link PopSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public PopSettings updatePopSettings(PopSettings popSettingsUpdated) throws IOException {
        return updatePopSettings(popSettingsUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "POP"} settings <br>
     *
     * @param popSettingsUpdated: {@code "POP"} settings for an account
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return {@code "POP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updatePopSettings(PopSettings popSettingsUpdated, ReturnFormat format) throws IOException {
        return returnPopSettings(settings.updatePop(userId, new com.google.api.services.gmail.model.PopSettings()
                .setAccessWindow(popSettingsUpdated.getAccessWindow().toString())
                .setDisposition(popSettingsUpdated.getDisposition().toString())).execute(), format);
    }

    /**
     * Method to update {@code "POP"} settings <br>
     * This method allows to update {@code "accessWindow"} setting without change all the current {@code "POP"} settings
     *
     * @param accessWindow: the range of messages which are accessible via {@link "POP"}
     * @param disposition:  the action that will be executed on a message after it has been fetched via {@link "POP"}
     * @return {@code "POP"} settings updated as {@link PopSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public PopSettings updatePopSettings(AccessWindow accessWindow, Disposition disposition) throws IOException {
        return updatePopSettings(accessWindow, disposition, LIBRARY_OBJECT);
    }

    /**
     * Method to update {@code "POP"} settings <br>
     * This method allows to update {@code "accessWindow"} setting without change all the current {@code "POP"} settings
     *
     * @param accessWindow: the range of messages which are accessible via {@link "POP"}
     * @param disposition:  the action that will be executed on a message after it has been fetched via {@link "POP"}
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return {@code "POP"} settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updatePop">
     * users.settings.updatePop</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updatePopSettings(AccessWindow accessWindow, Disposition disposition, ReturnFormat format) throws IOException {
        return returnPopSettings(settings.updatePop(userId, new com.google.api.services.gmail.model.PopSettings()
                .setAccessWindow(accessWindow.name()).setDisposition(disposition.name())).execute(), format);
    }

    /**
     * Method to create an {@code "POP"} settings object
     *
     * @param popSettings: {@code "POP"} settings obtained from Google's response
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return {@code "POP"} settings settings as {@code "format"} defines
     **/
    private <T> T returnPopSettings(com.google.api.services.gmail.model.PopSettings popSettings, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(popSettings);
            case LIBRARY_OBJECT:
                return (T) new PopSettings(new JSONObject(popSettings));
            default:
                return (T) popSettings.toString();
        }
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable vacation responder settings without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings enableVacationPlainTextAutoReply(String responseSubject, String responsePlainText) throws IOException {
        return enableVacationPlainTextAutoReply(responseSubject, responsePlainText, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable vacation responder settings without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T enableVacationPlainTextAutoReply(String responseSubject, String responsePlainText,
                                                  ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableAutoReply();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable vacation responder settings without change all the current vacation responder settings
     *
     * @param responseSubject: optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responseHtml:    response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     *                         If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"} are specified, {@code "responseBodyHtml"} will be used
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings enableVacationHtmlAutoReply(String responseSubject, String responseHtml) throws IOException {
        return enableVacationHtmlAutoReply(responseSubject, responseHtml, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable vacation responder settings without change all the current vacation responder settings
     *
     * @param responseSubject: optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responseHtml:    response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     *                         If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"} are specified, {@code "responseBodyHtml"} will be used
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T enableVacationHtmlAutoReply(String responseSubject, String responseHtml, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableAutoReply();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable vacation responder settings without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @param responseHtml:      response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     *                           If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"} are specified, {@code "responseBodyHtml"} will be used
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings enableVacationAutoReply(String responseSubject, String responsePlainText,
                                                    String responseHtml) throws IOException {
        return enableVacationAutoReply(responseSubject, responsePlainText, responseHtml, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable vacation responder settings without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @param responseHtml:      response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     *                           If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"} are specified, {@code "responseBodyHtml"} will be used
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T enableVacationAutoReply(String responseSubject, String responsePlainText, String responseHtml,
                                         ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableAutoReply();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update only {@code "responseSubject"} params without change all the current vacation responder settings
     *
     * @param responseSubject: optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationResponseSubject(String responseSubject) throws IOException {
        return updateVacationResponseSubject(responseSubject, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update only {@code "responseSubject"} params without change all the current vacation responder settings
     *
     * @param responseSubject: optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationResponseSubject(String responseSubject, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseSubject(responseSubject);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update only {@code "responsePlainText"} params without change all the current vacation responder settings
     *
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationResponsePlainText(String responsePlainText) throws IOException {
        return updateVacationResponsePlainText(responsePlainText, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update only {@code "responsePlainText"} params without change all the current vacation responder settings
     *
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationResponsePlainText(String responsePlainText, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "responseSubject"} and {@code "responsePlainText"} params without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationResponsePlainText(String responseSubject, String responsePlainText) throws IOException {
        return updateVacationResponsePlainText(responseSubject, responsePlainText, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "responseSubject"} and {@code "responsePlainText"} params without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationResponsePlainText(String responseSubject, String responsePlainText,
                                                 ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update only {@code "responseBodyHtml"} params without change all the current vacation responder settings
     *
     * @param responseHtml: response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationResponseHtml(String responseHtml) throws IOException {
        return updateVacationResponseHtml(responseHtml, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update only {@code "responseBodyHtml"} params without change all the current vacation responder settings
     *
     * @param responseHtml: response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     * @param format:       return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationResponseHtml(String responseHtml, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "responseSubject"} and {@code "responseBodyHtml"} params without change all the current vacation responder settings
     *
     * @param responseSubject: optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responseHtml:    response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationResponseHtml(String responseSubject, String responseHtml) throws IOException {
        return updateVacationResponseHtml(responseSubject, responseHtml, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "responseSubject"} and {@code "responseBodyHtml"} params without change all the current vacation responder settings
     *
     * @param responseSubject: optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responseHtml:    response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationResponseHtml(String responseSubject, String responseHtml, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "responseSubject"}, {@code "responsePlainText"}, and {@code "responseHtml"} params
     * without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @param responseHtml:      response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationResponseDetails(String responseSubject, String responsePlainText,
                                                          String responseHtml) throws IOException {
        return updateVacationResponseDetails(responseSubject, responsePlainText, responseHtml, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "responseSubject"}, {@code "responsePlainText"}, and {@code "responseHtml"} params
     * without change all the current vacation responder settings
     *
     * @param responseSubject:   optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText: response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                           are specified, {@code "responseBodyHtml"}  will be used
     * @param responseHtml:      response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationResponseDetails(String responseSubject, String responsePlainText, String responseHtml,
                                               ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.VacationSettings vSettings = settings.getVacation(userId).execute();
        settings.updateVacation(userId, vSettings.setResponseBodyHtml(responseHtml)).execute();
        vSettings.setResponseBodyHtml(null);
        return returnVacationSettings(settings.updateVacation(userId, vSettings
                        .setResponseSubject(responseSubject)
                        .setResponseBodyPlainText(responsePlainText))
                .execute().setResponseBodyHtml(responseHtml), format);
    }

    /**
     * Method to disable vacation responder settings <br>
     * Any params required
     *
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings disableVacationAutoReply() throws IOException {
        return disableVacationAutoReply(LIBRARY_OBJECT);
    }

    /**
     * Method to disable vacation responder settings <br>
     * Any params required
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T disableVacationAutoReply(ReturnFormat format) throws IOException {
        return returnVacationSettings(settings.updateVacation(userId, new com.google.api.services.gmail.model.VacationSettings()
                .setEnableAutoReply(false)).execute(), format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable {@code "restrictToContacts"} param without change all the current vacation responder settings <br>
     * Any params required
     *
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings enableRestrictToContacts() throws IOException {
        return enableRestrictToContacts(LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable {@code "restrictToContacts"} param without change all the current vacation responder settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T enableRestrictToContacts(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableRestrictToContacts();
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to disable {@code "restrictToContacts"} param without change all the current vacation responder settings <br>
     * Any params required
     *
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings disableRestrictToContacts() throws IOException {
        return disableRestrictToContacts(LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to disable {@code "restrictToContacts"} param without change all the current vacation responder settings <br>
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T disableRestrictToContacts(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.disableRestrictToContacts();
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable {@code "restrictToDomain"} param without change all the current vacation responder settings <br>
     * Any params required
     *
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings enableRestrictToDomain() throws IOException {
        return enableRestrictToDomain(LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to enable {@code "restrictToDomain"} param without change all the current vacation responder settings
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T enableRestrictToDomain(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableRestrictToDomain();
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to disable {@code "restrictToDomain"} param without change all the current vacation responder settings <br>
     * Any params required
     *
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings disableRestrictToDomain() throws IOException {
        return disableRestrictToDomain(LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to disable {@code "restrictToDomain"} param without change all the current vacation responder settings <br>
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T disableRestrictToDomain(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.disableRestrictToDomain();
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "startTime"} param without change all the current vacation responder settings
     *
     * @param startTime an optional start time for sending auto-replies (epoch ms). When this is specified,
     *                  {@code "Gmail"} will automatically reply only to messages that it receives after the start time.
     *                  If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationStartTime(long startTime) throws IOException {
        return updateVacationStartTime(startTime, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "startTime"} param without change all the current vacation responder settings
     *
     * @param startTime an optional start time for sending auto-replies (epoch ms). When this is specified,
     *                  {@code "Gmail"} will automatically reply only to messages that it receives after the start time.
     *                  If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationStartTime(long startTime, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setStartTime(startTime);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "endTime"} param without change all the current vacation responder settings
     *
     * @param endTime: an optional end time for sending auto-replies (epoch ms). When this is specified,
     *                 {@code "Gmail"} will automatically reply only to messages that it receives before the end time.
     *                 If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationEndTime(long endTime) throws IOException {
        return updateVacationEndTime(endTime, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "endTime"} param without change all the current vacation responder settings
     *
     * @param endTime: an optional end time for sending auto-replies (epoch ms). When this is specified,
     *                 {@code "Gmail"} will automatically reply only to messages that it receives before the end time.
     *                 If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationEndTime(long endTime, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setEndTime(endTime);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "startTime"} param without change all the current vacation responder settings
     *
     * @param startTime an optional start time for sending auto-replies (epoch ms). When this is specified,
     *                  {@code "Gmail"} will automatically reply only to messages that it receives after the start time.
     *                  If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param endTime:  an optional end time for sending auto-replies (epoch ms). When this is specified,
     *                  {@code "Gmail"} will automatically reply only to messages that it receives before the end time.
     *                  If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationTime(long startTime, long endTime) throws IOException {
        return updateVacationTime(startTime, endTime, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings <br>
     * This method allows to update {@code "startTime"} param without change all the current vacation responder settings
     *
     * @param startTime an optional start time for sending auto-replies (epoch ms). When this is specified,
     *                  {@code "Gmail"} will automatically reply only to messages that it receives after the start time.
     *                  If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param endTime:  an optional end time for sending auto-replies (epoch ms). When this is specified,
     *                  {@code "Gmail"} will automatically reply only to messages that it receives before the end time.
     *                  If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationTime(long startTime, long endTime, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setStartTime(startTime);
        actualVacationSettings.setEndTime(endTime);
        return updateVacationSettings(actualVacationSettings, format);
    }

    /**
     * Method to update vacation responder settings
     *
     * @param vacationSettings: vacation auto-reply settings for an account. These settings correspond to the "Vacation responder" feature in the web interface
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationSettings(VacationSettings vacationSettings) throws IOException {
        return updateVacationSettings(vacationSettings, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings
     *
     * @param vacationSettings: vacation auto-reply settings for an account. These settings correspond to the "Vacation responder" feature in the web interface
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationSettings(VacationSettings vacationSettings, ReturnFormat format) throws IOException {
        return returnVacationSettings(settings.updateVacation(userId, new com.google.api.services.gmail.model.VacationSettings()
                .setEnableAutoReply(vacationSettings.isEnableAutoReply())
                .setResponseSubject(vacationSettings.getResponseSubject())
                .setResponseBodyPlainText(vacationSettings.getResponseBodyPlainText())
                .setResponseBodyHtml(vacationSettings.getResponseBodyHtml())
                .setRestrictToContacts(vacationSettings.isRestrictToContacts())
                .setRestrictToDomain(vacationSettings.isRestrictToDomain())
                .setStartTime(vacationSettings.getStartTime())
                .setEndTime(vacationSettings.getEndTime())).execute(), format);
    }

    /**
     * Method to update vacation responder settings
     *
     * @param enableAutoReply:           flag that controls whether Gmail automatically replies to messages
     * @param responseSubject:           optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText:response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                                   are specified, {@code "responseBodyHtml"}  will be used
     * @param responseHtml:response      body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     *                                   If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"} are specified, {@code "responseBodyHtml"}
     *                                   will be used
     * @param restrictToContacts:        flag that determines whether responses are sent to recipients who are not in the user's list of contacts
     * @param restrictToDomain:          flag that determines whether responses are sent to recipients who are outside the user's domain. This feature is only available for G Suite users
     * @param startTime                  an optional start time for sending auto-replies (epoch ms). When this is specified,
     *                                   {@code "Gmail"} will automatically reply only to messages that it receives after the start time.
     *                                   If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param endTime:                   an optional end time for sending auto-replies (epoch ms). When this is specified,
     *                                   {@code "Gmail"} will automatically reply only to messages that it receives before the end time.
     *                                   If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @return vacation responder settings updated as {@link VacationSettings} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public VacationSettings updateVacationSettings(boolean enableAutoReply, String responseSubject, String responsePlainText,
                                                   String responseHtml, boolean restrictToContacts, boolean restrictToDomain,
                                                   long startTime, long endTime) throws IOException {
        return updateVacationSettings(enableAutoReply, responseSubject, responsePlainText, responseHtml, restrictToContacts,
                restrictToDomain, startTime, endTime, LIBRARY_OBJECT);
    }

    /**
     * Method to update vacation responder settings
     *
     * @param enableAutoReply:           flag that controls whether Gmail automatically replies to messages
     * @param responseSubject:           optional text to prepend to the subject line in vacation responses. In order to enable auto-replies, either the response subject or the response body must be nonempty
     * @param responsePlainText:response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     *                                   are specified, {@code "responseBodyHtml"}  will be used
     * @param responseHtml:response      body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     *                                   If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"} are specified, {@code "responseBodyHtml"}
     *                                   will be used
     * @param restrictToContacts:        flag that determines whether responses are sent to recipients who are not in the user's list of contacts
     * @param restrictToDomain:          flag that determines whether responses are sent to recipients who are outside the user's domain. This feature is only available for G Suite users
     * @param startTime                  an optional start time for sending auto-replies (epoch ms). When this is specified,
     *                                   {@code "Gmail"} will automatically reply only to messages that it receives after the start time.
     *                                   If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param endTime:                   an optional end time for sending auto-replies (epoch ms). When this is specified,
     *                                   {@code "Gmail"} will automatically reply only to messages that it receives before the end time.
     *                                   If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     * @param format:                    return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings/updateVacation">
     * users.settings.updateVacation</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T updateVacationSettings(boolean enableAutoReply, String responseSubject, String responsePlainText,
                                        String responseHtml, boolean restrictToContacts, boolean restrictToDomain,
                                        long startTime, long endTime, ReturnFormat format) throws IOException {
        return returnVacationSettings(settings.updateVacation(userId, new com.google.api.services.gmail.model.VacationSettings()
                .setEnableAutoReply(enableAutoReply)
                .setResponseSubject(responseSubject)
                .setResponseBodyPlainText(responsePlainText)
                .setResponseBodyHtml(responseHtml)
                .setRestrictToContacts(restrictToContacts)
                .setRestrictToDomain(restrictToDomain)
                .setStartTime(startTime)
                .setEndTime(endTime)).execute(), format);
    }

    /**
     * Method to create a vacation responder settings object
     *
     * @param vacationSettings: vacation responder settings obtained from Google's response
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return vacation responder settings as {@code "format"} defines
     **/
    private <T> T returnVacationSettings(com.google.api.services.gmail.model.VacationSettings vacationSettings,
                                         ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(vacationSettings);
            case LIBRARY_OBJECT:
                return (T) new VacationSettings(new JSONObject(vacationSettings));
            default:
                return (T) vacationSettings.toString();
        }
    }

    /**
     * Method to add a delegate with its verification status set directly to accepted, without sending any verification email. The delegate user must be a member of the same G Suite organization as the delegator user.
     * Gmail imposes limitations on the number of delegates and delegators each user in a G Suite organization can have. These limits depend on your organization, but in general each user can have up to 25 delegates and up to 10 delegators.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * Also note that when a new delegate is created, there may be up to a one-minute delay before the new delegate is available for use.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegate: Settings for a delegate. Delegates can read, send, and delete messages, as well as view and add contacts, for the delegator's account
     * @return delegate as {@link Delegate} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/create">
     * users.settings.delegates.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Delegate createDelegate(Delegate delegate) throws IOException {
        return createDelegate(delegate, LIBRARY_OBJECT);
    }

    /**
     * Method to add a delegate with its verification status set directly to accepted, without sending any verification email. The delegate user must be a member of the same G Suite organization as the delegator user.
     * Gmail imposes limitations on the number of delegates and delegators each user in a G Suite organization can have. These limits depend on your organization, but in general each user can have up to 25 delegates and up to 10 delegators.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * Also note that when a new delegate is created, there may be up to a one-minute delay before the new delegate is available for use.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegate: Settings for a delegate. Delegates can read, send, and delete messages, as well as view and add contacts, for the delegator's account
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return delegate as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/create">
     * users.settings.delegates.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDelegate(Delegate delegate, ReturnFormat format) throws IOException {
        return returnDelegate(delegates.create(userId, new com.google.api.services.gmail.model.Delegate()
                .setDelegateEmail(delegate.getDelegateEmail())
                .setVerificationStatus(delegate.getVerificationStatus().name())).execute(), format);
    }

    /**
     * Method to add a delegate with its verification status set directly to accepted, without sending any verification email. The delegate user must be a member of the same G Suite organization as the delegator user.
     * Gmail imposes limitations on the number of delegates and delegators each user in a G Suite organization can have. These limits depend on your organization, but in general each user can have up to 25 delegates and up to 10 delegators.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * Also note that when a new delegate is created, there may be up to a one-minute delay before the new delegate is available for use.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegateEmail:      the email address of the delegate
     * @param verificationStatus: indicates whether this address has been verified and can act as a delegate for the account. Read-only
     * @return delegate as {@link Delegate} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/create">
     * users.settings.delegates.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Delegate createDelegate(String delegateEmail, VerificationStatus verificationStatus) throws IOException {
        return createDelegate(delegateEmail, verificationStatus, LIBRARY_OBJECT);
    }

    /**
     * Method to add a delegate with its verification status set directly to accepted, without sending any verification email. The delegate user must be a member of the same G Suite organization as the delegator user.
     * Gmail imposes limitations on the number of delegates and delegators each user in a G Suite organization can have. These limits depend on your organization, but in general each user can have up to 25 delegates and up to 10 delegators.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * Also note that when a new delegate is created, there may be up to a one-minute delay before the new delegate is available for use.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegateEmail:      the email address of the delegate
     * @param verificationStatus: indicates whether this address has been verified and can act as a delegate for the account. Read-only
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return delegate as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/create">
     * users.settings.delegates.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createDelegate(String delegateEmail, VerificationStatus verificationStatus,
                                ReturnFormat format) throws IOException {
        return returnDelegate(delegates.create(userId, new com.google.api.services.gmail.model.Delegate()
                .setDelegateEmail(delegateEmail).setVerificationStatus(verificationStatus.name())).execute(), format);
    }

    /**
     * Method to remove the specified delegate (which can be of any verification status), and revokes any verification that may have been required for using it.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegateToDelete: delegate to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/delete">
     * users.settings.delegates.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteDelegate(Delegate delegateToDelete) {
        return deleteDelegate(delegateToDelete.getDelegateEmail());
    }

    /**
     * Method to remove the specified delegate (which can be of any verification status), and revokes any verification that may have been required for using it.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegateEmail: the email address of the user to be removed as a delegate
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/delete">
     * users.settings.delegates.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteDelegate(String delegateEmail) {
        try {
            delegates.delete(userId, delegateEmail).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get the specified delegate.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegateEmail: the email address of the user whose delegate relationship is to be retrieved
     * @return delegate requested as {@link Delegate} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/get">
     * users.settings.delegates.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Delegate getDelegate(String delegateEmail) throws IOException {
        return getDelegate(delegateEmail, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified delegate.
     * Note that a delegate user must be referred to by their primary email address, and not an email alias.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param delegateEmail: the email address of the user whose delegate relationship is to be retrieved
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return delegate requested as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/get">
     * users.settings.delegates.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDelegate(String delegateEmail, ReturnFormat format) throws IOException {
        return returnDelegate(delegates.get(userId, delegateEmail).execute(), format);
    }

    /**
     * Method to create a delegate object
     *
     * @param delegate: delegate obtained from Google's response
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return delegate as {@code "format"} defines
     **/
    private <T> T returnDelegate(com.google.api.services.gmail.model.Delegate delegate, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(delegate);
            case LIBRARY_OBJECT:
                return (T) new Delegate(new JSONObject(delegate));
            default:
                return (T) delegate.toString();
        }
    }

    /**
     * Method to get list of the delegates for the specified account.
     * This method is only available to service account clients that have been delegated domain-wide authority <br>
     * Any params required
     *
     * @return list of delegates requested as {@link Collection} of {@link Delegate} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/list">
     * users.settings.delegates.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Collection<Delegate> getDelegatesList() throws IOException {
        return getDelegatesList(LIBRARY_OBJECT);
    }

    /**
     * Method to get list of the delegates for the specified account.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return list of delegates requested as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates/list">
     * users.settings.delegates.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getDelegatesList(ReturnFormat format) throws IOException {
        ListDelegatesResponse delegates = this.delegates.list(userId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(delegates);
            case LIBRARY_OBJECT:
                ArrayList<Delegate> delegatesList = new ArrayList<>();
                if (delegates != null)
                    for (com.google.api.services.gmail.model.Delegate delegate : delegates.getDelegates())
                        delegatesList.add(new Delegate(new JSONObject(delegate)));
                return (T) delegatesList;
            default:
                return (T) delegates.toString();
        }
    }

    /**
     * Method to create a filter. Note: you can only create a maximum of 1,000 filters
     *
     * @param filter: filter with details to create
     * @return filter created as {@link Filter} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/create">
     * users.settings.filters.create</a>
     * @implSpec you have to load {@code "filter"} with custom details, but if you don't need some details you must
     * insert:
     * <ul>
     *     <li>
     *         null: for objects
     *     </li>
     *     <li>
     *         0 for integers
     *     </li>
     *     <li>
     *         false for booleans
     *     </li>
     * </ul>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Filter createFilter(Filter filter) throws IOException {
        return createFilter(filter, LIBRARY_OBJECT);
    }

    /**
     * Method to create a filter. Note: you can only create a maximum of 1,000 filters
     *
     * @param filter: filter with details to create
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return filter created as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/create">
     * users.settings.filters.create</a>
     * @implSpec you have to load {@code "filter"} with custom details, but if you don't need some details you must
     * insert:
     * <ul>
     *     <li>
     *         null: for objects
     *     </li>
     *     <li>
     *         0 for integers
     *     </li>
     *     <li>
     *         false for booleans
     *     </li>
     * </ul>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createFilter(Filter filter, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Filter gFilter = new com.google.api.services.gmail.model.Filter();
        JSONObject filterSource = new JSONObject(filter);
        for (String key : filterSource.keySet()) {
            Object value = filterSource.get(key);
            if (value instanceof JSONObject) {
                FilterCriteria filterCriteria = null;
                FilterAction filterAction = null;
                if (key.equals("criteria"))
                    filterCriteria = new FilterCriteria();
                else
                    filterAction = new FilterAction();
                for (String vKey : ((JSONObject) value).keySet()) {
                    if (filterCriteria != null) {
                        Object criteriaValue = JsonHelper.get((JSONObject) value, vKey);
                        if (criteriaValue instanceof Filter.Criteria.SizeComparison)
                            gFilter.setCriteria(filterCriteria.set(vKey, ((Filter.Criteria.SizeComparison) criteriaValue).name()));
                        else
                            gFilter.setCriteria(filterCriteria.set(vKey, criteriaValue));
                    } else {
                        Object actionValue = JsonHelper.get((JSONObject) value, vKey);
                        if (actionValue instanceof JSONArray)
                            gFilter.setAction(filterAction.set(vKey, ((JSONArray) actionValue).toList()));
                        else
                            gFilter.setAction(filterAction.set(vKey, actionValue));
                    }
                }
            } else
                gFilter.set(key, value);
        }
        return returnFilter(filters.create(userId, gFilter).execute(), format);
    }

    /**
     * Method to immediately and permanently deletes the specified filter
     *
     * @param filterToDelete: filter to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/delete">
     * users.settings.filters.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteFilter(Filter filterToDelete) {
        return deleteFilter(filterToDelete.getId());
    }

    /**
     * Method to immediately and permanently deletes the specified filter
     *
     * @param filterIdToDelete: the ID of the filter to be deleted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/delete">
     * users.settings.filters.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteFilter(String filterIdToDelete) {
        try {
            filters.delete(userId, filterIdToDelete).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get a filter
     *
     * @param filterId: the ID of the filter to be fetched
     * @return filter requested as {@link Filter} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/get">
     * users.settings.filters.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Filter getFilter(String filterId) throws IOException {
        return getFilter(filterId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a filter
     *
     * @param filterId: the ID of the filter to be fetched
     * @param format:   return type formatter -> {@link ReturnFormat}
     * @return filter requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/get">
     * users.settings.filters.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getFilter(String filterId, ReturnFormat format) throws IOException {
        return returnFilter(filters.get(userId, filterId).execute(), format);
    }

    /**
     * Method to create a filter object
     *
     * @param filter: filter obtained from Google's response
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return filter as {@code "format"} defines
     **/
    private <T> T returnFilter(com.google.api.services.gmail.model.Filter filter, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(filter);
            case LIBRARY_OBJECT:
                return (T) new Filter(new JSONObject(filter));
            default:
                return (T) filter.toString();
        }
    }

    /**
     * Method to get a list of the message filters of a Gmail user <br>
     * Any params required
     *
     * @return list of filters as {@link Collection} of {@link Filter} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/list">
     * users.settings.filters.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Collection<Filter> getFiltersList() throws IOException {
        return getFiltersList(LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the message filters of a Gmail user <br>
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return list of filters as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters/list">
     * users.settings.filters.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getFiltersList(ReturnFormat format) throws IOException {
        ListFiltersResponse filters = this.filters.list(userId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(filters);
            case LIBRARY_OBJECT:
                ArrayList<Filter> filtersList = new ArrayList<>();
                if (filters != null)
                    for (com.google.api.services.gmail.model.Filter filter : filters.getFilter())
                        filtersList.add(new Filter(new JSONObject(filter)));
                return (T) filtersList;
            default:
                return (T) filters.toString();
        }
    }
    
    /**
     * Method to create a forwarding address. If ownership verification is required, a message will be sent to the recipient
     * and the resource's verification status will be set to {@code "pending"}; otherwise, the resource will be created with verification status
     * set to {@code "accepted"} <br>
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param forwardingAddress: forwarding address to create
     * @return forwarding address created as {@link ForwardingAddress} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/create">
     * users.settings.forwardingAddresses.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ForwardingAddress createForwardingAddress(ForwardingAddress forwardingAddress) throws IOException {
        return createForwardingAddress(forwardingAddress, LIBRARY_OBJECT);
    }

    /**
     * Method to create a forwarding address. If ownership verification is required, a message will be sent to the recipient
     * and the resource's verification status will be set to {@code "pending"}; otherwise, the resource will be created with verification status
     * set to {@code "accepted"} <br>
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param forwardingAddress: forwarding address to create
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return forwarding address created as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/create">
     * users.settings.forwardingAddresses.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createForwardingAddress(ForwardingAddress forwardingAddress, ReturnFormat format) throws IOException {
        return returnForwardingAddress(forwardingAddresses.create(userId, new com.google.api.services.gmail.model.ForwardingAddress()
                .setForwardingEmail(forwardingAddress.getForwardingEmail())
                .setVerificationStatus(forwardingAddress.getVerificationStatus().name())).execute(), format);
    }

    /**
     * Method to create a forwarding address. If ownership verification is required, a message will be sent to the recipient
     * and the resource's verification status will be set to {@code "pending"}; otherwise, the resource will be created with verification status
     * set to {@code "accepted"} <br>
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param forwardingEmail: an email address to which messages can be forwarded
     * @return forwarding address created as {@link ForwardingAddress} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/create">
     * users.settings.forwardingAddresses.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ForwardingAddress createForwardingAddress(String forwardingEmail) throws IOException {
        return createForwardingAddress(forwardingEmail, LIBRARY_OBJECT);
    }

    /**
     * Method to create a forwarding address. If ownership verification is required, a message will be sent to the recipient
     * and the resource's verification status will be set to {@code "pending"}; otherwise, the resource will be created with verification status
     * set to {@code "accepted"} <br>
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param forwardingEmail: an email address to which messages can be forwarded
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return forwarding address created as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/create">
     * users.settings.forwardingAddresses.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createForwardingAddress(String forwardingEmail, ReturnFormat format) throws IOException {
        return returnForwardingAddress(forwardingAddresses.create(userId, new com.google.api.services.gmail.model.ForwardingAddress()
                .setForwardingEmail(forwardingEmail)).execute(), format);
    }

    /**
     * Method to delete the specified forwarding address and revokes any verification that may have been required.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param forwardingAddressToDelete: forwarding address to be deleted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/delete">
     * users.settings.forwardingAddresses.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteForwardingAddress(ForwardingAddress forwardingAddressToDelete) {
        return deleteForwardingAddress(forwardingAddressToDelete.getForwardingEmail());
    }

    /**
     * Method to delete the specified forwarding address and revokes any verification that may have been required.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param forwardingEmailToDelete: the forwarding address to be deleted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/delete">
     * users.settings.forwardingAddresses.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteForwardingAddress(String forwardingEmailToDelete) {
        try {
            forwardingAddresses.delete(userId, forwardingEmailToDelete).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get the specified forwarding address
     *
     * @param forwardingEmail: the forwarding address to be retrieved
     * @return forwarding address requested as {@link ForwardingAddress} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/get">
     * users.settings.forwardingAddresses.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public ForwardingAddress getForwardingAddress(String forwardingEmail) throws IOException {
        return getForwardingAddress(forwardingEmail, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified forwarding address
     *
     * @param forwardingEmail: the forwarding address to be retrieved
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return forwarding address requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/get">
     * users.settings.forwardingAddresses.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getForwardingAddress(String forwardingEmail, ReturnFormat format) throws IOException {
        return returnForwardingAddress(forwardingAddresses.get(userId, forwardingEmail).execute(), format);
    }

    /**
     * Method to create a forwarding address object
     *
     * @param forwardingAddress: forwarding address obtained from Google's response
     * @param format:            return type formatter -> {@link ReturnFormat}
     * @return forwarding address as {@code "format"} defines
     **/
    private <T> T returnForwardingAddress(com.google.api.services.gmail.model.ForwardingAddress forwardingAddress,
                                          ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(forwardingAddress);
            case LIBRARY_OBJECT:
                return (T) new ForwardingAddress(new JSONObject(forwardingAddress));
            default:
                return (T) forwardingAddress.toString();
        }
    }

    /**
     * Method to get a list of the forwarding addresses for the specified account <br>
     * Any params required
     *
     * @return list of forwarding addresses as {@link Collection} of {@link ForwardingAddress} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/list">
     * users.settings.forwardingAddresses.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Collection<ForwardingAddress> getForwardingAddressesList() throws IOException {
        return getForwardingAddressesList(LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the forwarding addresses for the specified account <br>
     * Any params required
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return list of forwarding addresses as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses/list">
     * users.settings.forwardingAddresses.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getForwardingAddressesList(ReturnFormat format) throws IOException {
        ListForwardingAddressesResponse addresses = this.forwardingAddresses.list(userId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(addresses);
            case LIBRARY_OBJECT:
                ArrayList<ForwardingAddress> addressesList = new ArrayList<>();
                if (addresses != null)
                    for (com.google.api.services.gmail.model.ForwardingAddress address : addresses.getForwardingAddresses())
                        addressesList.add(new ForwardingAddress(new JSONObject(address)));
                return (T) addressesList;
            default:
                return (T) addresses.toString();
        }
    }

    /**
     * Method to create a custom {@code "from"} send-as alias. If an {@code "SMTP MSA"} is specified, {@code "Gmail"} will attempt to connect to the
     * SMTP service to validate the configuration before creating the alias. If ownership verification is required for the alias,
     * a message will be sent to the email address and the resource's verification status will be set to pending; otherwise,
     * the resource will be created with verification status set to accepted. If a signature is provided, {@code "Gmail"} will sanitize
     * the {@code "HTML"} before saving it with the alias
     *
     * @param sendAsToCreate: send-as to create
     * @return send-as as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/create">
     * users.settings.sendAs.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public SendAs createSendAs(SendAs sendAsToCreate) throws IOException {
        return createSendAs(sendAsToCreate, LIBRARY_OBJECT);
    }

    /**
     * Method to create a custom {@code "from"} send-as alias. If an {@code "SMTP MSA"} is specified, {@code "Gmail"} will attempt to connect to the
     * SMTP service to validate the configuration before creating the alias. If ownership verification is required for the alias,
     * a message will be sent to the email address and the resource's verification status will be set to pending; otherwise,
     * the resource will be created with verification status set to accepted. If a signature is provided, {@code "Gmail"} will sanitize
     * the {@code "HTML"} before saving it with the alias
     *
     * @param sendAsToCreate: send-as to create
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return send-as created as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/create">
     * users.settings.sendAs.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public <T> T createSendAs(SendAs sendAsToCreate, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.SendAs gSendAs = new com.google.api.services.gmail.model.SendAs()
                .setSendAsEmail(sendAsToCreate.getSendAsEmail())
                .setDisplayName(sendAsToCreate.getDisplayName())
                .setReplyToAddress(sendAsToCreate.getReplyToAddress())
                .setSignature(sendAsToCreate.getSignature())
                .setIsDefault(sendAsToCreate.isDefault())
                .setTreatAsAlias(sendAsToCreate.isTreatAsAlias());
        SendAs.SmtpMsa smtpMsaToCreate = sendAsToCreate.getSmtpMsa();
        if (smtpMsaToCreate != null) {
            currentUsername = smtpMsaToCreate.getUsername();
            currentPassword = smtpMsaToCreate.getPassword();
            gSendAs.setSmtpMsa(new SmtpMsa().setHost(smtpMsaToCreate.getHost())
                    .setPort(smtpMsaToCreate.getPort())
                    .setUsername(currentUsername)
                    .setPassword(currentPassword)
                    .setSecurityMode(smtpMsaToCreate.getSecurityMode().name()));
        }
        return returnSendAs(sendAs.create(userId, gSendAs).execute(), format);
    }

    /**
     * Method to delete the specified send-as alias. Revokes any verification that may have been required for using it.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param sendAsToDelete: send-as to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/delete">
     * users.settings.sendAs.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteSendAs(SendAs sendAsToDelete) {
        return deleteSendAs(sendAsToDelete.getSendAsEmail());
    }

    /**
     * Method to delete the specified send-as alias. Revokes any verification that may have been required for using it.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param sendAsEmailToDelete: the send-as alias to be deleted
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/delete">
     * users.settings.sendAs.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteSendAs(String sendAsEmailToDelete) {
        try {
            sendAs.delete(userId, sendAsEmailToDelete).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get the specified send-as alias. Fails with an HTTP 404 error if the specified address is not a member of the collection
     *
     * @param sendAsEmailToGet: the send-as alias to be retrieved
     * @return send-as requested as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/get">
     * users.settings.sendAs.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs getSendAs(String sendAsEmailToGet) throws IOException {
        return getSendAs(sendAsEmailToGet, LIBRARY_OBJECT);
    }

    /**
     * Method to get the specified send-as alias. Fails with an HTTP 404 error if the specified address is not a member of the collection
     *
     * @param sendAsEmailToGet: the send-as alias to be retrieved
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return send-as requested as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/get">
     * users.settings.sendAs.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getSendAs(String sendAsEmailToGet, ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.get(userId, sendAsEmailToGet).execute(), format);
    }

    /**
     * Method to get a list of the send-as aliases for the specified account. The result includes the primary send-as address
     * associated with the account as well as any custom {@code "from"} aliases <br>
     * Any params required
     *
     * @return send-as list as {@link Collection} of {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/list">
     * users.settings.sendAs.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Collection<SendAs> getSendAsList() throws IOException {
        return getSendAsList(LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of the send-as aliases for the specified account. The result includes the primary send-as address
     * associated with the account as well as any custom {@code "from"} aliases <br>
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return send-as list as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/list">
     * users.settings.sendAs.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getSendAsList(ReturnFormat format) throws IOException {
        ListSendAsResponse list = sendAs.list(userId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(list);
            case LIBRARY_OBJECT:
                ArrayList<SendAs> sendAsList = new ArrayList<>();
                if (list != null)
                    for (com.google.api.services.gmail.model.SendAs sendAs : list.getSendAs())
                        sendAsList.add(new SendAs(new JSONObject(sendAs)));
                return (T) sendAsList;
            default:
                return (T) list.toString();
        }
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "displayName"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:        the send-as alias to be updated
     * @param displayNameUpdated: a name that appears in the {@code "From:"} header for mail sent using this alias
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSendAsDisplayName(String sendAsEmail, String displayNameUpdated) throws IOException {
        return changeSendAsDisplayName(sendAsEmail, displayNameUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "displayName"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:        the send-as alias to be updated
     * @param displayNameUpdated: a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param format:             return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSendAsDisplayName(String sendAsEmail, String displayNameUpdated,
                                         ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setDisplayName(displayNameUpdated)).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "replyToAddress"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:           the send-as alias to be updated
     * @param replyToAddressUpdated: an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSendAsReplyToAddress(String sendAsEmail, String replyToAddressUpdated) throws IOException {
        return changeSendAsReplyToAddress(sendAsEmail, replyToAddressUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "replyToAddress"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:           the send-as alias to be updated
     * @param replyToAddressUpdated: an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSendAsReplyToAddress(String sendAsEmail, String replyToAddressUpdated,
                                            ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setReplyToAddress(replyToAddressUpdated)).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "signature"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:      the send-as alias to be updated
     * @param signatureUpdated: an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSendAsSignature(String sendAsEmail, String signatureUpdated) throws IOException {
        return changeSendAsSignature(sendAsEmail, signatureUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "signature"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:      the send-as alias to be updated
     * @param signatureUpdated: an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @param format:           return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSendAsSignature(String sendAsEmail, String signatureUpdated, ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSignature(signatureUpdated)).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "default"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:    the send-as alias to be updated
     * @param defaultUpdated: whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                        a new message or sending a vacation auto-reply
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSendAsDefault(String sendAsEmail, boolean defaultUpdated) throws IOException {
        return changeSendAsDefault(sendAsEmail, defaultUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "default"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:    the send-as alias to be updated
     * @param defaultUpdated: whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                        a new message or sending a vacation auto-reply
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSendAsDefault(String sendAsEmail, boolean defaultUpdated, ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setIsDefault(defaultUpdated)).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "default"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param treatAsAliasUpdated: whether Gmail should treat this address as an alias for the user's primary email address
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSendAsTreatAsAlias(String sendAsEmail, boolean treatAsAliasUpdated) throws IOException {
        return changeSendAsTreatAsAlias(sendAsEmail, treatAsAliasUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "default"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param treatAsAliasUpdated: whether Gmail should treat this address as an alias for the user's primary email address
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSendAsTreatAsAlias(String sendAsEmail, boolean treatAsAliasUpdated,
                                          ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setTreatAsAlias(treatAsAliasUpdated)).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail: the send-as alias to be updated
     * @param hostUpdated: the hostname of the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSmtpMsaHost(String sendAsEmail, String hostUpdated) throws IOException {
        return changeSmtpMsaHost(sendAsEmail, hostUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail: the send-as alias to be updated
     * @param hostUpdated: the hostname of the {@code "SMTP"} service
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSmtpMsaHost(String sendAsEmail, String hostUpdated, ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setHost(hostUpdated))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "port"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail: the send-as alias to be updated
     * @param portUpdated: the port of the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSmtpMsaPort(String sendAsEmail, int portUpdated) throws IOException {
        return changeSmtpMsaPort(sendAsEmail, portUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "port"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail: the send-as alias to be updated
     * @param portUpdated: the port of the {@code "SMTP"} service
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSmtpMsaPort(String sendAsEmail, int portUpdated, ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setPort(portUpdated))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "username"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:     the send-as alias to be updated
     * @param usernameUpdated: the username that will be used for authentication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     **/
    public SendAs changeSmtpMsaUsername(String sendAsEmail, String usernameUpdated) throws IOException {
        return changeSmtpMsaUsername(sendAsEmail, usernameUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "username"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:     the send-as alias to be updated
     * @param usernameUpdated: the username that will be used for authentication with the {@code "SMTP"} service
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     **/
    public <T> T changeSmtpMsaUsername(String sendAsEmail, String usernameUpdated, ReturnFormat format) throws IOException {
        currentUsername = usernameUpdated;
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setUsername(usernameUpdated))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "password"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:     the send-as alias to be updated
     * @param passwordUpdated: the password that will be used for authentication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentPassword
     **/
    public SendAs changeSmtpMsaPassword(String sendAsEmail, String passwordUpdated) throws IOException {
        return changeSmtpMsaPassword(sendAsEmail, passwordUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "password"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:     the send-as alias to be updated
     * @param passwordUpdated: the password that will be used for authentication with the {@code "SMTP"} service
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentPassword
     **/
    public <T> T changeSmtpMsaPassword(String sendAsEmail, String passwordUpdated, ReturnFormat format) throws IOException {
        currentPassword = passwordUpdated;
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setPassword(passwordUpdated))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSmtpMsaSecurityMode(String sendAsEmail, SecurityMode securityModeUpdated) throws IOException {
        return changeSmtpMsaSecurityMode(sendAsEmail, securityModeUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSmtpMsaSecurityMode(String sendAsEmail, SecurityMode securityModeUpdated,
                                           ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setSecurityMode(securityModeUpdated.name()))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} and {@code "port"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail: the send-as alias to be updated
     * @param hostUpdated: the hostname of the {@code "SMTP"} service
     * @param portUpdated: the port of the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSmtpMsaDomainDetails(String sendAsEmail, String hostUpdated, int portUpdated) throws IOException {
        return changeSmtpMsaDomainDetails(sendAsEmail, hostUpdated, portUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} and {@code "port"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail: the send-as alias to be updated
     * @param hostUpdated: the hostname of the {@code "SMTP"} service
     * @param portUpdated: the port of the {@code "SMTP"} service
     * @param format:      return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSmtpMsaDomainDetails(String sendAsEmail, String hostUpdated, int portUpdated,
                                            ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setHost(hostUpdated).setPort(portUpdated))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} and {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param hostUpdated:         the hostname of the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSmtpMsaDomainDetails(String sendAsEmail, String hostUpdated,
                                             SecurityMode securityModeUpdated) throws IOException {
        return changeSmtpMsaDomainDetails(sendAsEmail, hostUpdated, securityModeUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} and {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param hostUpdated:         the hostname of the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSmtpMsaDomainDetails(String sendAsEmail, String hostUpdated, SecurityMode securityModeUpdated,
                                            ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                        .setSmtpMsa(new SmtpMsa().setHost(hostUpdated).setSecurityMode(securityModeUpdated.name()))).execute(),
                format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} and {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param portUpdated:         the port of the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSmtpMsaDomainDetails(String sendAsEmail, int portUpdated,
                                             SecurityMode securityModeUpdated) throws IOException {
        return changeSmtpMsaDomainDetails(sendAsEmail, portUpdated, securityModeUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"} and {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param portUpdated:         the port of the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSmtpMsaDomainDetails(String sendAsEmail, int portUpdated, SecurityMode securityModeUpdated,
                                            ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                        .setSmtpMsa(new SmtpMsa().setPort(portUpdated).setSecurityMode(securityModeUpdated.name()))).execute(),
                format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"}, {@code "port"} and {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param hostUpdated:         the hostname of the {@code "SMTP"} service
     * @param portUpdated:         the port of the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public SendAs changeSmtpMsaDomainDetails(String sendAsEmail, String hostUpdated, int portUpdated,
                                             SecurityMode securityModeUpdated) throws IOException {
        return changeSmtpMsaDomainDetails(sendAsEmail, hostUpdated, portUpdated, securityModeUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "host"}, {@code "port"} and {@code "securityMode"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param hostUpdated:         the hostname of the {@code "SMTP"} service
     * @param portUpdated:         the port of the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T changeSmtpMsaDomainDetails(String sendAsEmail, String hostUpdated, int portUpdated,
                                            SecurityMode securityModeUpdated, ReturnFormat format) throws IOException {
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setHost(hostUpdated).setPort(portUpdated)
                        .setSecurityMode(securityModeUpdated.name()))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "username"} and {@code "password"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:     the send-as alias to be updated
     * @param usernameUpdated: the username that will be used for authentication with the {@code "SMTP"} service
     * @param passwordUpdated: the password that will be used for authentication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public SendAs changeSmtpMsaCredentials(String sendAsEmail, String usernameUpdated,
                                           String passwordUpdated) throws IOException {
        return changeSmtpMsaCredentials(sendAsEmail, usernameUpdated, passwordUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "username"} and {@code "password"} of {@link com.tecknobit.googlemanager.gmail.settings.records.SendAs.SmtpMsa} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:     the send-as alias to be updated
     * @param usernameUpdated: the username that will be used for authentication with the {@code "SMTP"} service
     * @param passwordUpdated: the password that will be used for authentication with the {@code "SMTP"} service
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public <T> T changeSmtpMsaCredentials(String sendAsEmail, String usernameUpdated, String passwordUpdated,
                                          ReturnFormat format) throws IOException {
        currentUsername = usernameUpdated;
        currentPassword = passwordUpdated;
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setUsername(usernameUpdated).setPassword(passwordUpdated))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "smtpMsa"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:    the send-as alias to be updated
     * @param smtpMsaUpdated: the smtpMsa updated
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public SendAs changeSmtpMsa(String sendAsEmail, SendAs.SmtpMsa smtpMsaUpdated) throws IOException {
        return changeSmtpMsa(sendAsEmail, smtpMsaUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "smtpMsa"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:    the send-as alias to be updated
     * @param smtpMsaUpdated: the smtpMsa updated
     * @param format:         return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public <T> T changeSmtpMsa(String sendAsEmail, SendAs.SmtpMsa smtpMsaUpdated, ReturnFormat format) throws IOException {
        currentUsername = smtpMsaUpdated.getUsername();
        currentPassword = smtpMsaUpdated.getPassword();
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setHost(smtpMsaUpdated.getHost()).setPort(smtpMsaUpdated.getPort())
                        .setUsername(currentUsername).setPassword(currentPassword)
                        .setSecurityMode(smtpMsaUpdated.getSecurityMode().name()))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "smtpMsa"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param hostUpdated:         the hostname of the {@code "SMTP"} service {@code "Required"}
     * @param portUpdated:         the port of the {@code "SMTP"} service {@code "Required"}
     * @param usernameUpdated:     the username that will be used for authentication with the {@code "SMTP"} service
     * @param passwordUpdated:     the password that will be used for authentication with the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public SendAs changeSmtpMsa(String sendAsEmail, String hostUpdated, int portUpdated, String usernameUpdated,
                                String passwordUpdated, SecurityMode securityModeUpdated) throws IOException {
        return changeSmtpMsa(sendAsEmail, hostUpdated, portUpdated, usernameUpdated, passwordUpdated, securityModeUpdated,
                LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     * This method allows to change only {@code "smtpMsa"} without change all {code "SendAs"} details
     *
     * @param sendAsEmail:         the send-as alias to be updated
     * @param hostUpdated:         the hostname of the {@code "SMTP"} service {@code "Required"}
     * @param portUpdated:         the port of the {@code "SMTP"} service {@code "Required"}
     * @param usernameUpdated:     the username that will be used for authentication with the {@code "SMTP"} service
     * @param passwordUpdated:     the password that will be used for authentication with the {@code "SMTP"} service
     * @param securityModeUpdated: the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public <T> T changeSmtpMsa(String sendAsEmail, String hostUpdated, int portUpdated, String usernameUpdated,
                               String passwordUpdated, SecurityMode securityModeUpdated,
                               ReturnFormat format) throws IOException {
        currentUsername = usernameUpdated;
        currentPassword = passwordUpdated;
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setSmtpMsa(new SmtpMsa().setHost(hostUpdated).setPort(portUpdated).setUsername(usernameUpdated)
                        .setPassword(passwordUpdated).setSecurityMode(securityModeUpdated.name()))).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     *
     * @param sendAsUpdated: the send-as updated
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public SendAs changeSendAs(SendAs sendAsUpdated) throws IOException {
        return changeSendAs(sendAsUpdated, LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     *
     * @param sendAsUpdated: the send-as updated
     * @param format:        return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public <T> T changeSendAs(SendAs sendAsUpdated, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.SendAs gSendAs = new com.google.api.services.gmail.model.SendAs()
                .setDisplayName(sendAsUpdated.getDisplayName())
                .setReplyToAddress(sendAsUpdated.getReplyToAddress())
                .setSignature(sendAsUpdated.getSignature())
                .setIsDefault(sendAsUpdated.isDefault())
                .setTreatAsAlias(sendAsUpdated.isTreatAsAlias());
        SendAs.SmtpMsa smtpMsaUpdated = sendAsUpdated.getSmtpMsa();
        if (smtpMsaUpdated != null) {
            currentUsername = smtpMsaUpdated.getUsername();
            currentPassword = smtpMsaUpdated.getPassword();
            gSendAs.setSmtpMsa(new SmtpMsa().setHost(smtpMsaUpdated.getHost())
                    .setPort(smtpMsaUpdated.getPort())
                    .setUsername(currentUsername)
                    .setPassword(currentPassword)
                    .setSecurityMode(smtpMsaUpdated.getSecurityMode().name()));
        }
        return returnSendAs(sendAs.patch(userId, sendAsUpdated.getSendAsEmail(), gSendAs).execute(), format);
    }

    /**
     * Method to patch the specified send-as alias <br>
     *
     * @param sendAsEmail:           the send-as alias to be updated
     * @param displayNameUpdated:    a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param replyToAddressUpdated: an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param signatureUpdated:      an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @param defaultUpdated:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                               a new message or sending a vacation auto-reply
     * @param treatAsAliasUpdated:   whether Gmail should treat this address as an alias for the user's primary email address
     * @param hostUpdated:           the hostname of the {@code "SMTP"} service {@code "Required"}
     * @param portUpdated:           the port of the {@code "SMTP"} service {@code "Required"}
     * @param usernameUpdated:       the username that will be used for authentication with the {@code "SMTP"} service
     * @param passwordUpdated:       the password that will be used for authentication with the {@code "SMTP"} service
     * @param securityModeUpdated:   the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @return send-as updated as {@link SendAs} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public SendAs changeSendAs(String sendAsEmail, String displayNameUpdated, String replyToAddressUpdated,
                               String signatureUpdated, boolean defaultUpdated, boolean treatAsAliasUpdated,
                               String hostUpdated, int portUpdated, String usernameUpdated, String passwordUpdated,
                               SecurityMode securityModeUpdated) throws IOException {
        return changeSendAs(sendAsEmail, displayNameUpdated, replyToAddressUpdated, signatureUpdated, defaultUpdated,
                treatAsAliasUpdated, hostUpdated, portUpdated, usernameUpdated, passwordUpdated, securityModeUpdated,
                LIBRARY_OBJECT);
    }

    /**
     * Method to patch the specified send-as alias <br>
     *
     * @param sendAsEmail:           the send-as alias to be updated
     * @param displayNameUpdated:    a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param replyToAddressUpdated: an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param signatureUpdated:      an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @param defaultUpdated:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                               a new message or sending a vacation auto-reply
     * @param treatAsAliasUpdated:   whether Gmail should treat this address as an alias for the user's primary email address
     * @param hostUpdated:           the hostname of the {@code "SMTP"} service {@code "Required"}
     * @param portUpdated:           the port of the {@code "SMTP"} service {@code "Required"}
     * @param usernameUpdated:       the username that will be used for authentication with the {@code "SMTP"} service
     * @param passwordUpdated:       the password that will be used for authentication with the {@code "SMTP"} service
     * @param securityModeUpdated:   the protocol that will be used to secure communication with the {@code "SMTP"} service
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return send-as updated as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/patch">
     * users.settings.sendAs.patch</a>
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/update">
     * update method</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @see #currentUsername
     * @see #currentPassword
     **/
    public <T> T changeSendAs(String sendAsEmail, String displayNameUpdated, String replyToAddressUpdated,
                              String signatureUpdated, boolean defaultUpdated, boolean treatAsAliasUpdated,
                              String hostUpdated, int portUpdated, String usernameUpdated, String passwordUpdated,
                              SecurityMode securityModeUpdated, ReturnFormat format) throws IOException {
        currentUsername = usernameUpdated;
        currentPassword = passwordUpdated;
        return returnSendAs(sendAs.patch(userId, sendAsEmail, new com.google.api.services.gmail.model.SendAs()
                .setDisplayName(displayNameUpdated)
                .setReplyToAddress(replyToAddressUpdated)
                .setSignature(signatureUpdated)
                .setIsDefault(defaultUpdated)
                .setTreatAsAlias(treatAsAliasUpdated)
                .setSmtpMsa(new SmtpMsa().setHost(hostUpdated)
                        .setPort(portUpdated)
                        .setUsername(usernameUpdated)
                        .setPassword(passwordUpdated)
                        .setSecurityMode(securityModeUpdated.name()))).execute(), format);
    }

    /**
     * Method to create a send-as object
     *
     * @param sendAs: send-as obtained from Google's response
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return send-as as {@code "format"} defines
     **/
    private <T> T returnSendAs(com.google.api.services.gmail.model.SendAs sendAs, ReturnFormat format) {
        SmtpMsa smtpMsa = sendAs.getSmtpMsa();
        if (smtpMsa != null)
            sendAs.setSmtpMsa(smtpMsa.setUsername(currentUsername).setPassword(currentPassword));
        switch (format) {
            case JSON:
                return (T) new JSONObject(sendAs);
            case LIBRARY_OBJECT:
                return (T) new SendAs(new JSONObject(sendAs));
            default:
                return (T) sendAs.toString();
        }
    }

    /**
     * Method to send a verification email to the specified send-as alias address. The verification status must be {@code "pending"}.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param sendAsToVerify: send-as to verify
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/verify">
     * users.settings.sendAs.verify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean verifySendAs(SendAs sendAsToVerify) {
        return verifySendAs(sendAsToVerify.getSendAsEmail());
    }

    /**
     * Method to send a verification email to the specified send-as alias address. The verification status must be {@code "pending"}.
     * This method is only available to service account clients that have been delegated domain-wide authority
     *
     * @param sendAsEmailToVerify: the send-as alias to be verified
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs/verify">
     * users.settings.sendAs.verify</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean verifySendAs(String sendAsEmailToVerify) {
        try {
            sendAs.verify(userId, sendAsEmailToVerify).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
