package com.xy.xsql.entity.core.meta;

import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.meta.table.*;
import com.xy.xsql.entity.model.definition.RelationshipClass;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2017/10/23.
 */
public enum SimpleTableMetaManager
        implements
        TableClassIndexer<TableMeta>,
        TableRelationshipClassIndexer<TableMeta> {


    INSTANCE;

    private ClassTableMetaBuilderSelector classTableMetaBuilderSelector;

    SimpleTableMetaManager() {
        classTableMetaBuilderSelector = new ClassTableMetaBuilderSelector();
        classTableMetaBuilderSelector.registered(JPAClassEntityTableMetaBuilder::new, JPAClassEntityTableMetaBuilder::checkSupport);
        classTableMetaBuilderSelector.registered(BaseClassEntityTableMetaBuilder::new, BaseClassEntityTableMetaBuilder::checkSupport);

    }

    @Override
    public Optional<TableMeta> table(RelationshipClass<?, ?> relationshipClass) {
        //TODO
        return null;
    }

    @Override
    public Optional<TableMeta> table(Class<?> clazz) {
        return classTableMetaBuilderSelector.select(clazz)
                .map(Supplier::get)
                .map(b -> b.build(clazz));
    }

}
