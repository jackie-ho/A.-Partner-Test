package com.apppartner.androidprogrammertest.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JH on 9/11/16.
 */
public class ChatData implements Parcelable{
    private static final String LOG_TAG = "ChatData";

    public int userID;
    public String username;
    public String avatarURL;
    public String message;

    public ChatData(JSONObject jsonObject){

       try {
           userID = jsonObject.getInt("user_id");
           username = jsonObject.getString("username");
           avatarURL = jsonObject.getString("avatar_url");
           message = jsonObject.getString("message");
       } catch (JSONException e) {
           e.printStackTrace();
       }

    }

    protected ChatData(Parcel in) {
        userID = in.readInt();
        username = in.readString();
        avatarURL = in.readString();
        message = in.readString();
    }

    public static final Creator<ChatData> CREATOR = new Creator<ChatData>() {
        @Override
        public ChatData createFromParcel(Parcel in) {
            return new ChatData(in);
        }

        @Override
        public ChatData[] newArray(int size) {
            return new ChatData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(username);
        dest.writeString(avatarURL);
        dest.writeString(message);
    }
}
