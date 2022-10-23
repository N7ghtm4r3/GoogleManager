package com.tecknobit.googlemanager.gmail.users.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import java.math.BigInteger;

/**
 * The {@code Profile} class is useful to format a Gmail's profile
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users/getProfile">
 * users.getProfile</a>
 **/
public class Profile {

    /**
     * {@code emailAddress} is the user's email address
     * **/
    private final String emailAddress;

    /**
     * {@code messagesTotal} is the total number of messages in the mailbox
     * **/
    private final int messagesTotal;

    /**
     * {@code threadsTotal} is the total number of threads in the mailbox
     * **/
    private final int threadsTotal;

    /**
     * {@code historyId} is the ID of the mailbox's current history record
     **/
    private final BigInteger historyId;

    /**
     * Constructor to init a {@link Profile}
     *
     * @param emailAddress:  the user's email address
     * @param messagesTotal: the total number of messages in the mailbox
     * @param threadsTotal:  the total number of threads in the mailbox
     * @param historyId:     the ID of the mailbox's current history record
     **/
    public Profile(String emailAddress, int messagesTotal, int threadsTotal, BigInteger historyId) {
        this.emailAddress = emailAddress;
        this.messagesTotal = messagesTotal;
        this.threadsTotal = threadsTotal;
        this.historyId = historyId;
    }

    /** Constructor to init a {@link Profile}
     * @param jProfile: {@code "profile"} details as {@link JSONObject}
     * **/
    public Profile(JSONObject jProfile) {
        JsonHelper hProfile = new JsonHelper(jProfile);
        emailAddress = hProfile.getString("emailAddress");
        messagesTotal = hProfile.getInt("messagesTotal", 0);
        threadsTotal = hProfile.getInt("threadsTotal", 0);
        historyId = hProfile.getBigInteger("historyId", BigInteger.valueOf(0));
    }

    /** Method to get {@link #emailAddress} instance <br>
     * Any params required
     * @return {@link #emailAddress} instance as {@link String}
     * **/
    public String getEmailAddress() {
        return emailAddress;
    }

    /** Method to get {@link #messagesTotal} instance <br>
     * Any params required
     * @return {@link #messagesTotal} instance as int
     * **/
    public int getMessagesTotal() {
        return messagesTotal;
    }

    /** Method to get {@link #threadsTotal} instance <br>
     * Any params required
     * @return {@link #threadsTotal} instance as int
     * **/
    public int getThreadsTotal() {
        return threadsTotal;
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
     * Returns a string representation of the object <br>
     * Any params required
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
