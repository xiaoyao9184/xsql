package com.xy.xsql.entity.core.meta.sql;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.model.sql.ColumnCondition;
import com.xy.xsql.entity.model.sql.PredicateType;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class ColumnConditionBuilder<Entity,P>
        extends CodeTreeBuilder<ColumnConditionBuilder<Entity,P> ,P,ColumnCondition> {


    public ColumnConditionBuilder(ColumnCondition entityColumnCondition) {
        super(entityColumnCondition);
    }


    public ColumnConditionBuilder<Entity,P> withAnd(){
        target.setUseAnd(true);
        return this;
    }

    public ColumnConditionBuilder<Entity,P> withOr(){
        target.setUseAnd(false);
        return this;
    }

    public ColumnConditionBuilder<Entity,P> withColumnMeta(ColumnMeta columnMeta){
        target.setColumnMeta(columnMeta);
        return this;
    }


    public P equalTo(Object value) {
        target.setPredicateType(PredicateType.equalTo);
        target.setValue(value);
        return this.back();
    }

    public P notEqualTo(Object value) {
        target.setPredicateType(PredicateType.notEqualTo);
        target.setValue(value);
        return this.back();
    }

    public P like(Object value) {
        target.setPredicateType(PredicateType.like);
        target.setValue(value);
        return this.back();
    }
}
