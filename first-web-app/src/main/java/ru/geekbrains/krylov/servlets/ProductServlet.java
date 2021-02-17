package ru.geekbrains.krylov.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.krylov.entities.Product;
import ru.geekbrains.krylov.repositories.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private final static Logger logger = LogManager.getLogger(ProductServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("ProductRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/products.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {

            Long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }

            Product product = productRepository.findById(id);
            if (product == null) {
                resp.setStatus(404);
                return;
            }

            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {

            Long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }

            Product product = productRepository.deleteById(id);
            if (product == null) {
                resp.setStatus(404);
                return;
            }
            resp.sendRedirect(getServletContext().getContextPath() + "/products");

        } else if (req.getPathInfo().equals("/new")) {
            Product product = new Product(null, "", "", null);
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        String idStr = req.getParameter("id");
        logger.info("id =" + idStr);

        if (!idStr.equals("")) {
            try {
                id = Long.parseLong(idStr);
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
        }


        Float price;
        try {
            price = new Float(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        Product product = new Product(id, req.getParameter("title"), req.getParameter("description"), price);
        productRepository.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/products");
    }

}
