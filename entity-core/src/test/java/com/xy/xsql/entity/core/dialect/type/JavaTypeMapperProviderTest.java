package com.xy.xsql.entity.core.dialect.type;

import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import com.xy.xsql.entity.core.dialect.type.entity.Entity000;
import com.xy.xsql.entity.core.dialect.type.entity.Entity001;
import com.xy.xsql.entity.core.dialect.type.entity.group1.Entity002;
import com.xy.xsql.entity.core.dialect.type.entity.group1.Entity003;
import com.xy.xsql.entity.core.dialect.type.entity.group2.Entity004;
import com.xy.xsql.entity.core.dialect.type.entity.group2.Entity005;
import com.xy.xsql.entity.core.dialect.type.mapper.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
public class JavaTypeMapperProviderTest {

    @Test
    public void testAnnotationRegistrar(){
        JavaTypeMapperProvider provider = new JavaTypeMapperProvider(null,"com.xy.xsql");

        JavaTypeMapper mapper000 = provider.provide(Entity000.class);
        Assert.assertTrue(mapper000 instanceof CustomizeTargetTypeMapper);

        JavaTypeMapper mapper001 = provider.provide(Entity001.class);
        Assert.assertTrue(mapper001 instanceof CustomizeTypeMapper);

        JavaTypeMapper mapper002 = provider.provide(Entity002.class);
        Assert.assertTrue(mapper002 instanceof EntitiesTypeMapper);

        JavaTypeMapper mapper003 = provider.provide(Entity003.class);
        Assert.assertTrue(mapper003 instanceof PackagesTypeMapper);

        JavaTypeMapper mapper004 = provider.provide(Entity004.class);
        Assert.assertTrue(mapper004 instanceof ConfigurationEntitiesTypeMapper);

        JavaTypeMapper mapper005 = provider.provide(Entity005.class);
        Assert.assertTrue(mapper005 instanceof ConfigurationPackagesTypeMapper);
    }
}