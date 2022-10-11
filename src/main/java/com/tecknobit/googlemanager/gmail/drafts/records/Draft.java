package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Draft {

    private final String id;
    private final String threadId;
    private final ArrayList<String> labelIds;
    private final String snippet;
    private final String historyId;
    private final long internalDate;
    private final MessagePart payload;
    private final int sizeEstimate;
    private final String raw;

    public Draft(String id, String threadId, ArrayList<String> labelIds, String snippet, String historyId,
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
        historyId = hDraft.getString("historyId");
        internalDate = hDraft.getLong("internalDate");
        payload = new MessagePart(hDraft.getJSONObject("payload", new JSONObject()));
        sizeEstimate = hDraft.getInt("sizeEstimate");
        raw = hDraft.getString("raw");
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

    public String getHistoryId() {
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

    @Override
    public String toString() {
        return "Draft{" +
                "id='" + id + '\'' +
                ", threadId='" + threadId + '\'' +
                ", labelIds=" + labelIds +
                ", snippet='" + snippet + '\'' +
                ", historyId='" + historyId + '\'' +
                ", internalDate=" + internalDate +
                ", payload=" + payload +
                ", sizeEstimate=" + sizeEstimate +
                ", raw='" + raw + '\'' +
                '}';
    }

}
