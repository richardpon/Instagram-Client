package com.codepath.instagramclient;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class PhotosActivity extends ActionBarActivity {

    private final static String TAG = "PhotosActivity";
    public ArrayList<InstagramPhoto> photos;
    public InstagramPhotosAdapter aPhotos;
    private InstagramNetworkClient instagramNetworkClient;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                instagramNetworkClient.fetchPopularPhotos();
            }
        });

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

        // Clear any previous photos
        aPhotos.clear();

        // Add new photos
        for (int i = 0 ; i < newPhotos.size() ; i++) {
            photos.add(newPhotos.get(i));

            Log.i(TAG, "author="+newPhotos.get(i).username);
        }

        // Tell adapter that there is new data
        aPhotos.notifyDataSetChanged();

        // Done refreshing (if applicable)
        swipeContainer.setRefreshing(false);
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
