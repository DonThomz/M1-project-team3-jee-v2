package com.project.dao;

import com.project.database.Database;
import com.project.models.SkillRequired;

public class SkillRequiredDao extends DaoResource<SkillRequired> {


    public SkillRequiredDao(Database database) {
        super(database, SkillRequired.class);
    }

}
