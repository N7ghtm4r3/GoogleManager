package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition.leaveInInbox;
import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition.valueOf;

/**
 * The {@code AutoForwarding} class is useful to format a Gmail's auto-forwarding
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/AutoForwarding">AutoForwarding</a>
 **/
public class AutoForwarding {

    /**
     * {@code enabled} whether all incoming mail is automatically forwarded to another address
     **/
    private boolean enabled;

    /**
     * {@code emailAddress} email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     **/
    private String emailAddress;

    /**
     * {@code disposition} the state that a message should be left in after it has been forwarded
     **/
    private Disposition disposition;

    /**
     * Constructor to init a {@link AutoForwarding}
     *
     * @param enabled:      whether all incoming mail is automatically forwarded to another address
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     * @param disposition:  the state that a message should be left in after it has been forwarded
     **/
    public AutoForwarding(boolean enabled, String emailAddress, Disposition disposition) {
        this.enabled = enabled;
        this.emailAddress = emailAddress;
        this.disposition = disposition;
    }

    /**
     * Constructor to init a {@link AutoForwarding}
     *
     * @param jAutoForwarding: {@code "auto-forwarding"} details as {@link JSONObject}
     **/
    public AutoForwarding(JSONObject jAutoForwarding) {
        JsonHelper hAutoForwarding = new JsonHelper(jAutoForwarding);
        enabled = hAutoForwarding.getBoolean("enabled");
        emailAddress = hAutoForwarding.getString("emailAddress");
        disposition = valueOf(hAutoForwarding.getString("disposition", leaveInInbox.toString()));
    }

    /**
     * Method to get {@link #enabled} instance <br>
     * Any params required
     *
     * @return {@link #enabled} instance as boolean
     **/
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Method to set {@link #enabled} instance on {@code "true"}<br>
     * Any params required
     **/
    public void enable() {
        enabled = true;
    }

    /**
     * Method to set {@link #enabled} instance on {@code "false"}<br>
     * Any params required
     **/
    public void disable() {
        enabled = false;
    }

    /**
     * Method to get {@link #emailAddress} instance <br>
     * Any params required
     *
     * @return {@link #emailAddress} instance as {@link String}
     **/
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Method to set {@link #emailAddress} instance
     *
     * @param emailAddress: email address to which all incoming messages are forwarded. This email address must be a verified member of the forwarding addresses
     **/
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
     * @param disposition: the state that a message should be left in after it has been forwarded
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
     * {@code Disposition} specifies what Gmail should do with a message after it has been automatically forwarded
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/AutoForwarding#disposition">
     * Disposition</a>
     **/
    public enum Disposition {

        /**
         * {@code dispositionUnspecified} unspecified disposition
         **/
        dispositionUnspecified,

        /**
         * {@code leaveInInbox} leave the message in the {@code  "INBOX"}
         **/
        leaveInInbox,

        /**
         * {@code archive} archive the message
         **/
        archive,

        /**
         * {@code trash} move the message to the {@code "TRASH"}
         **/
        trash,

        /**
         * {@code markRead} leave the message in the {@code "INBOX"} and mark it as read
         **/
        markRead
    }

}
