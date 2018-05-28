package com.xy.xsql.tsql.style.constructor.introducer.elements.expressions;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.expressions.Coalesce;
import com.xy.xsql.tsql.model.elements.expressions.NullIf;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.expressions.coalesce.COALESCE;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.expressions.nullif.NULLIF;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
public class nullifTest {


    /**
     * NULLIF(COALESCE(current_year,
     previous_year), 0.00)
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        NullIf nullIf = NULLIF(
                COALESCE(
                        e("current_year"),
                        e("previous_year")
                ),
                e_number(0.00)
        ).build();
        // @formatter:on

        assertEquals(nullIf.getExpressionLeft().getClass(), Coalesce.class);
        assertEquals(nullIf.getExpressionRight().getClass(),NumberConstant.class);
        assertEquals(((NumberConstant)nullIf.getExpressionRight()).getNumber().toString(),"0.0");
    }

    /**
     * NULLIF(MakeFlag,FinishedGoodsFlag)
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        NullIf nullIf = NULLIF(
                e("MakeFlag"),
                e("FinishedGoodsFlag")
        ).build();
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
        NullIf nullIf = NULLIF(
                e("current_year"),
                e("previous_year")
        ).build();
        // @formatter:on

        assertEquals(nullIf.getExpressionLeft().toString(),"current_year");
        assertEquals(nullIf.getExpressionRight().toString(),"previous_year");
    }

}