package com.example.manoj.roposoapp.utils;

import com.example.manoj.roposoapp.model.CardDataType;
import com.example.manoj.roposoapp.model.StoryData;
import com.example.manoj.roposoapp.model.UserData;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataParserTemplateAdapter extends DataTypeAdapterFactory {

    private static final String GSON_MAPPING_KEY = "type";

    private static final Map<String, Class<? extends DataTypeProvider>> classMappingKeyValue
            = new LinkedHashMap<String, Class<? extends DataTypeProvider>>() {
        {
            put(CardDataType.USER.getType(), UserData.class);
            put(CardDataType.STORY.getType(), StoryData.class);
        }
    };

    public DataParserTemplateAdapter() {
        super(classMappingKeyValue, GSON_MAPPING_KEY);
    }
}