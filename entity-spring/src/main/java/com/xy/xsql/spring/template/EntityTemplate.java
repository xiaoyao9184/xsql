package com.xy.xsql.spring.template;

import com.xy.xsql.core.selecter.LeftRightSelector;
import com.xy.xsql.entity.core.meta.sql.EntityQueryBuilder;
import com.xy.xsql.entity.model.definition.RelationshipClass;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;
import com.xy.xsql.spring.template.relationship.RelationshipEntityManageTemplate;
import com.xy.xsql.spring.template.relationship.RelationshipEntityManager;
import com.xy.xsql.spring.template.simple.ClassEntityCRUDTemplate;
import com.xy.xsql.spring.template.simple.ClassEntityManageTemplate;
import com.xy.xsql.spring.template.simple.ClassEntityManager;
import com.xy.xsql.spring.template.simple.ClassEntitySearchTemplate;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface EntityTemplate {

    ClassEntityManageTemplate getClassEntityManageTemplate();

    ClassEntityCRUDTemplate getClassEntityCRUDTemplate();

    ClassEntitySearchTemplate getClassEntitySearchTemplate();


    RelationshipEntityManageTemplate getRelationshipEntityManageTemplate();

//    RelationshipEntityCRUDTemplate getRelationshipEntityCRUDTemplate();
//
//    RelationshipEntitySearchTemplate getRelationshipEntitySearchTemplate();

    ClassEntityManager classEntityManager();

    RelationshipEntityManager relationshipEntityManager();


    /*
    Class
     */

    default EntityHolderManageTemplate manage(Class entity) {
        ClassEntityManageTemplate classEntityManageTemplate = getClassEntityManageTemplate();

        if(classEntityManageTemplate == null){
            throw new UnsupportedOperationException("ClassEntityManageTemplate not support!");
        }
        
        return new EntityHolderManageTemplate() {
            @Override
            public Boolean checkTable() {
                return classEntityManageTemplate.checkTable(entity);
            }

            @Override
            public void createTable() {
                classEntityManageTemplate.createTable(entity);
            }

            @Override
            public void dropTable() {
                classEntityManageTemplate.dropTable(entity);
            }
        };
    }

    default <Entity> EntityHolderCRUDTemplate<Entity> crud(Class<Entity> entityClass){
        ClassEntityCRUDTemplate classEntityCRUDTemplate = getClassEntityCRUDTemplate();
        
        return new EntityHolderCRUDTemplate<Entity>() {
            @Override
            public Entity getById(Object id) {
                return classEntityCRUDTemplate.getById(entityClass,id);
            }

            @Override
            public List<Entity> listByIds(Object... id) {
                return classEntityCRUDTemplate.listByIds(entityClass,id);
            }

            @Override
            public void deleteById(Object... id) {
                classEntityCRUDTemplate.deleteById(entityClass,id);
            }

            @Override
            public void save(Entity... entity) {
                classEntityCRUDTemplate.saveByEntity(entityClass,entity);
            }

            @Override
            public void update(Entity... entity) {
                classEntityCRUDTemplate.updateByEntity(entityClass,entity);
            }

            @Override
            public Entity getByArg(Object... arg) {
                return classEntityCRUDTemplate.getByColumn(entityClass,arg);
            }

            @Override
            public List<Entity> listByArg(Object... args) {
                return classEntityCRUDTemplate.listByColumn(entityClass,args);
            }

            @Override
            public void deleteByArg(Object... args) {
                classEntityCRUDTemplate.deleteByColumn(entityClass,args);
            }
        };
    }

    default <Entity, Result> EntityHolderSearchTemplate<Result> search(Class<Entity> entityClass, Class<Result> resultClass){
        ClassEntitySearchTemplate classEntitySearchTemplate = getClassEntitySearchTemplate();

        return new EntityHolderSearchTemplate<Result>() {
            @Override
            public List<Result> searchListByArg(Object... args) {
                return classEntitySearchTemplate.searchListByArg(entityClass,resultClass,args);
            }

            @Override
            public PageResult<Result> searchPageByArg(PageQuery<Result> pageQuery, Object... args) {
                return classEntitySearchTemplate.searchPageByArg(entityClass,pageQuery.withTarClass(resultClass),args);
            }

            @Override
            public List<Result> searchListByTreeArg(EntityTemplateTreeArg entityTemplateTreeArg) {
                return classEntitySearchTemplate.searchListByTreeArg(entityClass,resultClass,entityTemplateTreeArg);
            }

            @Override
            public PageResult<Result> searchPageByTreeArg(PageQuery<Result> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg) {
                return classEntitySearchTemplate.searchPageByTreeArg(entityClass,pageQuery.withTarClass(resultClass),entityTemplateTreeArg);
            }
        };
    }

    /*
    Relationship class
     */

    default <LeftEntity,RightEntity> EntityHolderManageTemplate manage(RelationshipClass<LeftEntity,RightEntity> entity) {
        RelationshipEntityManageTemplate classEntityManageTemplate = getRelationshipEntityManageTemplate();

        if(classEntityManageTemplate == null){
            throw new UnsupportedOperationException("RelationshipEntityManageTemplate not support!");
        }

        return new EntityHolderManageTemplate() {
            @Override
            public Boolean checkTable() {
                return classEntityManageTemplate.checkTable(entity);
            }

            @Override
            public void createTable() {
                classEntityManageTemplate.createTable(entity);
            }

            @Override
            public void dropTable() {
                classEntityManageTemplate.dropTable(entity);
            }
        };
    }


    //TODO

    /*
    EntityQuery
     */

    default <E> EntityQueryBuilder<E> query(Class<E> entity){
        return EntityQueryBuilder.create(entity);
    }

    default <LeftE,RightE> LeftRightSelector<EntityQueryBuilder<LeftE>,EntityQueryBuilder<RightE>> query(RelationshipClass<LeftE,RightE> entity){
        return new LeftRightSelector<EntityQueryBuilder<LeftE>,EntityQueryBuilder<RightE>>() {
            @Override
            public EntityQueryBuilder<LeftE> left() {
                return EntityQueryBuilder.create(entity.getLeftClass());
            }

            @Override
            public EntityQueryBuilder<RightE> right() {
                return EntityQueryBuilder.create(entity.getRightClass());
            }
        };
    }

}
