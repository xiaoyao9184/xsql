package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.tsql.model.queries.predicates.IsNull;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class IsNullPredicateBuilderTest {

    /**
     * Color IS NULL
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        IsNull isNull = new IsNullPredicateBuilder<Void>()
                .withExpression(e("Color"))
                .build();
        // @formatter:on

        Assert.assertEquals(isNull.getExpression().toString(),"Color");
    }

}
