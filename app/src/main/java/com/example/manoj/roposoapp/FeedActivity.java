package com.example.manoj.roposoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.manoj.roposoapp.model.StoryData;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private List<StoryData> storyDataList;
    private RecyclerView storyRecyclerView;
    private StoryRecyclerAdapter storyRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_story_card);

        storyRecyclerView = (RecyclerView) findViewById(R.id.story_recycler_view);

        storyDataList = new ArrayList<>();
        storyRecyclerAdapter = new StoryRecyclerAdapter(this, storyDataList);
        storyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        storyRecyclerView.setAdapter(storyRecyclerAdapter);
        storyRecyclerView.addItemDecoration(new StoryListDivider(this));

    }
}
