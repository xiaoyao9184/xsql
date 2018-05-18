package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class OrderByBuilder<ParentBuilder>
        extends CodeTreeBuilder<OrderByBuilder<ParentBuilder>,ParentBuilder,OrderBy> {

    public OrderByBuilder(OrderBy orderBy) {
        super(orderBy);
    }

    public OrderByBuilder() {
        super(new OrderBy());
    }


    public OrderByItemBuilder<OrderByBuilder<ParentBuilder>> withItem(){
        return new OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                (initNew(OrderBy.Item::new,
                        target::getItems,
                        target::setItems))
                .in(this);
    }

    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> withOffsetFetch(){
        return new OffsetFetchBuilder<OrderByBuilder<ParentBuilder>>
                (initSet(OrderBy.OffsetFetch::new,
                        target::getOffsetFetch,
                        target::setOffsetFetch))
                .in(this);
    }

    public OrderByBuilder<ParentBuilder> withOffsetFetch(OrderBy.OffsetFetch offsetFetch){
        this.target.setOffsetFetch(offsetFetch);
        return this;
    }

    /*
    Quick set
     */

    /**
     * Quick set
     * @param expression
     * @return
     */
    public OrderByBuilder<ParentBuilder> $(Expression expression) {
        return withItem()
                .withExpression(expression)
                .and();
    }

    /**
     * Get last Item and set
     * @return
     */
    private OrderBy.Item getLastItem(){
        if(target.getItems().size() > 0){
            int i = target.getItems().size() - 1;
            return target.getItems().get(i);
        }
        return null;
    }

    /**
     * Quick set
     * @return
     */
    public OrderByBuilder<ParentBuilder> $Desc() {
        OrderBy.Item last = getLastItem();
        return new OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                (last)
                .in(this)
                .withDesc()
                .and();
    }

    /**
     * Quick set
     * @return
     */
    public OrderByBuilder<ParentBuilder> $Asc() {
        OrderBy.Item last = getLastItem();
        return new OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                (last)
                .in(this)
                .withAsc()
                .and();
    }

    /**
     * Quick into OffsetFetchBuilder
     * @param integerConstant
     * @return
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Offset(Integer integerConstant) {
        return withOffsetFetch()
                .withIntegerConstant(integerConstant);
    }

    /**
     * Quick into OffsetFetchBuilder
     * @param expression
     * @return
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Offset(Expression expression) {
        return withOffsetFetch()
                .withOffsetRowCountExpression(expression);
    }

    /**
     * Quick into OffsetFetchBuilder
     * @param integerConstant
     * @return
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Fetch_First(Integer integerConstant) {
        return withOffsetFetch()
                .withUseFetchFirst()
                .withFetchIntegerConstant(integerConstant);
    }

    /**
     * Quick into OffsetFetchBuilder
     * @param expression
     * @return
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Fetch_First(Expression expression) {
        return withOffsetFetch()
                .withUseFetchFirst()
                .withFetchOffsetRowCountExpression(expression);
    }

    /**
     * Quick into OffsetFetchBuilder
     * @param integerConstant
     * @return
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Fetch_Next(Integer integerConstant) {
        return withOffsetFetch()
                .withUseFetchNext()
                .withFetchIntegerConstant(integerConstant);
    }

    /**
     * Quick into OffsetFetchBuilder
     * @param expression
     * @return
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Fetch_Next(Expression expression) {
        return withOffsetFetch()
                .withUseFetchNext()
                .withFetchOffsetRowCountExpression(expression);
    }


    /**
     * OrderByItemBuilder
     * @param <ParentBuilder>
     */
    public static class OrderByItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<OrderByItemBuilder<ParentBuilder>,ParentBuilder,OrderBy.Item> {


        public OrderByItemBuilder(OrderBy.Item orderByItem) {
            super(orderByItem);
        }

        public OrderByItemBuilder<ParentBuilder> withAsc(){
            target.setUseAsc(true);
            return this;
        }

        @Deprecated
        public OrderByItemBuilder<ParentBuilder> withAsc(boolean useAsc){
            target.setUseAsc(useAsc);
            return this;
        }

        public OrderByItemBuilder<ParentBuilder> withDesc(){
            target.setUseDesc(true);
            return this;
        }

        @Deprecated
        public OrderByItemBuilder<ParentBuilder> withDesc(boolean useDesc){
            target.setUseDesc(useDesc);
            return this;
        }

        public OrderByItemBuilder<ParentBuilder> withExpression(Expression expression){
            target.setOrderByExpression(expression);
            return this;
        }

    }

    /**
     * OffsetFetchBuilder
     * @param <ParentBuilder>
     */
    public static class OffsetFetchBuilder<ParentBuilder>
            extends CodeTreeBuilder<OffsetFetchBuilder<ParentBuilder>,ParentBuilder,OrderBy.OffsetFetch> {


        public OffsetFetchBuilder(OrderBy.OffsetFetch offsetFetch) {
            super(offsetFetch);
        }



        public OffsetFetchBuilder<ParentBuilder> withIntegerConstant(Integer integerConstant) {
            target.setIntegerConstant(integerConstant);
            return this;
        }

        public OffsetFetchBuilder<ParentBuilder> withOffsetRowCountExpression(Expression offsetRowCountExpression) {
            target.setOffsetRowCountExpression(offsetRowCountExpression);
            return this;
        }

        public OffsetFetchBuilder<ParentBuilder> withUseRows() {
            target.setUseRows(true);
            return this;
        }

        public OffsetFetchBuilder<ParentBuilder> withUseFetchFirst() {
            target.setUseFetch(true);
            target.setUseFetchFirst(true);
            return this;
        }

        public OffsetFetchBuilder<ParentBuilder> withUseFetchNext() {
            target.setUseFetch(true);
            return this;
        }

        public OffsetFetchBuilder<ParentBuilder> withFetchIntegerConstant(Integer fetchIntegerConstant) {
            target.setFetchIntegerConstant(fetchIntegerConstant);
            return this;
        }

        public OffsetFetchBuilder<ParentBuilder> withFetchOffsetRowCountExpression(Expression fetchOffsetRowCountExpression) {
            target.setFetchOffsetRowCountExpression(fetchOffsetRowCountExpression);
            return this;
        }

        public OffsetFetchBuilder<ParentBuilder> withUseFetchRows() {
            target.setUseFetchRows(true);
            return this;
        }

        public void withFetch(OrderBy.OffsetFetch fetch) {
            target.setUseFetch(true);
            target.setUseFetchFirst(fetch.isUseFetchFirst());
            target.setUseFetchRows(fetch.isUseFetchRows());
            target.setFetchIntegerConstant(fetch.getIntegerConstant());
            target.setFetchOffsetRowCountExpression(fetch.getFetchOffsetRowCountExpression());
        }


        /**
         * Quick set
         * And get-out
         * @return
         */
        public ParentBuilder $Row() {
            if(target.getFetchOffsetRowCountExpression() != null){
                target.setUseFetchRows(false);
                return this.and();
            }
            target.setUseRows(false);
            return this.and();
        }

        /**
         * Quick set
         * And get-out
         * @return
         */
        public ParentBuilder $Rows() {
            if(target.getFetchOffsetRowCountExpression() != null){
                return withUseFetchRows()
                        .and();
            }
            return withUseRows()
                    .and();
        }
    }
}
