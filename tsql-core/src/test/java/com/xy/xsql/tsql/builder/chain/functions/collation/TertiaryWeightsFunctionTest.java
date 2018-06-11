package com.xy.xsql.tsql.builder.chain.functions.collation;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.collation.Tertiary_Weights;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.CollationFunctions.f_tertiary_weights;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TertiaryWeightsFunctionTest {


    /**
     * TERTIARY_WEIGHTS(Col1)
     */
    public Tertiary_Weights example1 = f_tertiary_weights(
            c("Col1"));

    @Test
    public void testExample1(){
        assertEquals(example1.getNonUnicodeCharacterStringExpression().getClass(), ColumnName.class);
    }
}