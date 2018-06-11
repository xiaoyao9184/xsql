package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.ParseName;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_parsename;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ParseNameFunctionTest {


    /**
     * PARSENAME('AdventureWorksPDW2012.dbo.DimCustomer', 1)
     */
    public ParseName example1 = f_parsename(
            c_string("AdventureWorksPDW2012.dbo.DimCustomer"),
            c_number(1)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getObjectName().getClass(), StringConstant.class);
        assertEquals(example1.getObjectPiece().getClass(), NumberConstant.class);
    }

}