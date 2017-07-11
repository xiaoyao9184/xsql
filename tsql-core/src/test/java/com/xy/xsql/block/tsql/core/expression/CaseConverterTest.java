package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CaseConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = CaseConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        assert true;
    }

    @Test
    public void testSimpleCase() throws Exception {
        BlockMeta b = CaseConverter.SimpleCaseConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        assert true;
    }

    @Test
    public void testSearchedCase() throws Exception {
        BlockMeta b = CaseConverter.SearchedCaseConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        assert true;
    }

}