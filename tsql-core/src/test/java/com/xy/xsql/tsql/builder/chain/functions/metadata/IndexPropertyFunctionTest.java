package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.IndexProperty;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_indexproperty;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IndexPropertyFunctionTest {


    /**
     * INDEXPROPERTY(OBJECT_ID('HumanResources.Employee'),
     'PK_Employee_BusinessEntityID','IsClustered')
     */
    public IndexProperty example1 = f_indexproperty(
            f_object_id(
                    c_string("HumanResources.Employee")),
            c_string("PK_Employee_BusinessEntityID"),
            c_string("IsClustered")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getObjectId().getClass(), Object_Id.class);
        assertEquals(example1.getIndexOrStatisticsName().getClass(), StringConstant.class);
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}