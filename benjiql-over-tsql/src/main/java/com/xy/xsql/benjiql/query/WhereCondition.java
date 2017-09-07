package com.xy.xsql.benjiql.query;

import com.xy.xsql.tsql.model.predicate.Predicate;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by xiaoyao9184 on 2017/8/2.
 */
public class WhereCondition<T> {
    private Function<T,?> fieldGetter;
    private Object value;
    //        private Function<Function<T,?>,ColumnName> columnNameBuildFunction;
//        private BiFunction<ColumnName, Object, Predicate> predicateBuildFunction;
    private BiFunction<Function<T,?>, Object, Predicate> predicateFunction;

    public WhereCondition(Function<T,?> fieldGetter){
        this.fieldGetter = fieldGetter;
    }

    public Function<T,?> getFieldGetter() {
        return fieldGetter;
    }

    public void setFieldGetter(Function<T,?> fieldGetter) {
        this.fieldGetter = fieldGetter;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

//        public Function<Function<T,?>, ColumnName> getColumnNameBuildFunction() {
//            return columnNameBuildFunction;
//        }
//
//        public void setColumnNameBuildFunction(Function<Function<T,?>, ColumnName> columnNameBuildFunction) {
//            this.columnNameBuildFunction = columnNameBuildFunction;
//        }
//
//        public BiFunction<ColumnName, Object, Predicate> getPredicateBuildFunction() {
//            return predicateBuildFunction;
//        }
//
//        public void setPredicateBuildFunction(BiFunction<ColumnName, Object, Predicate> predicateBuildFunction) {
//            this.predicateBuildFunction = predicateBuildFunction;
//        }

    public BiFunction<Function<T,?>, Object, Predicate> getPredicateFunction() {
        return predicateFunction;
    }

    public void setPredicateFunction(BiFunction<Function<T,?>, Object, Predicate> predicateFunction) {
        this.predicateFunction = predicateFunction;
    }


    public Predicate buildPredicate(){
        return predicateFunction.apply(
                fieldGetter,
                value);
    }

    public Predicate buildPredicate(Object value){
        return predicateFunction.apply(
                fieldGetter,
                value);
    }
}
