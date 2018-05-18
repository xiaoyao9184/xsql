package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.core.statement.dml.SelectBuilder;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.elements.operators.Operators;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.*;

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
                .withItem()
                    .withRowValueExpression(e_string("Helmet"))
                    .withRowValueExpression(e_number(25.50))
                    .and()
                .withItem()
                    .withRowValueExpression(e_string("Wheel"))
                    .withRowValueExpression(e_number(30.00))
                    .and()
                .build();

        //parent+quick
        MockParent<TableValueConstructor> parent = new MockParentBuilder<TableValueConstructorBuilder<MockParent<TableValueConstructor>>,TableValueConstructor>
                (TableValueConstructorBuilder.class,TableValueConstructor.class)
                .$child()
                    .$(e_string("Helmet"),
                            e_number(25.50))
                    .$(e_string("Wheel"),
                            e_number(30.00))
                    .and();
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
                                    .withOperator(Operators.EQUAL)
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
                                    .withOperator(Operators.EQUAL)
                                    .withExpression(e_number(720))
                                    .and()
                                .and()
                            .and()
                        .and()
                    .and()
                .build();



        TableValueConstructor TableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withItem()
                    .withRowValueExpression(e_string("Helmet"))
                    .withRowValueExpression(e_number(25.50))
                    .and()
                .withItem()
                    .withRowValueExpression(e_string("Wheel"))
                    .withRowValueExpression(e_number(30.00))
                    .and()
                .withItem()
                    .withRowValueExpression(e_subquery(select1))
                    .withRowValueExpression(e_subquery(select2))
                    .and()
                .build();

        //parent+quick
        MockParent<TableValueConstructor> parent = new MockParentBuilder<TableValueConstructorBuilder<MockParent<TableValueConstructor>>,TableValueConstructor>
                (TableValueConstructorBuilder.class,TableValueConstructor.class)
                .$child()
                    .$(e_string("Helmet"),
                            e_number(25.50))
                    .$(e_string("Wheel"),
                            e_number(30.00))
                    .$(e_subquery(select1),
                            e_subquery(select2))
                    .and();
        // @formatter:on

        Assert.assertEquals(TableValueConstructor.getRowValueExpressionListGroup().size(),3);


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
    public TableValueConstructor exampleA = new MockParentBuilder<TableValueConstructorBuilder<MockParent<TableValueConstructor>>,TableValueConstructor>
                (TableValueConstructorBuilder.class,TableValueConstructor.class)
                .$child()
                    .$(e_n_string("FT2"),
                            e_n_string("Square Feet "),
                            e_string("20080923"))
                    .$(e_n_string("Y"),
                            e_n_string("Yards"),
                            e_string("20080923"))
                    .$(e_n_string("Y3"),
                            e_n_string("Cubic Yards"),
                            e_string("20080923"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withItem()
                    .withRowValueExpression(e_n_string("FT2"))
                    .withRowValueExpression(e_n_string("Square Feet "))
                    .withRowValueExpression(e_string("20080923"))
                    .and()
                .withItem()
                    .withRowValueExpression(e_n_string("Y"))
                    .withRowValueExpression(e_n_string("Yards"))
                    .withRowValueExpression(e_string("20080923"))
                    .and()
                .withItem()
                    .withRowValueExpression(e_n_string("Y3"))
                    .withRowValueExpression(e_n_string("Cubic Yards"))
                    .withRowValueExpression(e_string("20080923"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).toString(),"N'FT2'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(1).toString(),"N'Square Feet '");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(2).toString(),"'20080923'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).toString(),"N'Y'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(1).toString(),"N'Yards'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(2).toString(),"'20080923'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).toString(),"N'Y3'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(1).toString(),"N'Cubic Yards'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(2).toString(),"'20080923'");

    }


    // @formatter:off
    //parent+quick
    /**
     * VALUES ('Recommendation','Other'), ('Advertisement', DEFAULT), (NULL, 'Promotion');
     */
    public TableValueConstructor exampleB = new MockParentBuilder<TableValueConstructorBuilder<MockParent<TableValueConstructor>>,TableValueConstructor>
                (TableValueConstructorBuilder.class,TableValueConstructor.class)
                .$child()
                    .$(e_string("Recommendation"),
                            e_string("Other"))
                    .$(e_string("Advertisement"),
                            e_default())
                    .$(e_null(),
                            e_string("Promotion"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withItem()
                    .withRowValueExpression(e_string("Recommendation"))
                    .withRowValueExpression(e_string("Other"))
                    .and()
                .withItem()
                    .withRowValueExpression(e_string("Advertisement"))
                    .withRowValueExpression(e_default())
                    .and()
                .withItem()
                    .withRowValueExpression(e_null())
                    .withRowValueExpression(e_string("Promotion"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).toString(),"'Recommendation'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(1).toString(),"'Other'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).toString(),"'Advertisement'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(1).toString(),"DEFAULT");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).toString(),"NULL");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(1).toString(),"'Promotion'");
    }


    // @formatter:off
    //parent+quick
    /**
     * VALUES ('Blade'), ('Crown Race'), ('AWC Logo Cap')
     */
    public TableValueConstructor exampleC = new MockParentBuilder<TableValueConstructorBuilder<MockParent<TableValueConstructor>>,TableValueConstructor>
                (TableValueConstructorBuilder.class,TableValueConstructor.class)
                .$child()
                    .$(e_string("Blade"))
                    .$(e_string("Crown Race"))
                    .$(e_string("AWC Logo Cap"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withItem()
                    .withRowValueExpression(e_string("Blade"))
                    .and()
                .withItem()
                    .withRowValueExpression(e_string("Crown Race"))
                    .and()
                .withItem()
                    .withRowValueExpression(e_string("AWC Logo Cap"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).toString(),"'Blade'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).toString(),"'Crown Race'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).toString(),"'AWC Logo Cap'");
    }


    // @formatter:off
    //parent+quick
    /**
     * VALUES ('Recommendation','Other'), ('Review', 'Marketing'), ('Internet', 'Promotion')
     */
    public TableValueConstructor exampleD = new MockParentBuilder<TableValueConstructorBuilder<MockParent<TableValueConstructor>>,TableValueConstructor>
                (TableValueConstructorBuilder.class,TableValueConstructor.class)
                .$child()
                    .$(e_string("Recommendation"),e_string("Other"))
                    .$(e_string("Review"),e_string("Marketing"))
                    .$(e_string("Internet"),e_string("Promotion"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        TableValueConstructor tableValueConstructor = new TableValueConstructorBuilder<Void>()
                .withItem()
                    .withRowValueExpression(e_string("Recommendation"))
                    .withRowValueExpression(e_string("Other"))
                    .and()
                .withItem()
                    .withRowValueExpression(e_string("Review"))
                    .withRowValueExpression(e_string("Marketing"))
                    .and()
                .withItem()
                    .withRowValueExpression(e_string("Internet"))
                    .withRowValueExpression(e_string("Promotion"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(0).toString(),"'Recommendation'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(0).get(1).toString(),"'Other'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(0).toString(),"'Review'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(1).get(1).toString(),"'Marketing'");

        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(0).toString(),"'Internet'");
        Assert.assertEquals(tableValueConstructor.getRowValueExpressionListGroup().get(2).get(1).toString(),"'Promotion'");
    }

}
