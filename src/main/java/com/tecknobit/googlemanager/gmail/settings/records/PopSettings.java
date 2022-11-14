package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition;
import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition.leaveInInbox;
import static com.tecknobit.googlemanager.gmail.settings.records.PopSettings.AccessWindow.accessWindowUnspecified;
import static com.tecknobit.googlemanager.gmail.settings.records.PopSettings.AccessWindow.valueOf;

/**
 * The {@code PopSettings} class is useful to format a Gmail's pop settings
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/PopSettings">PopSettings</a>
 **/
public class PopSettings {

    /**
     * {@code accessWindow} the range of messages which are accessible via {@link "POP"}
     **/
    private AccessWindow accessWindow;

    /**
     * {@code disposition} the action that will be executed on a message after it has been fetched via {@link "POP"}
     **/
    private Disposition disposition;

    /**
     * Constructor to init a {@link PopSettings}
     *
     * @param accessWindow: the range of messages which are accessible via {@link "POP"}
     * @param disposition:  the action that will be executed on a message after it has been fetched via {@link "POP"}
     **/
    public PopSettings(AccessWindow accessWindow, Disposition disposition) {
        this.accessWindow = accessWindow;
        this.disposition = disposition;
    }

    /**
     * Constructor to init a {@link PopSettings}
     *
     * @param jPopSettings: {@code "pop settings"} details as {@link JSONObject}
     **/
    public PopSettings(JSONObject jPopSettings) {
        JsonHelper hPopSettings = new JsonHelper(jPopSettings);
        accessWindow = valueOf(hPopSettings.getString("accessWindow", accessWindowUnspecified.toString()));
        disposition = Disposition.valueOf(hPopSettings.getString("disposition", leaveInInbox.toString()));
    }

    /**
     * Method to get {@link #accessWindow} instance <br>
     * Any params required
     *
     * @return {@link #accessWindow} instance as {@link AccessWindow}
     **/
    public AccessWindow getAccessWindow() {
        return accessWindow;
    }

    /**
     * Method to set {@link #accessWindow} instance
     *
     * @param accessWindow: the range of messages which are accessible via {@link "POP"}
     **/
    public void setAccessWindow(AccessWindow accessWindow) {
        this.accessWindow = accessWindow;
    }

    /**
     * Method to get {@link #disposition} instance <br>
     * Any params required
     *
     * @return {@link #disposition} instance as {@link Disposition}
     **/
    public Disposition getDisposition() {
        return disposition;
    }

    /**
     * Method to set {@link #disposition} instance
     *
     * @param disposition: the action that will be executed on a message after it has been fetched via {@link "POP"}
     **/
    public void setDisposition(Disposition disposition) {
        this.disposition = disposition;
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
     * {@code AccessWindow} list of a range of messages that are accessible via {@code "POP"}
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/PopSettings#accesswindow">
     * AccessWindow</a>
     **/
    public enum AccessWindow {

        /**
         * {@code accessWindowUnspecified} unspecified range
         **/
        accessWindowUnspecified,

        /**
         * {@code disabled} indicates that no messages are accessible via {@code "POP"}
         **/
        disabled,

        /**
         * {@code fromNowOn} indicates that unfetched messages received after some past point in time are accessible via {@code "POP"}
         **/
        fromNowOn,

        /**
         * {@code allMail} indicates that all unfetched messages are accessible via {@code "POP"}
         **/
        allMail
    }

}
