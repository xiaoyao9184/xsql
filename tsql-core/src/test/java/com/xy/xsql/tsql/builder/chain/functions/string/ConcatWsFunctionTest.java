package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Concat_Ws;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_null;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_concat_ws;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ConcatWsFunctionTest {


    /**
     * CONCAT_WS( ' - ', database_id, recovery_model_desc, containment_desc)
     */
    public Concat_Ws exampleA = f_concat_ws(
            c_string(" - "),
            c("database_id"),
            c("recovery_model_desc"),
            c("containment_desc")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getSeparator().getClass(), StringConstant.class);
        assertEquals(exampleA.getArgumentList().size(), 3);
    }

    /**
     * CONCAT_WS(',','1 Microsoft Way', NULL, NULL, 'Redmond', 'WA', 98052)
     */
    public Concat_Ws exampleB = f_concat_ws(
            c_string(","),
            c_string("1 Microsoft Way"),
            e_null(),
            e_null(),
            c_string("Redmond"),
            c_string("WA"),
            c_number(98052)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getSeparator().getClass(), StringConstant.class);
        assertEquals(exampleB.getArgumentList().size(), 6);
    }

}