package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

public class Draft {

    public static final String CHAT_LABEL = "CHAT";
    public static final String SENT_LABEL = "SENT";
    public static final String INBOX_LABEL = "INBOX";
    public static final String IMPORTANT_LABEL = "IMPORTANT";
    public static final String TRASH_LABEL = "TRASH";
    public static final String DRAFT_LABEL = "DRAFT";
    public static final String SPAM_LABEL = "SPAM";
    public static final String CATEGORY_FORUMS_LABEL = "CATEGORY_FORUMS";
    public static final String CATEGORY_UPDATES_LABEL = "CATEGORY_UPDATES";
    public static final String CATEGORY_PERSONAL_LABEL = "CATEGORY_PERSONAL";
    public static final String CATEGORY_PROMOTIONS_LABEL = "CATEGORY_PROMOTIONS";
    public static final String CATEGORY_SOCIAL_LABEL = "CATEGORY_SOCIAL";
    public static final String STARRED_LABEL = "STARRED";
    public static final String UNREAD_LABEL = "UNREAD";
    public static final String UNWANTED_LABEL = "UNWANTED";
    private final String id;
    private final Message message;

    public Draft(String id, Message message) {
        this.id = id;
        this.message = message;
    }

    public Draft(JSONObject jDraft) {
        JsonHelper hDraft = new JsonHelper(jDraft);
        id = hDraft.getString("id", null);
        JSONObject jMessage = hDraft.getJSONObject("payload", null);
        if (jMessage != null)
            message = new Message(jMessage);
        else
            message = new Message(hDraft.getJSONObject("message", null));
    }

    public String getId() {
        return id;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
