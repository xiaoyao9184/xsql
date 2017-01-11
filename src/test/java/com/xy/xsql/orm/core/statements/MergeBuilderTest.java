package com.xy.xsql.orm.core.statements;

import com.xy.xsql.orm.data.sql.statements.dml.Merge;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/1/11.
 */
public class MergeBuilderTest {

    /**
     * MERGE table USING table2
     * ON <merge_search_condition>
     * WHEN MATCHED
     * THEN <merge_matched>
     * WHEN NOT MATCHED
     * THEN <merge_not_matched>
     * WHEN NOT MATCHED BY SOURCE
     * THEN <merge_matched>
     */
    @Test
    public void testBaseBuild(){
        Merge delete = new MergeBuilder()
                .withTableName("table")
                .withTableSource()
                    .withTable("table2")
                    .out()
                .withMergeSearchCondition()
                    .withSearchCondition()
                        //TODO
                        .out()
                    .out()
                .withMatchedWhenThenList()
                    .withItem()
                        .withMergeMatched()
                            .withSet(true)
                            .withSetList()
                                .withItem()
                                    .withColumnName("c1")
                                    .out()
                                .out()
                            .out()
                        .out()
                    .out()
                .withNotMatchedWhenThenTarget()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumnList()
                            .withItem()
                                .withName("c1")
                                .out()
                            .out()
                        .out()
                    .out()
                .withNotMatchedWhenThenSourceList()
                    .withItem()
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(delete.getTargetTable().toString(),"table");
    }
    /**
     * MERGE INTO table AS t USING table2
     * ON <merge_search_condition>
     * WHEN MATCHED
     * THEN <merge_matched>
     * WHEN NOT MATCHED
     * THEN <merge_not_matched>
     * WHEN NOT MATCHED BY SOURCE
     * THEN <merge_matched>
     */
    @Test
    public void testClauseSearchConditionBuild(){
        Merge delete = new MergeBuilder()
                .withInto(true)
                .withTableName("table")
                .withAs(true)
                .withTableAlias("t")
                .withTableSource()
                    .withTable("table2")
                    .out()
                .withMatchedWhenThenList()
                    .withItem()
                        .withMergeMatched()
                            .withSet(true)
                            .withSetList()
                                .withItem()
                                    .withColumnName("c1")
                                    .out()
                                .out()
                            .out()
                        .out()
                    .out()
                .withNotMatchedWhenThenTarget()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumnList()
                            .withItem()
                                .withName("c1")
                                .out()
                            .out()
                        .out()
                    .out()
                .withNotMatchedWhenThenSourceList()
                    .withItem()
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(delete.getTableAlias().toString(),"t");
    }

}
