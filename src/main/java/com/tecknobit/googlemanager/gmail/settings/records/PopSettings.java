package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.Tools.Formatters.JsonHelper;
import org.json.JSONObject;

import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition;
import static com.tecknobit.googlemanager.gmail.settings.records.AutoForwarding.Disposition.leaveInInbox;
import static com.tecknobit.googlemanager.gmail.settings.records.PopSettings.AccessWindow.accessWindowUnspecified;
import static com.tecknobit.googlemanager.gmail.settings.records.PopSettings.AccessWindow.valueOf;

public class PopSettings {

    private AccessWindow accessWindow;
    private Disposition disposition;

    public PopSettings(AccessWindow accessWindow, Disposition disposition) {
        this.accessWindow = accessWindow;
        this.disposition = disposition;
    }

    public PopSettings(JSONObject jPopSettings) {
        JsonHelper hPopSettings = new JsonHelper(jPopSettings);
        accessWindow = valueOf(hPopSettings.getString("accessWindow", accessWindowUnspecified.toString()));
        disposition = Disposition.valueOf(hPopSettings.getString("disposition", leaveInInbox.toString()));
    }

    public AccessWindow getAccessWindow() {
        return accessWindow;
    }

    public void setAccessWindow(AccessWindow accessWindow) {
        this.accessWindow = accessWindow;
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

    public enum AccessWindow {

        accessWindowUnspecified("accessWindowUnspecified"),
        disabled("disabled"),
        fromNowOn("fromNowOn"),
        allMail("allMail");

        private final String accessWindow;

        AccessWindow(String accessWindow) {
            this.accessWindow = accessWindow;
        }

        @Override
        public String toString() {
            return accessWindow;
        }

    }

}
