package com.project.controller;


import com.project.dao.InternDao;
import com.project.dao.InternshipDao;
import com.project.database.DerbyDatabase;
import com.project.models.Intern;
import com.project.models.Internship;
import com.project.models.Tutor;
import com.project.services.InternService;
import com.project.services.InternshipService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.util.constants.Attribute.*;
import static com.project.util.constants.View.VIEW_HOME;

@WebServlet(name = "HomeController", urlPatterns = {""})
public class HomeController extends HttpServlet {

    private Tutor user;
    private List<Internship> internships;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        // is a request ajax
        if (ajax) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            try {
                handleModification(request);
                response.getWriter().write(MESSAGE_UPDATE_SUCCESS);
            } catch (Exception e) {
                response.setStatus(500);
                response.getWriter().write(MESSAGE_SERVER_ERROR);
            }
        } else request.getRequestDispatcher(VIEW_HOME).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get user logged
        user = (Tutor) request.getSession().getAttribute(SESSION_USER);

        // get interns
        try {

            // Dao
            DerbyDatabase database = DerbyDatabase.getInstance(request);
            InternshipDao dao = new InternshipDao(database);
            InternshipService internshipService = new InternshipService(dao);
            String year = request.getParameter(PARAM_YEAR);
            if (year != null) {
                try {
                    int yearParse = Integer.parseInt(year);
                    internships = internshipService.findByYear(user, yearParse);
                } catch (NumberFormatException e) {
                    request.setAttribute(ERROR_PARAM_YEAR, MESSAGE_ERROR_PARAM_YEAR);
                    // TODO add log here
                }
            } else {
                internships = internshipService.findInternshipsByTutorId(user);
            }
            request.setAttribute(ATTR_INTERNSHIPS, internships);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        request.getRequestDispatcher(VIEW_HOME).forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        // is a request ajax
        if (ajax) {

            DerbyDatabase database = DerbyDatabase.getInstance(request);
            InternshipDao dao = new InternshipDao(database);
            InternshipService internshipService = new InternshipService(dao);

            try {
                int id = Integer.parseInt(request.getParameter("id"));
                internshipService.remove(id);
                response.getWriter().write("Internship " + id + " deleted !");
            } catch (Exception e) {
                response.setStatus(500);
                response.getWriter().write(MESSAGE_SERVER_ERROR);
            }
        } else request.getRequestDispatcher(VIEW_HOME).forward(request, response);
    }

    private void handleModification(HttpServletRequest request) {

        DerbyDatabase database = DerbyDatabase.getInstance(request);
        InternDao dao = new InternDao(database);
        InternService internshipService = new InternService(dao);

        JSONObject jsonObject = new JSONObject(request.getParameter(ATTR_DATA));

        JSONArray data = jsonObject.getJSONArray("data");

        List<Intern> updatedInterns = new ArrayList<>();

        HttpSession session = request.getSession();
        user = (Tutor) session.getAttribute(SESSION_USER);

        for (int i = 0; i < data.length(); i++) {
            JSONObject internJson = (JSONObject) data.get(i);
            Intern intern = internshipService.mappingJson(internJson);
            intern.setTutor(user);
            updatedInterns.add(intern);
        }
        try {
            internshipService.updateAll(updatedInterns);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


}
