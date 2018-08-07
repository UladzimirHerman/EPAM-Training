package com.gmail.herman.uladzimir.filter;

import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.gmail.herman.uladzimir.command.AttributeName.USER;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_ADMIN_PRODUCT_PAGE;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_USER_PRODUCT_PAGE;

public class AuthenticationFilter implements Filter {

    public static final String PAGE_ATTRIBUTE = "?page=1";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (session.getAttribute(USER) != null) {
            UserRole userRole = ((User) session.getAttribute(USER)).getUserRole();

            if (userRole == UserRole.ADMIN) {
                response.sendRedirect(REDIRECT_TO_ADMIN_PRODUCT_PAGE + PAGE_ATTRIBUTE);
                return;
            } else if (userRole == UserRole.USER) {
                response.sendRedirect(REDIRECT_TO_USER_PRODUCT_PAGE);
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}