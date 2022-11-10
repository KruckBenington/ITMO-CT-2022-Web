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
    ArrayList<MessagePair> userToText = new ArrayList<MessagePair>();

    public static class MessagePair {
        private final String user;
        private final String text;

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

        final String uri = request.getRequestURI();
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        final PrintWriter writer = response.getWriter();
        final String json;

        switch (uri) {
            case "/message/auth":
                String userName = request.getParameter("user");
                if (userName != null && userName.trim().equals("")) {
                    response.sendError(406);
                } else {
                    session.setAttribute("user", userName);
                }
                json = new Gson().toJson(userName);
                writer.print(json);
                writer.flush();
                break;
            case "/message/add":
                String text = request.getParameter("text");
                if (text != null && !text.trim().equals("")) {
                    userToText.add(new MessagePair((String) session.getAttribute("user"), text));
                } else {
                    response.sendError(406);
                }
                break;
            case "/message/findAll":
                if (session.getAttribute("user") != null) {
                    json = new Gson().toJson(userToText.toArray());
                    writer.print(json);
                    writer.flush();
                }
                break;
            // чат не должен показываться, пока пользователь не авторизирован
            // пробелы и пустые строки не могут является контентом(user/text)
            // пофиксить utf-8

        }

    }
}
