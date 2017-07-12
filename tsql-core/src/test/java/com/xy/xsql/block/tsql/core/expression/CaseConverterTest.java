package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CaseConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CaseConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CASE> ::=\n" +
                        "CASE input_expression WHEN when_expression THEN result_expression [...n] [ ELSE else_result_expression ] END\n" +
                        "| CASE WHEN when_expression THEN result_expression [...n] [ ELSE else_result_expression ] END");
    }

    @Test
    public void testMetaPrint_SimpleCase() throws Exception {
        BlockMeta b = CaseConverter.SimpleCaseConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Simple CASE expression> ::=\n" +
                        "CASE input_expression WHEN when_expression THEN result_expression [...n] [ ELSE else_result_expression ] END");
    }

    @Test
    public void testMetaPrint_SearchedCase() throws Exception {
        BlockMeta b = CaseConverter.SearchedCaseConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Searched CASE expression> ::=\n" +
                        "CASE WHEN when_expression THEN result_expression [...n] [ ELSE else_result_expression ] END");
    }

}