package com.tecknobit.googlemanager.gmail.labels;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.LabelColor;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.labels.records.Label;
import com.tecknobit.googlemanager.gmail.labels.records.Label.LabelListVisibility;
import com.tecknobit.googlemanager.gmail.labels.records.Label.MessageListVisibility;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;
import static com.tecknobit.googlemanager.gmail.labels.records.Label.LabelColor.AllowedColor;

/**
 * The {@code GmailLabelsManager} class is useful to manage all Gmail's labels endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels">Gmail labels</a>
 **/
public class GmailLabelsManager extends GmailManager {

    /**
     * {@code labels} is the instance for {@link Gmail.Users.Labels}'s service
     **/
    private final Gmail.Users.Labels labels = gmail.labels();

    /**
     * Constructor to init a {@link GmailLabelsManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param host:            host used in the auth operations
     * @param callBackPath:    callback path used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailLabelsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              int port, String host, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailLabelsManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailLabelsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, applicationName);
    }

    /**
     * Constructor to init a {@link GmailLabelsManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailLabelsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailLabelsManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param callBackPath:    callback path used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailLabelsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              int port, String callBackPath, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath, applicationName);
    }

    /**
     * Constructor to init a {@link GmailLabelsManager}
     *
     * @param clientId:        client identifier value
     * @param clientSecret:    client secret value
     * @param userId:          used to identifier a user -> me to use an authenticated user
     * @param accessType:      access type used in the auth operations
     * @param approvalPrompt:  approval prompt type used in the auth operations
     * @param port:            port used in the auth operations
     * @param host:            host used in the auth operations
     * @param applicationName: name of application to give at the project
     * @throws IOException when auth request has been go wrong
     **/
    public GmailLabelsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    /**
     * Constructor to init a {@link GmailLabelsManager} <br>
     * Any params required
     *
     * @apiNote this constructor is useful to instantiate a new {@link GmailManager}'s manager without reinsert
     * credentials and is useful in those cases if you need to use different manager at the same time:
     * <pre>
     *     {@code
     *        //You need to insert all credentials requested
     *        GmailManager firstManager = new GmailManager(CLIENT_ID, CLIENT_SECRET, "email@gmail.com",
     *                 ACCESS_TYPE, APPROVAL_PROMPT, port, "host", "callback_path", "application_name");
     *        //You don't need to insert all credentials to make manager work
     *        GmailManager secondManager = new GmailManager(); //same credentials used
     *     }
     * </pre>
     **/
    public GmailLabelsManager() throws IOException {
        super();
    }

