package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.queries.select.OrderBy;
import com.xy.xsql.tsql.model.queries.select.Over;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * OverBuilder
 * Created by xiaoyao9184 on 2017/1/17.
 */
@SuppressWarnings({"unused", "WeakerAccess", "UnusedReturnValue"})
public class OverBuilder<ParentBuilder>
        extends CodeTreeBuilder<OverBuilder<ParentBuilder>,ParentBuilder,Over> {

    public OverBuilder() {
        super(new Over());
    }

    public OverBuilder(Over over) {
        super(over);
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
                (initSet(Over.PartitionBy::new,
                        target::getPartitionBy,
                        target::setPartitionBy))
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
                (initSet(Over.OrderBy::new,
                        target::getOrderBy,
                        target::setOrderBy))
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
                (initSet(Over.RowRange::new,
                        target::getRowRange,
                        target::setRowRange))
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
            extends CodeTreeBuilder<PartitionByBuilder<ParentBuilder>,ParentBuilder,Over.PartitionBy> {

        public PartitionByBuilder() {
            super(new Over.PartitionBy());
        }

        public PartitionByBuilder(Over.PartitionBy partitionBy) {
            super(partitionBy);
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

        public OrderByBuilder() {
            super(new Over.OrderBy());
        }

        public OrderByBuilder(Over.OrderBy orderBy) {
            super(orderBy);
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
            initAdd(Arrays.asList(orderByItems),
                    target::getItems,
                    target::setItems);
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
            initAdd(orderByItems,
                    target::getItems,
                    target::setItems);
            return this;
        }

        /**
         * in
         * @return OrderByItemBuilder
         */
        public com.xy.xsql.tsql.builder.chain.queries.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>> withItems(){
            return new com.xy.xsql.tsql.builder.chain.queries.select.OrderByBuilder.OrderByItemBuilder<OrderByBuilder<ParentBuilder>>
                    (initNew(OrderBy.Item::new,
                            target::getItems,
                            target::setItems))
                    .in(this);
        }
    }

    /**
     * RowRangeBuilder
     * @param <ParentBuilder>
     */
    public static class RowRangeBuilder<ParentBuilder>
            extends CodeTreeBuilder<RowRangeBuilder<ParentBuilder>,ParentBuilder,Over.RowRange> {

        public RowRangeBuilder() {
            super(new Over.RowRange());
        }

        public RowRangeBuilder(Over.RowRange rowRange) {
            super(rowRange);
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
         * in
         * @return WindowFrameExtentBuilder
         */
        public WindowFrameExtentBuilder<RowRangeBuilder<ParentBuilder>> withWindowFrameExtent(){
            return new WindowFrameExtentBuilder<RowRangeBuilder<ParentBuilder>>
                    (target::setWindowFrameExtent)
                    .in(this);
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
            extends CodeTreeBuilder<WindowFrameExtentBuilder<ParentBuilder>, ParentBuilder, Setter<Over.WindowFrameExtent>> {

        public WindowFrameExtentBuilder(Setter<Over.WindowFrameExtent> setter) {
            super(setter);
        }

        /**
         * Confirm type of WindowFrameExtent
         * @return THIS
         */
        public WindowFramePrecedingBuilder<ParentBuilder> _Preceding() {
            Over.WindowFramePreceding preceding = new Over.WindowFramePreceding();
            target.set(preceding);
            return new WindowFramePrecedingBuilder<ParentBuilder>
                    (preceding)
                    .in(out());
        }

        /**
         * Confirm type of WindowFrameExtent
         * @return THIS
         */
        public WindowFrameBetweenBuilder<ParentBuilder> _Between() {
            Over.WindowFrameBetween between = new Over.WindowFrameBetween();
            target.set(between);
            return new WindowFrameBetweenBuilder<ParentBuilder>
                    (between)
                    .in(out());
        }
    }

    /**
     * WindowFrameBetweenBuilder
     * @param <ParentBuilder>
     */
    public static class WindowFrameBetweenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WindowFrameBetweenBuilder<ParentBuilder>,ParentBuilder,Over.WindowFrameBetween> {

        public WindowFrameBetweenBuilder(Over.WindowFrameBetween windowFrameBetween) {
            super(windowFrameBetween);
        }

        /**
         * in
         * @return WindowFrameBoundBuilder
         */
        public WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>> withBetweenBound(){
            return new WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>>
                    (target::setBetweenBound)
                    .in(this);
        }

        /**
         * in
         * @return WindowFrameBoundBuilder
         */
        public WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>> withAndBound(){
            return new WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>>
                    (target::setAndBound)
                    .in(this);
        }


        /**
         * Quick in
         * @return WindowFrameBoundBuilder
         */
        public WindowFrameBoundBuilder<ParentBuilder> $And(){
            return new WindowFrameBoundBuilder<ParentBuilder>
                    (target::setAndBound)
                    .in(this.and());
        }

    }

    /**
     * WindowFrameBoundBuilder
     * @param <ParentBuilder>
     */
    public static class WindowFrameBoundBuilder<ParentBuilder>
            extends CodeTreeBuilder<WindowFrameBoundBuilder<ParentBuilder>, ParentBuilder, Setter<Over.WindowFrameBound>> {

        public WindowFrameBoundBuilder(Setter<Over.WindowFrameBound> setter) {
            super(setter);
        }

        /**
         * Confirm type of WindowFrameBound
         * @return WindowFramePrecedingBuilder
         */
        public WindowFramePrecedingBuilder<ParentBuilder> _Preceding() {
            return new WindowFramePrecedingBuilder<ParentBuilder>
                    (initSet(Over.WindowFramePreceding::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
        }

        /**
         * Confirm type of WindowFrameBound
         * @return WindowFrameFollowingBuilder
         */
        public WindowFrameFollowingBuilder<ParentBuilder> _Following() {
            return new WindowFrameFollowingBuilder<ParentBuilder>
                    (initSet(Over.WindowFrameFollowing::new,
                            Getter.empty(),
                            target::set))
                    .in(out());
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
            extends CodeTreeBuilder<WindowFramePrecedingBuilder<ParentBuilder>,ParentBuilder,Over.WindowFramePreceding> {

        public WindowFramePrecedingBuilder(Over.WindowFramePreceding windowFramePreceding) {
            super(windowFramePreceding);
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
            extends CodeTreeBuilder<WindowFrameFollowingBuilder<ParentBuilder>,ParentBuilder,Over.WindowFrameFollowing> {

        public WindowFrameFollowingBuilder(Over.WindowFrameFollowing windowFrameFollowing) {
            super(windowFrameFollowing);
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
