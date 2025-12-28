package taps19.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie-login")
public class CookieLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username) && "password".equals(password)) {
            Cookie cookie = new Cookie("user", username);
            cookie.setMaxAge(30 * 60); // 30 minutes
            cookie.setHttpOnly(true);
            resp.addCookie(cookie);
            resp.getWriter().write("Logged in via cookie");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Invalid credentials");
        }
    }
}
