package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition.leaveInInbox;
import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition.valueOf;

public class AutoForwarding {

    private boolean enabled;
    private String emailAddress;
    private Disposition disposition;

    public AutoForwarding(boolean enabled, String emailAddress, Disposition disposition) {
        this.enabled = enabled;
        this.emailAddress = emailAddress;
        this.disposition = disposition;
    }

    public AutoForwarding(JSONObject jAutoForwarding) {
        JsonHelper hAutoForwarding = new JsonHelper(jAutoForwarding);
        enabled = hAutoForwarding.getBoolean("enabled");
        emailAddress = hAutoForwarding.getString("emailAddress");
        disposition = valueOf(hAutoForwarding.getString("disposition", leaveInInbox.toString()));
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Disposition getDisposition() {
        return disposition;
    }

    public void setDisposition(Disposition disposition) {
        this.disposition = disposition;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    public enum Disposition {

        dispositionUnspecified("dispositionUnspecified"),
        leaveInInbox("leaveInInbox"),
        archive("archive"),
        trash("trash"),
        markRead("markRead");

        private final String disposition;

        Disposition(String disposition) {
            this.disposition = disposition;
        }

        @Override
        public String toString() {
            return disposition;
        }

    }

}
