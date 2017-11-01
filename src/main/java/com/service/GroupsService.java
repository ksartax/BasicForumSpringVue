package com.service;

import com.models.Group;
import java.util.List;

public interface GroupsService {
    public List<Group> getAll();
    public Group add(Group group);
}
