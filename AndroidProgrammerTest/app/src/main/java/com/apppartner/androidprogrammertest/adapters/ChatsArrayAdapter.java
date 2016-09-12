package com.apppartner.androidprogrammertest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;
import com.apppartner.androidprogrammertest.models.ChatData;
import com.apppartner.androidprogrammertest.views.activities.custom.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 12/23/14.
 *
 * @author Thomas Colligan
 */
public class ChatsArrayAdapter extends ArrayAdapter<ChatData>
{
    public ChatsArrayAdapter(Context context, List<ChatData> objects)
    {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatCell chatCell;
        ChatData data = getItem(position);
        if (convertView == null) {
            chatCell = new ChatCell();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cell_chat, parent, false);

            chatCell.usernameTextView = (TextView) convertView.findViewById(R.id.username_text);
            chatCell.messageTextView = (TextView) convertView.findViewById(R.id.message_text);
            chatCell.avatarImageView = (ImageView)convertView.findViewById(R.id.user_avatar);

            convertView.setTag(chatCell);

        } else {
            chatCell = (ChatCell)convertView.getTag();
        }

        chatCell.usernameTextView.setText(data.username);
        chatCell.messageTextView.setText(data.message);
        Picasso.with(getContext()).load(data.getAvatarURL())
                .transform(new CircleTransform())
                .into(chatCell.avatarImageView);

        return convertView;

    }

    private static class ChatCell
    {
        ImageView avatarImageView;
        TextView usernameTextView;
        TextView messageTextView;
    }
}
