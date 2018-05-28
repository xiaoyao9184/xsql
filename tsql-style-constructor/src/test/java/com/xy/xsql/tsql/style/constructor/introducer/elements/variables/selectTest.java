package com.xy.xsql.tsql.style.constructor.introducer.elements.variables;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.elements.variables.SelectVariable;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.style.constructor.introducer.queries.select_;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_subquery;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.p_equal;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.variables.select_$local_variable.SELECT;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.FROM;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.where.WHERE;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/17.
 */
public class selectTest {


    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/select-local-variable-transact-sql#examples
     */

    // @formatter:off
    /**
     * SELECT @var1 = 'Generic Name'
     */
    public SelectVariable exampleA = SELECT("var1")
            .$assign(e_string("Generic Name"))
            .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getItems().get(0).getLocalVariable().toString(),"@var1");
        assertTrue(exampleA.getItems().get(0).getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)exampleA.getItems().get(0).getExpression()).getString(),"Generic Name");
    }


    // @formatter:off
    /**
     * SELECT @var1 = 'Generic Name'
     */
    public SelectVariable exampleB1 = SELECT("var1")
            .$assign(e_string("Generic Name"))
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification querySpecificationB2 = select_.SELECT(
                            select_.$(c("Name"))
                            ,FROM(t("Sales","Store"))
                            ,WHERE(p_equal(
                                        c("CustomerID"),
                                        e_number(1000)))
    ).build();

    /**
     * SELECT @var1 = (SELECT Name
     FROM Sales.Store
     WHERE CustomerID = 1000)
     */
    public SelectVariable exampleB2 = SELECT("var1")
            .$assign(e_subquery(querySpecificationB2))
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB1.getItems().get(0).getLocalVariable().toString(),"@var1");
        assertTrue(exampleB1.getItems().get(0).getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)exampleB1.getItems().get(0).getExpression()).getString(),"Generic Name");

        assertEquals(exampleB2.getItems().get(0).getLocalVariable().toString(),"@var1");
        assertEquals(exampleB2.getItems().get(0).getExpression().getClass(),GroupExpression.class);
    }

}