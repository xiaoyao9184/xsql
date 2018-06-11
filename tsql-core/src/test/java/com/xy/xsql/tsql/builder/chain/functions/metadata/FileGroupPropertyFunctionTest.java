package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.FileGroupProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_filegroupproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FileGroupPropertyFunctionTest {


    /**
     * FILEGROUPPROPERTY('PRIMARY', 'IsDefault')
     */
    public FileGroupProperty example1 = f_filegroupproperty(
            c_string("PRIMARY"),
            c_string("IsDefault")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFilegroupName().getClass(), StringConstant.class);
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}