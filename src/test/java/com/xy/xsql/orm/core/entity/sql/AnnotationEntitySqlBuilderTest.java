package com.xy.xsql.orm.core.entity.sql;

import com.xy.xsql.orm.core.entity.sql.agreement.SqlEntityCRUD;
import com.xy.xsql.orm.data.config.AnnotationEntitySqlBuildConfig;
import com.xy.xsql.orm.dialect.none.AllVarCharTypeMapper;
import com.xy.xsql.orm.test.bean.User;
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
        AnnotationEntitySqlBuilder builder = new AnnotationEntitySqlBuilder();
        EntitySqlBuilder sqlBuilder = builder.build(User.class);
        assert sqlBuilder != null;
        assert sqlBuilder.getTemplate() != null;
    }

    /**
     * Customize core
     */
    @Test
    public void testConfig(){
        AnnotationEntitySqlBuildConfig config = new AnnotationEntitySqlBuildConfig();

        AnnotationEntitySqlBuilder builder = new AnnotationEntitySqlBuilder()
                .config(config
                        .withDialectEntitySqlBuilder(TestEntitySqlBuilder.class)
                        .withTypeMapper(new AllVarCharTypeMapper())
                        .withOnlySelectUseStatus(true));
        EntitySqlBuilder sqlBuilder = builder.build(User.class);
        assert sqlBuilder != null;
        assert sqlBuilder.toAgreementSql(SqlEntityCRUD.class).getSelectByIdSql(sqlBuilder.getTemplate()) != null;
    }


    /**
     * 4 Test
     */
    public static class TestEntitySqlBuilder extends BaseEntitySqlBuilder {
        @Override
        public String sqlSelectById(){
            return "SELECT * FORM " + super.template.getTable().getName();
        }
    }
}
