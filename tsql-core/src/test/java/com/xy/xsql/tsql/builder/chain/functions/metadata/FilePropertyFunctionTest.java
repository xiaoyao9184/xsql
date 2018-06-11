package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.FileProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_fileproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FilePropertyFunctionTest {


    /**
     * FILEPROPERTY('AdventureWorks2012_Data', 'IsPrimaryFile')
     */
    public FileProperty example1 = f_fileproperty(
            c_string("AdventureWorks2012_Data"),
            c_string("IsPrimaryFile")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFileName().getClass(), StringConstant.class);
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}