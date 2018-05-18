package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static com.xy.xsql.tsql.core.expression.Expressions.*;


/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class ConstantExpressionsTest {

    @Test
    public void testBinary(){
        // @formatter:off
        BinaryConstant uuidConstant = e_bin_uuid("6F9619FF-8B86-D011-B42D-00C04FC964FF");
        BinaryConstant uuidConstant2 = e_bin(new byte[]{1,2,3,4,5});
        BinaryConstant uuidConstant3 = e_bin(new byte[]{});
        // @formatter:on

        Assert.assertEquals(uuidConstant.toString().toLowerCase(),"0xff19966f868b11d0b42d00c04fc964ff");
        Assert.assertEquals(uuidConstant2.toString().toLowerCase(),"0x0102030405");
        Assert.assertEquals(uuidConstant3.toString().toLowerCase(),"0x");
    }

    @Test
    public void testString(){
        // @formatter:off
        StringConstant stringConstant = e_string("aa");
        StringConstant stringConstant2 = e_n_string("aa");
        UUID uuid = UUID.fromString("6F9619FF-8B86-D011-B42D-00C04FC964FF");
        StringConstant uuidConstant3 = e_string_uuid(uuid);
        // @formatter:on

        Assert.assertEquals(stringConstant.toString(),"'aa'");
        Assert.assertEquals(stringConstant2.toString(),"N'aa'");
        Assert.assertEquals(uuidConstant3.toString().toUpperCase(),"'6F9619FF-8B86-D011-B42D-00C04FC964FF'");
    }

    @Test
    public void testNumber(){
        // @formatter:off
        NumberConstant uuidConstant = e_number(1);
        NumberConstant uuidConstant2 = e_number(-1);
        NumberConstant uuidConstant3 = e_number(1.234567890);
        NumberConstant uuidConstant4 = e_number(new BigDecimal(1.234567890));
        NumberConstant uuidConstant5 = e_money(+999.8);
        // @formatter:on

        Assert.assertEquals(uuidConstant.toString(),"1");
        Assert.assertEquals(uuidConstant2.toString(),"-1");
        Assert.assertEquals(uuidConstant3.toString(),"1.23456789");
        Assert.assertNotEquals(uuidConstant4.toString().length(),11);
        Assert.assertEquals(uuidConstant5.toString(),"$999.8");
    }


}
