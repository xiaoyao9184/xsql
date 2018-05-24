package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.UniqueidentifierConstant;
import org.junit.Test;

import java.util.UUID;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_uniqueidentifier;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class UniqueidentifierConstantBuilderTest {

    public UniqueidentifierConstant example1 = c_uniqueidentifier("6F9619FF-8B86-D011-B42D-00C04FC964FF");
    public UniqueidentifierConstant example2 = c_uniqueidentifier(UUID.fromString("6F9619FF-8B86-D011-B42D-00C04FC964FF"));

    /**
     *
     */
    @Test
    public void testExample(){
        assertEquals(
                example1.getUuid().toString().toUpperCase(),
                "6F9619FF-8B86-D011-B42D-00C04FC964FF");
        assertEquals(
                example2.getUuid().toString().toUpperCase(),
                "6F9619FF-8B86-D011-B42D-00C04FC964FF");
        assertEquals(
                example2.isUseBinaryFormat(),
                true);
    }

}