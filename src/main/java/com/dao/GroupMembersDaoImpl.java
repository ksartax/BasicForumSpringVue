package com.dao;

import com.models.GroupMember;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("GroupMembersDao")
@Transactional
public class GroupMembersDaoImpl extends AbstractDao<Integer, GroupMember> implements GroupMembersDao
{
    @SuppressWarnings("unchecked")
    public List<GroupMember> getAllByUser(int id) {
        return (List<GroupMember>) createEntityCriteria()
                .add(Restrictions.eq("user.id", id)).list();
    }
}
