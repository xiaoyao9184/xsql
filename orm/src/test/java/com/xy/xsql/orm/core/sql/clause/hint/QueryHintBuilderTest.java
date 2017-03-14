package com.xy.xsql.orm.core.sql.clause.hint;

import com.xy.xsql.orm.core.sql.clause.hints.QueryHintBuilder;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.clause.hints.QueryHintBuilder.*;
import static com.xy.xsql.orm.core.sql.clause.hints.QueryHintBuilder.OptimizeForBuilder.OPTIMIZE_FOR_Item;
import static com.xy.xsql.orm.core.sql.clause.hints.TableHintBuilder.FORCESEEK;
import static com.xy.xsql.orm.core.sql.clause.hints.TableHintBuilder.INDEX;
import static com.xy.xsql.orm.core.sql.clause.hints.TableHintBuilder.NOLOCK;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class QueryHintBuilderTest {

    /**
     * OPTION (MERGE JOIN);
     */
    @Test
    public void testExampleA() {
        new QueryHintBuilder()
                .withType(QueryHint.Type.MERGE_JOIN)
                .build();

        QueryHint queryHint = MERGE_JOIN();

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.MERGE_JOIN);
    }


    /**
     * OPTION ( OPTIMIZE FOR (@city_name = 'Seattle', @postal_code UNKNOWN) );
     */
    @Test
    public void testExampleB() {
        QueryHint queryHint = OPTIMIZE_FOR(
                OPTIMIZE_FOR_Item("city_name",false,"Seattle"),
                OPTIMIZE_FOR_Item("postal_code",true,null));

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.OPTIMIZE_FOR);
        Assert.assertEquals(queryHint.getOptimizeFor().size(),2);
    }

    /**
     * OPTION (MAXRECURSION 2);
     */
    @Test
    public void testExampleC() {
        QueryHint queryHint = MAXRECURSION(2);

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.MAXRECURSION);
        Assert.assertEquals(queryHint.getNumber().intValue(),2);
    }

    /**
     * OPTION (MERGE UNION);
     */
    @Test
    public void testExampleD() {
        QueryHint queryHint = MERGE_UNION();

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.MERGE_UNION);
    }

    /**
     * OPTION (HASH GROUP, FAST 10);
     */
    @Test
    public void testExampleE() {
        QueryHint queryHint1 = HASH_GROUP();
        QueryHint queryHint2 = FAST(10);

        Assert.assertEquals(queryHint1.getType(),QueryHint.Type.HASH_GROUP);
        Assert.assertEquals(queryHint2.getType(),QueryHint.Type.FAST);
        Assert.assertEquals(queryHint2.getNumberRows().intValue(),10);
    }

    /**
     * OPTION (MAXDOP 2);
     */
    @Test
    public void testExampleF() {
        QueryHint queryHint = MAXDOP(2);

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.MAXDOP);
        Assert.assertEquals(queryHint.getNumberOfProcessors().intValue(),2);
    }

    /**
     * OPTION (TABLE HINT(e, INDEX (IX_Employee_ManagerID)))
     * OPTION (TABLE HINT(e, INDEX(PK_Employee_EmployeeID, IX_Employee_ManagerID)))
     */
    @Test
    public void testExampleG() {
        QueryHint queryHint = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"));

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint.getTableHintList().size(),1);
        Assert.assertEquals(queryHint.getTableHintList().get(0).getIndex_value().get(0).toString(),"IX_Employee_ManagerID");


        QueryHint queryHint2 = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID","IX_Employee_ManagerID"));

        Assert.assertEquals(queryHint2.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint2.getTableHintList().size(),1);
        Assert.assertEquals(queryHint2.getTableHintList().get(0).getIndex_value().get(0).toString(),"IX_Employee_ManagerID");
        Assert.assertEquals(queryHint2.getTableHintList().get(0).getIndex_value().get(1).toString(),"IX_Employee_ManagerID");
    }

    /**
     * OPTION (TABLE HINT( HumanResources.Employee, FORCESEEK))
     */
    @Test
    public void testExampleH() {
        QueryHint queryHint = TABLE_HINT(
                "HumanResources.Employee",
                FORCESEEK());

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint.getTableHintList().size(),1);
        Assert.assertEquals(queryHint.getTableHintList().get(0).getType(), TableHint.Type.FORCESEEK);
    }

    /**
     * OPTION (TABLE HINT (e, INDEX( IX_Employee_ManagerID))
     , TABLE HINT (c, FORCESEEK))
     */
    @Test
    public void testExampleI() {
        QueryHint queryHint = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"));
        QueryHint queryHint2 = TABLE_HINT(
                "c",
                FORCESEEK());

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint.getTableHintList().size(),1);
        Assert.assertEquals(queryHint.getTableHintList().get(0).getType(), TableHint.Type.INDEX);
        Assert.assertEquals(queryHint.getTableHintList().get(0).getIndex_value().get(0).toString(),"IX_Employee_ManagerID");

        Assert.assertEquals(queryHint2.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint2.getTableHintList().size(),1);
        Assert.assertEquals(queryHint2.getTableHintList().get(0).getType(), TableHint.Type.FORCESEEK);
    }

    /**
     * OPTION (TABLE HINT(e))
     */
    @Test
    public void testExampleJ() {
        QueryHint queryHint = TABLE_HINT(
                "e");

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint.getExposedObjectName(),"e");
        Assert.assertEquals(queryHint.getTableHintList().size(),0);
    }

    /**
     * OPTION (TABLE HINT (e, INDEX(IX_Employee_ManagerID), NOLOCK, FORCESEEK))
     * OPTION (TABLE HINT (e, NOLOCK))
     */
    @Test
    public void testExampleK() {
        QueryHint queryHint = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"),
                NOLOCK(),
                FORCESEEK());

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint.getExposedObjectName(),"e");
        Assert.assertEquals(queryHint.getTableHintList().size(),3);
        Assert.assertEquals(queryHint.getTableHintList().get(0).getType(), TableHint.Type.INDEX);
        Assert.assertEquals(queryHint.getTableHintList().get(1).getType(), TableHint.Type.NOLOCK);
        Assert.assertEquals(queryHint.getTableHintList().get(2).getType(), TableHint.Type.FORCESEEK);

        QueryHint queryHint2 = TABLE_HINT(
                "e",
                NOLOCK());

        Assert.assertEquals(queryHint2.getType(),QueryHint.Type.TABLE_HINT);
        Assert.assertEquals(queryHint2.getExposedObjectName(),"e");
        Assert.assertEquals(queryHint2.getTableHintList().size(),1);
        Assert.assertEquals(queryHint2.getTableHintList().get(0).getType(), TableHint.Type.NOLOCK);
    }

    /**
     * OPTION (RECOMPILE, USE HINT ('ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES', 'DISABLE_PARAMETER_SNIFFING'));
     */
    @Test
    public void testExampleL() {
        QueryHint queryHint = RECOMPILE();
        QueryHint queryHint2 = USE_HINT("ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES", "DISABLE_PARAMETER_SNIFFING");

        Assert.assertEquals(queryHint.getType(),QueryHint.Type.RECOMPILE);
        Assert.assertEquals(queryHint2.getType(),QueryHint.Type.USE_HINT);
        Assert.assertEquals(queryHint2.getHintNameList().size(),2);
        Assert.assertEquals(queryHint2.getHintNameList().get(0).toString(),"ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES");
        Assert.assertEquals(queryHint2.getHintNameList().get(1).toString(),"DISABLE_PARAMETER_SNIFFING");
    }
}
