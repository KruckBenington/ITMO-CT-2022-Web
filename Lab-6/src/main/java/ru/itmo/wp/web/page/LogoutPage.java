package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class LogoutPage extends Page{
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        Event logoutEvent = new Event();
        logoutEvent.setType("LOGOUT");

        if (getUser() != null) {
            eventService.registerEvent(logoutEvent, getUser());
            request.getSession().removeAttribute("user");
            request.getSession().setAttribute("message", "Good bye. Hope to see you soon!");
        }
        throw new RedirectException("/index");
    }
}
