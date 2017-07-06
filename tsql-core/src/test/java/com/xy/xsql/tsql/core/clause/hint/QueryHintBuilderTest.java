package com.xy.xsql.tsql.core.clause.hint;

import com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.*;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.OptimizeForBuilder.OPTIMIZE_FOR_Item;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.FORCESEEK;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.INDEX;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.NOLOCK;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class QueryHintBuilderTest {

    // @formatter:off
    /**
     * MERGE JOIN
     */
    public QueryHint exampleA = MERGE_JOIN();
    // @formatter:on

    @Test
    public void testExampleA() {
        QueryHint queryHint = new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MERGE_JOIN)
                .build();

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.MERGE_JOIN);
    }


    // @formatter:off
    /**
     * OPTIMIZE FOR (@city_name = 'Seattle', @postal_code UNKNOWN)
     */
    public QueryHint exampleB = OPTIMIZE_FOR(
                OPTIMIZE_FOR_Item("city_name",false,"Seattle"),
                OPTIMIZE_FOR_Item("postal_code",true,null));
    // @formatter:on

    @Test
    public void testExampleB() {
        Assert.assertEquals(exampleB.getType(),QueryHint.Type.OPTIMIZE_FOR);
        Assert.assertEquals(exampleB.getOptimizeFor().size(),2);
    }


    // @formatter:off
    /**
     * MAXRECURSION 2
     */
    public QueryHint exampleC = MAXRECURSION(2);
    // @formatter:on

    @Test
    public void testExampleC() {
        Assert.assertEquals(exampleC.getType(),QueryHint.Type.MAXRECURSION);
        Assert.assertEquals(exampleC.getNumber().intValue(),2);
    }


    // @formatter:off
    /**
     * MERGE UNION
     */
    public QueryHint exampleD = MERGE_UNION();
    // @formatter:on

    @Test
    public void testExampleD() {
        Assert.assertEquals(exampleD.getType(),QueryHint.Type.MERGE_UNION);
    }


    // @formatter:off
    /**
     * HASH GROUP
     */
    public QueryHint exampleE1 = HASH_GROUP();

    /**
     * FAST 10
     */
    public QueryHint exampleE2 = FAST(10);
    // @formatter:on

    @Test
    public void testExampleE() {
        Assert.assertEquals(exampleE1.getType(),QueryHint.Type.HASH_GROUP);
        Assert.assertEquals(exampleE2.getType(),QueryHint.Type.FAST);
        Assert.assertEquals(exampleE2.getNumberRows().intValue(),10);
    }


    // @formatter:off
    /**
     * MAXDOP 2
     */
    public QueryHint exampleF = MAXDOP(2);
    // @formatter:on

    @Test
    public void testExampleF() {
        Assert.assertEquals(exampleF.getType(),QueryHint.Type.MAXDOP);
        Assert.assertEquals(exampleF.getNumberOfProcessors().intValue(),2);
    }


    // @formatter:off
    /**
     * TABLE HINT(e, INDEX (IX_Employee_ManagerID))
     */
    public QueryHint exampleG1 = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"));

    /**
     * TABLE HINT(e, INDEX(PK_Employee_EmployeeID, IX_Employee_ManagerID))
     */
    public QueryHint exampleG2 = TABLE_HINT(
            "e",
            INDEX("PK_Employee_EmployeeID","IX_Employee_ManagerID"));
    // @formatter:on

    @Test
    public void testExampleG() {
        Assert.assertEquals(exampleG1.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleG1.getTableHintList().size(),1);
        Assert.assertEquals(exampleG1.getTableHintList().get(0).getIndex_value().get(0).toString(),"IX_Employee_ManagerID");

        Assert.assertEquals(exampleG2.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleG2.getTableHintList().size(),1);
        Assert.assertEquals(exampleG2.getTableHintList().get(0).getIndex_value().get(0).toString(),"PK_Employee_EmployeeID");
        Assert.assertEquals(exampleG2.getTableHintList().get(0).getIndex_value().get(1).toString(),"IX_Employee_ManagerID");
    }


    // @formatter:off
    /**
     * TABLE HINT( HumanResources.Employee, FORCESEEK)
     */
    public QueryHint exampleH = TABLE_HINT(
                "HumanResources.Employee",
                FORCESEEK());
    // @formatter:on

    @Test
    public void testExampleH() {
        Assert.assertEquals(exampleH.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleH.getTableHintList().size(),1);
        Assert.assertEquals(exampleH.getTableHintList().get(0).getType(), TableHint.Type.FORCESEEK);
    }


    // @formatter:off
    /**
     * TABLE HINT (e, INDEX( IX_Employee_ManagerID))
     */
    public QueryHint exampleI1 = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"));

    /**
     * TABLE HINT (c, FORCESEEK)
     */
    public QueryHint exampleI2 = TABLE_HINT(
            "c",
            FORCESEEK());
    // @formatter:on

    @Test
    public void testExampleI() {
        Assert.assertEquals(exampleI1.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleI1.getTableHintList().size(),1);
        Assert.assertEquals(exampleI1.getTableHintList().get(0).getType(), TableHint.Type.INDEX);
        Assert.assertEquals(exampleI1.getTableHintList().get(0).getIndex_value().get(0).toString(),"IX_Employee_ManagerID");

        Assert.assertEquals(exampleI2.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleI2.getTableHintList().size(),1);
        Assert.assertEquals(exampleI2.getTableHintList().get(0).getType(), TableHint.Type.FORCESEEK);
    }

    // @formatter:off
    /**
     * TABLE HINT(e)
     */
    public QueryHint exampleJ = TABLE_HINT("e");
    // @formatter:on

    @Test
    public void testExampleJ() {
        Assert.assertEquals(exampleJ.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleJ.getExposedObjectName(),"e");
        Assert.assertNull(exampleJ.getTableHintList());
    }

    // @formatter:off
    /**
     * TABLE HINT (e, INDEX(IX_Employee_ManagerID), NOLOCK, FORCESEEK)
     */
    public QueryHint exampleK1 = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"),
                NOLOCK(),
                FORCESEEK());

    /**
     * TABLE HINT (e, NOLOCK)
     */
    public QueryHint exampleK2 = TABLE_HINT(
            "e",
            NOLOCK());
    // @formatter:on

    @Test
    public void testExampleK() {
        Assert.assertEquals(exampleK1.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleK1.getExposedObjectName(),"e");
        Assert.assertEquals(exampleK1.getTableHintList().size(),3);
        Assert.assertEquals(exampleK1.getTableHintList().get(0).getType(), TableHint.Type.INDEX);
        Assert.assertEquals(exampleK1.getTableHintList().get(1).getType(), TableHint.Type.NOLOCK);
        Assert.assertEquals(exampleK1.getTableHintList().get(2).getType(), TableHint.Type.FORCESEEK);

        Assert.assertEquals(exampleK2.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(exampleK2.getExposedObjectName(),"e");
        Assert.assertEquals(exampleK2.getTableHintList().size(),1);
        Assert.assertEquals(exampleK2.getTableHintList().get(0).getType(), TableHint.Type.NOLOCK);
    }

    // @formatter:off
    /**
     * RECOMPILE
     */
    public QueryHint exampleL1 = RECOMPILE();

    /**
     * USE HINT ('ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES', 'DISABLE_PARAMETER_SNIFFING')
     */
    public QueryHint exampleL2 = USE_HINT("ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES", "DISABLE_PARAMETER_SNIFFING");
    // @formatter:on

    @Test
    public void testExampleL() {
        Assert.assertEquals(exampleL1.getType(),QueryHint.Type.RECOMPILE);
        Assert.assertEquals(exampleL2.getType(),QueryHint.Type.USE_HINT);
        Assert.assertEquals(exampleL2.getHintNameList().size(),2);
        Assert.assertEquals(exampleL2.getHintNameList().get(0).toString(),"'ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES'");
        Assert.assertEquals(exampleL2.getHintNameList().get(1).toString(),"'DISABLE_PARAMETER_SNIFFING'");
    }

}
