package com.xy.xsql.block.core.printer;

import com.xy.xsql.block.model.KeywordBlock;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/7/11.
 */
public class KeywordBlockPrinter
        implements BlockPrinter<KeywordBlock,StringWriter> {

    @Override
    public StringWriter print(KeywordBlock block) {
        return new StringWriter().append(block.toString());
    }
}
