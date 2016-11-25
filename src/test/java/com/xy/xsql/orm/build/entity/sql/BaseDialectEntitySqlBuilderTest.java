package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.build.entity.data.AnnotationEntityDataBuilder;
import com.xy.xsql.orm.data.entity.SqlEntityData;
import com.xy.xsql.orm.test.bean.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class BaseDialectEntitySqlBuilderTest {


    @Before
    public void setUp(){

    }

    /**
     * Default build
     */
    @Test
    public void testBuild(){
        AnnotationEntityDataBuilder builder = new AnnotationEntityDataBuilder();
        SqlEntityData data = builder.build(User.class);
        assert data != null;
        assert data.getTableName().getName().equals("b_user");
    }

    /**
     * Customize build
     */
    @Test
    public void testConfig(){
        AnnotationEntityDataBuilder builder = new AnnotationEntityDataBuilder()
                .tablePrefix("")
                .supportMultipleKey(true)
                .scanStatus(true)
                .scanParam(true)
                .scanOrder(true);

        SqlEntityData data = builder.build(User.class);
        assert data != null;
        assert data.getTableStatus() != null;
    }

}
