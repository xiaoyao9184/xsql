package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_ORDER_BY;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_OVER;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface over {

    static b_OVER OVER(
            b_OVER.b_PARTITION_BY partition_by){
        return new b_OVER(){
            {
                withPartitionBy(partition_by.build());
            }
        };
    }

    static b_OVER OVER(
            b_OVER.b_PARTITION_BY partition_by,
            b_ORDER_BY order_by){
        return new b_OVER(){
            {
                withPartitionBy(partition_by.build());
                withOrderBy().withItems(order_by.build().getItems());
            }
        };
    }

    static b_OVER OVER(
            b_OVER.b_PARTITION_BY partition_by,
            b_ORDER_BY order_by,
            b_OVER.b_ROW_RANGE row_range){
        return new b_OVER(){
            {
                withPartitionBy(partition_by.build());
                withOrderBy().withItems(order_by.build().getItems());
                withRowRange(row_range.build());
            }
        };
    }

    static b_OVER OVER(
            b_ORDER_BY order_by){
        return new b_OVER(){
            {
                withOrderBy().withItems(order_by.build().getItems());
            }
        };
    }

    static b_OVER OVER(
            b_ORDER_BY order_by,
            b_OVER.b_ROW_RANGE row_range){
        return new b_OVER(){
            {
                withOrderBy().withItems(order_by.build().getItems());
                withRowRange(row_range.build());
            }
        };
    }

    static b_OVER OVER(
            b_OVER.b_ROW_RANGE row_range){
        return new b_OVER(){
            {
                withRowRange(row_range.build());
            }
        };
    }





    static b_OVER.b_PARTITION_BY PARTITION_BY(Expression... expressions){
        return new b_OVER.b_PARTITION_BY(){
            {
                withExpression(expressions);
            }
        };
    }


    static b_OVER.b_ROW_RANGE ROWS(
            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRows()
                        .$UnboundedPreceding();
            }
        };
    }
    static b_OVER.b_ROW_RANGE ROWS(
            Integer unsignedValueSpecification,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRows()
                        .$Preceding(unsignedValueSpecification);
            }
        };
    }
    static b_OVER.b_ROW_RANGE ROWS(
            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRows()
                        .$CurrentRow();
            }
        };
    }
    static b_OVER.b_ROW_RANGE ROWS(
            b_OVER.b_ROW_RANGE.b_BETWEEN between){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRows();
                withWindowFrameExtent(between.build());
            }
        };
    }

//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded2,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding2
//            ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded2,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded2,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded2,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//
//
//
//
//
//
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded2,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification2,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification2,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification2,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification2,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_PRECEDING preceding
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            Integer unsignedValueSpecification,
//            b_OVER.b_ROW_RANGE.k_FOLLOWING following
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }
//
//    static b_OVER.b_ROW_RANGE ROWS(
//            b_OVER.b_ROW_RANGE.k_BETWEEN between,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row1,
//            b_OVER.b_ROW_RANGE.k_AND and,
//            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row2
//    ){
//        return new b_OVER.b_ROW_RANGE(){
//            {
//                withRows()
//                        .$Between().$UnboundedPreceding();
//            }
//        };
//    }

    static b_OVER.b_ROW_RANGE RANGE(
            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRange()
                        .$UnboundedPreceding();
            }
        };
    }
    static b_OVER.b_ROW_RANGE RANGE(
            Integer unsignedValueSpecification,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRange()
                        .$Preceding(unsignedValueSpecification);
            }
        };
    }
    static b_OVER.b_ROW_RANGE RANGE(
            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRange()
                        .$CurrentRow();
            }
        };
    }
    static b_OVER.b_ROW_RANGE RANGE(
            b_OVER.b_ROW_RANGE.b_BETWEEN between){
        return new b_OVER.b_ROW_RANGE(){
            {
                withRange();
                withWindowFrameExtent(between.build());
            }
        };
    }


    static b_OVER.b_ROW_RANGE.k_UNBOUNDED UNBOUNDED(){
        return null;
    }
    static b_OVER.b_ROW_RANGE.k_PRECEDING PRECEDING(){
        return null;
    }
    static b_OVER.b_ROW_RANGE.k_FOLLOWING FOLLOWING(){
        return null;
    }
    static b_OVER.b_ROW_RANGE.k_CURRENT_ROW CURRENT_ROW(){
        return null;
    }

    static b_OVER.b_ROW_RANGE.b_BETWEEN BETWEEN(
            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
            b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND and
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN(){
            {
                withBetweenBound().$UnboundedPreceding();
                withAndBound(and.build());
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN BETWEEN(
            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
            b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND and
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN(){
            {
                withBetweenBound().$UnboundedFollowing();
                withAndBound(and.build());
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN BETWEEN(
            Integer unsignedValueSpecification,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding,
            b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND and
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN(){
            {
                withBetweenBound().$Preceding(unsignedValueSpecification);
                withAndBound(and.build());
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN BETWEEN(
            Integer unsignedValueSpecification,
            b_OVER.b_ROW_RANGE.k_FOLLOWING following,
            b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND and
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN(){
            {
                withBetweenBound().$Following(unsignedValueSpecification);
                withAndBound(and.build());
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN BETWEEN(
            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row,
            b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND and
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN(){
            {
                withBetweenBound().$CurrentRow();
                withAndBound(and.build());
            }
        };
    }

    static b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND AND(
            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND(){
            {
                $UnboundedPreceding();
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND AND(
            b_OVER.b_ROW_RANGE.k_UNBOUNDED unbounded,
            b_OVER.b_ROW_RANGE.k_FOLLOWING following
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND(){
            {
                $UnboundedFollowing();
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND AND(
            Integer unsignedValueSpecification,
            b_OVER.b_ROW_RANGE.k_PRECEDING preceding
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND(){
            {
                $Preceding(unsignedValueSpecification);
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND AND(
            Integer unsignedValueSpecification,
            b_OVER.b_ROW_RANGE.k_FOLLOWING following
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND(){
            {
                $Following(unsignedValueSpecification);
            }
        };
    }
    static b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND AND(
            b_OVER.b_ROW_RANGE.k_CURRENT_ROW current_row
    ){
        return new b_OVER.b_ROW_RANGE.b_BETWEEN.b_AND(){
            {
                $CurrentRow();
            }
        };
    }

}
