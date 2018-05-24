package com.xy.xsql.tsql.builder.chain.datatypes;

import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.datatypes.constants.MoneyConstant;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class ConstantsTest {

    @Test
    public void testBinary(){
        // @formatter:off
        BinaryConstant uuidConstant = c_bin(UUID.fromString("6F9619FF-8B86-D011-B42D-00C04FC964FF"));
        BinaryConstant uuidConstant2 = c_bin(new byte[]{1,2,3,4,5});
        BinaryConstant uuidConstant3 = c_bin(new byte[]{});
        // @formatter:on

        assertEquals(uuidConstant.getData().length,16);
        assertEquals(uuidConstant2.getData().length,5);
        assertEquals(uuidConstant3.getData().length,0);
    }

    @Test
    public void testString(){
        // @formatter:off
        StringConstant stringConstant = c_string("aa");
        StringConstant stringConstant2 = c_n_string("aa");
        StringConstant uuidConstant3 = c_string("6F9619FF-8B86-D011-B42D-00C04FC964FF");
        // @formatter:on

        assertEquals(stringConstant.getString(),"aa");
        assertEquals(stringConstant2.getString(),"aa");
        assertEquals(stringConstant2.isUseNQuote(),true);
        assertEquals(uuidConstant3.getString(),"6F9619FF-8B86-D011-B42D-00C04FC964FF");
    }

    @Test
    public void testNumber(){
        // @formatter:off
        NumberConstant uuidConstant = c_number(1);
        NumberConstant uuidConstant2 = c_number(-1);
        NumberConstant uuidConstant3 = c_number(1.234567890);
        NumberConstant uuidConstant4 = c_number(new BigDecimal(1.234567890));
        // @formatter:on

        assertEquals(uuidConstant.getNumber().toString(),"1");
        assertEquals(uuidConstant2.getNumber().toString(),"-1");
        assertEquals(uuidConstant3.getNumber().toString(),"1.23456789");
        assertNotEquals(uuidConstant4.getNumber().toString(),"1.23456789");
    }

    @Test
    public void testMoney(){
        // @formatter:off
        MoneyConstant moneyConstant = c_money(999.8);
        // @formatter:on

        assertEquals(moneyConstant.getNumber(),999.8);
        assertEquals(moneyConstant.getSymbol(), Currency.getInstance(Locale.getDefault()).getSymbol());
    }


}
