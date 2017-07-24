package com.xy.xsql.block.meta;

import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/7/24.
 */
public class MetaReadOnlyProxyTest {

    @Test
    public void testEnableDisable(){
        BlockMeta blockMeta = new BlockMeta();
        blockMeta.setName("test");

        MetaReadOnlyProxy proxy = new MetaReadOnlyProxy(blockMeta);
        blockMeta = proxy.meta();

        blockMeta.setName("test1");
        Assert.assertNotNull(blockMeta.getName(),"test1");

        proxy.enable();
        try{
            blockMeta.setName("test2");
        }catch (Exception e){
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }

        proxy.disable();
        blockMeta.setName("test2");
        Assert.assertNotNull(blockMeta.getName(),"test2");
    }

}