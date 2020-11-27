package com.project.dao;

import com.project.database.Database;
import com.project.exceptions.DaoException;
import com.project.models.Skill;

import java.util.List;

public class SkillDao extends DaoResource<Skill> {

    public SkillDao(Database database) {
        super(database, Skill.class);
    }

}
