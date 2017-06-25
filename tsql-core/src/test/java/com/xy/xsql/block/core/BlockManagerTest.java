package com.xy.xsql.block.core;

import org.junit.Test;

import java.util.Comparator;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class BlockManagerTest {

    @Test
    public void printAll(){
        // @formatter:off
        BlockManager.INSTANCE.getTypeBlockConverterMap()
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(kv -> kv.getKey().getTypeName()))
                .forEach(type -> {
                    System.out.print(type.getKey().getTypeName());
                    System.out.print(" -> ");
                    System.out.print(type.getValue().getClass().getName());
                    System.out.println("");
                });
        // @formatter:on
        assert true;
    }


}
