package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.FullTextServiceProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_fulltextserviceproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FullTextServicePropertyFunctionTest {


    /**
     * fulltextserviceproperty('VerifySignature')
     */
    public FullTextServiceProperty example1 = f_fulltextserviceproperty(
            c_string("VerifySignature")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}