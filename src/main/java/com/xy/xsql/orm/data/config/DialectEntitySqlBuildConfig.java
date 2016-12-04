package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class DialectEntitySqlBuildConfig extends AnnotationEntityTemplateBuildConfig {

    private boolean useSentenceBuilder;
    private boolean onlySelectUseStatus;
    private Class<? extends TypeMapper> typeMapper;

    public boolean isUseSentenceBuilder() {
        return useSentenceBuilder;
    }

    public DialectEntitySqlBuildConfig useSentenceBuilder(boolean useSentenceBuilder) {
        this.useSentenceBuilder = useSentenceBuilder;
        return this;
    }

    public boolean isOnlySelectUseStatus() {
        return onlySelectUseStatus;
    }

    public DialectEntitySqlBuildConfig onlySelectUseStatus(boolean onlySelectUseStatus) {
        this.onlySelectUseStatus = onlySelectUseStatus;
        return this;
    }

    public Class<? extends TypeMapper> getTypeMapperClass() {
        return typeMapper;
    }

    public DialectEntitySqlBuildConfig typeMapperClass(Class<? extends TypeMapper> typeMapper) {
        this.typeMapper = typeMapper;
        return this;
    }
}
