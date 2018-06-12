package com.xy.xsql.tsql.converter.functions.trigger;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.trigger.TriggerNestLevelFunctionTest;
import com.xy.xsql.tsql.builder.chain.functions.trigger.UpdateFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.trigger.Trigger_NestLevel;
import com.xy.xsql.tsql.model.functions.trigger.Update;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TriggerNestLevelConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TriggerNestLevelConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TRIGGER_NESTLEVEL> ::=\n" +
                        "TRIGGER_NESTLEVEL ( [ [ object_id ] , [ 'trigger_type' ] , [ 'trigger_event_category' ] ] )");
    }

    private Map<Trigger_NestLevel,String> model2StringMap;

    @Before
    public void init(){
        TriggerNestLevelFunctionTest builderTest = new TriggerNestLevelFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TRIGGER_NESTLEVEL( OBJECT_ID('xyz') , 'AFTER' , 'DML' )");
        model2StringMap.put(
                builderTest.exampleB,
                "TRIGGER_NESTLEVEL ( ( SELECT object_id FROM sys.triggers\n" +
                        "     WHERE name = 'abc' ), 'AFTER' , 'DDL' )");
        model2StringMap.put(
                builderTest.exampleC,
                "TRIGGER_NESTLEVEL ( )");
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