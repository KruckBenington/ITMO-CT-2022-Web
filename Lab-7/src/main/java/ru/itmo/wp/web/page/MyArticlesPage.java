package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        long userId =  ((User)(request.getSession().getAttribute("user"))).getId();
        view.put("articles", articleRepository.findAllByUserId(userId));
    }


    private void changeHidden(HttpServletRequest request, Map<String, Object> view) {
        long articleId = Long.parseLong(request.getParameter("articleId"));
        articleRepository.changeHidden(articleId);
    }


}
