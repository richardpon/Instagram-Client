package com.codepath.instagramclient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstagramPhotoFactory {
    private final static String TAG = "InstagramPhotoFactory";


    //                -Response:
//                - TYPE: {"data" => [x] => "type"} ("image" or "video")
//                - caption: {"data" => [x] => "caption" => "text"}
//                - url: {"data" => [x] => "images" => "standard_resolution" => "url"}
//                - username: {"data" => [x] => "user" => "username"}
//                - profile_picture: {"data" => [x] => "user" => "profile_picture"}
//                - created_time: {"data" => [x] => "created_time"}
    public ArrayList<InstagramPhoto> getInstagramPhotosFromJsonArray(JSONArray photosJSON) {

        ArrayList<InstagramPhoto> photos = new ArrayList<>();

        try {

            //iterate
            for (int i = 0 ; i < photosJSON.length() ; i++) {
                //get JSON object
                JSONObject photoJSON = photosJSON.getJSONObject(i);

                //decode attributes
                InstagramPhoto photo = getInstgramPhotoFromJsonObject(photoJSON);

                photos.add(photo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return photos;
    }

    /**
     * @param photoJSON
     * @return
     */
    private InstagramPhoto getInstgramPhotoFromJsonObject(JSONObject photoJSON) {

        InstagramPhoto photo = new InstagramPhoto();
        try {
            photo.username = photoJSON.getJSONObject("user").getString("username");
            photo.userProfilePictureUrl = photoJSON.getJSONObject("user").getString("profile_picture");

            photo.imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
            photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
            photo.caption = photoJSON.getJSONObject("caption").getString("text");
            photo.numLikes = photoJSON.getJSONObject("likes").getInt("count");
            photo.createdTime = photoJSON.getInt("created_time");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return photo;
    }

}
