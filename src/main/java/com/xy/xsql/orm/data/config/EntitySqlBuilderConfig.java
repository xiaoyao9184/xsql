package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class EntitySqlBuilderConfig extends AnnotationEntityDataBuilderConfig {

    private boolean useSentenceBuilder;
    private boolean onlySelectUseStatus;
    private Class<? extends TypeMapper> typeMapper;

    public boolean isUseSentenceBuilder() {
        return useSentenceBuilder;
    }

    public EntitySqlBuilderConfig useSentenceBuilder(boolean useSentenceBuilder) {
        this.useSentenceBuilder = useSentenceBuilder;
        return this;
    }

    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public EntitySqlBuilderConfig onlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
        return this;
    }

    public Class<? extends TypeMapper> getTypeMapper() {
        return typeMapper;
    }

    public EntitySqlBuilderConfig typeMapper(Class<? extends TypeMapper> typeMapper) {
        this.typeMapper = typeMapper;
        return this;
    }
}
