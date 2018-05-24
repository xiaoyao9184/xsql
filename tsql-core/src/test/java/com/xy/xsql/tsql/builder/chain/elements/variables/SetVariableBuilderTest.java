package com.xy.xsql.tsql.builder.chain.elements.variables;

import com.xy.xsql.tsql.builder.chain.queries.SelectBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.elements.variables.SetVariable;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.elements.variables.SetVariableBuilder.$Set;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static org.junit.Assert.*;


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
    public SetVariable exampleA = $Set()
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

        assertEquals(setVariable.getLocalVariable().toString(),"@myvar");
        assertTrue(setVariable.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setVariable.getExpression()).getString(),"This is a test");
    }


    // @formatter:off
    /**
     * SET @state = N'Oregon'
     */
    public SetVariable exampleB = $Set()
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

        assertEquals(setVariable.getLocalVariable().toString(),"@state");
        assertTrue(setVariable.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setVariable.getExpression()).getString(),"Oregon");
    }


    // @formatter:off
    /**
     * SET  @NewBalance  =  10
     */
    public SetVariable exampleC1 = $Set()
            .$("NewBalance")
                .$Assign(e_number(10))
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * SET  @NewBalance  =  @NewBalance  *  10
     */
    public SetVariable exampleC2 = $Set()
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
    public SetVariable exampleC3 = $Set()
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
                .withCompound(Compound.MULTIPLY_ASSIGNMENT)
                .withExpression(e_number(10))
                .build();
        // @formatter:on

        assertEquals(setVariable.getLocalVariable().toString(),"@NewBalance");
        assertTrue(setVariable.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)setVariable.getExpression()).getNumber().toString(),"10");

        assertEquals(setVariable2.getLocalVariable().toString(),"@NewBalance");
        assertEquals(setVariable2.getExpression().getClass(), BinaryExpression.class);

        assertEquals(setVariable3.getLocalVariable().toString(),"@NewBalance");
        assertEquals(setVariable3.getCompound(),Compound.MULTIPLY_ASSIGNMENT);
        assertTrue(setVariable3.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)setVariable3.getExpression()).getNumber().toString(),"10");
    }


    // @formatter:off
    /**
     * SET @my_variable = my_cursor
     */
    public SetVariable exampleD = $Set()
            .$("my_variable")
            //TODO CURSOR
            .$Assign(e("my_cursor"))
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

        assertEquals(setVariable.getLocalVariable().toString(),"@my_variable");
        assertEquals(setVariable.getExpression().toString(),"my_cursor");
    }


    // @formatter:off
    /**
     * SET @CursorVar = CURSOR SCROLL DYNAMIC
     */
    public SetVariable exampleE = $Set()
            .$("my_variable")
            //TODO CURSOR
            .$Assign(e("my_cursor"))
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

        assertEquals(setVariable.getLocalVariable().toString(),"@CursorVar");
        assertEquals(setVariable.getExpression().toString(),"CURSOR SCROLL DYNAMIC");
    }


    // @formatter:off
    /**
     * SET @rows = (SELECT COUNT(*) FROM Sales.Customer)
     */
    public SetVariable exampleF = $Set()
            .$("rows")
            .$Assign(e_subquery(
                    $Query()
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

        assertEquals(setVariable.getLocalVariable().toString(),"@rows");
        assertEquals(setVariable.getExpression().getClass(),GroupExpression.class);
    }


    // @formatter:off
    /**
     * SET @p.X = @p.X + 1.1
     */
    public SetVariable exampleG = $Set()
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

        assertEquals(setVariable.getLocalVariable().toString(),"@p.X");
        assertEquals(setVariable.getExpression().getClass(),BinaryExpression.class);
    }


    // @formatter:off
    /**
     * SET @p=point.SetXY(23.5, 23.5)
     */
    public SetVariable exampleH = $Set()
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

        assertEquals(setVariable.getLocalVariable().toString(),"@p");
        assertEquals(setVariable.getExpression().toString(),"point.SetXY(23.5, 23.5)");
    }


    // @formatter:off
    /**
     * SET @p.SetXY(22, 23)
     */
    public SetVariable exampleI = $Set()
            //TODO local_variable property_name
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

        assertEquals(setVariable.getLocalVariable().toString(),"@p.SetXY(22, 23)");
    }

    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/set-local-variable-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */


}
