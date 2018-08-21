package com.epam.herman.uladzimir.filter;

import com.epam.herman.uladzimir.entity.User;
import com.epam.herman.uladzimir.entity.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.herman.uladzimir.command.AttributeName.USER;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_ADMIN_PRODUCT_FIRST_PAGE;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_USER_PRODUCT_FIRST_PAGE;

/**
 * Class {@link AuthenticationFilter} manages access to application resources
 * according to result of authentication.
 *
 * @author Uladzimir Herman
 */
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationFilter.class);

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
            User user = (User) session.getAttribute(USER);

            if (user.getUserRole() == UserRole.ADMIN) {
                response.sendRedirect(REDIRECT_TO_ADMIN_PRODUCT_FIRST_PAGE);
                LOGGER.info("Successful authorization: " + user.getLogin());
                return;
            } else if (user.getUserRole() == UserRole.USER) {
                response.sendRedirect(REDIRECT_TO_USER_PRODUCT_FIRST_PAGE);
                LOGGER.info("Successful authorization: " + user.getLogin());
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}