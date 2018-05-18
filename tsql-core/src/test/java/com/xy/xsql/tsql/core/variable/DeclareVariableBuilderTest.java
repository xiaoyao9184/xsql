package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.tsql.model.elements.variables.DeclareVariable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.datatype.DataTypes.*;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;
import static com.xy.xsql.tsql.core.expression.Expressions.e_variable;
import static com.xy.xsql.tsql.core.variable.DeclareVariableBuilder.DECLARE;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class DeclareVariableBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/declare-local-variable-transact-sql#examples
     */

    // @formatter:off
    /**
     * DECLARE @find varchar(30)
     */
    public DeclareVariable exampleA1 = DECLARE()
            .$("find")
                .$(_varchar(30))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @find varchar(30) = 'Man%'
     */
    public DeclareVariable exampleA2 = DECLARE()
            .$("find")
                .$(_varchar(30))
                .$(e_string("Man%"))
            .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        DeclareVariable declareVariable = new DeclareVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable(e_variable("find"))
                    .withDateType(_varchar(30))
                    .and()
                .build();

        DeclareVariable declareVariable2 = new DeclareVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable(e_variable("find"))
                    .withDateType(_varchar(30))
                    .withValue(e_string("Man%"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@find");
        Assert.assertEquals(declareVariable.getItems().get(0).getDataType().toString(),"varchar(30)");

        Assert.assertEquals(declareVariable2.getItems().get(0).getLocalVariable().toString(),"@find");
        Assert.assertEquals(declareVariable2.getItems().get(0).getValue().toString(),"'Man%'");
    }


    // @formatter:off
    /**
     * DECLARE @Group nvarchar(50), @Sales money
     */
    public DeclareVariable exampleB = DECLARE()
            .$("Group")
                .$(_nvarchar(50))
                .and()
            .$("Sales")
                .$(_money())
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        DeclareVariable declareVariable = new DeclareVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable(e_variable("Group"))
                    .withDateType(_varchar(50))
                    .and()
                .withItem()
                    .withLocalVariable(e_variable("Sales"))
                    .withDateType(_money())
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@Group");
        Assert.assertEquals(declareVariable.getItems().get(0).getDataType().toString(),"varchar(50)");
        Assert.assertEquals(declareVariable.getItems().get(1).getLocalVariable().toString(),"@Sales");
        Assert.assertEquals(declareVariable.getItems().get(1).getDataType().toString(),"money");
    }


    // @formatter:off
    /**
     * DECLARE @MyTableVar table(
     EmpID int NOT NULL,
     OldVacationHours int,
     NewVacationHours int,
     ModifiedDate datetime)
     */
    public DeclareVariable exampleC = DECLARE()
            .$("MyTableVar")
            //TODO table_type_definition
            .$Table()
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleC(){
//        // @formatter:off
//        DeclareVariable declareVariable = new DeclareVariableBuilder<Void>()
//                .withTableVariable("MyTableVar")
//                .withDateType(_varchar(50))
//                .build();
//        // @formatter:on
//
//        Assert.assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@Group");
//        Assert.assertEquals(declareVariable.getItems().get(0).getDataType().toString(),"varchar(50)");
//        Assert.assertEquals(declareVariable.getItems().get(1).getLocalVariable().toString(),"@Sales");
//        Assert.assertEquals(declareVariable.getItems().get(1).getDataType().toString(),"money");
    }


    // @formatter:off
    /**
     * DECLARE @LocationTVP
     AS LocationTableType
     */
    public DeclareVariable exampleD = DECLARE()
            .$("LocationTVP")
                .$As()
                .$(_user_defined("LocationTableType"))
                .and()
            .build();
    // @formatter:on


    @Test
    public void testExampleD(){
        // @formatter:off
        DeclareVariable declareVariable = new DeclareVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable(e_variable("LocationTVP"))
                    .withAs()
                    .withDateType(_user_defined("LocationTableType"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@LocationTVP");
        Assert.assertEquals(declareVariable.getItems().get(0).getDataType().toString(),"LocationTableType");
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/declare-local-variable-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */


    // @formatter:off
    /**
     * DECLARE @find varchar(30)
     */
    public DeclareVariable exampleE1 = DECLARE()
            .$("find")
                .$(_varchar(30))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @find varchar(30) = 'Man%'
     */
    public DeclareVariable exampleE2 = DECLARE()
            .$("find")
                .$(_varchar(30))
                .$(e_string("Man%"))
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @lastName varchar(30), @firstName varchar(30)
     */
    public DeclareVariable exampleF = DECLARE()
            .$("lastName")
                .$(_varchar(30))
                .and()
            .$("firstName")
                .$(_varchar(30))
                .and()
            .build();
    // @formatter:on
}
