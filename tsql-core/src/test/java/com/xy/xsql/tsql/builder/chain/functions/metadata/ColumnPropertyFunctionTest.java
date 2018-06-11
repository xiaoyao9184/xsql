package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.ColumnProperty;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_columnproperty;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ColumnPropertyFunctionTest {


    /**
     * COLUMNPROPERTY( OBJECT_ID('Person.Person'),'LastName','PRECISION')
     */
    public ColumnProperty example1 = f_columnproperty(
            f_object_id(c_string("Person.Person")),
            c_string("LastName"),
            c_string("PRECISION")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getId().getClass(), Object_Id.class);
        assertEquals(example1.getColumn().getClass(), StringConstant.class);
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}