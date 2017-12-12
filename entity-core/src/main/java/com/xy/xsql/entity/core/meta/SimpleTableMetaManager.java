package com.xy.xsql.entity.core.meta;

import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.meta.table.*;
import com.xy.xsql.entity.model.definition.RelationshipClass;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * All in one, all indexing way are supported here without cache
 * Created by xiaoyao9184 on 2017/10/23.
 */
public enum SimpleTableMetaManager
        implements
        TableNameIndexer<TableMeta>,
        TableClassIndexer<TableMeta>,
        TableRelationshipClassIndexer<TableMeta> {


    INSTANCE;

    private ClassTableMetaBuilderSelector classTableMetaBuilderSelector;
    private RelationshipClassTableMetaBuilderSelector relationshipClassTableMetaBuilderSelector;

    SimpleTableMetaManager() {
        classTableMetaBuilderSelector = new ClassTableMetaBuilderSelector();
        classTableMetaBuilderSelector.registered(JPAClassEntityTableMetaBuilder::new, JPAClassEntityTableMetaBuilder::checkSupport);
        classTableMetaBuilderSelector.registered(BaseClassEntityTableMetaBuilder::new, BaseClassEntityTableMetaBuilder::checkSupport);

        relationshipClassTableMetaBuilderSelector = new RelationshipClassTableMetaBuilderSelector();
        relationshipClassTableMetaBuilderSelector.registered(RelationshipClassEntityTableMetaBuilder::new, RelationshipClassEntityTableMetaBuilder::checkSupport);
    }



    @Override
    public Optional<TableMeta> table(RelationshipClass<?, ?> relationshipClass) {
        return relationshipClassTableMetaBuilderSelector.select(relationshipClass)
                .map(Supplier::get)
                .map(b -> b.build(relationshipClass));
    }

    @Override
    public Optional<TableMeta> table(Class<?> clazz) {
        return classTableMetaBuilderSelector.select(clazz)
                .map(Supplier::get)
                .map(b -> b.build(clazz));
    }

    @Override
    public Optional<TableMeta> table(String name) {
        return Optional.empty();
    }

}
