package com.xy.xsql.tsql.model.statement.ddl;

import com.xy.xsql.tsql.model.datatype.NumberConstant;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.Statement;

import java.util.List;

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


    public interface Partitions {

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

    }

    public static class PartitionNumberExpression extends NumberConstant implements Partitions {

        public PartitionNumberExpression(Number number) {
            super(number);
        }

    }

}
