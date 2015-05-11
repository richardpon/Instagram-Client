package com.codepath.instagramclient;

public class InstagramPhoto {

    private final static String TAG = "InstagramPhoto";

    public String username;
    public String userProfilePictureUrl;

    public String caption;
    public String imageUrl;
    public int imageHeight;
    public int numLikes;
    public int createdTime;

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

    /**
     * Creates and returns a string to represent when an image was created
     * @return String
     */
    public String createdTimeString()
    {
        // Current Time
        long unixTimeNow = System.currentTimeMillis() / 1000L;

        // diff betweeen current and created time
        int diff = (int)unixTimeNow - createdTime;

        //Convert GMT to PST
        int diffLocal = diff - 60 * 60 * 7;

        //calculate display time
        return getDisplayTime(diffLocal);
    }

    /**
     * Creates a display time in the format 10w, 6d, 20h, or 55m
     * @param timeDiffSeconds int
     * @return String
     */
    private String getDisplayTime(int timeDiffSeconds) {

        // Weeks
        int numWeeks = timeDiffSeconds / (60 * 60 * 24 * 7);

        if (numWeeks > 0) {
            return numWeeks+"w";
        }

        // Days
        int numDays = timeDiffSeconds / (60 * 60 * 24);

        if (numDays > 0) {
            return numDays+"d";
        }

        // Hours
        int numHours = timeDiffSeconds / (60 * 60);

        if (numHours > 0) {
            return numHours+"h";
        }

        // Minutes
        int numMinutes = timeDiffSeconds / (60);

        return numMinutes+"m";
    }

}
