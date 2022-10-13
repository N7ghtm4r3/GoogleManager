package com.tecknobit.googlemanager.gmail.drafts.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import static com.google.common.io.BaseEncoding.base64Url;

public class Message {

    private final String threadId;
    private final ArrayList<String> labelIds;
    private final String snippet;
    private final BigInteger historyId;
    private final long internalDate;
    private final String partId;
    private final String mineType;
    private final String fileName;
    private final ArrayList<Header> headers;
    private final MessageBody messageBody;
    private final ArrayList<Message> parts;
    private final int sizeEstimate;
    private String raw;

    public Message(String partId, String mineType, String fileName, ArrayList<Header> headers, MessageBody messageBody,
                   ArrayList<Message> parts, String threadId, ArrayList<String> labelIds, String snippet,
                   BigInteger historyId, long internalDate, int sizeEstimate, String raw) {
        this.partId = partId;
        this.mineType = mineType;
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

    public Message(JSONObject jMessagePart) {
        JsonHelper hMessagePart = new JsonHelper(jMessagePart);
        partId = hMessagePart.getString("partId", null);
        mineType = hMessagePart.getString("mimeType", null);
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

    public String getThreadId() {
        return threadId;
    }

    public Collection<String> getLabelIds() {
        return labelIds;
    }

    public String getSnippet() {
        return snippet;
    }

    public BigInteger getHistoryId() {
        return historyId;
    }

    public long getInternalDate() {
        return internalDate;
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

    public MessageBody getMessageBody() {
        return messageBody;
    }

    public Collection<Message> getParts() {
        return parts;
    }

    public int getSizeEstimate() {
        return sizeEstimate;
    }

    public String getRaw() {
        return raw;
    }

    public void encodeRaw() {
        try {
            Base64.getUrlDecoder().decode(raw);
        } catch (IllegalArgumentException e) {
            raw = base64Url().omitPadding().encode(raw.getBytes());
        }
    }

    public void decodeRaw() {
        try {
            raw = new String(Base64.getUrlDecoder().decode(raw));
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
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

    public static class MessageBody {

        private final String attachmentId;
        private final int size;
        private String data;
        private boolean dataEncoded;

        public MessageBody(String attachmentId, int size, String data) {
            this.attachmentId = attachmentId;
            this.size = size;
            this.data = data;
            dataEncoded = false;
        }

        public MessageBody(JSONObject jMessagePartBody) {
            JsonHelper hMessagePartBody = new JsonHelper(jMessagePartBody);
            attachmentId = hMessagePartBody.getString("attachmentId", null);
            size = hMessagePartBody.getInt("size", 0);
            data = hMessagePartBody.getString("data", null);
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
            return "MessageBody{" +
                    "attachmentId='" + attachmentId + '\'' +
                    ", size=" + size +
                    ", data='" + data + '\'' +
                    '}';
        }

    }

}
