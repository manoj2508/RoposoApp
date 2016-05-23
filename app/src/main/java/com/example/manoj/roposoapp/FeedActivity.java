package com.example.manoj.roposoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.manoj.roposoapp.model.BaseDataTypeModel;
import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.utils.FileReadHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private List<StoryData> storyDataList;
    private RecyclerView storyRecyclerView;
    private StoryRecyclerAdapter storyRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        //updating data store
        DataStore.getInstance().updateDataSet(fetctCardsData());

        storyRecyclerView = (RecyclerView) findViewById(R.id.story_recycler_view);
        storyDataList = DataStore.getInstance().getStoryDataList();

        storyRecyclerAdapter = new StoryRecyclerAdapter(this, storyDataList);
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
}
