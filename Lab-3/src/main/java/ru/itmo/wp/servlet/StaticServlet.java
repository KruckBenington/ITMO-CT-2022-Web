package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String filePath = "C:\\Users\\Ropka\\OneDrive\\Рабочий стол\\WEB\\Lab-3\\src\\main\\webapp\\static";
        String[] uris = request.getRequestURI().split(Pattern.quote("+"));
        List<File> files = new ArrayList<>();

        for (String uri : uris) {
            File srcFile = new File(filePath + "/" + uri);
            if (!srcFile.isFile()) {
                File serverFile = new File(getServletContext().getRealPath("/static" + uri));
                if (!serverFile.isFile()) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
                } else {
                    files.add(serverFile);
                }
            } else {
                files.add(srcFile);
            }
        }


        if (files.size() == uris.length) {
            for (File file : files) {
                if (response.getContentType() == null) {
                    response.setContentType(getServletContext().getMimeType(file.getName()));
                }
                try (OutputStream outputStream = response.getOutputStream()) {
                    Files.copy(file.toPath(), outputStream);
                }
            }
        }
    }
}
