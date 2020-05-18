package servlet;
import service.CookiesService;
import service.UserService;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine engine;
    private UserService usersService=new UserService();
    private CookiesService cookiesService;

    public LoginServlet(TemplateEngine engine) throws SQLException {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HashMap<String, Object> data = new HashMap<>();
        data.put("Email","Email");
        data.put("message", "Please sign in");
        engine.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("Email");
        String password = req.getParameter("Password");
       cookiesService = new CookiesService(req, resp);
       cookiesService.addCookie(usersService.getUserId(login,password));

        resp.sendRedirect("/users");
    }
}
