package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

/**
 * The {@code VacationSettings} class is useful to format a Gmail's vacation settings
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/VacationSettings">Vacation settings</a>
 **/
public class VacationSettings {

    /**
     * {@code enableAutoReply} flag that controls whether Gmail automatically replies to messages
     **/
    private boolean enableAutoReply;

    /**
     * {@code responseSubject} optional text to prepend to the subject line in vacation responses.
     * In order to enable auto-replies, either the response subject or the response body must be nonempty
     **/
    private String responseSubject;

    /**
     * {@code responseBodyPlainText} response body in plain text format. If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"}
     * are specified, {@code "responseBodyHtml"}  will be used
     **/
    private String responseBodyPlainText;

    /**
     * {@code responseBodyHtml} response body in {@code "HTML"} format. {@code "Gmail"} will sanitize the {@code "HTML"} before storing it.
     * If both {@code "responseBodyPlainText"} and {@code "responseBodyHtml"} are specified, {@code "responseBodyHtml"}
     * will be used
     **/
    private String responseBodyHtml;

    /**
     * {@code restrictToContacts} flag that determines whether responses are sent to recipients who are not in the user's list of contacts
     **/
    private boolean restrictToContacts;

    /**
     * {@code restrictToDomain} flag that determines whether responses are sent to recipients who are outside
     * the user's domain. This feature is only available for G Suite users
     **/
    private boolean restrictToDomain;

    /**
     * {@code startTime} an optional start time for sending auto-replies (epoch ms). When this is specified,
     * {@code "Gmail"} will automatically reply only to messages that it receives after the start time.
     * If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     **/
    private long startTime;

    /**
     * {@code endTime} an optional end time for sending auto-replies (epoch ms). When this is specified,
     * {@code "Gmail"} will automatically reply only to messages that it receives before the end time.
     * If both {@code "startTime"} and {@code "endTime"} are specified, {@code "startTime"} must precede {@code "endTime"}
     **/
    private long endTime;

