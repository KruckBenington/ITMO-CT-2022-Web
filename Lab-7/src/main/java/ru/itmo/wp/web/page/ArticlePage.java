package ru.itmo.wp.web.page;

import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {
    private final ArticleService articleService = new ArticleService();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void publish(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article = new Article();
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        article.setTitle(title);
        article.setText(text);
        articleService.validateArticle(title, text);
        articleService.createArticle(article, (User) request.getSession().getAttribute("user"));
        request.getSession().setAttribute("message", "The Article" + title + "was successfully created");
        throw new RedirectException("/index");
    }
}
