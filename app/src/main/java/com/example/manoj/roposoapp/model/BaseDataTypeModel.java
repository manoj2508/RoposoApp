package com.example.manoj.roposoapp.model;

import com.example.manoj.roposoapp.utils.DataParserTemplateAdapter;
import com.example.manoj.roposoapp.utils.DataTypeProvider;
import com.google.gson.annotations.JsonAdapter;

/**
 * Created by manoj on 23/05/16.
 */
@JsonAdapter(DataParserTemplateAdapter.class)
public abstract class BaseDataTypeModel implements DataTypeProvider {

    public abstract CardDataType getCardType();
}
