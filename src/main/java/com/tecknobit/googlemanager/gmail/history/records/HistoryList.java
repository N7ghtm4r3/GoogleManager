package com.tecknobit.googlemanager.gmail.history.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import com.tecknobit.googlemanager.gmail.records.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code HistoryList} class is useful to format a Gmail's history list
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.history/list#history">History list</a>
 **/
public class HistoryList {

    /**
     * {@code id} the mailbox sequence ID
     **/
    private final String id;

    /**
     * {@code messages} list of messages changed in this history record
     **/
    private final ArrayList<Message> messages;

    /**
     * {@code messagesAdded} messages added to the mailbox in this history record
     **/
    private final ArrayList<Message> messagesAdded;

    /**
     * {@code messagesDeleted} messages deleted (not Trashed) from the mailbox in this history record
     **/
    private final ArrayList<Message> messagesDeleted;

    /**
     * {@code labelsAdded} labels added to messages in this history record
     **/
    private final ArrayList<LabelAction> labelsAdded;

    /**
     * {@code labelsRemoved} labels removed from messages in this history record
     **/
    private final ArrayList<LabelAction> labelsRemoved;

    /**
     * Constructor to init a {@link HistoryList}
     *
     * @param id:              the mailbox sequence ID
     * @param messages:        list of messages changed in this history record
     * @param messagesAdded:   messages added to the mailbox in this history record
     * @param messagesDeleted: messages deleted (not Trashed) from the mailbox in this history record
     * @param labelsAdded:     labels added to messages in this history record
     * @param labelsRemoved:   labels removed from messages in this history record
     **/
    public HistoryList(String id, ArrayList<Message> messages, ArrayList<Message> messagesAdded,
                       ArrayList<Message> messagesDeleted, ArrayList<LabelAction> labelsAdded,
                       ArrayList<LabelAction> labelsRemoved) {
        this.id = id;
        this.messages = messages;
        this.messagesAdded = messagesAdded;
        this.messagesDeleted = messagesDeleted;
        this.labelsAdded = labelsAdded;
        this.labelsRemoved = labelsRemoved;
    }

    /**
     * Constructor to init a {@link HistoryList}
     *
     * @param jHistoryList: {@code "history list"} details as {@link JSONObject}
     **/
    public HistoryList(JSONObject jHistoryList) {
        JsonHelper hHistoryList = new JsonHelper(jHistoryList);
        id = hHistoryList.getString("id");
        messages = loadMessagesList(hHistoryList.getJSONArray("messages"), "messages");
        messagesAdded = loadMessagesList(hHistoryList.getJSONArray("messagesAdded"), "messagesAdded");
        messagesDeleted = loadMessagesList(hHistoryList.getJSONArray("messagesDeleted"), "messagesDeleted");
        labelsAdded = loadLabelsList(hHistoryList.getJSONArray("labelsAdded"), "labelsAdded");
        labelsRemoved = loadLabelsList(hHistoryList.getJSONArray("labelsRemoved"), "labelsRemoved");
    }

    /**
     * Method to create a messages list
     *
     * @param list:    list from create the messages list in {@link JSONArray} format
     * @param keyList: key of the list from fetch messages
     * @return list of messages as {@link ArrayList} of {@link Message} custom object
     **/
    private ArrayList<Message> loadMessagesList(JSONArray list, String keyList) {
        if (list == null)
            list = new JSONArray();
        ArrayList<Message> messages = new ArrayList<>();
        for (int j = 0; j < list.length(); j++) {
            JSONObject message = list.getJSONObject(j);
            if (!keyList.equals("messages"))
                message = message.getJSONObject("message");
            messages.add(new Message(message));
        }
        return messages;
    }

