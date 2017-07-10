package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.SelectVariable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERY;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.p_equal;
import static com.xy.xsql.tsql.core.variable.SelectVariableBuilder.SELECT_V;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class SelectVariableBuilderTest {


    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/select-local-variable-transact-sql#examples
     */

    // @formatter:off
    /**
     * SELECT @var1 = 'Generic Name'
     */
    public SelectVariable exampleA = SELECT_V()
            .$("var1")
                .$Assign(e_string("Generic Name"))
            .build();
    // @formatter:on

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


    // @formatter:off
    /**
     * SELECT @var1 = 'Generic Name'
     */
    public SelectVariable exampleB1 = SELECT_V()
            .$("var1")
                .$Assign(e_string("Generic Name"))
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification querySpecificationB2 = QUERY()
                            .$(c("Name"))
                            .$From()
                                .$(t("Sales","Store"))
                                .and()
                            .$Where()
                                .$(p_equal(
                                        c("CustomerID"),
                                        e_number(1000)
                                ))
                                .and()
                        .build();

    /**
     * SELECT @var1 = (SELECT Name
     FROM Sales.Store
     WHERE CustomerID = 1000)
     */
    public SelectVariable exampleB2 = SELECT_V()
            .$("var1")
                .$Assign(e_subquery(querySpecificationB2))
            .build();
    // @formatter:on

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
                    .withExpression(e_subquery(querySpecificationB2))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(selectVariable.getItems().get(0).getLocalVariable().toString(),"@var1");
        Assert.assertEquals(selectVariable.getItems().get(0).getExpression().toString(),"'Generic Name'");

        Assert.assertEquals(selectVariable2.getItems().get(0).getLocalVariable().toString(),"@var1");
        Assert.assertEquals(selectVariable2.getItems().get(0).getExpression().getClass(),GroupExpression.class);
    }

}
