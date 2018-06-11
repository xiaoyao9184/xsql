package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.string.Char;
import com.xy.xsql.tsql.model.functions.string.Concat;
import com.xy.xsql.tsql.model.functions.string.String_Agg;
import com.xy.xsql.tsql.model.functions.system.IsNull;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.*;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_isnull;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StringAggFunctionTest {


    /**
     * STRING_AGG (FirstName, CHAR(13))
     */
    public String_Agg exampleA = f_string_agg(
            c("FirstName"),
            f_char(c_number(13))
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getSeparator().getClass(), Char.class);
    }

    /**
     * STRING_AGG ( ISNULL(FirstName,'N/A'), ',')
     */
    public String_Agg exampleB = f_string_agg(
            f_isnull(c("FirstName"),c_string("N/A")),
            c_string(",")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), IsNull.class);
        assertEquals(exampleB.getSeparator().getClass(), StringConstant.class);
    }

    /**
     * STRING_AGG(CONCAT(FirstName, ' ', LastName, ' (', ModifiedDate, ')'), CHAR(13))
     */
    public String_Agg exampleC = f_string_agg(
            f_concat(c("FirstName"),c_string(" "),c("LastName"),c_string(" ("),c("ModifiedDate"),c_string(")")),
            f_char(c_number(13))
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(), Concat.class);
        assertEquals(exampleC.getSeparator().getClass(), Char.class);
    }

    /**
     * STRING_AGG (tag, ',')
     */
    public String_Agg exampleD = f_string_agg(
            c("tag"),
            c_string(",")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleD.getSeparator().getClass(), StringConstant.class);
    }

    /**
     * STRING_AGG (email, ';')
     */
    public String_Agg exampleE = f_string_agg(
            c("email"),
            c_string(";")
    );

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleE.getSeparator().getClass(), StringConstant.class);
    }

    //ExampleF is same as E

}