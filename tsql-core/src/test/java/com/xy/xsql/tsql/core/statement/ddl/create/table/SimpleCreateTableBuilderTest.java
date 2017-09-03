package com.xy.xsql.tsql.core.statement.ddl.create.table;

import com.xy.xsql.tsql.model.statement.ddl.create.table.SimpleCreateTable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.datatype.ColumnDefinitionFactory.c_int;
import static com.xy.xsql.tsql.core.datatype.DataTypes._int;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.statement.ddl.create.table.SimpleCreateTableBuilder.CREATE_TABLE;

/**
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class SimpleCreateTableBuilderTest {

    // @formatter:off
    /**
     * CREATE TABLE ##test ( a int, b int)
     */
    public SimpleCreateTable example = CREATE_TABLE(t("##test"),c_int("a"),c_int("b"));
    // @formatter:on

    @Test
    public void testExample(){
        Assert.assertEquals(example.getTableName().toString(),"##test");
        Assert.assertEquals(example.getColumnDefinitionList().size(),2);
    }

    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql#examples
     */

    // @formatter:off
    /**
     * CREATE TABLE dbo.Employee (EmployeeID int
    PRIMARY KEY CLUSTERED)
     */
    public SimpleCreateTable exampleA = CREATE_TABLE()
            .$(t("dbo","Employee"))
            .$("EmployeeID")
                .$(_int())
                .$PRIMARY_KEY()
                    .$CLUSTERED()
                    .and()
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        Assert.assertEquals(exampleA.getTableName().toString(),"dbo.Employee");
        Assert.assertEquals(exampleA.getColumnDefinitionList().size(),1);
    }

    // @formatter:off
    /**
     * SalesPersonID int NULL
    REFERENCES SalesPerson(SalesPersonID)
     */
    public SimpleCreateTable exampleB = CREATE_TABLE()
            .$("SalesPersonID")
                .$(_int())
                .$NULL()
                .$REFERENCES("SalesPerson","SalesPersonID")
                    .and()
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
//        Assert.assertEquals(exampleB.getTableName().toString(),"SalesPersonID");
        Assert.assertEquals(exampleB.getColumnDefinitionList().size(),1);
    }

}