package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.sql.statements.SelectBuilder;
import com.xy.xsql.orm.data.sql.clause.TableValueConstructor;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.statements.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e;
import static com.xy.xsql.orm.core.sql.expression.RowValueExpressionBuilder.rve;

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
     * ERROR
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
                .withSelectList()
                    .withSelectItem().withColumnName("Name").out()
                    .out()
                .withFrom()
                    .withTableSource().withTable("Production.Product")
                    .out()
                    .out()
                .withWhere().withSearchCondition().withPredicate().Operator()
                    .withExpression(e("ProductID"))
                    .withOperator(OperatorEnum.EQUAL)
                    .withExpression(e(720))
                    .out().out().out()
                .build(null);


        Select select2 = new SelectBuilder()
                .withSelectList()
                    .withSelectItem().withColumnName("ListPrice").out()
                    .out()
                .withFrom()
                    .withTableSource().withTable("Production.Product")
                    .out()
                    .out()
                .withWhere().withSearchCondition().withPredicate().Operator()
                    .withExpression(e("ProductID"))
                    .withOperator(OperatorEnum.EQUAL)
                    .withExpression(e(720))
                    .out().out().out()
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
}
