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
import java.util.logging.Logger;

import static com.project.util.constants.Attribute.ATTR_INTERNSHIP;
import static com.project.util.constants.Attribute.SESSION_USER;
import static com.project.util.constants.Path.PATH_HOME;
import static com.project.util.constants.View.VIEW_DETAIL;

@WebServlet(name = "DetailsController", urlPatterns = {"/detail"})
public class DetailsController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DetailsController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Integer id) throws ServletException, IOException {

        if (id != null) {
            try {
                Internship internship = this.findInternshipData(request, id);
                // check if the user is allowed to see this detail page
                if (internship.getIntern().getTutor().getTutorId() != ((Tutor) request.getSession().getAttribute(SESSION_USER)).getTutorId()) {
                    response.sendRedirect(this.getServletContext().getContextPath() + PATH_HOME);
                } else {
                    request.setAttribute(ATTR_INTERNSHIP, internship);
                    request.getRequestDispatcher(VIEW_DETAIL).forward(request, response);
                }
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        } else response.sendRedirect(this.getServletContext().getContextPath() + PATH_HOME);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : null;
        } catch (NumberFormatException e) {
            logger.warning(e.getMessage());
        }
        if (id != null) {
            // update data
            // enable entities services
            DerbyDatabase database = DerbyDatabase.getInstance(request);
            InternshipDao dao = new InternshipDao(database);
            InternshipService service = new InternshipService(dao);
            DetailService detailService = new DetailService(service);

            detailService.updateDetailInformation(request, id);

            // return to detail page
            processRequest(request, response, id);
        } else response.sendRedirect(this.getServletContext().getContextPath() + PATH_HOME);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : null;
        } catch (NumberFormatException e) {
            logger.warning(e.getMessage());
        }
        // check if user is logged and internship is accessible by the user
        processRequest(request, response, id);
    }

    private Internship findInternshipData(HttpServletRequest request, int id) {
        DerbyDatabase database = DerbyDatabase.getInstance(request);
        InternshipDao dao = new InternshipDao(database);
        InternshipService service = new InternshipService(dao);
        return service.find(id);
    }


}
