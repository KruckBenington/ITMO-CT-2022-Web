package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (Strings.isNullOrEmpty(title)) {
            throw new ValidationException("Title is required");
        }

        if (title.length() > 40) {
            throw new ValidationException("Title can't be longer than 40 letters");
        }

        if (Strings.isNullOrEmpty(text)) {
            throw new ValidationException("Text is required");
        }
    }

    public void createArticle(Article article, User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("Not logged user cannot post article");
        }
        articleRepository.save(article, user);
    }

    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public List<Article> findAllNotHidden(){
        return articleRepository.findAllNotHidden();
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public void changeArticleHidden(long id) {
        articleRepository.changeHidden(id);
    }

    public List<Article> findAllByUserId(long userId){
        return articleRepository.findAllByUserId(userId);
    }

}
