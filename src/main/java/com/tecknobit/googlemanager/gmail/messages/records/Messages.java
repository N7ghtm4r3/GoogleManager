package com.tecknobit.googlemanager.gmail.messages.records;

import com.tecknobit.googlemanager.gmail.records.BaseList;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code Messages} class is useful to format a Gmail's messages list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">users.messages.list</a>
 * @see BaseList
 **/
public class Messages extends BaseList {

    /**
     * {@code messages} list of messages
     **/
    private final ArrayList<Message> messages;

    /**
     * Constructor to init a {@link Messages}
     *
     * @param nextPageToken:      token to retrieve the next page of results in the list
     * @param resultSizeEstimate: estimated total number of results
     * @param messages:           list of messages
     **/
    public Messages(String nextPageToken, int resultSizeEstimate, ArrayList<Message> messages) {
        super(nextPageToken, resultSizeEstimate);
        this.messages = messages;
    }

    /**
     * Constructor to init a {@link Messages}
     *
     * @param jList: {@code "messages"} details as {@link JSONObject}
     **/
    public Messages(JSONObject jList) {
        super(jList);
        messages = new ArrayList<>();
        JSONArray messagesList = hList.getJSONArray("messages", new JSONArray());
        for (int j = 0; j < messagesList.length(); j++)
            messages.add(new Message(messagesList.getJSONObject(j)));
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
