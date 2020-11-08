package ru.kpfu.kutyavina.service;

import javax.servlet.http.HttpServletRequest;

public class SecurityService {
    public static boolean isSing (HttpServletRequest req) {
        return !(req.getSession().getAttribute("SignIn") == null);
    }
}
