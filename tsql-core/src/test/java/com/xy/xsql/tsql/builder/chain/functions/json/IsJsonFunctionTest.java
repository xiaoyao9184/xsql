package com.xy.xsql.tsql.builder.chain.functions.json;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.json.IsJson;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.JsonFunctions.f_isjson;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsJsonFunctionTest {


    /**
     * ISJSON(@param)
     */
    public IsJson example1 = f_isjson(
            e_variable("param")
    );

    /**
     * ISJSON(json_col)
     */
    public IsJson example2 = f_isjson(
            c("json_col")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getExpression().getClass(), LocalVariable.class);
        assertEquals(example2.getExpression().getClass(), ColumnName.class);
    }

}