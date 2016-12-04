package com.xy.xsql.orm.data.config;

import com.xy.xsql.orm.core.sentence.sql.DialectSentenceSqlBuilder;
import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class SentenceSqlBuilderConfig {

    private String tablePrefix;
    private Class<? extends TypeMapper> typeMapper;
    private Class<? extends DialectSentenceSqlBuilder> dialectSqlBuilder;

    public String getTablePrefix() {
        return tablePrefix;
    }

    public SentenceSqlBuilderConfig tablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public Class<? extends TypeMapper> getTypeMapper() {
        return typeMapper;
    }

    public SentenceSqlBuilderConfig typeMapper(Class<? extends TypeMapper> typeMapper) {
        this.typeMapper = typeMapper;
        return this;
    }

    public Class<? extends DialectSentenceSqlBuilder> getDialectSqlBuilder() {
        return dialectSqlBuilder;
    }

    public SentenceSqlBuilderConfig dialectSqlBuilder(Class<? extends DialectSentenceSqlBuilder> dialectSqlBuilder) {
        this.dialectSqlBuilder = dialectSqlBuilder;
        return this;
    }


}
