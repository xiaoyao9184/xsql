package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.metadata.TypeProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_addition;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_schema_name;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_typeproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TypePropertyFunctionTest {


    /**
     * TYPEPROPERTY(SCHEMA_NAME(schema_id) + '.' + name, 'OwnerId')
     */
    public TypeProperty exampleA = f_typeproperty(
            e_addition(
                    e_addition(
                        f_schema_name(c("schema_id")),
                            e_string(".")
                    ),
                    c("name")
            ),
            c_string("OwnerId")
    );

    @Test
    public void testExample(){
        assertEquals(exampleA.getType().getClass(), BinaryExpression.class);
        assertEquals(exampleA.getProperty().getClass(), StringConstant.class);
    }

}