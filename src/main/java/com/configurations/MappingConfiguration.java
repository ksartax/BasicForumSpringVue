package com.configurations;

import com.dvo.CategoryView;
import com.dvo.CommentView;
import com.dvo.PostView;
import com.dvo.UserView;
import com.models.Category;
import com.models.Comment;
import com.models.Post;
import com.models.User;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(User.class, UserView.class);

                mapping(Post.class, PostView.class)
                        .fields("user", "userView")
                        .fields("category", "categoryView");

                mapping(Category.class, CategoryView.class)
                        .fields("user", "userView");

                mapping(Comment.class, CommentView.class)
                        .fields("user", "userView")
                        .fields("post", "postView");
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

        dozerBeanMapper.addMapping(beanMappingBuilder());

        return dozerBeanMapper;
    }
}
