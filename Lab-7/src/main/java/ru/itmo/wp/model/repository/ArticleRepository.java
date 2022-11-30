package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface ArticleRepository {

    void save(Article article, User user);

    void changeHidden(long id);

    Article find(long id);

    List<Article> findAll();

    List<Article> findAllNotHidden();
    
    List<Article> findAllByUserId(long userId);
}
