package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKeyProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_negative_number;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_asymkeyproperty;
import static com.xy.xsql.tsql.model.functions.cryptographic.AsymKeyProperty.Properties.algorithm_desc;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AsymKeyPropertyFunctionTest {


    /**
     * ASYMKEYPROPERTY(256, 'algorithm_desc')
     */
    public AsymKeyProperty example1 = f_asymkeyproperty(
            c_negative_number(256),
            algorithm_desc);

    @Test
    public void testExample1(){
        assertEquals(example1.getKeyId().getClass(), NumberConstant.class);
        assertEquals(example1.getProperties(), AsymKeyProperty.Properties.algorithm_desc);
    }
}