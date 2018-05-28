package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.builder.chain.elements.operators.Operators;
import com.xy.xsql.tsql.builder.chain.queries.SelectBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.values.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
public class valuesTest {


    /**
     * VALUES ('Helmet', 25.50),
     ('Wheel', 30.00)
     */
    @Test
    public void testTableValueConstructor(){
        // @formatter:off
        TableValueConstructor TableValueConstructor = VALUES(
                e_string("Helmet"),
                e_number(25.50)
        ).$$(
                e_string("Wheel"),
                e_number(30.00)
        ).build();
        // @formatter:on

        assertEquals(TableValueConstructor.getRowValueExpressionListGroup().size(),2);
    }

    /**
     *
     *
     *
     *
     * ERROR
     *
     *
     *
     *
     VALUES ('Helmet', 25.50),
     ('Wheel', 30.00),
     (SELECT Name, ListPrice FROM Production.Product WHERE ProductID = 720);

     * INSERT INTO dbo.MyProducts (Name, ListPrice)
     VALUES ('Helmet', 25.50),
     ('Wheel', 30.00),
     ((SELECT Name FROM Production.Product WHERE ProductID = 720),
     (SELECT ListPrice FROM Production.Product WHERE ProductID = 720));
     */
    @Test
    public void testUseSelect(){
        // @formatter:off

        Select select1 = new SelectBuilder()
                .withQuery()
                .withQuerySpecification()
                .withSelectItem().withColumnName(c("Name")).and()
                .withFrom()
                .withItem()._Base()
                .withTableName(t("Production.Product"))
                .and()
                .and()
                .withWhere()
                .withSearchCondition()
                .withPredicate()._Comparison()
                .withExpression(e("ProductID"))
                .withOperator(Operators.$Equal)
                .withExpression(e_number(720))
                .and()
                .and()
                .and()
                .and()
                .and()
                .build();


        Select select2 = new SelectBuilder()
                .withQuery()
                .withQuerySpecification()
                .withSelectItem().withColumnName(c("ListPrice")).and()
                .withFrom()
                .withItem()._Base()
                .withTableName(t("Production.Product"))
                .and()
                .and()
                .withWhere()
                .withSearchCondition()
                .withPredicate()._Comparison()
                .withExpression(e("ProductID"))
                .withOperator(Operators.$Equal)
                .withExpression(e_number(720))
                .and()
                .and()
                .and()
                .and()
                .and()
                .build();



        TableValueConstructor TableValueConstructor = VALUES(
                e_string("Helmet"),
                e_number(25.50)
        ).$$(
                e_string("Wheel"),
                e_number(30.00)
        ).$$(
                e_subquery(select1),
                e_subquery(select2)
        ).build();
        // @formatter:on

        assertEquals(TableValueConstructor.getRowValueExpressionListGroup().size(),3);


    }

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/table-value-constructor-transact-sql#examples
     */


    // @formatter:off
    //parent+quick
    /**
     * VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923'), (N'Y3', N'Cubic Yards', '20080923');
     */
    public TableValueConstructor exampleA = VALUES(
            e_n_string("FT2"),
            e_n_string("Square Feet "),
            e_string("20080923")
    ).$$(
            e_n_string("Y"),
            e_n_string("Yards"),
            e_string("20080923")
    ).$$(
            e_n_string("Y3"),
            e_n_string("Cubic Yards"),
            e_string("20080923")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getRowValueExpressionListGroup().size(),3);
        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(0).get(0)).getString(),"FT2");
        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(0).get(1)).getString(),"Square Feet ");
        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(0).get(2)).getString(),"20080923");

        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(1).get(0)).getString(),"Y");
        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(1).get(1)).getString(),"Yards");
        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(1).get(2)).getString(),"20080923");

        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(2).get(0)).getString(),"Y3");
        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(2).get(1)).getString(),"Cubic Yards");
        assertEquals(((StringConstant)exampleA.getRowValueExpressionListGroup().get(2).get(2)).getString(),"20080923");

    }


    // @formatter:off
    //parent+quick
    /**
     * VALUES ('Recommendation','Other'), ('Advertisement', DEFAULT), (NULL, 'Promotion');
     */
    public TableValueConstructor exampleB = VALUES(
            e_string("Recommendation"),
            e_string("Other")
    ).$$(
            e_string("Advertisement"),
            e_default()
    ).$$(
            e_null(),
            e_string("Promotion")
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getRowValueExpressionListGroup().size(),3);
        assertEquals(((StringConstant)exampleB.getRowValueExpressionListGroup().get(0).get(0)).getString(),"Recommendation");
        assertEquals(((StringConstant)exampleB.getRowValueExpressionListGroup().get(0).get(1)).getString(),"Other");

        assertEquals(((StringConstant)exampleB.getRowValueExpressionListGroup().get(1).get(0)).getString(),"Advertisement");
        assertEquals(exampleB.getRowValueExpressionListGroup().get(1).get(1).toString(),"DEFAULT");

        assertEquals(exampleB.getRowValueExpressionListGroup().get(2).get(0).toString(),"NULL");
        assertEquals(((StringConstant)exampleB.getRowValueExpressionListGroup().get(2).get(1)).getString(),"Promotion");
    }


    // @formatter:off
    //parent+quick
    /**
     * VALUES ('Blade'), ('Crown Race'), ('AWC Logo Cap')
     */
    public TableValueConstructor exampleC = VALUES(
            e_string("Blade")
    ).$$(
            e_string("Crown Race")
    ).$$(
            e_string("AWC Logo Cap")
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getRowValueExpressionListGroup().size(),3);
        assertEquals(((StringConstant)exampleC.getRowValueExpressionListGroup().get(0).get(0)).getString(),"Blade");

        assertEquals(((StringConstant)exampleC.getRowValueExpressionListGroup().get(1).get(0)).getString(),"Crown Race");

        assertEquals(((StringConstant)exampleC.getRowValueExpressionListGroup().get(2).get(0)).getString(),"AWC Logo Cap");
    }


    // @formatter:off
    //parent+quick
    /**
     * VALUES ('Recommendation','Other'), ('Review', 'Marketing'), ('Internet', 'Promotion')
     */
    public TableValueConstructor exampleD = VALUES(
            e_string("Recommendation"),
            e_string("Other")
    ).$$(
            e_string("Review"),
            e_string("Marketing")
    ).$$(
            e_string("Internet"),
            e_string("Promotion")
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getRowValueExpressionListGroup().size(),3);
        assertEquals(((StringConstant)exampleD.getRowValueExpressionListGroup().get(0).get(0)).getString(),"Recommendation");
        assertEquals(((StringConstant)exampleD.getRowValueExpressionListGroup().get(0).get(1)).getString(),"Other");

        assertEquals(((StringConstant)exampleD.getRowValueExpressionListGroup().get(1).get(0)).getString(),"Review");
        assertEquals(((StringConstant)exampleD.getRowValueExpressionListGroup().get(1).get(1)).getString(),"Marketing");

        assertEquals(((StringConstant)exampleD.getRowValueExpressionListGroup().get(2).get(0)).getString(),"Internet");
        assertEquals(((StringConstant)exampleD.getRowValueExpressionListGroup().get(2).get(1)).getString(),"Promotion");
    }




}