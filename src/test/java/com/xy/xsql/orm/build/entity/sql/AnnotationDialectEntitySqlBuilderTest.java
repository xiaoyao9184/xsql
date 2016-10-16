package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.data.config.AnnotationEntitySqlBuilderConfig;
import com.xy.xsql.orm.data.config.EntitySqlBuilderConfig;
import com.xy.xsql.orm.mapping.type.AllVarCharTypeMapper;
import com.xy.xsql.orm.test.bean.User;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationDialectEntitySqlBuilderTest {

    /**
     * Default build
     */
    @Test
    public void testBuild(){
        AnnotationEntitySqlBuilder builder = new AnnotationEntitySqlBuilder();
        DialectEntitySqlBuilder sqlBuilder = builder.build(User.class);
        assert sqlBuilder != null;
        assert sqlBuilder.sqlSelectById() != null;
    }

    /**
     * Customize build
     */
    @Test
    public void testConfig(){
        AnnotationEntitySqlBuilderConfig config = new AnnotationEntitySqlBuilderConfig();

        AnnotationEntitySqlBuilder builder = new AnnotationEntitySqlBuilder()
                .config(config
                        .dialectEntitySqlBuilder(TestDialectDialectEntitySqlBuilder.class)
                        .typeMapper(AllVarCharTypeMapper.class)
                        .onlySelectUseStatus(true));
        DialectEntitySqlBuilder sqlBuilder = builder.build(User.class);
        assert sqlBuilder != null;
        assert sqlBuilder.sqlSelectById().equals("SELECT * FORM b_user");
    }


    /**
     * 4 Test
     */
    public static class TestDialectDialectEntitySqlBuilder extends BaseDialectEntitySqlBuilder {
        @Override
        public String sqlSelectById(){
            return "SELECT * FORM " + super.data.getTableName().getRealName();
        }
    }
}
