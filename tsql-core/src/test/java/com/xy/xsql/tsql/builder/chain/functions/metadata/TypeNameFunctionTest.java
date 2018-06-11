package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.metadata.Type_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_type_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TypeNameFunctionTest {


    /**
     * TYPE_NAME(c.user_type_id)
     */
    public Type_Name example1 = f_type_name(
            c("c","user_type_id")
    );

    /**
     * TYPE_NAME(36)
     */
    public  Type_Name example2 = f_type_name(
            c_number(36)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getTypeId().getClass(), ColumnName.class);
        assertEquals(example2.getTypeId().getClass(), NumberConstant.class);
    }

}