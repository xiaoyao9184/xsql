package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.metadata.FileGroup_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_filegroup_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FileGroupNameFunctionTest {


    /**
     * FILEGROUP_NAME(1)
     */
    public FileGroup_Name example1 = f_filegroup_name(
            c_number(1)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFilegroupId().getClass(), NumberConstant.class);
    }

}