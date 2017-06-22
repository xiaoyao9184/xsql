package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ContainsPredicateConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = ContainsPredicateConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<CONTAINS> ::=\n" +
                        "CONTAINS ( { column_name | ( column_list ) | * | PROPERTY ( { column_name } , 'property_name' ) } , '<contains_search_condition>' , [ LANGUAGE language_term ] )");
    }
}