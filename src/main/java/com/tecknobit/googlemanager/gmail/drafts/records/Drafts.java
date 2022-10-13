package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Drafts {

    private final ArrayList<Draft> drafts;
    private final String nextPageToken;
    private final int resultSizeEstimate;

    public Drafts(ArrayList<Draft> drafts, String nextPageToken, int resultSizeEstimate) {
        this.drafts = drafts;
        this.nextPageToken = nextPageToken;
        this.resultSizeEstimate = resultSizeEstimate;
    }

    public Drafts(JSONObject jDrafts) {
        JsonHelper hDrafts = new JsonHelper(jDrafts);
        drafts = new ArrayList<>();
        JSONArray jDraftsList = hDrafts.getJSONArray("drafts", new JSONArray());
        for (int j = 0; j < jDraftsList.length(); j++)
            drafts.add(new Draft(jDraftsList.getJSONObject(j)));
        nextPageToken = hDrafts.getString("nextPageToken", null);
        resultSizeEstimate = hDrafts.getInt("resultSizeEstimate", 0);
    }

    public ArrayList<Draft> getDrafts() {
        return drafts;
    }

    public void addDraft(Draft draft) {
        if (!drafts.contains(draft))
            drafts.add(draft);
    }

    public boolean removeDraft(Draft draftToRemove) {
        return drafts.remove(draftToRemove);
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public int getResultSizeEstimate() {
        return resultSizeEstimate;
    }

    @Override
    public String toString() {
        return "Drafts{" +
                "drafts=" + drafts +
                ", nextPageToken='" + nextPageToken + '\'' +
                ", resultSizeEstimate=" + resultSizeEstimate +
                '}';
    }

}
