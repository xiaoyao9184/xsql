package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.QueryHint;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.queries.hints.QueryHintBuilder.*;
import static com.xy.xsql.tsql.builder.chain.queries.hints.QueryHintBuilder.OptimizeForBuilder.*;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintBuilder.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class QueryHintBuilderTest {

    // @formatter:off
    /**
     * MERGE JOIN
     */
    public QueryHint exampleA = $MergeJoin();
    // @formatter:on

    @Test
    public void testExampleA() {
        QueryHint queryHint = new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MERGE_JOIN)
                .build();

        assertEquals(queryHint.getType(),QueryHint.Type.MERGE_JOIN);
    }


    // @formatter:off
    /**
     * OPTIMIZE FOR (@city_name = 'Seattle', @postal_code UNKNOWN)
     */
    public QueryHint exampleB = $OptimizeFor(
                $OptimizeForItem("city_name",false,"Seattle"),
                $OptimizeForItem("postal_code",true,null));
    // @formatter:on

    @Test
    public void testExampleB() {
        assertEquals(exampleB.getType(),QueryHint.Type.OPTIMIZE_FOR);
        assertEquals(exampleB.getOptimizeFor().size(),2);
    }


    // @formatter:off
    /**
     * MAXRECURSION 2
     */
    public QueryHint exampleC = $Maxrecursion(2);
    // @formatter:on

    @Test
    public void testExampleC() {
        assertEquals(exampleC.getType(),QueryHint.Type.MAXRECURSION);
        assertEquals(exampleC.getNumber().intValue(),2);
    }


    // @formatter:off
    /**
     * MERGE UNION
     */
    public QueryHint exampleD = $MergeUnion();
    // @formatter:on

    @Test
    public void testExampleD() {
        assertEquals(exampleD.getType(),QueryHint.Type.MERGE_UNION);
    }


    // @formatter:off
    /**
     * HASH GROUP
     */
    public QueryHint exampleE1 = $HashGroup();

    /**
     * FAST 10
     */
    public QueryHint exampleE2 = $Fast(10);
    // @formatter:on

    @Test
    public void testExampleE() {
        assertEquals(exampleE1.getType(),QueryHint.Type.HASH_GROUP);
        assertEquals(exampleE2.getType(),QueryHint.Type.FAST);
        assertEquals(exampleE2.getNumberRows().intValue(),10);
    }


    // @formatter:off
    /**
     * MAXDOP 2
     */
    public QueryHint exampleF = $Maxdop(2);
    // @formatter:on

    @Test
    public void testExampleF() {
        assertEquals(exampleF.getType(),QueryHint.Type.MAXDOP);
        assertEquals(exampleF.getNumberOfProcessors().intValue(),2);
    }


    // @formatter:off
    /**
     * TABLE HINT(e, INDEX (IX_Employee_ManagerID))
     */
    public QueryHint exampleG1 = $TableHint(
                "e",
                $Index("IX_Employee_ManagerID"));

    /**
     * TABLE HINT(e, INDEX(PK_Employee_EmployeeID, IX_Employee_ManagerID))
     */
    public QueryHint exampleG2 = $TableHint(
            "e",
            $Index("PK_Employee_EmployeeID","IX_Employee_ManagerID"));
    // @formatter:on

    @Test
    public void testExampleG() {
        assertEquals(exampleG1.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleG1.getTableHintList().size(),1);
        assertEquals(exampleG1.getTableHintList().get(0).getIndex_value().get(0).toString(),"IX_Employee_ManagerID");

        assertEquals(exampleG2.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleG2.getTableHintList().size(),1);
        assertEquals(exampleG2.getTableHintList().get(0).getIndex_value().get(0).toString(),"PK_Employee_EmployeeID");
        assertEquals(exampleG2.getTableHintList().get(0).getIndex_value().get(1).toString(),"IX_Employee_ManagerID");
    }


    // @formatter:off
    /**
     * TABLE HINT( HumanResources.Employee, FORCESEEK)
     */
    public QueryHint exampleH = $TableHint(
                "HumanResources.Employee",
                $Forceseek());
    // @formatter:on

    @Test
    public void testExampleH() {
        assertEquals(exampleH.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleH.getTableHintList().size(),1);
        assertEquals(exampleH.getTableHintList().get(0).getType(), TableHint.Type.FORCESEEK);
    }


    // @formatter:off
    /**
     * TABLE HINT (e, INDEX( IX_Employee_ManagerID))
     */
    public QueryHint exampleI1 = $TableHint(
                "e",
                $Index("IX_Employee_ManagerID"));

    /**
     * TABLE HINT (c, FORCESEEK)
     */
    public QueryHint exampleI2 = $TableHint(
            "c",
            $Forceseek());
    // @formatter:on

    @Test
    public void testExampleI() {
        assertEquals(exampleI1.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleI1.getTableHintList().size(),1);
        assertEquals(exampleI1.getTableHintList().get(0).getType(), TableHint.Type.INDEX);
        assertEquals(exampleI1.getTableHintList().get(0).getIndex_value().get(0).toString(),"IX_Employee_ManagerID");

        assertEquals(exampleI2.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleI2.getTableHintList().size(),1);
        assertEquals(exampleI2.getTableHintList().get(0).getType(), TableHint.Type.FORCESEEK);
    }

    // @formatter:off
    /**
     * TABLE HINT(e)
     */
    public QueryHint exampleJ = $TableHint("e");
    // @formatter:on

    @Test
    public void testExampleJ() {
        assertEquals(exampleJ.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleJ.getExposedObjectName(),"e");
        assertNull(exampleJ.getTableHintList());
    }

    // @formatter:off
    /**
     * TABLE HINT (e, INDEX(IX_Employee_ManagerID), NOLOCK, FORCESEEK)
     */
    public QueryHint exampleK1 = $TableHint(
                "e",
                $Index("IX_Employee_ManagerID"),
                $Nolock(),
                $Forceseek());

    /**
     * TABLE HINT (e, NOLOCK)
     */
    public QueryHint exampleK2 = $TableHint(
            "e",
            $Nolock());
    // @formatter:on

    @Test
    public void testExampleK() {
        assertEquals(exampleK1.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleK1.getExposedObjectName(),"e");
        assertEquals(exampleK1.getTableHintList().size(),3);
        assertEquals(exampleK1.getTableHintList().get(0).getType(), TableHint.Type.INDEX);
        assertEquals(exampleK1.getTableHintList().get(1).getType(), TableHint.Type.NOLOCK);
        assertEquals(exampleK1.getTableHintList().get(2).getType(), TableHint.Type.FORCESEEK);

        assertEquals(exampleK2.getType(),QueryHint.Type.TABLE_HINT);
        assertEquals(exampleK2.getExposedObjectName(),"e");
        assertEquals(exampleK2.getTableHintList().size(),1);
        assertEquals(exampleK2.getTableHintList().get(0).getType(), TableHint.Type.NOLOCK);
    }

    // @formatter:off
    /**
     * RECOMPILE
     */
    public QueryHint exampleL1 = $Recompile();

    /**
     * USE HINT ('ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES', 'DISABLE_PARAMETER_SNIFFING')
     */
    public QueryHint exampleL2 = $UseHint("ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES", "DISABLE_PARAMETER_SNIFFING");
    // @formatter:on

    @Test
    public void testExampleL() {
        assertEquals(exampleL1.getType(),QueryHint.Type.RECOMPILE);
        assertEquals(exampleL2.getType(),QueryHint.Type.USE_HINT);
        assertEquals(exampleL2.getHintNameList().size(),2);
        assertEquals(exampleL2.getHintNameList().get(0).getString(),"ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES");
        assertEquals(exampleL2.getHintNameList().get(1).getString(),"DISABLE_PARAMETER_SNIFFING");
    }

}
