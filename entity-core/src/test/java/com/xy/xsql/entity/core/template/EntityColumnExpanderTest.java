package com.xy.xsql.entity.core.template;


import com.xy.xsql.entity.model.template.EntityColumn;
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
public class EntityColumnExpanderTest {

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
        List<EntityColumn> listColumn = new ArrayList<>();
        listColumn.add(new EntityColumn());
        listColumn.add(new EntityColumn());

        EntityInfo template4Float = new EntityInfo()
                .withClass(Float.class)
                .withTable(new EntityTable().withName("Float"))
                .withColumns(listColumn);

        EntityInfo template4Double = new EntityInfo()
                .withClass(Double.class)
                .withTable(new EntityTable().withName("Float"))
                .withColumns(listColumn);

        List<EntityLink> linkList4Integer = new ArrayList<>();
        linkList4Integer.add(new EntityLink().withTemplate(template4Float));
        linkList4Integer.add(new EntityLink().withTemplate(template4Double));

        EntityInfo template4Integer = new EntityInfo()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withLinks(linkList4Integer)
                .withColumns(listColumn);

        List<EntityLink> linkList4Byte = new ArrayList<>();
        linkList4Byte.add(new EntityLink().withTemplate(template4Integer));

        entityInfo = new EntityInfo()
                .withClass(Byte.class)
                .withTable(new EntityTable().withName("Byte"))
                .withLinks(linkList4Byte)
                .withColumns(listColumn);
    }

    @Test
    public void testExpandAll(){
        List<EntityColumn> listAssert = new EntityColumnExpander()
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),8);
    }

    @Test
    public void testExpandDeep(){
        List<EntityColumn> listAssert = new EntityColumnExpander()
                .withDeepMax(0)
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),2);


        List<EntityColumn> listAssert2 = new EntityColumnExpander()
                .withDeepMax(1)
                .build(entityInfo);

        Assert.assertEquals(listAssert2.size(),4);
    }

}
