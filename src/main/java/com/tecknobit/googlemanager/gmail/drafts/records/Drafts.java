package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code Draft} class is useful to format a Gmail's drafts list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">users.drafts.list</a>
 **/
public class Drafts {

    /**
     * {@code drafts} list of drafts
     **/
    private final ArrayList<Draft> drafts;

    /**
     * {@code nextPageToken} token to retrieve the next page of results in the list
     **/
    private final String nextPageToken;

    /**
     * {@code resultSizeEstimate} estimated total number of results
     **/
    private final int resultSizeEstimate;

    /**
     * Constructor to init a {@link Drafts}
     *
     * @param drafts:             list of drafts
     * @param nextPageToken:      token to retrieve the next page of results in the list
     * @param resultSizeEstimate: estimated total number of results
     **/
    public Drafts(ArrayList<Draft> drafts, String nextPageToken, int resultSizeEstimate) {
        this.drafts = drafts;
        this.nextPageToken = nextPageToken;
        this.resultSizeEstimate = resultSizeEstimate;
    }

    /**
     * Constructor to init a {@link Drafts}
     *
     * @param jDrafts: {@code "drafts"} details as {@link JSONObject}
     **/
    public Drafts(JSONObject jDrafts) {
        JsonHelper hDrafts = new JsonHelper(jDrafts);
        drafts = new ArrayList<>();
        JSONArray jDraftsList = hDrafts.getJSONArray("drafts", new JSONArray());
        for (int j = 0; j < jDraftsList.length(); j++)
            drafts.add(new Draft(jDraftsList.getJSONObject(j)));
        nextPageToken = hDrafts.getString("nextPageToken", null);
        resultSizeEstimate = hDrafts.getInt("resultSizeEstimate", 0);
    }

    /**
     * Method to get {@link #drafts} instance <br>
     * Any params required
     *
     * @return {@link #drafts} instance as {@link Collection} of {@link Draft}
     **/
    public Collection<Draft> getDrafts() {
        return drafts;
    }

    /**
     * Method to add at the {@link #drafts} instance another one draft
     *
     * @param draft: draft to add
     * @apiNote only if the entry is not present in the {@link #drafts} list will be inserted
     **/
    public void addDraft(Draft draft) {
        if (!drafts.contains(draft))
            drafts.add(draft);
    }

    /**
     * Method to remove from the {@link #drafts} one draft
     *
     * @param draftToRemove: draft to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeDraft(Draft draftToRemove) {
        return drafts.remove(draftToRemove);
    }

    /**
     * Method to get {@link #nextPageToken} instance <br>
     * Any params required
     *
     * @return {@link #nextPageToken} instance as {@link String}
     **/
    public String getNextPageToken() {
        return nextPageToken;
    }

    /**
     * Method to get {@link #resultSizeEstimate} instance <br>
     * Any params required
     *
     * @return {@link #resultSizeEstimate} instance as int
     **/
    public int getResultSizeEstimate() {
        return resultSizeEstimate;
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
