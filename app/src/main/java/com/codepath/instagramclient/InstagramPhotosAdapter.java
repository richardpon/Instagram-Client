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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get data item for position
        InstagramPhoto photo = getItem(position);

        // Check if using recycled view, if not inflate
        if (convertView == null) {
            // Create a new view
            // create within parent container
            // false->don't attach to parent
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        // lookup views for populating the data (image, caption)
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);

        // Insert the item data into each of the view items
        tvCaption.setText(photo.caption);

        // Clear out image view (if recycled)
        ivPhoto.setImageResource(0);
        // Insert image using picasso
        Picasso.with(getContext()).load(photo.imageUrl).placeholder(R.drawable.ic_instagram).into(ivPhoto);

        // Return the created item as a view
        return convertView;
    }


}
