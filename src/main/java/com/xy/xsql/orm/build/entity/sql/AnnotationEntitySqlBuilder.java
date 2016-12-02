package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.build.entity.data.AnnotationEntityDataBuilder;
import com.xy.xsql.orm.data.config.AnnotationEntitySqlBuilderConfig;
import com.xy.xsql.orm.data.entity.EntityTemplateData;
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
    private AnnotationEntityDataBuilder entityDataBuilder;

    /**
     * Class
     */
    private Class<?> entityClass;

    /**
     * Data
     */
    private EntityTemplateData data;

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
     * @param config EntitySqlBuilderConfig
     * @return This
     */
    public AnnotationEntitySqlBuilder config(AnnotationEntitySqlBuilderConfig config) {
        if(config == null){
            this.config = new AnnotationEntitySqlBuilderConfig()
                    .typeMapper(AllVarCharTypeMapper.class)
                    .dialectEntitySqlBuilder(BaseDialectEntitySqlBuilder.class);
        }else{
            this.config = config;
        }

        //create EntityDataBuilder and config it
        this.entityDataBuilder = new AnnotationEntityDataBuilder()
                .tablePrefix(this.config.getTablePrefix())
                .supportMultipleKey(this.config.isSupportMultipleKey())
                .scanStatus(this.config.isScanStatus())
                .scanParam(this.config.isScanParam())
                .scanOrder(this.config.isScanOrder())
                .scanEntity(this.config.isScanEntity());

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
        if(this.entityDataBuilder == null){
            this.config(null);
        }

        if(this.entityClass == null ||
                !this.entityClass.equals(aClass)){
            this.entityClass = aClass;

            this.data = entityDataBuilder.build(aClass);
            this.dialectEntitySqlBuilder.cacheData(this.data);
        }
        return dialectEntitySqlBuilder;
    }
}
