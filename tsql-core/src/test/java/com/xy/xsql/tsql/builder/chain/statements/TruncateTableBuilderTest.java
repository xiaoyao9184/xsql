package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.tsql.model.statements.TruncateTable;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$TruncateTable;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class TruncateTableBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/truncate-table-transact-sql#examples
     */

    // @formatter:off
    /**
     * TRUNCATE TABLE HumanResources.JobCandidate
     */
    public TruncateTable exampleA = $TruncateTable(t("HumanResources","JobCandidate"));
    // @formatter:on

    @Test
    public void testExampleA(){
        TruncateTable truncateTable = new TruncateTableBuilder()
                .withTableName(t("HumanResources","JobCandidate"))
                .build();

        assertEquals(truncateTable.getTableName().toString(),"HumanResources.JobCandidate");
    }


    // @formatter:off
    /**
     * TRUNCATE TABLE PartitionTable1
    WITH (PARTITIONS (2, 4, 6 TO 8))
     */
    public TruncateTable exampleB = new TruncateTableBuilder()
            .withTableName(t("PartitionTable1"))
            .withNumber(2)
            .withNumber(4)
            .withRange(6,8)
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        TruncateTable truncateTable = new TruncateTableBuilder()
                .withTableName(t("PartitionTable1"))
                .withNumber(2)
                .withNumber(4)
                .withRange(6,8)
                .build();
        // @formatter:on

        assertEquals(truncateTable.getTableName().toString(),"PartitionTable1");
        assertEquals(truncateTable.getPartitionsList().size(),3);
    }

}
