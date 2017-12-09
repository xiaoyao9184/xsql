package com.xy.xsql.entity.core.meta.sql;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.model.sql.JoinCondition;
import com.xy.xsql.entity.model.sql.JoinType;
import com.xy.xsql.entity.model.sql.TableJoin;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/10/21
 */
public class EntityJoinBuilder<Entity,JoinEntity>
        extends CodeTreeBuilder<EntityJoinBuilder<Entity,JoinEntity>,EntityQueryBuilder<Entity>,TableJoin> {

    private Class<JoinEntity> joinEntityClass;
    private Function<Function<Entity,?>,ColumnMeta> getterColumnMetaFunction;

    public EntityJoinBuilder(Class<JoinEntity> joinEntityClass,
                             Function<Function<Entity,?>,ColumnMeta> getterColumnMetaFunction){
        super(new TableJoin());
        this.joinEntityClass = joinEntityClass;
        this.getterColumnMetaFunction = getterColumnMetaFunction;
    }

    public EntityJoinBuilder<Entity,JoinEntity> join(JoinType joinType){
        target.setJoinType(joinType);
        return this;
    }

    public EntityJoinBuilder<Entity,JoinEntity> table(TableMeta tableMeta){
        target.setTableMeta(tableMeta);
        return this;
    }

    /**
     * Set JoinCondition by main entity field getter
     * @param getter column getter
     * @return EntityQueryBuilder
     */
    @SafeVarargs
    public final EntityQueryBuilder<JoinEntity> on(Function<Entity, ?>... getter) {
        EntityQueryBuilder<JoinEntity> joinEntityQueryBuilder = new EntityQueryBuilder<>
                (this.back(), joinEntityClass);
        Function<String,ColumnMeta> joinNameColumnMetaFunction = joinEntityQueryBuilder::columnMetaByName;

        List<JoinCondition> joinConditions = Stream.of(getter)
                .map(g -> {
                    ColumnMeta columnMeta = getterColumnMetaFunction.apply(g);
                    ColumnMeta joinColumnMeta = joinNameColumnMetaFunction.apply(columnMeta.getName());
                    JoinCondition joinCondition = new JoinCondition();
                    joinCondition.setLeftColumn(columnMeta);
                    joinCondition.setRightColumn(joinColumnMeta);
                    return joinCondition;
                })
                .collect(Collectors.toList());
        target.setJoinConditions(joinConditions);

        return joinEntityQueryBuilder;
    }
}
