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
        BlockMeta meta =  new BlockMetaBuilder<Void,Object>()
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
}