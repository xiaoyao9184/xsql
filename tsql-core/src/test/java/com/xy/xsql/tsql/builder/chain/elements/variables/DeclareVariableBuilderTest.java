package com.xy.xsql.tsql.builder.chain.elements.variables;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.Money;
import com.xy.xsql.tsql.model.datatypes.string.VarChar;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.elements.variables.DeclareVariable;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes.*;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitions.*;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.elements.variables.DeclareVariableBuilder.$Declare;
import static org.junit.Assert.*;

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
    public DeclareVariable exampleA1 = $Declare()
            .$("find")
                .$(_varchar(30))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @find varchar(30) = 'Man%'
     */
    public DeclareVariable exampleA2 = $Declare()
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

        assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@find");
        assertTrue(declareVariable.getItems().get(0).getDataType() instanceof VarChar);
        assertEquals(((VarChar)declareVariable.getItems().get(0).getDataType()).length(),30);

        assertEquals(declareVariable2.getItems().get(0).getLocalVariable().toString(),"@find");
        assertTrue(declareVariable2.getItems().get(0).getValue() instanceof StringConstant);
        assertEquals(((StringConstant)declareVariable2.getItems().get(0).getValue()).getString(),"Man%");
    }


    // @formatter:off
    /**
     * DECLARE @Group nvarchar(50), @Sales money
     */
    public DeclareVariable exampleB = $Declare()
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

        assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@Group");
        assertTrue(declareVariable.getItems().get(0).getDataType() instanceof VarChar);
        assertEquals(((VarChar)declareVariable.getItems().get(0).getDataType()).length(),50);
        assertEquals(declareVariable.getItems().get(1).getLocalVariable().toString(),"@Sales");
        assertTrue(declareVariable.getItems().get(1).getDataType() instanceof Money);
    }


    // @formatter:off
    /**
     * DECLARE @MyTableVar table(
     EmpID int NOT NULL,
     OldVacationHours int,
     NewVacationHours int,
     ModifiedDate datetime)
     */
    public DeclareVariable exampleC = $Declare()
            .$("MyTableVar")
            .$Table()
                .$_(
                        c_not_null(c_int("EmpID")),
                        c_int("OldVacationHours"),
                        c_int("NewVacationHours"),
                        c_datetime("ModifiedDate")
                )
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        DeclareVariable declareVariable = new DeclareVariableBuilder<Void>()
                .withTableVariableName(e_variable("MyTableVar"))
                .withTable()
                    .withColumnDefinition()
                        .withColumnName(c("EmpID"))
                        .withDataType(_int())
                        .withUseNotNull(true)
                        .and()
                    .withColumnDefinition()
                        .withColumnName(c("OldVacationHours"))
                        .withDataType(_int())
                        .and()
                    .withColumnDefinition()
                        .withColumnName(c("NewVacationHours"))
                        .withDataType(_int())
                        .and()
                    .withColumnDefinition()
                        .withColumnName(c("ModifiedDate"))
                        .withDataType(_datetime())
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertNull(declareVariable.getItems());
        assertEquals(declareVariable.getTableTypeDefinition().getList().size(),4);
        assertTrue(declareVariable.getTableTypeDefinition().getList().get(0) instanceof ColumnDefinition);
        ColumnDefinition cd = (ColumnDefinition) declareVariable.getTableTypeDefinition().getList().get(0);
        assertEquals(cd.getFullName(),"EmpID");
        assertEquals(cd.getDataType().name(),_int().name());
        assertTrue(declareVariable.getTableTypeDefinition().getList().get(1) instanceof ColumnDefinition);
        ColumnDefinition cd1 = (ColumnDefinition) declareVariable.getTableTypeDefinition().getList().get(1);
        assertEquals(cd1.getFullName(),"OldVacationHours");
        assertEquals(cd1.getDataType().name(),_int().name());
        assertTrue(declareVariable.getTableTypeDefinition().getList().get(2) instanceof ColumnDefinition);
        ColumnDefinition cd2 = (ColumnDefinition) declareVariable.getTableTypeDefinition().getList().get(2);
        assertEquals(cd2.getFullName(),"NewVacationHours");
        assertEquals(cd2.getDataType().name(),_int().name());
        assertTrue(declareVariable.getTableTypeDefinition().getList().get(3) instanceof ColumnDefinition);
        ColumnDefinition cd3 = (ColumnDefinition) declareVariable.getTableTypeDefinition().getList().get(3);
        assertEquals(cd3.getFullName(),"ModifiedDate");
        assertEquals(cd3.getDataType().name(),_datetime().name());
    }


    // @formatter:off
    /**
     * DECLARE @LocationTVP
     AS LocationTableType
     */
    public DeclareVariable exampleD = $Declare()
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

        assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@LocationTVP");
        assertTrue(declareVariable.getItems().get(0).getDataType() instanceof DataType.SimpleDataType);
        assertEquals(declareVariable.getItems().get(0).getDataType().name(),"LocationTableType");
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/declare-local-variable-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */


    // @formatter:off
    /**
     * DECLARE @find varchar(30)
     */
    public DeclareVariable exampleE1 = $Declare()
            .$("find")
                .$(_varchar(30))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @find varchar(30) = 'Man%'
     */
    public DeclareVariable exampleE2 = $Declare()
            .$("find")
                .$(_varchar(30))
                .$(e_string("Man%"))
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @lastName varchar(30), @firstName varchar(30)
     */
    public DeclareVariable exampleF = $Declare()
            .$("lastName")
                .$(_varchar(30))
                .and()
            .$("firstName")
                .$(_varchar(30))
                .and()
            .build();
    // @formatter:on
}
