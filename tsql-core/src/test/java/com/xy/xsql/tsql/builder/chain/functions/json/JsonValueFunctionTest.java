package com.xy.xsql.tsql.builder.chain.functions.json;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.json.Json_Value;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.JsonFunctions.f_json_value;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class JsonValueFunctionTest {


    /**
     * JSON_VALUE(jsonInfo,'$.info.address[0].town')
     */
    public Json_Value example1 = f_json_value(
            c("jsonInfo"),
            "$.info.address[0].town"
    );

    /**
     * JSON_VALUE(jsonInfo,'$.info.address[0].state')
     */
    public Json_Value example11 = f_json_value(
            c("jsonInfo"),
            "$.info.address[0].state"
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getExpression().getClass(), ColumnName.class);
        assertEquals(example1.getPath(),"$.info.address[0].town");
        assertEquals(example11.getExpression().getClass(), ColumnName.class);
        assertEquals(example11.getPath(),"$.info.address[0].state");
    }

    /**
     * JSON_VALUE(jsonContent, '$.address[0].longitude')
     */
    public Json_Value example2 = f_json_value(
            c("jsonInfo"),
            "$.address[0].longitude"
    );

    @Test
    public void testExample2(){
        assertEquals(example2.getExpression().getClass(), ColumnName.class);
        assertEquals(example2.getPath(),"$.address[0].longitude");
    }

}