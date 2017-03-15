package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.statement.SelectBuilder;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.rve;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.rve_default;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.rve_null;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class TableValueConstructorBuilderTest {

    /**
     * VALUES ('Helmet', 25.50),
     ('Wheel', 30.00)
     */
    @Test
    public void testTableValueConstructor(){
        // @formatter:off
        TableValueConstructor TableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withRowValues()
                    .withRowValueExpression(rve("Helmet"))
                    .withRowValueExpression(rve(25.50))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Wheel"))
                    .withRowValueExpression(rve(30.00))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(TableValueConstructor.getRowValueExpressionListGroup().size(),2);
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
                    .withSelectList()
                        .withSelectItem().withColumnName("Name").out()
                        .out()
                    .withFrom()
                        .withTableSource().withTableName("Production.Product")
                        .out()
                        .out()
                    .withWhere().withSearchCondition().withPredicate().Operator()
                        .withExpression(e("ProductID"))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e(720))
                        .out().out().out()
                .out()
                .build(null);


        Select select2 = new SelectBuilder()
                .withQuery()
                    .withSelectList()
                        .withSelectItem().withColumnName("ListPrice").out()
                        .out()
                    .withFrom()
                        .withTableSource().withTableName("Production.Product")
                        .out()
                        .out()
                    .withWhere().withSearchCondition().withPredicate().Operator()
                        .withExpression(e("ProductID"))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e(720))
                        .out().out().out()
                .out()
                .build(null);



        TableValueConstructor TableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withRowValues()
                    .withRowValueExpression(rve("Helmet"))
                    .withRowValueExpression(rve(25.50))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Wheel"))
                    .withRowValueExpression(rve(30.00))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve(select1))
                    .withRowValueExpression(rve(select2))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(TableValueConstructor.getRowValueExpressionListGroup().size(),3);


    }


    /**
     * VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923'), (N'Y3', N'Cubic Yards', '20080923');
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withRowValues()
                    .withRowValueExpression(rve("FT2",true))
                    .withRowValueExpression(rve("Square Feet ",true))
                    .withRowValueExpression(rve("20080923"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Y",true))
                    .withRowValueExpression(rve("Yards",true))
                    .withRowValueExpression(rve("20080923"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Y3",true))
                    .withRowValueExpression(rve("Cubic Yards",true))
                    .withRowValueExpression(rve("20080923"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).getExpression().toString(),"N'FT2'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(1).getExpression().toString(),"N'Square Feet '");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(2).getExpression().toString(),"'20080923'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).getExpression().toString(),"N'Y'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(1).getExpression().toString(),"N'Yards'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(2).getExpression().toString(),"'20080923'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).getExpression().toString(),"N'Y3'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(1).getExpression().toString(),"N'Cubic Yards'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(2).getExpression().toString(),"'20080923'");

    }

    /**
     * VALUES ('Recommendation','Other'), ('Advertisement', DEFAULT), (NULL, 'Promotion');
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withRowValues()
                    .withRowValueExpression(rve("Recommendation"))
                    .withRowValueExpression(rve("Other"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Advertisement"))
                    .withRowValueExpression(rve_default())
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve_null())
                    .withRowValueExpression(rve("Promotion"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).getExpression().toString(),"'Recommendation'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(1).getExpression().toString(),"'Other'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).getExpression().toString(),"'Advertisement'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(1).isUseDefault(),true);

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).isUseNull(),true);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(1).getExpression().toString(),"'Promotion'");
    }

    /**
     * VALUES ('Blade'), ('Crown Race'), ('AWC Logo Cap')
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withRowValues()
                    .withRowValueExpression(rve("Blade"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Crown Race"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("AWC Logo Cap"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).getExpression().toString(),"'Blade'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).getExpression().toString(),"'Crown Race'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).getExpression().toString(),"'AWC Logo Cap'");
    }

    /**
     * VALUES ('Recommendation','Other'), ('Review', 'Marketing'), ('Internet', 'Promotion'))
     * VALUES (NewName, NewReasonType)
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withRowValues()
                    .withRowValueExpression(rve("Recommendation"))
                    .withRowValueExpression(rve("Other"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Review"))
                    .withRowValueExpression(rve("Marketing"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(rve("Internet"))
                    .withRowValueExpression(rve("Promotion"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).getExpression().toString(),"'Recommendation'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(1).getExpression().toString(),"'Other'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).getExpression().toString(),"'Review'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(1).getExpression().toString(),"'Marketing'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).getExpression().toString(),"'Internet'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(1).getExpression().toString(),"'Promotion'");
    }
}
