package com.project.services;

import com.project.forms.DetailForm;
import com.project.models.Internship;

import javax.servlet.http.HttpServletRequest;

import static com.project.util.constants.Attribute.ATTR_FORM_DETAIL;
import static com.project.util.constants.Attribute.ATTR_INTERNSHIP;

public class DetailService {

    private InternshipService internshipService;

    public DetailService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    public void updateDetailInformation(HttpServletRequest request, int id) {

        DetailForm detailForm = new DetailForm();
        Internship internshipFounded = internshipService.find(id);
        internshipFounded = detailForm.handleForm(request, internshipFounded);

        request.setAttribute(ATTR_FORM_DETAIL, detailForm);
        request.setAttribute(ATTR_INTERNSHIP, internshipFounded);
        if (detailForm.getErrors().isEmpty()) {
            internshipService.update(internshipFounded);
        } else {
            System.err.println(detailForm.getErrors());
        }

    }

}
