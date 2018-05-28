package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.*;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_SELECT;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.*;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;


/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface select_ {


    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_specification query_specification
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_specification.build());
            }
        };
    }

    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_WITH with,
            b_SELECT.b$query_expression query_expression
    ){
        return new b_SELECT(){
            {
                withWith(with.build());
                withQuery(query_expression.build());
            }
        };
    }


    static b_SELECT $(
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_specification query_specification,
            b_ORDER_BY order_by
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
                withOrderBy(order_by.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_specification query_specification,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_specification query_specification,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_specification query_specification,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_specification query_specification
    ){
        return new b_SELECT(){
            {
                withQuery(query_specification.build());
            }
        };
    }

    static b_SELECT $(
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_expression query_expression,
            b_ORDER_BY order_by
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
                withOrderBy(order_by.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_expression query_expression,
            b_FOR for_,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
                withFor(for_.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_expression query_expression,
            b_FOR for_
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
                withFor(for_.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_expression query_expression,
            b_OPTION option
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
                withOption(option.build());
            }
        };
    }
    static b_SELECT $(
            b_SELECT.b$query_expression query_expression
    ){
        return new b_SELECT(){
            {
                withQuery(query_expression.build());
            }
        };
    }



    //

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withTop(top.build());
                withSelectItem(select_list.build());
            }
        };
    }


    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_ALL all,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list){
        return new b_SELECT.b$query_specification(){
            {
                withAll();
                withSelectItem(select_list.build());
            }
        };
    }


    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem(select_list.build());
            }
        };
    }


    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            k_.k_DISTINCT distinct,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list){
        return new b_SELECT.b$query_specification(){
            {
                withDistinct();
                withSelectItem(select_list.build());
            }
        };
    }


    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            b_TOP top,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list){
        return new b_SELECT.b$query_specification(){
            {
                withTop(top.build());
                withSelectItem(select_list.build());
            }
        };
    }


    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_INTO into){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withInto(into.build().getNewTable().getFullName());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_FROM from){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withFrom(from.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withWhere(where.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withWhere(where.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_WHERE where){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withWhere(where.build());
            }
        };
    }

    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_GROUP_BY group_by){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withGroupBy(group_by.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list,
            b_HAVING having){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
                withHaving(having.build());
            }
        };
    }
    static b_SELECT.b$query_specification SELECT(
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list select_list){
        return new b_SELECT.b$query_specification(){
            {
                withSelectItem(select_list.build());
            }
        };
    }

    //
    static k_.k_ALL ALL(){
        return null;
    }
    static k_.k_DISTINCT DISTINCT(){
        return null;
    }

    //
    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            TableName tableName){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withTableAll(tableName);
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            TableName tableName,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.k$IDENTITY $identity){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem().withColumnName(c(tableName,"$IDENTITY"));
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            TableName tableName,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.k$ROWGUID $rowguid){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem().withColumnName(c(tableName,"$ROWGUID"));
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            ColumnName columnName){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withColumnName(columnName);
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            ColumnName columnName,
            String column_alias){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withColumnName(columnName)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            ColumnName columnName,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b_AS as){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withColumnName(columnName)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            Expression expression){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withExpression(expression);
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            Expression expression,
            String column_alias){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withExpression(expression)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            Expression expression,
            com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b_AS as){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withExpression(expression)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(
            String column_alias,
            Expression expression){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withColumnAlias(new Alias<>(column_alias))
                        .withEQ()
                        .withExpression(expression);
            }
        };
    }

    static com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list $(){
        return new com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT.b$select_list(){
            {
                withItem()
                        .withAll();
            }
        };
    }
}
