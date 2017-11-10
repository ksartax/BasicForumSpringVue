package com.dao;

import com.models.Friend;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("FriendsDao")
@Transactional
public class FriendsDaoImpl extends AbstractDao<Integer, Friend> implements FriendsDao
{
    @SuppressWarnings("unchecked")
    public List<Friend> getAllByUser(int id)
    {
        return (List<Friend>) createEntityCriteria()
                .add(
                        Restrictions.eq("myUser.id", id)
                )
                .list();
    }
}
