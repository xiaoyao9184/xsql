package com.xy.xsql.orm.core.entity.sql;

import com.xy.xsql.orm.core.ConfigBuilder;
import com.xy.xsql.orm.core.ConfigChild;
import com.xy.xsql.orm.core.entity.template.AnnotationEntityTemplateBuilder;
import com.xy.xsql.orm.data.config.AnnotationEntitySqlBuildConfig;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import com.xy.xsql.orm.dialect.none.BaseEntitySql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AnnotationEntitySqlBuilder
 * core EntitySqlBuilder by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuilder implements
        ConfigChild<AnnotationEntitySqlBuildConfig>,
        ConfigBuilder<
                AnnotationEntitySqlBuilder,
                AnnotationEntitySqlBuildConfig,
                Class<?>,
                AnnotationEntitySqlBuilder> {

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


    //ConfigInOut
    @Override
    public AnnotationEntitySqlBuilder config(AnnotationEntitySqlBuildConfig config) {
        this.config = config;
        return this;
    }

    //Build
    @Override
    public AnnotationEntitySqlBuilder build(Class<?> aClass) {
        if(config == null){
            this.config = new AnnotationEntitySqlBuildConfig()
                    .withDialectEntitySqlBuilder(StringEntitySqlBuilder.class)
                    .configTemplate()
                        .withTypeMapper(new AllVarCharTypeMapper())
                        .withScanAll(true)
                        .out()
                    .configDialect()
                        .withAllInThisDialectClass(BaseEntitySql.class)
                        .out();
        }
        if(this.entityTemplateBuilder == null){
            //create EntityTemplateBuilder and config it
            this.entityTemplateBuilder = new AnnotationEntityTemplateBuilder()
                    .config(this.config.getTemplateConfig());
        }

        //create EntitySqlBuilder
        try {
            Class dialectEntitySqlBuilderClass = this.config.getDialectEntitySqlBuilder();
            this.entitySqlBuilder = (EntitySqlBuilder) dialectEntitySqlBuilderClass.newInstance();
        } catch (Exception e) {
            log.error("Cant create Dialect EntitySqlBuilder, create default.",e);
            this.entitySqlBuilder = new StringEntitySqlBuilder();
        }

        if(this.entityClass == null ||
                !this.entityClass.equals(aClass)){
            this.entityClass = aClass;

            this.entityTemplate = entityTemplateBuilder.build(aClass);
            this.entitySqlBuilder.cacheTemplate(this.entityTemplate);
            this.entitySqlBuilder.cacheConfig(this.config.getDialectConfig());
            this.entitySqlBuilder.build(null);
        }
//        return entitySqlBuilder;
        return this;
    }

    //Config Child
    @Override
    public AnnotationEntitySqlBuildConfig configStart() {
        if(this.config == null){
            this.config = new AnnotationEntitySqlBuildConfig();
        }
        return this.config.in(this);
    }

    /**
     * To Real Type
     * @param <T> T
     * @return T
     */
    @SuppressWarnings("unchecked")
    public <T> T to(){
        return (T) entitySqlBuilder;
    }

}
