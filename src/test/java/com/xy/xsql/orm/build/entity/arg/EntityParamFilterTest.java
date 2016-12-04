package com.xy.xsql.orm.build.entity.arg;


import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.entity.EntityTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityParamFilterTest {

    private List<EntityParam> listAllNeedValue;
    private List<EntityParam> listHalfNeedValue;

    @Before
    public void init(){
        EntityTable table = new EntityTable()
                .withName("t");

        listAllNeedValue = new ArrayList<>();
        listAllNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("1")
                        .withAliasName("1")
                        .withTable(table)));
        listAllNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("2")
                        .withAliasName("2")
                        .withTable(table)));
        listAllNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("3")
                        .withAliasName("3")
                        .withTable(table)));
        listAllNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("4")
                        .withAliasName("4")
                        .withTable(table)));
        listAllNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("5")
                        .withAliasName("5")
                        .withTable(table)));

        listHalfNeedValue = new ArrayList<>();
        listHalfNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("1")
                        .withAliasName("1")
                        .withTable(table)));
        listHalfNeedValue.add(new EntityParam()
                .withArgs(2)
                .withColumn(new EntityColumn()
                        .withName("2")
                        .withAliasName("2")
                        .withTable(table)));
        listHalfNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("3")
                        .withAliasName("3")
                        .withTable(table)));
        listHalfNeedValue.add(new EntityParam()
                .withArgs(4)
                .withColumn(new EntityColumn()
                        .withName("4")
                        .withAliasName("4")
                        .withTable(table)));
        listHalfNeedValue.add(new EntityParam()
                .withColumn(new EntityColumn()
                        .withName("5")
                        .withAliasName("5")
                        .withTable(table)));
    }

    @Test
    public void TestFilterAll(){
        List<EntityParam> listAssert = new EntityParamFilter()
                .withArgs(1,2,3,4,5)
                .build(listAllNeedValue);
        Assert.assertEquals(listAssert.get(0).getArg(),1);
        Assert.assertEquals(
                listAssert.get(0).getColumn().getName(),
                listAllNeedValue.get(0).getColumn().getName());
        Assert.assertEquals(listAssert.get(1).getArg(),2);
        Assert.assertEquals(
                listAssert.get(1).getColumn().getName(),
                listAllNeedValue.get(1).getColumn().getName());
        Assert.assertEquals(listAssert.get(2).getArg(),3);
        Assert.assertEquals(
                listAssert.get(2).getColumn().getName(),
                listAllNeedValue.get(2).getColumn().getName());
        Assert.assertEquals(listAssert.get(3).getArg(),4);
        Assert.assertEquals(
                listAssert.get(3).getColumn().getName(),
                listAllNeedValue.get(3).getColumn().getName());
        Assert.assertEquals(listAssert.get(4).getArg(),5);
        Assert.assertEquals(
                listAssert.get(4).getColumn().getName(),
                listAllNeedValue.get(4).getColumn().getName());
    }

    @Test
    public void TestFilterNotNullArg(){
        List<EntityParam> listAssert = new EntityParamFilter()
                .withArgs(1,null,3,null,5)
                .build(listAllNeedValue);
        Assert.assertEquals(listAssert.get(0).getArg(),1);
        Assert.assertEquals(
                listAssert.get(0).getColumn().getName(),
                listAllNeedValue.get(0).getColumn().getName());
        Assert.assertEquals(listAssert.get(1).getArg(),3);
        Assert.assertEquals(
                listAssert.get(1).getColumn().getName(),
                listAllNeedValue.get(2).getColumn().getName());
        Assert.assertEquals(listAssert.get(2).getArg(),5);
        Assert.assertEquals(
                listAssert.get(2).getColumn().getName(),
                listAllNeedValue.get(4).getColumn().getName());
    }

    @Test
    public void TestFilterWithDefaultArg(){
        List<EntityParam> listAssert = new EntityParamFilter()
                .withArgs(1,3,5)
                .build(listHalfNeedValue);
        Assert.assertEquals(listAssert.get(0).getArg(),1);
        Assert.assertEquals(
                listAssert.get(0).getColumn().getName(),
                listHalfNeedValue.get(0).getColumn().getName());
        Assert.assertEquals(listAssert.get(1).getArg(),2);
        Assert.assertEquals(
                listAssert.get(1).getColumn().getName(),
                listHalfNeedValue.get(1).getColumn().getName());
        Assert.assertEquals(listAssert.get(2).getArg(),3);
        Assert.assertEquals(
                listAssert.get(2).getColumn().getName(),
                listHalfNeedValue.get(2).getColumn().getName());
        Assert.assertEquals(listAssert.get(3).getArg(),4);
        Assert.assertEquals(
                listAssert.get(3).getColumn().getName(),
                listHalfNeedValue.get(3).getColumn().getName());
        Assert.assertEquals(listAssert.get(4).getArg(),5);
        Assert.assertEquals(
                listAssert.get(4).getColumn().getName(),
                listHalfNeedValue.get(4).getColumn().getName());
    }

}
