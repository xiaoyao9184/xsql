package com.xy.xsql.tsql.model.statement.ddl;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.NumberConstant;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operator;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

import static com.xy.xsql.tsql.model.Keywords.k;

/**
 * https://msdn.microsoft.com/en-us/library/ms177570.aspx
 *
 * TRUNCATE TABLE
 [ { database_name .[ schema_name ] . | schema_name . } ]
 table_name
 [ WITH ( PARTITIONS ( { <partition_number_expression> | <range> }
 [ , ...n ] ) ) ]
 *
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class TruncateTable implements Statement {

    //[ { database_name .[ schema_name ] . | schema_name . } ]
    // table_name
    private TableName tableName;
    //[ WITH ( PARTITIONS ( { <partition_number_expression> | <range> }
    //[ , ...n ] ) ) ]
    private List<Partitions> partitionsList;



    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<Partitions> getPartitionsList() {
        return partitionsList;
    }

    public void setPartitionsList(List<Partitions> partitionsList) {
        this.partitionsList = partitionsList;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.TRUNCATE)
                .append(Keywords.TABLE)
                .append(tableName);

        if(partitionsList != null){
            b.append(Keywords.WITH)
                    .append(Other.GROUP_START)
                    .append(Keywords.Key.PARTITIONS)
                    .append(Other.GROUP_START)
                    .append(partitionsList)
                    .append(Other.GROUP_END)
                    .append(Other.GROUP_END);
        }
        return b.build();
    }


    public interface Partitions extends Block {

        /**
         * must override
         * @return
         */
        List<Block> toBlockList();

    }


    public static class Range implements Partitions {
        private PartitionNumberExpression partitionNumberExpressionLeft;
        private PartitionNumberExpression partitionNumberExpressionRight;

        public Range() {
        }

        public Range(PartitionNumberExpression partitionNumberExpression, PartitionNumberExpression partitionNumberExpression1) {
            this.partitionNumberExpressionLeft = partitionNumberExpression;
            this.partitionNumberExpressionRight = partitionNumberExpression1;
        }

        public PartitionNumberExpression getPartitionNumberExpressionLeft() {
            return partitionNumberExpressionLeft;
        }

        public void setPartitionNumberExpressionLeft(PartitionNumberExpression partitionNumberExpressionLeft) {
            this.partitionNumberExpressionLeft = partitionNumberExpressionLeft;
        }

        public Expression getPartitionNumberExpressionRight() {
            return partitionNumberExpressionRight;
        }

        public void setPartitionNumberExpressionRight(PartitionNumberExpression partitionNumberExpressionRight) {
            this.partitionNumberExpressionRight = partitionNumberExpressionRight;
        }

        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(partitionNumberExpressionLeft)
                    .append(Keywords.TO)
                    .append(partitionNumberExpressionRight)
                    .build();
        }
    }

    public static class PartitionNumberExpression extends NumberConstant implements Partitions {

        public PartitionNumberExpression(Number number) {
            super(number);
        }

        @Override
        public List<Block> toBlockList() {
            return null;
        }
    }

}
