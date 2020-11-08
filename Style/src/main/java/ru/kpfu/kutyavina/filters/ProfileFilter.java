package ru.kpfu.kutyavina.filters;

import ru.kpfu.kutyavina.service.SecurityService;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/profile")
public class ProfileFilter extends HttpFilter{

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (!SecurityService.isSing(req)) {
            try {
                res.sendRedirect("/style/signin");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        chain.doFilter(req, res);
    }

}
