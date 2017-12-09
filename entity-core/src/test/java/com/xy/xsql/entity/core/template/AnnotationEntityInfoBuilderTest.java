package com.xy.xsql.entity.core.template;

import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.test.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityInfoBuilderTest {


    @Before
    public void setUp(){

    }

    /**
     * Default core
     */
    @Test
    public void testBuild(){
        EAnnotationEntityInfoBuilder builder = new EAnnotationEntityInfoBuilder();
        EntityInfo data = builder.build(User.class);

        Assert.assertNotNull(data);
        Assert.assertEquals(data.getTable().getName(),"b_user");
    }

    /**
     * Customize core
     */
    @Test
    public void testConfig(){
        EAnnotationEntityInfoBuilder builder = new EAnnotationEntityInfoBuilder()
                .configStart()
                    .withSeparator("_")
                    .withNamePrefix("prefix")
                    .withNameSuffix("suffix")
                    .withAliasNamePrefix("p")
    //                .withAliasNameSuffix("s")
                    .withSupportMultipleKey(true)
                    .withScanStatus(true)
                    .withScanParam(true)
                    .withScanOrder(true)
                    .withScanLink(true)
                    .out();

        EntityInfo data = builder.build(User.class);
        Assert.assertNotNull(data);
        Assert.assertEquals(data.getTable().getName(),"prefix_b_user_suffix");
//        Assert.assertEquals(template.getTable().getAliasName(),"p_u_s");
        Assert.assertEquals(data.getTable().getAliasName(),"p_u");

        Assert.assertEquals(data.getColumns().get(3).getName(),"prefix_type_suffix");
//        Assert.assertEquals(template.getColumns().get(3).getAliasName(),"p_typeID_s");
        Assert.assertEquals(data.getColumns().get(3).getAliasName(),"p_typeID");
        Assert.assertEquals(data.getKeys().get(0).getName(),"prefix_id_suffix");
        Assert.assertEquals(data.getStatus().getName(),"prefix_status_suffix");
        Assert.assertEquals(data.getOrders().get(0).getColumn().getName(),"prefix_time_suffix");

        EntityLink link = data.getLinks().get(0);
        Assert.assertEquals(link.getColumn().getName(),"prefix_type_suffix");
        EntityInfo dataSub = link.getTemplate();
        Assert.assertEquals(dataSub.getTable().getName(),"prefix_b_user_type_suffix");
//        Assert.assertEquals(dataSub.getTable().getAliasName(),"p_typeID_s_ut_s");
        Assert.assertEquals(dataSub.getTable().getAliasName(),"p_typeID_ut");
    }

}
