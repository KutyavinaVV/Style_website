package ru.kpfu.kutyavina.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.kpfu.kutyavina.dao.UserDao;
import ru.kpfu.kutyavina.dao.UserDaoImpl;
import ru.kpfu.kutyavina.models.*;
import ru.kpfu.kutyavina.service.CheckerUp;

public class SignUpServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("content", "");
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("psw");
        String password_repeat = req.getParameter("psw-repeat");
        UserDao ud = new UserDaoImpl();
        if (!CheckerUp.checkName(name)) {
            wrong("Wrong name! Try again.", req, resp);
        }
        else if (!CheckerUp.checkEmail(email)) {
            wrong("Wrong email!Try again", req, resp);
        }
        else if (!CheckerUp.checkPassword(password, password_repeat)) {
            wrong("Wrong password!Try again", req, resp);
        }
        else if (ud.findByEmail(email)) {
            wrong("A user with the same email already exists", req, resp);
        }
        else {
            User user = new User(name, email, password);
            ud.add(user);
            req.getSession().setAttribute("SignIn", true);
            req.getSession().setAttribute("User", new User(name, email, password));
            try {
                String path = req.getContextPath() + "/profile";
                resp.sendRedirect(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void wrong(String content, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("content", content);
        try {
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
