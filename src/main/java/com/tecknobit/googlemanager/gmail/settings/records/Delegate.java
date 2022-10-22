package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.Delegate.VerificationStatus.verificationStatusUnspecified;

/**
 * The {@code Delegate} class is useful to format a Gmail's delegate
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates#resource:-delegate">Delegate</a>
 **/
public class Delegate {

    /**
     * {@code delegateEmail} the email address of the delegate
     **/
    private final String delegateEmail;
    /**
     * {@code verificationStatus} indicates whether this address has been verified and can act as a delegate for the account. Read-only
     **/
    private final VerificationStatus verificationStatus;

    /**
     * Constructor to init a {@link Delegate}
     *
     * @param delegateEmail: the email address of the delegate
     * @apiNote this constructor is useful in those cases if you want to create a new {@link Delegate}
     **/
    public Delegate(String delegateEmail) {
        this.delegateEmail = delegateEmail;
        verificationStatus = verificationStatusUnspecified;
    }

    /**
     * Constructor to init a {@link Delegate}
     *
     * @param delegateEmail:      the email address of the delegate
     * @param verificationStatus: indicates whether this address has been verified and can act as a delegate for the account. Read-only
     **/
    public Delegate(String delegateEmail, VerificationStatus verificationStatus) {
        this.delegateEmail = delegateEmail;
        this.verificationStatus = verificationStatus;
    }

    /**
     * Constructor to init a {@link Delegate}
     *
     * @param jDelegate: {@code "delegate"} details as {@link JSONObject}
     **/
    public Delegate(JSONObject jDelegate) {
        JsonHelper hDelegate = new JsonHelper(jDelegate);
        delegateEmail = hDelegate.getString("delegateEmail");
        verificationStatus = VerificationStatus.valueOf(hDelegate.getString("verificationStatus",
                verificationStatusUnspecified.name()));
    }

    /**
     * Method to get {@link #delegateEmail} instance <br>
     * Any params required
     *
     * @return {@link #delegateEmail} instance as {@link String}
     **/
    public String getDelegateEmail() {
        return delegateEmail;
    }

    /**
     * Method to get {@link #verificationStatus} instance <br>
     * Any params required
     *
     * @return {@link #verificationStatus} instance as {@link VerificationStatus}
     **/
    public VerificationStatus getVerificationStatus() {
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
     * {@code VerificationStatus} indicates whether ownership of an email address has been verified for delegation use
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.delegates#verificationstatus">
     * Verification Status</a>
     **/
    public enum VerificationStatus {

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
        pending,

        /**
         * {@code rejected} verification request was mailed to the address, and the owner rejected it
         **/
        rejected,

        /**
         * {@code expired} verification request was mailed to the address, and it expired without verification
         **/
        expired
    }

}
