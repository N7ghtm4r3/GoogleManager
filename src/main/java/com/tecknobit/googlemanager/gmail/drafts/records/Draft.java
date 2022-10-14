package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

/**
 * The {@code Draft} class is useful to format a Gmail's draft
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts">Gmail drafts</a>
 **/
public class Draft {

    /**
     * {@code CHAT_LABEL} is a constant for chat label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CHAT_LABEL = "CHAT";

    /**
     * {@code SENT_LABEL} is a constant for sent label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String SENT_LABEL = "SENT";

    /**
     * {@code INBOX_LABEL} is a constant for inbox label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String INBOX_LABEL = "INBOX";

    /**
     * {@code IMPORTANT_LABEL} is a constant for important label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String IMPORTANT_LABEL = "IMPORTANT";

    /**
     * {@code TRASH_LABEL} is a constant for trash label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String TRASH_LABEL = "TRASH";

    /**
     * {@code DRAFT_LABEL} is a constant for draft label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String DRAFT_LABEL = "DRAFT";

    /**
     * {@code SPAM_LABEL} is a constant for spam label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String SPAM_LABEL = "SPAM";

    /**
     * {@code CATEGORY_FORUMS_LABEL} is a constant for category forums label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_FORUMS_LABEL = "CATEGORY_FORUMS";

    /**
     * {@code CATEGORY_UPDATES_LABEL} is a constant for category updates label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_UPDATES_LABEL = "CATEGORY_UPDATES";

    /**
     * {@code CATEGORY_PERSONAL_LABEL} is a constant for category personal label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_PERSONAL_LABEL = "CATEGORY_PERSONAL";

    /**
     * {@code CATEGORY_PROMOTIONS_LABEL} is a constant for category promotions label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_PROMOTIONS_LABEL = "CATEGORY_PROMOTIONS";

    /**
     * {@code CATEGORY_SOCIAL_LABEL} is a constant for category social label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_SOCIAL_LABEL = "CATEGORY_SOCIAL";

    /**
     * {@code STARRED_LABEL} is a constant for starred label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String STARRED_LABEL = "STARRED";

    /**
     * {@code UNREAD_LABEL} is a constant for unread label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String UNREAD_LABEL = "UNREAD";

    /**
     * {@code UNWANTED_LABEL} is a constant for unwanted label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String UNWANTED_LABEL = "UNWANTED";

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
