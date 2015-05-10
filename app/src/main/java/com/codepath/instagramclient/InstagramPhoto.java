package com.codepath.instagramclient;

public class InstagramPhoto {

    private final static String TAG = "InstagramPhoto";

    public String username;
    public String userProfilePictureUrl;

    public String caption;
    public String imageUrl;
    public int imageHeight;
    public int numLikes;

    /**
     * What string the likes should output. This calculates where the "," should go, if needed
     * @return String
     */
    public String likesString() {

        int thousands = numLikes / 1000;
        int hundreds = numLikes % 1000;

        String displayLikes;
        String displayHundreds = Integer.toString(hundreds);

        if (hundreds < 100) {
            displayHundreds = "0" + displayHundreds;
        }
        if (hundreds < 10) {
            displayHundreds = "0" + displayHundreds;
        }

        if (hundreds == 0) {
            displayHundreds = "0";
        }

        if (thousands > 0) {
            displayLikes = thousands + "," + displayHundreds;
        } else {
            displayLikes = displayHundreds;
        }

        return displayLikes+" likes";
    }

}
