package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import com.xy.xsql.tsql.model.clause.select.Over;
import com.xy.xsql.tsql.model.expression.Expression;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/1/17.
 */
public class OverBuilder<ParentBuilder>
        extends CodeTreeBuilder<OverBuilder<ParentBuilder>,ParentBuilder,Over> {

    public OverBuilder() {
        super(new Over());
    }

    public OverBuilder(Over over) {
        super(over);
    }


    public PartitionByBuilder<OverBuilder<ParentBuilder>> withPartitionBy(){
        return new PartitionByBuilder<OverBuilder<ParentBuilder>>
                (initSet(Over.PartitionBy::new,
                        tar::getPartitionBy,
                        tar::setPartitionBy))
                .in(this);
    }

    public OrderByBuilder<OverBuilder<ParentBuilder>> withOrderBy(){
        return new OrderByBuilder<OverBuilder<ParentBuilder>>
                (initSet(Over.OrderBy::new,
                        tar::getOrderBy,
                        tar::setOrderBy))
                .in(this);
    }




    /**
     *
     * @param <ParentBuilder>
     */
    public static class PartitionByBuilder<ParentBuilder>
            extends CodeTreeBuilder<PartitionByBuilder<ParentBuilder>,ParentBuilder,Over.PartitionBy> {

        public PartitionByBuilder(Over.PartitionBy partitionBy) {
            super(partitionBy);
        }

        public PartitionByBuilder<ParentBuilder> withExpression(Expression expression){
            initAdd(expression,
                    tar::getValueExpressionList,
                    tar::setValueExpressionList);
            return this;
        }
    }

    /**
     *
     * @param <ParentBuilder>
     */
    public static class OrderByBuilder<ParentBuilder>
            extends CodeTreeBuilder<OrderByBuilder<ParentBuilder>,ParentBuilder,Over.OrderBy> {

        public OrderByBuilder(Over.OrderBy orderBy) {
            super(orderBy);
        }

        public com.xy.xsql.tsql.core.clause.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>> withItems(){
            return new com.xy.xsql.tsql.core.clause.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                    (initNew(OrderBy.OrderByItem::new,
                            tar::getItems,
                            tar::setItems))
                    .in(this);
        }

        public OrderByBuilder<ParentBuilder> withItems(OrderBy.OrderByItem orderByItem){
            initAdd(orderByItem,
                            tar::getItems,
                            tar::setItems);
            return this;
        }
    }
}
