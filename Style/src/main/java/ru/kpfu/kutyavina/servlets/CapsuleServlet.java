package ru.kpfu.kutyavina.servlets;
import ru.kpfu.kutyavina.dao.*;
import ru.kpfu.kutyavina.service.CheckerUp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CapsuleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CapsuleDao cd = new CapsuleDaoImpl();
        CapsuleProductDao cpd = new CapsuleProductDaoImpl();
        String action = req.getParameter("action");
        String idCap = req.getParameter("idCap");
        String idProd = req.getParameter("idProd");
        switch (action) {
            case "name":
                String name = req.getParameter("nameCap");
                if (CheckerUp.checkName(name)) {
                    cd.updateName(name, Integer.parseInt(idCap));
                }
                break;
            case "deleteCap":
                String delete = req.getParameter("deleteCap");
                if (delete.compareToIgnoreCase("Yes") >= 0) {
                    cd.delete(Integer.parseInt(idCap));
                }
                break;
            case "deleteProd":
                cpd.delete(Integer.parseInt(idProd), Integer.parseInt(idCap));
                break;
            case "deleteApp":
                AppointmentDao ad = new AppointmentDaoImpl();
                ad.delete(req.getParameter("date"), req.getParameter("time"));
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
