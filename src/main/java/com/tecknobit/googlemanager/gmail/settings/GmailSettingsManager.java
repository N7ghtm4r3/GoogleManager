package com.tecknobit.googlemanager.gmail.settings;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.LanguageSettings;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding;
import com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition;
import com.tecknobit.googlemanager.gmail.settings.records.ImapSettings;
import com.tecknobit.googlemanager.gmail.settings.records.PopSettings;
import com.tecknobit.googlemanager.gmail.settings.records.PopSettings.AccessWindow;
import com.tecknobit.googlemanager.gmail.settings.records.VacationSettings;
import org.json.JSONObject;

import java.io.IOException;

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
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        if (enabled)
            actualAutoForwarding.enable();
        else
            actualAutoForwarding.disable();
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    public AutoForwarding updateAutoForwardingEmailAddress(String emailAddress) throws IOException {
        return updateAutoForwardingEmailAddress(emailAddress, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwardingEmailAddress(String emailAddress, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        actualAutoForwarding.setEmailAddress(emailAddress);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    public AutoForwarding updateAutoForwardingDisposition(Disposition disposition) throws IOException {
        return updateAutoForwardingDisposition(disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwardingDisposition(Disposition disposition, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        actualAutoForwarding.setDisposition(disposition);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    public AutoForwarding updateAutoForwarding(boolean enabled, String emailAddress) throws IOException {
        return updateAutoForwarding(enabled, emailAddress, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(boolean enabled, String emailAddress, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        if (enabled)
            actualAutoForwarding.enable();
        else
            actualAutoForwarding.disable();
        actualAutoForwarding.setEmailAddress(emailAddress);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    public AutoForwarding updateAutoForwarding(boolean enabled, Disposition disposition) throws IOException {
        return updateAutoForwarding(enabled, disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(boolean enabled, Disposition disposition, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        if (enabled)
            actualAutoForwarding.enable();
        else
            actualAutoForwarding.disable();
        actualAutoForwarding.setDisposition(disposition);
        return updateAutoForwarding(actualAutoForwarding, format);
    }

    public AutoForwarding updateAutoForwarding(String emailAddress, Disposition disposition) throws IOException {
        return updateAutoForwarding(emailAddress, disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(String emailAddress, Disposition disposition, ReturnFormat format) throws IOException {
        AutoForwarding actualAutoForwarding = getAutoForwarding();
        actualAutoForwarding.setEmailAddress(emailAddress);
        actualAutoForwarding.setDisposition(disposition);
        return updateAutoForwarding(actualAutoForwarding, format);
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

    public AutoForwarding updateAutoForwarding(boolean enabled, String emailAddress,
                                               Disposition disposition) throws IOException {
        return updateAutoForwarding(enabled, emailAddress, disposition, LIBRARY_OBJECT);
    }

    public <T> T updateAutoForwarding(boolean enabled, String emailAddress, Disposition disposition,
                                      ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.AutoForwarding autoForwarding = new com.google.api.services.gmail.model.AutoForwarding();
        autoForwarding.setEnabled(enabled);
        autoForwarding.setEmailAddress(emailAddress);
        autoForwarding.setDisposition(disposition.name());
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

    public ImapSettings enableImap() throws IOException {
        return enableImap(LIBRARY_OBJECT);
    }

    public <T> T enableImap(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.enable();
        return updateImap(actualImap, format);
    }

    public ImapSettings disableImap() throws IOException {
        return disableImap(LIBRARY_OBJECT);
    }

    public <T> T disableImap(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.disable();
        return updateImap(actualImap, format);
    }

    public ImapSettings enableImapAutoExpunge() throws IOException {
        return enableImapAutoExpunge(LIBRARY_OBJECT);
    }

    public <T> T enableImapAutoExpunge(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.enableAutoExpunge();
        return updateImap(actualImap, format);
    }

    public ImapSettings disableImapAutoExpunge() throws IOException {
        return disableImapAutoExpunge(LIBRARY_OBJECT);
    }

    public <T> T disableImapAutoExpunge(ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.disableAutoExpunge();
        return updateImap(actualImap, format);
    }

    public ImapSettings updateImapExpungeBehavior(ExpungeBehavior expungeBehavior) throws IOException {
        return updateImapExpungeBehavior(expungeBehavior, LIBRARY_OBJECT);
    }

    public <T> T updateImapExpungeBehavior(ExpungeBehavior expungeBehavior, ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.setExpungeBehavior(expungeBehavior);
        return updateImap(actualImap, format);
    }

    public ImapSettings updateImapMaxFolderSize(int maxFolderSize) throws IOException {
        return updateImapMaxFolderSize(maxFolderSize, LIBRARY_OBJECT);
    }

    public <T> T updateImapMaxFolderSize(int maxFolderSize, ReturnFormat format) throws IOException {
        ImapSettings actualImap = getImap();
        actualImap.setMaxFolderSize(maxFolderSize);
        return updateImap(actualImap, format);
    }

    @SafeVarargs
    public final <T> ImapSettings updateImap(T... imapSettingsUpdated) throws IOException {
        return (ImapSettings) updateImap(LIBRARY_OBJECT, imapSettingsUpdated);
    }

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

    public ImapSettings updateImap(ImapSettings imapSettingsUpdated) throws IOException {
        return updateImap(imapSettingsUpdated, LIBRARY_OBJECT);
    }

    public <T> T updateImap(ImapSettings imapSettingsUpdated, ReturnFormat format) throws IOException {
        return returnImap(settings.updateImap(userId, new com.google.api.services.gmail.model.ImapSettings()
                .setEnabled(imapSettingsUpdated.isEnabled())
                .setAutoExpunge(imapSettingsUpdated.isAutoExpungeEnabled())
                .setExpungeBehavior(imapSettingsUpdated.getExpungeBehavior().toString())
                .setMaxFolderSize(imapSettingsUpdated.getMaxFolderSize())).execute(), format);
    }

    public ImapSettings updateImap(boolean enable, boolean autoExpunge, ExpungeBehavior expungeBehavior,
                                   int maxFolderSize) throws IOException {
        return updateImap(enable, autoExpunge, expungeBehavior, maxFolderSize, LIBRARY_OBJECT);
    }

    public <T> T updateImap(boolean enable, boolean autoExpunge, ExpungeBehavior expungeBehavior, int maxFolderSize,
                            ReturnFormat format) throws IOException {
        return returnImap(settings.updateImap(userId, new com.google.api.services.gmail.model.ImapSettings()
                .setEnabled(enable)
                .setAutoExpunge(autoExpunge)
                .setExpungeBehavior(expungeBehavior.name())
                .setMaxFolderSize(maxFolderSize)).execute(), format);
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

    public <T> T updateLanguage(String languageUpdated, ReturnFormat format) throws IOException {
        return returnLanguage(settings.updateLanguage(userId, new LanguageSettings()
                .setDisplayLanguage(languageUpdated)).execute(), format);
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

    public PopSettings updatePopAccessWindow(AccessWindow accessWindow) throws IOException {
        return updatePopAccessWindow(accessWindow, LIBRARY_OBJECT);
    }

    public <T> T updatePopAccessWindow(AccessWindow accessWindow, ReturnFormat format) throws IOException {
        PopSettings actualPopSettings = getPopSettings();
        actualPopSettings.setAccessWindow(accessWindow);
        return updatePopSettings(actualPopSettings, format);
    }

    public PopSettings updatePopDisposition(Disposition disposition) throws IOException {
        return updatePopDisposition(disposition, LIBRARY_OBJECT);
    }

    public <T> T updatePopDisposition(Disposition disposition, ReturnFormat format) throws IOException {
        PopSettings actualPopSettings = getPopSettings();
        actualPopSettings.setDisposition(disposition);
        return updatePopSettings(actualPopSettings, format);
    }

    public PopSettings updatePopSettings(PopSettings popSettingsUpdated) throws IOException {
        return updatePopSettings(popSettingsUpdated, LIBRARY_OBJECT);
    }

    public <T> T updatePopSettings(PopSettings popSettingsUpdated, ReturnFormat format) throws IOException {
        return returnPopSettings(settings.updatePop(userId, new com.google.api.services.gmail.model.PopSettings()
                .setAccessWindow(popSettingsUpdated.getAccessWindow().toString())
                .setDisposition(popSettingsUpdated.getDisposition().toString())).execute(), format);
    }

    public PopSettings updatePopSettings(AccessWindow accessWindow, Disposition disposition) throws IOException {
        return updatePopSettings(accessWindow, disposition, LIBRARY_OBJECT);
    }

    public <T> T updatePopSettings(AccessWindow accessWindow, Disposition disposition, ReturnFormat format) throws IOException {
        return returnPopSettings(settings.updatePop(userId, new com.google.api.services.gmail.model.PopSettings()
                .setAccessWindow(accessWindow.name()).setDisposition(disposition.name())).execute(), format);
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

    public VacationSettings enableVacationPlainTextAutoReply(String responseSubject, String responsePlainText) throws IOException {
        return enableVacationPlainTextAutoReply(responseSubject, responsePlainText, LIBRARY_OBJECT);
    }

    public <T> T enableVacationPlainTextAutoReply(String responseSubject, String responsePlainText,
                                                  ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableAutoReply();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings enableVacationHtmlAutoReply(String responseSubject, String responseHtml) throws IOException {
        return enableVacationHtmlAutoReply(responseSubject, responseHtml, LIBRARY_OBJECT);
    }

    public <T> T enableVacationHtmlAutoReply(String responseSubject, String responseHtml, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableAutoReply();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings enableVacationAutoReply(String responseSubject, String responsePlainText,
                                                    String responseHtml) throws IOException {
        return enableVacationAutoReply(responseSubject, responsePlainText, responseHtml, LIBRARY_OBJECT);
    }

    public <T> T enableVacationAutoReply(String responseSubject, String responsePlainText, String responseHtml,
                                         ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableAutoReply();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationResponseSubject(String responseSubject) throws IOException {
        return updateVacationResponseSubject(responseSubject, LIBRARY_OBJECT);
    }

    public <T> T updateVacationResponseSubject(String responseSubject, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseSubject(responseSubject);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationResponsePlainText(String responsePlainText) throws IOException {
        return updateVacationResponsePlainText(responsePlainText, LIBRARY_OBJECT);
    }

    public <T> T updateVacationResponsePlainText(String responsePlainText, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationResponsePlainText(String responseSubject, String responsePlainText) throws IOException {
        return updateVacationResponsePlainText(responseSubject, responsePlainText, LIBRARY_OBJECT);
    }

    public <T> T updateVacationResponsePlainText(String responseSubject, String responsePlainText,
                                                 ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyPlainText(responsePlainText);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationResponseHtml(String responseHtml) throws IOException {
        return updateVacationResponseHtml(responseHtml, LIBRARY_OBJECT);
    }

    public <T> T updateVacationResponseHtml(String responseHtml, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationResponseHtml(String responseSubject, String responseHtml) throws IOException {
        return updateVacationResponseHtml(responseSubject, responseHtml, LIBRARY_OBJECT);
    }

    public <T> T updateVacationResponseHtml(String responseSubject, String responseHtml, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setResponseSubject(responseSubject);
        actualVacationSettings.setResponseBodyHtml(responseHtml);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationResponseDetails(String responseSubject, String responsePlainText,
                                                          String responseHtml) throws IOException {
        return updateVacationResponseDetails(responseSubject, responsePlainText, responseHtml, LIBRARY_OBJECT);
    }

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

    public VacationSettings disableVacationAutoReply() throws IOException {
        return disableVacationAutoReply(LIBRARY_OBJECT);
    }

    public <T> T disableVacationAutoReply(ReturnFormat format) throws IOException {
        return returnVacationSettings(settings.updateVacation(userId, new com.google.api.services.gmail.model.VacationSettings()
                .setEnableAutoReply(false)).execute(), format);
    }

    public VacationSettings enableRestrictToContacts() throws IOException {
        return enableRestrictToContacts(LIBRARY_OBJECT);
    }

    public <T> T enableRestrictToContacts(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableRestrictToContacts();
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings disableRestrictToContacts() throws IOException {
        return disableRestrictToContacts(LIBRARY_OBJECT);
    }

    public <T> T disableRestrictToContacts(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.disableRestrictToContacts();
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings enableRestrictToDomain() throws IOException {
        return enableRestrictToDomain(LIBRARY_OBJECT);
    }

    public <T> T enableRestrictToDomain(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.enableRestrictToDomain();
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings disableRestrictToDomain() throws IOException {
        return disableRestrictToDomain(LIBRARY_OBJECT);
    }

    public <T> T disableRestrictToDomain(ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.disableRestrictToDomain();
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationStartTime(long startTime) throws IOException {
        return updateVacationStartTime(startTime, LIBRARY_OBJECT);
    }

    public <T> T updateVacationStartTime(long startTime, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setStartTime(startTime);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationEndTime(long endTime) throws IOException {
        return updateVacationEndTime(endTime, LIBRARY_OBJECT);
    }

    public <T> T updateVacationEndTime(long endTime, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setEndTime(endTime);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationTime(long startTime, long endTime) throws IOException {
        return updateVacationTime(startTime, endTime, LIBRARY_OBJECT);
    }

    public <T> T updateVacationTime(long startTime, long endTime, ReturnFormat format) throws IOException {
        VacationSettings actualVacationSettings = getVacationSettings();
        actualVacationSettings.setStartTime(startTime);
        actualVacationSettings.setEndTime(endTime);
        return updateVacationSettings(actualVacationSettings, format);
    }

    public VacationSettings updateVacationSettings(VacationSettings vacationSettings) throws IOException {
        return updateVacationSettings(vacationSettings, LIBRARY_OBJECT);
    }

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

    public VacationSettings updateVacationSettings(boolean enableAutoReply, String responseSubject, String responsePlainText,
                                                   String responseHtml, boolean restrictToContacts, boolean restrictToDomain,
                                                   long startTime, long endTime) throws IOException {
        return updateVacationSettings(enableAutoReply, responseSubject, responsePlainText, responseHtml, restrictToContacts,
                restrictToDomain, startTime, endTime, LIBRARY_OBJECT);
    }

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
