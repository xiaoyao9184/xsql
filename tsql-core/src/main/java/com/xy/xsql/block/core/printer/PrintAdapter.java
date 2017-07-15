package com.xy.xsql.block.core.printer;

import com.xy.xsql.block.model.Block;

import java.io.Writer;

/**
 * Created by xiaoyao9184 on 2017/7/11.
 */
public class PrintAdapter<B extends Block,W extends Writer> {

    private B block;
    private BlockPrinter<B,W> printer;

    public PrintAdapter withBlock(B block) {
        this.block = block;
        return this;
    }

    public PrintAdapter withPrinter(BlockPrinter<B,W> printer) {
        this.printer = printer;
        return this;
    }

    public void initPrinter(){
        //TODO
        withPrinter((BlockPrinter<B, W>) new KeywordListBlockPrinter());
    }


    public String print(){
        if(printer == null){
            initPrinter();
        }
        W w = printer.print(block);
        return w.toString();
    }
}
