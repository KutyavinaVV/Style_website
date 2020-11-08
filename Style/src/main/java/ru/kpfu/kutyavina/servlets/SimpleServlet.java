package ru.kpfu.kutyavina.servlets;

import ru.kpfu.kutyavina.dao.FavoriteDao;
import ru.kpfu.kutyavina.dao.FavoriteDaoImpl;
import ru.kpfu.kutyavina.models.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    try {
      req.setCharacterEncoding("UTF-8");
      resp.setCharacterEncoding("UTF-8");

      String title = req.getParameter("name");
        if (title == null) {
          req.getRequestDispatcher("/WEB-INF/views/MainPage.jsp").forward(req, resp);
        }
        else {
          if (req.getSession().getAttribute("User") != null) {
          String id = ((User) req.getSession().getAttribute("User")).getId();
            FavoriteDao fd = new FavoriteDaoImpl();
            if (fd.read(Integer.parseInt(id), title)) {
              req.setAttribute("favorite", "add");
            }
            req.setAttribute("name", title);
            req.setAttribute("id", id);
          }
          else req.setAttribute("error", " ");
          req.getRequestDispatcher("/WEB-INF/views/title/" + title + ".jsp").forward(req, resp);
        }
        } catch (ServletException | IOException e) {
             e.printStackTrace();
          }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String title = req.getParameter("name");
    String id = req.getParameter("id");
    String action = req.getParameter("action");
    FavoriteDao fd = new FavoriteDaoImpl();

    switch (action) {
      case "delete":
        fd.delete(Integer.parseInt(id), title);
        break;
      case "add":
        fd.add(Integer.parseInt(id), title);
        break;
    }
    resp.sendRedirect(req.getContextPath() +  "?name=" + title);
  }
}
