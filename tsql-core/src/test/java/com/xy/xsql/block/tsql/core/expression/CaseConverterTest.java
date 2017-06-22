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

        System.out.print(writer);
        assert true;
    }

}