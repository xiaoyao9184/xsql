package com.xy.xsql.spring.config;

import com.xy.xsql.entity.core.dialect.type.JavaTypeMapperProvider;
import com.xy.xsql.entity.core.meta.table.BaseClassEntityTableMetaBuilder;
import com.xy.xsql.entity.core.meta.table.ClassTableMetaBuilderSelector;
import com.xy.xsql.entity.core.meta.table.JPAClassEntityTableMetaBuilder;
import com.xy.xsql.spring.template.*;
import com.xy.xsql.spring.template.relationship.DefaultRelationshipEntityManageTemplate;
import com.xy.xsql.spring.template.relationship.DefaultRelationshipEntityManager;
import com.xy.xsql.spring.template.relationship.RelationshipEntityManageTemplate;
import com.xy.xsql.spring.template.relationship.RelationshipEntityManager;
import com.xy.xsql.spring.template.simple.*;
import org.springframework.context.annotation.Bean;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultEntityTemplateConfiguration {

    @Bean
    public EntityTemplate entityTemplate(){
        return new DefaultEntityTemplate();
    }


    /**
     * Provide entity class by package
     * Used for consumption in DefaultClassEntityManager
     * @return PackageClassStreamProvider
     */
    @Bean
    public PackageClassStreamProvider packageClassStreamProvider() {
        return new PackageClassStreamProvider();
    }

    /**
     * Provide JavaTypeMapper by entity class
     * @return JavaTypeMapperProvider
     */
    @Bean
    public JavaTypeMapperProvider typeMapperProvider() {
        return new JavaTypeMapperProvider(null,"com.xy.xsql.entity.core.dialect");
    }

    /**
     * Select ClassTableMetaBuilder by entity class
     * @return ClassTableMetaBuilderSelector
     */
    @Bean
    public ClassTableMetaBuilderSelector tableMetaBuilderSelector() {
        ClassTableMetaBuilderSelector selector = new ClassTableMetaBuilderSelector();
        selector.registered(JPAClassEntityTableMetaBuilder::new, JPAClassEntityTableMetaBuilder::checkSupport);
        selector.registered(BaseClassEntityTableMetaBuilder::new, BaseClassEntityTableMetaBuilder::checkSupport);
        return selector;
    }



    /*
    Class
     */

    /**
     *
     * @return
     */
    @Bean
    public ClassEntityManager entityTemplateManager() {
        return new DefaultClassEntityManager();
    }

    @Bean
    public ClassEntityManageTemplate entityManageTemplate(){
        return new DefaultClassEntityManageTemplate();
    }

    @Bean
    public ClassEntityCRUDTemplate entityCRUDTemplate(){
        return new DefaultClassEntityCRUDTemplate();
    }

    @Bean
    public ClassEntitySearchTemplate entitySearchTemplate(){
        return new DefaultClassEntitySearchTemplate();
    }


    /*
    Relationship
     */

    @Bean
    public RelationshipEntityManager relationshipEntityTemplateManager() {
        return new DefaultRelationshipEntityManager();
    }

    @Bean
    public RelationshipEntityManageTemplate relationshipEntityManageTemplate(){
        return new DefaultRelationshipEntityManageTemplate();
    }
}
