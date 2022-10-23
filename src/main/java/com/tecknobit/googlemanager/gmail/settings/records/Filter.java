package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.tecknobit.googlemanager.gmail.settings.records.Filter.Criteria.SizeComparison.unspecified;

/**
 * The {@code Filter} class is useful to format a Gmail's filter
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters#resource:-filter">Filter</a>
 **/
public class Filter {

    /**
     * {@code criteria} matching criteria for the filter
     **/
    private final Criteria criteria;
    /**
     * {@code action} action that the filter performs
     **/
    private final Action action;
    /**
     * {@code id} the server assigned ID of the filter
     **/
    private String id;

    /**
     * Constructor to init a {@link Filter}
     *
     * @param criteria: matching criteria for the filter
     * @param action:   action that the filter performs
     * @apiNote this constructor is useful in those cases if you want to create a new {@link Filter}
     **/
    public Filter(Criteria criteria, Action action) {
        this.criteria = criteria;
        this.action = action;
    }

    /**
     * Constructor to init a {@link Filter}
     *
     * @param id:       the server assigned ID of the filter
     * @param criteria: matching criteria for the filter
     * @param action:   action that the filter performs
     **/
    public Filter(String id, Criteria criteria, Action action) {
        this(criteria, action);
        this.id = id;
    }

