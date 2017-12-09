package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.model.definition.RelationshipClass;

import java.util.Optional;

/**
 * Created by xiaoyao9184 on 2017/10/21
 */
public interface TableRelationshipClassIndexer<T extends TableMeta> {

    Optional<T> table(RelationshipClass<?,?> relationshipClass);
}
