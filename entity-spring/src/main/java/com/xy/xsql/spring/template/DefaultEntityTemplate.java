package com.xy.xsql.spring.template;

import com.xy.xsql.spring.template.relationship.RelationshipEntityManageTemplate;
import com.xy.xsql.spring.template.relationship.RelationshipEntityManager;
import com.xy.xsql.spring.template.simple.ClassEntityCRUDTemplate;
import com.xy.xsql.spring.template.simple.ClassEntityManageTemplate;
import com.xy.xsql.spring.template.simple.ClassEntityManager;
import com.xy.xsql.spring.template.simple.ClassEntitySearchTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultEntityTemplate implements EntityTemplate {

    @Autowired
    private ClassEntityManageTemplate classEntityManageTemplate;

    @Autowired
    private ClassEntityCRUDTemplate classEntityCRUDTemplate;

    @Autowired
    private ClassEntitySearchTemplate classEntitySearchTemplate;

    @Autowired
    private RelationshipEntityManageTemplate relationshipEntityManageTemplate;


    @Autowired
    private ClassEntityManager classEntityManager;

    @Autowired
    private RelationshipEntityManager relationshipEntityManager;


    @Override
    public ClassEntityManageTemplate getClassEntityManageTemplate() {
        return classEntityManageTemplate;
    }

    @Override
    public ClassEntityCRUDTemplate getClassEntityCRUDTemplate() {
        return classEntityCRUDTemplate;
    }

    @Override
    public ClassEntitySearchTemplate getClassEntitySearchTemplate() {
        return classEntitySearchTemplate;
    }

    @Override
    public RelationshipEntityManageTemplate getRelationshipEntityManageTemplate() {
        return relationshipEntityManageTemplate;
    }

    @Override
    public ClassEntityManager classEntityManager() {
        return classEntityManager;
    }

    @Override
    public RelationshipEntityManager relationshipEntityManager() {
        return relationshipEntityManager;
    }
}
