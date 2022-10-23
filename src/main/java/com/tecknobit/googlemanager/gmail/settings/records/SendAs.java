package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.SendAs.SendAsVerificationStatus.verificationStatusUnspecified;

/**
 * The {@code SendAs} class is useful to format a Gmail's send-as
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs#SendAs">SendAs</a>
 **/
public class SendAs {

    /**
     * {@code sendAsEmail} the email address that appears in the {@code "From:"} header for mail sent using this alias.
     * This is read-only for all operations except create
     **/
    private final String sendAsEmail;

    /**
     * {@code displayName} a name that appears in the {@code "From:"} header for mail sent using this alias.
     * For custom {@code "from"} addresses, when this is empty, {@code "Gmail"} will populate the {@code "From:"} header with the name that is used
     * for the primary address associated with the account. If the admin has disabled the ability for users to update their
     * name format, requests to update this field for the primary login will silently fail
     **/
    private final String displayName;

    /**
     * {@code replyToAddress} an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias.
     * If this is empty, {@code "Gmail"} will not generate a {@code "Reply-To:"} header
     **/
    private final String replyToAddress;

    /**
     * {@code signature} an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI.
     * This signature is added to new emails only
     **/
    private final String signature;

    /**
     * {@code isPrimary} whether this address is the primary address used to login to the account. Every Gmail account has
     * exactly one primary address, and it cannot be deleted from the collection of send-as aliases. This field is read-only
     **/
    private final boolean isPrimary;

    /**
     * {@code isDefault} whether this address is selected as the default {@code "From:"} address in situations such as composing
     * a new message or sending a vacation auto-reply. Every Gmail account has exactly one default send-as address,
     * so the only legal value that clients may write to this field is true. Changing this from false to true for an address
     * will result in this field becoming false for the other previous default address
     **/
    private final boolean isDefault;

    /**
     * {@code treatAsAlias} whether Gmail should treat this address as an alias for the user's primary email address.
     * This setting only applies to custom {@code "from"} aliases
     **/
    private final boolean treatAsAlias;

    /**
     * {@code smtpMsa} an optional {@code "SMTP"} service that will be used as an outbound relay for mail sent using this alias.
     * If this is empty, outbound mail will be sent directly from Gmail's servers to the destination {@code "SMTP"} service
     * This setting only applies to custom {@code "from"} aliases
     **/
    private final SmtpMsa smtpMsa;

