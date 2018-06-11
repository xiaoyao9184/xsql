package com.xy.xsql.tsql.builder.chain.functions.datatype;

import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Current;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Seed;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_addition;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_ident_current;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_ident_seed;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IdentSeedFunctionTest {


    /**
     * IDENT_SEED('Person.Address')
     */
    public Ident_Seed exampleA = f_ident_seed(c_string("Person.Address"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTableOrView().getString(), "Person.Address");
    }

    /**
     * IDENT_SEED(TABLE_SCHEMA + '.' + TABLE_NAME)
     */
    public Ident_Current exampleB = f_ident_current(
            e_addition(
                    e_addition(
                            c("TABLE_SCHEMA"),
                            c_string(".")
                    ),
                    c("TABLE_NAME")
            ));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTableName().getClass(), BinaryExpression.class);
    }
}