    /**
     * Method to create a labels list
     *
     * @param list:    list from create the labels list in {@link JSONArray} format
     * @param keyList: key of the list from fetch labels
     * @return list of labels as {@link ArrayList} of {@link LabelAction} custom object
     **/
    private ArrayList<LabelAction> loadLabelsList(JSONArray list, String keyList) {
        if (list == null)
            list = new JSONArray();
        ArrayList<LabelAction> labels = new ArrayList<>();
        for (int j = 0; j < list.length(); j++)
            labels.add(new LabelAction(list.getJSONObject(j)));
        return labels;
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
     * Method to get {@link #messages} instance <br>
     * Any params required
     *
     * @return {@link #messages} instance as {@link Collection} of {@link Message}
     **/
    public Collection<Message> getMessages() {
        return messages;
    }

    /**
     * Method to add at the {@link #messages} instance another one message
     *
     * @param message : message to add
     * @apiNote only if the entry is not present in the {@link #messages} list will be inserted
     **/
    public void addMessage(Message message) {
        if (!messages.contains(message))
            messages.add(message);
    }

    /**
     * Method to remove from the {@link #messages} one message
     *
     * @param messageToRemove : message to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeMessage(Message messageToRemove) {
        return messages.remove(messageToRemove);
    }

    /**
     * Method to get {@link #messagesAdded} instance <br>
     * Any params required
     *
     * @return {@link #messagesAdded} instance as {@link Collection} of {@link Message}
     **/
    public Collection<Message> getMessagesAdded() {
        return messagesAdded;
    }

    /**
     * Method to add at the {@link #messagesAdded} instance another one message added
     *
     * @param message : message added to add
     * @apiNote only if the entry is not present in the {@link #messagesAdded} list will be inserted
     **/
    public void addMessageAdded(Message message) {
        if (!messagesAdded.contains(message))
            messagesAdded.add(message);
    }

    /**
     * Method to remove from the {@link #messagesAdded} one message
     *
     * @param messageAddedToRemove : message added to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeMessageAdded(Message messageAddedToRemove) {
        return messagesAdded.remove(messageAddedToRemove);
    }

    /**
     * Method to get {@link #messagesDeleted} instance <br>
     * Any params required
     *
     * @return {@link #messagesDeleted} instance as {@link Collection} of {@link Message}
     **/
    public Collection<Message> getMessagesDeleted() {
        return messagesDeleted;
    }

    /**
     * Method to add at the {@link #messagesDeleted} instance another one message deleted
     *
     * @param message : message deleted to add
     * @apiNote only if the entry is not present in the {@link #messagesDeleted} list will be inserted
     **/
    public void addMessageDeleted(Message message) {
        if (!messagesDeleted.contains(message))
            messagesDeleted.add(message);
    }

    /**
     * Method to remove from the {@link #messagesDeleted} one message
     *
     * @param messageDeletedToRemove : message deleted to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeMessageDeleted(Message messageDeletedToRemove) {
        return messagesDeleted.remove(messageDeletedToRemove);
    }

    /**
     * Method to get {@link #labelsAdded} instance <br>
     * Any params required
     *
     * @return {@link #labelsAdded} instance as {@link Collection} of {@link LabelAction}
     **/
    public Collection<LabelAction> getLabelsAdded() {
        return labelsAdded;
    }

    /**
     * Method to add at the {@link #labelsAdded} instance another one label added
     *
     * @param label : label added to add
     * @apiNote only if the entry is not present in the {@link #labelsAdded} list will be inserted
     **/
    public void addLabelAdded(LabelAction label) {
        if (!labelsAdded.contains(label))
            labelsAdded.add(label);
    }

    /**
     * Method to remove from the {@link #labelsAdded} one label
     *
     * @param labelAddedToRemove : label added to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeLabelAdded(LabelAction labelAddedToRemove) {
        return labelsAdded.remove(labelAddedToRemove);
    }

    /**
     * Method to get {@link #labelsRemoved} instance <br>
     * Any params required
     *
     * @return {@link #labelsRemoved} instance as {@link Collection} of {@link LabelAction}
     **/
    public Collection<LabelAction> getLabelsRemoved() {
        return labelsRemoved;
    }

    /**
     * Method to add at the {@link #labelsRemoved} instance another one label removed
     *
     * @param label : label removed to add
     * @apiNote only if the entry is not present in the {@link #labelsAdded} list will be inserted
     **/
    public void addLabelRemoved(LabelAction label) {
        if (!labelsRemoved.contains(label))
            labelsRemoved.add(label);
    }

    /**
     * Method to remove from the {@link #labelsRemoved} one label
     *
     * @param labelAddedToRemove : label removed to remove
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     **/
    public boolean removeLabelRemoved(LabelAction labelAddedToRemove) {
        return labelsRemoved.remove(labelAddedToRemove);
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
