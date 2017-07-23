package com.xy.xsql.util;

import com.xy.xsql.block.core.printer.BlockPrinter;
import com.xy.xsql.block.core.printer.KeywordBlockPrinter;
import com.xy.xsql.block.model.KeywordBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.util.GenericTypeUtil.getGenericArguments;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/7/23.
 */
public class GenericTypeUtilTest {

    @Test
    public void getGenericArguments() throws Exception {
        List<Type> typeList = GenericTypeUtil.getGenericArguments(KeywordBlockPrinter.class,BlockPrinter.class)
                .collect(Collectors.toList());

        Assert.assertTrue(typeList.get(0).equals(KeywordBlock.class));
        Assert.assertTrue(typeList.get(1).equals(StringWriter.class));
    }

}