    /**
     * {@code verificationStatus} indicates whether this address has been verified for use as a send-as alias. Read-only.
     * This setting only applies to custom {@code "from"} aliases
     **/
    private final SendAsVerificationStatus verificationStatus;

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:        the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:        a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param replyToAddress:     an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param signature:          an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @param isPrimary:          whether this address is the primary address used to login to the account
     * @param isDefault:whether   this address is selected as the default {@code "From:"} address in situations such as composing
     *                            a new message or sending a vacation auto-reply
     * @param treatAsAlias:       whether Gmail should treat this address as an alias for the user's primary email address
     * @param smtpMsa:            an optional {@code "SMTP"} service that will be used as an outbound relay for mail sent using this alias
     * @param verificationStatus: indicates whether this address has been verified for use as a send-as alias. Read-only
     **/
    public SendAs(String sendAsEmail, String displayName, String replyToAddress, String signature, boolean isPrimary,
                  boolean isDefault, boolean treatAsAlias, SmtpMsa smtpMsa, SendAsVerificationStatus verificationStatus) {
        this.sendAsEmail = sendAsEmail;
        this.displayName = displayName;
        this.replyToAddress = replyToAddress;
        this.signature = signature;
        this.isPrimary = isPrimary;
        this.isDefault = isDefault;
        this.treatAsAlias = treatAsAlias;
        this.smtpMsa = smtpMsa;
        this.verificationStatus = verificationStatus;
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:      the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:      a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param isDefault:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                          a new message or sending a vacation auto-reply
     * @param treatAsAlias:     whether Gmail should treat this address as an alias for the user's primary email address
     * @param smtpMsa:          an optional {@code "SMTP"} service that will be used as an outbound relay for mail sent using this alias
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SendAs}
     **/
    public SendAs(String sendAsEmail, String displayName, boolean isDefault, boolean treatAsAlias, SmtpMsa smtpMsa) {
        this(sendAsEmail, displayName, null, null, false, isDefault, treatAsAlias, smtpMsa,
                verificationStatusUnspecified);
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:      the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:      a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param signature:        an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @param isDefault:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                          a new message or sending a vacation auto-reply
     * @param treatAsAlias:     whether Gmail should treat this address as an alias for the user's primary email address
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SendAs}
     **/
    public SendAs(String sendAsEmail, String displayName, String signature, boolean isDefault, boolean treatAsAlias) {
        this(sendAsEmail, displayName, null, signature, false, isDefault, treatAsAlias, null,
                verificationStatusUnspecified);
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:      the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:      a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param replyToAddress:   an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param isDefault:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                          a new message or sending a vacation auto-reply
     * @param treatAsAlias:     whether Gmail should treat this address as an alias for the user's primary email address
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SendAs}
     **/
    public SendAs(String sendAsEmail, String displayName, boolean isDefault, String replyToAddress, boolean treatAsAlias) {
        this(sendAsEmail, displayName, replyToAddress, null, false, isDefault, treatAsAlias, null,
                verificationStatusUnspecified);
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:      the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:      a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param replyToAddress:   an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param isDefault:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                          a new message or sending a vacation auto-reply
     * @param treatAsAlias:     whether Gmail should treat this address as an alias for the user's primary email address
     * @param smtpMsa:          an optional {@code "SMTP"} service that will be used as an outbound relay for mail sent using this alias
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SendAs}
     **/
    public SendAs(String sendAsEmail, String displayName, boolean isDefault, String replyToAddress, boolean treatAsAlias,
                  SmtpMsa smtpMsa) {
        this(sendAsEmail, displayName, replyToAddress, null, false, isDefault, treatAsAlias, smtpMsa,
                verificationStatusUnspecified);
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:      the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:      a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param replyToAddress:   an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param signature:        an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @param isDefault:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                          a new message or sending a vacation auto-reply
     * @param treatAsAlias:     whether Gmail should treat this address as an alias for the user's primary email address
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SendAs}
     **/
    public SendAs(String sendAsEmail, String displayName, String replyToAddress, String signature, boolean isDefault,
                  boolean treatAsAlias) {
        this(sendAsEmail, displayName, replyToAddress, signature, false, isDefault, treatAsAlias, null,
                verificationStatusUnspecified);
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:      the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:      a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param replyToAddress:   an optional email address that is included in a {@code "Reply-To:"} header for mail sent using this alias
     * @param signature:        an optional {@code "HTML"} signature that is included in messages composed with this alias in the Gmail web UI
     * @param isDefault:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                          a new message or sending a vacation auto-reply
     * @param treatAsAlias:     whether Gmail should treat this address as an alias for the user's primary email address
     * @param smtpMsa:          an optional {@code "SMTP"} service that will be used as an outbound relay for mail sent using this alias
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SendAs}
     **/
    public SendAs(String sendAsEmail, String displayName, String replyToAddress, String signature, boolean isDefault,
                  boolean treatAsAlias, SmtpMsa smtpMsa) {
        this(sendAsEmail, displayName, replyToAddress, signature, false, isDefault, treatAsAlias, smtpMsa,
                verificationStatusUnspecified);
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param sendAsEmail:      the email address that appears in the {@code "From:"} header for mail sent using this alias
     * @param displayName:      a name that appears in the {@code "From:"} header for mail sent using this alias
     * @param isDefault:whether this address is selected as the default {@code "From:"} address in situations such as composing
     *                          a new message or sending a vacation auto-reply
     * @param treatAsAlias:     whether Gmail should treat this address as an alias for the user's primary email address
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SendAs}
     **/
    public SendAs(String sendAsEmail, String displayName, boolean isDefault, boolean treatAsAlias) {
        this(sendAsEmail, displayName, null, null, false, isDefault, treatAsAlias, null,
                verificationStatusUnspecified);
    }

