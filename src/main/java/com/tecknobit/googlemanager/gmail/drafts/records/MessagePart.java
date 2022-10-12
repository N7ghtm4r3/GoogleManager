package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.google.common.io.BaseEncoding.base64Url;

public class MessagePart {

    private final String partId;
    private final String mineType;
    private final String fileName;
    private final ArrayList<Header> headers;
    private final MessagePartBody messagePartBody;
    private final ArrayList<MessagePart> parts;

    public MessagePart(String partId, String mineType, String fileName, ArrayList<Header> headers,
                       MessagePartBody messagePartBody, ArrayList<MessagePart> parts) {
        this.partId = partId;
        this.mineType = mineType;
        this.fileName = fileName;
        this.headers = headers;
        this.messagePartBody = messagePartBody;
        this.parts = parts;
    }

    public MessagePart(JSONObject jMessagePart) {
        JsonHelper hMessagePart = new JsonHelper(jMessagePart);
        partId = hMessagePart.getString("partId");
        mineType = hMessagePart.getString("mimeType");
        fileName = hMessagePart.getString("filename");
        JSONArray jHeaders = hMessagePart.getJSONArray("headers", new JSONArray());
        headers = new ArrayList<>();
        for (int j = 0; j < jHeaders.length(); j++)
            headers.add(new Header(jHeaders.getJSONObject(j)));
        messagePartBody = new MessagePartBody(hMessagePart.getJSONObject("body", new JSONObject()));
        JSONArray jParts = hMessagePart.getJSONArray("parts", new JSONArray());
        parts = new ArrayList<>();
        for (int j = 0; j < jParts.length(); j++)
            parts.add(new MessagePart(jParts.getJSONObject(j)));
    }

    public String getPartId() {
        return partId;
    }

    public String getMineType() {
        return mineType;
    }

    public String getFileName() {
        return fileName;
    }

    public Collection<Header> getHeaders() {
        return headers;
    }

    public MessagePartBody getMessagePartBody() {
        return messagePartBody;
    }

    public Collection<MessagePart> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return "MessagePart{" +
                "partId='" + partId + '\'' +
                ", mineType='" + mineType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", headers=" + headers +
                ", messagePartBody=" + messagePartBody +
                ", parts=" + parts +
                '}';
    }

    public static class Header {

        public static final String To = "To";
        public static final String From = "From";
        public static final String Cc = "Cc";
        public static final String Bcc = "Bcc";
        public static final String IN_REPLY_TO = "In-Reply-To";
        public static final String SUBJECT = "Subject";
        public static final String REFERENCES = "References";

        private final String name;
        private final String value;

        public Header(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public Header(JSONObject jHeader) {
            JsonHelper hHeader = new JsonHelper(jHeader);
            name = hHeader.getString("name");
            value = hHeader.getString("value");
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

    }

    public static class MessagePartBody {

        private final String attachmentId;
        private final int size;
        private String data;
        private boolean dataEncoded;

        public MessagePartBody(String attachmentId, int size, String data) {
            this.attachmentId = attachmentId;
            this.size = size;
            this.data = data;
            dataEncoded = false;
        }

        public MessagePartBody(JSONObject jMessagePartBody) {
            JsonHelper hMessagePartBody = new JsonHelper(jMessagePartBody);
            attachmentId = hMessagePartBody.getString("attachmentId");
            size = hMessagePartBody.getInt("size");
            data = hMessagePartBody.getString("data");
            dataEncoded = false;
        }

        public String getAttachmentId() {
            return attachmentId;
        }

        public int getSize() {
            return size;
        }

        public String getData() {
            return data;
        }

        public void encodeData() {
            if (!dataEncoded) {
                data = base64Url().omitPadding().encode(data.getBytes());
                dataEncoded = true;
            }
        }

        public void decodeData() {
            if (dataEncoded) {
                data = new String(base64Url().omitPadding().decode(data));
                dataEncoded = false;
            }
        }

        @Override
        public String toString() {
            return "MessagePartBody{" +
                    "attachmentId='" + attachmentId + '\'' +
                    ", size=" + size +
                    ", data='" + data + '\'' +
                    '}';
        }

    }

}
