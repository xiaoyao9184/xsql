package com.xy.xsql.spring.template.relationship;

import com.xy.xsql.entity.model.definition.RelationshipClass;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface RelationshipEntityManageTemplate {

    <LeftEntity,RightEntity> Boolean checkTable(RelationshipClass<LeftEntity,RightEntity> entityRelationshipClass);

    <LeftEntity,RightEntity> void createTable(RelationshipClass<LeftEntity,RightEntity> entityRelationshipClass);

    <LeftEntity,RightEntity> void dropTable(RelationshipClass<LeftEntity,RightEntity> entityRelationshipClass);
}
