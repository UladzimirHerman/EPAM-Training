package com.gmail.herman.uladzimir.filter;

import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.gmail.herman.uladzimir.command.AttributeName.USER;
import static com.gmail.herman.uladzimir.route.ResponsePath.REDIRECT_TO_LOGIN_PAGE;

/**
 * Class {@link AccessFilter} manages access to pages according to access settings.
 *
 * @author Uladzimir Herman
 * @see AccessURL
 */
public class AccessFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AccessFilter.class);

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
            LOGGER.info("Non-authorized user tried to get access to application page");
            return;
        } else {
            User user = (User) session.getAttribute(USER);

            if (!AccessURL.getInstance().isCorrectAccess
                    (user.getUserRole(), request.getRequestURI())) {
                response.sendRedirect(REDIRECT_TO_LOGIN_PAGE);
                LOGGER.info(user.getLogin() + " tried to get access to forbidden page");
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}