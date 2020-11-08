package ru.kpfu.kutyavina.servlets;

import ru.kpfu.kutyavina.dao.CapsuleDao;
import ru.kpfu.kutyavina.dao.CapsuleDaoImpl;
import ru.kpfu.kutyavina.dao.ProductDao;
import ru.kpfu.kutyavina.dao.ProductDaoImpl;
import ru.kpfu.kutyavina.models.Capsule;
import ru.kpfu.kutyavina.models.Product;
import ru.kpfu.kutyavina.models.User;
import ru.kpfu.kutyavina.service.CheckerUp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("name");
        String ex = (String) req.getSession().getAttribute("exception");
        if(ex != null) req.setAttribute("exception", ex);
        req.getSession().removeAttribute("exception");
        if (title != null) {
            try {
                ProductDao pd = new ProductDaoImpl();
                List<Product> products = pd.getProductByType(title);
                req.setAttribute("products", products);
                CapsuleDao cd = new CapsuleDaoImpl();
                List<Capsule> capsules = new ArrayList<>();
                User user = (User) req.getSession().getAttribute("User");
                if (user != null) {
                    capsules = cd.getByUserId(Integer.parseInt(user.getId()));
                }
                req.setAttribute("title", title);
                req.setAttribute("capsules", capsules);
                req.getRequestDispatcher("/WEB-INF/views/title/productList.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                resp.sendRedirect("/style");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("name");
        String name = req.getParameter("nameCapsule");
        User user = (User) req.getSession().getAttribute("User");

        if ((name != null) && (user != null)) {
            if (CheckerUp.checkName(name)) {
                CapsuleDao cd = new CapsuleDaoImpl();
                cd.create(name, Integer.parseInt(user.getId()));
            }
        }
        if(user == null) req.getSession().setAttribute ("exception", "Вы не авторизваны");
        resp.sendRedirect("/style/title?name=" + param);
    }
}
