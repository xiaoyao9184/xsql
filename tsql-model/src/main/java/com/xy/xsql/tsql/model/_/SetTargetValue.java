//package com.xy.xsql.tsql.model._;
//
//import com.xy.xsql.core.Setter;
//import com.xy.xsql.tsql.model.Block;
//import com.xy.xsql.tsql.model.expression.Expression;
//import com.xy.xsql.tsql.model.operator.Comparison;
//import com.xy.xsql.tsql.model.statement.dml.BulkInsert;
//import com.xy.xsql.tsql.util.ListBlockBuilder;
//
//import java.util.List;
//
///**
// * Created by xiaoyao9184 on 2017/3/19.
// */
//public abstract class SetTargetValue<SetTargetType,ValueType extends Expression> implements Item<SetTargetType> {
//
//    private BulkInsert.WithEnum key;
//    private ValueType value;
//
//    public SetTargetValue(BulkInsert.WithEnum key, ValueType value){
//        this.key = key;
//        this.value = value;
//    }
//
//    public abstract Setter<ValueType> getSetter(SetTargetType target);
//
//    public ValueType getValue(){
//        return value;
//    }
//
//    @Override
//    public void set(SetTargetType target) {
//        getSetter(target).set(value);
//    }
//
//    @Override
//    public List<Block> toBlockList() {
//        return new ListBlockBuilder()
//                .append(key)
//                .append(Comparison.EQUAL)
//                .append(value)
//                .build();
//    }
//}