package com.tecknobit.googlemanager.gmail.users.records;

import org.json.JSONObject;

public class PushNotificationWatch {

    public static String INCLUDE_LABEL_FILTER_ACTION = "include";
    public static String EXCLUDE_LABEL_FILTER_ACTION = "exclude";
    private final String historyId;
    private final String expiration;

    public PushNotificationWatch(String historyId, String expiration) {
        this.historyId = historyId;
        this.expiration = expiration;
    }

    public PushNotificationWatch(JSONObject jPushNotification) {
        historyId = jPushNotification.getString("historyId");
        expiration = jPushNotification.getString("expiration");
    }

    public String getHistoryId() {
        return historyId;
    }

    public String getExpiration() {
        return expiration;
    }

    @Override
    public String toString() {
        return "PushNotificationWatch{" +
                "historyId='" + historyId + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }

}
