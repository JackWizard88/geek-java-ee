package ru.geekbrains.krylov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private ServletConfig config;
    private final static Logger logger = LogManager.getLogger(FirstServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("FirstServlet initialized");
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("FirstServlet request accepted");
        servletResponse.getWriter().println("<h1> Hello from first servlet </h1>");
    }

    @Override
    public String getServletInfo() {
        return "FirstServlet info";
    }

    @Override
    public void destroy() {

    }
}
