package com.example.manoj.roposoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.manoj.roposoapp.model.BaseDataTypeModel;
import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.utils.FileReadHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FeedActivity extends AppCompatActivity implements StoryRecyclerAdapter.IRecycerItemSelected {

    private List<StoryData> storyDataList;
    private RecyclerView storyRecyclerView;
    private StoryRecyclerAdapter storyRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);


        //updating data store
        DataStore.getInstance().updateDataSet(fetctCardsData());

        storyRecyclerView = (RecyclerView) findViewById(R.id.story_recycler_view);
        storyDataList = DataStore.getInstance().getStoryDataList();

        storyRecyclerAdapter = new StoryRecyclerAdapter(this, storyDataList, this);
        storyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        storyRecyclerView.setAdapter(storyRecyclerAdapter);
    }

    private List<BaseDataTypeModel> fetctCardsData() {
        FileReadHelper fileReadHelper = new FileReadHelper(this);
        String data = fileReadHelper.getDataFromRaw(R.raw.api_data);
        Type autocompleteList = new TypeToken<List<BaseDataTypeModel>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(data, autocompleteList);
    }

    @Override
    public void onItemClicked(StoryData storyData) {
        Intent intent = new Intent(this, StoryDetailActivity.class);
        intent.putExtra(Constants.EXTRA_STORY_DATA, storyData);
        startActivity(intent);
    }
}
