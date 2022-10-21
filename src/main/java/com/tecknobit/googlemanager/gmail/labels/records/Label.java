package com.tecknobit.googlemanager.gmail.labels.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.labels.records.Label.LabelColor.AllowedColor.valueOf;
import static com.tecknobit.googlemanager.gmail.labels.records.Label.LabelListVisibility.labelShow;
import static com.tecknobit.googlemanager.gmail.labels.records.Label.MessageListVisibility.show;

/**
 * The {@code Label} class is useful to format a Gmail's label
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels#Label">Label</a>
 **/
public class Label {

    /**
     * {@code CHAT_LABEL} is a constant for chat label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CHAT_LABEL = "CHAT";

    /**
     * {@code SENT_LABEL} is a constant for sent label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String SENT_LABEL = "SENT";

    /**
     * {@code INBOX_LABEL} is a constant for inbox label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String INBOX_LABEL = "INBOX";

    /**
     * {@code IMPORTANT_LABEL} is a constant for important label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String IMPORTANT_LABEL = "IMPORTANT";

    /**
     * {@code TRASH_LABEL} is a constant for trash label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String TRASH_LABEL = "TRASH";

    /**
     * {@code DRAFT_LABEL} is a constant for draft label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String DRAFT_LABEL = "DRAFT";

    /**
     * {@code SPAM_LABEL} is a constant for spam label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String SPAM_LABEL = "SPAM";

    /**
     * {@code CATEGORY_FORUMS_LABEL} is a constant for category forums label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_FORUMS_LABEL = "CATEGORY_FORUMS";

    /**
     * {@code CATEGORY_UPDATES_LABEL} is a constant for category updates label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_UPDATES_LABEL = "CATEGORY_UPDATES";

    /**
     * {@code CATEGORY_PERSONAL_LABEL} is a constant for category personal label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_PERSONAL_LABEL = "CATEGORY_PERSONAL";

    /**
     * {@code CATEGORY_PROMOTIONS_LABEL} is a constant for category promotions label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_PROMOTIONS_LABEL = "CATEGORY_PROMOTIONS";

    /**
     * {@code CATEGORY_SOCIAL_LABEL} is a constant for category social label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String CATEGORY_SOCIAL_LABEL = "CATEGORY_SOCIAL";

    /**
     * {@code STARRED_LABEL} is a constant for starred label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String STARRED_LABEL = "STARRED";

    /**
     * {@code UNREAD_LABEL} is a constant for unread label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String UNREAD_LABEL = "UNREAD";

    /**
     * {@code UNWANTED_LABEL} is a constant for unwanted label type
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels/list?apix=true#try-it">labels</a>
     **/
    public static final String UNWANTED_LABEL = "UNWANTED";

    /**
     * {@code id} the immutable ID of the label
     **/
    private final String id;

    /**
     * {@code name} the display name of the label
     **/
    private final String name;

    /**
     * {@code messageListVisibility} the visibility of messages with this label in the message list in the Gmail web interface
     **/
    private final MessageListVisibility messageListVisibility;

    /**
     * {@code labelListVisibility} the visibility of the label in the label list in the Gmail web interface
     **/
    private final LabelListVisibility labelListVisibility;

    /**
     * {@code type} the owner type for the label. User labels are created by the user and can be modified and deleted
     * by the user and can be applied to any message or thread. System labels are internally created and cannot be added, modified, or deleted
     **/
    private final LabelType type;

    /**
     * {@code messagesTotal} the total number of messages with the label
     **/
    private final int messagesTotal;

    /**
     * {@code messagesUnread} the number of unread messages with the label
     **/
    private final int messagesUnread;

    /**
     * {@code threadsTotal} the total number of threads with the label
     **/
    private final int threadsTotal;

    /**
     * {@code threadsUnread} the number of unread threads with the label
     **/
    private final int threadsUnread;

    /**
     * {@code color} the color to assign to the label. Color is only available for labels that have their type set to user
     **/
    private final LabelColor color;

    /**
     * Constructor to init a {@link Label}
     *
     * @param id:                    the immutable ID of the label
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the Gmail web interface
     * @param labelListVisibility:   the visibility of the label in the label list in the Gmail web interface
     * @param type:                  the owner type for the label
     * @param messagesTotal:         the total number of messages with the label
     * @param messagesUnread:        the number of unread messages with the label
     * @param threadsTotal:          the total number of threads with the label
     * @param threadsUnread:         the number of unread threads with the label
     * @param color:                 the color to assign to the label. Color is only available for labels that have their type set to user
     **/
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

