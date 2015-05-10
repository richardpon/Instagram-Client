package com.codepath.instagramclient;


import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstagramNetworkClient {
    private static final String CLIENT_ID = "320c04755d82435f839f9b76d8ddc7e2";

    private static final String TAG = "InstagramNetworkClient";
    private Context context;

    public ArrayList<InstagramPhoto> photos;


    public InstagramNetworkClient(Context context) {
        this.context = context;
    }

    // API Request
    /**
     * client id 320c04755d82435f839f9b76d8ddc7e2
     * -Popular: https://api.instagram.com/v1/media/popular?access_token=ACCESS-TOKEN
     */
    public void fetchPopularPhotos() {

        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, null, new JsonHttpResponseHandler() {

            //onSuccess (worked! 200)
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //expecting a JSON object

                try {

                    JSONArray photosJSON = null;
                    //array of posts
                    photosJSON = response.getJSONArray("data");
                    InstagramPhotoFactory factory = new InstagramPhotoFactory();

                    photos = factory.getInstagramPhotosFromJsonArray(photosJSON);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                PhotosActivity photosActivity = (PhotosActivity) context;
                photosActivity.addPhotos(photos);
            }


            //onFailure (fail)
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // Ignore it!
            }
        });
    }


}
