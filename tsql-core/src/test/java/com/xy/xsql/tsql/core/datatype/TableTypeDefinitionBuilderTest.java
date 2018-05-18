package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.TableTypeDefinition;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.datatype.ColumnDefinitionFactory.c_datetime;
import static com.xy.xsql.tsql.core.datatype.ColumnDefinitionFactory.c_int;
import static com.xy.xsql.tsql.core.datatype.DataTypes._int;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;

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

        Assert.assertEquals(table.getList().size(), 4);
        ColumnDefinition columnDefinition = (ColumnDefinition) table.getList().get(0);
        ColumnDefinition columnDefinition1 = (ColumnDefinition) table.getList().get(1);
        ColumnDefinition columnDefinition2 = (ColumnDefinition) table.getList().get(2);
        ColumnDefinition columnDefinition3 = (ColumnDefinition) table.getList().get(3);
        Assert.assertEquals(columnDefinition.getName(), "EmpID");
        Assert.assertEquals(columnDefinition1.getName(), "OldVacationHours");
        Assert.assertEquals(columnDefinition2.getName(), "NewVacationHours");
        Assert.assertEquals(columnDefinition3.getName(), "ModifiedDate");
        Assert.assertEquals(columnDefinition.getDataType().toString(), "int");
        Assert.assertEquals(columnDefinition1.getDataType().toString(), "int");
        Assert.assertEquals(columnDefinition2.getDataType().toString(), "int");
        Assert.assertEquals(columnDefinition3.getDataType().toString(), "datetime");
    }

    /**
     * DONT Support table-valued function
     */
//    @Test
//    public void testExampleB1(){
//        //TODO
//    }

}
