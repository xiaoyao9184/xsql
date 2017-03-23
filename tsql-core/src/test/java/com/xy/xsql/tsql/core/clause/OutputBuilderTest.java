package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.Output;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.OutputBuilder.*;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.GroupExpressions.e_subtraction;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class OutputBuilderTest {


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

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$Inserted("LastName","Name","ModifiedDate")
                    .$Into(t("MyTable"),
                            c("NewScrapReasonID"),
                            c("Name"),
                            c("ModifiedDate"))
                    .and();
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


    /**
     * OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate
     INTO @MyTableVar
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_inserted("ScrapReasonID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("Name"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("ModifiedDate"))
                    .and()
                .withTableVariable("MyTableVar")
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_inserted("ScrapReasonID"),
                            c_inserted("Name"),
                            c_inserted("ModifiedDate"))
                    .$Into("MyTableVar")
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),3);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);

    }

    /**
     * OUTPUT DELETED.*
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Output Output = new OutputBuilder<Void>()
                .withDmlSelect()
                    .withColumnName()
                        .withDeleted()
                        .withColumnName("*")
                        .and()
                    .and()
                .build();

        //same as
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_deleted())
                    .and()
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_deleted())
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),1);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseAll(),true);

    }

    /**
     * OUTPUT inserted.BusinessEntityID,
     deleted.VacationHours,
     inserted.VacationHours,
     inserted.ModifiedDate
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_inserted("BusinessEntityID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_deleted("VacationHours"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("VacationHours"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("ModifiedDate"))
                    .and()
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_inserted("BusinessEntityID"),
                        c_deleted("VacationHours"),
                        c_inserted("VacationHours"),
                        c_inserted("ModifiedDate"))
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),4);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(3).getColumnName().isUseInserted(),true);

    }

    /**
     * OUTPUT inserted.BusinessEntityID,
     deleted.VacationHours,
     inserted.VacationHours,
     inserted.VacationHours - deleted.VacationHours,
     inserted.ModifiedDate
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_inserted("BusinessEntityID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_deleted("VacationHours"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("VacationHours"))
                    .and()
                .withDmlSelect()
                    .withScalarExpression(
                                e_subtraction(
                                        c_inserted("VacationHours"),
                                        c_deleted("VacationHours")
                                ))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("ModifiedDate"))
                    .and()
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_inserted("BusinessEntityID"),
                        c_deleted("VacationHours"),
                        c_inserted("VacationHours"))
                    .$(e_subtraction(
                        c_inserted("VacationHours"),
                        c_deleted("VacationHours")))
                    .$(c_inserted("ModifiedDate"))
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),5);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"BusinessEntityID");
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"VacationHours");
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().getColumnName(),"VacationHours");
        Assert.assertNotNull(output.getDmlSelectList().get(3).getScalarExpression());
        Assert.assertEquals(output.getDmlSelectList().get(4).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(4).getColumnName().getColumnName(),"ModifiedDate");


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
                            e_subtraction(
                                    c_inserted("VacationHours"),
                                    c_inserted("VacationHours")
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

        Assert.assertEquals(Output.getDmlSelectList().size(),5);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().getColumnName(),"BusinessEntityID");
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().getColumnName(),"VacationHours");
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().getColumnName(),"VacationHours");
        Assert.assertNotNull(Output.getDmlSelectList().get(3).getScalarExpression());
        Assert.assertEquals(Output.getDmlSelectList().get(4).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(4).getColumnName().getColumnName(),"ModifiedDate");
        Assert.assertEquals(Output.getTableVariable().toString(),"@MyTableVar");

    }

    /**
     * OUTPUT deleted.ScrapReasonID,
     inserted.ScrapReasonID,
     inserted.WorkOrderID,
     inserted.ProductID,
     p.Name
     INTO @MyTestVar
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_deleted("ScrapReasonID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("ScrapReasonID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("WorkOrderID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("ProductID"))
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withFromTableName("p")
                        .withColumnName("Name")
                        .and()
                    .and()
                .withTableVariable("MyTestVar")
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_deleted("ScrapReasonID"),
                        c_inserted("ScrapReasonID"),
                        c_inserted("WorkOrderID"),
                        c_inserted("ProductID"),
                        c("p","Name"))
                    .$Into("MyTestVar")
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),5);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"ScrapReasonID");
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().getColumnName(),"WorkOrderID");
        Assert.assertEquals(output.getDmlSelectList().get(3).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(3).getColumnName().getColumnName(),"ProductID");
        Assert.assertEquals(output.getDmlSelectList().get(4).getColumnName().getColumnName(),"Name");
        Assert.assertEquals(output.getTableVariable().toString(),"@MyTestVar");

        // @formatter:off
        Output Output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName()
                        .withDeleted()
                        .withColumnName("ScrapReasonID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("ScrapReasonID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("WorkOrderID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withInserted()
                        .withColumnName("ProductID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withFromTableName("p")
                        .withColumnName("Name")
                        .and()
                    .and()
                .withTableVariable("MyTestVar")
                .build();
        // @formatter:on

        Assert.assertEquals(Output.getDmlSelectList().size(),5);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(1).getColumnName().getColumnName(),"ScrapReasonID");
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(2).getColumnName().getColumnName(),"WorkOrderID");
        Assert.assertEquals(Output.getDmlSelectList().get(3).getColumnName().isUseInserted(),true);
        Assert.assertEquals(Output.getDmlSelectList().get(3).getColumnName().getColumnName(),"ProductID");
        Assert.assertEquals(Output.getDmlSelectList().get(4).getColumnName().getColumnName(),"Name");
        Assert.assertEquals(Output.getTableVariable().toString(),"@MyTestVar");
    }

    /**
     * OUTPUT DELETED.ProductID,
     p.Name,
     p.ProductModelID,
     DELETED.ProductPhotoID
     INTO @MyTableVar
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_deleted("ProductID"))
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withFromTableName("p")
                        .withColumnName("Name")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withFromTableName("p")
                        .withColumnName("ProductModelID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName(c_deleted("ProductPhotoID"))
                    .and()
                .withTableVariable("MyTestVar")
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_deleted("ProductID"),
                        c("p","Name"),
                        c("p","ProductModelID"),
                        c_deleted("ProductPhotoID"))
                    .$Into("MyTableVar")
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),4);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ProductID");
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseDeleted(),false);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),false);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseDeleted(),false);
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseInserted(),false);
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().getColumnName(),"ProductModelID");
        Assert.assertEquals(output.getDmlSelectList().get(3).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(3).getColumnName().getColumnName(),"ProductPhotoID");
        Assert.assertEquals(output.getTableVariable().toString(),"@MyTestVar");

    }

    /**
     * OUTPUT deleted.DocumentSummary,
     inserted.DocumentSummary
     INTO @MyTableVar
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_deleted("ScrapReasonID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("Name"))
                    .and()
                .withTableVariable("MyTableVar")
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_deleted("DocumentSummary"),
                        c_inserted("DocumentSummary"))
                    .$Into("MyTableVar")
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),2);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        Assert.assertEquals(output.getTableVariable().toString(),"@MyTableVar");
    }

    /**
     * OUTPUT INSERTED.ScrapReasonID, INSERTED.Name,
     INSERTED.ModifiedDate
     */
    @Test
    public void testExampleH(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_inserted("ScrapReasonID"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("Name"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("ModifiedDate"))
                    .and()
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_inserted("ScrapReasonID"),
                        c_inserted("Name"),
                        c_inserted("ModifiedDate"))
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),3);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().getColumnName(),"ModifiedDate");
    }

    /**
     * OUTPUT INSERTED.LastName,
     INSERTED.FirstName,
     INSERTED.CurrentSales
     */
    @Test
    public void testExampleI(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_inserted("LastName"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("FirstName"))
                    .and()
                .withDmlSelect()
                    .withColumnName(c_inserted("CurrentSales"))
                    .and()
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_inserted("LastName"),
                        c_inserted("FirstName"),
                        c_inserted("CurrentSales"))
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),3);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"LastName");
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"FirstName");
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().getColumnName(),"CurrentSales");
    }

    /**
     * OUTPUT DELETED.ProductID,
     p.Name,
     p.ProductModelID,
     DELETED.ProductPhotoID
     INTO @MyTableVar
     OUTPUT DELETED.ProductID, DELETED.ProductPhotoID, GETDATE() AS DeletedDate
     */
    @Test
    public void testExampleJ(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName(c_deleted("ProductID"))
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withFromTableName("p")
                        .withColumnName("Name")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName()
                        .withFromTableName("p")
                        .withColumnName("ProductModelID")
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName(c_deleted("ProductPhotoID"))
                    .and()
                .withTableVariable("MyTableVar")
                .withOutputDmlSelect()
                    .withColumnName(c_deleted("ProductID"))
                    .and()
                .withOutputDmlSelect()
                    .withColumnName(c_deleted("ProductPhotoID"))
                    .and()
                .withOutputDmlSelect()
                    .withScalarExpression(e("GETDATE()"))
                    .withAs()
                    .withColumnAliasIdentifier("DeletedDate")
                    .and()
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_deleted("ProductID"),
                        c("p","Name"),
                        c("p","ProductModelID"),
                        c_deleted("ProductPhotoID"))
                    .$Into("MyTableVar")
                    .$Output(
                            c_deleted("ProductID"),
                            c_deleted("ProductPhotoID"),
                            //todo
                            c("GETDATE() AS DeletedDate")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),4);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ProductID");
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        Assert.assertEquals(output.getDmlSelectList().get(2).getColumnName().getColumnName(),"ProductModelID");
        Assert.assertEquals(output.getDmlSelectList().get(3).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(3).getColumnName().getColumnName(),"ProductPhotoID");
        Assert.assertEquals(output.getTableVariable().toString(),"@MyTableVar");

        Assert.assertEquals(output.getOutputDmlSelectList().size(),3);
        Assert.assertEquals(output.getOutputDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getOutputDmlSelectList().get(0).getColumnName().getColumnName(),"ProductID");
        Assert.assertEquals(output.getOutputDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getOutputDmlSelectList().get(1).getColumnName().getColumnName(),"ProductPhotoID");
        Assert.assertEquals(output.getOutputDmlSelectList().get(2).getColumnAliasIdentifier(),"DeletedDate");
    }

    /**
     * OUTPUT $action, deleted.ProductID
     */
    @Test
    public void testExampleK(){
        // @formatter:off
        Output output = new OutputBuilder<Output>()
                .withDmlSelect()
                    .withColumnName()
                        .with$action()
                        .and()
                    .and()
                .withDmlSelect()
                    .withColumnName(c_deleted("ProductID"))
                    .and()
                .build();

        //parent+quick
        MockParent<Output> parent = new MockParentBuilder<OutputBuilder<MockParent<Output>>,Output>
                (OutputBuilder.class,Output.class)
                .$child()
                    .$(c_$action(),
                        c_deleted("ProductID"))
                    .and();
        // @formatter:on

        Assert.assertEquals(output.getDmlSelectList().size(),2);
        Assert.assertEquals(output.getDmlSelectList().get(0).getColumnName().is$action(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        Assert.assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"ProductID");

    }
}
