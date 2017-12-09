package com.xy.xsql.entity.test.config.spring;

import com.xy.xsql.spring.config.DefaultEntityTemplateConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
@Configuration
@Import(DefaultEntityTemplateConfiguration.class)
public class EntityTemplateConfig {

}
