package com.xy.xsql.tsql.builder.chain.datatypes.table;

import com.xy.xsql.tsql.builder.chain.datatypes.table.table.TableTypeDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.datetime.DateTime;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableTypeDefinition;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitions.c_datetime;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitions.c_int;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._int;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class TableTypeDefinitionBuilderTest {

    @Test
    public void testQuick() {
        // @formatter:off
        TableTypeDefinition table = new TableTypeDefinitionBuilder<Void>()
                .withColumnDefinition()
                    .withColumnName(c("EmpID"))
                    .withDataType(_int())
                    .withUseNotNull(true)
                    .and()
                .withColumnDefinition(
                        c_int("OldVacationHours"),
                        c_int("NewVacationHours"),
                        c_datetime("ModifiedDate"))
                .withTableConstraint()
//                    .$Constraint()
//                    .withPrimaryKey()
//                    .withColumnName(c("EmpID"))
                    .and()
                .withTableConstraint()
//                    .withUnique()
//                    .withColumnName(c("ModifiedDate"))
                    .and()
                .build();


        TableTypeDefinition quick = new TableTypeDefinitionBuilder<Void>()
                .withColumnDefinition()
                    .withColumnName(c("EmpID"))
                    .withDataType(_int())
                //TODO NOT NULL
                    .and()
                .$_(
                        c_int("OldVacationHours"),
                        c_int("NewVacationHours"),
                        c_datetime("ModifiedDate"))
                .$_PrimaryKey(c("EmpID"))
                .$_Unique(c("ModifiedDate"))
                .build();
        // @formatter:on

    }


    /**
     * table(
     * EmpID int NOT NULL,
     * OldVacationHours int,
     * NewVacationHours int,
     * ModifiedDate datetime);
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        TableTypeDefinition table = new TableTypeDefinitionBuilder<Void>()
                .withColumnDefinition()
                    .withColumnName(c("EmpID"))
                    .withDataType(_int())
                    .withUseNotNull(true)
                    .and()
                .withColumnDefinition(
                        c_int("OldVacationHours"),
                        c_int("NewVacationHours"),
                        c_datetime("ModifiedDate"))
                .build();
        // @formatter:on

        assertEquals(table.getList().size(), 4);
        ColumnDefinition columnDefinition = (ColumnDefinition) table.getList().get(0);
        ColumnDefinition columnDefinition1 = (ColumnDefinition) table.getList().get(1);
        ColumnDefinition columnDefinition2 = (ColumnDefinition) table.getList().get(2);
        ColumnDefinition columnDefinition3 = (ColumnDefinition) table.getList().get(3);
        assertEquals(columnDefinition.getName(), "EmpID");
        assertEquals(columnDefinition1.getName(), "OldVacationHours");
        assertEquals(columnDefinition2.getName(), "NewVacationHours");
        assertEquals(columnDefinition3.getName(), "ModifiedDate");
        assertTrue(columnDefinition.getDataType() instanceof Int);
        assertTrue(columnDefinition1.getDataType() instanceof Int);
        assertTrue(columnDefinition2.getDataType() instanceof Int);
        assertTrue(columnDefinition3.getDataType() instanceof DateTime);
    }

    /**
     * DONT Support table-valued function
     */
//    @Test
//    public void testExampleB1(){
//        //TODO
//    }

}