    /**
     * Constructor to init a {@link Filter}
     *
     * @param jFilter: {@code "filter"} details as {@link JSONObject}
     **/
    public Filter(JSONObject jFilter) {
        JsonHelper hFilter = new JsonHelper(jFilter);
        id = hFilter.getString("id");
        criteria = new Criteria(hFilter.getJSONObject("criteria", new JSONObject()));
        action = new Action(hFilter.getJSONObject("action", new JSONObject()));
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
     * Method to get {@link #criteria} instance <br>
     * Any params required
     *
     * @return {@link #criteria} instance as {@link Criteria}
     **/
    public Criteria getCriteria() {
        return criteria;
    }

    /**
     * Method to get {@link #action} instance <br>
     * Any params required
     *
     * @return {@link #action} instance as {@link Action}
     **/
    public Action getAction() {
        return action;
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
     * The {@code Criteria} class is useful to format a Gmail's filter criteria
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters#criteria">Filter criteria</a>
     **/
    public static class Criteria {

        /**
         * {@code from} the sender's display name or email address
         **/
        private final String from;
        /**
         * {@code to} the recipient's display name or email address. Includes recipients in the "to", "cc", and "bcc" header fields.
         * You can use simply the local part of the email address. For example, "example" and "example@" both match "example@gmail.com". This field is case-insensitive
         **/
        private final String to;
        /**
         * {@code subject} case-insensitive phrase found in the message's subject. Trailing and leading whitespace are be trimmed and adjacent spaces are collapsed
         **/
        private final String subject;
        /**
         * {@code query} only return messages matching the specified query. Supports the same query format as the Gmail search box.
         * For example, {@code "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"}
         **/
        private final String query;
        /**
         * {@code negatedQuery} only return messages not matching the specified query. Supports the same query format as the Gmail search box.
         * For example, {@code "from:someuser@example.com rfc822msgid:<somemsgid@example.com> is:unread"}
         **/
        private final String negatedQuery;
        /**
         * {@code hasAttachment} whether the message has any attachment
         **/
        private final boolean hasAttachment;
        /**
         * {@code excludeChats} whether the response should exclude chats
         **/
        private final boolean excludeChats;
        /**
         * {@code size} the size of the entire {@code "RFC822"} message in bytes, including all headers and attachments
         **/
        private final int size;
        /**
         * {@code sizeComparison} how the message size in bytes should be in relation to the size field
         **/
        private final SizeComparison sizeComparison;

        /**
         * Constructor to init a {@link Criteria}
         *
         * @param from:           the sender's display name or email address
         * @param to:             the recipient's display name or email address
         * @param subject:        case-insensitive phrase found in the message's subject. Trailing and leading whitespace are be trimmed and adjacent spaces are collapsed
         * @param query:          only return messages matching the specified query
         * @param negatedQuery:   only return messages not matching the specified query
         * @param hasAttachment:  whether the message has any attachment
         * @param excludeChats:   whether the response should exclude chats
         * @param size:           the size of the entire {@code "RFC822"} message in bytes, including all headers and attachments
         * @param sizeComparison: how the message size in bytes should be in relation to the size field
         **/
        public Criteria(String from, String to, String subject, String query, String negatedQuery, boolean hasAttachment,
                        boolean excludeChats, int size, SizeComparison sizeComparison) {
            this.from = from;
            this.to = to;
            this.subject = subject;
            this.query = query;
            this.negatedQuery = negatedQuery;
            this.hasAttachment = hasAttachment;
            this.excludeChats = excludeChats;
            this.size = size;
            if (sizeComparison == null)
                sizeComparison = unspecified;
            this.sizeComparison = sizeComparison;
        }

        /**
         * Constructor to init a {@link Criteria}
         *
         * @param jCriteria: {@code "criteria"} details as {@link JSONObject}
         **/
        public Criteria(JSONObject jCriteria) {
            JsonHelper hCriteria = new JsonHelper(jCriteria);
            from = hCriteria.getString("from");
            to = hCriteria.getString("to");
            subject = hCriteria.getString("subject");
            query = hCriteria.getString("query");
            negatedQuery = hCriteria.getString("negatedQuery");
            hasAttachment = hCriteria.getBoolean("hasAttachment");
            excludeChats = hCriteria.getBoolean("excludeChats");
            size = hCriteria.getInt("size");
            sizeComparison = SizeComparison.valueOf(hCriteria.getString("sizeComparison", unspecified.name()));
        }

        /**
         * Method to get {@link #from} instance <br>
         * Any params required
         *
         * @return {@link #from} instance as {@link String}
         **/
        public String getFrom() {
            return from;
        }

        /**
         * Method to get {@link #to} instance <br>
         * Any params required
         *
         * @return {@link #to} instance as {@link String}
         **/
        public String getTo() {
            return to;
        }

        /**
         * Method to get {@link #subject} instance <br>
         * Any params required
         *
         * @return {@link #subject} instance as {@link String}
         **/
        public String getSubject() {
            return subject;
        }

        /**
         * Method to get {@link #query} instance <br>
         * Any params required
         *
         * @return {@link #query} instance as {@link String}
         **/
        public String getQuery() {
            return query;
        }

        /**
         * Method to get {@link #negatedQuery} instance <br>
         * Any params required
         *
         * @return {@link #negatedQuery} instance as {@link String}
         **/
        public String getNegatedQuery() {
            return negatedQuery;
        }

        /**
         * Method to get {@link #hasAttachment} instance <br>
         * Any params required
         *
         * @return {@link #hasAttachment} instance as boolean
         **/
        public boolean isHasAttachment() {
            return hasAttachment;
        }

        /**
         * Method to get {@link #excludeChats} instance <br>
         * Any params required
         *
         * @return {@link #excludeChats} instance as boolean
         **/
        public boolean isExcludeChats() {
            return excludeChats;
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
         * Method to get {@link #sizeComparison} instance <br>
         * Any params required
         *
         * @return {@link #sizeComparison} instance as {@link SizeComparison}
         **/
        public SizeComparison getSizeComparison() {
            return sizeComparison;
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
         * {@code SizeComparison} determines how the size field should be compared to the message size
         *
         * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters#sizecomparison">
         * Size comparison</a>
         **/
        public enum SizeComparison {

            /**
             * {@code unspecified} unspecified size comparison
             **/
            unspecified,

            /**
             * {@code smaller} find messages smaller than the given size
             **/
            smaller,

            /**
             * {@code larger} find messages larger than the given size
             **/
            larger
        }

    }

    /**
     * The {@code Action} class is useful to format a Gmail's filter action
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.filters#action">Filter action</a>
     **/
    public static class Action {

        /**
         * {@code addLabelIds} list of labels to add to the message
         **/
        private final ArrayList<String> addLabelIds;

        /**
         * {@code removeLabelIds} list of labels to remove from the message
         **/
        private final ArrayList<String> removeLabelIds;

        /**
         * {@code forward} email address that the message should be forwarded to
         **/
        private final String forward;

        /**
         * Constructor to init a {@link Action}
         *
         * @param addLabelIds:    list of labels to add to the message
         * @param removeLabelIds: list of labels to remove from the message
         * @param forward:        email address that the message should be forwarded to
         **/
        public Action(Collection<String> addLabelIds, Collection<String> removeLabelIds, String forward) {
            this.addLabelIds = new ArrayList<>(addLabelIds);
            this.removeLabelIds = new ArrayList<>(removeLabelIds);
            this.forward = forward;
        }

        /**
         * Constructor to init a {@link Action}
         *
         * @param addLabelIds:    list of labels to add to the message
         * @param removeLabelIds: list of labels to remove from the message
         * @param forward:        email address that the message should be forwarded to
         **/
        public Action(ArrayList<String> addLabelIds, ArrayList<String> removeLabelIds, String forward) {
            this.addLabelIds = addLabelIds;
            this.removeLabelIds = removeLabelIds;
            this.forward = forward;
        }

        /**
         * Constructor to init a {@link Action}
         *
         * @param jAction: {@code "action"} details as {@link JSONObject}
         **/
        public Action(JSONObject jAction) {
            JsonHelper hAction = new JsonHelper(jAction);
            addLabelIds = loadLabelsList(hAction.getJSONArray("addLabelIds", new JSONArray()));
            removeLabelIds = loadLabelsList(hAction.getJSONArray("removeLabelIds", new JSONArray()));
            forward = hAction.getString("forward");
        }

        /**
         * Method to create a list of labels
         *
         * @param list: list of labels obtained from Google's response
         * @return labels list as {@link ArrayList} of {@link String}
         **/
        private ArrayList<String> loadLabelsList(JSONArray list) {
            ArrayList<String> labels = new ArrayList<>();
            for (int j = 0; j < list.length(); j++)
                labels.add(list.getString(j));
            return labels;
        }

        /**
         * Method to get {@link #addLabelIds} instance <br>
         * Any params required
         *
         * @return {@link #addLabelIds} instance as {@link Collection} of {@link String}
         **/
        public Collection<String> getAddLabelIds() {
            return addLabelIds;
        }

        /**
         * Method to get {@link #removeLabelIds} instance <br>
         * Any params required
         *
         * @return {@link #removeLabelIds} instance as {@link Collection} of {@link String}
         **/
        public Collection<String> getRemoveLabelIds() {
            return removeLabelIds;
        }

        /**
         * Method to get {@link #forward} instance <br>
         * Any params required
         *
         * @return {@link #forward} instance as {@link String}
         **/
        public String getForward() {
            return forward;
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
