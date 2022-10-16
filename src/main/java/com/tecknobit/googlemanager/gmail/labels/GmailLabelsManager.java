package com.tecknobit.googlemanager.gmail.labels;

import com.google.api.services.gmail.model.LabelColor;
import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.labels.records.Label;
import com.tecknobit.googlemanager.gmail.labels.records.Label.LabelListVisibility;
import com.tecknobit.googlemanager.gmail.labels.records.Label.MessageListVisibility;
import org.json.JSONObject;

import java.io.IOException;

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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
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
     * @throws IOException when auth request have been go wrong
     **/
    public GmailLabelsManager(String clientId, String clientSecret, String userId, String accessType, String approvalPrompt,
                              String host, int port, String applicationName) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port, applicationName);
    }

    public Label createLabel(String name, MessageListVisibility messageListVisibility,
                             LabelListVisibility labelListVisibility) throws IOException {
        return createLabel(name, messageListVisibility, labelListVisibility, LIBRARY_OBJECT);
    }

    public Label createLabel(Label label) throws IOException {
        return createLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(), LIBRARY_OBJECT);
    }

    public <T> T createLabel(Label label, ReturnFormat format) throws IOException {
        return createLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(), format);
    }

    public <T> T createLabel(String name, MessageListVisibility messageListVisibility,
                             LabelListVisibility labelListVisibility, ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label responseLabel = gmail.labels().create(userId, assembleLabel(name,
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

    // TODO: 16/10/2022 CHECK ORDER AND PARAMETERS  
    public Label createLabelColoured(String name, MessageListVisibility messageListVisibility, LabelListVisibility labelListVisibility,
                                     AllowedColor textColor, AllowedColor backgroundColor) throws IOException {
        return createLabel(name, messageListVisibility, labelListVisibility, LIBRARY_OBJECT);
    }

    public Label createLabelColoured(Label label) throws IOException {
        return createLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(), LIBRARY_OBJECT);
    }

    public <T> T createLabelColoured(Label label, ReturnFormat format) throws IOException {
        return createLabel(label.getName(), label.getMessageListVisibility(), label.getLabelListVisibility(), format);
    }

    public <T> T createLabelColoured(String name, MessageListVisibility messageListVisibility,
                                     LabelListVisibility labelListVisibility, AllowedColor textColor,
                                     AllowedColor backgroundColor, ReturnFormat format) throws IOException {
        return createLabelColoured(name, messageListVisibility, labelListVisibility, textColor.name(), backgroundColor.name(),
                format);
    }

    public <T> T createLabelColoured(String name, MessageListVisibility messageListVisibility,
                                     LabelListVisibility labelListVisibility, String textColor, String backgroundColor,
                                     ReturnFormat format) throws IOException {
        com.google.api.services.gmail.model.Label requestLabel = assembleLabel(name, messageListVisibility, labelListVisibility);
        requestLabel.setColor(new LabelColor().setTextColor(textColor).setBackgroundColor(backgroundColor));
        com.google.api.services.gmail.model.Label responseLabel = gmail.labels().create(userId, requestLabel).execute();
        switch (format) {
            case JSON:
                return (T) new JSONObject(responseLabel).toString();
            case LIBRARY_OBJECT:
                return (T) new Label(new JSONObject(responseLabel));
            default:
                return (T) responseLabel.toString();
        }
    }

    private com.google.api.services.gmail.model.Label assembleLabel(String name, MessageListVisibility messageListVisibility,
                                                                    LabelListVisibility labelListVisibility) {
        com.google.api.services.gmail.model.Label requestLabel = new com.google.api.services.gmail.model.Label();
        requestLabel.setName(name);
        requestLabel.setMessageListVisibility(messageListVisibility.name());
        requestLabel.setLabelListVisibility(labelListVisibility.name());
        return requestLabel;
    }

}
