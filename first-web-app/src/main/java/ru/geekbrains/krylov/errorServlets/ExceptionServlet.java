package ru.geekbrains.krylov.errorServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exceptionServlet")
public class ExceptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Wooops! Houston we have a problem!</h1>");
        resp.getWriter().println("<h3>We have an Exception but we can't show it due to Security policy!</h3>");
    }
}
