package com.xy.xsql.tsql.builder.chain.functions.conversion;

import com.xy.xsql.tsql.model.datatypes.Xml;
import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.datetime.DateTime;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.string.Binary;
import com.xy.xsql.tsql.model.datatypes.string.Char;
import com.xy.xsql.tsql.model.datatypes.string.NVarChar;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.conversion.Convert;
import com.xy.xsql.tsql.model.functions.datetime.GetDate;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes.*;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_bin;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_convert;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_getdate;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ConvertFunctionTest {


    /**
     * CONVERT(int, ListPrice)
     */
    public Convert exampleA = f_convert(_int(),c("ListPrice"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getDataType().getClass(), Int.class);
    }

    /**
     * CONVERT(XML, '<root><child/></root>')
     */
    public Convert exampleF1 = f_convert(
            _xml(),
            c_string("<root><child/></root>"));

    /**
     * CONVERT(XML, '<root>          <child/>         </root>', 1)
     */
    public Convert exampleF2 = f_convert(
            _xml(),
            c_string("<root>          <child/>         </root>"),
            e_number(1));

    @Test
    public void testExampleF(){
        assertEquals(exampleF1.getExpression().getClass(), StringConstant.class);
        assertEquals(exampleF1.getDataType().getClass(), Xml.class);
        assertEquals(exampleF2.getExpression().getClass(), StringConstant.class);
        assertEquals(exampleF2.getDataType().getClass(), Xml.class);
        assertEquals(exampleF2.getStyle().getClass(), NumberConstant.class);
    }

    /**
     * CONVERT(nvarchar(30), GETDATE(), 126)
     */
    public Convert exampleG1 = f_convert(
            _nvarchar(30),
            f_getdate(),
            e_number(126));

    /**
     * CONVERT(datetime, '2006-04-25T15:50:59.997', 126)
     */
    public Convert exampleG2 = f_convert(
            _datetime(),
            c_string("2006-04-25T15:50:59.997"),
            e_number(126));

    @Test
    public void testExampleG(){
        assertEquals(exampleG1.getExpression().getClass(), GetDate.class);
        assertEquals(exampleG1.getDataType().getClass(), NVarChar.class);
        assertEquals(exampleG1.getStyle().getClass(), NumberConstant.class);
        assertEquals(exampleG2.getExpression().getClass(), StringConstant.class);
        assertEquals(exampleG2.getDataType().getClass(), DateTime.class);
        assertEquals(exampleG2.getStyle().getClass(), NumberConstant.class);
    }

    /**
     * CONVERT(char(8), 0x4E616d65, 0)
     */
    public Convert exampleH1 = f_convert(
            _char(8),
            e_bin(new byte[]{0x4E, 0x61, 0x6d, 0x65 }),
            e_number(0));

    /**
     * CONVERT(binary(8), 'Name', 0)
     */
    public Convert exampleH2 = f_convert(
            _binary(8),
            c_string("Name"),
            e_number(0));

    /**
     * CONVERT(binary(4), '4E616D65', 2)
     */
    public Convert exampleH3 = f_convert(
            _binary(4),
            c_string("4E616D65"),
            e_number(2));

    @Test
    public void testExampleH(){
        assertEquals(exampleH1.getExpression().getClass(), BinaryConstant.class);
        assertEquals(exampleH1.getDataType().getClass(), Char.class);
        assertEquals(exampleH1.getStyle().getClass(), NumberConstant.class);
        assertEquals(exampleH2.getExpression().getClass(), StringConstant.class);
        assertEquals(exampleH2.getDataType().getClass(), Binary.class);
        assertEquals(exampleH2.getStyle().getClass(), NumberConstant.class);
        assertEquals(exampleH3.getExpression().getClass(), StringConstant.class);
        assertEquals(exampleH3.getDataType().getClass(), Binary.class);
        assertEquals(exampleH3.getStyle().getClass(), NumberConstant.class);
    }

    //not need
    //Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
}