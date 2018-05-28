package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.TruncateTable;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_TRUNCATE_TABLE;

import static com.xy.xsql.tsql.builder.chain.statements.TruncateTableBuilder.e_pn;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface truncate_table {

    //

    static b_TRUNCATE_TABLE TRUNCATE_TABLE(
            TableName table_name,
            b_TRUNCATE_TABLE.b_WITH with){
        return new b_TRUNCATE_TABLE(){
            {
                withTableName(table_name);
                withPartitions(with.build());
            }
        };
    }
    static b_TRUNCATE_TABLE TRUNCATE_TABLE(
            TableName table_name){
        return new b_TRUNCATE_TABLE(){
            {
                withTableName(table_name);
            }
        };
    }

    //

    static b_TRUNCATE_TABLE.b_WITH WITH(
            b_TRUNCATE_TABLE.b_PARTITIONS partitions){
        return new b_TRUNCATE_TABLE.b_WITH(){
            {
                this.target = partitions.build();
            }
        };
    }

    //

    static b_TRUNCATE_TABLE.b_PARTITIONS PARTITIONS(
            Integer partition_number_expression){
        return new b_TRUNCATE_TABLE.b_PARTITIONS(){
            {
                withItem(e_pn(partition_number_expression));
            }
        };
    }
    static b_TRUNCATE_TABLE.b_PARTITIONS PARTITIONS(
            Integer partition_number_expression,
            b_TRUNCATE_TABLE.b_PARTITIONS.b_TO to){
        return new b_TRUNCATE_TABLE.b_PARTITIONS(){
            {
                TruncateTable.Range range = new TruncateTable.Range();
                range.setPartitionNumberExpressionLeft(e_pn(partition_number_expression));
                range.setPartitionNumberExpressionRight(to.build());
                withItem(range);
            }
        };
    }

    //

    static b_TRUNCATE_TABLE.b_PARTITIONS.b_TO TO(
            Integer partition_number_expression){
        return new b_TRUNCATE_TABLE.b_PARTITIONS.b_TO(e_pn(partition_number_expression));
    }

}
