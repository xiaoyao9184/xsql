package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.datatype.ColumnDefinitionFactory.c_int;
import static com.xy.xsql.tsql.core.datatype.DataTypes._int;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class ColumnDefinitionBuilderTest {

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

        ColumnDefinition quick = c_int("EmpID");
        // @formatter:on

        Assert.assertEquals(columnDefinition.getName(), "EmpID");
        Assert.assertEquals(columnDefinition.getDataType().toString(), "int");

        Assert.assertEquals(quick.getName(), "EmpID");
        Assert.assertEquals(quick.getDataType().toString(), "int");
    }

}
