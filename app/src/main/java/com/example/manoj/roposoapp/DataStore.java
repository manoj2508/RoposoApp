package com.example.manoj.roposoapp;

import com.example.manoj.roposoapp.model.BaseDataTypeModel;
import com.example.manoj.roposoapp.model.CardDataType;
import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.model.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 24/05/16.
 */
public class DataStore {

    private static DataStore instance;
    private List<UserData> userDataList;
    private List<StoryData> storyDataList;

    public DataStore() {
        userDataList = new ArrayList<>();
        storyDataList = new ArrayList<>();
    }

    public synchronized static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void updateDataSet(List<BaseDataTypeModel> baseDataTypeModels) {
        for (BaseDataTypeModel data : baseDataTypeModels) {
            if (data.getCardType() == CardDataType.USER) {
                userDataList.add((UserData) data);
            } else if (data.getCardType() == CardDataType.STORY) {
                storyDataList.add((StoryData) data);
            }
        }
    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public List<StoryData> getStoryDataList() {
        return storyDataList;
    }

    public UserData getUserData(String id) {
        for (UserData userData : userDataList) {
            if (id.equalsIgnoreCase(userData.getId())) {
                return userData;
            }
        }
        return null;
    }
}
