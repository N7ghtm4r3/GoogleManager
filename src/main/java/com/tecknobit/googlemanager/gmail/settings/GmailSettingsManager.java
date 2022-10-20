package com.tecknobit.googlemanager.gmail.settings;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.LanguageSettings;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding;
import com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition;
import com.tecknobit.googlemanager.gmail.settings.records.ImapSettings;
import com.tecknobit.googlemanager.gmail.settings.records.PopSettings;
import com.tecknobit.googlemanager.gmail.settings.records.VacationSettings;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GmailSettingsManager} class is useful to manage all Gmail's settings endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings">Gmail settings</a>
 **/
public class GmailSettingsManager extends GmailManager {

    /**
     * {@code settings} is the instance for {@link Gmail.Users.Settings}'s service
     **/
    protected final Gmail.Users.Settings settings = gmail.settings();

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

    public AutoForwarding getAutoForwarding() throws IOException {
        return getAutoForwarding(LIBRARY_OBJECT);
    }

    public <T> T getAutoForwarding(ReturnFormat format) throws IOException {
        return returnAutoForwarding(settings.getAutoForwarding(userId).execute(), format);
    }

    public ImapSettings getImap() throws IOException {
        return getImap(LIBRARY_OBJECT);
    }

    public <T> T getImap(ReturnFormat format) throws IOException {
        return returnImap(settings.getImap(userId).execute(), format);
    }

    public <T> T getLanguage(ReturnFormat format) throws IOException {
        return returnLanguage(settings.getLanguage(userId).execute(), format);
    }

    public PopSettings getPopSettings() throws IOException {
        return getPopSettings(LIBRARY_OBJECT);
    }

    public <T> T getPopSettings(ReturnFormat format) throws IOException {
        return returnPopSettings(settings.getPop(userId).execute(), format);
    }

    public VacationSettings getVacationSettings() throws IOException {
        return getVacationSettings(LIBRARY_OBJECT);
    }

    public <T> T getVacationSettings(ReturnFormat format) throws IOException {
        return returnVacationSettings(settings.getVacation(userId).execute(), format);
    }

    public AutoForwarding updateAutoForwardingEnabled(boolean enabled) throws IOException {
        return updateAutoForwardingEnabled(enabled, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwardingEnabled(boolean enabled, ReturnFormat format) throws IOException {
        return returnAutoForwarding(settings.updateAutoForwarding(userId, new com.google.api.services.gmail.model.AutoForwarding()
                .setEnabled(enabled)).execute(), format);
    }

    public AutoForwarding updateAutoForwardingEmailAddress(String emailAddress) throws IOException {
        return updateAutoForwardingEmailAddress(emailAddress, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwardingEmailAddress(String emailAddress, ReturnFormat format) throws IOException {
        return returnAutoForwarding(settings.updateAutoForwarding(userId, new com.google.api.services.gmail.model.AutoForwarding()
                .setEmailAddress(emailAddress)).execute(), format);
    }

    public AutoForwarding updateAutoForwardingDisposition(Disposition disposition) throws IOException {
        return updateAutoForwardingDisposition(disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwardingDisposition(Disposition disposition, ReturnFormat format) throws IOException {
        return returnAutoForwarding(settings.updateAutoForwarding(userId, new com.google.api.services.gmail.model.AutoForwarding()
                .setEmailAddress(disposition.toString())).execute(), format);
    }

    public AutoForwarding updateAutoForwarding(boolean enabled, String emailAddress) throws IOException {
        return updateAutoForwarding(enabled, emailAddress, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(boolean enabled, String emailAddress, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEnabled(enabled);
        autoForwarding.setEmailAddress(emailAddress);
        return returnAutoForwarding(settings.updateAutoForwarding(userId, autoForwarding).execute(), format);
    }

    public AutoForwarding updateAutoForwarding(boolean enabled, Disposition disposition) throws IOException {
        return updateAutoForwarding(enabled, disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(boolean enabled, Disposition disposition, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEnabled(enabled);
        autoForwarding.setDisposition(disposition.toString());
        return returnAutoForwarding(settings.updateAutoForwarding(userId, autoForwarding).execute(), format);
    }

    public AutoForwarding updateAutoForwarding(String emailAddress, Disposition disposition) throws IOException {
        return updateAutoForwarding(emailAddress, disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(String emailAddress, Disposition disposition, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEmailAddress(emailAddress);
        autoForwarding.setDisposition(disposition.toString());
        return returnAutoForwarding(settings.updateAutoForwarding(userId, autoForwarding).execute(), format);
    }

    public AutoForwarding updateAutoForwarding(boolean enabled, String emailAddress,
                                               Disposition disposition) throws IOException {
        return updateAutoForwarding(enabled, emailAddress, disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(boolean enabled, String emailAddress, Disposition disposition,
                                      ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEnabled(enabled);
        autoForwarding.setEmailAddress(emailAddress);
        autoForwarding.setDisposition(disposition.toString());
        return returnAutoForwarding(settings.updateAutoForwarding(userId, autoForwarding).execute(), format);
    }

    public AutoForwarding updateAutoForwarding(AutoForwarding autoForwardingUpdated) throws IOException {
        return updateAutoForwarding(autoForwardingUpdated, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(AutoForwarding autoForwardingUpdated, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEnabled(autoForwardingUpdated.isEnabled());
        autoForwarding.setEmailAddress(autoForwardingUpdated.getEmailAddress());
        autoForwarding.setDisposition(autoForwardingUpdated.getDisposition().toString());
        return returnAutoForwarding(settings.updateAutoForwarding(userId, autoForwarding).execute(), format);
    }

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

}
