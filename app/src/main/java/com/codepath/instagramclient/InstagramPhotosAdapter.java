package com.codepath.instagramclient;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    // What do we need from the activity?
    // resource specifies the layout for the item
    // Context, Data Source
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> photos) {
        super(context, android.R.layout.simple_list_item_1, photos);
    }

    //What out item looks like
    //Use template to display each photo


    private static class ViewHolder {
        ImageView profile;
        TextView username;
        TextView createdTime;
        ImageView photo;
        TextView likes;
        TextView caption;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get data item for position
        InstagramPhoto photo = getItem(position);

        ViewHolder viewHolder;

        // Check if using recycled view, if not inflate
        if (convertView == null) {
            // Create a new view
            // create within parent container
            // false->don't attach to parent
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);

            // lookup views for populating the data (image, caption)

            //viewHolder
            viewHolder = new ViewHolder();
            viewHolder.profile = (ImageView) convertView.findViewById(R.id.ivProfile);
            viewHolder.username = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.createdTime = (TextView) convertView.findViewById(R.id.tvCreatedTime);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.likes = (TextView) convertView.findViewById(R.id.tvLikes);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Insert the item data into each of the view items
        viewHolder.username.setText(photo.username);
        viewHolder.createdTime.setText(photo.createdTimeString());
        viewHolder.likes.setText(photo.likesString());
        viewHolder.caption.setText(photo.caption);

        // IMAGES
        // Clear out image view (if recycled)
        viewHolder.profile.setImageResource(0);
        viewHolder.photo.setImageResource(0);

        // Insert image using picasso
        Picasso.with(getContext()).load(photo.userProfilePictureUrl).placeholder(R.drawable.profile).into(viewHolder.profile);
        Picasso.with(getContext()).load(photo.imageUrl).placeholder(R.drawable.ic_instagram).into(viewHolder.photo);

        // Return the created item as a view
        return convertView;
    }


}
