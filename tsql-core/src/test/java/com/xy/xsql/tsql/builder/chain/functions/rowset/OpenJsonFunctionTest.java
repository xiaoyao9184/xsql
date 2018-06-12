package com.xy.xsql.tsql.builder.chain.functions.rowset;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.rowset.OpenJson;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.RowsetFunctions.f_openjson;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class OpenJsonFunctionTest {


    /**
     * OPENJSON(@pSearchOptions)
     */
    public OpenJson example1 = f_openjson(
            e_variable("pSearchOptions")
    );

    /**
     * OPENJSON(SalesReasons)
     */
    public OpenJson example3 = f_openjson(
            c("SalesReasons")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getJsonExpression().getClass(), LocalVariable.class);
        assertEquals(example3.getJsonExpression().getClass(), ColumnName.class);
    }


}