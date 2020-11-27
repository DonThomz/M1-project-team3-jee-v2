package com.project.services;

import com.project.forms.DetailForm;
import com.project.models.Internship;

import javax.servlet.http.HttpServletRequest;

import static com.project.util.constants.Attribute.ATTR_FORM_DETAIL;
import static com.project.util.constants.Attribute.ATTR_INTERNSHIP;

public class DetailService {

    private final InternshipService internshipService;

    public DetailService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    public void updateDetailInformation(HttpServletRequest request, int id) {

        DetailForm detailForm = new DetailForm();
        Internship internshipFounded = null;
        try {
            internshipFounded = internshipService.find(id);
        } catch (com.project.exceptions.ServiceException e) {
            e.printStackTrace();
        }
        internshipFounded = detailForm.handleForm(request, internshipFounded);

        request.setAttribute(ATTR_FORM_DETAIL, detailForm);
        request.setAttribute(ATTR_INTERNSHIP, internshipFounded);
        if (detailForm.getErrors().isEmpty()) {
            try {
                internshipService.update(internshipFounded);
            } catch (com.project.exceptions.ServiceException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(detailForm.getErrors());
        }

    }

}
