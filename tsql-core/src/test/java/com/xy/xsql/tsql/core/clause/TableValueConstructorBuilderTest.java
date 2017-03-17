package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder;
import com.xy.xsql.tsql.core.statement.SelectBuilder;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.e_rv;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.e_rv_default;
import static com.xy.xsql.tsql.core.expression.RowValueExpressionBuilder.e_rv_null;

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
                    .withRowValueExpression(e_rv("Helmet"))
                    .withRowValueExpression(e_rv(25.50))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Wheel"))
                    .withRowValueExpression(e_rv(30.00))
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
                        .withExpression(e_number(720))
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
                        .withExpression(e_number(720))
                        .out().out().out()
                .out()
                .build(null);



        TableValueConstructor TableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withRowValues()
                    .withRowValueExpression(e_rv("Helmet"))
                    .withRowValueExpression(e_rv(25.50))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Wheel"))
                    .withRowValueExpression(e_rv(30.00))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv(select1))
                    .withRowValueExpression(e_rv(select2))
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
                    .withRowValueExpression(e_rv("FT2",true))
                    .withRowValueExpression(e_rv("Square Feet ",true))
                    .withRowValueExpression(e_rv("20080923"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Y",true))
                    .withRowValueExpression(e_rv("Yards",true))
                    .withRowValueExpression(e_rv("20080923"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Y3",true))
                    .withRowValueExpression(e_rv("Cubic Yards",true))
                    .withRowValueExpression(e_rv("20080923"))
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
                    .withRowValueExpression(e_rv("Recommendation"))
                    .withRowValueExpression(e_rv("Other"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Advertisement"))
                    .withRowValueExpression(e_rv_default())
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv_null())
                    .withRowValueExpression(e_rv("Promotion"))
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
                    .withRowValueExpression(e_rv("Blade"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Crown Race"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("AWC Logo Cap"))
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
                    .withRowValueExpression(e_rv("Recommendation"))
                    .withRowValueExpression(e_rv("Other"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Review"))
                    .withRowValueExpression(e_rv("Marketing"))
                    .and()
                .withRowValues()
                    .withRowValueExpression(e_rv("Internet"))
                    .withRowValueExpression(e_rv("Promotion"))
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
