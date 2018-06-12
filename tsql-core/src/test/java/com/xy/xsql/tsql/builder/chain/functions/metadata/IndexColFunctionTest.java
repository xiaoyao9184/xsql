package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.functions.metadata.Index_Col;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_index_col;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IndexColFunctionTest {


    /**
     * INDEX_COL (N'AdventureWorks2012.Sales.SalesOrderDetail', 1,1)
     */
    public Index_Col example1 = f_index_col(
            c_n_string("AdventureWorks2012.Sales.SalesOrderDetail"),
            c_number(1),
            c_number(1)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getTableName().getClass(), StringConstant.class);
        assertEquals(example1.getIndexId().getClass(), NumberConstant.class);
        assertEquals(example1.getKeyId().getClass(), NumberConstant.class);
    }

}