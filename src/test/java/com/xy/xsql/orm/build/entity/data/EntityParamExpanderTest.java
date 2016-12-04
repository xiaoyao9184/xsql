package com.xy.xsql.orm.build.entity.data;

import com.xy.xsql.orm.data.entity.*;
import com.xy.xsql.orm.data.param.EntityTemplateDataArgTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityParamExpanderTest {

    @Test
    public void TestExpanderAll(){

        EntityTable table = new EntityTable().withName("Short");

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

        List<EntityParam> params = new ArrayList<>();
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




        EntityTemplate entityTemplate2 = new EntityTemplate()
                .withClass(Integer.class)
                .withTable(new EntityTable().withName("Integer"))
                .withColumns(columns)
                .withParams(params)
                .withKey(columns.get(0));

        List<EntityLink> links = new ArrayList<>();
        links.add(new EntityLink().withColumn(columns.get(2)).withTemplate(entityTemplate2));

        EntityTemplate entityTemplate = new EntityTemplate()
                .withClass(Short.class)
                .withTable(table)
                .withColumns(columns)
                .withParams(params)
                .withKey(columns.get(0))
                .withLinks(links);





        EntityTemplateDataArgTree treeArg = new EntityTemplateDataArgTree()
                .withClass(Short.class)
                .withArgs(1,2,3,4,5)
                .withSub(new EntityTemplateDataArgTree()
                        .withClass(Integer.class)
                        .withArgs(11,12,13,14,15));

        List<EntityParam> listAssert = new EntityParamExpander()
                .withTreeArg(treeArg)
                .build(entityTemplate);

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

}
