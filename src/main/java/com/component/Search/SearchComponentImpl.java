package com.component.Search;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component("SearchComponent")
@Transactional
public class SearchComponentImpl implements SearchComponent {

    private final static String userQuery = "FROM User WHERE lower(username) LIKE lower(:search) OR lower(email) LIKE lower(:search)";
    private final static String categoryQuery = "FROM Category WHERE lower(title) LIKE lower(:search) OR lower(description) LIKE lower(:search)";
    private final static String postQuery = "FROM Post WHERE lower(title) LIKE lower(:search) OR lower(description) LIKE lower(:search)";

    private SessionFactory sessionFactory;
    private List<SearchInfection> searchObjects;

    @Autowired
    public SearchComponentImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List search(String search) {
        searchObjects = new ArrayList<SearchInfection>();

        searchObjects.addAll(
                setData(SearchComponentImpl.categoryQuery, search)
        );

        searchObjects.addAll(
                setData(SearchComponentImpl.postQuery, search)
        );

        searchObjects.addAll(
                setData(SearchComponentImpl.userQuery, search)
        );

        return searchObjects;
    }

    private List setData(String query, String search) {
        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        query
                )
                .setParameter("search", "%" + search + "%")
                .list();
    }
}
