package com.tecknobit.googlemanager.gmail.history.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code LabelAction} class is useful to format a Gmail's label action
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#labeladded">Label added</a>
 *     </li>
 *     <li>
 *         <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#labelremoved">Label removed</a>
 *     </li>
 * </ul>
 **/
public class LabelAction {

    /**
     * {@code message} message
     **/
    private final Message message;

    /**
     * {@code labelIds} label IDs from the message
     **/
    private final ArrayList<String> labelIds;

    /**
     * Constructor to init a {@link LabelAction}
     *
     * @param message:  message
     * @param labelIds: label IDs from the message
     **/
    public LabelAction(Message message, ArrayList<String> labelIds) {
        this.message = message;
        this.labelIds = labelIds;
    }

    /**
     * Constructor to init a {@link LabelAction}
     *
     * @param jLabel: {@code "label"} details as {@link JSONObject}
     **/
    public LabelAction(JSONObject jLabel) {
        JsonHelper hLabel = new JsonHelper(jLabel);
        message = new Message(hLabel.getJSONObject("message", new JSONObject()));
        JSONArray jLabelIds = hLabel.getJSONArray("labelIds", new JSONArray());
        labelIds = new ArrayList<>();
        for (int j = 0; j < jLabelIds.length(); j++)
            labelIds.add(jLabelIds.getString(j));
    }

    /**
     * Method to get {@link #message} instance <br>
     * Any params required
     *
     * @return {@link #message} instance as {@link Message}
     **/
    public Message getMessage() {
        return message;
    }

    /**
     * Method to get {@link #labelIds} instance <br>
     * Any params required
     *
     * @return {@link #labelIds} instance as {@link Collection} of {@link String}
     **/
    public Collection<String> getLabelIds() {
        return labelIds;
    }

    /**
     * Method to add at the {@link #labelIds} instance another one label id
     *
     * @param labelId : label identifier to add
     * @apiNote only if the entry is not present in the {@link #labelIds} list will be inserted
     **/
    public void addLabelId(String labelId) {
        if (!labelIds.contains(labelId))
            labelIds.add(labelId);
    }

    /**
     * Method to remove from the {@link #labelIds} one label id
     *
     * @param labelIdToRemove : label identifier to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeLabelId(String labelIdToRemove) {
        return labelIds.remove(labelIdToRemove);
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
