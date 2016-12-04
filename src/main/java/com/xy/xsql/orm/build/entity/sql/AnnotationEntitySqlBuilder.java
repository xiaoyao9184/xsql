package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.build.ConfigBuilder;
import com.xy.xsql.orm.build.entity.template.AnnotationEntityTemplateBuilder;
import com.xy.xsql.orm.data.config.AnnotationEntitySqlBuildConfig;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AnnotationEntitySqlBuilder
 * build EntitySqlBuilder by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuilder implements
        ConfigBuilder<
                AnnotationEntitySqlBuilder,
                AnnotationEntitySqlBuildConfig,
                Class<?>,
                EntitySqlBuilder> {

    //Fields

    /**
     * Log
     */
    private Logger log;

    /**
     * 配置
     */
    private AnnotationEntitySqlBuildConfig config;

    /**
     * DataBuilder
     */
    private AnnotationEntityTemplateBuilder entityTemplateBuilder;

    /**
     * Class
     */
    private Class<?> entityClass;

    /**
     * Data
     */
    private EntityTemplate entityTemplate;

    /**
     * SqlBuilder
     */
    private EntitySqlBuilder entitySqlBuilder;



    public AnnotationEntitySqlBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
    }


    //Config
    @Override
    public AnnotationEntitySqlBuilder config(AnnotationEntitySqlBuildConfig config) {
        if(config == null){
            this.config = new AnnotationEntitySqlBuildConfig()
                    .withDialectEntitySqlBuilder(BaseEntitySqlBuilder.class)
                    .withTypeMapper(new AllVarCharTypeMapper());
        }else{
            this.config = config;
        }

        //create EntityTemplateBuilder and config it
        this.entityTemplateBuilder = new AnnotationEntityTemplateBuilder()
                .config(this.config);

        //create EntitySqlBuilder
        try {
            Class dialectEntitySqlBuilderClass = this.config.getDialectEntitySqlBuilder();
            this.entitySqlBuilder = (EntitySqlBuilder) dialectEntitySqlBuilderClass.newInstance();
        } catch (Exception e) {
            log.error("Cant create Dialect EntitySqlBuilder, create default.",e);
            this.entitySqlBuilder = new BaseEntitySqlBuilder();
        }

        //config EntitySqlBuilder
        this.entitySqlBuilder.cacheConfig(this.config.toEntitySqlBuilderConfig());

        return this;
    }

    //Build
    @Override
    public EntitySqlBuilder build(Class<?> aClass) {
        if(this.entityTemplateBuilder == null){
            this.config(null);
        }

        if(this.entityClass == null ||
                !this.entityClass.equals(aClass)){
            this.entityClass = aClass;

            this.entityTemplate = entityTemplateBuilder.build(aClass);
            this.entitySqlBuilder.cacheTemplate(this.entityTemplate);
        }
        return entitySqlBuilder;
    }

}
