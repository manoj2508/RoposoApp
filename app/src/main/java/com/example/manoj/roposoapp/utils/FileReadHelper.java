package com.example.manoj.roposoapp.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by manoj on 23/05/16.
 */
public class FileReadHelper {

    private static final String TAG = "FileReadHelper";
    private Context context;

    public FileReadHelper(Context context) {
        this.context = context;
    }

    private String readDataFromInputStream(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                total.append(line);
            }
            return total.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getDataFromRaw(int fileId) {
        try {
            InputStream resourceReader = context.getResources().openRawResource(fileId);
            String result = readDataFromInputStream(resourceReader);
            resourceReader.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <M> List<M> getModel(int fileId, Class<M> className) {
        String data = getDataFromRaw(fileId);
        //TODO: create parsing logic
        return null;
    }

}
