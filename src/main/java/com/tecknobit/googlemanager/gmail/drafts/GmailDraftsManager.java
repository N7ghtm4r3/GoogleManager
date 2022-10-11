package com.tecknobit.googlemanager.gmail.drafts;

import com.tecknobit.googlemanager.gmail.GmailManager;
import com.tecknobit.googlemanager.gmail.drafts.records.Draft;
import org.json.JSONObject;

import java.io.IOException;

import static com.tecknobit.googlemanager.GoogleManager.ReturnFormat.LIBRARY_OBJECT;

/**
 * The {@code GmailDraftsManager} class is useful to manage all Gmail's drafts endpoints
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.drafts">Gmail drafts</a>
 **/
public class GmailDraftsManager extends GmailManager {

    /**
     * Constructor to init a {@link GmailDraftsManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param host:           host used in the auth operations
     * @param callBackPath:   callback path used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port, String host, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, host, callBackPath);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param callBackPath:   callback path used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, int port, String callBackPath) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, port, callBackPath);
    }

    /**
     * Constructor to init a {@link GmailDraftsManager}
     *
     * @param clientId:       client identifier value
     * @param clientSecret:   client secret value
     * @param userId:         used to identifier a user -> me to use an authenticated user
     * @param accessType:     access type used in the auth operations
     * @param approvalPrompt: approval prompt type used in the auth operations
     * @param port:           port used in the auth operations
     * @param host:           host used in the auth operations
     * @throws IOException when auth request have been go wrong
     **/
    public GmailDraftsManager(String clientId, String clientSecret, String userId, String accessType,
                              String approvalPrompt, String host, int port) throws IOException {
        super(clientId, clientSecret, userId, accessType, approvalPrompt, host, port);
    }

    public Draft createDraftUpload(Draft draft) throws IOException {
        return createDraftUpload(draft, LIBRARY_OBJECT);
    }

    public <T> T createDraftUpload(Draft draft, ReturnFormat format) throws IOException {
        return createDraft(sendUploadRequest("", draft.toString()), format);
    }

    public Draft createMetadataDraft(Draft draft) throws IOException {
        return createDraftUpload(draft, LIBRARY_OBJECT);
    }

    public <T> T createMetadataDraft(Draft draft, ReturnFormat format) throws IOException {
        return createDraft(sendPostRequest("", draft.toString()), format);
    }


    // TODO: 11/10/2022 USE TO TEST {
    //  "id": "aga",
    //  "message": {
    //    "raw": "gaga",
    //    "historyId": 0,
    //    "id": "",
    //    "labelIds": [
    //      ""
    //    ],
    //    "payload": {
    //      "body": {
    //        "attachmentId": "",
    //        "data": "",
    //        "size": 0
    //      },
    //      "filename": "",
    //      "headers": [
    //        {
    //          "name": "",
    //          "value": ""
    //        }
    //      ],
    //      "mimeType": "",
    //      "partId": "",
    //      "parts": [
    //        {
    //          "body": {
    //            "attachmentId": "",
    //            "data": "",
    //            "size": 0
    //          },
    //          "filename": "",
    //          "headers": [
    //            {
    //              "name": "",
    //              "value": ""
    //            },
    //            {
    //              "name": "",
    //              "value": ""
    //            },
    //            {
    //              "name": "",
    //              "value": ""
    //            }
    //          ],
    //          "mimeType": "",
    //          "partId": ""
    //        }
    //      ]
    //    },
    //    "internalDate": 0,
    //    "sizeEstimate": 0,
    //    "snippet": "",
    //    "threadId": ""
    //  }
    //}

    private <T> T createDraft(String response, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(response);
            case LIBRARY_OBJECT:
                return (T) new Draft(new JSONObject(response));
            default:
                return (T) response;
        }
    }

    /**
     * Method to send a GET request
     *
     * @param endpoint: endpoint to make the request
     * @return response of the request in JSON format as {@link String}
     * @throws IOException when request have been go wrong
     **/
    @Override
    public String sendGetRequest(String endpoint) throws IOException {
        return super.sendGetRequest("drafts/" + endpoint);
    }

    /**
     * Method to send a POST request
     *
     * @param endpoint:   endpoint to make the request
     * @param bodyParams: body payload for the POST request
     * @return response of the request in JSON format as {@link String}
     * @throws IOException when request have been go wrong
     **/
    @Override
    public String sendPostRequest(String endpoint, String bodyParams) throws IOException {
        return super.sendPostRequest("drafts/" + endpoint, bodyParams);
    }

    /**
     * Method to send a media upload POST request
     *
     * @param endpoint:   endpoint to make the request
     * @param bodyParams: body payload for the POST request
     * @return response of the request in JSON format as {@link String}
     * @throws IOException when request have been go wrong
     **/
    @Override
    public String sendUploadRequest(String endpoint, String bodyParams) throws IOException {
        return super.sendUploadRequest("drafts/" + endpoint, bodyParams);
    }

}
