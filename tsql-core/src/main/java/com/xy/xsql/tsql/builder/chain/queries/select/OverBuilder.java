package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.select.OrderBy;
import com.xy.xsql.tsql.model.queries.select.Over;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * OverBuilder
 * Created by xiaoyao9184 on 2017/1/17.
 */
@SuppressWarnings({"unused", "WeakerAccess", "UnusedReturnValue"})
public class OverBuilder<ParentBuilder>
        extends ParentHoldBuilder<OverBuilder<ParentBuilder>,ParentBuilder,Over> {

    public OverBuilder() {
        super(new Over());
    }

    public OverBuilder(Over target) {
        super(target);
    }


    /**
     * set
     * @param partitionBy PartitionBy
     * @return THIS
     */
    public OverBuilder<ParentBuilder> withPartitionBy(Over.PartitionBy partitionBy){
        this.target.setPartitionBy(partitionBy);
        return this;
    }

    /**
     * in
     * @return PartitionByBuilder
     */
    public PartitionByBuilder<OverBuilder<ParentBuilder>> withPartitionBy(){
        return new PartitionByBuilder<OverBuilder<ParentBuilder>>
                (object(target::getPartitionBy, target::setPartitionBy)
                        .init(Over.PartitionBy::new))
                .in(this);
    }

    /**
     * set
     * @param orderBy OrderBy
     * @return THIS
     */
    public OverBuilder<ParentBuilder> withOrderBy(Over.OrderBy orderBy){
        this.target.setOrderBy(orderBy);
        return this;
    }

    /**
     * in
     * @return OrderByBuilder
     */
    public OrderByBuilder<OverBuilder<ParentBuilder>> withOrderBy(){
        return new OrderByBuilder<OverBuilder<ParentBuilder>>
                (object(target::getOrderBy, target::setOrderBy)
                        .init(Over.OrderBy::new))
                .in(this);
    }

    /**
     * set
     * @param rowRange RowRange
     * @return THIS
     */
    public OverBuilder<ParentBuilder> withRowRange(Over.RowRange rowRange){
        this.target.setRowRange(rowRange);
        return this;
    }

    /**
     * in
     * @return RowRangeBuilder
     */
    public RowRangeBuilder<OverBuilder<ParentBuilder>> withRowRange(){
        return new RowRangeBuilder<OverBuilder<ParentBuilder>>
                (object(target::getRowRange, target::setRowRange)
                        .init(Over.RowRange::new))
                .in(this);
    }




    /*
    Quick set
     */

    /**
     * Quick set partitionBy
     * into PartitionByBuilder get-out
     * @param expressions Expression
     * @return THIS
     */
    public OverBuilder<ParentBuilder> $PartitionBy(Expression... expressions){
        return withPartitionBy()
                .withExpression(expressions)
                .and();
    }

    /**
     * Quick set orderBy
     * into OrderByBuilder get-out
     * @param expressions Expression
     * @return THIS
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
     * @param expressions Expression
     * @return THIS
     */
    public OverBuilder<ParentBuilder> $OrderByDesc(Expression... expressions){
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
     * Quick in
     * @return RowRangeBuilder
     */
    public RowRangeBuilder<OverBuilder<ParentBuilder>> $Rows() {
        return withRowRange().withRows();
    }

    /**
     * Quick in
     * @return RowRangeBuilder
     */
    public RowRangeBuilder<OverBuilder<ParentBuilder>> $Range() {
        return withRowRange().withRange();
    }


    /**
     * PartitionByBuilder
     * @param <ParentBuilder>
     */
    public static class PartitionByBuilder<ParentBuilder>
            extends ParentHoldBuilder<PartitionByBuilder<ParentBuilder>,ParentBuilder,Over.PartitionBy> {

        public PartitionByBuilder() {
            super(new Over.PartitionBy());
        }

        public PartitionByBuilder(Over.PartitionBy target) {
            super(target);
        }

        /**
         * set
         * @param expressions Expression
         * @return THIS
         */
        public PartitionByBuilder<ParentBuilder> withExpression(Expression... expressions){
            if(CheckUtil.isNullOrEmpty(expressions)){
                return this;
            }
            list(target::getValueExpressionList, target::setValueExpressionList)
                    .addAll(expressions);
            return this;
        }
    }

    /**
     * OrderByBuilder
     * @param <ParentBuilder>
     */
    public static class OrderByBuilder<ParentBuilder>
            extends ParentHoldBuilder<OrderByBuilder<ParentBuilder>,ParentBuilder,Over.OrderBy> {

        public OrderByBuilder() {
            super(new Over.OrderBy());
        }

        public OrderByBuilder(Over.OrderBy target) {
            super(target);
        }

        /**
         * sert
         * @param orderByItems Item
         * @return THIS
         */
        public OrderByBuilder<ParentBuilder> withItems(OrderBy.Item... orderByItems){
            if(CheckUtil.isNullOrEmpty(orderByItems)){
                return this;
            }
            list(target::getItems, target::setItems)
                    .addAll(orderByItems);
            return this;
        }

        /**
         * set
         * @param orderByItems Item
         * @return THIS
         */
        public OrderByBuilder<ParentBuilder> withItems(List<OrderBy.Item> orderByItems) {
            if(CheckUtil.isNullOrEmpty(orderByItems)){
                return this;
            }
            list(target::getItems, target::setItems)
                    .addAll(orderByItems);
            return this;
        }

        /**
         * in
         * @return OrderByItemBuilder
         */
        public com.xy.xsql.tsql.builder.chain.queries.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>> withItems(){
            return new com.xy.xsql.tsql.builder.chain.queries.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                    (list(target::getItems, target::setItems)
                            .addNew(OrderBy.Item::new))
                    .in(this);
        }
    }

    /**
     * RowRangeBuilder
     * @param <ParentBuilder>
     */
    public static class RowRangeBuilder<ParentBuilder>
            extends ParentHoldBuilder<RowRangeBuilder<ParentBuilder>,ParentBuilder,Over.RowRange> {

        public RowRangeBuilder() {
            super(new Over.RowRange());
        }

        public RowRangeBuilder(Over.RowRange target) {
            super(target);
        }

        /**
         * set
         * @return THIS
         */
        public RowRangeBuilder<ParentBuilder> withRows(){
            target.setUseRows(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public RowRangeBuilder<ParentBuilder> withRange(){
            target.setUseRows(false);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public RowRangeBuilder<ParentBuilder> withWindowFrameExtent(Over.WindowFrameExtent windowFrameExtent){
            target.setWindowFrameExtent(windowFrameExtent);
            return this;
        }

        /**
         * in
         * @return WindowFrameExtentBuilder
         */
        public WindowFrameExtentBuilder<RowRangeBuilder<ParentBuilder>> withWindowFrameExtent(){
            return new WindowFrameExtentBuilder<RowRangeBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target::setWindowFrameExtent);
        }

        /**
         * Quick set Preceding WindowFrameExtent
         * @return PARENT
         */
        public ParentBuilder $UnboundedPreceding() {
            return withWindowFrameExtent()
                    ._Preceding()
                    .withUnbounded()
                    .and()
                    .and();
        }

        /**
         * Quick set Preceding WindowFrameExtent
         * @return PARENT
         */
        public ParentBuilder $Preceding(Integer unsignedValueSpecification) {
            return withWindowFrameExtent()
                    ._Preceding()
                    .withUnsignedValueSpecification(unsignedValueSpecification)
                    .and()
                    .and();
        }

        /**
         * Quick set Preceding WindowFrameExtent
         * @return PARENT
         */
        public ParentBuilder $CurrentRow() {
            return withWindowFrameExtent()
                    ._Preceding()
                    .withCurrent()
                    .and()
                    .and();
        }

        /**
         * Quick set Between WindowFrameExtent
         * @return WindowFrameBoundBuilder
         */
        public WindowFrameBoundBuilder<WindowFrameBetweenBuilder<RowRangeBuilder<ParentBuilder>>> $Between() {
            return withWindowFrameExtent()
                    ._Between()
                    .withBetweenBound();
        }
    }

    /**
     * WindowFrameExtentBuilder
     * @param <ParentBuilder>
     */
    public static class WindowFrameExtentBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<WindowFrameExtentBuilder<ParentBuilder>, ParentBuilder, Over.WindowFrameExtent> {

        public WindowFrameExtentBuilder() {}

        /**
         * Confirm type of WindowFrameExtent
         * @return THIS
         */
        public WindowFramePrecedingBuilder<ParentBuilder> _Preceding() {
            return new WindowFramePrecedingBuilder<ParentBuilder>
                    (object(Over.WindowFramePreceding::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of WindowFrameExtent
         * @return THIS
         */
        public WindowFrameBetweenBuilder<ParentBuilder> _Between() {
            return new WindowFrameBetweenBuilder<ParentBuilder>
                    (object(Over.WindowFrameBetween::new).set(this::init))
                    .in(this.and());
        }
    }

    /**
     * WindowFrameBetweenBuilder
     * @param <ParentBuilder>
     */
    public static class WindowFrameBetweenBuilder<ParentBuilder>
            extends ParentHoldBuilder<WindowFrameBetweenBuilder<ParentBuilder>,ParentBuilder,Over.WindowFrameBetween> {

        public WindowFrameBetweenBuilder() {
            super(new Over.WindowFrameBetween());
        }

        public WindowFrameBetweenBuilder(Over.WindowFrameBetween target) {
            super(target);
        }

        /**
         * in
         * @return WindowFrameBoundBuilder
         */
        public WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>> withBetweenBound(){
            return new WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target::setBetweenBound);
        }

        /**
         * set
         * @return THIS
         */
        public WindowFrameBetweenBuilder<ParentBuilder> withAndBound(Over.WindowFrameBound windowFrameBound){
            target.setAndBound(windowFrameBound);
            return this;
        }

        /**
         * in
         * @return WindowFrameBoundBuilder
         */
        public WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>> withAndBound(){
            return new WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target::setAndBound);
        }


        /**
         * Quick in
         * @return WindowFrameBoundBuilder
         */
        public WindowFrameBoundBuilder<ParentBuilder> $And(){
            return new WindowFrameBoundBuilder<ParentBuilder>()
                    .enter(this.out(), Getter.empty(), target::setAndBound);
        }

    }

    /**
     * WindowFrameBoundBuilder
     * @param <ParentBuilder>
     */
    public static class WindowFrameBoundBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<WindowFrameBoundBuilder<ParentBuilder>, ParentBuilder, Over.WindowFrameBound> {

        public WindowFrameBoundBuilder() {}

        /**
         * Confirm type of WindowFrameBound
         * @return WindowFramePrecedingBuilder
         */
        public WindowFramePrecedingBuilder<ParentBuilder> _Preceding() {
            return new WindowFramePrecedingBuilder<ParentBuilder>
                    (object(Over.WindowFramePreceding::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of WindowFrameBound
         * @return WindowFrameFollowingBuilder
         */
        public WindowFrameFollowingBuilder<ParentBuilder> _Following() {
            return new WindowFrameFollowingBuilder<ParentBuilder>
                    (object(Over.WindowFrameFollowing::new).set(this::init))
                    .in(this.and());
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $CurrentRow() {
            return _Preceding()
                    .withCurrent()
                    .and();
        }

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $UnboundedPreceding() {
            return _Preceding()
                    .withUnbounded()
                    .and();
        }

        /**
         * Quick set
         * @param unsignedValueSpecification  unsigned value specification
         * @return PARENT
         */
        public ParentBuilder $Preceding(Integer unsignedValueSpecification) {
            return _Preceding()
                    .withUnsignedValueSpecification(unsignedValueSpecification)
                    .and();
        }

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $UnboundedFollowing() {
            return _Following()
                    .withUnbounded()
                    .and();
        }

        /**
         * Quick set
         * @param unsignedValueSpecification  unsigned value specification
         * @return PARENT
         */
        public ParentBuilder $Following(Integer unsignedValueSpecification) {
            return _Following()
                    .withUnsignedvaluespecification(unsignedValueSpecification)
                    .and();
        }
    }


    /**
     * WindowFramePrecedingBuilder
     * @param <ParentBuilder>
     */
    public static class WindowFramePrecedingBuilder<ParentBuilder>
            extends ParentHoldBuilder<WindowFramePrecedingBuilder<ParentBuilder>,ParentBuilder,Over.WindowFramePreceding> {

        public WindowFramePrecedingBuilder() {
            super(new Over.WindowFramePreceding());
        }

        public WindowFramePrecedingBuilder(Over.WindowFramePreceding target) {
            super(target);
        }

        /**
         * set
         * @return THIS
         */
        public WindowFramePrecedingBuilder<ParentBuilder> withUnbounded(){
            target.setUseUnbounded(true);
            return this;
        }

        /**
         * set
         * @param unsignedValueSpecification unsigned value specification
         * @return THIS
         */
        public WindowFramePrecedingBuilder<ParentBuilder> withUnsignedValueSpecification(Integer unsignedValueSpecification){
            target.setUnsignedvaluespecification(new Over.UnsignedValueSpecification(unsignedValueSpecification));
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public WindowFramePrecedingBuilder<ParentBuilder> withCurrent(){
            target.setUseCurrent(true);
            return this;
        }
    }

    /**
     * WindowFrameFollowingBuilder
     * @param <ParentBuilder>
     */
    public static class WindowFrameFollowingBuilder<ParentBuilder>
            extends ParentHoldBuilder<WindowFrameFollowingBuilder<ParentBuilder>,ParentBuilder,Over.WindowFrameFollowing> {

        public WindowFrameFollowingBuilder() {
            super(new Over.WindowFrameFollowing());
        }

        public WindowFrameFollowingBuilder(Over.WindowFrameFollowing target) {
            super(target);
        }

        /**
         * set
         * @return THIS
         */
        public WindowFrameFollowingBuilder<ParentBuilder> withUnbounded(){
            target.setUseUnbounded(true);
            return this;
        }

        /**
         * set
         * @param unsignedValueSpecification unsigned value specification
         * @return THIS
         */
        public WindowFrameFollowingBuilder<ParentBuilder> withUnsignedvaluespecification(Integer unsignedValueSpecification){
            target.setUnsignedvaluespecification(new Over.UnsignedValueSpecification(unsignedValueSpecification));
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public WindowFrameFollowingBuilder<ParentBuilder> withCurrent(){
            target.setUseCurrent(false);
            return this;
        }
    }

}
