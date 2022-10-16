package com.tecknobit.googlemanager.gmail.labels.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

public class Label {

    private final String id;
    private final String name;
    private final MessageListVisibility messageListVisibility;
    private final LabelListVisibility labelListVisibility;
    private final LabelType type;
    private final int messagesTotal;
    private final int messagesUnread;
    private final int threadsTotal;
    private final int threadsUnread;
    private final LabelColor color;

    public Label(String id, String name, MessageListVisibility messageListVisibility, LabelListVisibility labelListVisibility,
                 LabelType type, int messagesTotal, int messagesUnread, int threadsTotal, int threadsUnread, LabelColor color) {
        this.id = id;
        this.name = name;
        this.messageListVisibility = messageListVisibility;
        this.labelListVisibility = labelListVisibility;
        this.type = type;
        this.messagesTotal = messagesTotal;
        this.messagesUnread = messagesUnread;
        this.threadsTotal = threadsTotal;
        this.threadsUnread = threadsUnread;
        this.color = color;
    }

    public Label(JSONObject jLabel) {
        JsonHelper hLabel = new JsonHelper(jLabel);
        id = jLabel.getString("id");
        name = jLabel.getString("name");
        messageListVisibility = MessageListVisibility.valueOf(jLabel.getString("messageListVisibility"));
        labelListVisibility = LabelListVisibility.valueOf(jLabel.getString("labelListVisibility"));
        type = LabelType.valueOf(hLabel.getString("type", LabelType.user.name()));
        messagesTotal = hLabel.getInt("messagesTotal", 0);
        messagesUnread = hLabel.getInt("messagesUnread", 0);
        threadsTotal = hLabel.getInt("threadsTotal", 0);
        threadsUnread = hLabel.getInt("threadsUnread", 0);
        color = new LabelColor(hLabel.getJSONObject("color"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MessageListVisibility getMessageListVisibility() {
        return messageListVisibility;
    }

    public LabelListVisibility getLabelListVisibility() {
        return labelListVisibility;
    }

    public LabelType getType() {
        return type;
    }

    public int getMessagesTotal() {
        return messagesTotal;
    }

    public int getMessagesUnread() {
        return messagesUnread;
    }

    public int getThreadsTotal() {
        return threadsTotal;
    }

    public int getThreadsUnread() {
        return threadsUnread;
    }

    public LabelColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    public enum MessageListVisibility {
        show,
        hide
    }

    public enum LabelListVisibility {
        labelShow,
        labelShowIfUnread,
        labelHide
    }

    public enum LabelType {
        system,
        user
    }

    public static class LabelColor {

        private final AllowedColor textColor;
        private final AllowedColor backgroundColor;

        public LabelColor(AllowedColor textColor, AllowedColor backgroundColor) {
            this.textColor = textColor;
            this.backgroundColor = backgroundColor;
        }

        public LabelColor(String textColor, String backgroundColor) throws IllegalArgumentException {
            char firstCharText = textColor.charAt(0);
            char firstCharBackground = backgroundColor.charAt(0);
            if (firstCharText != '_' && firstCharText != '#')
                textColor = "_" + textColor;
            if (firstCharBackground != '_' && firstCharBackground != '#')
                backgroundColor = "_" + backgroundColor;
            this.textColor = AllowedColor.valueOf(textColor.replace("#", "_"));
            this.backgroundColor = AllowedColor.valueOf(backgroundColor.replace("#", "_"));
        }

        public LabelColor(JSONObject jLabelColor) throws IllegalArgumentException {
            if (jLabelColor == null) {
                textColor = AllowedColor._000000;
                backgroundColor = AllowedColor._000000;
            } else {
                textColor = AllowedColor.valueOf(jLabelColor.getString("textColor"));
                backgroundColor = AllowedColor.valueOf(jLabelColor.getString("backgroundColor"));
            }
        }

        public AllowedColor getTextColor() {
            return textColor;
        }

        public String getTextColorHex() {
            return textColor.color;
        }

        public AllowedColor getBackgroundColor() {
            return backgroundColor;
        }

        public String getBackgroundColorHex() {
            return backgroundColor.color;
        }

        @Override
        public String toString() {
            return new JSONObject(this).toString();
        }

        public enum AllowedColor {
            _000000("#000000"), _434343("#434343"), _666666("#666666"), _999999("#999999"),
            _cccccc("#cccccc"), _efefef("#efefef"), _f3f3f3("#f3f3f3"), _ffffff("#ffffff"),
            _fb4c2f("#fb4c2f"), _ffad47("#ffad47"), _fad165("#fad165"), _16a766("#16a766"),
            _43d692("#43d692"), _4a86e8("#4a86e8"), _a479e2("#a479e2"), _f691b3("#f691b3"),
            _f6c5be("#f6c5be"), _ffe6c7("#ffe6c7"), _fef1d1("#fef1d1"), _b9e4d0("#b9e4d0"),
            _c6f3de("#c6f3de"), _c9daf8("#c9daf8"), _e4d7f5("#e4d7f5"), _fcdee8("#fcdee8"),
            _efa093("#efa093"), _ffd6a2("#ffd6a2"), _fce8b3("#fce8b3"), _89d3b2("#89d3b2"),
            _a0eac9("#a0eac9"), _a4c2f4("#a4c2f4"), _d0bcf1("#d0bcf1"), _fbc8d9("#fbc8d9"),
            _e66550("#e66550"), _ffbc6b("#ffbc6b"), _fcda83("#fcda83"), _44b984("#44b984"),
            _6d9eeb("#6d9eeb"), _68dfa9("#68dfa9"), _b694e8("#b694e8"), _f7a7c0("#f7a7c0"),
            _cc3a21("#cc3a21"), _eaa041("#eaa041"), _f2c960("#f2c960"), _149e60("#149e60"),
            _3dc789("#3dc789"), _3c78d8("#3c78d8"), _8e63ce("#8e63ce"), _e07798("#e07798"),
            _ac2b16("#ac2b16"), _cf8933("#cf8933"), _d5ae49("#d5ae49"), _0b804b("#0b804b"),
            _2a9c68("#2a9c68"), _285bac("#285bac"), _653e9b("#653e9b"), _b65775("#b65775"),
            _822111("#822111"), _a46a21("#a46a21"), _aa8831("#aa8831"), _076239("#076239"),
            _1a764d("#1a764d"), _1c4587("#1c4587"), _41236d("#41236d"), _83334c("#83334c"),
            _464646("#464646"), _e7e7e7("#e7e7e7"), _0d3472("#0d3472"), _b6cff5("#b6cff5"),
            _0d3b44("#0d3b44"), _98d7e4("#98d7e4"), _3d188e("#3d188e"), _e3d7ff("#e3d7ff"),
            _711a36("#711a36"), _fbd3e0("#fbd3e0"), _8a1c0a("#8a1c0a"), _f2b2a8("#f2b2a8"),
            _7a2e0b("#7a2e0b"), _ffc8af("#ffc8af"), _7a4706("#7a4706"), _ffdeb5("#ffdeb5"),
            _594c05("#594c05"), _fbe983("#fbe983"), _684e07("#684e07"), _fdedc1("#fdedc1"),
            _0b4f30("#0b4f30"), _b3efd3("#b3efd3"), _04502e("#04502e"), _a2dcc1("#a2dcc1"),
            _c2c2c2("#c2c2c2"), _4986e7("#4986e7"), _2da2bb("#2da2bb"), _b99aff("#b99aff"),
            _994a64("#994a64"), _f691b2("#f691b2"), _ff7537("#ff7537"), _ffad46("#ffad46"),
            _662e37("#662e37"), _ebdbde("#ebdbde"), _cca6ac("#cca6ac"), _094228("#094228"),
            _42d692("#42d692"), _16a765("#16a765");

            private final String color;

            AllowedColor(final String color) {
                this.color = color;
            }

            @Override
            public String toString() {
                return color;
            }

        }

    }

}
