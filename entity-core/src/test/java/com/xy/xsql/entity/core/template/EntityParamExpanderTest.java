package com.xy.xsql.entity.core.template;

import com.xy.xsql.entity.model.template.*;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityParamExpanderTest {

    private EntityInfo entityInfo;
    private List<EntityParam> params;
    private EntityTemplateTreeArg treeArg;

    /**
     * EntityInfo Tree:
     * Byte
     *   Integer
     */
    @Before
    public void init(){
        EntityTable table = new EntityTable().withName("Integer&Byte");

        List<EntityColumn> columns = new ArrayList<>();
        columns.add(new EntityColumn()
                .withTable(table)
                .withName("1")
                .withAliasName("55"));
        columns.add(new EntityColumn()
                .withTable(table)
                .withName("2")
                .withAliasName("55"));
        columns.add(new EntityColumn()
                .withTable(table)
                .withName("3")
                .withAliasName("55"));
        columns.add(new EntityColumn()
                .withTable(table)
                .withName("4")
                .withAliasName("55"));
        columns.add(new EntityColumn()
                .withTable(table)
                .withName("5")
                .withAliasName("55"));

        params = new ArrayList<>();
        params.add(new EntityParam()
                .withColumn(columns.get(0)));
        params.add(new EntityParam()
                .withColumn(columns.get(1)));
        params.add(new EntityParam()
                .withColumn(columns.get(2)));
        params.add(new EntityParam()
                .withColumn(columns.get(3)));
        params.add(new EntityParam()
                .withColumn(columns.get(4)));


        EntityInfo template4Integer = new EntityInfo()
                .withClass(Integer.class)
                .withTable(table)
                .withColumns(columns)
                .withParams(params)
                .withKey(columns.get(0));

        List<EntityLink> linkList4Byte = new ArrayList<>();
        linkList4Byte.add(new EntityLink().withColumn(columns.get(2)).withTemplate(template4Integer));

        entityInfo = new EntityInfo()
                .withClass(Byte.class)
                .withTable(table)
                .withColumns(columns)
                .withParams(params)
                .withKey(columns.get(0))
                .withLinks(linkList4Byte);

        treeArg = new EntityTemplateTreeArg()
                .withClass(Short.class)
                .withArgs(1,2,3,4,5)
                .withSub(new EntityTemplateTreeArg()
                        .withClass(Integer.class)
                        .withArgs(11,12,13,14,15));
    }

    @Test
    public void testExpandAll(){
        List<EntityParam> listAssert = new EntityParamExpander()
                .withTreeArg(treeArg)
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),10);
        Assert.assertEquals(listAssert.get(0).getArg(),1);
        Assert.assertEquals(
                listAssert.get(0).getColumn().getName(),
                params.get(0).getColumn().getName());
        Assert.assertEquals(listAssert.get(1).getArg(),2);
        Assert.assertEquals(
                listAssert.get(1).getColumn().getName(),
                params.get(1).getColumn().getName());
        Assert.assertEquals(listAssert.get(2).getArg(),3);
        Assert.assertEquals(
                listAssert.get(2).getColumn().getName(),
                params.get(2).getColumn().getName());
        Assert.assertEquals(listAssert.get(3).getArg(),4);
        Assert.assertEquals(
                listAssert.get(3).getColumn().getName(),
                params.get(3).getColumn().getName());
        Assert.assertEquals(listAssert.get(4).getArg(),5);
        Assert.assertEquals(
                listAssert.get(4).getColumn().getName(),
                params.get(4).getColumn().getName());


        Assert.assertEquals(listAssert.get(5).getArg(),11);
        Assert.assertEquals(
                listAssert.get(5).getColumn().getName(),
                params.get(0).getColumn().getName());
        Assert.assertEquals(listAssert.get(6).getArg(),12);
        Assert.assertEquals(
                listAssert.get(6).getColumn().getName(),
                params.get(1).getColumn().getName());
        Assert.assertEquals(listAssert.get(7).getArg(),13);
        Assert.assertEquals(
                listAssert.get(7).getColumn().getName(),
                params.get(2).getColumn().getName());
        Assert.assertEquals(listAssert.get(8).getArg(),14);
        Assert.assertEquals(
                listAssert.get(8).getColumn().getName(),
                params.get(3).getColumn().getName());
        Assert.assertEquals(listAssert.get(9).getArg(),15);
        Assert.assertEquals(
                listAssert.get(9).getColumn().getName(),
                params.get(4).getColumn().getName());
    }

    @Test
    public void testExpandDeep0(){
        List<EntityParam> listAssert = new EntityParamExpander()
                .withDeepMax(0)
                .withTreeArg(treeArg)
                .build(entityInfo);

        Assert.assertEquals(listAssert.size(),5);
        Assert.assertEquals(listAssert.get(0).getArg(),1);
        Assert.assertEquals(
                listAssert.get(0).getColumn().getName(),
                params.get(0).getColumn().getName());
        Assert.assertEquals(listAssert.get(1).getArg(),2);
        Assert.assertEquals(
                listAssert.get(1).getColumn().getName(),
                params.get(1).getColumn().getName());
        Assert.assertEquals(listAssert.get(2).getArg(),3);
        Assert.assertEquals(
                listAssert.get(2).getColumn().getName(),
                params.get(2).getColumn().getName());
        Assert.assertEquals(listAssert.get(3).getArg(),4);
        Assert.assertEquals(
                listAssert.get(3).getColumn().getName(),
                params.get(3).getColumn().getName());
        Assert.assertEquals(listAssert.get(4).getArg(),5);
        Assert.assertEquals(
                listAssert.get(4).getColumn().getName(),
                params.get(4).getColumn().getName());
    }
}
