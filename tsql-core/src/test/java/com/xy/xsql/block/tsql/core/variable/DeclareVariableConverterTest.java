package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.variable.DeclareVariableBuilderTest;
import com.xy.xsql.tsql.model.variable.DeclareVariable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public class DeclareVariableConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = DeclareVariableConverter.meta();

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        assert true;
    }


    private Map<DeclareVariable,String> model2StringMap;

    @Before
    public void init(){
        DeclareVariableBuilderTest builderTest = new DeclareVariableBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA1,
                "DECLARE @find varchar(30)");

        model2StringMap.put(
                builderTest.exampleA2,
                " DECLARE @find varchar(30) = 'Man%'");

        model2StringMap.put(
                builderTest.exampleB,
                "DECLARE @Group nvarchar(50), @Sales money");

//        model2StringMap.put(
//                builderTest.exampleC,
//                "DECLARE @MyTableVar table(\n" +
//                        "     EmpID int NOT NULL,\n" +
//                        "     OldVacationHours int,\n" +
//                        "     NewVacationHours int,\n" +
//                        "     ModifiedDate datetime)");

        model2StringMap.put(
                builderTest.exampleD,
                "DECLARE @LocationTVP\n" +
                        "     AS LocationTableType");

        model2StringMap.put(
                builderTest.exampleE1,
                "DECLARE @find varchar(30)");

        model2StringMap.put(
                builderTest.exampleE2,
                "DECLARE @find varchar(30) = 'Man%'");

        model2StringMap.put(
                builderTest.exampleF,
                "DECLARE @lastName varchar(30), @firstName varchar(30)");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

}
