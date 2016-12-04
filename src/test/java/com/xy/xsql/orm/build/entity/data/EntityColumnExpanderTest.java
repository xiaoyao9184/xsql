package com.xy.xsql.orm.build.entity.data;


import com.xy.xsql.orm.data.entity.*;
import com.xy.xsql.orm.data.param.EntityTemplateTreeArg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/2.
 */
public class EntityColumnExpanderTest {

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
        List<EntityColumn> listColumn = new ArrayList<>();
        listColumn.add(new EntityColumn());
        listColumn.add(new EntityColumn());

        EntityTemplate template4Float = new EntityTemplate()
                .withClass(Float.class)
                .withTable(new EntityTable().withName("Float"))
                .withColumns(listColumn);

        EntityTemplate template4Double = new EntityTemplate()
                .withClass(Double.class)
                .withTable(new EntityTable().withName("Float"))
                .withColumns(listColumn);

        List<EntityLink> linkList4Integer = new ArrayList<>();
        linkList4Integer.add(new EntityLink().withTemplate(template4Float));
        linkList4Integer.add(new EntityLink().withTemplate(template4Double));

        EntityTemplate template4Integer = new EntityTemplate()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(linkList4Integer)
                .withColumns(listColumn);

        List<EntityLink> linkList4Byte = new ArrayList<>();
        linkList4Byte.add(new EntityLink().withTemplate(template4Integer));

        entityTemplate = new EntityTemplate()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(linkList4Byte)
                .withColumns(listColumn);
    }

    @Test
    public void testExpandAll(){
        List<EntityColumn> listAssert = new EntityColumnExpander()
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),8);
    }

    @Test
    public void testExpandDeep(){
        List<EntityColumn> listAssert = new EntityColumnExpander()
                .withDeepMax(0)
                .build(entityTemplate);

        Assert.assertEquals(listAssert.size(),2);


        List<EntityColumn> listAssert2 = new EntityColumnExpander()
                .withDeepMax(1)
                .build(entityTemplate);

        Assert.assertEquals(listAssert2.size(),4);
    }

}