    /**
     * Method to create a new label
     *
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the{@code " Gmail web interface"}
     * @param labelListVisibility:   the visibility of the label in the label list in the{@code " Gmail web interface"}
     * @return label created as {@link Label} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Label createLabel(String name, MessageListVisibility messageListVisibility,
                             LabelListVisibility labelListVisibility) throws IOException {
        return createLabel(name, messageListVisibility, labelListVisibility, LIBRARY_OBJECT);
    }

    /**
     * Method to create a new label
     *
     * @param label: label filled with detail to create a new one
     * @return label created as {@link Label} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Label createLabel(Label label) throws IOException {
        return createLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(),
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a new label
     *
     * @param label:  label filled with detail to create a new one
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createLabel(Label label, ReturnFormat format) throws IOException {
        return createLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(), format);
    }

    /**
     * Method to create a new label
     *
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the{@code " Gmail web interface"}
     * @param labelListVisibility:   the visibility of the label in the label list in the{@code " Gmail web interface"}
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createLabel(String name, MessageListVisibility messageListVisibility,
                             LabelListVisibility labelListVisibility, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label responseLabel = labels.create(userId, assembleLabel(name,
                messageListVisibility, labelListVisibility)).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(responseLabel).toString();
            case LIBRARY_OBJECT:
                return (T) new Label(new JSONObject(responseLabel));
            default:
                return (T) responseLabel.toString();
        }
    }

    /**
     * Method to create a new label with custom colors
     *
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the{@code " Gmail web interface"}
     * @param labelListVisibility:   the visibility of the label in the label list in the{@code " Gmail web interface"}
     * @param textColor:             the text color of the label -> available at {@link AllowedColor}
     * @param backgroundColor:       the background color of the label -> available at {@link AllowedColor}
     * @return label created as {@link Label} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Label createColouredLabel(String name, MessageListVisibility messageListVisibility,
                                     LabelListVisibility labelListVisibility, AllowedColor textColor,
                                     AllowedColor backgroundColor) throws IOException {
        return createColouredLabel(name, messageListVisibility, labelListVisibility, textColor, backgroundColor,
                LIBRARY_OBJECT);
    }

    /**
     * Method to create a new label with custom colors
     *
     * @param label: label filled with detail to create a new one
     * @return label created as {@link Label} custom object
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Label createColouredLabel(Label label) throws IOException {
        Label.LabelColor color = label.getColor();
        return createColouredLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(),
                color.getTextColor(), color.getBackgroundColor(), LIBRARY_OBJECT);
    }

    /**
     * Method to create a new label with custom colors
     *
     * @param label:  label filled with detail to create a new one
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createColouredLabel(Label label, ReturnFormat format) throws IOException {
        Label.LabelColor color = label.getColor();
        return createColouredLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(),
                color.getTextColor(), color.getBackgroundColor(), format);
    }

    /**
     * Method to create a new label with custom colors
     *
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the{@code " Gmail web interface"}
     * @param labelListVisibility:   the visibility of the label in the label list in the{@code " Gmail web interface"}
     * @param textColor:             the text color of the label -> available at {@link AllowedColor}
     * @param backgroundColor:       the background color of the label -> available at {@link AllowedColor}
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @throws IOException when request has been go wrong
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/create">
     * users.labels.create</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T createColouredLabel(String name, MessageListVisibility messageListVisibility,
                                     LabelListVisibility labelListVisibility, AllowedColor textColor,
                                     AllowedColor backgroundColor, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label requestLabel = assembleLabel(name, messageListVisibility,
                labelListVisibility);
        requestLabel.setColor(new LabelColor().setTextColor(textColor.toString())
                .setBackgroundColor(backgroundColor.toString()));
        com.google.api.services.gmail.model.Label responseLabel = labels.create(userId, requestLabel).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(responseLabel).toString();
            case LIBRARY_OBJECT:
                return (T) new Label(new JSONObject(responseLabel));
            default:
                return (T) responseLabel.toString();
        }
    }

    /**
     * Method to create a label with base details
     *
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the{@code " Gmail web interface"}
     * @param labelListVisibility:   the visibility of the label in the label list in the{@code " Gmail web interface"}
     * @return label as {@link com.google.api.services.gmail.model.Label}
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels#Label">Label</a>
     **/
    private com.google.api.services.gmail.model.Label assembleLabel(String name, MessageListVisibility messageListVisibility,
                                                                    LabelListVisibility labelListVisibility) {
        com.google.api.services.gmail.model.Label requestLabel = new com.google.api.services.gmail.model.Label();
        requestLabel.setName(name);
        requestLabel.setMessageListVisibility(messageListVisibility.name());
        requestLabel.setLabelListVisibility(labelListVisibility.name());
        return requestLabel;
    }

    /**
     * Method to delete a label
     *
     * @param labelToDelete: label to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/delete">
     * users.labels.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteLabel(Label labelToDelete) {
        return deleteLabel(labelToDelete.getId());
    }

    /**
     * Method to delete a label
     *
     * @param labelId: label identifier to delete
     * @return result of the operation -> {@code "true"} is successful, {@code "false"} if not successful
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/delete">
     * users.labels.delete</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public boolean deleteLabel(String labelId) {
        try {
            labels.delete(userId, labelId).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to get a label
     *
     * @param labelId: label identifier to get
     * @return label requested as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/get">
     * users.labels.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Label getLabel(String labelId) throws IOException {
        return getLabel(labelId, LIBRARY_OBJECT);
    }

    /**
     * Method to get a label
     *
     * @param labelToGet: label to get
     * @return label requested as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/get">
     * users.labels.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Label getLabel(Label labelToGet) throws IOException {
        return getLabel(labelToGet.getId(), LIBRARY_OBJECT);
    }

    /**
     * Method to get a label
     *
     * @param labelToGet: label to get
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/get">
     * users.labels.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getLabel(Label labelToGet, ReturnFormat format) throws IOException {
        return getLabel(labelToGet.getId(), format);
    }

    /**
     * Method to get a label
     *
     * @param labelId: label identifier to get
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return label as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/get">
     * users.labels.get</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getLabel(String labelId, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label label = labels.get(userId, labelId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(label);
            case LIBRARY_OBJECT:
                return (T) new Label(new JSONObject(label));
            default:
                return (T) label.toString();
        }
    }

    /**
     * Method to get a list of labels <br>
     * Any params required
     *
     * @return labels list requested as {@link Collection} of {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list">
     * users.labels.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public Collection<Label> getLabelsList() throws IOException {
        return getLabelsList(LIBRARY_OBJECT);
    }

    /**
     * Method to get a list of labels
     *
     * @param format: return type formatter -> {@link ReturnFormat}
     * @return labels list as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list">
     * users.labels.list</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     **/
    public <T> T getLabelsList(ReturnFormat format) throws IOException {
        ListLabelsResponse list = labels.list(userId).execute();
        switch (format) {
            case JSON:
                return (T) new JSONArray(list.getLabels());
            case LIBRARY_OBJECT:
                ArrayList<Label> labels = new ArrayList<>();
                for (com.google.api.services.gmail.model.Label label : list.getLabels())
                    labels.add(new Label(new JSONObject(label)));
                return (T) labels;
            default:
                return (T) list.toString();
        }
    }

