package com.xy.xsql.block.meta;

import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.statement.dml.SelectConverter;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;

/**
 * Created by xiaoyao9184 on 2017/7/23.
 */
public class MetaManagerTest {

    @Test
    public void testGetMeta(){
        BlockMeta meta = MetaManager
                .byModel(Select.class)
                .meta()
                .get();
        BlockMeta meta2 = MetaManager
                .byConverter(SelectConverter.class)
                .meta()
                .get();

        Assert.assertEquals(meta.getName(), SelectConverter.meta.getName());
        Assert.assertEquals(meta2.getName(), SelectConverter.meta.getName());
    }

    @Test
    public void testPrintSyntax(){
        String syntax = MetaManager
                .byModel(Select.class)
                .print();
        String syntax2 = MetaManager
                .byConverter(SelectConverter.class)
                .print();

        Assert.assertEquals(syntax,syntax2);
    }

    @Test
    public void testGetType(){
        Type typeModel = MetaManager
                .byModel(Select.class)
                .model();
        Type typeModel2 = MetaManager
                .byConverter(SelectConverter.class)
                .model();

        Assert.assertEquals(typeModel.getTypeName(), typeModel2.getTypeName());

        Type typeConverter = MetaManager
                .byModel(Select.class)
                .converter();
        Type typeConverter2 = MetaManager
                .byConverter(SelectConverter.class)
                .converter();

        Assert.assertEquals(typeConverter.getTypeName(), typeConverter2.getTypeName());
    }

}