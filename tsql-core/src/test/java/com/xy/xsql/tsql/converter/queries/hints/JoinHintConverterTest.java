package com.xy.xsql.tsql.converter.queries.hints;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.xy.xsql.tsql.builder.chain.queries.hints.JoinHints.*;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class JoinHintConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = JoinHintConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<join_hint> ::=\n" +
                        "{ LOOP | HASH | MERGE | REMOTE }");
    }

    private Map<JoinHint,String> model2StringMap;

    @Before
    public void init(){
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                $Hash(),
                "HASH");

        model2StringMap.put(
                $Loop(),
                "LOOP");

        model2StringMap.put(
                $Merge(),
                "MERGE");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ModelMetaBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }
}