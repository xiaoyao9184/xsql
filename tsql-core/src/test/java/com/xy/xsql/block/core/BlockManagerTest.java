package com.xy.xsql.block.core;

import com.xy.xsql.block.core.printer.KeywordBlockPrinter;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.block.model.KeywordBlock;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.predicate.Predicates.p_like;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class BlockManagerTest {

    @Test
    public void testRegisterPrinter(){
        // @formatter:off
        BlockManager
                .byPrinter()
                .register(new KeywordBlockPrinter());

        KeywordBlock keywordBlock = new KeywordBlock("test_block");

        Assert.assertEquals(
                BlockManager
                        .byPrinter()
                        .print(keywordBlock),
                "test_block");
        // @formatter:on
        assert true;
    }

    @Test
    public void testInitMetaManager(){
        BlockManager.init(MetaManager.INSTANCE);

        String c = BlockManager
                .INSTANCE
                .print(c("t","c"));

        Assert.assertEquals(
                c,
                "t . c");
        // @formatter:on
        assert true;
    }

}
