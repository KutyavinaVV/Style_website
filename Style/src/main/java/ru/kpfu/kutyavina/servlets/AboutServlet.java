package ru.kpfu.kutyavina.servlets;

import ru.kpfu.kutyavina.dao.AppointmentDao;
import ru.kpfu.kutyavina.dao.AppointmentDaoImpl;
import ru.kpfu.kutyavina.models.User;
import ru.kpfu.kutyavina.service.AppointmentList;
import ru.kpfu.kutyavina.service.CheckerUp;
import ru.kpfu.kutyavina.service.TimeService;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AboutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("maxDate", TimeService.getMaxDate());
            req.setAttribute("date", TimeService.getTodayDay());
            if(req.getSession().getAttribute("appo") != null) {
                req.setAttribute("appo", req.getSession().getAttribute("appo"));
                req.getSession().removeAttribute("appo");
            }
            req.getRequestDispatcher("/WEB-INF/views/about.jsp").forward(req, resp);
        } catch (ServletException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("User") != null) {
            String date = req.getParameter("date");
            try {
                req.setAttribute("maxDate", TimeService.getMaxDate());
                req.setAttribute("maxDate", TimeService.getMaxDate());
                req.setAttribute("today", TimeService.getTodayDay());
                req.setAttribute("date", date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                String a = req.getParameter("a");
                if (a != null) {
                if (CheckerUp.checkData(date)) {
                    ArrayList<String> timeList = AppointmentList.getTime();
                    AppointmentDao ap = new AppointmentDaoImpl();
                    List<String> timeB = ap.getBookedTime(date);
                    for (String time : timeB) {
                        timeList.set(timeList.indexOf(time), null);
                    }

                    List<String> answerTime = new ArrayList<>();

                    if ((a.equals("cons30")) || (a.equals("cons60"))) {
                        if ((a.equals("cons30"))) req.getSession().setAttribute("nameP", "cons30");
                        else req.getSession().setAttribute("nameP", "cons60");
                        for (String time : timeList) {
                            if (time != null) {
                                answerTime.add(time);
                            }
                        }
                    }

                    if (a.equals("analysis")) {
                        req.getSession().setAttribute("nameP", "analysis");
                        for (String time : timeList) {
                            int k = timeList.indexOf(time);
                            if ((time != null)) {
                                if ((k + 1 < timeList.size()) && (timeList.get(timeList.indexOf(time) + 1) != null)) {
                                    if ((k + 2 < timeList.size()) && (timeList.get(timeList.indexOf(time) + 2) != null)) {
                                        answerTime.add(time);
                                    }
                                }
                            }
                        }
                    }

                    if (a.equals("newG")) {
                        req.getSession().setAttribute("nameP", "newG");
                        if (!timeList.contains(null)) answerTime.add("9:00");
                    }
                    req.getSession().setAttribute("date", date);
                    req.setAttribute("time", answerTime);
                    req.setAttribute("post", "true");
                    if (answerTime.size() == 0)
                        req.setAttribute("exception", "На выбранный день все места заняты, попробуйте выбрать другую дату");
                } else req.setAttribute("exception", "Пожалуйста, выберет корректную дату для записи");
                } else req.setAttribute("exception", "Пожалуйста, выберет услугу");

            } else {
                String name = req.getParameter("FIO");
                String number = req.getParameter("tel");
                String time = req.getParameter("myTime");
                time = time.substring(1, time.length()-1);

                if (!CheckerUp.checkNumber(number)) {
                    req.setAttribute("exception", "Введите правильный номер телефона");
                } else if (!CheckerUp.checkName(name)) {
                    req.setAttribute("exception", "Введите настоящее имя");
                } else {
                    AppointmentDao ad = new AppointmentDaoImpl();
                    String user_id = ((User) req.getSession().getAttribute("User")).getId();
                    String date2 = (String) req.getSession().getAttribute("date");
                    String nameP = (String) req.getSession().getAttribute("nameP");
                    req.getSession().removeAttribute("date");
                    ad.create(date2, time, user_id, nameP);
                    req.getSession().setAttribute("appo", "Ваша запись обрабатывается, ожидайте звонка");

                    try {
                        resp.sendRedirect("/style/about");
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                req.getRequestDispatcher("/WEB-INF/views/about.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                resp.sendRedirect("/style/signin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}