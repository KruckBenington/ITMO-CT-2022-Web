package ru.itmo.wp.web.page;

import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleRepository.findAll());
    }
}
