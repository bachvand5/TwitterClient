package com.codepath.apps.VaniTwitter;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.VaniTwitter.models.Tweet;
import com.codepath.apps.VaniTwitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by thuynh6 on 3/27/2016.
 */
public class NewTweet extends DialogFragment {

    private TwitterClient client;
    private User user;
    OnClickListener eOnClickListener;

    public NewTweet() {

    }

    public static NewTweet newInstance(String title){
        NewTweet frag = new NewTweet();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    public interface OnClickListener {
        public void onTweetClick(Tweet tweet);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            eOnClickListener = (OnClickListener) activity;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        user = new User();
        client = TwitterApp.getRestClient();
        final View view = inflater.inflate(R.layout.fragment_new_tweet, container);
        client.getUserSetting(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user = User.fromJSON(response);
                Log.d("DEBUG", response.toString());
                ImageView ivNewTweetProfileImage = (ImageView) view.findViewById(R.id.ivNewTweetProfileImage);
                TextView tvNewTweetScreenName = (TextView) view.findViewById(R.id.tvNewTweetScreenName);
                TextView tvNewTweetName = (TextView) view.findViewById(R.id.tvNewTweetName);
                Button btNewTweet = (Button) view.findViewById(R.id.btNewTweet);
                final EditText etNewTweetBody = (EditText) view.findViewById(R.id.etNewTweetBody);

                tvNewTweetName.setText(user.getName());
                tvNewTweetScreenName.setText(user.getScreenName());
                Picasso.with(view.getContext()).load(user.getProfileImageUrl()).into(ivNewTweetProfileImage);
                btNewTweet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        client.postNewTweet(new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                Tweet tweet = new Tweet();
                                tweet = Tweet.fromJSON(response);
                                eOnClickListener.onTweetClick(tweet);
                            }
                        }, etNewTweetBody.getText().toString());
                        getDialog().dismiss();
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("DEBUG", errorResponse.toString());
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
