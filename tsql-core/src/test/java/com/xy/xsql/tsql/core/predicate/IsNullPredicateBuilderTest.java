package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.tsql.model.predicate.FreeText;
import com.xy.xsql.tsql.model.predicate.IsNull;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;

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
