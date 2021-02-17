package ru.geekbrains.krylov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index/*")
public class MainServlet extends HttpServlet {

    private final String HEADER = "Главная";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("pageHeader", HEADER);
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

    }
}
