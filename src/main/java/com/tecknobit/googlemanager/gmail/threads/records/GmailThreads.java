package com.tecknobit.googlemanager.gmail.threads.records;

import com.tecknobit.googlemanager.gmail.records.BaseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code GmailThreads} class is useful to format a Gmail's threads list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.threads/list">users.threads.list</a>
 * @see BaseList
 **/
public class GmailThreads extends BaseList {

    /**
     * {@code threads} list of threads
     **/
    private final ArrayList<GmailThread> threads;

    /**
     * Constructor to init a {@link GmailThreads}
     *
     * @param nextPageToken      :      token to retrieve the next page of results in the list
     * @param resultSizeEstimate : estimated total number of results
     * @param threads:           list of threads
     **/
    public GmailThreads(String nextPageToken, int resultSizeEstimate, ArrayList<GmailThread> threads) {
        super(nextPageToken, resultSizeEstimate);
        this.threads = threads;
    }

    /**
     * Constructor to init a {@link GmailThreads}
     *
     * @param jThreads : {@code "threads"} details as {@link JSONObject}
     **/
    public GmailThreads(JSONObject jThreads) {
        super(jThreads);
        threads = new ArrayList<>();
        JSONArray threadsList = hList.getJSONArray("threads", new JSONArray());
        for (int j = 0; j < threadsList.length(); j++)
            threads.add(new GmailThread(threadsList.getJSONObject(j)));
    }

    /**
     * Method to get {@link #threads} instance <br>
     * Any params required
     *
     * @return {@link #threads} instance as {@link Collection} of {@link GmailThread}
     **/
    public Collection<GmailThread> getThreads() {
        return threads;
    }

    /**
     * Method to add at the {@link #threads} instance another one thread
     *
     * @param thread: thread to add
     * @apiNote only if the entry is not present in the {@link #threads} list will be inserted
     **/
    public void addThread(GmailThread thread) {
        if (!threads.contains(thread))
            threads.add(thread);
    }

    /**
     * Method to remove from the {@link #threads} one thread
     *
     * @param threadToRemove: thread to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeThread(GmailThread threadToRemove) {
        return threads.remove(threadToRemove);
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
