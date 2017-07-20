package com.xy.xsql.block.core.meta;

import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/7/19.
 */
public class BlockMetaBuilderTest {

    @Test
    public void testReadOnly(){
        BlockMeta meta = new BlockMetaBuilder<Void,Object>()
                .overall("test")
                .sub("sub")
                    .and()
                .build();

        Assert.assertEquals(meta.getName(),"test");
        try{
            meta.setName("cant");
        }catch (Exception e){
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }
    }



    @Test
    public void testFormatPrototype(){
        BlockMeta meta = new BlockMetaBuilder<Void,Object>()
                .overall("test")
                .syntax_sub_line()
                .syntax_sub_indentation_right()
                .sub("sub")
                    .and()
                .build();

        Assert.assertTrue(meta.getSubSyntaxFormat().isNewLine());

        Assert.assertEquals(meta.getSub().size(),1);
        BlockMeta meta1 = meta.getSub().get(0);
        Assert.assertTrue(meta1.getSyntaxFormat().isNewLine());

    }
}