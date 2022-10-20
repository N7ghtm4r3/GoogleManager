package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.ImapSettings.ExpungeBehavior.expungeBehaviorUnspecified;

public class ImapSettings {

    private boolean enabled;
    private boolean autoExpunge;
    private ExpungeBehavior expungeBehavior;
    private double maxFolderSize;

    public ImapSettings(boolean enabled, boolean autoExpunge, ExpungeBehavior expungeBehavior, double maxFolderSize) {
        this.enabled = enabled;
        this.autoExpunge = autoExpunge;
        this.expungeBehavior = expungeBehavior;
        this.maxFolderSize = maxFolderSize;
    }

    public ImapSettings(JSONObject jImapSettings) {
        JsonHelper hImapSettings = new JsonHelper(jImapSettings);
        enabled = hImapSettings.getBoolean("enabled");
        autoExpunge = hImapSettings.getBoolean("autoExpunge");
        expungeBehavior = ExpungeBehavior.valueOf(hImapSettings.getString("expungeBehavior",
                expungeBehaviorUnspecified.toString()));
        maxFolderSize = hImapSettings.getDouble("maxFolderSize");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public boolean isAutoExpunge() {
        return autoExpunge;
    }

    public void setAutoExpunge(boolean autoExpunge) {
        this.autoExpunge = autoExpunge;
    }

    public ExpungeBehavior getExpungeBehavior() {
        return expungeBehavior;
    }

    public void setExpungeBehavior(ExpungeBehavior expungeBehavior) {
        this.expungeBehavior = expungeBehavior;
    }

    public double getMaxFolderSize() {
        return maxFolderSize;
    }

    public void setMaxFolderSize(double maxFolderSize) {
        this.maxFolderSize = maxFolderSize;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    public enum ExpungeBehavior {

        expungeBehaviorUnspecified("expungeBehaviorUnspecified"),
        archive("archive"),
        trash("trash"),
        deleteForever("deleteForever");

        private final String expungeBehavior;

        ExpungeBehavior(String expungeBehavior) {
            this.expungeBehavior = expungeBehavior;
        }

        @Override
        public String toString() {
            return expungeBehavior;
        }

    }

}
