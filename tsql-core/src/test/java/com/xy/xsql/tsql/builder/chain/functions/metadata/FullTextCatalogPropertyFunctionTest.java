package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.FullTextCatalogProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_fulltextcatalogproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FullTextCatalogPropertyFunctionTest {


    /**
     * fulltextcatalogproperty('Cat_Desc', 'ItemCount')
     */
    public FullTextCatalogProperty example1 = f_fulltextcatalogproperty(
            c_string("Cat_Desc"),
            c_string("ItemCount")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCatalogName().getClass(), StringConstant.class);
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}