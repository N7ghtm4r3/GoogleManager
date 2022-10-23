package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.ImapSettings.ExpungeBehavior.expungeBehaviorUnspecified;

/**
 * The {@code ImapSettings} class is useful to format a Gmail's imap settings
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/ImapSettings">ImapSettings</a>
 **/
public class ImapSettings {

    /**
     * {@code enabled} whether {@code "IMAP"} is enabled for the account
     **/
    private boolean enabled;

    /**
     * {@code autoExpunge} if this value is true, {@code "Gmail"} will immediately expunge a message when it is marked as deleted in {@code "IMAP"}. Otherwise, {@code "Gmail"} will wait for an update from the client before expunging messages marked as deleted
     **/
    private boolean autoExpunge;

    /**
     * {@code expungeBehavior} the action that will be executed on a message when it is marked as deleted and expunged from the last visible {@code "IMAP"} folder
     **/
    private ExpungeBehavior expungeBehavior;

    /**
     * {@code maxFolderSize} an optional limit on the number of messages that an {@code "IMAP"} folder may contain. Legal values are 0, 1000, 2000, 5000 or 10000. A value of zero is interpreted to mean that there is no limit
     **/
    private int maxFolderSize;

    /**
     * Constructor to init a {@link ImapSettings}
     *
     * @param enabled:         whether {@code "IMAP"} is enabled for the account
     * @param autoExpunge:     if this value is true, {@code "Gmail"} will immediately expunge a message when it is marked as deleted in {@code "IMAP"}. Otherwise, {@code "Gmail"} will wait for an update from the client before expunging messages marked as deleted
     * @param expungeBehavior: the action that will be executed on a message when it is marked as deleted and expunged from the last visible {@code "IMAP"} folder
     * @param maxFolderSize:   an optional limit on the number of messages that an {@code "IMAP"} folder may contain. Legal values are 0, 1000, 2000, 5000 or 10000. A value of zero is interpreted to mean that there is no limit
     **/
    public ImapSettings(boolean enabled, boolean autoExpunge, ExpungeBehavior expungeBehavior, int maxFolderSize) {
        this.enabled = enabled;
        this.autoExpunge = autoExpunge;
        this.expungeBehavior = expungeBehavior;
        this.maxFolderSize = maxFolderSize;
    }

    /**
     * Constructor to init a {@link ImapSettings}
     *
     * @param jImapSettings: {@code "imap settings"} details as {@link JSONObject}
     **/
    public ImapSettings(JSONObject jImapSettings) {
        JsonHelper hImapSettings = new JsonHelper(jImapSettings);
        enabled = hImapSettings.getBoolean("enabled");
        autoExpunge = hImapSettings.getBoolean("autoExpunge");
        expungeBehavior = ExpungeBehavior.valueOf(hImapSettings.getString("expungeBehavior",
                expungeBehaviorUnspecified.toString()));
        maxFolderSize = hImapSettings.getInt("maxFolderSize");
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
     * Method to get {@link #autoExpunge} instance <br>
     * Any params required
     *
     * @return {@link #autoExpunge} instance as boolean
     **/
    public boolean isAutoExpungeEnabled() {
        return autoExpunge;
    }

    /**
     * Method to set {@link #autoExpunge} instance on {@code "true"}<br>
     * Any params required
     **/
    public void enableAutoExpunge() {
        autoExpunge = true;
    }

    /**
     * Method to set {@link #autoExpunge} instance on {@code "false"}<br>
     * Any params required
     **/
    public void disableAutoExpunge() {
        autoExpunge = false;
    }

    /**
     * Method to get {@link #expungeBehavior} instance <br>
     * Any params required
     *
     * @return {@link #expungeBehavior} instance as {@link ExpungeBehavior}
     **/
    public ExpungeBehavior getExpungeBehavior() {
        return expungeBehavior;
    }

    /**
     * Method to set {@link #expungeBehavior} instance
     *
     * @param expungeBehavior: the action that will be executed on a message when it is marked as deleted and expunged from the last visible {@code "IMAP"} folder
     **/
    public void setExpungeBehavior(ExpungeBehavior expungeBehavior) {
        this.expungeBehavior = expungeBehavior;
    }

    /**
     * Method to get {@link #maxFolderSize} instance <br>
     * Any params required
     *
     * @return {@link #maxFolderSize} instance as int
     **/
    public int getMaxFolderSize() {
        return maxFolderSize;
    }

    /**
     * Method to set {@link #maxFolderSize} instance
     *
     * @param maxFolderSize: an optional limit on the number of messages that an {@code "IMAP"} folder may contain.
     *                       Legal values are 0, 1000, 2000, 5000 or 10000. A value of zero is interpreted to mean that there is no limit
     **/
    public void setMaxFolderSize(int maxFolderSize) {
        this.maxFolderSize = maxFolderSize;
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
     * {@code ExpungeBehavior} list of the actions that will be executed on a message when it is marked as deleted and
     * expunged from the last visible {@code "IMAP"} folder
     *
     * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/ImapSettings#ExpungeBehavior">
     * ExpungeBehavior</a>
     **/
    public enum ExpungeBehavior {

        /**
         * {@code expungeBehaviorUnspecified} unspecified behavior
         **/
        expungeBehaviorUnspecified,

        /**
         * {@code archive} archive messages marked as deleted
         **/
        archive,

        /**
         * {@code trash} move messages marked as deleted to the trash
         **/
        trash,

        /**
         * {@code deleteForever} immediately and permanently delete messages marked as deleted. The expunged messages cannot be recovered
         **/
        deleteForever
    }

}
