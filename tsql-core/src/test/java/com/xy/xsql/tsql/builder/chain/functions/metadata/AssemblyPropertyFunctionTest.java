package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.AssemblyProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_assemblyproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AssemblyPropertyFunctionTest {


    /**
     * ASSEMBLYPROPERTY ('HelloWorld' , 'PublicKey')
     */
    public AssemblyProperty example1 = f_assemblyproperty(
            c_string("HelloWorld"),
            c_string("PublicKey")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getAssemblyName().getClass(), StringConstant.class);
        assertEquals(example1.getPropertyName().getClass(), StringConstant.class);
    }

}