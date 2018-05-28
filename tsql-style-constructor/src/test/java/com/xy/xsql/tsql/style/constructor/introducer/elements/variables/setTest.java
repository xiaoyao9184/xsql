package com.xy.xsql.tsql.style.constructor.introducer.elements.variables;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.elements.variables.SetVariable;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.variables.set_$local_variable.SET;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.FROM;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select_.$;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select_.SELECT;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/17.
 */
public class setTest {


    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/set-local-variable-transact-sql#examples
     */


    // @formatter:off
    /**
     * SET @myvar = 'This is a test'
     */
    public SetVariable exampleA = SET("myvar")
            .$assign(e_string("This is a test"))
            .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getLocalVariable().toString(),"@myvar");
        assertTrue(exampleA.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)exampleA.getExpression()).getString(),"This is a test");
    }


    // @formatter:off
    /**
     * SET @state = N'Oregon'
     */
    public SetVariable exampleB = SET("state")
            .$assign(e_n_string("Oregon"))
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getLocalVariable().toString(),"@state");
        assertTrue(exampleB.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)exampleB.getExpression()).getString(),"Oregon");
    }


    // @formatter:off
    /**
     * SET  @NewBalance  =  10
     */
    public SetVariable exampleC1 = SET("NewBalance")
            .$assign(e_number(10))
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * SET  @NewBalance  =  @NewBalance  *  10
     */
    public SetVariable exampleC2 = SET("NewBalance")
            .$assign(e_multiplication(
                    e_variable("NewBalance"),
                    e_number(10)
            ))
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * SET @NewBalance *= 10
     */
    public SetVariable exampleC3 = SET("NewBalance")
            .$multiply_assign(e_number(10))
            .build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC1.getLocalVariable().toString(),"@NewBalance");
        assertTrue(exampleC1.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)exampleC1.getExpression()).getNumber().toString(),"10");

        assertEquals(exampleC2.getLocalVariable().toString(),"@NewBalance");
        assertEquals(exampleC2.getExpression().getClass(), BinaryExpression.class);

        assertEquals(exampleC3.getLocalVariable().toString(),"@NewBalance");
        assertEquals(exampleC3.getCompound(),Compound.MULTIPLY_ASSIGNMENT);
        assertTrue(exampleC3.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)exampleC3.getExpression()).getNumber().toString(),"10");
    }


    // @formatter:off
    /**
     * SET @my_variable = my_cursor
     */
    public SetVariable exampleD = SET("my_variable")
            //TODO CURSOR
            .$assign(e("my_cursor"))
            .build();
    // @formatter:on

    /**
     * SET @my_variable = my_cursor ;
     */
    @Test
    public void testExampleD(){
        assertEquals(exampleD.getLocalVariable().toString(),"@my_variable");
        assertEquals(exampleD.getExpression().toString(),"my_cursor");
    }


    // @formatter:off
    /**
     * SET @CursorVar = CURSOR SCROLL DYNAMIC
     */
    public SetVariable exampleE = SET("CursorVar")
            //TODO CURSOR
            .$assign(e("CURSOR SCROLL DYNAMIC"))
            .build();
    // @formatter:on

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getLocalVariable().toString(),"@CursorVar");
        assertEquals(exampleE.getExpression().toString(),"CURSOR SCROLL DYNAMIC");
    }


    // @formatter:off
    /**
     * SET @rows = (SELECT COUNT(*) FROM Sales.Customer)
     */
    public SetVariable exampleF = SET("rows")
            .$assign(
                    e_subquery(
                    SELECT(
                            $(e("COUNT(*)"))
                            ,FROM(t("Sales","Customer"))
                    ).build())
            )
            .build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getLocalVariable().toString(),"@rows");
        assertEquals(exampleF.getExpression().getClass(),GroupExpression.class);
    }


    // @formatter:off
    /**
     * SET @p.X = @p.X + 1.1
     */
    public SetVariable exampleG = SET("p.X")
            .$assign(e_addition(
                    e_variable("p.X"),
                    e_number(1.1)
            ))
            .build();
    // @formatter:on

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getLocalVariable().toString(),"@p.X");
        assertEquals(exampleG.getExpression().getClass(),BinaryExpression.class);
    }


    // @formatter:off
    /**
     * SET @p=point.SetXY(23.5, 23.5)
     */
    public SetVariable exampleH = SET("p")
            .$assign(e("point.SetXY(23.5, 23.5)"))
            .build();
    // @formatter:on

    /**
     * SET @p=point.SetXY(23.5, 23.5);
     */
    @Test
    public void testExampleH(){
        assertEquals(exampleH.getLocalVariable().toString(),"@p");
        assertEquals(exampleH.getExpression().toString(),"point.SetXY(23.5, 23.5)");
    }


    // @formatter:off
    /**
     * SET @p.SetXY(22, 23)
     */
    public SetVariable exampleI = SET("@p.SetXY(22, 23)")
            //TODO local_variable property_name
            .build();
    // @formatter:on

    @Test
    public void testExampleI(){
        assertEquals(exampleI.getLocalVariable().toString(),"@p.SetXY(22, 23)");
    }

    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/set-local-variable-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */


}