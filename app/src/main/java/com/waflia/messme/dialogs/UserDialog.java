package com.waflia.messme.dialogs;

import android.net.Uri;

public class UserDialog {
    private String username;
    private int dialogAvatar;

    public UserDialog(String username, int imgUri) {
        this.username = username;
        this.dialogAvatar = imgUri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDialogAvatar() {
        return dialogAvatar;
    }

    public void setDialogAvatar(int dialogAvatar) {
        this.dialogAvatar = dialogAvatar;
    }
}
