package com.xy.xsql.tsql.builder.chain.functions.json;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.json.Json_Modify;
import com.xy.xsql.tsql.model.functions.json.Json_Query;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._int;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._numeric;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_cast;
import static com.xy.xsql.tsql.builder.chain.functions.JsonFunctions.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class JsonModifyFunctionTest {


    /**
     * JSON_MODIFY(@info,'$.name','Mike')
     */
    public Json_Modify example1 = f_json_modify(
            e_variable("info"),
            "$.name",
            c_string("Mike")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getExpression().getClass(), LocalVariable.class);
        assertEquals(example1.getPath(),"$.name");
        assertEquals(example1.getNewValue().getClass(), StringConstant.class);
    }

    /**
     * JSON_MODIFY(@info,'$.name',NULL)
     */
    public Json_Modify example3 = f_json_modify(
            e_variable("info"),
            "$.name",
            e_null()
    );

    @Test
    public void testExample3(){
        assertEquals(example3.getExpression().getClass(), LocalVariable.class);
        assertEquals(example3.getPath(),"$.name");
        assertEquals(example3.getNewValue().getClass(), Null.class);
    }

    /**
     * JSON_MODIFY(JSON_MODIFY(JSON_MODIFY(@info,'$.name','Mike'),'$.surname','Smith'),'append $.skills','Azure')
     */
    public Json_Modify example4 = f_json_modify(
            f_json_modify(
                    f_json_modify(
                            e_variable("info"),
                            "$.name",
                            c_string("Mike")
                    ),
                    "$.surname",
                    c_string("Smith")
            ),
            "append $.skills",
            c_string("Azure")
    );

    @Test
    public void testExample4(){
        assertEquals(example4.getExpression().getClass(), Json_Modify.class);
        assertEquals(example4.getPath(),"append $.skills");
        assertEquals(example4.getNewValue().getClass(), StringConstant.class);
    }

    /**
     * JSON_MODIFY(
     JSON_MODIFY(@product,'$.Price',CAST(JSON_VALUE(@product,'$.price') AS NUMERIC(4,2))),
     '$.price',
     NULL
     )
     */
    public Json_Modify example5 = f_json_modify(
            f_json_modify(
                    e_variable("product"),
                    "$.Price",
                    f_cast(
                            f_json_value(
                                    e_variable("product"),
                                    "$.price"
                            ),
                            _numeric(4,2)
                    )
            ),
            "$.price",
            e_null()
    );

    @Test
    public void testExample5(){
        assertEquals(example5.getExpression().getClass(), Json_Modify.class);
        assertEquals(example5.getPath(),"$.price");
        assertEquals(example5.getNewValue().getClass(), Null.class);
    }

    /**
     * JSON_MODIFY(@stats,'$.click_count',
     CAST(JSON_VALUE(@stats,'$.click_count') AS INT)+1)
     */
    public Json_Modify example6 = f_json_modify(
            e_variable("stats"),
            "$.click_count",
            e_addition(
                f_cast(
                        f_json_value(
                                e_variable("stats"),
                                "$.click_count"
                        ),
                        _int()
                ),
                e_number(1)
            )
    );

    @Test
    public void testExample6(){
        assertEquals(example6.getExpression().getClass(), LocalVariable.class);
        assertEquals(example6.getPath(),"$.click_count");
        assertEquals(example6.getNewValue().getClass(), BinaryExpression.class);
    }

    /**
     * JSON_MODIFY(@info,'$.skills','["C#","T-SQL","Azure"]')
     */
    public Json_Modify example7 = f_json_modify(
            e_variable("info"),
            "$.skills",
            c_string("[\"C#\",\"T-SQL\",\"Azure\"]")
    );

    @Test
    public void testExample7(){
        assertEquals(example7.getExpression().getClass(), LocalVariable.class);
        assertEquals(example7.getPath(),"$.skills");
        assertEquals(example7.getNewValue().getClass(), StringConstant.class);
    }

    /**
     * JSON_MODIFY(@info,'$.skills',JSON_QUERY('["C#","T-SQL","Azure"]'))
     */
    public Json_Modify example8 = f_json_modify(
            e_variable("info"),
            "$.skills",
            f_json_query(
                    c_string("[\"C#\",\"T-SQL\",\"Azure\"]")
            )
    );

    @Test
    public void testExample8(){
        assertEquals(example8.getExpression().getClass(), LocalVariable.class);
        assertEquals(example8.getPath(),"$.skills");
        assertEquals(example8.getNewValue().getClass(), Json_Query.class);
    }

    /**
     * JSON_MODIFY(jsonCol,"$.info.address.town",'London')
     */
    public Json_Modify example9 = f_json_modify(
            c("jsonCol"),
            "$.info.address.town",
            c_string("London")
    );

    @Test
    public void testExample9(){
        assertEquals(example9.getExpression().getClass(), ColumnName.class);
        assertEquals(example9.getPath(),"$.info.address.town");
        assertEquals(example9.getNewValue().getClass(), StringConstant.class);
    }

}