package com.example.manoj.roposoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.model.UserData;
import com.squareup.picasso.Picasso;

public class StoryDetailActivity extends AppCompatActivity {

    private CheckedTextView followBtnView;
    private UserData userData;
    private StoryData storyData;
    private UserProfileManager userProfileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userProfileManager = UserProfileManager.getInstance();
        setContentView(R.layout.activity_story_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //story basic data
        String id = getIntent().getExtras().getString(Constants.EXTRA_STORY_DATA);
        storyData = DataStore.getInstance().getStory(id);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(storyData.getSi()).into(imageView);

        TextView eventTitle = (TextView) findViewById(R.id.event_title);
        TextView eventDescription = (TextView) findViewById(R.id.event_description);

        eventTitle.setText(storyData.getTitle());
        eventDescription.setText(storyData.getDescription());


        //update user info
        TextView nameTextView = (TextView) findViewById(R.id.user_name);
        TextView userTagTextView = (TextView) findViewById(R.id.user_handle);
        ImageView profileImageView = (ImageView) findViewById(R.id.profile_image);
        final TextView followersCountTextView = (TextView) findViewById(R.id.followers_count);
        TextView followingCountTextView = (TextView) findViewById(R.id.following_count);
        followBtnView = (CheckedTextView) findViewById(R.id.follow_btn);

        userData = storyData.getUserData();
        nameTextView.setText(userData.getUsername());
        userTagTextView.setText(userData.getHandle());
        Picasso.with(this).load(userData.getImage()).into(profileImageView);
        followersCountTextView.setText(getString(R.string.followers_count, userData.getFollowers()));
        followingCountTextView.setText(getString(R.string.following_count, userData.getFollowing()));

        //follow btn view
        updateFollowingState();
        followBtnView.setChecked(userData.isFollowing());
        followBtnView.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 userData.toggleFollowingState();
                                                 followBtnView.setChecked(userData.isFollowing());
                                                 userProfileManager.updateFollower(userData.getId(), userData.isFollowing());
                                                 followersCountTextView.setText(getString(R.string.followers_count, userData.getFollowers()));
                                             }
                                         }
        );

        //like comment views
        updateLikeState();
        CheckBox likeBtnView = (CheckBox) findViewById(R.id.like_btn);
        final TextView likeCountTextView = (TextView) findViewById(R.id.likes_count);
        TextView commentCountTextView = (TextView) findViewById(R.id.comment_count);
        likeCountTextView.setText(getString(R.string.likes_count, storyData.getLikesCount()));
        commentCountTextView.setText(getString(R.string.comment_count, storyData.getCommentCount()));
        likeBtnView.setChecked(storyData.isLikeFlag());

        likeBtnView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userProfileManager.updateLikeStatus(storyData.getId(), isChecked);
                storyData.setLikeState(isChecked);
                likeCountTextView.setText(getString(R.string.likes_count, storyData.getLikesCount()));
            }
        });

    }

    private void updateFollowingState() {
        userData.setIsFollowing(userProfileManager.isFollowing(userData.getId()));
    }

    private void updateLikeState() {
        storyData.setLikeState(userProfileManager.isLiked(storyData.getId()));
    }
}
