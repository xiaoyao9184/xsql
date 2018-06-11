package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.FileGroup_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_filegroup_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FileGroupIdFunctionTest {


    /**
     * FILEGROUP_ID('PRIMARY')
     */
    public FileGroup_Id example1 = f_filegroup_id(
            c_string("PRIMARY")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFilegroupName().getClass(), StringConstant.class);
    }

}