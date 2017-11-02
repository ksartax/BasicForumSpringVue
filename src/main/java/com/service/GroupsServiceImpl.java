package com.service;

import com.dao.GroupsDao;
import com.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service("groupsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class GroupsServiceImpl implements GroupsService
{
    @Autowired
    private GroupsDao groupsDao;

    public List<Group> getAll(int limit) {
        return groupsDao.getAll(limit);
    }

    public Group add(Group group) {
        return groupsDao.add(group);
    }
}
