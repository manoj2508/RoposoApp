package com.example.manoj.roposoapp;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by manoj on 24/05/16.
 */
public class UserProfileManager {
    private static UserProfileManager instance;

    private Set<String> followersId;
    private Set<String> likesSet;

    public UserProfileManager() {
        followersId = new HashSet<>();
        likesSet = new HashSet<>();
    }

    public synchronized static UserProfileManager getInstance() {
        if (instance == null) {
            instance = new UserProfileManager();
        }
        return instance;
    }

    public void addLikesId(String id) {
        if (!likesSet.contains(id)) {
            likesSet.add(id);
        }
    }

    public void addFollowerId(String id) {
        if (!followersId.contains(id)) {
            followersId.add(id);
        }
    }

    public boolean isFollowing(String id) {
        return followersId.contains(id);
    }

    public boolean isLiked(String id) {
        return likesSet.contains(id);
    }

    public void updateLikeStatus(String id, boolean following) {
        if (!following && isLiked(id)) {
            likesSet.remove(id);
        } else if (following && !isLiked(id)) {
            addLikesId(id);
        }
    }

    public void updateFollower(String id, boolean following) {
        if (!following && isFollowing(id)) {
            followersId.remove(id);
        } else if (following && !isFollowing(id)) {
            addFollowerId(id);
        }
    }
}
