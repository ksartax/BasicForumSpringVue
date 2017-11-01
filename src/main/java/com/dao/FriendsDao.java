package com.dao;

import com.models.Friend;
import com.models.User;

import java.util.List;

public interface FriendsDao
{
    public List<Friend> getAllByUser(int id);
}
