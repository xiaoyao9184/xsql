package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.Case;
import com.xy.xsql.tsql.model.elements.operators.Operators;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.expression.Expressions.*;


/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class CaseBuilderTest {


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
        Case aCase = new CaseBuilder<Void>()
                .withInput(e("ProductLine"))
                .withWhen(e_string("R"),e_string("Road"))
                .withWhen(e_string("M"),e_string("Mountain"))
                .withWhen(e_string("T"),e_string("Touring"))
                .withWhen(e_string("S"),e_string("Other sale items"))
                .withElse(e_string("Not for sale"))
                .build();
        // @formatter:on

        Assert.assertEquals(aCase.getInputExpression().toString(),"ProductLine");
        Assert.assertEquals(aCase.getElseResultExpression().toString(),"'Not for sale'");

        Assert.assertEquals(aCase.getWhenThenExpressionList().size(),4);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().toString(),"'R'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(1).getWhenExpression().toString(),"'M'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(2).getWhenExpression().toString(),"'T'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(3).getWhenExpression().toString(),"'S'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().toString(),"'Road'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(1).getResultExpression().toString(),"'Mountain'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(2).getResultExpression().toString(),"'Touring'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(3).getResultExpression().toString(),"'Other sale items'");
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
        Case aCase = new CaseBuilder<Void>()
                .withWhen(e_binary(e("ListPrice"),Operators.EQUAL,e_number(0)),
                        e_string("Mfg item - not for resale"))
                .withWhen(e_binary(e("ListPrice"),Operators.LESS,e_number(50)),
                        e_string("Under $50"))
                .withWhen(
                        e_and(
                            e_binary(e("ListPrice"),Operators.GREATER_EQUAL,e_number(50)),
                            e_binary(e("ListPrice"),Operators.LESS,e_number(250))
                        ),
                        e_string("Under $250"))
                .withWhen(
                        e_and(
                            e_binary(e("ListPrice"),Operators.GREATER_EQUAL,e_number(250)),
                            e_binary(e("ListPrice"),Operators.LESS,e_number(1000))
                        ),
                        e_string("Under $1000"))
                .withElse(e_string("Over $1000"))
                .build();
        // @formatter:on

        Assert.assertNull(aCase.getInputExpression());
        Assert.assertEquals(aCase.getElseResultExpression().toString(),"'Over $1000'");

        Assert.assertEquals(aCase.getWhenThenExpressionList().size(),4);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(1).getWhenExpression().getClass(),BinaryExpression.class);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(2).getWhenExpression().getClass(),BinaryExpression.class);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(3).getWhenExpression().getClass(),BinaryExpression.class);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().toString(),"'Mfg item - not for resale'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(1).getResultExpression().toString(),"'Under $50'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(2).getResultExpression().toString(),"'Under $250'");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(3).getResultExpression().toString(),"'Under $1000'");
    }

    /**
     * CASE SalariedFlag WHEN 1 THEN BusinessEntityID END DESC
     ,CASE WHEN SalariedFlag = 0 THEN BusinessEntityID END
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Case aCase = new CaseBuilder<Void>()
                .withInput(e("SalariedFlag"))
                .withWhen(e_number(1),
                        e("BusinessEntityID"))
                .withElse(e("DESC"))
                .build();

        Case aCase2 = new CaseBuilder<Void>()
                .withWhen(
                        e_binary(
                            e("SalariedFlag"),
                            Operators.EQUAL,
                            e_number(0)
                        ),
                        e("BusinessEntityID"))
                .build();
        // @formatter:on

        Assert.assertEquals(aCase.getInputExpression().toString(),"SalariedFlag");
        Assert.assertEquals(aCase.getElseResultExpression().toString(),"DESC");

        Assert.assertEquals(aCase.getWhenThenExpressionList().size(),1);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().toString(), "1");
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().toString(),"BusinessEntityID");


        Assert.assertNull(aCase2.getInputExpression());
        Assert.assertNull(aCase2.getElseResultExpression());

        Assert.assertEquals(aCase2.getWhenThenExpressionList().size(),1);
        Assert.assertEquals(aCase2.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        Assert.assertEquals(aCase2.getWhenThenExpressionList().get(0).getResultExpression().toString(),"BusinessEntityID");
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
        Case aCase = new CaseBuilder<Void>()
                .withWhen(
                        e_binary(
                                e_binary(
                                        e("VacationHours"),
                                        Operators.NEGATIVE,
                                        e_number(10.00)
                                ),
                                Operators.LESS,
                                e_number(0)
                        ),
                        e_binary(
                                e("VacationHours"),
                                Operators.PLUS,
                                e_number(40)))
                .withElse(
                        e_binary(
                                e("VacationHours"),
                                Operators.PLUS,
                                e_number(20.00)
                        )
                )
                .build();
        // @formatter:on

        Assert.assertNull(aCase.getInputExpression());
        Assert.assertEquals(aCase.getElseResultExpression().getClass(),BinaryExpression.class);

        Assert.assertEquals(aCase.getWhenThenExpressionList().size(),1);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().getClass(),BinaryExpression.class);

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
        //TODO
//        // @formatter:off
//        Case aCase = new CaseBuilder<Void>()
//                .withWhen(
//                        e_binary(
//                                e_binary(
//                                        e("VacationHours"),
//                                        Operators.NEGATIVE,
//                                        e_number(10.00)
//                                ),
//                                Operators.LESS,
//                                e_number(0)
//                        ),
//                        e_binary(
//                                e("VacationHours"),
//                                Operators.PLUS,
//                                e_number(40)))
//                .withElse(
//                        e_binary(
//                                e("VacationHours"),
//                                Operators.PLUS,
//                                e_number(20.00)
//                        )
//                )
//                .build();
//        // @formatter:on
//
//        Assert.assertNull(aCase.getInputExpression());
//        Assert.assertEquals(aCase.getElseResultExpression().getClass(),GroupExpression.class);
//
//        Assert.assertEquals(aCase.getWhenThenExpressionList().size(),1);
//        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), GroupExpression.class);
//        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().getClass(),GroupExpression.class);

    }

    /**
     * CASE WHEN Gender = 'M'
     THEN ph1.Rate
     ELSE NULL END
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Case aCase = new CaseBuilder<Void>()
                .withWhen(
                        e_binary(
                                e("Gender"),
                                Operators.EQUAL,
                                e_string("M")
                        ),
                        c("ph1","Rate"))
                .withElse(
                        e_null()
                )
                .build();
        // @formatter:on

        Assert.assertNull(aCase.getInputExpression());
        Assert.assertEquals(aCase.getElseResultExpression().getClass(),Null.class);
        Assert.assertEquals(aCase.getElseResultExpression().toString(),"NULL");

        Assert.assertEquals(aCase.getWhenThenExpressionList().size(),1);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getWhenExpression().getClass(), BinaryExpression.class);
        Assert.assertEquals(aCase.getWhenThenExpressionList().get(0).getResultExpression().getClass(), ColumnName.class);

    }

}
