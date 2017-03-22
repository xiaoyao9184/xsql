package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.tsql.core.statement.SelectBuilder;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.SetVariable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.*;
import static com.xy.xsql.tsql.core.expression.GroupExpressionBuilder.e_addition;
import static com.xy.xsql.tsql.core.expression.GroupExpressionBuilder.e_multiplication;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class SetVariableBuilderTest {

    /**
     * SET @myvar = 'This is a test';
     */
    @Test
    public void testExample1A(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("myvar")
                .withExpression(e_string("This is a test"))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@myvar");
        Assert.assertEquals(setVariable.getExpression().toString(),"'This is a test'");
    }

    /**
     * SET @state = N'Oregon';
     */
    @Test
    public void testExample1B(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("state")
                .withExpression(e_n_string("Oregon"))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@state");
        Assert.assertEquals(setVariable.getExpression().toString(),"N'Oregon'");
    }

    /**
     * SET  @NewBalance  =  10;
     SET  @NewBalance  =  @NewBalance  *  10;

     SET @NewBalance *= 10;
     */
    @Test
    public void testExample1C(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("NewBalance")
                .withExpression(e_number(10))
                .build();

        SetVariable setVariable2 = new SetVariableBuilder<Void>()
                .withLocalVariable("NewBalance")
                .withExpression(e_multiplication(
                        e_variable("NewBalance"),
                        e_number(10)))
                .build();

        SetVariable setVariable3 = new SetVariableBuilder<Void>()
                .withLocalVariable("NewBalance")
                .withCompound(Compound.MULTIPLY_EQUALS)
                .withExpression(e_number(10))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@NewBalance");
        Assert.assertEquals(setVariable.getExpression().toString(),"10");

        Assert.assertEquals(setVariable2.getLocalVariable().toString(),"@NewBalance");
        Assert.assertEquals(setVariable2.getExpression().getClass(), GroupExpression.class);

        Assert.assertEquals(setVariable3.getLocalVariable().toString(),"@NewBalance");
        Assert.assertEquals(setVariable3.getCompound(),Compound.MULTIPLY_EQUALS);
        Assert.assertEquals(setVariable3.getExpression().toString(),"10");
    }

    /**
     * SET @my_variable = my_cursor ;
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("my_variable")
                .withExpression(e("my_cursor"))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@my_variable");
        Assert.assertEquals(setVariable.getExpression().toString(),"my_cursor");
    }

    /**
     * SET @CursorVar = CURSOR SCROLL DYNAMIC
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("CursorVar")
                .withExpression(e("CURSOR SCROLL DYNAMIC"))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@CursorVar");
        Assert.assertEquals(setVariable.getExpression().toString(),"CURSOR SCROLL DYNAMIC");
    }

    /**
     * SET @rows = (SELECT COUNT(*) FROM Sales.Customer);
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Select.QuerySpecification querySpecification = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withExpression(e("COUNT(*)"))
                    .and()
                .withFrom()
                    .withItem()._Base()
                        .withTableName(t("Sales","Customer"))
                        .and()
                    .and()
                .build();

        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("rows")
                .withExpression(e_subquery(querySpecification))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@rows");
        Assert.assertEquals(setVariable.getExpression().getClass(),GroupExpression.class);
    }

    /**
     * SET @p.X = @p.X + 1.1;
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("p.X")
                .withExpression(e_addition(
                        e_variable("p.X"),
                        e_number(1.1)
                ))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@p.X");
        Assert.assertEquals(setVariable.getExpression().getClass(),GroupExpression.class);
    }

    /**
     * SET @p=point.SetXY(23.5, 23.5);
     */
    @Test
    public void testExampleH(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("p")
                .withExpression(e("point.SetXY(23.5, 23.5)"))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@p");
        Assert.assertEquals(setVariable.getExpression().toString(),"point.SetXY(23.5, 23.5)");
    }

    /**
     * SET @p.SetXY(22, 23);
     */
    @Test
    public void testExampleI(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("p.SetXY(22, 23)")
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@p.SetXY(22, 23)");
    }


}
