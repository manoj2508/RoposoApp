package com.example.manoj.roposoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.model.UserData;
import com.squareup.picasso.Picasso;

public class StoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        StoryData storyData = (StoryData) getIntent().getExtras().getSerializable(Constants.EXTRA_STORY_DATA);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(storyData.getSi()).into(imageView);

        TextView eventTitle = (TextView) findViewById(R.id.event_title);
        TextView eventDescription = (TextView) findViewById(R.id.event_description);

        eventTitle.setText(storyData.getTitle());
        eventDescription.setText(storyData.getDescription());


        TextView nameTextView = (TextView) findViewById(R.id.user_name);
        TextView userTagTextView = (TextView) findViewById(R.id.user_handle);
        ImageView profileImageView = (ImageView) findViewById(R.id.profile_image);
        TextView followersCountTextView = (TextView) findViewById(R.id.followers_count);
        TextView followingCountTextView = (TextView) findViewById(R.id.following_count);

        UserData userData = DataStore.getInstance().getUserData(storyData.getDb());
        nameTextView.setText(userData.getUsername());
        userTagTextView.setText(userData.getHandle());
        Picasso.with(this).load(userData.getImage()).into(profileImageView);
        followersCountTextView.setText(getString(R.string.followers_count, userData.getFollowers()));
        followingCountTextView.setText(getString(R.string.following_count, userData.getFollowing()));

    }
}
