package com.xy.xsql.tsql.builder.chain.functions.conversion;

import com.xy.xsql.tsql.model.datatypes.Xml;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.datetime.DateTime;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.string.Char;
import com.xy.xsql.tsql.model.datatypes.string.NVarChar;
import com.xy.xsql.tsql.model.datatypes.string.VarChar;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.conversion.Cast;
import com.xy.xsql.tsql.model.functions.datetime.GetDate;
import com.xy.xsql.tsql.model.functions.mathematical.Round;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes.*;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_cast;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_getdate;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_round;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CastFunctionTest {


    /**
     * CAST(ListPrice AS int)
     */
    public Cast exampleA = f_cast(c("ListPrice"),_int());

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getDataType().getClass(), Int.class);
    }

    /**
     * CAST(ROUND(SalesYTD/CommissionPCT, 0) AS int)
     */
    public Cast exampleB = f_cast(
            f_round(
                    e_division(c("SalesYTD"),c("CommissionPCT")),
                    e_number(0))
            ,_int());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), Round.class);
        assertEquals(exampleB.getDataType().getClass(), Int.class);
    }

    /**
     * CAST(ListPrice AS varchar(12))
     */
    public Cast exampleC = f_cast(
            c("ListPrice")
            ,_varchar(12));

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleC.getDataType().getClass(), VarChar.class);
    }

    /**
     * CAST(EnglishProductName AS char(10))
     */
    public Cast exampleD = f_cast(
            c("EnglishProductName")
            ,_char(10));

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getExpression().getClass(), ColumnName.class);
        assertEquals(exampleD.getDataType().getClass(), Char.class);
    }

    /**
     * CAST(CAST(s.SalesYTD AS int) AS char(20))
     */
    public Cast exampleE = f_cast(
            f_cast(
                    c("s","SalesYTD"),
                    _int()),
            _char(20));

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getExpression().getClass(), Cast.class);
        assertEquals(exampleE.getDataType().getClass(), Char.class);
    }

    /**
     * CAST('<Name><FName>Carol</FName><LName>Elliot</LName></Name>'  AS XML)
     */
    public Cast exampleF = f_cast(
            c_string("<Name><FName>Carol</FName><LName>Elliot</LName></Name>"),
            _xml());

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getExpression().getClass(), StringConstant.class);
        assertEquals(exampleF.getDataType().getClass(), Xml.class);
    }

    /**
     * CAST(GETDATE() AS nvarchar(30))
     */
    public Cast exampleG1 = f_cast(
            f_getdate(),
            _nvarchar(30));

    /**
     * CAST('2006-04-25T15:50:59.997' AS datetime)
     */
    public Cast exampleG2 = f_cast(
            c_string("2006-04-25T15:50:59.997"),
            _datetime());

    @Test
    public void testExampleG(){
        assertEquals(exampleG1.getExpression().getClass(), GetDate.class);
        assertEquals(exampleG1.getDataType().getClass(), NVarChar.class);
        assertEquals(exampleG2.getExpression().getClass(), StringConstant.class);
        assertEquals(exampleG2.getDataType().getClass(), DateTime.class);
    }

    /**
     * CAST (@d1 AS datetime)
     */
    public Cast exampleH = f_cast(
            e_variable("d1"),
            _datetime());

    @Test
    public void testExampleH(){
        assertEquals(exampleH.getExpression().getClass(), LocalVariable.class);
        assertEquals(exampleH.getDataType().getClass(), DateTime.class);
    }


    //not need
    //Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
}