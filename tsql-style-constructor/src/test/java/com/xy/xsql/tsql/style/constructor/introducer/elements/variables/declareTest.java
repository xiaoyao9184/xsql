package com.xy.xsql.tsql.style.constructor.introducer.elements.variables;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.Money;
import com.xy.xsql.tsql.model.datatypes.string.NVarChar;
import com.xy.xsql.tsql.model.datatypes.string.VarChar;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.elements.variables.DeclareVariable;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes.*;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.variables.declare_$local_variable.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.variables.declare_$local_variable.DECLARE;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class declareTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/declare-local-variable-transact-sql#examples
     */

    // @formatter:off
    /**
     * DECLARE @find varchar(30)
     */
    public DeclareVariable exampleA1 = DECLARE(
            "find",
            _varchar(30)
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @find varchar(30) = 'Man%'
     */
    public DeclareVariable exampleA2 = DECLARE(
            "find",
            _varchar(30),
            e_string("Man%")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA1.getItems().get(0).getLocalVariable().toString(),"@find");
        assertTrue(exampleA1.getItems().get(0).getDataType() instanceof VarChar);
        assertEquals(((VarChar)exampleA1.getItems().get(0).getDataType()).length(),30);

        assertEquals(exampleA2.getItems().get(0).getLocalVariable().toString(),"@find");
        assertTrue(exampleA2.getItems().get(0).getValue() instanceof StringConstant);
        assertEquals(((StringConstant)exampleA2.getItems().get(0).getValue()).getString(),"Man%");
    }


    // @formatter:off
    /**
     * DECLARE @Group nvarchar(50), @Sales money
     */
    public DeclareVariable exampleB = DECLARE(
            "Group",
            _nvarchar(50)
    ).$$(
            "Sales",
            _money()
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getItems().get(0).getLocalVariable().toString(),"@Group");
        assertTrue(exampleB.getItems().get(0).getDataType() instanceof NVarChar);
        assertEquals(((NVarChar)exampleB.getItems().get(0).getDataType()).length(),50);
        assertEquals(exampleB.getItems().get(1).getLocalVariable().toString(),"@Sales");
        assertTrue(exampleB.getItems().get(1).getDataType() instanceof Money);
    }


    // @formatter:off
    /**
     * DECLARE @MyTableVar table(
     EmpID int NOT NULL,
     OldVacationHours int,
     NewVacationHours int,
     ModifiedDate datetime)
     */
    public DeclareVariable exampleC = DECLARE(
            "MyTableVar",
            //TODO table_type_definition
            _varchar()
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
//        assertNull(exampleC.getItems());
//        assertEquals(exampleC.getTableTypeDefinition().getList().size(),4);
//        assertTrue(exampleC.getTableTypeDefinition().getList().get(0) instanceof ColumnDefinition);
//        ColumnDefinition cd = (ColumnDefinition) exampleC.getTableTypeDefinition().getList().get(0);
//        assertEquals(cd.getFullName(),"EmpID");
//        assertEquals(cd.getDataType().name(),_int().name());
//        assertTrue(exampleC.getTableTypeDefinition().getList().get(1) instanceof ColumnDefinition);
//        ColumnDefinition cd1 = (ColumnDefinition) exampleC.getTableTypeDefinition().getList().get(1);
//        assertEquals(cd1.getFullName(),"OldVacationHours");
//        assertEquals(cd1.getDataType().name(),_int().name());
//        assertTrue(exampleC.getTableTypeDefinition().getList().get(2) instanceof ColumnDefinition);
//        ColumnDefinition cd2 = (ColumnDefinition) exampleC.getTableTypeDefinition().getList().get(2);
//        assertEquals(cd2.getFullName(),"NewVacationHours");
//        assertEquals(cd2.getDataType().name(),_int().name());
//        assertTrue(exampleC.getTableTypeDefinition().getList().get(3) instanceof ColumnDefinition);
//        ColumnDefinition cd3 = (ColumnDefinition) exampleC.getTableTypeDefinition().getList().get(3);
//        assertEquals(cd3.getFullName(),"ModifiedDate");
//        assertEquals(cd3.getDataType().name(),_datetime().name());
    }


    // @formatter:off
    /**
     * DECLARE @LocationTVP
     AS LocationTableType
     */
    public DeclareVariable exampleD = DECLARE(
            "LocationTVP",
            AS(),
            _user_defined("LocationTableType")
    ).build();
    // @formatter:on


    @Test
    public void testExampleD(){
        assertEquals(exampleD.getItems().get(0).getLocalVariable().toString(),"@LocationTVP");
        assertTrue(exampleD.getItems().get(0).getDataType() instanceof DataType.SimpleDataType);
        assertEquals(exampleD.getItems().get(0).getDataType().name(),"LocationTableType");
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/declare-local-variable-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */


    // @formatter:off
    /**
     * DECLARE @find varchar(30)
     */
    public DeclareVariable exampleE1 = DECLARE(
            "find",
            _varchar(30)
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @find varchar(30) = 'Man%'
     */
    public DeclareVariable exampleE2 = DECLARE(
            "find",
            _varchar(30),
            e_string("Man%")
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * DECLARE @lastName varchar(30), @firstName varchar(30)
     */
    public DeclareVariable exampleF = DECLARE(
            "lastName",
            _varchar(30)
    ).$$("firstName",
            _varchar(30)
    ).build();
    // @formatter:on
}
