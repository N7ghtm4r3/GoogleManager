package com.tecknobit.googlemanager.gmail.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

/**
 * The {@code BaseList} class is useful to format base list from Gmail's responses
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts/list">users.drafts.list</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages/list">users.messages.list</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">users.threads.list</a>
 *     </li>
 * </ul>
 **/
public class BaseList {

    /**
     * {@code nextPageToken} token to retrieve the next page of results in the list
     **/
    protected final String nextPageToken;

    /**
     * {@code resultSizeEstimate} estimated total number of results
     **/
    protected final int resultSizeEstimate;

    /**
     * {@code hList} instance to work with {@code "JSON"} details
     **/
    protected final JsonHelper hList;

    /**
     * Constructor to init a {@link BaseList}
     *
     * @param nextPageToken:      token to retrieve the next page of results in the list
     * @param resultSizeEstimate: estimated total number of results
     **/
    public BaseList(String nextPageToken, int resultSizeEstimate) {
        this.nextPageToken = nextPageToken;
        this.resultSizeEstimate = resultSizeEstimate;
        hList = null;
    }

    /**
     * Constructor to init a {@link BaseList}
     *
     * @param jList: {@code "list"} details as {@link JSONObject}
     **/
    public BaseList(JSONObject jList) {
        hList = new JsonHelper(jList);
        nextPageToken = hList.getString("nextPageToken", null);
        resultSizeEstimate = hList.getInt("resultSizeEstimate", 0);
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
