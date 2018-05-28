package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.queries.Output;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.OutputBuilder.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.output.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/11.
 */
public class outputTest {

    /**
     * OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate
     INTO MyTable ( NewScrapReasonID,
     Name,
     ModifiedDate)
     */
    @Test
    public void testTableColumn(){
        // @formatter:off
        Output output = OUTPUT(
                INSERTED("ScrapReasonID")
                .INSERTED("Name")
                .INSERTED("ModifiedDate"),
                INTO(t("MyTable"))
                .$$(c("NewScrapReasonID"),c("Name"),c("ModifiedDate"))
        ).build();
        // @formatter:on

        assertEquals(output.getDmlSelectList().size(),3);
        assertEquals(output.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        assertEquals(output.getDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        assertEquals(output.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        assertEquals(output.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        assertEquals(output.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        assertEquals(output.getDmlSelectList().get(2).getColumnName().getColumnName(),"ModifiedDate");
        assertEquals(output.getOutputTable().toString(),"MyTable");
        assertEquals(output.getColumnList().get(0).toString(),"NewScrapReasonID");
        assertEquals(output.getColumnList().get(1).toString(),"Name");
        assertEquals(output.getColumnList().get(2).toString(),"ModifiedDate");
    }


    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/output-clause-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate
     INTO @MyTableVar
     */
    public Output exampleA = OUTPUT(
            INSERTED("ScrapReasonID")
            .INSERTED("Name")
            .INSERTED("ModifiedDate"),
            INTO("MyTableVar")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getDmlSelectList().size(),3);
        assertEquals(exampleA.getDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        assertEquals(exampleA.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        assertEquals(exampleA.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT DELETED.*
     */
    public Output exampleB = OUTPUT(
            DELETED()
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getOutputDmlSelectList().size(),1);
        assertEquals(exampleB.getOutputDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        assertEquals(exampleB.getOutputDmlSelectList().get(0).getColumnName().isUseAll(),true);

    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT inserted.BusinessEntityID,
     deleted.VacationHours,
     inserted.VacationHours,
     inserted.ModifiedDate
     */
    public Output exampleC = OUTPUT(
            INSERTED("BusinessEntityID")
            .DELETED("VacationHours")
            .INSERTED("VacationHours")
            .INSERTED("ModifiedDate")
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getOutputDmlSelectList().size(),4);
        assertEquals(exampleC.getOutputDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        assertEquals(exampleC.getOutputDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        assertEquals(exampleC.getOutputDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        assertEquals(exampleC.getOutputDmlSelectList().get(3).getColumnName().isUseInserted(),true);
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT inserted.BusinessEntityID,
     deleted.VacationHours,
     inserted.VacationHours,
     inserted.VacationHours - deleted.VacationHours,
     inserted.ModifiedDate
     */
    public Output exampleD = OUTPUT(
            INSERTED("BusinessEntityID")
            .DELETED("VacationHours")
            .INSERTED("VacationHours")
            .$$(e_subtraction(
                        c_inserted("VacationHours"),
                        c_deleted("VacationHours")))
            .INSERTED("ModifiedDate")
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getOutputDmlSelectList().size(),5);
        assertEquals(exampleD.getOutputDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        assertEquals(exampleD.getOutputDmlSelectList().get(0).getColumnName().getColumnName(),"BusinessEntityID");
        assertEquals(exampleD.getOutputDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        assertEquals(exampleD.getOutputDmlSelectList().get(1).getColumnName().getColumnName(),"VacationHours");
        assertEquals(exampleD.getOutputDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        assertEquals(exampleD.getOutputDmlSelectList().get(2).getColumnName().getColumnName(),"VacationHours");
        assertNotNull(exampleD.getOutputDmlSelectList().get(3).getScalarExpression());
        assertEquals(exampleD.getOutputDmlSelectList().get(4).getColumnName().isUseInserted(),true);
        assertEquals(exampleD.getOutputDmlSelectList().get(4).getColumnName().getColumnName(),"ModifiedDate");
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT deleted.ScrapReasonID,
     inserted.ScrapReasonID,
     inserted.WorkOrderID,
     inserted.ProductID,
     p.Name
     INTO @MyTestVar
     */
    public Output exampleE = OUTPUT(
            DELETED("ScrapReasonID")
            .INSERTED("ScrapReasonID")
            .INSERTED("WorkOrderID")
            .INSERTED("ProductID")
            .$$(c("p","Name")),
            INTO("MyTestVar")
    ).build();
    // @formatter:on

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getDmlSelectList().size(),5);
        assertEquals(exampleE.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        assertEquals(exampleE.getDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        assertEquals(exampleE.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        assertEquals(exampleE.getDmlSelectList().get(1).getColumnName().getColumnName(),"ScrapReasonID");
        assertEquals(exampleE.getDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        assertEquals(exampleE.getDmlSelectList().get(2).getColumnName().getColumnName(),"WorkOrderID");
        assertEquals(exampleE.getDmlSelectList().get(3).getColumnName().isUseInserted(),true);
        assertEquals(exampleE.getDmlSelectList().get(3).getColumnName().getColumnName(),"ProductID");
        assertEquals(exampleE.getDmlSelectList().get(4).getColumnName().getColumnName(),"Name");
        assertEquals(exampleE.getTableVariable().toString(),"@MyTestVar");
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT DELETED.ProductID,
     p.Name,
     p.ProductModelID,
     DELETED.ProductPhotoID
     INTO @MyTableVar
     */
    public Output exampleF = OUTPUT(
            DELETED("ProductID")
            .$$(c("p","Name"))
            .$$(c("p","ProductModelID"))
            .DELETED("ProductPhotoID"),
            INTO("MyTableVar")
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getDmlSelectList().size(),4);
        assertEquals(exampleF.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        assertEquals(exampleF.getDmlSelectList().get(0).getColumnName().getColumnName(),"ProductID");
        assertEquals(exampleF.getDmlSelectList().get(1).getColumnName().isUseDeleted(),false);
        assertEquals(exampleF.getDmlSelectList().get(1).getColumnName().isUseInserted(),false);
        assertEquals(exampleF.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        assertEquals(exampleF.getDmlSelectList().get(2).getColumnName().isUseDeleted(),false);
        assertEquals(exampleF.getDmlSelectList().get(2).getColumnName().isUseInserted(),false);
        assertEquals(exampleF.getDmlSelectList().get(2).getColumnName().getColumnName(),"ProductModelID");
        assertEquals(exampleF.getDmlSelectList().get(3).getColumnName().isUseDeleted(),true);
        assertEquals(exampleF.getDmlSelectList().get(3).getColumnName().getColumnName(),"ProductPhotoID");
        assertEquals(exampleF.getTableVariable().toString(),"@MyTableVar");
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT deleted.DocumentSummary,
     inserted.DocumentSummary
     INTO @MyTableVar
     */
    public Output exampleG = OUTPUT(
            DELETED("DocumentSummary")
            .INSERTED("DocumentSummary"),
            INTO("MyTableVar")
    ).build();
    // @formatter:on

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getDmlSelectList().size(),2);
        assertEquals(exampleG.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        assertEquals(exampleG.getDmlSelectList().get(0).getColumnName().getColumnName(),"DocumentSummary");
        assertEquals(exampleG.getDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        assertEquals(exampleG.getDmlSelectList().get(1).getColumnName().getColumnName(),"DocumentSummary");
        assertEquals(exampleG.getTableVariable().toString(),"@MyTableVar");
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT INSERTED.ScrapReasonID, INSERTED.Name,
     INSERTED.ModifiedDate
     */
    public Output exampleH = OUTPUT(
            INSERTED("ScrapReasonID")
            .INSERTED("Name")
            .INSERTED("ModifiedDate")
    ).build();
    // @formatter:on

    @Test
    public void testExampleH(){
        assertEquals(exampleH.getOutputDmlSelectList().size(),3);
        assertEquals(exampleH.getOutputDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        assertEquals(exampleH.getOutputDmlSelectList().get(0).getColumnName().getColumnName(),"ScrapReasonID");
        assertEquals(exampleH.getOutputDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        assertEquals(exampleH.getOutputDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        assertEquals(exampleH.getOutputDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        assertEquals(exampleH.getOutputDmlSelectList().get(2).getColumnName().getColumnName(),"ModifiedDate");
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT INSERTED.LastName,
     INSERTED.FirstName,
     INSERTED.CurrentSales
     */
    public Output exampleI = OUTPUT(
            INSERTED("LastName")
            .INSERTED("FirstName")
            .INSERTED("CurrentSales")
    ).build();
    // @formatter:on

    @Test
    public void testExampleI(){
        assertEquals(exampleI.getOutputDmlSelectList().size(),3);
        assertEquals(exampleI.getOutputDmlSelectList().get(0).getColumnName().isUseInserted(),true);
        assertEquals(exampleI.getOutputDmlSelectList().get(0).getColumnName().getColumnName(),"LastName");
        assertEquals(exampleI.getOutputDmlSelectList().get(1).getColumnName().isUseInserted(),true);
        assertEquals(exampleI.getOutputDmlSelectList().get(1).getColumnName().getColumnName(),"FirstName");
        assertEquals(exampleI.getOutputDmlSelectList().get(2).getColumnName().isUseInserted(),true);
        assertEquals(exampleI.getOutputDmlSelectList().get(2).getColumnName().getColumnName(),"CurrentSales");
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT DELETED.ProductID,
     p.Name,
     p.ProductModelID,
     DELETED.ProductPhotoID
     INTO @MyTableVar
     OUTPUT DELETED.ProductID, DELETED.ProductPhotoID, GETDATE() AS DeletedDate
     */
    public Output exampleJ = OUTPUT(
            DELETED("ProductID")
            .$$(c("p","Name"))
            .$$(c("p","ProductModelID"))
            .DELETED("ProductPhotoID"),
            INTO("MyTableVar"),
            OUTPUT(
                    DELETED("ProductID")
                    .DELETED("ProductPhotoID")
                    .$$(e("GETDATE()"),AS("DeletedDate"))
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleJ(){
        assertEquals(exampleJ.getDmlSelectList().size(),4);
        assertEquals(exampleJ.getDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        assertEquals(exampleJ.getDmlSelectList().get(0).getColumnName().getColumnName(),"ProductID");
        assertEquals(exampleJ.getDmlSelectList().get(1).getColumnName().getColumnName(),"Name");
        assertEquals(exampleJ.getDmlSelectList().get(2).getColumnName().getColumnName(),"ProductModelID");
        assertEquals(exampleJ.getDmlSelectList().get(3).getColumnName().isUseDeleted(),true);
        assertEquals(exampleJ.getDmlSelectList().get(3).getColumnName().getColumnName(),"ProductPhotoID");
        assertEquals(exampleJ.getTableVariable().toString(),"@MyTableVar");

        assertEquals(exampleJ.getOutputDmlSelectList().size(),3);
        assertEquals(exampleJ.getOutputDmlSelectList().get(0).getColumnName().isUseDeleted(),true);
        assertEquals(exampleJ.getOutputDmlSelectList().get(0).getColumnName().getColumnName(),"ProductID");
        assertEquals(exampleJ.getOutputDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        assertEquals(exampleJ.getOutputDmlSelectList().get(1).getColumnName().getColumnName(),"ProductPhotoID");
        assertEquals(exampleJ.getOutputDmlSelectList().get(2).getColumnAliasIdentifier(),"DeletedDate");
    }


    // @formatter:off
    //parent+quick
    /**
     * OUTPUT $action, deleted.ProductID
     */
    public Output exampleK = OUTPUT(
            $action()
            .DELETED("ProductID")
    ).build();
    // @formatter:on

    @Test
    public void testExampleK(){
        assertEquals(exampleK.getOutputDmlSelectList().size(),2);
        assertEquals(exampleK.getOutputDmlSelectList().get(0).getColumnName().is$action(),true);
        assertEquals(exampleK.getOutputDmlSelectList().get(1).getColumnName().isUseDeleted(),true);
        assertEquals(exampleK.getOutputDmlSelectList().get(1).getColumnName().getColumnName(),"ProductID");
    }

}