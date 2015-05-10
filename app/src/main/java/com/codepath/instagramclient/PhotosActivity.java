package com.codepath.instagramclient;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class PhotosActivity extends ActionBarActivity {

    public ArrayList<InstagramPhoto> photos;
    public InstagramPhotosAdapter aPhotos;
    private InstagramNetworkClient instagramNetworkClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        photos = new ArrayList<>();

        instagramNetworkClient = new InstagramNetworkClient(this);

        // Create adapter and link to source
        aPhotos = new InstagramPhotosAdapter(this, photos);

        // Find the listview
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);

        // Set the adapter binding to listView
        lvPhotos.setAdapter(aPhotos);

        //fetch photos
        instagramNetworkClient.fetchPopularPhotos();
    }

    /**
     * Callback for fetching popular photos
     * @param newPhotos ArrayList<InstagramPhoto>
     */
    public void addPhotos(ArrayList<InstagramPhoto> newPhotos) {
        for (int i = 0 ; i < newPhotos.size() ; i++) {
            photos.add(newPhotos.get(i));
        }

        aPhotos.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
