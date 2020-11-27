package com.project.controller;

import com.project.dao.InternshipDao;
import com.project.dao.SkillDao;
import com.project.dao.SkillRequiredDao;
import com.project.database.DerbyDatabase;
import com.project.exceptions.ServiceException;
import com.project.models.Internship;
import com.project.models.Skill;
import com.project.models.SkillRequired;
import com.project.models.Tutor;
import com.project.services.DetailService;
import com.project.services.InternshipService;
import com.project.services.SkillRequiredService;
import com.project.services.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static com.project.util.constants.Attribute.*;
import static com.project.util.constants.Path.PATH_HOME;
import static com.project.util.constants.View.VIEW_DETAIL;

@WebServlet(name = "DetailsController", urlPatterns = {"/detail"})
public class DetailsController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DetailsController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Integer id) throws ServletException, IOException, ServiceException {

        if (id != null) {
            Internship internship = this.findInternshipData(request, id);
            List<Skill> skills = this.findSkills(request);

            // check if the user is allowed to see this detail page
            if (internship != null && internship.getIntern().getTutor().getTutorId() != ((Tutor) request.getSession().getAttribute(SESSION_USER)).getTutorId()) {
                response.sendRedirect(this.getServletContext().getContextPath() + PATH_HOME);
            } else {
                request.setAttribute(ATTR_SKILLS, skills);
                request.setAttribute(ATTR_INTERNSHIP, internship);
                request.getRequestDispatcher(VIEW_DETAIL).forward(request, response);
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

            try {
                detailService.updateDetailInformation(request, id);
            } catch (ServiceException e) {
                request.setAttribute(ERROR_SERVER, MESSAGE_SERVER_ERROR);
            }

            // return to detail page
            try {
                processRequest(request, response, id);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
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
        try {
            processRequest(request, response, id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private Internship findInternshipData(HttpServletRequest request, int id) {
        DerbyDatabase database = DerbyDatabase.getInstance(request);
        InternshipDao dao = new InternshipDao(database);
        InternshipService service = new InternshipService(dao);
        try {
            return service.find(id);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_SERVER, MESSAGE_ERROR_PARAM_YEAR);
            return null;
        }
    }

    private List<Skill> findSkills(HttpServletRequest request) {
        DerbyDatabase database = DerbyDatabase.getInstance(request);
        SkillDao dao = new SkillDao(database);
        SkillService service = new SkillService(dao);
        try {
            return service.findAll();
        } catch (ServiceException e) {
            request.setAttribute(ERROR_SERVER, MESSAGE_ERROR_PARAM_YEAR);
            return null;
        }
    }
}
