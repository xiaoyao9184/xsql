package com.xy.xsql.entity.core.template;

import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.model.template.EntityOrder;
import com.xy.xsql.entity.model.template.EntityTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class EntityOrderExpanderTest {

    private EntityInfo entityInfo;

    /**
     * EntityInfo Tree:
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

        EntityInfo template4Float = new EntityInfo()
                .withClass(Float.class)
                .withTable(new EntityTable().withName("Float"))
                .withOrders(orderList);

        EntityInfo template4Double = new EntityInfo()
                .withClass(Double.class)
                .withTable(new EntityTable().withName("Double"))
                .withOrders(orderList);

        List<EntityLink> linkList4Integer = new ArrayList<>();
        linkList4Integer.add(new EntityLink().withTemplate(template4Float));
        linkList4Integer.add(new EntityLink().withTemplate(template4Double));

        EntityInfo template4Integer = new EntityInfo()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(linkList4Integer)
                .withOrders(orderList);

        List<EntityLink> linkList4Byte = new ArrayList<>();
        linkList4Byte.add(new EntityLink().withTemplate(template4Integer));

        entityInfo = new EntityInfo()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(linkList4Byte)
                .withOrders(orderList);
    }

    @Test
    public void testExpandAll(){
        List<EntityOrder> listAssert = new EntityOrderExpander()
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),8);
    }


    @Test
    public void testExpandDeep(){
        List<EntityOrder> listAssert = new EntityOrderExpander()
                .withDeepMax(0)
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),2);


        List<EntityOrder> listAssert1 = new EntityOrderExpander()
                .withDeepMax(1)
                .build(entityInfo);

        Assert.assertEquals(listAssert1.size(),4);
    }
}
