package com.apppartner.androidprogrammertest.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.adapters.ChatsArrayAdapter;
import com.apppartner.androidprogrammertest.models.ChatData;
import com.apppartner.androidprogrammertest.mvp.views.ChatListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatActivity extends AppCompatActivity implements ChatListView {
    private static final String LOG_TAG = "ActionBarActivity";
    private static final String CHAT_DATA = "RAWCHATDATA";
    private ArrayList<ChatData> chatDataArrayList;
    private ChatsArrayAdapter chatsArrayAdapter;
    @BindView(R.id.chat_listview)
    ListView mListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            chatDataArrayList = serializeJsonFile();
        } else if (savedInstanceState.getParcelableArrayList(CHAT_DATA) != null){
            chatDataArrayList = savedInstanceState.getParcelableArrayList(CHAT_DATA);
        }

        chatsArrayAdapter = new ChatsArrayAdapter(this, chatDataArrayList);
        mListView.setAdapter(chatsArrayAdapter);
        setUpToolbar();
    }

    private void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.chat));
    }

    private ArrayList<ChatData> serializeJsonFile(){
        chatDataArrayList = new ArrayList<ChatData>();

        try {
            String chatFileData = loadChatFile();
            JSONObject jsonData = new JSONObject(chatFileData);
            JSONArray jsonArray = jsonData.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ChatData chatData = new ChatData(jsonObject);
                chatData.setUserID(jsonObject.getInt("user_id"));
                chatData.setUsername(jsonObject.getString("username"));
                chatData.setAvatarURL(jsonObject.getString("avatar_url"));
                chatData.setMessage(jsonObject.getString("message"));
                chatDataArrayList.add(chatData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chatDataArrayList;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (chatDataArrayList != null) {
            outState.putParcelableArrayList(CHAT_DATA, chatDataArrayList);
        }
    }

    private String loadChatFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.chat_data);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String receiveString;
        StringBuilder stringBuilder = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null) {
            stringBuilder.append(receiveString);
            stringBuilder.append("\n");
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();


        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
