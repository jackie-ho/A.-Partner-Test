package com.ho.jackie.model.entities;


/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatData
{
    private static final String LOG_TAG = "ChatData";

    public int userID;
    public String username;
    public String avatarURL;
    public String message;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
