package com.project.controller;

import com.project.dao.InternshipDao;
import com.project.database.DerbyDatabase;
import com.project.models.Internship;
import com.project.models.Tutor;
import com.project.services.DetailService;
import com.project.services.InternshipService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.util.constants.Attribute.ATTR_INTERNSHIP;
import static com.project.util.constants.Attribute.SESSION_USER;
import static com.project.util.constants.Path.PATH_HOME;
import static com.project.util.constants.View.VIEW_DETAIL;

@WebServlet(name = "DetailsController", urlPatterns = {"/detail"})
public class DetailsController extends HttpServlet {

    private Internship internship;

    private DetailService detailService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        if (id != -1) {
            try {
                // get parameter
                this.findInternshipData(request, id);
                // check if the user is allowed to see this detail page
                if (internship.getIntern().getTutor().getTutorId() != ((Tutor) request.getSession().getAttribute(SESSION_USER)).getTutorId()) {
                    response.sendRedirect(this.getServletContext().getContextPath() + PATH_HOME);
                } else {
                    request.setAttribute(ATTR_INTERNSHIP, internship);
                    request.getRequestDispatcher(VIEW_DETAIL).forward(request, response);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else response.sendRedirect(this.getServletContext().getContextPath() + PATH_HOME);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
        if (id != -1) {
            // update data
            // enable entities services
            DerbyDatabase database = DerbyDatabase.getInstance(request);
            InternshipDao dao = new InternshipDao(database);
            InternshipService service = new InternshipService(dao);
            detailService = new DetailService(service);

            detailService.updateDetailInformation(request, id);

            // return to detail page
            processRequest(request, response, id);
        } else response.sendRedirect(this.getServletContext().getContextPath() + PATH_HOME);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
        // check if user is logged and internship is accessible by the user
        processRequest(request, response, id);
    }

    private void findInternshipData(HttpServletRequest request, int id) {
        DerbyDatabase database = DerbyDatabase.getInstance(request);
        InternshipDao dao = new InternshipDao(database);
        InternshipService service = new InternshipService(dao);
        internship = service.find(id);
    }


}
