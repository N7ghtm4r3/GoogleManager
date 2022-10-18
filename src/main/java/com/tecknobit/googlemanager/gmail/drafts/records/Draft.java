package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONObject;

/**
 * The {@code Draft} class is useful to format a Gmail's draft
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts">Gmail drafts</a>
 **/
public class Draft {

    /**
     * {@code id} is the immutable ID of the draft
     **/
    private final String id;

    /**
     * {@code id} is the message content of the draft
     **/
    private final Message message;

    /**
     * Constructor to init a {@link Draft}
     *
     * @param id:      the immutable ID of the draft
     * @param message: the message content of the draft
     **/
    public Draft(String id, Message message) {
        this.id = id;
        this.message = message;
    }

    /**
     * Constructor to init a {@link Draft}
     *
     * @param jDraft: {@code "draft"} details as {@link JSONObject}
     **/
    public Draft(JSONObject jDraft) {
        JsonHelper hDraft = new JsonHelper(jDraft);
        id = hDraft.getString("id", null);
        JSONObject jMessage = hDraft.getJSONObject("payload", null);
        if (jMessage != null)
            message = new Message(jMessage);
        else
            message = new Message(hDraft.getJSONObject("message", null));
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
     * Method to get {@link #message} instance <br>
     * Any params required
     *
     * @return {@link #message} instance as {@link Message} custom object
     **/
    public Message getMessage() {
        return message;
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
