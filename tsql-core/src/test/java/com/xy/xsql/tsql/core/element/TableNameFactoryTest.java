package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameFactory.t;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class TableNameFactoryTest {

    @Test
    public void testQuick(){
        // @formatter:off
        TableName quick = t("table");
        TableName quick2 = t("s","table");
        // @formatter:on

        Assert.assertEquals(quick.toString(), "table");
        Assert.assertEquals(quick2.toString(), "s.table");
    }

}