    /**
     * Constructor to init a {@link SendAs}
     *
     * @param jSendAs: {@code "send-as"} details as {@link JSONObject}
     **/
    public SendAs(JSONObject jSendAs) {
        JsonHelper hSendAs = new JsonHelper(jSendAs);
        sendAsEmail = hSendAs.getString("sendAsEmail");
        displayName = hSendAs.getString("displayName");
        replyToAddress = hSendAs.getString("replyToAddress");
        signature = hSendAs.getString("signature");
        isPrimary = hSendAs.getBoolean("isPrimary");
        isDefault = hSendAs.getBoolean("isDefault");
        treatAsAlias = hSendAs.getBoolean("treatAsAlias");
        smtpMsa = new SmtpMsa(hSendAs.getJSONObject("smtpMsa", new JSONObject()));
        verificationStatus = SendAsVerificationStatus.valueOf(hSendAs.getString("verificationStatus",
                verificationStatusUnspecified.name()));
    }

    /**
     * Method to get {@link #sendAsEmail} instance <br>
     * Any params required
     *
     * @return {@link #sendAsEmail} instance as {@link String}
     **/
    public String getSendAsEmail() {
        return sendAsEmail;
    }

    /**
     * Method to get {@link #displayName} instance <br>
     * Any params required
     *
     * @return {@link #displayName} instance as {@link String}
     **/
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Method to get {@link #replyToAddress} instance <br>
     * Any params required
     *
     * @return {@link #replyToAddress} instance as {@link String}
     **/
    public String getReplyToAddress() {
        return replyToAddress;
    }

    /**
     * Method to get {@link #signature} instance <br>
     * Any params required
     *
     * @return {@link #signature} instance as {@link String}
     **/
    public String getSignature() {
        return signature;
    }

    /**
     * Method to get {@link #isPrimary} instance <br>
     * Any params required
     *
     * @return {@link #isPrimary} instance as boolean
     **/
    public boolean isPrimary() {
        return isPrimary;
    }

    /**
     * Method to get {@link #isDefault} instance <br>
     * Any params required
     *
     * @return {@link #isDefault} instance as boolean
     **/
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Method to get {@link #treatAsAlias} instance <br>
     * Any params required
     *
     * @return {@link #treatAsAlias} instance as boolean
     **/
    public boolean isTreatAsAlias() {
        return treatAsAlias;
    }

    /**
     * Method to get {@link #smtpMsa} instance <br>
     * Any params required
     *
     * @return {@link #smtpMsa} instance as {@link SmtpMsa}
     **/
    public SmtpMsa getSmtpMsa() {
        return smtpMsa;
    }

    /**
     * Method to get {@link #verificationStatus} instance <br>
     * Any params required
     *
     * @return {@link #verificationStatus} instance as {@link SendAsVerificationStatus}
     **/
    public SendAsVerificationStatus getVerificationStatus() {
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
     * {@code VerificationStatus} indicates whether ownership of an address has been verified for its use as a send-as alias
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs#verificationstatus">
     * Verification Status</a>
     **/
    public enum SendAsVerificationStatus {

        /**
         * {@code verificationStatusUnspecified} unspecified verification status
         **/
        verificationStatusUnspecified,

        /**
         * {@code accepted} the address is ready to use as a send-as alias
         **/
        accepted,

        /**
         * {@code pending} the address is awaiting verification by the owner
         **/
        pending
    }

    /**
     * The {@code SmtpMsa} class is useful to format a Gmail's SmtpMsa
     *
     * @author N7ghtm4r3 - Tecknobit
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs#smtpmsa">SmtpMsa</a>
     **/
    public static class SmtpMsa {

        /**
         * {@code host} the hostname of the {@code "SMTP"} service {@code "Required"}
         **/
        private final String host;
        /**
         * {@code port} the port of the {@code "SMTP"} service {@code "Required"}
         **/
        private final int port;
        /**
         * {@code username} the username that will be used for authentication with the {@code "SMTP"} service
         * This is a {@code "write-only"}  field that can be specified in requests to create or update {@code "SendAs"} settings;
         * {@code "it is never populated in responses"}
         **/
        private final String username;
        /**
         * {@code password} the password that will be used for authentication with the {@code "SMTP"} service This is a {@code "write-only"}
         * field that can be specified in requests to create or update {@code "SendAs"} settings; {@code "it is never populated in responses"}
         **/
        private final String password;
        /**
         * {@code securityMode} the protocol that will be used to secure communication with the {@code "SMTP"} service {@code "Required"}
         **/
        private final SecurityMode securityMode;

        /**
         * Constructor to init a {@link SmtpMsa}
         *
         * @param host:         the hostname of the {@code "SMTP"} service {@code "Required"}
         * @param port:         the port of the {@code "SMTP"} service {@code "Required"}
         * @param username:     the username that will be used for authentication with the {@code "SMTP"} service
         * @param password:     the password that will be used for authentication with the {@code "SMTP"} service
         * @param securityMode: the protocol that will be used to secure communication with the {@code "SMTP"} service
         **/
        public SmtpMsa(String host, int port, String username, String password, SecurityMode securityMode) {
            this.host = host;
            this.port = port;
            this.username = username;
            this.password = password;
            this.securityMode = securityMode;
        }

        /**
         * Constructor to init a {@link SmtpMsa}
         *
         * @param jSmtpMsa: {@code "SmtpMsa"} details as {@link JSONObject}
         **/
        public SmtpMsa(JSONObject jSmtpMsa) {
            JsonHelper hSmtpMsa = new JsonHelper(jSmtpMsa);
            host = hSmtpMsa.getString("host");
            port = hSmtpMsa.getInt("port", 0);
            username = hSmtpMsa.getString("username");
            password = hSmtpMsa.getString("password");
            securityMode = SecurityMode.valueOf(hSmtpMsa.getString("securityMode",
                    SecurityMode.securityModeUnspecified.name()));
        }

        /**
         * Method to get {@link #host} instance <br>
         * Any params required
         *
         * @return {@link #host} instance as {@link String}
         **/
        public String getHost() {
            return host;
        }

        /**
         * Method to get {@link #port} instance <br>
         * Any params required
         *
         * @return {@link #port} instance as int
         **/
        public int getPort() {
            return port;
        }

        /**
         * Method to get {@link #username} instance <br>
         * Any params required
         *
         * @return {@link #username} instance as {@link String}
         **/
        public String getUsername() {
            return username;
        }

        /**
         * Method to get {@link #password} instance <br>
         * Any params required
         *
         * @return {@link #password} instance as {@link String}
         **/
        public String getPassword() {
            return password;
        }

        /**
         * Method to get {@link #securityMode} instance <br>
         * Any params required
         *
         * @return {@link #securityMode} instance as {@link SecurityMode}
         **/
        public SecurityMode getSecurityMode() {
            return securityMode;
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
         * {@code SecurityMode} indicates whether ownership of an address has been verified for its use as a send-as alias
         *
         * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs#securitymode">
         * Security mode</a>
         **/
        public enum SecurityMode {

            /**
             * {@code securityModeUnspecified} unspecified security mode
             **/
            securityModeUnspecified,

            /**
             * {@code none} communication with the remote {@code "SMTP"} service is unsecured. Requires port {@code "25"}
             **/
            none,

            /**
             * {@code ssl} communication with the remote {@code "SMTP"} service is secured using {@code "SSL"}
             **/
            ssl,

            /**
             * {@code starttls} communication with the remote {@code "SMTP"} service is secured using {@code "STARTTLS"}
             **/
            starttls
        }

    }

}
