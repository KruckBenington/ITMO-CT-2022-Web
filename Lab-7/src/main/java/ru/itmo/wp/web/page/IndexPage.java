package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import org.checkerframework.checker.units.qual.A;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class IndexPage {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    private void findAllArticles(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleRepository.findAllNotHidden());
    }
}
