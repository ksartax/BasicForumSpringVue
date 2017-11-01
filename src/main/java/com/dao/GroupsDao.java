package com.dao;

import com.models.Group;
import java.util.List;

public interface GroupsDao
{
    public List<Group> getAll();
    public Group add(Group group);
}
