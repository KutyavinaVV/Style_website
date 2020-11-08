package ru.kpfu.kutyavina.servlets;

import ru.kpfu.kutyavina.dao.AppointmentDao;
import ru.kpfu.kutyavina.dao.AppointmentDaoImpl;
import ru.kpfu.kutyavina.dao.UserDao;
import ru.kpfu.kutyavina.dao.UserDaoImpl;
import ru.kpfu.kutyavina.models.User;
import ru.kpfu.kutyavina.service.CheckerUp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!CheckerUp.checkEmail(email)) {
            wrong("Wrong email!Try again", req, resp);
            return;
        }
        UserDao ud = new UserDaoImpl();
        User user = ud.find(email, password);
        if (user!= null) {
            req.getSession().setAttribute("User", user);
            req.getSession().setAttribute("SignIn", "true");
            AppointmentDao ad = new AppointmentDaoImpl();
            ad.deleteByUserId(Integer.parseInt(user.getId()));
            try {
                String path = req.getContextPath() + "/profile";
                resp.sendRedirect(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else wrong("Try again", req, resp);

    }

    private void wrong(String content, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("content", content);
        try {
            req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
