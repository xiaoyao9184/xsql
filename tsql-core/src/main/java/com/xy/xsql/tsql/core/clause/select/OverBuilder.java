package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import com.xy.xsql.tsql.model.clause.select.Over;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * OverBuilder
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
                        target::getPartitionBy,
                        target::setPartitionBy))
                .in(this);
    }

    public OrderByBuilder<OverBuilder<ParentBuilder>> withOrderBy(){
        return new OrderByBuilder<OverBuilder<ParentBuilder>>
                (initSet(Over.OrderBy::new,
                        target::getOrderBy,
                        target::setOrderBy))
                .in(this);
    }



    /*
    Quick set
     */

    /**
     * Quick set partitionBy
     * into PartitionByBuilder get-out
     * @param expressions
     * @return
     */
    public OverBuilder<ParentBuilder> $PartitionBy(Expression... expressions){
        return withPartitionBy()
                .withExpression(expressions)
                .and();
    }

    /**
     * Quick set orderBy
     * into OrderByBuilder get-out
     * @param expressions
     * @return
     */
    public OverBuilder<ParentBuilder> $OrderBy(Expression... expressions){
        OrderBy.Item[] array = Arrays.stream(expressions)
                .map(OrderBy.Item::new)
                .toArray(OrderBy.Item[]::new);
        return withOrderBy()
                .withItems(array)
                .and();
    }

    /**
     * Quick set orderBy
     * into OrderByBuilder get-out
     * @param expressions
     * @return
     */
    public OverBuilder<ParentBuilder> $OrderBy_Desc(Expression... expressions){
        OrderBy.Item[] array = Arrays.stream(expressions)
                .map((expression -> {
                    OrderBy.Item orderByItem = new OrderBy.Item();
                    orderByItem.setOrderByExpression(expression);
                    orderByItem.setUseDesc(true);
                    return orderByItem;
                }))
                .toArray(OrderBy.Item[]::new);
        return withOrderBy()
                .withItems(array)
                .and();
    }




    /**
     * PartitionByBuilder
     * @param <ParentBuilder>
     */
    public static class PartitionByBuilder<ParentBuilder>
            extends CodeTreeBuilder<PartitionByBuilder<ParentBuilder>,ParentBuilder,Over.PartitionBy> {

        public PartitionByBuilder(Over.PartitionBy partitionBy) {
            super(partitionBy);
        }

        public PartitionByBuilder<ParentBuilder> withExpression(Expression... expressions){
            if(CheckUtil.isNullOrEmpty(expressions)){
                return this;
            }
            initAdd(Arrays.asList(expressions),
                    target::getValueExpressionList,
                    target::setValueExpressionList);
            return this;
        }
    }

    /**
     * OrderByBuilder
     * @param <ParentBuilder>
     */
    public static class OrderByBuilder<ParentBuilder>
            extends CodeTreeBuilder<OrderByBuilder<ParentBuilder>,ParentBuilder,Over.OrderBy> {

        public OrderByBuilder(Over.OrderBy orderBy) {
            super(orderBy);
        }

        public com.xy.xsql.tsql.core.clause.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>> withItems(){
            return new com.xy.xsql.tsql.core.clause.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                    (initNew(OrderBy.Item::new,
                            target::getItems,
                            target::setItems))
                    .in(this);
        }

        public OrderByBuilder<ParentBuilder> withItems(OrderBy.Item... orderByItems){
            if(CheckUtil.isNullOrEmpty(orderByItems)){
                return this;
            }
            initAdd(Arrays.asList(orderByItems),
                            target::getItems,
                            target::setItems);
            return this;
        }
    }

}
