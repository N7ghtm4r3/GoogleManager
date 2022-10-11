package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

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
        private final String data;

        public MessagePartBody(String attachmentId, int size, String data) {
            this.attachmentId = attachmentId;
            this.size = size;
            this.data = data;
        }

        public MessagePartBody(JSONObject jMessagePartBody) {
            JsonHelper hMessagePartBody = new JsonHelper(jMessagePartBody);
            attachmentId = hMessagePartBody.getString("attachmentId");
            size = hMessagePartBody.getInt("size");
            data = hMessagePartBody.getString("data");
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