    /**
     * Constructor to init a {@link VacationSettings}
     *
     * @param enableAutoReply:       flag that controls whether Gmail automatically replies to messages
     * @param responseSubject:       optional text to prepend to the subject line in vacation responses
     * @param responseBodyPlainText: response body in plain text format
     * @param responseBodyHtml:      response body in {@code "HTML"} format
     * @param restrictToContacts:    flag that determines whether responses are sent to recipients who are not in the user's list of contacts
     * @param restrictToDomain:      flag that determines whether responses are sent to recipients who are outside
     *                               the user's domain. This feature is only available for G Suite users
     * @param startTime:             an optional start time for sending auto-replies (epoch ms)
     * @param endTime:               an optional end time for sending auto-replies (epoch ms)
     **/
    public VacationSettings(boolean enableAutoReply, String responseSubject, String responseBodyPlainText,
                            String responseBodyHtml, boolean restrictToContacts, boolean restrictToDomain,
                            long startTime, long endTime) {
        this.enableAutoReply = enableAutoReply;
        this.responseSubject = responseSubject;
        this.responseBodyPlainText = responseBodyPlainText;
        this.responseBodyHtml = responseBodyHtml;
        this.restrictToContacts = restrictToContacts;
        this.restrictToDomain = restrictToDomain;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor to init a {@link VacationSettings}
     *
     * @param jVacationSettings: {@code "vacation settings"} details as {@link JSONObject}
     **/
    public VacationSettings(JSONObject jVacationSettings) {
        JsonHelper hVacationSettings = new JsonHelper(jVacationSettings);
        enableAutoReply = hVacationSettings.getBoolean("enableAutoReply");
        responseSubject = hVacationSettings.getString("responseSubject");
        responseBodyPlainText = hVacationSettings.getString("responseBodyPlainText");
        responseBodyHtml = hVacationSettings.getString("responseBodyHtml");
        restrictToContacts = hVacationSettings.getBoolean("restrictToContacts");
        restrictToDomain = hVacationSettings.getBoolean("restrictToDomain");
        startTime = hVacationSettings.getLong("startTime", 0);
        endTime = hVacationSettings.getLong("endTime", 0);
    }

    /**
     * Method to get {@link #enableAutoReply} instance <br>
     * Any params required
     *
     * @return {@link #enableAutoReply} instance as boolean
     **/
    public boolean isEnableAutoReply() {
        return enableAutoReply;
    }

    /**
     * Method to set {@link #enableAutoReply} instance on {@code "true"}<br>
     * Any params required
     **/
    public void enableAutoReply() {
        enableAutoReply = true;
    }

    /**
     * Method to set {@link #enableAutoReply} instance on {@code "false"}<br>
     * Any params required
     **/
    public void disableAutoReply() {
        enableAutoReply = false;
    }

    /**
     * Method to get {@link #responseSubject} instance <br>
     * Any params required
     *
     * @return {@link #responseSubject} instance as {@link String}
     **/
    public String getResponseSubject() {
        return responseSubject;
    }

    /**
     * Method to set {@link #responseSubject} instance
     *
     * @param responseSubject: optional text to prepend to the subject line in vacation responses
     **/
    public void setResponseSubject(String responseSubject) {
        this.responseSubject = responseSubject;
    }

    /**
     * Method to get {@link #responseBodyPlainText} instance <br>
     * Any params required
     *
     * @return {@link #responseBodyPlainText} instance as {@link String}
     **/
    public String getResponseBodyPlainText() {
        return responseBodyPlainText;
    }

    /**
     * Method to set {@link #responseBodyPlainText} instance
     *
     * @param responseBodyPlainText: response body in plain text format
     **/
    public void setResponseBodyPlainText(String responseBodyPlainText) {
        this.responseBodyPlainText = responseBodyPlainText;
    }

    /**
     * Method to get {@link #responseBodyHtml} instance <br>
     * Any params required
     *
     * @return {@link #responseBodyHtml} instance as {@link String}
     **/
    public String getResponseBodyHtml() {
        return responseBodyHtml;
    }

    /**
     * Method to set {@link #responseBodyHtml} instance
     *
     * @param responseBodyHtml: response body in {@code "HTML"} format
     **/
    public void setResponseBodyHtml(String responseBodyHtml) {
        this.responseBodyHtml = responseBodyHtml;
    }

    /**
     * Method to get {@link #restrictToContacts} instance <br>
     * Any params required
     *
     * @return {@link #restrictToContacts} instance as boolean
     **/
    public boolean isRestrictToContacts() {
        return restrictToContacts;
    }

    /**
     * Method to set {@link #restrictToContacts} instance on {@code "true"}<br>
     * Any params required
     **/
    public void enableRestrictToContacts() {
        restrictToContacts = true;
    }

    /**
     * Method to set {@link #restrictToContacts} instance on {@code "false"}<br>
     * Any params required
     **/
    public void disableRestrictToContacts() {
        restrictToContacts = false;
    }

    /**
     * Method to get {@link #restrictToDomain} instance <br>
     * Any params required
     *
     * @return {@link #restrictToDomain} instance as boolean
     **/
    public boolean isRestrictToDomain() {
        return restrictToDomain;
    }

    /**
     * Method to set {@link #restrictToDomain} instance on {@code "true"}<br>
     * Any params required
     **/
    public void enableRestrictToDomain() {
        restrictToDomain = true;
    }

    /**
     * Method to set {@link #restrictToDomain} instance on {@code "false"}<br>
     * Any params required
     **/
    public void disableRestrictToDomain() {
        restrictToDomain = false;
    }

    /**
     * Method to get {@link #startTime} instance <br>
     * Any params required
     *
     * @return {@link #startTime} instance as long
     **/
    public long getStartTime() {
        return startTime;
    }

    /**
     * Method to set {@link #startTime} instance
     *
     * @param startTime: optional start time for sending auto-replies (epoch ms)
     **/
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Method to get {@link #endTime} instance <br>
     * Any params required
     *
     * @return {@link #endTime} instance as long
     **/
    public long getEndTime() {
        return endTime;
    }

    /**
     * Method to set {@link #endTime} instance
     *
     * @param endTime: optional end time for sending auto-replies (epoch ms)
     **/
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
