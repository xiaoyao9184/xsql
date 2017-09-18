package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/18
 */
public class SetConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SetConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "SET\n" +
                        "(\n" +
                        "\tFILESTREAM_ON =\n" +
                        "\t\t{ partition_scheme_name | filegroup_name | \"default\" | \"NULL\" }\n" +
                        "\t| SYSTEM_VERSIONING = {\n" +
                        "\t\tON | OFF\n" +
                        "\t\t[ (\n" +
                        "\t\t\tHISTORY_TABLE = schema_name . history_table_name\n" +
                        "\t\t\t[ , DATA_CONSISTENCY_CHECK = { ON | OFF } ]\n" +
                        "\t\t\t[ , HISTORY_RETENTION_PERIOD = {\n" +
                        "\t\t\t\tINFINITE | number { DAY | DAYS | WEEK | WEEKS | MONTH | MONTHS | YEAR | YEARS }\n" +
                        "\t\t\t} ]\n" +
                        "\t\t) ]\n" +
                        "\t}\n" +
                        ")");
    }

}