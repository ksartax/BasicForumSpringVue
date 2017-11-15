package com.service;

import com.dao.GlobalStatisticsDao;
import com.models.Category;
import com.models.GlobalStatistic;
import com.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("globalStatisticsService")
@ComponentScan(value = "spring.dao")
@Transactional
public class GlobalStatisticsServiceImpl implements GlobalStatisticsService {

    private GlobalStatisticsDao globalStatisticsDao;
    private CategoriesService categoriesService;
    private PostsService postsService;

    @Autowired
    public GlobalStatisticsServiceImpl(
            GlobalStatisticsDao globalStatisticsDao,
            CategoriesService categoriesService,
            PostsService postsService
    ) {
        this.globalStatisticsDao = globalStatisticsDao;
        this.categoriesService = categoriesService;
        this.postsService = postsService;
    }

    public List<GlobalStatistic> getAll() {
        return globalStatisticsDao.getAll();
    }

    public void decrementCategory(int categoryId, int count) {
        Category category = this.categoriesService.get(categoryId);

        decrementCategory(1);
        decrementComments(category.getPostsCommentsCount());
        decrementPosts(category.getPostsCount());
    }

    private void decrementCategory(int count) {
        GlobalStatistic globalStatisticCategory = this.globalStatisticsDao.getByTitle("Kategorie");
        globalStatisticCategory.decrementCount(count);

        globalStatisticsDao.add(globalStatisticCategory);
    }

    public void incrementCategory(int count) {
        GlobalStatistic globalStatisticCategory = this.globalStatisticsDao.getByTitle("Kategorie");
        globalStatisticCategory.incrementCount(count);

        globalStatisticsDao.add(globalStatisticCategory);
    }

    public void decrementPosts(int postId, int count) {
        Post post = this.postsService.get(postId);

        decrementPosts(1);
        decrementComments(post.getCommentCount());
    }

    private void decrementPosts(int count) {
        GlobalStatistic globalStatisticPosts = this.globalStatisticsDao.getByTitle("Posty");
        globalStatisticPosts.decrementCount(count);

        globalStatisticsDao.add(globalStatisticPosts);
    }

    public void incrementPosts(int count) {
        GlobalStatistic globalStatisticPosts = this.globalStatisticsDao.getByTitle("Posty");
        globalStatisticPosts.incrementCount(count);

        globalStatisticsDao.add(globalStatisticPosts);
    }

    public void decrementComments(int count) {
        GlobalStatistic globalStatisticComment = this.globalStatisticsDao.getByTitle("Komentarze");
        globalStatisticComment.decrementCount(count);

        globalStatisticsDao.add(globalStatisticComment);
    }

    public void incrementComments(int count) {
        GlobalStatistic globalStatisticComment = this.globalStatisticsDao.getByTitle("Komentarze");
        globalStatisticComment.incrementCount(count);

        globalStatisticsDao.add(globalStatisticComment);
    }

    public GlobalStatistic getByTitle(String title) {
        return globalStatisticsDao.getByTitle(title);
    }
}
