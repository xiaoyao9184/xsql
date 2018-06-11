package com.xy.xsql.tsql.builder.chain.functions.json;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.json.Json_Query;
import com.xy.xsql.tsql.model.functions.json.Json_Value;
import com.xy.xsql.tsql.model.functions.string.Concat;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.JsonFunctions.f_json_query;
import static com.xy.xsql.tsql.builder.chain.functions.JsonFunctions.f_json_value;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_concat;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class JsonQueryFunctionTest {


    /**
     * JSON_QUERY(CustomFields,'$.OtherLanguages')
     */
    public Json_Query example1 = f_json_query(
            c("CustomFields"),
            "$.OtherLanguages"
    );

    /**
     * JSON_QUERY(Tags)
     */
    public Json_Query example11 = f_json_query(
            c("Tags")
    );

    /**
     * JSON_QUERY(CONCAT('["',ValidFrom,'","',ValidTo,'"]'))
     */
    public Json_Query example12 = f_json_query(
            f_concat(
                    c_string("[\""),
                    c("ValidFrom"),
                    c_string("\",\""),
                    c("ValidTo"),
                    c_string("\"]")
            )
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getExpression().getClass(), ColumnName.class);
        assertEquals(example1.getPath(),"$.OtherLanguages");
        assertEquals(example11.getExpression().getClass(), ColumnName.class);
        assertEquals(example12.getExpression().getClass(), Concat.class);
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