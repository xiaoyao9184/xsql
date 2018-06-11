package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKeyProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.SymKeyProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_symkeyproperty;
import static com.xy.xsql.tsql.model.functions.cryptographic.AsymKeyProperty.Properties.algorithm_desc;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SymkeyPropertyFunctionTest {


    /**
     * SYMKEYPROPERTY(256, 'algorithm_desc')
     */
    public SymKeyProperty example1 = f_symkeyproperty(
            e_number(256),
            algorithm_desc
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getKeyId().getClass(), NumberConstant.class);
        assertEquals(example1.getProperties(), AsymKeyProperty.Properties.algorithm_desc);
    }

}