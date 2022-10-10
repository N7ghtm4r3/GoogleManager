package com.tecknobit.gmailmanager.managers.gmail.users.records;

import org.json.JSONObject;

public class Profile {

    private final String emailAddress;
    private final int messagesTotal;
    private final int threadsTotal;
    private final String historyId;

    public Profile(String emailAddress, int messagesTotal, int threadsTotal, String historyId) {
        this.emailAddress = emailAddress;
        this.messagesTotal = messagesTotal;
        this.threadsTotal = threadsTotal;
        this.historyId = historyId;
    }

    public Profile(JSONObject jProfile) {
        emailAddress = jProfile.getString("emailAddress");
        messagesTotal = jProfile.getInt("messagesTotal");
        threadsTotal = jProfile.getInt("threadsTotal");
        historyId = jProfile.getString("historyId");
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getMessagesTotal() {
        return messagesTotal;
    }

    public int getThreadsTotal() {
        return threadsTotal;
    }

    public String getHistoryId() {
        return historyId;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "emailAddress='" + emailAddress + '\'' +
                ", messagesTotal=" + messagesTotal +
                ", threadsTotal=" + threadsTotal +
                ", historyId='" + historyId + '\'' +
                '}';
    }

}
