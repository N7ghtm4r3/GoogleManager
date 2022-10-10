package com.tecknobit.googlemanager.gmail.users.records;

import org.json.JSONObject;

/**
 * The {@code PushNotificationWatch} class is useful to format a Gmail's push notification watch
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/watch">
 * users.watch</a>
 **/
public class PushNotificationWatch {

    /**
     * {@code INCLUDE_LABEL_FILTER_ACTION} is a constant to only get push notifications for message changes relating to labelIds specified
     **/
    public static String INCLUDE_LABEL_FILTER_ACTION = "include";

    /**
     * {@code EXCLUDE_LABEL_FILTER_ACTION} is a constant to get push notifications for all message changes except those relating to labelIds specified
     **/
    public static String EXCLUDE_LABEL_FILTER_ACTION = "exclude";

    /**
     * {@code historyId} is the ID of the mailbox's current history record
     **/
    private final String historyId;

    /**
     * {@code expiration} when Gmail will stop sending notifications for mailbox updates (epoch millis). Call watch again before this time to renew the watch
     **/
    private final long expiration;

    /**
     * Constructor to init a {@link PushNotificationWatch}
     *
     * @param historyId:  the ID of the mailbox's current history record
     * @param expiration: when Gmail will stop sending notifications for mailbox updates (epoch millis). Call watch again before this time to renew the watch
     **/
    public PushNotificationWatch(String historyId, long expiration) {
        this.historyId = historyId;
        this.expiration = expiration;
    }

    /**
     * Constructor to init a {@link PushNotificationWatch}
     *
     * @param jPushNotification: {@code "push notification watch"} details as {@link JSONObject}
     **/
    public PushNotificationWatch(JSONObject jPushNotification) {
        historyId = jPushNotification.getString("historyId");
        expiration = jPushNotification.getLong("expiration");
    }

    /**
     * Method to get {@link #historyId} instance <br>
     * Any params required
     *
     * @return {@link #historyId} instance as {@link String}
     **/
    public String getHistoryId() {
        return historyId;
    }

    /**
     * Method to get {@link #expiration} instance <br>
     * Any params required
     *
     * @return {@link #expiration} instance as {@link String}
     **/
    public long getExpiration() {
        return expiration;
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return "PushNotificationWatch{" +
                "historyId='" + historyId + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }

}
