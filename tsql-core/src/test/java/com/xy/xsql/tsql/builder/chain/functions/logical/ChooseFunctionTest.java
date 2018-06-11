package com.xy.xsql.tsql.builder.chain.functions.logical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.datetime.Month;
import com.xy.xsql.tsql.model.functions.logical.Choose;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_month;
import static com.xy.xsql.tsql.builder.chain.functions.LogicalFunctions.f_choose;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ChooseFunctionTest {


    /**
     * CHOOSE ( 3, 'Manager', 'Director', 'Developer', 'Tester' )
     */
    public Choose example1 = f_choose(
            c_number(3),
            c_string("Manager"),
            c_string("Director"),
            c_string("Developer"),
            c_string("Tester")
    );

    /**
     * CHOOSE (ProductCategoryID, 'A','B','C','D','E')
     */
    public Choose example2 = f_choose(
            c("ProductCategoryID"),
            c_string("A"),
            c_string("B"),
            c_string("C"),
            c_string("D"),
            c_string("E")
    );

    /**
     * CHOOSE(MONTH(HireDate),'Winter','Winter', 'Spring','Spring','Spring','Summer','Summer',
     'Summer','Autumn','Autumn','Autumn','Winter')
     */
    public Choose example3 = f_choose(
            f_month(c("HireDate")),
            c_string("Winter"),
            c_string("Winter"),
            c_string("Spring"),
            c_string("Spring"),
            c_string("Spring"),
            c_string("Summer"),
            c_string("Summer"),
            c_string("Summer"),
            c_string("Autumn"),
            c_string("Autumn"),
            c_string("Autumn"),
            c_string("Winter")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getIndex().getClass(), NumberConstant.class);
        assertEquals(example1.getValList().size(), 4);
        assertEquals(example2.getIndex().getClass(), ColumnName.class);
        assertEquals(example2.getValList().size(), 5);
        assertEquals(example3.getIndex().getClass(), Month.class);
        assertEquals(example3.getValList().size(), 12);
    }

}