package com.example.manoj.roposoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public StoryRecyclerAdapter(Context context, List<StoryData> storyDataList, IRecycerItemSelected callback) {
        this.context = context;
        this.storyDataList = storyDataList;
        this.callback = callback;
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
        UserData userData = DataStore.getInstance().getUserData(storyData.getDb());

        Picasso.with(context).load(storyData.getSi()).into(holder.storyImageView);
        holder.eventTitleTextView.setText(storyData.getTitle());
        holder.commentCountTextView.setText(context.getString(R.string.comment_count, storyData.getComment_count()));
        holder.likeCountTextView.setText(context.getString(R.string.likes_count, storyData.getLikes_count()));

        holder.nameTextView.setText(userData.getUsername());
        holder.userTagTextView.setText(storyData.getVerb());
        Picasso.with(context).load(userData.getImage()).into(holder.profileImageView);
    }

    @Override
    public int getItemCount() {
        return storyDataList.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView, userTagTextView;
        private ImageView profileImageView;
        private View followBtnView;

        private ImageView storyImageView;
        private TextView eventTitleTextView;
        private TextView likeCountTextView, commentCountTextView;

        public StoryHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.user_name);
            userTagTextView = (TextView) itemView.findViewById(R.id.user_handle);
            profileImageView = (ImageView) itemView.findViewById(R.id.profile_image);
            followBtnView = itemView.findViewById(R.id.follow_btn);

            eventTitleTextView = (TextView) itemView.findViewById(R.id.event_title);
            storyImageView = (ImageView) itemView.findViewById(R.id.story_image_view);
            likeCountTextView = (TextView) itemView.findViewById(R.id.likes_count);
            commentCountTextView = (TextView) itemView.findViewById(R.id.comment_count);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (callback != null) {
                callback.onItemClicked(storyDataList.get(getAdapterPosition()));
            }
        }
    }

    interface IRecycerItemSelected {
        void onItemClicked(StoryData storyData);
    }
}
