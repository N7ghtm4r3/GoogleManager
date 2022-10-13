package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import static com.google.common.io.BaseEncoding.base64Url;

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
    private final String threadId;
    private final ArrayList<String> labelIds;
    private final String snippet;
    private final BigInteger historyId;
    private final long internalDate;
    private final Message payload;
    private final int sizeEstimate;
    private String raw;

    public Draft(String id, String threadId, ArrayList<String> labelIds, String snippet, BigInteger historyId,
                 long internalDate, Message payload, int sizeEstimate, String raw) {
        this.id = id;
        this.threadId = threadId;
        this.labelIds = labelIds;
        this.snippet = snippet;
        this.historyId = historyId;
        this.internalDate = internalDate;
        this.payload = payload;
        this.sizeEstimate = sizeEstimate;
        this.raw = raw;
    }

    public Draft(JSONObject jDraft) {
        JsonHelper hDraft = new JsonHelper(jDraft);
        id = hDraft.getString("id", null);
        threadId = hDraft.getString("threadId", null);
        JSONArray jLabelIds = hDraft.getJSONArray("labelIds", new JSONArray());
        labelIds = new ArrayList<>();
        for (int j = 0; j < jLabelIds.length(); j++)
            labelIds.add(jLabelIds.getString(j));
        snippet = hDraft.getString("snippet", null);
        historyId = hDraft.getBigInteger("historyId", BigInteger.valueOf(0));
        internalDate = hDraft.getLong("internalDate", 0);
        payload = new Message(hDraft.getJSONObject("payload", new JSONObject()));
        sizeEstimate = hDraft.getInt("sizeEstimate", 0);
        raw = hDraft.getString("raw", null);
    }

    public String getId() {
        return id;
    }

    public String getThreadId() {
        return threadId;
    }

    public Collection<String> getLabelIds() {
        return labelIds;
    }

    public String getSnippet() {
        return snippet;
    }

    public BigInteger getHistoryId() {
        return historyId;
    }

    public long getInternalDate() {
        return internalDate;
    }

    public Message getPayload() {
        return payload;
    }

    public int getSizeEstimate() {
        return sizeEstimate;
    }

    public String getRaw() {
        return raw;
    }

    public void encodeRaw() {
        try {
            Base64.getUrlDecoder().decode(raw);
        } catch (IllegalArgumentException e) {
            raw = base64Url().omitPadding().encode(raw.getBytes());
        }
    }

    public void decodeRaw() {
        try {
            raw = new String(Base64.getUrlDecoder().decode(raw));
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
