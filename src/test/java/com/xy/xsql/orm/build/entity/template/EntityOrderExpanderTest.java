package com.xy.xsql.orm.build.entity.template;

import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityOrder;
import com.xy.xsql.orm.data.entity.EntityTable;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class EntityOrderExpanderTest {

    private EntityTemplate entityTemplate;

    /**
     * EntityTemplate Tree:
     * Byte
     *   Integer
     *     Float
     *     Double
     */
    @Before
    public void init(){
        List<EntityOrder> orderList = new ArrayList<>();
        orderList.add(new EntityOrder());
        orderList.add(new EntityOrder());

        EntityTemplate template4Float = new EntityTemplate()
                .withClass(Float.class)
                .withTable(new EntityTable().withName("Float"))
                .withOrders(orderList);

        EntityTemplate template4Double = new EntityTemplate()
                .withClass(Double.class)
                .withTable(new EntityTable().withName("Double"))
                .withOrders(orderList);

        List<EntityLink> linkList4Integer = new ArrayList<>();
        linkList4Integer.add(new EntityLink().withTemplate(template4Float));
        linkList4Integer.add(new EntityLink().withTemplate(template4Double));

        EntityTemplate template4Integer = new EntityTemplate()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(linkList4Integer)
                .withOrders(orderList);

        List<EntityLink> linkList4Byte = new ArrayList<>();
        linkList4Byte.add(new EntityLink().withTemplate(template4Integer));

        entityTemplate = new EntityTemplate()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(linkList4Byte)
                .withOrders(orderList);
    }

    @Test
    public void testExpandAll(){
        List<EntityOrder> listAssert = new EntityOrderExpander()
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),8);
    }


    @Test
    public void testExpandDeep(){
        List<EntityOrder> listAssert = new EntityOrderExpander()
                .withDeepMax(0)
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),2);


        List<EntityOrder> listAssert1 = new EntityOrderExpander()
                .withDeepMax(1)
                .build(entityTemplate);

        Assert.assertEquals(listAssert1.size(),4);
    }
}
