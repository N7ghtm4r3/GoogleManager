package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

public class VacationSettings {

    private boolean enableAutoReply;
    private String responseSubject;
    private String responseBodyPlainText;
    private String responseBodyHtml;
    private boolean restrictToContacts;
    private boolean restrictToDomain;
    private long startTime;
    private long endTime;

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

    public boolean isEnableAutoReply() {
        return enableAutoReply;
    }

    public void enableAutoReply() {
        enableAutoReply = true;
    }

    public void disableAutoReply() {
        enableAutoReply = false;
    }

    public String getResponseSubject() {
        return responseSubject;
    }

    public void setResponseSubject(String responseSubject) {
        this.responseSubject = responseSubject;
    }

    public String getResponseBodyPlainText() {
        return responseBodyPlainText;
    }

    public void setResponseBodyPlainText(String responseBodyPlainText) {
        this.responseBodyPlainText = responseBodyPlainText;
    }

    public String getResponseBodyHtml() {
        return responseBodyHtml;
    }

    public void setResponseBodyHtml(String responseBodyHtml) {
        this.responseBodyHtml = responseBodyHtml;
    }

    public boolean isRestrictToContacts() {
        return restrictToContacts;
    }

    public void setRestrictToContacts(boolean restrictToContacts) {
        this.restrictToContacts = restrictToContacts;
    }

    public boolean isRestrictToDomain() {
        return restrictToDomain;
    }

    public void setRestrictToDomain(boolean restrictToDomain) {
        this.restrictToDomain = restrictToDomain;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
