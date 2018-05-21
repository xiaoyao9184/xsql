package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class DropClusteredConstraintOptionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DropClusteredConstraintOptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<drop_clustered_constraint_option> ::=\n" +
                        "{\n" +
                        "\tMAXDOP = max_degree_of_parallelism\n" +
                        "\t| ONLINE = { ON | OFF }\n" +
                        "\t| MOVE TO =\n" +
                        "\t\t{ partition_scheme_name ( column_name )\n" +
                        "\t\t| filegroup_name\n" +
                        "\t\t| default }\n" +
                        "}");
    }

}