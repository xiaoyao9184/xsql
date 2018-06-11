package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.File_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_file_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FileIdFunctionTest {


    /**
     * FILE_ID('AdventureWorks2012_Data')
     */
    public File_Id example1 = f_file_id(
            c_string("AdventureWorks2012_Data")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFileName().getClass(), StringConstant.class);
    }

}