package com.xy.xsql.entity.test.config.db.mssql;

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
@PropertySource("classpath:db-mssql.properties")
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
                    String user,
            @Value("${jdbc.password}")
                    String password,
            @Value("${jdbc.initialPoolSize}")
                    Integer initialPoolSize,
            @Value("${jdbc.maxActive}")
                    Integer maxPoolSize,
            @Value("${jdbc.minPoolSize}")
                    Integer minPoolSize,
            @Value("${jdbc.maxIdleTime}")
                    Integer maxIdleTime,
            @Value("${jdbc.idleConnectionTestPeriod}")
                    Integer idleConnectionTestPeriod,
            @Value("${jdbc.automaticTestTable}")
                    String automaticTestTable
    ) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverClassName);
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setInitialPoolSize(initialPoolSize);
        comboPooledDataSource.setMaxPoolSize(maxPoolSize);
        comboPooledDataSource.setMinPoolSize(minPoolSize);
        comboPooledDataSource.setMaxIdleTime(maxIdleTime);
        comboPooledDataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        comboPooledDataSource.setAutomaticTestTable(automaticTestTable);
        return comboPooledDataSource;
    }
}
