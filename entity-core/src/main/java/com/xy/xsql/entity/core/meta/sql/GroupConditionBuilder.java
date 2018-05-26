package com.xy.xsql.entity.core.meta.sql;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.model.sql.ColumnCondition;
import com.xy.xsql.entity.model.sql.GroupCondition;

import java.util.Objects;
import java.util.function.Function;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class GroupConditionBuilder<Entity,P>
        extends CodeTreeBuilder<GroupConditionBuilder<Entity,P>,P,GroupCondition> {


    private Function<Function<Entity,?>,ColumnMeta> getterColumnMetaFunction;


    public GroupConditionBuilder(GroupCondition groupCondition,Function<Function<Entity,?>,ColumnMeta> getterColumnMetaFunction) {
        super(groupCondition);
        Objects.requireNonNull(getterColumnMetaFunction);
        this.getterColumnMetaFunction = getterColumnMetaFunction;
    }

    public GroupConditionBuilder<Entity,P> withAnd(){
        target.setUseAnd(true);
        return this;
    }

    public GroupConditionBuilder<Entity,P> withOr(){
        target.setUseAnd(false);
        return this;
    }

    public <U> ColumnConditionBuilder<Entity,GroupConditionBuilder<Entity,P>> where(Function<Entity,U> getter){
        ColumnMeta columnMeta = getterColumnMetaFunction.apply(getter);

        return new ColumnConditionBuilder<Entity,GroupConditionBuilder<Entity,P>>
                (list(target::getInternal, target::setInternal)
                        .addNew(ColumnCondition::new))
                .in(this)
                .withColumnMeta(columnMeta);
    }

    public <U> ColumnConditionBuilder<Entity,GroupConditionBuilder<Entity,P>> and(Function<Entity,U> getter){
        return where(getter)
                .withAnd();
    }

    public <U> ColumnConditionBuilder<Entity,GroupConditionBuilder<Entity,P>> or(Function<Entity,U> getter){
        return where(getter)
                .withOr();
    }

}
