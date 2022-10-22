package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.ForwardingAddress.ForwardingVerificationStatus.valueOf;
import static com.tecknobit.googlemanager.gmail.settings.records.ForwardingAddress.ForwardingVerificationStatus.verificationStatusUnspecified;

/**
 * The {@code ForwardingAddress} class is useful to format a Gmail's forwarding address
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses#resource:-forwardingaddress">
 * Forwarding address</a>
 **/
public class ForwardingAddress {

    /**
     * {@code forwardingEmail} an email address to which messages can be forwarded
     **/
    private final String forwardingEmail;

    /**
     * {@code verificationStatus} indicates whether this address has been verified and is usable for forwarding. Read-only
     **/
    private final ForwardingVerificationStatus verificationStatus;

    /**
     * Constructor to init a {@link ForwardingAddress}
     *
     * @param forwardingEmail: an email address to which messages can be forwarded
     * @apiNote this constructor is useful in those cases if you want to create a new {@link ForwardingAddress}
     **/
    public ForwardingAddress(String forwardingEmail) {
        this.forwardingEmail = forwardingEmail;
        verificationStatus = verificationStatusUnspecified;
    }

    /**
     * Constructor to init a {@link ForwardingAddress}
     *
     * @param forwardingEmail:    an email address to which messages can be forwarded
     * @param verificationStatus: indicates whether this address has been verified and is usable for forwarding. Read-only
     **/
    public ForwardingAddress(String forwardingEmail, ForwardingVerificationStatus verificationStatus) {
        this.forwardingEmail = forwardingEmail;
        this.verificationStatus = verificationStatus;
    }

    /**
     * Constructor to init a {@link ForwardingAddress}
     *
     * @param jForwardingAddress: {@code "forwarding address"} details as {@link JSONObject}
     **/
    public ForwardingAddress(JSONObject jForwardingAddress) {
        JsonHelper hForwardingAddress = new JsonHelper(jForwardingAddress);
        forwardingEmail = hForwardingAddress.getString("forwardingEmail");
        verificationStatus = valueOf(hForwardingAddress.getString("verificationStatus",
                verificationStatusUnspecified.name()));
    }

    /**
     * Method to get {@link #forwardingEmail} instance <br>
     * Any params required
     *
     * @return {@link #forwardingEmail} instance as {@link String}
     **/
    public String getForwardingEmail() {
        return forwardingEmail;
    }

    /**
     * Method to get {@link #verificationStatus} instance <br>
     * Any params required
     *
     * @return {@link #verificationStatus} instance as {@link ForwardingVerificationStatus}
     **/
    public ForwardingVerificationStatus getVerificationStatus() {
        return verificationStatus;
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
     * {@code VerificationStatus} indicates whether ownership of an email address has been verified for forwarding use
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.forwardingAddresses#verificationstatus">
     * Verification Status</a>
     **/
    public enum ForwardingVerificationStatus {

        /**
         * {@code verificationStatusUnspecified} unspecified verification status
         **/
        verificationStatusUnspecified,

        /**
         * {@code accepted} the address can act a delegate for the account
         **/
        accepted,

        /**
         * {@code pending} verification request was mailed to the address, and the owner has not yet accepted it
         **/
        pending
    }

}
