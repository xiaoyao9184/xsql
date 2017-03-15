//package com.xy.xsql.tsql.core.clause.subquery;
//
//import com.xy.xsql.core.SubBuilder;
//import com.xy.xsql.tsql.model.expression.Expression;
//import com.xy.xsql.orm.data.sql.element.info.GroupList;
//import com.xy.xsql.tsql.model.statement.dml.Insert;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by xiaoyao9184 on 2017/3/10.
// */
//public class ValuesSubQueryBuilder<Done>
//        extends SubBuilder<ValuesSubQueryBuilder<Done>,Void,Done> {
//
//
//    //TODO can DEFAULT ?
//    //VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
//    private GroupList<Insert.Value> valueGroupList;
//
//    public ValueListBuilder<ValuesSubQueryBuilder<Done>> withGroupItem(){
//        List<Insert.Value> valueList = new ArrayList<>();
//        if(this.valueGroupList == null){
//            this.valueGroupList = new GroupList<>();
//        }
//        this.valueGroupList.add(valueList);
//        return new ValueListBuilder<ValuesSubQueryBuilder<Done>>(valueList)
//                .in(this);
//    }
//
//
//
//    public static class ValueListBuilder<Done>
//            extends SubBuilder<ValueListBuilder<Done>,Void,Done> {
//
//        private List<Insert.Value> valueList;
//
//        public ValueListBuilder(List<Insert.Value> valueList) {
//            this.valueList = valueList;
//        }
//
//        public ValueBuilder<ValueListBuilder<Done>> withItem(){
//            Insert.Value value = new Insert.Value();
//            if(this.valueList == null){
//                this.valueList = new ArrayList<>();
//            }
//            this.valueList.add(value);
//            return new ValueBuilder<ValueListBuilder<Done>>(value)
//                    .in(this);
//        }
//    }
//
//    public static class ValueBuilder<Done>
//            extends SubBuilder<ValueBuilder<Done>,Void,Done> {
//
//        private Insert.Value value;
//
//        public ValueBuilder(Insert.Value value) {
//            this.value = value;
//        }
//
//        public ValueBuilder<Done> withExpression(Expression expression){
//            this.value.setExpression(expression);
//            return this;
//        }
//
//        public ValueBuilder<Done> withNull(Boolean useNull){
//            this.value.setUseNull(useNull);
//            return this;
//        }
//    }
//
//}
