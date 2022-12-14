package com.tecknobit.googlemanager.gmail.users.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

import java.math.BigInteger;

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
     * {@code historyId} is the {@code "ID"} of the mailbox's current history record
     **/
    private final BigInteger historyId;

    /**
     * {@code expiration} when {@code "Gmail"} will stop sending notifications for mailbox updates (epoch millis). Call watch again before this time to renew the watch
     **/
    private final long expiration;

    /**
     * Constructor to init a {@link PushNotificationWatch}
     *
     * @param historyId:  the {@code "ID"} of the mailbox's current history record
     * @param expiration: when {@code "Gmail"} will stop sending notifications for mailbox updates (epoch millis). Call watch again before this time to renew the watch
     **/
    public PushNotificationWatch(BigInteger historyId, long expiration) {
        this.historyId = historyId;
        this.expiration = expiration;
    }

    /**
     * Constructor to init a {@link PushNotificationWatch}
     *
     * @param jPushNotification: {@code "push notification watch"} details as {@link JSONObject}
     **/
    public PushNotificationWatch(JSONObject jPushNotification) {
        JsonHelper hPushNotification = new JsonHelper(jPushNotification);
        historyId = hPushNotification.getBigInteger("historyId", BigInteger.valueOf(0));
        expiration = hPushNotification.getLong("expiration", 0);
    }

    /**
     * Method to get {@link #historyId} instance <br>
     * Any params required
     *
     * @return {@link #historyId} instance as {@link String}
     **/
    public BigInteger getHistoryId() {
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
        return new JSONObject(this).toString();
    }

}
