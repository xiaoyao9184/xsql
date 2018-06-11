package com.xy.xsql.tsql.builder.chain.functions.trigger;

import com.xy.xsql.tsql.model.functions.trigger.Update;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.TriggerFunctions.f_update;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class UpdateFunctionTest {


    /**
     * UPDATE (StateProvinceID)
     */
    public Update example1 = f_update(
            c("StateProvinceID")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getColumn().getFullName(), "StateProvinceID");
    }

}