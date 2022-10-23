package com.tecknobit.googlemanager.gmail.threads.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code GmailThread} class is useful to format a Gmail's thread
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads#resource:-thread">Thread</a>
 **/
public class GmailThread {

    /**
     * {@code id} the unique ID of the thread
     **/
    private final String id;

    /**
     * {@code snippet} a short part of the message text
     **/
    private final String snippet;

    /**
     * {@code historyId} the ID of the last history record that modified this thread
     **/
    private final BigInteger historyId;

    /**
     * {@code messages} the list of messages in the thread
     **/
    private final ArrayList<Message> messages;

    /**
     * Constructor to init a {@link GmailThread}
     *
     * @param id:        the unique ID of the thread
     * @param snippet:   a short part of the message text
     * @param historyId: the ID of the last history record that modified this thread
     * @param messages:  the list of messages in the thread
     **/
    public GmailThread(String id, String snippet, BigInteger historyId, ArrayList<Message> messages) {
        this.id = id;
        this.snippet = snippet;
        this.historyId = historyId;
        this.messages = messages;
    }

    /**
     * Constructor to init a {@link GmailThread}
     *
     * @param jThread: {@code "thread"} details as {@link JSONObject}
     **/
    public GmailThread(JSONObject jThread) {
        JsonHelper hThread = new JsonHelper(jThread);
        id = hThread.getString("id");
        snippet = hThread.getString("snippet");
        historyId = hThread.getBigInteger("historyId", BigInteger.valueOf(0));
        messages = new ArrayList<>();
        JSONArray messagesList = hThread.getJSONArray("messages", new JSONArray());
        for (int j = 0; j < messagesList.length(); j++)
            messages.add(new Message(messagesList.getJSONObject(j)));
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as {@link String}
     **/
    public String getId() {
        return id;
    }

    /**
     * Method to get {@link #snippet} instance <br>
     * Any params required
     *
     * @return {@link #snippet} instance as {@link String}
     **/
    public String getSnippet() {
        return snippet;
    }

    /**
     * Method to get {@link #historyId} instance <br>
     * Any params required
     *
     * @return {@link #historyId} instance as {@link BigInteger}
     **/
    public BigInteger getHistoryId() {
        return historyId;
    }

    /**
     * Method to get {@link #messages} instance <br>
     * Any params required
     *
     * @return {@link #messages} instance as {@link Collection} of {@link Message}
     **/
    public Collection<Message> getMessages() {
        return messages;
    }

    /**
     * Method to add at the {@link #messages} instance another one message
     *
     * @param message: message to add
     * @apiNote only if the entry is not present in the {@link #messages} list will be inserted
     **/
    public void addMessage(Message message) {
        if (!messages.contains(message))
            messages.add(message);
    }

    /**
     * Method to remove from the {@link #messages} one message
     *
     * @param messageToRemove: message to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeMessage(Message messageToRemove) {
        return messages.remove(messageToRemove);
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
