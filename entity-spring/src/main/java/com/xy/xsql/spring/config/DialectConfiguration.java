package com.xy.xsql.spring.config;

import java.util.Properties;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class DialectConfiguration {


    public static String ORM_DIALECT = "xsql.orm.dialect";
    public static String ORM_DIALECT_ENTITY_SQL = "xsql.orm.dialect.entity.sql";
    public static String ORM_DIALECT_TYPE_MAPPER = "xsql.orm.dialect.type.mapper";

    public static String ORM_TABLE_AUTO_CREATE = "xsql.orm.table.auto.create";

    public static String ORM_PAGE_ROW_NUMBER_NAME = "xsql.orm.page.row.number.name";


    private Properties configurationProperties;

    public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {
        this.configurationProperties = sqlSessionFactoryProperties;
    }

    public Properties getConfigurationProperties() {
        return configurationProperties;
    }

    public String getOrmDialect() {
        return configurationProperties.getProperty(ORM_DIALECT);
    }

    public String getOrmDialectTypeMapper() {
        return configurationProperties.getProperty(ORM_DIALECT_TYPE_MAPPER);
    }

    public String getOrmDialectEntitySql() {
        return configurationProperties.getProperty(ORM_DIALECT_ENTITY_SQL);
    }

    public Boolean isOrmTableAutoCreate() {
        return Boolean.parseBoolean(configurationProperties.getProperty(ORM_TABLE_AUTO_CREATE));
    }

    public String getOrmPageRowNumberName(){
        return configurationProperties.getProperty(ORM_PAGE_ROW_NUMBER_NAME);
    }
}
