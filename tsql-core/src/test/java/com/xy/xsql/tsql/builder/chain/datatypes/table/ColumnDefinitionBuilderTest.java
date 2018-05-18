package com.xy.xsql.tsql.builder.chain.datatypes.table;

import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionFactory.c_int;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.column.DataTypes._int;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class ColumnDefinitionBuilderTest {



    public ColumnDefinition exampleA = c_int("EmpID");

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
        ColumnDefinition columnDefinition = new ColumnDefinitionBuilder<Void>()
                .withColumnName(c("EmpID"))
                .withDataType(_int())
                .build();
        // @formatter:on

        Assert.assertEquals(columnDefinition.getName(), "EmpID");
        Assert.assertEquals(columnDefinition.getDataType().toString(), "int");

        Assert.assertEquals(exampleA.getName(), "EmpID");
        Assert.assertEquals(exampleA.getDataType().toString(), "int");
    }

}
