package com.service;

import com.dao.GroupMembersDao;
import com.models.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("groupMembersService")
@ComponentScan(value = "spring.dao")
@Transactional
public class GroupMembersServiceImpl implements GroupMembersService
{
    @Autowired
    private GroupMembersDao groupMembersDao;

    public List<GroupMember> getAllByUser(int id) {
        return groupMembersDao.getAllByUser(id);
    }
}
