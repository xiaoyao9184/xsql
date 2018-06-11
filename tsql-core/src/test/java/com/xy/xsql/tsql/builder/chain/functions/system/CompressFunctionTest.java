package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.system.Compress;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_compress;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CompressFunctionTest {


    /**
     * COMPRESS(N'{"sport":"Tennis","age": 28,"rank":1,"points":15258, turn":17}')
     */
    public Compress exampleA = f_compress(
            c_n_string("{\"sport\":\"Tennis\",\"age\": 28,\"rank\":1,\"points\":15258, turn\":17}")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), StringConstant.class);
    }

    /**
     * COMPRESS(info)
     */
    public Compress exampleB = f_compress(
            c("info")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
    }

}