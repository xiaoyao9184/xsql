package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.core.statement.dml.InsertBuilder;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.Expressions.e_n_string;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;
import static com.xy.xsql.tsql.core.statement.dml.InsertBuilder.INSERT;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class InsertBuilderTest {


    // @formatter:off
    //parent+quick
    public Insert example1A = INSERT()
                .$Into()
                .$(t("Production","UnitMeasure"))
                .$Values()
                    .$(e_n_string("FT"),e_n_string("Feet"),e_string("20080414"))
                    .and()
                .done();
    // @formatter:on

    /**
     * INSERT INTO Production.UnitMeasure
     VALUES (N'FT', N'Feet', '20080414');
     */
    @Test
    public void testExample1A(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("Production","UnitMeasure"))
                .withValues()
                  .withItem()
                        .withRowValueExpression(e_n_string("FT"))
                        .withRowValueExpression(e_n_string("Feet"))
                        .withRowValueExpression(e_string("20080414"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
    }


    // @formatter:off
    //parent+quick
    public Insert example1B = INSERT()
                .$Into()
                .$(t("Production","UnitMeasure"))
                .$Values()
                    .$(e_n_string("FT2"),e_n_string("Square Feet "),e_string("20080923"))
                    .$(e_n_string("Y"),e_n_string("Yards"),e_string("20080923"))
                    .$(e_n_string("Y3"),e_n_string("Cubic Yards"),e_string("20080923"))
                    .and()
                .done();
    // @formatter:on

    /**
     * INSERT INTO Production.UnitMeasure
     VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923'), (N'Y3', N'Cubic Yards', '20080923');
     */
    @Test
    public void testExample1B(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("Production","UnitMeasure"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_n_string("FT2"))
                        .withRowValueExpression(e_n_string("Square Feet "))
                        .withRowValueExpression(e_string("20080923"))
                        .and()
                    .withItem()
                        .withRowValueExpression(e_n_string("Y"))
                        .withRowValueExpression(e_n_string("Yards"))
                        .withRowValueExpression(e_string("20080923"))
                        .and()
                    .withItem()
                        .withRowValueExpression(e_n_string("Y3"))
                        .withRowValueExpression(e_n_string("Cubic Yards"))
                        .withRowValueExpression(e_string("20080923"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(1).size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(2).size(),3);
    }


    // @formatter:off
    //parent+quick
    public Insert example1C = INSERT()
                .$Into()
                .$(t("Production","UnitMeasure"))
                .$(c("Name"),c("UnitMeasureCode"),c("ModifiedDate"))
                .$Values()
                    .$(e_n_string("Square Yards"),e_n_string("Y2"),e("GETDATE()"))
                    .and()
                .done();
    // @formatter:on

    /**
     * INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,
     ModifiedDate)
     VALUES (N'Square Yards', N'Y2', GETDATE());
     */
    @Test
    public void testExample1C(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("Production","UnitMeasure"))
                .withColumn(c("Name"))
                .withColumn(c("UnitMeasureCode"))
                .withColumn(c("ModifiedDate"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_n_string("Square Yards"))
                        .withRowValueExpression(e_n_string("Y2"))
                        .withRowValueExpression(e("GETDATE()"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        Assert.assertEquals(insert.getColumns().size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
    }


    // @formatter:off
    //parent+quick
    public Insert example2A = INSERT()
            .$Into()
            .$(t("dbo","T1"))
            .$(c("column_4"))
            .$Values()
            .$(e_string("Explicit value"))
            .and()
            .done();

    public Insert example2A1 = INSERT()
            .$Into()
            .$(t("dbo","T1"))
            .$(c("column_2"),c("column_4"))
            .$Values()
            .$(e_string("Explicit value"),e_string("Explicit value"))
            .and()
            .done();

    public Insert example2A2 = INSERT()
            .$Into()
            .$(t("dbo","T1"))
            .$(c("column_2"))
            .$Values()
            .$(e_string("Explicit value"))
            .and()
            .done();

    public Insert example2A3 = INSERT()
            .$Into()
            .$(t("T1"))
            .$Default_Values()
            .done();
    // @formatter:on

    /**
     * INSERT INTO dbo.T1 (column_4)
     VALUES ('Explicit value');
     INSERT INTO dbo.T1 (column_2, column_4)
     VALUES ('Explicit value', 'Explicit value');
     INSERT INTO dbo.T1 (column_2)
     VALUES ('Explicit value');
     INSERT INTO T1 DEFAULT VALUES;
     */
    @Test
    public void testExample2A(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("dbo","T1"))
                .withColumn(c("column_4"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_string("Explicit value"))
                        .and()
                    .and()
                .build();

        Insert insert1 = new InsertBuilder()
                .withInto()
                .withTableName(t("dbo","T1"))
                .withColumn(c("column_2"))
                .withColumn(c("column_4"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_string("Explicit value"))
                        .withRowValueExpression(e_string("Explicit value"))
                        .and()
                    .and()
                .build();

        Insert insert2 = new InsertBuilder()
                .withInto()
                .withTableName(t("dbo","T1"))
                .withColumn(c("column_2"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_string("Explicit value"))
                        .and()
                    .and()
                .build();

        Insert insert3 = new InsertBuilder()
                .withInto()
                .withTableName(t("T1"))
                .withDefaultValues()
                .build();
        // @formatter:on

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"dbo.T1");
        Assert.assertEquals(insert.getColumns().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        Assert.assertTrue(insert1.isUseInto());
        Assert.assertEquals(insert1.getTableName().toString(),"dbo.T1");
        Assert.assertEquals(insert1.getColumns().size(),2);
        Assert.assertEquals(insert1.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert1.getValues().getRowValueExpressionListGroup().get(0).size(),2);

        Assert.assertTrue(insert2.isUseInto());
        Assert.assertEquals(insert2.getTableName().toString(),"dbo.T1");
        Assert.assertEquals(insert2.getColumns().size(),1);
        Assert.assertEquals(insert2.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert2.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        Assert.assertTrue(insert3.isUseInto());
        Assert.assertEquals(insert3.getTableName().toString(),"T1");
        Assert.assertEquals(insert3.isUseDefaultValues(),true);
    }
}
