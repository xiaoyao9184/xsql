package com.xy.xsql.tsql.builder.chain.statements.create.table;

import com.xy.xsql.tsql.builder.chain.statements.create.Creates;
import com.xy.xsql.tsql.model.statements.create.table.SimpleCreateTable;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitions.c_int;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._int;
import static com.xy.xsql.tsql.builder.chain.statements.create.Creates.$CreateTable;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class SimpleCreateTableBuilderTest {

    // @formatter:off
    /**
     * CREATE TABLE ##test ( a int, b int)
     */
    public SimpleCreateTable example = $CreateTable(t("##test"),c_int("a"),c_int("b"));
    // @formatter:on

    @Test
    public void testExample(){
        assertEquals(example.getTableName().toString(),"##test");
        assertEquals(example.getColumnDefinitionList().size(),2);
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
    public SimpleCreateTable exampleA = Creates.$CreateTable()
            .$(t("dbo","Employee"))
            .$("EmployeeID")
                .$(_int())
                .$PrimaryKey()
                    .$Clustered()
                    .and()
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTableName().toString(),"dbo.Employee");
        assertEquals(exampleA.getColumnDefinitionList().size(),1);
    }

    // @formatter:off
    /**
     * SalesPersonID int NULL
    REFERENCES SalesPerson(SalesPersonID)
     */
    public SimpleCreateTable exampleB = Creates.$CreateTable()
            .$("SalesPersonID")
                .$(_int())
                .$Null()
                .$References("SalesPerson","SalesPersonID")
                    .and()
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
//        assertEquals(exampleB.getTableName().toString(),"SalesPersonID");
        assertEquals(exampleB.getColumnDefinitionList().size(),1);
    }

}