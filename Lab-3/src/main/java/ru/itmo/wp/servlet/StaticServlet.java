package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] uris = request.getRequestURI().split(Pattern.quote("+"));

        for (String uri : uris) {
            final String filePath = "C:\\Users\\Ropka\\ИТМО - КТ\\Семестр 3\\CT-ITMO-2022-Web\\Lab-3\\src\\main\\webapp\\static";
            File srcFile = new File(filePath + "/" + uri);

            if (srcFile.isFile()) {
                if (response.getContentType() == null){
                    response.setContentType(getServletContext().getMimeType(srcFile.getName()));
                }
                try (OutputStream outputStream = response.getOutputStream()) {
                    Files.copy(srcFile.toPath(), outputStream);
                }
            } else {
                File serverFile = new File(getServletContext().getRealPath("/static" + uri));

                if (serverFile.isFile()) {
                    response.setContentType(getServletContext().getMimeType(serverFile.getName()));
                    try (OutputStream outputStream = response.getOutputStream()) {
                        Files.copy(serverFile.toPath(), outputStream);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }
    }
}
