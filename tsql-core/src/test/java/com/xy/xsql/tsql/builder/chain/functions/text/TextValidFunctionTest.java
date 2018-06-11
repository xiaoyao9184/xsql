package com.xy.xsql.tsql.builder.chain.functions.text;

import com.xy.xsql.tsql.model.functions.text.TextPtr;
import com.xy.xsql.tsql.model.functions.text.TextValid;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.TextFunctions.f_textptr;
import static com.xy.xsql.tsql.builder.chain.functions.TextFunctions.f_textvalid;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TextValidFunctionTest {


    /**
     * TEXTVALID ('pub_info.logo', TEXTPTR(logo))
     */
    public TextValid example1 = f_textvalid(
            c_string("pub_info.logo"),
            f_textptr(c("logo"))
    );

    @Test
    public void testExample(){
        assertEquals(example1.getColumn().getString(), "pub_info.logo");
        assertEquals(example1.getTextPtr().getClass(), TextPtr.class);
    }

}