package com.xy.xsql.entity.core.template;


import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.model.template.EntityTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class EntityLinkExpanderTest {

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
        EntityInfo template4Float = new EntityInfo()
                .withClass(Float.class)
                .withTable(new EntityTable().withName("Float"));

        EntityInfo template4Double = new EntityInfo()
                .withClass(Double.class)
                .withTable(new EntityTable().withName("Double"));

        List<EntityLink> list4Integer = new ArrayList<>();
        list4Integer.add(new EntityLink().withTemplate(template4Float));
        list4Integer.add(new EntityLink().withTemplate(template4Double));

        EntityInfo template4Integer = new EntityInfo()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(list4Integer);

        List<EntityLink> linkList4Byte = new ArrayList<>();
        linkList4Byte.add(new EntityLink().withTemplate(template4Integer));

        entityInfo = new EntityInfo()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(linkList4Byte);
    }

    @Test
    public void testExpandAll(){
        List<EntityLink> listAssert = new EntityLinkExpander()
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),3);
    }


    @Test
    public void testExpandDeep(){
        List<EntityLink> listAssert = new EntityLinkExpander()
                .withDeepMax(0)
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),1);


        List<EntityLink> listAssert1 = new EntityLinkExpander()
                .withDeepMax(1)
                .build(entityInfo);

        Assert.assertEquals(listAssert1.size(),3);
    }
}
