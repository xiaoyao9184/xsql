package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.tsql.core.statement.dml.SelectBuilder;
import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.SetVariable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERY;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.variable.SetVariableBuilder.SET_V;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class SetVariableBuilderTest {


    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/set-local-variable-transact-sql#examples
     */


    // @formatter:off
    /**
     * SET @myvar = 'This is a test'
     */
    public SetVariable exampleA = SET_V()
            .$("myvar")
                .$Assign(e_string("This is a test"))
            .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("myvar")
                .withExpression(e_string("This is a test"))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@myvar");
        Assert.assertEquals(setVariable.getExpression().toString(),"'This is a test'");
    }


    // @formatter:off
    /**
     * SET @state = N'Oregon'
     */
    public SetVariable exampleB = SET_V()
            .$("state")
                .$Assign(e_n_string("Oregon"))
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("state")
                .withExpression(e_n_string("Oregon"))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@state");
        Assert.assertEquals(setVariable.getExpression().toString(),"N'Oregon'");
    }


    // @formatter:off
    /**
     * SET  @NewBalance  =  10
     */
    public SetVariable exampleC1 = SET_V()
            .$("NewBalance")
                .$Assign(e_number(10))
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * SET  @NewBalance  =  @NewBalance  *  10
     */
    public SetVariable exampleC2 = SET_V()
            .$("NewBalance")
                .$Assign(e_multiplication(
                        e_variable("NewBalance"),
                        e_number(10)
                ))
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * SET @NewBalance *= 10
     */
    public SetVariable exampleC3 = SET_V()
            .$("NewBalance")
                .$MultiplyAssign(e_number(10))
            .build();
    // @formatter:on

    @Test
    public void testExampleC(){
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
                .withCompound(Options.MULTIPLY_ASSIGNMENT)
                .withExpression(e_number(10))
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@NewBalance");
        Assert.assertEquals(setVariable.getExpression().toString(),"10");

        Assert.assertEquals(setVariable2.getLocalVariable().toString(),"@NewBalance");
        Assert.assertEquals(setVariable2.getExpression().getClass(), BinaryExpression.class);

        Assert.assertEquals(setVariable3.getLocalVariable().toString(),"@NewBalance");
        Assert.assertEquals(setVariable3.getCompound(),Options.MULTIPLY_ASSIGNMENT);
        Assert.assertEquals(setVariable3.getExpression().toString(),"10");
    }


    // @formatter:off
    /**
     * SET @my_variable = my_cursor
     */
    public SetVariable exampleD = SET_V()
            .$("my_variable")
            //TODO CURSOR
//                .$Assign("my_cursor")
            .build();
    // @formatter:on

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


    // @formatter:off
    /**
     * SET @CursorVar = CURSOR SCROLL DYNAMIC
     */
    public SetVariable exampleE = SET_V()
            .$("my_variable")
            //TODO CURSOR
//                .$Assign("my_cursor")
            .build();
    // @formatter:on

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


    // @formatter:off
    /**
     * SET @rows = (SELECT COUNT(*) FROM Sales.Customer)
     */
    public SetVariable exampleF = SET_V()
            .$("rows")
                .$Assign(e_subquery(
                        QUERY()
                            .$(e("COUNT(*)"))
                            .$From()
                                .$(t("Sales","Customer"))
                                .and()
                            .build()
                ))
            .build();
    // @formatter:on

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


    // @formatter:off
    /**
     * SET @p.X = @p.X + 1.1
     */
    public SetVariable exampleG = SET_V()
            .$("p.X")
                .$Assign(e_addition(
                        e_variable("p.X"),
                        e_number(1.1)
                ))
            .build();
    // @formatter:on

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
        Assert.assertEquals(setVariable.getExpression().getClass(),BinaryExpression.class);
    }


    // @formatter:off
    /**
     * SET @p=point.SetXY(23.5, 23.5)
     */
    public SetVariable exampleH = SET_V()
            .$("p")
                .$Assign(e("point.SetXY(23.5, 23.5)"))
            .build();
    // @formatter:on

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


    // @formatter:off
    /**
     * SET @p.SetXY(22, 23)
     */
    public SetVariable exampleI = SET_V()
            //TODO
            .$("@p.SetXY(22, 23)")
            .build();
    // @formatter:on

    @Test
    public void testExampleI(){
        // @formatter:off
        SetVariable setVariable = new SetVariableBuilder<Void>()
                .withLocalVariable("p.SetXY(22, 23)")
                .build();
        // @formatter:on

        Assert.assertEquals(setVariable.getLocalVariable().toString(),"@p.SetXY(22, 23)");
    }

    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/set-local-variable-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */


}
