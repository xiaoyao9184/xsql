package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.ColumnName;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class ColumnNameFactoryTest {

    @Test
    public void testQuick(){
        // @formatter:off
        ColumnName quick = c("column");
        ColumnName quick2 = c("s","table","column");
        ColumnName quick3 = c(t("s","table"),"column");
        // @formatter:on

        Assert.assertEquals(quick.toString(), "column");
        Assert.assertEquals(quick2.toString(), "s.table.column");
        Assert.assertEquals(quick3.toString(), "s.table.column");
    }

}
