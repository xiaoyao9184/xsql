package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.datetime.SwitchOffset;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_switchoffset;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SwitchOffsetFunctionTest {

    /**
     * SWITCHOFFSET (ColDatetimeoffset, '-08:00')
     */
    public SwitchOffset example1 = f_switchoffset(
            c("ColDatetimeoffset"),
            c_string("-08:00")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getDatetimeOffset().getClass(), ColumnName.class);
        assertEquals(example1.getTimeZone().getClass(), StringConstant.class);
    }


}