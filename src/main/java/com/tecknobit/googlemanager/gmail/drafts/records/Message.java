package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import static com.google.common.io.BaseEncoding.base64Url;

/**
 * The {@code Message} class is useful to format a Gmail's message
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages#Message">Message</a>
 **/
public class Message {

    /**
     * {@code id} the immutable ID of the message
     **/
    private final String id;

    /**
     * {@code partId} the immutable ID of the message part
     **/
    private final String partId;

    /**
     * {@code mimeType} the MIME type of the message part
     **/
    private final String mimeType;

    /**
     * {@code fileName} the filename of the attachment. Only present if this message part represents an attachment
     **/
    private final String fileName;

    /**
     * {@code headers} list of headers on this message part
     **/
    private final ArrayList<Header> headers;

    /**
     * {@code messageBody} the message part body for this part, which may be empty for container MIME message parts
     **/
    private final MessageBody messageBody;

    /**
     * {@code parts} the child MIME message parts of this part
     **/
    private final ArrayList<Message> parts;

    /**
     * {@code threadId} the ID of the thread the message belongs to. To add a message or draft to a thread, the following criteria must be met
     **/
    private final String threadId;

    /**
     * {@code labelIds} list of IDs of labels applied to this message
     **/
    private final ArrayList<String> labelIds;

    /**
     * {@code snippet} a short part of the message text
     **/
    private final String snippet;

    /**
     * {@code historyId} the ID of the last history record that modified this message
     **/
    private final BigInteger historyId;

    /**
     * {@code internalDate} the internal message creation timestamp (epoch ms), which determines ordering in the inbox
     **/
    private final long internalDate;

    /**
     * {@code sizeEstimate} estimated size in bytes of the message
     **/
    private final int sizeEstimate;

    /**
     * {@code raw} the entire email message in an RFC 2822 formatted and base64url encoded string
     **/
    private String raw;

    /**
     * Constructor to init a {@link Message}
     *
     * @param id:           the immutable ID of the message
     * @param partId:       the immutable ID of the message part
     * @param mimeType:     the MIME type of the message part
     * @param fileName:     the filename of the attachment. Only present if this message part represents an attachment
     * @param headers:      list of headers on this message part
     * @param messageBody:  the message part body for this part, which may be empty for container MIME message parts
     * @param parts:        the child MIME message parts of this part
     * @param threadId:     the ID of the thread the message belongs to. To add a message or draft to a thread, the following criteria must be met
     * @param labelIds:     list of IDs of labels applied to this message
     * @param snippet:      a short part of the message text
     * @param historyId:    the ID of the last history record that modified this message
     * @param internalDate: estimated total number of results
     * @param sizeEstimate: estimated size in bytes of the message
     * @param raw:          the entire email message in an RFC 2822 formatted and base64url encoded string
     **/
    public Message(String id, String partId, String mimeType, String fileName, ArrayList<Header> headers,
                   MessageBody messageBody, ArrayList<Message> parts, String threadId, ArrayList<String> labelIds,
                   String snippet, BigInteger historyId, long internalDate, int sizeEstimate, String raw) {
        this.id = id;
        this.partId = partId;
        this.mimeType = mimeType;
        this.fileName = fileName;
        this.headers = headers;
        this.messageBody = messageBody;
        this.parts = parts;
        this.threadId = threadId;
        this.labelIds = labelIds;
        this.snippet = snippet;
        this.historyId = historyId;
        this.internalDate = internalDate;
        this.sizeEstimate = sizeEstimate;
        this.raw = raw;
    }

