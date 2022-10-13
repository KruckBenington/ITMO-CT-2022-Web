package ru.itmo.wp.servlet;




import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SessionServlet extends HttpServlet {



    public static class MessagePair {
        private final String user;
        private final  String text;

        public MessagePair(String user, String text) {
            this.text = text;
            this.user = user;
        }

        public String getText() {
            return text;
        }

        public String getUser() {
            return user;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user:text") == null) {
            session.setAttribute("user:text", new ArrayList<MessagePair>());
        }

        final String uri = request.getRequestURI();
        response.setContentType("application/json");
        final PrintWriter writer = response.getWriter();
        final String json;

        switch (uri) {
            case "/message/auth":
                String userName = request.getParameter("user");
                if (userName != null) {
                    session.setAttribute("user", userName);
                }
                json = new Gson().toJson(userName);
                response.getWriter().print(json);
                response.getWriter().flush();
        }

    }
}
