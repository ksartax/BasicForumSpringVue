package com.dao;

import com.models.Group;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("GroupsDao")
@Transactional
public class GroupsDaoImpl extends AbstractDao<Integer, Group> implements GroupsDao
{
    @SuppressWarnings("unchecked")
    public List<Group> getAll()
    {
        return (List<Group>) createEntityCriteria().list();
    }

    public Group add(Group group) {
        this.persist(group);

        return group;
    }
}
