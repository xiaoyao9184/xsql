package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Test;

import java.io.StringWriter;

import static com.xy.xsql.tsql.core.datatype.DataTypes._varchar;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class DeclareVariableConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = DeclareVariableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        assert true;
    }

}
