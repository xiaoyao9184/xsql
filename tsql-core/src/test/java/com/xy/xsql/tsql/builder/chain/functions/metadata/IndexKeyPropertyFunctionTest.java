package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.IndexKey_Property;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_indexkey_property;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IndexKeyPropertyFunctionTest {


    /**
     * INDEXKEY_PROPERTY(OBJECT_ID('Production.Location', 'U'),
     1,1,'ColumnId')
     */
    public IndexKey_Property example1 = f_indexkey_property(
            f_object_id(
                    c_string("Production.Location"),
                    c_string("U")),
            c_number(1),
            c_number(1),
            c_string("ColumnId")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getObjectId().getClass(), Object_Id.class);
        assertEquals(example1.getIndexId().getClass(), NumberConstant.class);
        assertEquals(example1.getKeyId().getClass(), NumberConstant.class);
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}