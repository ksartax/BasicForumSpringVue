package com.service;

import com.models.Comment;
import com.models.Friend;

import java.util.List;

public interface FriendsService
{
    public List<Friend> getAllByUser(int id);
}
