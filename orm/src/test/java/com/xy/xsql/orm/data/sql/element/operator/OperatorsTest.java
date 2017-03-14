package com.xy.xsql.orm.data.sql.element.operator;

import com.xy.xsql.tsql.model.operator.Arithmetic;
import com.xy.xsql.tsql.model.operator.Logical;
import com.xy.xsql.tsql.model.operator.Operators;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/1/10.
 */
public class OperatorsTest {

    @Test
    public void testOperator(){
        Assert.assertEquals(
                Arithmetic.ADDITION.getKeyword(),
                Operators.ADDITION.getKeyword());
        Assert.assertEquals(
                Logical.LIKE.getKeyword(),
                Operators.LIKE.getKeyword());
    }

}
