package com.xy.xsql.benjiql.core;

import com.google.common.collect.ImmutableMap;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.datatype.Null;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.UnknownExpression;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;

/**
 * Created by xiaoyao9184 on 2017/7/31.
 */
public class TSqlConversions {

    public static Optional<SearchCondition> searchCondition(Collection<Predicate> predicates){
        return predicates.stream()
                .findFirst()
                .map(predicate -> {
                    SearchCondition searchCondition = new SearchCondition();
                    searchCondition.setPredicate(predicate);
                    return searchCondition;
                })
                .map(searchCondition -> {
                    List<SearchCondition.AndOrNotItem> andList = predicates.stream()
                            .skip(1)
                            .map(predicate -> {
                                SearchCondition.AndOrNotItem item = new SearchCondition.AndOrNotItem();
                                item.setUseAnd(true);
                                item.setPredicate(predicate);
                                return item;
                            })
                            .collect(Collectors.toList());
                    searchCondition.setAndOrList(andList);
                    return searchCondition;
                });
    }

    public static Optional<Where> where(Collection<Predicate> predicates){
        return searchCondition(predicates)
                .map(searchCondition -> {
                    Where where = new Where();
                    where.setSearchCondition(searchCondition);
                    return where;
                });
    }

    public static TableValueConstructor values(){
        TableValueConstructor t = new TableValueConstructor();
        t.setRowValueExpressionListGroup(new ArrayList<>(new ArrayList<>()));
        return t;
    }

    public static Expression expression(Object object){
        if(object == null){
            return new Null();
        }
        if(object instanceof Expression){
            return (Expression) object;
        }
        if(jdbcSetters.containsKey(object.getClass())){
            return jdbcSetters.get(object.getClass()).apply(object);
        }else{
            return new UnknownExpression(object.toString());
        }
    }

    public static Expression placeholderExpression(){
        return new UnknownExpression("?");
    }


    private static final Map<Class<?>, Function<Object,Expression>> jdbcSetters = ImmutableMap.<Class<?>,Function<Object,Expression>>builder()
            .put(String.class, (v) -> e_string(v.toString()))
            .put(Integer.class, (v) -> e_number((Number) v))
            .put(Float.class, (v) -> e_number((Number) v))
            .put(Double.class, (v) -> e_number((Number) v))
            .put(Long.class, (v) -> e_number((Number) v))
            .put(Character.class, (v) -> e_string(v.toString()))
            .put(Byte.class, (v) -> e_number((Number) v))
            .put(int.class, (v) -> e_number((Number) v))
            .put(float.class, (v) -> e_number((Number) v))
            .put(double.class, (v) -> e_number((Number) v))
            .put(long.class, (v) -> e_number((Number) v))
            .put(char.class, (v) -> e_string(v.toString()))
            .put(byte.class, (v) -> e_number((Number) v))
            .build();

}
