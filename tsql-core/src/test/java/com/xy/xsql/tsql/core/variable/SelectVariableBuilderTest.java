package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.SelectVariable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.Expressions.e_string;
import static com.xy.xsql.tsql.core.expression.Expressions.e_subquery;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class SelectVariableBuilderTest {

    /**
     * SELECT @var1 = 'Generic Name';
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        SelectVariable selectVariable = new SelectVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable("var1")
                    .withExpression(e_string("Generic Name"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(selectVariable.getItems().get(0).getLocalVariable().toString(),"@var1");
        Assert.assertEquals(selectVariable.getItems().get(0).getExpression().toString(),"'Generic Name'");
    }

    /**
     * SELECT @var1 = 'Generic Name'
     SELECT @var1 = (SELECT Name
     FROM Sales.Store
     WHERE CustomerID = 1000)
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        SelectVariable selectVariable = new SelectVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable("var1")
                    .withExpression(e_string("Generic Name"))
                    .and()
                .build();

        SelectVariable selectVariable2 = new SelectVariableBuilder<Void>()
                .withItem()
                    .withLocalVariable("var1")
                    .withExpression(e_subquery(new Select.QuerySpecification()))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(selectVariable.getItems().get(0).getLocalVariable().toString(),"@var1");
        Assert.assertEquals(selectVariable.getItems().get(0).getExpression().toString(),"'Generic Name'");

        Assert.assertEquals(selectVariable2.getItems().get(0).getLocalVariable().toString(),"@var1");
        Assert.assertEquals(selectVariable2.getItems().get(0).getExpression().getClass(),GroupExpression.class);
    }

}