    /**
     * Method to change name of the label
     *
     * @param labelId: label identifier to change the name
     * @param name:    new name for the label
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public Label changeLabelName(String labelId, String name) throws IOException {
        return changeLabelName(labelId, name, LIBRARY_OBJECT);
    }

    /**
     * Method to change name of the label
     *
     * @param labelId: label identifier to change the name
     * @param name:    new name for the label
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public <T> T changeLabelName(String labelId, String name, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        label.setName(name);
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change message list visibility of the label
     *
     * @param labelId:               label identifier to change the message list visibility
     * @param messageListVisibility: new message list visibility for the label
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public Label changeLabelMessageListVisibility(String labelId, MessageListVisibility messageListVisibility) throws IOException {
        return changeLabelMessageListVisibility(labelId, messageListVisibility, LIBRARY_OBJECT);
    }

    /**
     * Method to change message list visibility of the label
     *
     * @param labelId:               label identifier to change the message list visibility
     * @param messageListVisibility: new message list visibility for the label
     * @param format:                return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public <T> T changeLabelMessageListVisibility(String labelId, MessageListVisibility messageListVisibility,
                                                  ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        label.setMessageListVisibility(messageListVisibility.toString());
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change label list visibility of the label
     *
     * @param labelId:             label identifier to change the label list visibility
     * @param labelListVisibility: new label list visibility for the label
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public Label changeLabelLabelListVisibility(String labelId, LabelListVisibility labelListVisibility) throws IOException {
        return changeLabelLabelListVisibility(labelId, labelListVisibility, LIBRARY_OBJECT);
    }

    /**
     * Method to change label list visibility of the label
     *
     * @param labelId:             label identifier to change the label list visibility
     * @param labelListVisibility: new label list visibility for the label
     * @param format:              return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public <T> T changeLabelLabelListVisibility(String labelId, LabelListVisibility labelListVisibility,
                                                ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        label.setLabelListVisibility(labelListVisibility.toString());
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change the text color of the label
     *
     * @param labelId:   label identifier to change the text color
     * @param textColor: new text color for the label
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public Label changeLabelTextColor(String labelId, AllowedColor textColor) throws IOException {
        return changeLabelTextColor(labelId, textColor, LIBRARY_OBJECT);
    }

    /**
     * Method to change the text color of the label
     *
     * @param labelId:   label identifier to change the text color
     * @param textColor: new text color for the label
     * @param format:    return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public <T> T changeLabelTextColor(String labelId, AllowedColor textColor, ReturnFormat format) throws IOException {
        String actualBackgroundColor = getLabel(labelId).getColor().getBackgroundColorHex();
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        label.setColor(new LabelColor().setTextColor(textColor.toString()).setBackgroundColor(actualBackgroundColor));
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change the background color of the label
     *
     * @param labelId:         label identifier to change the background color
     * @param backgroundColor: new background color for the label
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public Label changeLabelBackgroundColor(String labelId, AllowedColor backgroundColor) throws IOException {
        return changeLabelBackgroundColor(labelId, backgroundColor, LIBRARY_OBJECT);
    }

    /**
     * Method to change the background color of the label
     *
     * @param labelId:         label identifier to change the background color
     * @param backgroundColor: new background color for the label
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public <T> T changeLabelBackgroundColor(String labelId, AllowedColor backgroundColor, ReturnFormat format) throws IOException {
        String actualTextColor = getLabel(labelId).getColor().getTextColorHex();
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        label.setColor(new LabelColor().setTextColor(actualTextColor).setBackgroundColor(backgroundColor.toString()));
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change the colors of the label
     *
     * @param labelId:         label identifier to change the colors
     * @param textColor:       new text color for the label
     * @param backgroundColor: new background color for the label
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public Label changeLabelColor(String labelId, AllowedColor textColor, AllowedColor backgroundColor) throws IOException {
        return changeLabelColor(labelId, textColor, backgroundColor, LIBRARY_OBJECT);
    }

    /**
     * Method to change the colors of the label
     *
     * @param labelId:         label identifier to change the colors
     * @param textColor:       new text color for the label
     * @param backgroundColor: new background color for the label
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public <T> T changeLabelColor(String labelId, AllowedColor textColor, AllowedColor backgroundColor,
                                  ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        label.setColor(new LabelColor().setTextColor(textColor.toString()).setBackgroundColor(backgroundColor.toString()));
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change the colors of the label
     *
     * @param labelId:    label identifier to change the colors
     * @param labelColor: new colors for the label
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public Label changeLabelColor(String labelId, Label.LabelColor labelColor) throws IOException {
        return changeLabelColor(labelId, labelColor, LIBRARY_OBJECT);
    }

    /**
     * Method to change the colors of the label
     *
     * @param labelId:    label identifier to change the colors
     * @param labelColor: new colors for the label
     * @param format:     return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    public <T> T changeLabelColor(String labelId, Label.LabelColor labelColor, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        label.setColor(new LabelColor().setTextColor(labelColor.getTextColorHex())
                .setBackgroundColor(labelColor.getBackgroundColorHex()));
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change different details of the label
     *
     * @param labelId: label identifier to change different details
     * @param changes: new details for the label -> keys accepted ({@link String} for name, {@link MessageListVisibility}
     *                 for message list visibility, {@link LabelListVisibility} for label list visibility and {@link Label.LabelColor}
     *                 for text and background colors) <br>
     *                 <pre>
     *                 {@code
     *                 //To keep one of the colors unchanged you need to instantiate Label.LabelColor like this:
     *                 Label.LabelColor newTextColor = new Label.LabelColor(AllowedColor.#new_text_color, null); //Background color will be not modified
     *                 //OR
     *                 Label.LabelColor newBackgroundColor = new Label.LabelColor(null, AllowedColor.#new_background_color); //Text color will be not modified
     *                 }
     *                 </pre>
     * @return label modified as {@link Label} custom object
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    @SafeVarargs
    public final <T> Label changeLabel(String labelId, T... changes) throws IOException {
        return (Label) changeLabel(labelId, LIBRARY_OBJECT, changes);
    }

    /**
     * Method to change different details of the label
     *
     * @param labelId: label identifier to change different details
     * @param changes: new details for the label -> keys accepted ({@link String} for name, {@link MessageListVisibility}
     *                 for message list visibility, {@link LabelListVisibility} for label list visibility and {@link Label.LabelColor}
     *                 for text and background colors) <br>
     *                 <pre>
     *                 {@code
     *                 //To keep one of the colors unchanged you need to instantiate Label.LabelColor like this:
     *                 Label.LabelColor newTextColor = new Label.LabelColor(AllowedColor.#new_text_color, null); //Background color will be not modified
     *                 //OR
     *                 Label.LabelColor newBackgroundColor = new Label.LabelColor(null, AllowedColor.#new_background_color); //Text color will be not modified
     *                 }
     *                 </pre>
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     * @implNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/patch">
     * users.labels.patch</a>
     * @apiNote {@code "userId"} indicated by official documentation is {@link #userId} instantiated by this library
     * @implSpec this method substitutes the equivalent <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/update">
     * update method</a>
     **/
    @SafeVarargs
    public final <T> T changeLabel(String labelId, ReturnFormat format, T... changes) throws IOException {
        com.google.api.services.gmail.model.Label label = new com.google.api.services.gmail.model.Label();
        for (T change : changes) {
            Object changeClass = change.getClass();
            if (changeClass.equals(String.class))
                label.setName(change.toString());
            else if (changeClass.equals(MessageListVisibility.class))
                label.setMessageListVisibility(change.toString());
            else if (changeClass.equals(LabelListVisibility.class))
                label.setLabelListVisibility(change.toString());
            else if (changeClass.equals(Label.LabelColor.class)) {
                Label.LabelColor changeColor = (Label.LabelColor) change;
                LabelColor labelColor = new LabelColor();
                if (changeColor.getTextColor() != null)
                    labelColor.setTextColor(changeColor.getTextColorHex());
                if (changeColor.getBackgroundColor() != null)
                    labelColor.setBackgroundColor(changeColor.getBackgroundColorHex());
                label.setColor(labelColor);
            }
        }
        return changeLabel(labelId, label, format);
    }

    /**
     * Method to change the label
     *
     * @param labelId: label identifier to change
     * @param label:   label modified to update
     * @param format:  return type formatter -> {@link ReturnFormat}
     * @return label modified as {@code "format"} defines
     **/
    private <T> T changeLabel(String labelId, com.google.api.services.gmail.model.Label label,
                              ReturnFormat format) throws IOException {
        label = labels.patch(userId, labelId, label).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(label);
            case LIBRARY_OBJECT:
                return (T) new Label(new JSONObject(label));
            default:
                return (T) label.toString();
        }
    }

}
