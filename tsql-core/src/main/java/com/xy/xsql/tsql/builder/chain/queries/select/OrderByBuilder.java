package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.select.OrderBy;

import static com.xy.xsql.core.ListBuilder.getLastItem;
import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * OrderByBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue","unused"})
public class OrderByBuilder<ParentBuilder>
        extends ParentHoldBuilder<OrderByBuilder<ParentBuilder>,ParentBuilder,OrderBy> {

    public OrderByBuilder(OrderBy target) {
        super(target);
    }

    public OrderByBuilder() {
        super(new OrderBy());
    }


    /**
     * set
     * @return THIS
     */
    public OrderByItemBuilder<OrderByBuilder<ParentBuilder>> withItem(){
        return new OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                (list(target::getItems, target::setItems)
                        .addNew(OrderBy.Item::new))
                .in(this);
    }

    /**
     * set
     * @param offsetFetch OffsetFetch
     * @return THIS
     */
    public OrderByBuilder<ParentBuilder> withOffsetFetch(OrderBy.OffsetFetch offsetFetch){
        this.target.setOffsetFetch(offsetFetch);
        return this;
    }

    /**
     * in
     * @return OffsetFetchBuilder
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> withOffsetFetch(){
        return new OffsetFetchBuilder<OrderByBuilder<ParentBuilder>>
                (object(target::getOffsetFetch, target::setOffsetFetch)
                        .init(OrderBy.OffsetFetch::new))
                .in(this);
    }




    /*
    Quick set
     */

    /**
     * Quick set
     * @param expression Expression
     * @return THIS
     */
    public OrderByBuilder<ParentBuilder> $(Expression expression) {
        return withItem()
                .withExpression(expression)
                .and();
    }

    /**
     * Quick set
     * @return THIS
     */
    public OrderByBuilder<ParentBuilder> $Desc() {
        OrderBy.Item last = getLastItem(target.getItems());
        return new OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                (last)
                .in(this)
                .withDesc()
                .and();
    }

    /**
     * Quick set
     * @return THIS
     */
    public OrderByBuilder<ParentBuilder> $Asc() {
        OrderBy.Item last = getLastItem(target.getItems());
        return new OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                (last)
                .in(this)
                .withAsc()
                .and();
    }

    /**
     * Quick in OffsetFetchBuilder
     * @param integerConstant integer constant
     * @return OffsetFetchBuilder
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Offset(Integer integerConstant) {
        return withOffsetFetch()
                .withIntegerConstant(integerConstant);
    }

    /**
     * Quick in OffsetFetchBuilder
     * @param expression Expression
     * @return OffsetFetchBuilder
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $Offset(Expression expression) {
        return withOffsetFetch()
                .withOffsetRowCountExpression(expression);
    }

    /**
     * Quick in OffsetFetchBuilder
     * @param integerConstant integer constant
     * @return OffsetFetchBuilder
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $FetchFirst(Integer integerConstant) {
        return withOffsetFetch()
                .withUseFetchFirst()
                .withFetchIntegerConstant(integerConstant);
    }

    /**
     * Quick in OffsetFetchBuilder
     * @param expression Expression
     * @return OffsetFetchBuilder
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $FetchFirst(Expression expression) {
        return withOffsetFetch()
                .withUseFetchFirst()
                .withFetchOffsetRowCountExpression(expression);
    }

    /**
     * Quick in OffsetFetchBuilder
     * @param integerConstant integer constant
     * @return OffsetFetchBuilder
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $FetchNext(Integer integerConstant) {
        return withOffsetFetch()
                .withUseFetchNext()
                .withFetchIntegerConstant(integerConstant);
    }

    /**
     * Quick in OffsetFetchBuilder
     * @param expression Expression
     * @return OffsetFetchBuilder
     */
    public OffsetFetchBuilder<OrderByBuilder<ParentBuilder>> $FetchNext(Expression expression) {
        return withOffsetFetch()
                .withUseFetchNext()
                .withFetchOffsetRowCountExpression(expression);
    }


    /**
     * OrderByItemBuilder
     * @param <ParentBuilder>
     */
    public static class OrderByItemBuilder<ParentBuilder>
            extends ParentHoldBuilder<OrderByItemBuilder<ParentBuilder>,ParentBuilder,OrderBy.Item> {

        public OrderByItemBuilder() {
            super(new OrderBy.Item());
        }

        public OrderByItemBuilder(OrderBy.Item target) {
            super(target);
        }

        /**
         * set
         * @return THIS
         */
        public OrderByItemBuilder<ParentBuilder> withAsc(){
            target.setUseAsc(true);
            return this;
        }

        /**
         * set
         * @param useAsc asc
         * @return THIS
         */
        @Deprecated
        public OrderByItemBuilder<ParentBuilder> withAsc(boolean useAsc){
            target.setUseAsc(useAsc);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public OrderByItemBuilder<ParentBuilder> withDesc(){
            target.setUseDesc(true);
            return this;
        }

        /**
         * set
         * @param useDesc desc
         * @return THIS
         */
        @Deprecated
        public OrderByItemBuilder<ParentBuilder> withDesc(boolean useDesc){
            target.setUseDesc(useDesc);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
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
            extends ParentHoldBuilder<OffsetFetchBuilder<ParentBuilder>,ParentBuilder,OrderBy.OffsetFetch> {

        public OffsetFetchBuilder() {
            super(new OrderBy.OffsetFetch());
        }

        public OffsetFetchBuilder(OrderBy.OffsetFetch target) {
            super(target);
        }


        /**
         * set
         * @param integerConstant integer constant
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withIntegerConstant(Integer integerConstant) {
            target.setIntegerConstant(integerConstant);
            return this;
        }

        /**
         * set
         * @param offsetRowCountExpression Expression
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withOffsetRowCountExpression(Expression offsetRowCountExpression) {
            target.setOffsetRowCountExpression(offsetRowCountExpression);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withUseRows() {
            target.setUseRows(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withUseFetchFirst() {
            target.setUseFetch(true);
            target.setUseFetchFirst(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withUseFetchNext() {
            target.setUseFetch(true);
            return this;
        }

        /**
         * set
         * @param fetchIntegerConstant integer constant
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withFetchIntegerConstant(Integer fetchIntegerConstant) {
            target.setFetchIntegerConstant(fetchIntegerConstant);
            return this;
        }

        /**
         * set
         * @param fetchOffsetRowCountExpression Expression
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withFetchOffsetRowCountExpression(Expression fetchOffsetRowCountExpression) {
            target.setFetchOffsetRowCountExpression(fetchOffsetRowCountExpression);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public OffsetFetchBuilder<ParentBuilder> withUseFetchRows() {
            target.setUseFetchRows(true);
            return this;
        }

        /**
         * set
         * @param fetch OffsetFetch
         */
        public OffsetFetchBuilder<ParentBuilder> withFetch(OrderBy.OffsetFetch fetch) {
            target.setUseFetch(true);
            target.setUseFetchFirst(fetch.isUseFetchFirst());
            target.setFetchIntegerConstant(fetch.getIntegerConstant());
            target.setFetchOffsetRowCountExpression(fetch.getFetchOffsetRowCountExpression());
            target.setUseFetchRows(fetch.isUseFetchRows());
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * And get-out
         * @return PARENT
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
         * @return PARENT
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
