package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
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
    private final MessagePart payload;
    private final int sizeEstimate;
    private String raw;
    private boolean alreadyEncoded;

    public Draft(String id, String threadId, ArrayList<String> labelIds, String snippet, BigInteger historyId,
                 long internalDate, MessagePart payload, int sizeEstimate, String raw) {
        this.id = id;
        this.threadId = threadId;
        this.labelIds = labelIds;
        this.snippet = snippet;
        this.historyId = historyId;
        this.internalDate = internalDate;
        this.payload = payload;
        this.sizeEstimate = sizeEstimate;
        this.raw = raw;
        alreadyEncoded = false;
    }

    public Draft(JSONObject jDraft) {
        JsonHelper hDraft = new JsonHelper(jDraft);
        id = hDraft.getString("id");
        threadId = hDraft.getString("threadId");
        JSONArray jLabelIds = hDraft.getJSONArray("labelIds", new JSONArray());
        labelIds = new ArrayList<>();
        for (int j = 0; j < jLabelIds.length(); j++)
            labelIds.add(jLabelIds.getString(j));
        snippet = hDraft.getString("snippet");
        historyId = hDraft.getBigInteger("historyId");
        internalDate = hDraft.getLong("internalDate");
        payload = new MessagePart(hDraft.getJSONObject("payload", new JSONObject()));
        sizeEstimate = hDraft.getInt("sizeEstimate");
        raw = hDraft.getString("raw");
        alreadyEncoded = false;
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

    public MessagePart getPayload() {
        return payload;
    }

    public int getSizeEstimate() {
        return sizeEstimate;
    }

    public String getRaw() {
        return raw;
    }

    public void encodeRaw() {
        if (!alreadyEncoded) {
            raw = base64Url().omitPadding().encode(raw.getBytes());
            alreadyEncoded = true;
        }
    }

    public void decodeRaw() {
        if (alreadyEncoded) {
            raw = new String(base64Url().omitPadding().decode(raw));
            alreadyEncoded = false;
        }
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
