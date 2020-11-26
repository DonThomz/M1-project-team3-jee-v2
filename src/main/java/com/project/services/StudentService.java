package com.project.services;

import com.project.dao.StudentDao;
import com.project.models.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService implements EntityService<Student> {

    private final StudentDao dao;

    public StudentService(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Student> findAll() {
        return dao.findAll();
    }

    @Override
    public Student find(int id) {
        return dao.find(id);
    }

    @Override
    public void save(Student object) {
        dao.save(object);
    }

    @Override
    public void saveAll(Student... objects) {
        dao.saveAll(objects);
    }

    @Override
    public void update(Student object) {
        dao.update(object);
    }

    @Override
    public void updateAll(List<Student> objects) throws SQLException {
        dao.updateAll(objects);
    }

}
