package com.project.services;

import com.project.dao.InternshipDao;
import com.project.models.Internship;
import com.project.models.Tutor;

import java.util.List;

public class InternshipService implements EntityService<Internship> {

    private final InternshipDao dao;

    public InternshipService(InternshipDao dao) {
        this.dao = dao;
    }


    @Override
    public List<Internship> findAll() {
        return dao.findAll();
    }

    @Override
    public Internship find(int id) {
        return dao.find(id);
    }


    @Override
    public void save(Internship object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Internship... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Internship object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Internship> objects) {
        dao.updateAll(objects);
    }

    public void remove(int id) {
        dao.remove(id);
    }

    // =======================
    // Custom Queries Methods
    // =======================

    public List<Internship> findInternshipsByTutorId(Tutor tutor) {
        return dao.findInternshipsByTutorId(tutor);
    }

    public List<Internship> findByYear(Tutor tutor, int year) {
        return dao.findByYear(tutor, year);
    }

    public Internship findInternshipByTutorId(Tutor tutor) {
        return dao.findInternshipByTutorId(tutor);
    }

    // =======================
    // Custom Queries Methods
    // =======================
}
