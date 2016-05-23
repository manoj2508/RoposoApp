package com.example.manoj.roposoapp.model;

/**
 * Created by manoj on 23/05/16.
 */
public class UserData extends BaseDataTypeModel {

    private String id;
    private String username;
    private String handle;
    private String image;
    private String about;
    private int following;
    private int followers;
    private boolean is_following;
    private String type;
    private String url;


    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHandle() {
        return handle;
    }

    public String getImage() {
        return image;
    }

    public String getAbout() {
        return about;
    }

    public int getFollowing() {
        return following;
    }

    public int getFollowers() {
        return followers;
    }

    public boolean is_following() {
        return is_following;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    @Override
    CardDataType getCardType() {
        return CardDataType.USER;
    }
}
