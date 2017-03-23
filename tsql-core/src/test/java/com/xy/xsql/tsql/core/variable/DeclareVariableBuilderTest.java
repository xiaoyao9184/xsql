package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.tsql.model.variable.DeclareVariable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.datatype.DataTypes._money;
import static com.xy.xsql.tsql.core.datatype.DataTypes._varchar;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class DeclareVariableBuilderTest {

    /**
     * DECLARE @find varchar(30);
     DECLARE @find varchar(30) = 'Man%';
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        DeclareVariable declareVariable = new DeclareVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable("find")
                    .withDateType(_varchar(30))
                    .and()
                .build();

        DeclareVariable declareVariable2 = new DeclareVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable("find")
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

    /**
     * DECLARE @Group nvarchar(50), @Sales money;
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        DeclareVariable declareVariable = new DeclareVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable("Group")
                    .withDateType(_varchar(50))
                    .and()
                .withItem()
                    .withLocalVariable("Sales")
                    .withDateType(_money())
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(declareVariable.getItems().get(0).getLocalVariable().toString(),"@Group");
        Assert.assertEquals(declareVariable.getItems().get(0).getDataType().toString(),"varchar(50)");
        Assert.assertEquals(declareVariable.getItems().get(1).getLocalVariable().toString(),"@Sales");
        Assert.assertEquals(declareVariable.getItems().get(1).getDataType().toString(),"money");
    }

    /**
     * DECLARE @MyTableVar table(
     EmpID int NOT NULL,
     OldVacationHours int,
     NewVacationHours int,
     ModifiedDate datetime);
     */
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

    /**
     * DECLARE @LocationTVP
     AS LocationTableType;
     */
    @Test
    public void testExampleD(){
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

}
