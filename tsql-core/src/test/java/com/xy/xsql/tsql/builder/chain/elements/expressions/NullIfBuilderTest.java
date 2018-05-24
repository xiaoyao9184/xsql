package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.expressions.Coalesce;
import com.xy.xsql.tsql.model.elements.expressions.NullIf;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class NullIfBuilderTest {


    /**
     * NULLIF(COALESCE(current_year,
     previous_year), 0.00)
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        NullIf nullIf = new NullIfBuilder<Void>()
                .withExpression(
                    e_coalesce(
                            e("current_year"),
                            e("previous_year")))
                .withExpression(e_number(0.00))
                .build();
        // @formatter:on

        assertEquals(nullIf.getExpressionLeft().getClass(), Coalesce.class);
        assertTrue(nullIf.getExpressionRight() instanceof NumberConstant);
        assertEquals(((NumberConstant)nullIf.getExpressionRight()).getNumber().toString(),"0.0");
    }

    /**
     * NULLIF(MakeFlag,FinishedGoodsFlag)
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        NullIf nullIf = new NullIfBuilder<Void>()
                .withExpression(e("MakeFlag"))
                .withExpression(e("FinishedGoodsFlag"))
                .build();
        // @formatter:on

        assertEquals(nullIf.getExpressionLeft().toString(),"MakeFlag");
        assertEquals(nullIf.getExpressionRight().toString(),"FinishedGoodsFlag");
    }

    /**
     * NULLIF(current_year,
     previous_year)
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        NullIf nullIf = new NullIfBuilder<Void>()
                .withExpression(e("current_year"))
                .withExpression(e("previous_year"))
                .build();
        // @formatter:on

        assertEquals(nullIf.getExpressionLeft().toString(),"current_year");
        assertEquals(nullIf.getExpressionRight().toString(),"previous_year");
    }

}
