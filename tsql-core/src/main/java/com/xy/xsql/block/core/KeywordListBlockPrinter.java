package com.xy.xsql.block.core;

import com.xy.xsql.block.model.KeywordListBlock;

import java.io.StringWriter;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/7/11.
 */
public class KeywordListBlockPrinter
        implements BlockPrinter<KeywordListBlock,StringWriter> {

    @Override
    public StringWriter print(KeywordListBlock block) {
        StringWriter writer = new StringWriter();
        writer.append(block.getKeywordList()
                .stream()
                .collect(Collectors.joining(" ")));
        return writer;
    }

}
