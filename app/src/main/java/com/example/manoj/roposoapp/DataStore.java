package com.example.manoj.roposoapp;

import com.example.manoj.roposoapp.model.BaseDataTypeModel;
import com.example.manoj.roposoapp.model.CardDataType;
import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.model.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manoj on 24/05/16.
 */
public class DataStore {

    private static DataStore instance;
    private Map<String, UserData> userDataMap;
    private List<StoryData> storyDataList;

    public DataStore() {
        userDataMap = new HashMap<>();
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
                UserData userData = (UserData) data;
                userDataMap.put(userData.getId(), userData);
            } else if (data.getCardType() == CardDataType.STORY) {
                storyDataList.add((StoryData) data);
            }
        }

        for (BaseDataTypeModel data : baseDataTypeModels) {
            if (data.getCardType() == CardDataType.USER) {
                UserData userData = (UserData) data;
                userDataMap.put(userData.getId(), userData);
            } else if (data.getCardType() == CardDataType.STORY) {
                storyDataList.add((StoryData) data);
            }
        }

        for (StoryData storyData : storyDataList) {
            storyData.setUserData(userDataMap.get(storyData.getDb()));
        }
    }

    public List<StoryData> getStoryDataList() {
        return storyDataList;
    }

    public StoryData getStory(String id) {
        for (StoryData storyData : storyDataList) {
            if (storyData.getId().equalsIgnoreCase(id)) {
                return storyData;
            }
        }
        return null;
    }
}
