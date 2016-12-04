package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.build.entity.data.AnnotationEntityTemplateBuilder;
import com.xy.xsql.orm.data.config.AnnotationEntitySqlBuilderConfig;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AnnotationEntitySqlBuilder
 * build DialectEntitySqlBuilder by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuilder implements BaseBuilder<Class<?>,DialectEntitySqlBuilder> {

    //Fields

    /**
     * Log
     */
    private Logger log;

    /**
     * 配置
     */
    private AnnotationEntitySqlBuilderConfig config;

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
    private EntityTemplate data;

    /**
     * SqlBuilder
     */
    private DialectEntitySqlBuilder dialectEntitySqlBuilder;



    public AnnotationEntitySqlBuilder(){
        this.log = LoggerFactory.getLogger(this.getClass());
    }


    //Config
    /**
     * config
     * @param config DialectEntitySqlBuilderConfig
     * @return This
     */
    public AnnotationEntitySqlBuilder config(AnnotationEntitySqlBuilderConfig config) {
        if(config == null){
            this.config = new AnnotationEntitySqlBuilderConfig()
                    .withDialectEntitySqlBuilder(BaseDialectEntitySqlBuilder.class)
                    .withTypeMapper(new AllVarCharTypeMapper());
        }else{
            this.config = config;
        }

        //create EntityDataBuilder and config it
        this.entityTemplateBuilder = new AnnotationEntityTemplateBuilder()
                .withTypeMapper(this.config.getTypeMapper())
                .withSeparator(this.config.getSeparator())
                .withNamePrefix(this.config.getNamePrefix())
                .withNameSuffix(this.config.getNamePrefix())
                .withAliasNamePrefix(this.config.getAliasNamePrefix())
//                .withAliasNameSuffix(this.config.getAliasNameSuffix())
                .withSupportMultipleKey(this.config.isSupportMultipleKey())
                .withScanStatus(this.config.isScanStatus())
                .withScanParam(this.config.isScanParam())
                .withScanOrder(this.config.isScanOrder())
                .withScanEntity(this.config.isScanEntity());

        //create DialectEntitySqlBuilder
        try {
            Class dialectEntitySqlBuilderClass = config.getDialectEntitySqlBuilder();
            this.dialectEntitySqlBuilder = (DialectEntitySqlBuilder) dialectEntitySqlBuilderClass.newInstance();
        } catch (Exception e) {
            log.error("Cant create Dialect DialectEntitySqlBuilder, create default.",e);
            this.dialectEntitySqlBuilder = new BaseDialectEntitySqlBuilder();
        }

        //config DialectEntitySqlBuilder
        this.dialectEntitySqlBuilder.cacheConfig(this.config.toEntitySqlBuilderConfig());

        return this;
    }


    //Build
    @Override
    public DialectEntitySqlBuilder build(Class<?> aClass) {
        if(this.entityTemplateBuilder == null){
            this.config(null);
        }

        if(this.entityClass == null ||
                !this.entityClass.equals(aClass)){
            this.entityClass = aClass;

            this.data = entityTemplateBuilder.build(aClass);
            this.dialectEntitySqlBuilder.cacheData(this.data);
        }
        return dialectEntitySqlBuilder;
    }
}
