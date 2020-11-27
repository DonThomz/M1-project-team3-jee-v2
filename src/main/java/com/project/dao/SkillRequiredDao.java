package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.SkillRequired;

import java.util.List;

public class SkillRequiredDao extends DaoResource<SkillRequired> {


    public SkillRequiredDao(Database database) {
        super(database, SkillRequired.class);
    }

}
