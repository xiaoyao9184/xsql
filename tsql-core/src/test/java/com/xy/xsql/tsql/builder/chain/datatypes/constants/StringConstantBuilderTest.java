package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class StringConstantBuilderTest {

    public StringConstant example1 = c_string("Cincinnati");
    public StringConstant example2 = c_string("O''Brien");
    public StringConstant example3 = c_string("Process X is 50% complete.");
    public StringConstant example4 = c_string("The level for job_id: %d should be between %d and %d.");
    public StringConstant example5 = c_string("O'Brien");


    /**
     *
     */
    @Test
    public void testExample(){
        assertEquals(
                example1.getString(),
                "Cincinnati");
        assertEquals(
                example2.getString(),
                "O''Brien");
        assertEquals(
                example3.getString(),
                "Process X is 50% complete.");
        assertEquals(
                example4.getString(),
                "The level for job_id: %d should be between %d and %d.");
        assertEquals(
                example5.getString(),
                "O'Brien");
    }

}