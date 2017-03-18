package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.TableName;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class TableNameBuilderTest {

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