    /**
     * Constructor to init a {@link Message}
     *
     * @param jMessagePart: {@code "message"} details as {@link JSONObject}
     **/
    public Message(JSONObject jMessagePart) {
        JsonHelper hMessagePart = new JsonHelper(jMessagePart);
        id = hMessagePart.getString("id", null);
        partId = hMessagePart.getString("partId", null);
        mimeType = hMessagePart.getString("mimeType", null);
        fileName = hMessagePart.getString("filename", null);
        JSONArray jHeaders = hMessagePart.getJSONArray("headers", new JSONArray());
        headers = new ArrayList<>();
        for (int j = 0; j < jHeaders.length(); j++)
            headers.add(new Header(jHeaders.getJSONObject(j)));
        messageBody = new MessageBody(hMessagePart.getJSONObject("body", new JSONObject()));
        JSONArray jParts = hMessagePart.getJSONArray("parts", new JSONArray());
        parts = new ArrayList<>();
        for (int j = 0; j < jParts.length(); j++)
            parts.add(new Message(jParts.getJSONObject(j)));
        threadId = hMessagePart.getString("threadId", null);
        JSONArray jLabelIds = hMessagePart.getJSONArray("labelIds", new JSONArray());
        labelIds = new ArrayList<>();
        for (int j = 0; j < jLabelIds.length(); j++)
            labelIds.add(jLabelIds.getString(j));
        snippet = hMessagePart.getString("snippet", null);
        historyId = hMessagePart.getBigInteger("historyId", BigInteger.valueOf(0));
        internalDate = hMessagePart.getLong("internalDate", 0);
        sizeEstimate = hMessagePart.getInt("sizeEstimate", 0);
        raw = hMessagePart.getString("raw", null);
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
     * Method to get {@link #threadId} instance <br>
     * Any params required
     *
     * @return {@link #threadId} instance as {@link String}
     **/
    public String getThreadId() {
        return threadId;
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
     * Method to get {@link #snippet} instance <br>
     * Any params required
     *
     * @return {@link #snippet} instance as {@link String}
     **/
    public String getSnippet() {
        return snippet;
    }

    /**
     * Method to get {@link #historyId} instance <br>
     * Any params required
     *
     * @return {@link #historyId} instance as {@link BigInteger}
     **/
    public BigInteger getHistoryId() {
        return historyId;
    }

    /**
     * Method to get {@link #internalDate} instance <br>
     * Any params required
     *
     * @return {@link #internalDate} instance as long
     **/
    public long getInternalDate() {
        return internalDate;
    }

    /**
     * Method to get {@link #partId} instance <br>
     * Any params required
     *
     * @return {@link #partId} instance as {@link String}
     **/
    public String getPartId() {
        return partId;
    }

    /**
     * Method to get {@link #mimeType} instance <br>
     * Any params required
     *
     * @return {@link #mimeType} instance as {@link String}
     **/
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Method to get {@link #fileName} instance <br>
     * Any params required
     *
     * @return {@link #fileName} instance as {@link String}
     **/
    public String getFileName() {
        return fileName;
    }

    /**
     * Method to get {@link #headers} instance <br>
     * Any params required
     *
     * @return {@link #headers} instance as {@link Collection} of {@link Header}
     **/
    public Collection<Header> getHeaders() {
        return headers;
    }

    /**
     * Method to get {@link #messageBody} instance <br>
     * Any params required
     *
     * @return {@link #messageBody} instance as {@link MessageBody}
     **/
    public MessageBody getMessageBody() {
        return messageBody;
    }

    /**
     * Method to get {@link #parts} instance <br>
     * Any params required
     *
     * @return {@link #parts} instance as {@link Collection} of {@link Message}
     **/
    public Collection<Message> getParts() {
        return parts;
    }

    /**
     * Method to get {@link #sizeEstimate} instance <br>
     * Any params required
     *
     * @return {@link #sizeEstimate} instance as int
     **/
    public int getSizeEstimate() {
        return sizeEstimate;
    }

    /**
     * Method to get {@link #raw} instance <br>
     * Any params required
     *
     * @return {@link #raw} instance as {@link String}
     **/
    public String getRaw() {
        return raw;
    }

    /**
     * Method to encode {@link #raw} in {@link Base64#getUrlDecoder()} format
     * Any params required
     *
     * @apiNote {@link #raw} will be encoded only if it is not encoded yet
     **/
    public void encodeRaw() {
        try {
            Base64.getUrlDecoder().decode(raw);
        } catch (IllegalArgumentException e) {
            raw = base64Url().omitPadding().encode(raw.getBytes());
        }
    }

    /**
     * Method to decode {@link #raw} in {@link Base64#getUrlDecoder()} format
     * Any params required
     *
     * @apiNote {@link #raw} will be decoded only if it is already encoded
     **/
    public void decodeRaw() {
        try {
            raw = new String(Base64.getUrlDecoder().decode(raw));
        } catch (IllegalArgumentException ignored) {
        }
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

    /**
     * The {@code Header} class is useful to format a Gmail's header
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages#Message.Header">Header</a>
     **/
    public static class Header {

        /**
         * {@code To} is a constant for {@code "To"} header key type
         **/
        public static final String To = "To";

        /**
         * {@code From} is a constant for {@code "From"} header key type
         **/
        public static final String From = "From";

        /**
         * {@code Cc} is a constant for {@code "Cc"} header key type
         **/
        public static final String Cc = "Cc";

        /**
         * {@code Bcc} is a constant for {@code "Bcc"} header key type
         **/
        public static final String Bcc = "Bcc";

        /**
         * {@code IN_REPLY_TO} is a constant for {@code "IN_REPLY_TO"} header key type
         **/
        public static final String IN_REPLY_TO = "In-Reply-To";

        /**
         * {@code SUBJECT} is a constant for {@code "Subject"} header key type
         **/
        public static final String SUBJECT = "Subject";

        /**
         * {@code REFERENCES} is a constant for {@code "References"} header key type
         **/
        public static final String REFERENCES = "References";

        /**
         * {@code name} name of the header
         **/
        private final String name;

        /**
         * {@code value} value of the header
         **/
        private final String value;

        /**
         * Constructor to init a {@link Header}
         *
         * @param name:  name of the header
         * @param value: value of the header
         **/
        public Header(String name, String value) {
            this.name = name;
            this.value = value;
        }

        /**
         * Constructor to init a {@link Header}
         *
         * @param jHeader: {@code "header"} details as {@link JSONObject}
         **/
        public Header(JSONObject jHeader) {
            JsonHelper hHeader = new JsonHelper(jHeader);
            name = hHeader.getString("name");
            value = hHeader.getString("value");
        }

        /**
         * Method to get {@link #name} instance <br>
         * Any params required
         *
         * @return {@link #name} instance as {@link String}
         **/
        public String getName() {
            return name;
        }

        /**
         * Method to get {@link #value} instance <br>
         * Any params required
         *
         * @return {@link #value} instance as {@link String}
         **/
        public String getValue() {
            return value;
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

    /**
     * The {@code MessageBody} class is useful to format a Gmail's message body
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.messages.attachments#MessagePartBody">
     * MessageBody</a>
     **/
    public static class MessageBody {

        /**
         * {@code attachmentId} contains the ID of an external attachment that can be retrieved in a separate
         **/
        private final String attachmentId;

        /**
         * {@code size} number of bytes for the message part data (encoding notwithstanding)
         **/
        private final int size;

        /**
         * {@code data} the body data of a MIME message part as a base64url encoded string
         **/
        private String data;

        /**
         * Constructor to init a {@link MessageBody}
         *
         * @param attachmentId: contains the ID of an external attachment that can be retrieved in a separate
         * @param size: number of bytes for the message part data (encoding notwithstanding)
         * @param data: the body data of a MIME message part as a base64url encoded string
         **/
        public MessageBody(String attachmentId, int size, String data) {
            this.attachmentId = attachmentId;
            this.size = size;
            this.data = data;
        }

        /**
         * Constructor to init a {@link MessageBody}
         *
         * @param jMessagePartBody: {@code "message body"} details as {@link JSONObject}
         **/
        public MessageBody(JSONObject jMessagePartBody) {
            JsonHelper hMessagePartBody = new JsonHelper(jMessagePartBody);
            attachmentId = hMessagePartBody.getString("attachmentId", null);
            size = hMessagePartBody.getInt("size", 0);
            data = hMessagePartBody.getString("data", null);
        }

        /**
         * Method to get {@link #attachmentId} instance <br>
         * Any params required
         *
         * @return {@link #attachmentId} instance as {@link String}
         **/
        public String getAttachmentId() {
            return attachmentId;
        }

        /**
         * Method to get {@link #size} instance <br>
         * Any params required
         *
         * @return {@link #size} instance as int
         **/
        public int getSize() {
            return size;
        }

        /**
         * Method to get {@link #data} instance <br>
         * Any params required
         *
         * @return {@link #data} instance as {@link String}
         **/
        public String getData() {
            return data;
        }

        /**
         * Method to encode {@link #raw} in {@link Base64#getUrlDecoder()} format
         * Any params required
         * @apiNote {@link #raw} will be encoded only if it is not encoded yet
         **/
        public void encodeData() {
            try {
                Base64.getUrlDecoder().decode(data);
            } catch (IllegalArgumentException e) {
                data = base64Url().omitPadding().encode(data.getBytes());
            }
        }

        /**
         * Method to decode {@link #raw} in {@link Base64#getUrlDecoder()} format
         * Any params required
         * @apiNote {@link #raw} will be decoded only if it is already encoded
         **/
        public void decodeData() {
            try {
                data = new String(Base64.getUrlDecoder().decode(data));
            } catch (IllegalArgumentException ignored) {
            }
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

}
