package com.codepath.apps.VaniTwitter;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.VaniTwitter.models.Tweet;
import com.codepath.apps.VaniTwitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity implements NewTweet.OnClickListener {
    private TweetArrayAdapter aTweets;
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private RecyclerView lvTweets;
    private User user;

    public User getUser() {
        return user;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        MenuItem searchItem = menu.findItem(R.id.action_tweet);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_tweet:
                // User chose the "Settings" item, show the app settings UI...
                showNewTweetDialog();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        lvTweets = (RecyclerView) findViewById(R.id.lvTweets);

        tweets = new ArrayList<>();
        aTweets = new TweetArrayAdapter(this, tweets);
        lvTweets.setAdapter(aTweets);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        lvTweets.setLayoutManager(gridLayoutManager);
        lvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });

        client = TwitterApp.getRestClient();
        user = new User();
        populateTimeline(25, 1);
    }

    private void populateTimeline(int count, int since) {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                tweets.addAll(Tweet.fromJSONArray(json));
                aTweets.notifyDataSetChanged();
                //Log.d("DEBUG", json.toString());
                //Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
                //Toast.makeText(getBaseContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        }, count, since);
    }

    private void showNewTweetDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        NewTweet newTweetDialog = NewTweet.newInstance("New Tweet");
        newTweetDialog.show(ft, "fragment_new_tweet");

    }

    @Override
    public void onTweetClick(Tweet tweet) {
        //tweets.add(tweet);
        //aTweets.clear();
        tweets.add(0, tweet);
        aTweets.notifyItemInserted(0);
        lvTweets.scrollToPosition(0);
    }

    public void customLoadMoreDataFromApi(int offset) {
        // Send an API request to retrieve appropriate data using the offset value as a parameter.
        // Deserialize API response and then construct new objects to append to the adapter
        // Add the new objects to the data source for the adapter
        populateTimeline(25, offset);
        //tweets.addAll(moreItems);
        // For efficiency purposes, notify the adapter of only the elements that got changed
        // curSize will equal to the index of the first element inserted because the list is 0-indexed
        //int curSize = aTweets.getItemCount();
        //aTweets.notifyItemRangeInserted(curSize, tweets.size() - 1);
    }
}
