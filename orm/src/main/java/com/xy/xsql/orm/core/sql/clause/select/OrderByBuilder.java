package com.xy.xsql.orm.core.sql.clause.select;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.select.OrderBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class OrderByBuilder<Done>
        extends SubBuilder<OrderByBuilder<Done>,Void,Done> {

    private OrderBy orderBy;

    public OrderByBuilder(OrderBy orderBy) {
        this.orderBy = orderBy;
    }


    public OrderByListBuilder<OrderByBuilder<Done>> withItems(){
        List<OrderBy.OrderByItem> items = new ArrayList<>();
        if(this.orderBy.getItems() == null){
            this.orderBy.setItems(new ArrayList<OrderBy.OrderByItem>());
        }
        this.orderBy.setItems(items);
        return new OrderByListBuilder<OrderByBuilder<Done>>(items)
                .in(this);
    }

    public OffsetFetchBuilder<OrderByBuilder<Done>> withOffsetFetch(){
        OrderBy.OffsetFetch offsetFetch = new OrderBy.OffsetFetch();
        this.orderBy.setOffsetFetch(offsetFetch);
        return new OffsetFetchBuilder<OrderByBuilder<Done>>(offsetFetch)
                .in(this);
    }




    /**
     *
     * @param <Done>
     */
    public static class OrderByListBuilder<Done>
            extends SubBuilder<OrderByListBuilder<Done>,Void,Done> {

        private List<OrderBy.OrderByItem> orderByList;

        public OrderByListBuilder(List<OrderBy.OrderByItem> orderByList) {
            this.orderByList = orderByList;
        }

        public OrderByItemBuilder<OrderByListBuilder<Done>> withItem(){
            OrderBy.OrderByItem orderByItem = new OrderBy.OrderByItem();
            if(this.orderByList == null){
                this.orderByList = new ArrayList<>();
            }
            this.orderByList.add(orderByItem);
            return new OrderByItemBuilder<OrderByListBuilder<Done>>(orderByItem)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class OrderByItemBuilder<Done>
            extends SubBuilder<OrderByItemBuilder<Done>,Void,Done> {

        private OrderBy.OrderByItem orderByItem;

        public OrderByItemBuilder(OrderBy.OrderByItem orderByItem) {
            this.orderByItem = orderByItem;
        }

        public OrderByItemBuilder<Done> withAsc(boolean useAsc){
            this.orderByItem.setUseAsc(useAsc);
            return this;
        }

        public OrderByItemBuilder<Done> withDesc(boolean useDesc){
            this.orderByItem.setUseDesc(useDesc);
            return this;
        }

        public OrderByItemBuilder<Done> withExpression(Expression expression){
            this.orderByItem.setOrderByExpression(expression);
            return this;
        }

    }

    /**
     *
     * @param <Done>
     */
    public static class OffsetFetchBuilder<Done>
            extends SubBuilder<OffsetFetchBuilder<Done>,Void,Done> {

        private OrderBy.OffsetFetch offsetFetch;

        public OffsetFetchBuilder(OrderBy.OffsetFetch offsetFetch) {
            this.offsetFetch = offsetFetch;
        }



        public OffsetFetchBuilder<Done> withIntegerConstant(Integer integerConstant) {
            this.offsetFetch.setIntegerConstant(integerConstant);
            return this;
        }

        public OffsetFetchBuilder<Done> setOffsetRowCountExpression(Expression offsetRowCountExpression) {
            this.offsetFetch.setOffsetRowCountExpression(offsetRowCountExpression);
            return this;
        }

        public OffsetFetchBuilder<Done> setUseRow(boolean useRow) {
            this.offsetFetch.setUseRow(useRow);
            return this;
        }

        public OffsetFetchBuilder<Done> setUseFetch(boolean useFetch) {
            this.offsetFetch.setUseFetch(useFetch);
            return this;
        }

        public OffsetFetchBuilder<Done> setUseFetchFirst(boolean useFetchFirst) {
            this.offsetFetch.setUseFetchFirst(useFetchFirst);
            return this;
        }

        public OffsetFetchBuilder<Done> setFetchIntegerConstant(Integer fetchIntegerConstant) {
            this.offsetFetch.setFetchIntegerConstant(fetchIntegerConstant);
            return this;
        }

        public OffsetFetchBuilder<Done> setFetchOffsetRowCountExpression(Expression fetchOffsetRowCountExpression) {
            this.offsetFetch.setFetchOffsetRowCountExpression(fetchOffsetRowCountExpression);
            return this;
        }

        public OffsetFetchBuilder<Done> setUseFetchRow(boolean useFetchRow) {
            this.offsetFetch.setUseFetchRow(useFetchRow);
            return this;
        }

    }
}
