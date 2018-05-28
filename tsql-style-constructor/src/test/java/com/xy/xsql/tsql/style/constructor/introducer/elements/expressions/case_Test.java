package com.xy.xsql.tsql.style.constructor.introducer.elements.expressions;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.Case;
import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.queries.predicates.Exists;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.p_equal;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.expressions.case_.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.FROM;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.predicates.exists.EXISTS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select_.$;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select_.SELECT;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.where.WHERE;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
public class case_Test {


    /**
     * CASE ProductLine
     WHEN 'R' THEN 'Road'
     WHEN 'M' THEN 'Mountain'
     WHEN 'T' THEN 'Touring'
     WHEN 'S' THEN 'Other sale items'
     ELSE 'Not for sale'
     END
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Case aCase = CASE(
                e("ProductLine")
                ,WHEN(e_string("R"),
                        THEN(e_string("Road")))
                .WHEN(e_string("M"),
                        THEN(e_string("Mountain")))
                .WHEN(e_string("T"),
                        THEN(e_string("Touring")))
                .WHEN(e_string("S"),
                        THEN(e_string("Other sale items")))
                ,ELSE(e_string("Not for sale"))
        ).build();
        // @formatter:on

        assertEquals(aCase.getInputExpression().toString(),"ProductLine");
        assertTrue(aCase.getElseResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getElseResultExpression()).getString(),"Not for sale");

        assertEquals(aCase.getWhenThenExpressionList().size(),4);
        assertTrue(aCase.getWhenThenExpressionList().get(0).getWhenExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(0).getWhenExpression()).getString(),"R");
        assertTrue(aCase.getWhenThenExpressionList().get(1).getWhenExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(1).getWhenExpression()).getString(),"M");
        assertTrue(aCase.getWhenThenExpressionList().get(2).getWhenExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(2).getWhenExpression()).getString(),"T");
        assertTrue(aCase.getWhenThenExpressionList().get(3).getWhenExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(3).getWhenExpression()).getString(),"S");

        assertTrue(aCase.getWhenThenExpressionList().get(0).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(0).getResultExpression()).getString(),"Road");
        assertTrue(aCase.getWhenThenExpressionList().get(1).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(1).getResultExpression()).getString(),"Mountain");
        assertTrue(aCase.getWhenThenExpressionList().get(2).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(2).getResultExpression()).getString(),"Touring");
        assertTrue(aCase.getWhenThenExpressionList().get(3).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(3).getResultExpression()).getString(),"Other sale items");
    }

    /**
     * CASE
     WHEN ListPrice =  0 THEN 'Mfg item - not for resale'
     WHEN ListPrice < 50 THEN 'Under $50'
     WHEN ListPrice >= 50 and ListPrice < 250 THEN 'Under $250'
     WHEN ListPrice >= 250 and ListPrice < 1000 THEN 'Under $1000'
     ELSE 'Over $1000'
     END
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Case aCase = CASE(
                WHEN(e_equals(e("ListPrice"),e_number(0)),
                        THEN(e_string("Mfg item - not for resale")))
                .WHEN(e_less(e("ListPrice"),e_number(50)),
                        THEN(e_string("Under $50")))
                .WHEN(
                        e_and(
                            e_greater_equal(e("ListPrice"),e_number(50)),
                            e_less(e("ListPrice"),e_number(250))
                        ),
                        THEN(e_string("Under $250")))
                .WHEN(
                        e_and(
                            e_greater_equal(e("ListPrice"),e_number(250)),
                            e_less(e("ListPrice"),e_number(1000))
                        ),
                        THEN(e_string("Under $1000")))
                ,ELSE(e_string("Over $1000"))
        ).build();
        // @formatter:on

        assertNull(aCase.getInputExpression());
        assertTrue(aCase.getElseResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getElseResultExpression()).getString(),"Over $1000");

        assertEquals(aCase.getWhenThenExpressionList().size(),4);
        assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        assertEquals(aCase.getWhenThenExpressionList().get(1).getWhenExpression().getClass(),BinaryExpression.class);
        assertEquals(aCase.getWhenThenExpressionList().get(2).getWhenExpression().getClass(),BinaryExpression.class);
        assertEquals(aCase.getWhenThenExpressionList().get(3).getWhenExpression().getClass(),BinaryExpression.class);
        assertTrue(aCase.getWhenThenExpressionList().get(0).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(0).getResultExpression()).getString(),"Mfg item - not for resale");
        assertTrue(aCase.getWhenThenExpressionList().get(1).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(1).getResultExpression()).getString(),"Under $50");
        assertTrue(aCase.getWhenThenExpressionList().get(2).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(2).getResultExpression()).getString(),"Under $250");
        assertTrue(aCase.getWhenThenExpressionList().get(3).getResultExpression() instanceof StringConstant);
        assertEquals(((StringConstant)aCase.getWhenThenExpressionList().get(3).getResultExpression()).getString(),"Under $1000");
    }

    /**
     * CASE SalariedFlag WHEN 1 THEN BusinessEntityID END DESC
     ,CASE WHEN SalariedFlag = 0 THEN BusinessEntityID END
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Case aCase = CASE(
                e("SalariedFlag")
                ,WHEN(e_number(1),
                        THEN(e("BusinessEntityID")))
                ,ELSE(e("DESC"))
        ).build();

        Case aCase2 = CASE(
                WHEN(
                        e_equals(
                            e("SalariedFlag"),
                            e_number(0)
                        ),
                        THEN(e("BusinessEntityID")))
        ).build();
        // @formatter:on

        assertEquals(aCase.getInputExpression().toString(),"SalariedFlag");
        assertEquals(aCase.getElseResultExpression().toString(),"DESC");

        assertEquals(aCase.getWhenThenExpressionList().size(),1);
        assertTrue(aCase.getWhenThenExpressionList().get(0).getWhenExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)aCase.getWhenThenExpressionList().get(0).getWhenExpression()).getNumber().toString(), "1");
        assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().toString(),"BusinessEntityID");


        assertNull(aCase2.getInputExpression());
        assertNull(aCase2.getElseResultExpression());

        assertEquals(aCase2.getWhenThenExpressionList().size(),1);
        assertEquals(aCase2.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        assertEquals(aCase2.getWhenThenExpressionList().get(0).getResultExpression().toString(),"BusinessEntityID");
    }

    /**
     * CASE
     WHEN ((VacationHours - 10.00) < 0) THEN VacationHours + 40
     ELSE (VacationHours + 20.00)
     END
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Case aCase = CASE(
                WHEN(
                        e_less(
                                e_subtraction(
                                        e("VacationHours"),
                                        e_number(10.00)
                                ),
                                e_number(0)
                        ),
                        THEN(e_addition(
                                e("VacationHours"),
                                e_number(40))))
                ,ELSE(
                        e_addition(
                                e("VacationHours"),
                                e_number(20.00)
                        )
                )
        ).build();
        // @formatter:on

        assertNull(aCase.getInputExpression());
        assertEquals(aCase.getElseResultExpression().getClass(),BinaryExpression.class);

        assertEquals(aCase.getWhenThenExpressionList().size(),1);
        assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().getClass(),BinaryExpression.class);
    }

    /**
     * CASE
     -- Check for employee
     WHEN EXISTS(SELECT * FROM HumanResources.Employee AS e
     WHERE e.BusinessEntityID = @BusinessEntityID)
     THEN 'Employee'

     -- Check for vendor
     WHEN EXISTS(SELECT * FROM Person.BusinessEntityContact AS bec
     WHERE bec.BusinessEntityID = @BusinessEntityID)
     THEN 'Vendor'

     -- Check for store
     WHEN EXISTS(SELECT * FROM Purchasing.Vendor AS v
     WHERE v.BusinessEntityID = @BusinessEntityID)
     THEN 'Store Contact'

     -- Check for individual consumer
     WHEN EXISTS(SELECT * FROM Sales.Customer AS c
     WHERE c.PersonID = @BusinessEntityID)
     THEN 'Consumer'
     END;
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Case aCase = CASE(
                WHEN(
                    EXISTS(
                            SELECT(
                                    $()
                                    ,FROM(t("HumanResources.Employee"),AS("e"))
                                    ,WHERE(p_equal(c("e.BusinessEntityID"),c("@BusinessEntityID")))
                            )
                    ).build(),
                    THEN(e_string("Employee")))
                .WHEN(
                    EXISTS(
                            SELECT(
                                    $()
                                    ,FROM(t("Person.BusinessEntityContact"),AS("bec"))
                                    ,WHERE(p_equal(c("bec.BusinessEntityID"),c("@BusinessEntityID")))
                            )
                    ).build(),
                    THEN(e_string("Vendor"))
                )
                .WHEN(
                    EXISTS(
                            SELECT(
                                    $()
                                    ,FROM(t("Purchasing.Vendor"),AS("v"))
                                    ,WHERE(p_equal(c("v.BusinessEntityID"),c("@BusinessEntityID")))
                            )
                    ).build(),
                    THEN(e_string("Store Contact"))
                )
                .WHEN(
                    EXISTS(
                            SELECT(
                                    $()
                                    ,FROM(t("Sales.Customer"),AS("c"))
                                    ,WHERE(p_equal(c("c.PersonID"),c("@BusinessEntityID")))
                            )
                    ).build(),
                    THEN(e_string("Consumer"))
                )
        ).build();
        // @formatter:on

        assertNull(aCase.getInputExpression());
        assertNull(aCase.getElseResultExpression());

        assertEquals(aCase.getWhenThenExpressionList().size(),4);
        assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), Exists.class);
        assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().getClass(),StringConstant.class);
        assertEquals(aCase.getWhenThenExpressionList().get(1).getWhenExpression().getClass(), Exists.class);
        assertEquals(aCase.getWhenThenExpressionList().get(1).getResultExpression().getClass(),StringConstant.class);
        assertEquals(aCase.getWhenThenExpressionList().get(2).getWhenExpression().getClass(), Exists.class);
        assertEquals(aCase.getWhenThenExpressionList().get(2).getResultExpression().getClass(),StringConstant.class);
        assertEquals(aCase.getWhenThenExpressionList().get(3).getWhenExpression().getClass(), Exists.class);
        assertEquals(aCase.getWhenThenExpressionList().get(3).getResultExpression().getClass(),StringConstant.class);
    }

    /**
     * CASE WHEN Gender = 'M'
     THEN ph1.Rate
     ELSE NULL END
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Case aCase = CASE(
                WHEN(
                        e_equals(
                                e("Gender"),
                                e_string("M")
                        ),
                        THEN(c("ph1","Rate")))
                ,ELSE(
                        e_null()
                )
        ).build();
        // @formatter:on

        assertNull(aCase.getInputExpression());
        assertEquals(aCase.getElseResultExpression().getClass(),Null.class);
        assertEquals(aCase.getElseResultExpression().toString(),"NULL");

        assertEquals(aCase.getWhenThenExpressionList().size(),1);
        assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().getClass(), ColumnName.class);
    }

}