package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.SubBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import com.xy.xsql.tsql.model.clause.select.Over;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/1/17.
 */
public class OverBuilder<Done>
        extends SubBuilder<OverBuilder<Done>,Void,Done> {

    private Over over;

    public OverBuilder(Over over) {
        this.over = over;
    }


    public PartitionByBuilder<OverBuilder<Done>> withPartitionBy(){
        Over.PartitionBy partitionBy = new Over.PartitionBy();
        this.over.setPartitionBy(partitionBy);
        return new PartitionByBuilder<OverBuilder<Done>>(partitionBy)
                .in(this);
    }

    public OrderByBuilder<OverBuilder<Done>> withOrderBy(){
        Over.OrderBy orderBy = new Over.OrderBy();
        this.over.setOrderBy(orderBy);
        return new OrderByBuilder<OverBuilder<Done>>(orderBy)
                .in(this);
    }




    /**
     *
     * @param <Done>
     */
    public static class PartitionByBuilder<Done>
            extends SubBuilder<PartitionByBuilder<Done>,Void,Done> {

        private Over.PartitionBy partitionBy;

        public PartitionByBuilder(Over.PartitionBy partitionBy) {
            this.partitionBy = partitionBy;
        }

        public PartitionByBuilder<Done> withExpression(Expression expression){
            if(this.partitionBy.getValueExpressionList() == null){
                this.partitionBy.setValueExpressionList(new ArrayList<Expression>());
            }
            this.partitionBy.getValueExpressionList().add(expression);
            return this;
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class OrderByBuilder<Done>
            extends SubBuilder<OrderByBuilder<Done>,Void,Done> {

        private Over.OrderBy orderBy;

        public OrderByBuilder(Over.OrderBy orderBy) {
            this.orderBy = orderBy;
        }

        public com.xy.xsql.tsql.core.clause.select.OrderByBuilder.OrderByListBuilder<OrderByBuilder<Done>> withItems(){
            List<OrderBy.OrderByItem> items = new ArrayList<>();
            if(this.orderBy.getItems() == null){
                this.orderBy.setItems(new ArrayList<OrderBy.OrderByItem>());
            }
            this.orderBy.setItems(items);
            return new com.xy.xsql.tsql.core.clause.select.OrderByBuilder.OrderByListBuilder<OrderByBuilder<Done>>(items)
                    .in(this);
        }
    }
}
