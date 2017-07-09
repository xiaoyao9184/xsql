package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.statement.ddl.TruncateTableBuilder.TRUNCATE_TABLE;

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
    public TruncateTable exampleA = TRUNCATE_TABLE(t("HumanResources","JobCandidate"));
    // @formatter:on

    @Test
    public void testExampleA(){
        TruncateTable truncateTable = new TruncateTableBuilder()
                .withTableName(t("HumanResources","JobCandidate"))
                .build();

        Assert.assertEquals(truncateTable.getTableName().toString(),"HumanResources.JobCandidate");
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

        Assert.assertEquals(truncateTable.getTableName().toString(),"PartitionTable1");
        Assert.assertEquals(truncateTable.getPartitionsList().size(),3);
    }

}
