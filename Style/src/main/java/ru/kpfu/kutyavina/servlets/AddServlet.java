package ru.kpfu.kutyavina.servlets;

import ru.kpfu.kutyavina.dao.CapsuleProductDao;
import ru.kpfu.kutyavina.dao.CapsuleProductDaoImpl;
import ru.kpfu.kutyavina.exeption.NoThisIdException;
import ru.kpfu.kutyavina.exeption.ProductAlreadyExistsException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String capId = req.getParameter("cap");
        String prodId = req.getParameter("prod");
        if(name == null || capId == null || prodId == null) {
            try {
                resp.sendRedirect("/style");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CapsuleProductDao cpd = new CapsuleProductDaoImpl();
        try {
            if(cpd.check(Integer.parseInt(prodId), Integer.parseInt(capId))) {
                cpd.add(Integer.parseInt(prodId), Integer.parseInt(capId));
            }
        }
        catch (ProductAlreadyExistsException ex) {
            req.getSession().setAttribute("exception", "Продукт уже находится в даном листе");
        }
        catch (NoThisIdException ex) {
            req.getSession().setAttribute("exception", "Неверные данные");
        }
        try {
            resp.sendRedirect("/style/title?name=" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
