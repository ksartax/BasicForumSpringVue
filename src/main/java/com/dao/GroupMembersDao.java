package com.dao;

import com.models.GroupMember;
import java.util.List;

public interface GroupMembersDao
{
    public List<GroupMember> getAllByUser(int id);
}
