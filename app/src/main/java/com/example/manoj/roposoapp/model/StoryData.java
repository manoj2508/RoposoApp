package com.example.manoj.roposoapp.model;

import java.io.Serializable;

/**
 * Created by manoj on 23/05/16.
 */
public class StoryData extends BaseDataTypeModel implements Serializable {

    public static final String TYPE = CardDataType.USER.getType();

    private String id;
    private String db;
    private int comment_count;
    private int likes_count;
    private boolean like_flag;
    private String title;
    private String description;
    private String verb;
    private String type;
    private String url;
    private String si;

    private UserData userData;


    public String getId() {
        return id;
    }

    public String getDb() {
        return db;
    }

    public int getCommentCount() {
        return comment_count;
    }

    public int getLikesCount() {
        return likes_count;
    }

    public boolean isLikeFlag() {
        return like_flag;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVerb() {
        if (verb != null) {
            return verb.replace("created this story on ", "");
        }
        return verb;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getSi() {
        return si;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public CardDataType getCardType() {
        return CardDataType.STORY;
    }

    @Override
    public String getDataType() {
        return TYPE;
    }


    public void setLikeState(boolean likeState) {
        if (likeState && !like_flag) {
            likes_count++;
        } else if (!likeState && like_flag) {
            likes_count--;
        }
        this.like_flag = likeState;
    }
}
