package com.example.manoj.roposoapp.utils;

import android.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

public class DataTypeAdapterFactory implements TypeAdapterFactory {

    private Map<String, TypeAdapter<? extends DataTypeProvider>> typeAdapterMap = new ArrayMap<>();
    private TypeAdapter<JsonElement> jsonElementAdapter;
    private TypeAdapter<String> typeIdAdapter;
    private Map<String, Class<? extends DataTypeProvider>> typeIdToClassMap;
    private String jsonKeyForTypeId;

    public DataTypeAdapterFactory(
            final Map<String, Class<? extends DataTypeProvider>> typeIdToClassMap,
            final String jsonKeyForTypeId) {
        this.typeIdToClassMap = typeIdToClassMap;
        this.jsonKeyForTypeId = jsonKeyForTypeId;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        TypeAdapter<T> adapter = null;
        if (DataTypeProvider.class.isAssignableFrom(type.getRawType()) == true) {
            for (String key : typeIdToClassMap.keySet()) {
                typeAdapterMap.put(key, gson.getDelegateAdapter(this, TypeToken
                        .get(typeIdToClassMap.get(key))));
            }
            jsonElementAdapter = gson.getAdapter(JsonElement.class);
            typeIdAdapter = gson.getAdapter(String.class);
            adapter = (TypeAdapter<T>) new DataTypeAdapter().nullSafe();
        }
        return adapter;
    }

    private class DataTypeAdapter extends TypeAdapter<DataTypeProvider> {

        @Override
        public void write(JsonWriter out, DataTypeProvider value) throws IOException {
            String dataType = value.getDataType();
            TypeAdapter<? extends DataTypeProvider> adapter = typeAdapterMap.get(dataType);
            if (null != adapter) {
                writeTyped(adapter, out, value);
                return;
            }
        }

        private <T extends DataTypeProvider> void writeTyped(
                TypeAdapter<? extends DataTypeProvider> adapter, JsonWriter out, T value)
                throws IOException {
            ((TypeAdapter<T>) adapter).write(out, value);
        }

        @Override
        public DataTypeProvider read(JsonReader in) throws IOException {
            JsonObject objectJson = jsonElementAdapter.read(in).getAsJsonObject();
            JsonElement typeJson = objectJson.get(jsonKeyForTypeId);
            String dataType = typeIdAdapter.fromJsonTree(typeJson);
            TypeAdapter<? extends DataTypeProvider> adapter = typeAdapterMap.get(dataType);
            if (null != adapter) {
                return adapter.fromJsonTree(objectJson);
            }
            return null;
        }
    }


}
