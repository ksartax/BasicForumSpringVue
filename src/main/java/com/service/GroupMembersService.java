package com.service;

import com.models.GroupMember;
import java.util.List;

public interface GroupMembersService
{
    public List<GroupMember> getAllByUser(int id);
}
