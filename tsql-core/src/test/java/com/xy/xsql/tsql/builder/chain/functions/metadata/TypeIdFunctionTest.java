package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.Type_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._datetime;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_type_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TypeIdFunctionTest {


    /**
     * TYPE_ID('NewType')
     */
    public Type_Id exampleA = f_type_id(
            c_string("NewType")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTypeName().getClass(), StringConstant.class);
    }

    /**
     * TYPE_ID('datetime')
     */
    public Type_Id exampleB = f_type_id(
            _datetime()
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTypeName().getClass(), StringConstant.class);
    }

}