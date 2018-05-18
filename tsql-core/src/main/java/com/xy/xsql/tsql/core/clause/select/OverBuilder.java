package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import com.xy.xsql.tsql.model.clause.select.Over;
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

    public RowRangeBuilder<OverBuilder<ParentBuilder>> withRowRange(){
        return new RowRangeBuilder<OverBuilder<ParentBuilder>>
                (initSet(Over.RowRange::new,
                        target::getRowRange,
                        target::setRowRange))
                .in(this);
    }

    public OverBuilder<ParentBuilder> withPartitionBy(Over.PartitionBy partitionBy){
        this.target.setPartitionBy(partitionBy);
        return this;
    }

    public OverBuilder<ParentBuilder> withRowRange(Over.RowRange rowRange){
        this.target.setRowRange(rowRange);
        return this;
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

    public RowRangeBuilder<OverBuilder<ParentBuilder>> $Rows() {
        return withRowRange().withRows();
    }

    public RowRangeBuilder<OverBuilder<ParentBuilder>> $Range() {
        return withRowRange().withRange();
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

        public OrderByBuilder<ParentBuilder> withItems(List<OrderBy.Item> orderByItems) {
            if(CheckUtil.isNullOrEmpty(orderByItems)){
                return this;
            }
            initAdd(orderByItems,
                    target::getItems,
                    target::setItems);
            return this;
        }
    }

    /**
     * RowRangeBuilder
     * @param <ParentBuilder>
     */
    public static class RowRangeBuilder<ParentBuilder>
            extends CodeTreeBuilder<RowRangeBuilder<ParentBuilder>,ParentBuilder,Over.RowRange> {

        public RowRangeBuilder(Over.RowRange rowRange) {
            super(rowRange);
        }

        public RowRangeBuilder<ParentBuilder> withRows(){
            target.setUseRows(true);
            return this;
        }

        public RowRangeBuilder<ParentBuilder> withRange(){
            target.setUseRows(false);
            return this;
        }

        public WindowFrameExtentBuilder<RowRangeBuilder<ParentBuilder>> withWindowFrameExtent(){
            return new WindowFrameExtentBuilder<RowRangeBuilder<ParentBuilder>>
                    (target::setWindowFrameExtent)
                    .in(this);
        }

        /**
         * Quick set Preceding WindowFrameExtent
         * @return
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
         * @return
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
         * @return
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
         * @return
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
         * @return
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
         * @return
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

        public WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>> withBetweenBound(){
            return new WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>>
                    (target::setBetweenBound)
                    .in(this);
        }

        public WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>> withAndBound(){
            return new WindowFrameBoundBuilder<WindowFrameBetweenBuilder<ParentBuilder>>
                    (target::setAndBound)
                    .in(this);
        }


        /**
         * Quick set
         * @return
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
         * @return
         */
        public WindowFramePrecedingBuilder<ParentBuilder> _Preceding() {
            Over.WindowFramePreceding preceding = new Over.WindowFramePreceding();
            target.set(preceding);
            return new WindowFramePrecedingBuilder<ParentBuilder>
                    (preceding)
                    .in(out());
        }

        /**
         * Confirm type of WindowFrameBound
         * @return
         */
        public WindowFrameFollowingBuilder<ParentBuilder> _Following() {
            Over.WindowFrameFollowing following = new Over.WindowFrameFollowing();
            target.set(following);
            return new WindowFrameFollowingBuilder<ParentBuilder>
                    (following)
                    .in(out());
        }

        public ParentBuilder $CurrentRow() {
            return _Preceding()
                    .withCurrent()
                    .and();
        }
        
        public ParentBuilder $UnboundedPreceding() {
            return _Preceding()
                    .withUnbounded()
                    .and();
        }

        public ParentBuilder $Preceding(Integer unsignedValueSpecification) {
            return _Preceding()
                    .withUnsignedValueSpecification(unsignedValueSpecification)
                    .and();
        }
        
        public ParentBuilder $UnboundedFollowing() {
            return _Following()
                    .withUnbounded()
                    .and();
        }

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

        public WindowFramePrecedingBuilder<ParentBuilder> withUnbounded(){
            target.setUseUnbounded(true);
            return this;
        }

        public WindowFramePrecedingBuilder<ParentBuilder> withUnsignedValueSpecification(Integer unsignedValueSpecification){
            target.setUnsignedvaluespecification(new Over.UnsignedValueSpecification(unsignedValueSpecification));
            return this;
        }

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

        public WindowFrameFollowingBuilder<ParentBuilder> withUnbounded(){
            target.setUseUnbounded(true);
            return this;
        }

        public WindowFrameFollowingBuilder<ParentBuilder> withUnsignedvaluespecification(Integer unsignedValueSpecification){
            target.setUnsignedvaluespecification(new Over.UnsignedValueSpecification(unsignedValueSpecification));
            return this;
        }

        public WindowFrameFollowingBuilder<ParentBuilder> withCurrent(){
            target.setUseCurrent(false);
            return this;
        }
    }

}
