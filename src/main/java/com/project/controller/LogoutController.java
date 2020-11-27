package com.project.controller;

import com.project.models.Internship;
import com.project.models.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.project.util.constants.Attribute.ATTR_INTERNSHIP;
import static com.project.util.constants.Attribute.SESSION_USER;
import static com.project.util.constants.Path.PATH_HOME;
import static com.project.util.constants.Path.PATH_LOGIN;
import static com.project.util.constants.View.VIEW_DETAIL;

@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    protected void removeSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_USER) != null) {
            session.removeAttribute(SESSION_USER);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        removeSessionUser(request);
        response.sendRedirect(this.getServletContext().getContextPath() + PATH_LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
