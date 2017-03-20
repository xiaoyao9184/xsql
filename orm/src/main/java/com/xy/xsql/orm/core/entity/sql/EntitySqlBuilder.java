package com.xy.xsql.orm.core.entity.sql;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.orm.data.config.EntityDialectSqlBuildConfig;
import com.xy.xsql.orm.data.entity.EntityTemplate;

/**
 * Entity Sql Builder
 * dialect if you want change default implementation
 * Created by xiaoyao9184 on 2016/1/13.
 */
public interface EntitySqlBuilder extends BaseBuilder<Void,Void> {


    /**
     * cache config for later
     * @param config EntityDialectSqlBuildConfig
     * @return EntitySqlBuilder
     */
    EntitySqlBuilder cacheConfig(EntityDialectSqlBuildConfig config);

    /**
     * cache elementsSentence for later
     * @param template EntityTemplate
     * @return EntitySqlBuilder
     */
    EntitySqlBuilder cacheTemplate(EntityTemplate template);

    /**
     * Check EntitySql support agreement
     * @param agreementClass agreement
     * @return True/False
     */
    Boolean isSupport(Class<?> agreementClass);

    /**
     * Build
     * @param aVoid NULL
     * @return NULL
     */
    Void build(Void aVoid);

    /**
     * To agreement EntitySql
     * @param agreementClass agreement
     * @param <T> agreement
     * @return agreement EntitySql
     */
    <T> T toAgreementSql(Class<T> agreementClass);


    EntityTemplate getTemplate();
}
