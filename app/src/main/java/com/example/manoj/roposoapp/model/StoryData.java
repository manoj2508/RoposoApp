package com.example.manoj.roposoapp.model;

/**
 * Created by manoj on 23/05/16.
 */
public class StoryData extends BaseDataTypeModel {

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


    public String getId() {
        return id;
    }

    public String getDb() {
        return db;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public boolean isLike_flag() {
        return like_flag;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVerb() {
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

    @Override
    public CardDataType getCardType() {
        return CardDataType.STORY;
    }

    @Override
    public String getDataType() {
        return TYPE;
    }
}