    /**
     * Constructor to init a {@link Label}
     *
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the Gmail web interface
     * @param labelListVisibility:   the visibility of the label in the label list in the Gmail web interface
     **/
    public Label(String name, MessageListVisibility messageListVisibility, LabelListVisibility labelListVisibility) {
        this(null, name, messageListVisibility, labelListVisibility, null, 0, 0, 0, 0, null);
    }

    /**
     * Constructor to init a {@link Label}
     *
     * @param name:                  the display name of the label
     * @param messageListVisibility: the visibility of messages with this label in the message list in the Gmail web interface
     * @param labelListVisibility:   the visibility of the label in the label list in the Gmail web interface
     * @param color:                 the color to assign to the label. Color is only available for labels that have their type set to user
     **/
    public Label(String name, MessageListVisibility messageListVisibility, LabelListVisibility labelListVisibility,
                 LabelColor color) {
        this(null, name, messageListVisibility, labelListVisibility, null, 0, 0, 0, 0, color);
    }

    /**
     * Constructor to init a {@link Label}
     *
     * @param jLabel: {@code "label"} details as {@link JSONObject}
     **/
    public Label(JSONObject jLabel) {
        JsonHelper hLabel = new JsonHelper(jLabel);
        id = jLabel.getString("id");
        name = jLabel.getString("name");
        messageListVisibility = MessageListVisibility.valueOf(hLabel.getString("messageListVisibility", show.name()));
        labelListVisibility = LabelListVisibility.valueOf(hLabel.getString("labelListVisibility", labelShow.name()));
        type = LabelType.valueOf(hLabel.getString("type", LabelType.user.name()));
        messagesTotal = hLabel.getInt("messagesTotal", 0);
        messagesUnread = hLabel.getInt("messagesUnread", 0);
        threadsTotal = hLabel.getInt("threadsTotal", 0);
        threadsUnread = hLabel.getInt("threadsUnread", 0);
        color = new LabelColor(hLabel.getJSONObject("color"));
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
     * Method to get {@link #name} instance <br>
     * Any params required
     *
     * @return {@link #name} instance as {@link String}
     **/
    public String getName() {
        return name;
    }

    /**
     * Method to get {@link #messageListVisibility} instance <br>
     * Any params required
     *
     * @return {@link #messageListVisibility} instance as {@link MessageListVisibility}
     **/
    public MessageListVisibility getMessageListVisibility() {
        return messageListVisibility;
    }

    /**
     * Method to get {@link #labelListVisibility} instance <br>
     * Any params required
     *
     * @return {@link #labelListVisibility} instance as {@link LabelListVisibility}
     **/
    public LabelListVisibility getLabelListVisibility() {
        return labelListVisibility;
    }

    /**
     * Method to get {@link #type} instance <br>
     * Any params required
     *
     * @return {@link #type} instance as {@link LabelType}
     **/
    public LabelType getType() {
        return type;
    }

    /**
     * Method to get {@link #messagesTotal} instance <br>
     * Any params required
     *
     * @return {@link #messagesTotal} instance as int
     **/
    public int getMessagesTotal() {
        return messagesTotal;
    }

    /**
     * Method to get {@link #messagesUnread} instance <br>
     * Any params required
     *
     * @return {@link #messagesUnread} instance as int
     **/
    public int getMessagesUnread() {
        return messagesUnread;
    }

    /**
     * Method to get {@link #threadsTotal} instance <br>
     * Any params required
     *
     * @return {@link #threadsTotal} instance as int
     **/
    public int getThreadsTotal() {
        return threadsTotal;
    }

    /**
     * Method to get {@link #threadsUnread} instance <br>
     * Any params required
     *
     * @return {@link #threadsUnread} instance as int
     **/
    public int getThreadsUnread() {
        return threadsUnread;
    }

    /**
     * Method to get {@link #color} instance <br>
     * Any params required
     *
     * @return {@link #color} instance as {@link LabelColor}
     **/
    public LabelColor getColor() {
        return color;
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
     * {@code MessageListVisibility} the messages visibilities available for messages with this label in the message list in the Gmail web interface
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels#messagelistvisibility">
     * Messages visibilities</a>
     **/
    public enum MessageListVisibility {

        /**
         * {@code show} show the label in the message list
         **/
        show,

        /**
         * {@code hide} do not show the label in the message list
         **/
        hide
    }

    /**
     * {@code LabelListVisibility} the list of visibilities available for visibility of the label in the label list in the Gmail web interface
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels#labellistvisibility">
     * Label list visibilities</a>
     **/
    public enum LabelListVisibility {

        /**
         * {@code labelShow} show the label in the label list
         **/
        labelShow,

        /**
         * {@code labelShowIfUnread} show the label if there are any unread messages with that label
         **/
        labelShowIfUnread,

        /**
         * {@code labelHide} do not show the label in the label list
         **/
        labelHide
    }

    /**
     * {@code LabelType} the label types available for the owner for the label
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels#type">Label types</a>
     **/
    public enum LabelType {

        /**
         * {@code system} labels created by Gmail
         **/
        system,

        /**
         * {@code user} custom labels created by the user or application
         **/
        user
    }

    /**
     * The {@code LabelColor} class is useful to format a Gmail's label color object for {@link Label}
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels#Color">Label color</a>
     **/
    public static class LabelColor {

        /**
         * {@code textColor} the text color of the label, represented as hex string
         **/
        private final AllowedColor textColor;

        /**
         * {@code backgroundColor} the background color represented as hex string
         **/
        private final AllowedColor backgroundColor;

        /**
         * Constructor to init a {@link LabelColor}
         *
         * @param textColor:       the text color of the label, represented as hex string
         * @param backgroundColor: the background color represented as hex string
         **/
        public LabelColor(AllowedColor textColor, AllowedColor backgroundColor) {
            this.textColor = textColor;
            this.backgroundColor = backgroundColor;
        }

        /**
         * Constructor to init a {@link LabelColor}
         *
         * @param textColor:       the text color of the label, represented as hex string
         * @param backgroundColor: the background color represented as hex string
         * @throws IllegalArgumentException when colors inserted are not allowed, so not present in {@link AllowedColor} enum
         **/
        public LabelColor(String textColor, String backgroundColor) throws IllegalArgumentException {
            char firstCharText = textColor.charAt(0);
            char firstCharBackground = backgroundColor.charAt(0);
            if (firstCharText != '_' && firstCharText != '#')
                textColor = "_" + textColor;
            if (firstCharBackground != '_' && firstCharBackground != '#')
                backgroundColor = "_" + backgroundColor;
            this.textColor = valueOf(textColor.replace("#", "_"));
            this.backgroundColor = valueOf(backgroundColor.replace("#", "_"));
        }

        /**
         * Constructor to init a {@link LabelColor}
         *
         * @param jLabelColor: {@code "label colors"} details as {@link JSONObject}
         * @throws IllegalArgumentException when colors inserted are not allowed, so not present in {@link AllowedColor} enum
         **/
        public LabelColor(JSONObject jLabelColor) throws IllegalArgumentException {
            if (jLabelColor == null) {
                textColor = AllowedColor._000000;
                backgroundColor = AllowedColor._000000;
            } else {
                textColor = valueOf(jLabelColor.getString("textColor").replace("#", "_"));
                backgroundColor = valueOf(jLabelColor.getString("backgroundColor").replace("#", "_"));
            }
        }

        /**
         * Method to get {@link #textColor} instance <br>
         * Any params required
         *
         * @return {@link #textColor} instance as {@link AllowedColor}
         **/
        public AllowedColor getTextColor() {
            return textColor;
        }

        /**
         * Method to get {@link #textColor} instance <br>
         * Any params required
         *
         * @return {@link #textColor} instance as {@link String}
         **/
        public String getTextColorHex() {
            return textColor.color;
        }

        /**
         * Method to get {@link #backgroundColor} instance <br>
         * Any params required
         *
         * @return {@link #backgroundColor} instance as {@link AllowedColor}
         **/
        public AllowedColor getBackgroundColor() {
            return backgroundColor;
        }

        /**
         * Method to get {@link #backgroundColor} instance <br>
         * Any params required
         *
         * @return {@link #backgroundColor} instance as {@link String}
         **/
        public String getBackgroundColorHex() {
            return backgroundColor.color;
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
         * {@code AllowedColor} the list of colors available
         *
         * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.labels#color">Allowed colors</a>
         **/
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

            /**
             * {@code color} the color represented as hex string
             **/
            private final String color;

            /**
             * Constructor to init a {@link LabelColor}
             *
             * @param color: the color represented as hex string
             **/
            AllowedColor(final String color) {
                this.color = color;
            }

            /**
             * Method to get {@link #color} instance <br>
             * Any params required
             *
             * @return {@link #color} instance as hex {@link String}
             **/
            @Override
            public String toString() {
                return color;
            }

        }

    }

}
