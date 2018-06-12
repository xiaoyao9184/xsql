package com.xy.xsql.tsql.converter.functions.cursor;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cursor.CursorStatusFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cursor.Cursor_Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CursorStatusConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CursorStatusConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CURSOR_STATUS> ::=\n" +
                        "CURSOR_STATUS ( 'global' , 'cursor_name' | 'local' , 'cursor_name' | 'variable' , 'cursor_variable' )");
    }

    private Map<Cursor_Status,String> model2StringMap;

    @Before
    public void init(){
        CursorStatusFunctionTest builderTest = new CursorStatusFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "CURSOR_STATUS('global','cur')");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrint(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}