package com.example.manoj.roposoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.model.UserData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by manoj on 23/05/16.
 */
public class StoryRecyclerAdapter extends RecyclerView.Adapter<StoryRecyclerAdapter.StoryHolder> {

    private Context context;
    private List<StoryData> storyDataList;
    private IRecycerItemSelected callback;
    private UserProfileManager userProfileManager;


    public StoryRecyclerAdapter(Context context, List<StoryData> storyDataList, IRecycerItemSelected callback) {
        this.context = context;
        this.storyDataList = storyDataList;
        this.callback = callback;
        this.userProfileManager = UserProfileManager.getInstance();
    }


    @Override
    public StoryRecyclerAdapter.StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_story_card, parent, false);
        StoryHolder storyHolder = new StoryHolder(v);
        return storyHolder;
    }

    @Override
    public void onBindViewHolder(StoryRecyclerAdapter.StoryHolder holder, int position) {
        StoryData storyData = storyDataList.get(position);
        UserData userData = storyData.getUserData();
        storyData.setLikeState(userProfileManager.isLiked(storyData.getId()));
        Picasso.with(context).load(storyData.getSi()).into(holder.storyImageView);
        holder.eventTitleTextView.setText(storyData.getTitle());
        holder.commentCountTextView.setText(context.getString(R.string.comment_count, storyData.getCommentCount()));
        holder.likeCountTextView.setText(context.getString(R.string.likes_count, storyData.getLikesCount()));
        holder.likeBtnView.setChecked(storyData.isLikeFlag());

        if (userData != null) {
            holder.nameTextView.setText(userData.getUsername());
            holder.userTagTextView.setText(storyData.getVerb());
            Picasso.with(context).load(userData.getImage()).into(holder.profileImageView);

            userData.setIsFollowing(userProfileManager.isFollowing(userData.getId()));
            holder.followBtnView.setChecked(userData.isFollowing());
        }
    }

    @Override
    public int getItemCount() {
        return storyDataList.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        private TextView nameTextView, userTagTextView;
        private ImageView profileImageView;
        private CheckedTextView followBtnView;

        private ImageView storyImageView;
        private TextView eventTitleTextView;
        private TextView likeCountTextView, commentCountTextView;
        private CheckBox likeBtnView;

        public StoryHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.user_name);
            userTagTextView = (TextView) itemView.findViewById(R.id.user_handle);
            profileImageView = (ImageView) itemView.findViewById(R.id.profile_image);
            followBtnView = (CheckedTextView) itemView.findViewById(R.id.follow_btn);

            eventTitleTextView = (TextView) itemView.findViewById(R.id.event_title);
            storyImageView = (ImageView) itemView.findViewById(R.id.story_image_view);
            likeCountTextView = (TextView) itemView.findViewById(R.id.likes_count);
            commentCountTextView = (TextView) itemView.findViewById(R.id.comment_count);
            likeBtnView = (CheckBox) itemView.findViewById(R.id.like_btn);
            likeBtnView.setOnCheckedChangeListener(this);
            itemView.setOnClickListener(this);

            followBtnView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (R.id.follow_btn == v.getId()) {
                UserData userData = storyDataList.get(getAdapterPosition()).getUserData();
                userData.toggleFollowingState();
                followBtnView.setChecked(userData.isFollowing());
                userProfileManager.updateFollower(userData.getId(), userData.isFollowing());
                callback.onFollowBtnClick(getAdapterPosition());
            } else {
                callback.onItemClicked(storyDataList.get(getAdapterPosition()));
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            userProfileManager.updateLikeStatus(storyDataList.get(getAdapterPosition()).getId(), isChecked);
            storyDataList.get(getAdapterPosition()).setLikeState(isChecked);
            likeCountTextView.setText(context.getString(R.string.likes_count, storyDataList.get(getAdapterPosition()).getLikesCount()));
        }
    }

    interface IRecycerItemSelected {
        void onItemClicked(StoryData storyData);

        void onFollowBtnClick(int position);
    }
}
