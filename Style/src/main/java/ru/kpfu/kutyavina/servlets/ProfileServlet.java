package ru.kpfu.kutyavina.servlets;

import ru.kpfu.kutyavina.dao.*;
import ru.kpfu.kutyavina.models.Appointment;
import ru.kpfu.kutyavina.models.Capsule;
import ru.kpfu.kutyavina.models.User;
import ru.kpfu.kutyavina.service.CheckerUp;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("User");
            req.setAttribute("UserName", user.getName());
            req.setAttribute("UserEmail", user.getEmail());
            AppointmentDao ap = new AppointmentDaoImpl();
            List<Appointment> list = ap.getByUserId(Integer.parseInt(user.getId()));
            req.setAttribute("Appointment", list);
            CapsuleDao cd = new CapsuleDaoImpl();
            List<Capsule> capsules = cd.getByUserId(Integer.parseInt(user.getId()));
            req.setAttribute("capsules", capsules);
            req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String delete = req.getParameter("delete");
        if (name != null) {
            if (CheckerUp.checkName(name)) {
                UserDao ud = new UserDaoImpl();
                User user = (User) req.getSession().getAttribute("User");
                user.setName(name);
                req.getSession().setAttribute("User", user);
            }
        }
        if (delete != null) {
            if (delete.compareToIgnoreCase("Yes") >= 0) {
                UserDao ud = new UserDaoImpl();
                ud.delete((User) req.getSession().getAttribute("User"));
                req.getSession().removeAttribute("User");
                resp.sendRedirect(req.getContextPath());
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
