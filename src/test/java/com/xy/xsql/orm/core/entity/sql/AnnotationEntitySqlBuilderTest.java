package com.xy.xsql.orm.core.entity.sql;

import com.xy.xsql.orm.core.entity.sql.agreement.*;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import com.xy.xsql.orm.dialect.none.BaseEntitySql;
import com.xy.xsql.orm.test.bean.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntitySqlBuilderTest {

    /**
     * Default core
     */
    @Test
    public void testBuild(){
        EntitySqlBuilder sqlBuilder = new AnnotationEntitySqlBuilder()
                .build(User.class).to();
        Assert.assertNotNull(sqlBuilder);
        Assert.assertNotNull(sqlBuilder
                .getTemplate());
    }

    /**
     * Customize core
     */
    @Test
    public void testConfig(){
        AnnotationEntitySqlBuilder builder = new AnnotationEntitySqlBuilder()
                .configStart()
                        .withDialectEntitySqlBuilder(StringEntitySqlBuilder.class)
                        .withOnlySelectUseStatus(true)
                        .configTemplate()
                            .withTypeMapper(new AllVarCharTypeMapper())
                            .withSeparator("_")
                            .withScanAll(true)
                            .out()
                        .configDialect()
                            .withUseSentenceBuilder(false)
                            .withOnlySelectUseStatus(false)
                            .withAllInThisDialectClass(BaseEntitySql.class)
                            .out()
                        .out()
                .build(User.class);

        EntitySqlBuilder sqlBuilder = builder.to();
        Assert.assertNotNull(sqlBuilder);
        Assert.assertNotNull(sqlBuilder
                .toAgreementSql(SqlEntityCRUD.class)
                .getSelectByIdSql(sqlBuilder.getTemplate()));

        StringEntitySqlBuilder stringSqlBuilder = builder.to();
        Assert.assertNotNull(stringSqlBuilder);
        Assert.assertNotNull(stringSqlBuilder.sqlSelectById());
    }

}
