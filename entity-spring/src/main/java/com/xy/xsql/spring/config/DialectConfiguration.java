package com.xy.xsql.spring.config;

import java.util.Properties;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class DialectConfiguration {


    public static String DIALECT = "xsql.entity.dialect";
    public static String DIALECT_TEMPLATE_RENDERER = "xsql.entity.dialect.template.renderer";
    public static String DIALECT_TYPE_MAPPER = "xsql.entity.dialect.type.mapper";

    public static String TABLE_AUTO_CREATE = "xsql.entity.table.auto.create";

    public static String PAGE_ROW_NUMBER_NAME = "xsql.entity.page.row.number.name";


    private Properties configurationProperties;

    public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {
        this.configurationProperties = sqlSessionFactoryProperties;
    }

    public Properties getConfigurationProperties() {
        return configurationProperties;
    }

    public String getOrmDialect() {
        return configurationProperties.getProperty(DIALECT);
    }

    public String getOrmDialectTypeMapper() {
        return configurationProperties.getProperty(DIALECT_TYPE_MAPPER);
    }

    public String getOrmDialectEntitySql() {
        return configurationProperties.getProperty(DIALECT_TEMPLATE_RENDERER);
    }

    public Boolean isOrmTableAutoCreate() {
        return Boolean.parseBoolean(configurationProperties.getProperty(TABLE_AUTO_CREATE));
    }

    public String getOrmPageRowNumberName(){
        return configurationProperties.getProperty(PAGE_ROW_NUMBER_NAME);
    }
}
