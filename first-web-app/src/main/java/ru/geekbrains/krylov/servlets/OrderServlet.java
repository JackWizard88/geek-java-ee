package ru.geekbrains.krylov.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders/*")
public class OrderServlet extends HttpServlet {

    private final String HEADER = "Заказы";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader", HEADER);
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
    }
}
