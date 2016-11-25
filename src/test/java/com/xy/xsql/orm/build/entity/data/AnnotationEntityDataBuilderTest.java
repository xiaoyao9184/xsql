package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.data.entity.EntityTemplateData;
import com.xy.xsql.orm.test.bean.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityDataBuilderTest {


    @Before
    public void setUp(){

    }

    /**
     * Default build
     */
    @Test
    public void testBuild(){
        AnnotationEntityDataBuilder builder = new AnnotationEntityDataBuilder();
        EntityTemplateData data = builder.build(User.class);
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

        EntityTemplateData data = builder.build(User.class);
        assert data != null;
        assert data.getTableStatus() != null;
    }

}
