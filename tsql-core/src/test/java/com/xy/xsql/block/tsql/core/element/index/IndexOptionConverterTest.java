package com.xy.xsql.block.tsql.core.element.index;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class IndexOptionConverterTest {


    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IndexOptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<index_option> ::=\n" +
                        "{\n" +
                        "\tPAD_INDEX = ON | OFF\n" +
                        "\t| FILLFACTOR = fillfactor\n" +
                        "\t| IGNORE_DUP_KEY = ON | OFF\n" +
                        "\t| STATISTICS_NORECOMPUTE = ON | OFF\n" +
                        "\t| ALLOW_ROW_LOCKS = ON | OFF\n" +
                        "\t| ALLOW_PAGE_LOCKS = ON | OFF\n" +
                        "\t| COMPRESSION_DELAY = 0 | delay [Minutes]\n" +
                        "\t| DATA_COMPRESSION = NONE | ROW | PAGE | COLUMNSTORE | COLUMNSTORE_ARCHIVE\n" +
                        "\t\t[ ON PARTITIONS ( { <partition_number_expression> | <range> } [,...n] ) ]\n" +
                        "}");
    }

}