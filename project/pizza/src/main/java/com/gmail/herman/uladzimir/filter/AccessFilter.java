package com.gmail.herman.uladzimir.filter;

import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.gmail.herman.uladzimir.command.AttributeName.USER;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_LOGIN_PAGE;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (session.getAttribute(USER) == null) {
            response.sendRedirect(REDIRECT_TO_LOGIN_PAGE);
            return;
        } else {
            UserRole userRole = ((User) session.getAttribute(USER)).getUserRole();

            if (!AccessURL.getInstance().isCorrectAccess(userRole, request.getRequestURI())) {
                response.sendRedirect(REDIRECT_TO_LOGIN_PAGE);
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}