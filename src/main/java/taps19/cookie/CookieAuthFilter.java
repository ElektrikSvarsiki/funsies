package taps19.cookie;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/cookie-protected")
public class CookieAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();
        String user = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("user".equals(c.getName())) {
                    user = c.getValue();
                    break;
                }
            }
        }

        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Not authenticated via cookie");
            return;
        }

        chain.doFilter(request, response);
    }
}
