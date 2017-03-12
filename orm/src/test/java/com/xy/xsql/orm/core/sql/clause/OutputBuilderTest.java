package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.data.sql.clause.Output;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e;
import static com.xy.xsql.orm.core.sql.clause.OutputBuilder.column_name;
import static com.xy.xsql.orm.core.sql.element.ColumnNameBuilder.c;
import static com.xy.xsql.orm.core.sql.element.TableNameBuilder.t;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class OutputBuilderTest {

    /**
     * OUTPUT deleted.*
     */
    @Test
    public void testOutput(){
        Output Output = new OutputBuilder<Void>()
                .withDmlSelect()
                    .withColumnName()
                        .withDeleted()
                        .withColumnName("*")
                        .and()
                    .and()
                .build();

        Assert.assertEquals(Output.getDmlSelectList().size(),1);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().getColumnName(),"*");
    }

    /**
     * OUTPUT DELETED.* INTO @MyTableVar
     */
    @Test
    public void testTableVariable(){
        Output Output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName()
                        .withDeleted()
                        .withColumnName("*")
                        .and()
                    .and()
                .withTableVariable("MyTableVar")
                .build();

        Assert.assertEquals(Output.getDmlSelectList().size(),1);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().getColumnName(),"*");
        Assert.assertEquals(Output.getTableVariable().toString(),"@MyTableVar");
    }

    /**
     * OUTPUT inserted.BusinessEntityID,
     deleted.VacationHours,
     inserted.VacationHours,
     inserted.ModifiedDate
     INTO @MyTableVar;
     */
    @Test
    public void testInsertedDeleted(){
        Output Output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("BusinessEntityID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withDeleted()
                        .withColumnName("VacationHours")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("VacationHours")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("ModifiedDate")
                        .and()
                    .and()
                .withTableVariable("MyTableVar")
                .build();

        Assert.assertEquals(Output.getDmlSelectList().size(),4);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().getColumnName(),"BusinessEntityID");
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().getColumnName(),"VacationHours");
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().getColumnName(),"VacationHours");
        Assert.assertEquals(Output.getDmlSelectList().get(3).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(3).getColumnName().getColumnName(),"ModifiedDate");
        Assert.assertEquals(Output.getTableVariable().toString(),"@MyTableVar");
    }

    /**
     * OUTPUT inserted.BusinessEntityID,
     deleted.VacationHours,
     inserted.VacationHours,
     inserted.VacationHours - deleted.VacationHours,
     inserted.ModifiedDate
     */
    @Test
    public void testScalarExpression (){
        // @formatter:off
        Output Output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("BusinessEntityID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withDeleted()
                        .withColumnName("VacationHours")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("VacationHours")
                        .and()
                    .and()
                .withDmlSelect()
                    .withScalarExpression(
                            e(
                            column_name()
                                .withInserted()
                                .withColumnName("VacationHours")
                                .build(),
                            OperatorEnum.Negative,
                            column_name()
                                .withInserted()
                                .withColumnName("VacationHours")
                                .build()
                            ))
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("ModifiedDate")
                        .and()
                    .and()
                .withTableVariable("MyTableVar")
                .build();

        // @formatter:on

        Assert.assertEquals(Output.getDmlSelectList().size(),4);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().getColumnName(),"BusinessEntityID");
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().getColumnName(),"VacationHours");
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().getColumnName(),"VacationHours");
        Assert.assertEquals(Output.getDmlSelectList().get(3).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(3).getColumnName().getColumnName(),"ModifiedDate");
        Assert.assertEquals(Output.getTableVariable().toString(),"@MyTableVar");
    }

    /**
     * OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate
     INTO MyTable ( NewScrapReasonID,
     Name,
     ModifiedDate)
     */
    @Test
    public void testTableColumn(){
        // @formatter:off
        Output Output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("ScrapReasonID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("Name")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("ModifiedDate")
                        .and()
                    .and()
                .withTableName(t("MyTable"))
                .withColumnName(c("NewScrapReasonID"))
                .withColumnName(c("Name"))
                .withColumnName(c("ModifiedDate"))
                .build();

        // @formatter:on

        Assert.assertEquals(Output.getDmlSelectList().size(),3);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().getColumnName(),"ModifiedDate");
        Assert.assertEquals(Output.getOutputTable().toString(),"MyTable");
        Assert.assertEquals(Output.getColumnList().get(0).toString(),"NewScrapReasonID");
        Assert.assertEquals(Output.getColumnList().get(1).toString(),"Name");
        Assert.assertEquals(Output.getColumnList().get(2).toString(),"ModifiedDate");
    }
}
