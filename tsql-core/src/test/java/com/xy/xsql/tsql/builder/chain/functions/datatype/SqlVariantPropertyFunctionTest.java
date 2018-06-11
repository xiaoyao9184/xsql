package com.xy.xsql.tsql.builder.chain.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.datatype.Sql_Variant_Property;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_sql_variant_property;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SqlVariantPropertyFunctionTest {


    /**
     * SQL_VARIANT_PROPERTY(colA,'BaseType')
     */
    public Sql_Variant_Property exampleA = f_sql_variant_property(
            c("colA"),
            c_string("BaseType")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getProperty().getString(), "BaseType");
    }

    /**
     * SQL_VARIANT_PROPERTY(@v1, 'BaseType')
     */
    public Sql_Variant_Property exampleB = f_sql_variant_property(
            e_variable("v1"),
            c_string("BaseType")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), LocalVariable.class);
        assertEquals(exampleB.getProperty().getString(), "BaseType");
    }
}