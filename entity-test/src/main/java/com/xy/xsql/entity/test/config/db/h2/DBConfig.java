package com.xy.xsql.entity.test.config.db.h2;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xy.xsql.spring.config.DialectConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by xiaoyao9184 on 2017/3/24.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db-h2.properties")
public class DBConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public DialectConfiguration dialectConfiguration(
            @Value("${xsql.entity.dialect}")
                    String dialect,
            @Value("${xsql.entity.dialect.template.renderer}")
                    String dialectTemplateRenderer,
            @Value("${xsql.entity.dialect.type.mapper}")
                    String dialectTypeMapper,
            @Value("${xsql.entity.table.auto.create}")
                    String tableAutoCreate,
            @Value("${xsql.entity.page.row.number.name}")
                    String rowNumberName
    ){
        DialectConfiguration dialectConfiguration = new DialectConfiguration();
        dialectConfiguration.setConfigurationProperties(new Properties());
        dialectConfiguration.getConfigurationProperties().setProperty(DialectConfiguration.DIALECT,dialect);
        dialectConfiguration.getConfigurationProperties().setProperty(DialectConfiguration.DIALECT_TEMPLATE_RENDERER,dialectTemplateRenderer);
        dialectConfiguration.getConfigurationProperties().setProperty(DialectConfiguration.DIALECT_TYPE_MAPPER,dialectTypeMapper);
        dialectConfiguration.getConfigurationProperties().setProperty(DialectConfiguration.TABLE_AUTO_CREATE,tableAutoCreate);
        dialectConfiguration.getConfigurationProperties().setProperty(DialectConfiguration.PAGE_ROW_NUMBER_NAME,rowNumberName);
        return dialectConfiguration;
    }

    @Bean
    public ComboPooledDataSource dataSource(
            @Value("${jdbc.driverClassName}")
                    String driverClassName,
            @Value("${jdbc.url}")
                    String jdbcUrl,
            @Value("${jdbc.username}")
                    String user
    ) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverClassName);
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        comboPooledDataSource.setUser(user);
        return comboPooledDataSource;
    }
}
