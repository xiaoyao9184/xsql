package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.test.bean.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityTemplateBuilderTest {


    @Before
    public void setUp(){

    }

    /**
     * Default build
     */
    @Test
    public void testBuild(){
        AnnotationEntityTemplateBuilder builder = new AnnotationEntityTemplateBuilder();
        EntityTemplate data = builder.build(User.class);
        assert data != null;
        assert data.getTable().getName().equals("b_user");
    }

    /**
     * Customize build
     */
    @Test
    public void testConfig(){
        AnnotationEntityTemplateBuilder builder = new AnnotationEntityTemplateBuilder()
                .tablePrefix("")
                .supportMultipleKey(true)
                .scanStatus(true)
                .scanParam(true)
                .scanOrder(true);

        EntityTemplate data = builder.build(User.class);
        assert data != null;
        assert data.getStatus() != null;
    }

}
