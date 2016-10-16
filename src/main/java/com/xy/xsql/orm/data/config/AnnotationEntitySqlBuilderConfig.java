package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.build.entity.sql.DialectEntitySqlBuilder;
import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuilderConfig extends AnnotationEntityDataBuilderConfig {

    private boolean onlySelectUseStatus;
    private Class<? extends TypeMapper> typeMapper;
    private Class<? extends DialectEntitySqlBuilder> dialectEntitySqlBuilder;

    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public AnnotationEntitySqlBuilderConfig onlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
        return this;
    }

    public Class<? extends TypeMapper> getTypeMapper() {
        return typeMapper;
    }

    public AnnotationEntitySqlBuilderConfig typeMapper(Class<? extends TypeMapper> typeMapper) {
        this.typeMapper = typeMapper;
        return this;
    }

    public Class<? extends DialectEntitySqlBuilder> getDialectEntitySqlBuilder() {
        return dialectEntitySqlBuilder;
    }

    public AnnotationEntitySqlBuilderConfig dialectEntitySqlBuilder(Class<? extends DialectEntitySqlBuilder> dialectEntitySqlBuilder) {
        this.dialectEntitySqlBuilder = dialectEntitySqlBuilder;
        return this;
    }


    public EntitySqlBuilderConfig toEntitySqlBuilderConfig(){
        return new EntitySqlBuilderConfig()
                .typeMapper(this.typeMapper)
                .onlySelectUseStatus(this.onlySelectUseStatus);
    }

}
