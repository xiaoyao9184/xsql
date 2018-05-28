package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.statements.TruncateTable;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.truncate_table.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/5/28.
 */
public class truncate_tableTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/truncate-table-transact-sql#examples
     */

    // @formatter:off
    /**
     * TRUNCATE TABLE HumanResources.JobCandidate
     */
    public TruncateTable exampleA = TRUNCATE_TABLE(
            t("HumanResources","JobCandidate")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTableName().toString(),"HumanResources.JobCandidate");
    }


    // @formatter:off
    /**
     * TRUNCATE TABLE PartitionTable1
    WITH (PARTITIONS (2, 4, 6 TO 8))
     */
    public TruncateTable exampleB = TRUNCATE_TABLE(
            t("PartitionTable1")
            ,WITH(
                    PARTITIONS(2)
                            .$$(4)
                            .$$(6,TO(8)))
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTableName().toString(),"PartitionTable1");
        assertEquals(exampleB.getPartitionsList().size(),3);
    }

}