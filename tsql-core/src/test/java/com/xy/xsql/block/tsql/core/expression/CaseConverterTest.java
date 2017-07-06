package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CaseConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = CaseConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        assert true;
    }

    @Test
    public void testSimpleCase() throws Exception {
        ReferenceBlock b = CaseConverter.SimpleCaseConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        assert true;
    }

    @Test
    public void testSearchedCase() throws Exception {
        ReferenceBlock b = CaseConverter.SearchedCaseConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        assert true;
    }

}