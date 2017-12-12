package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.core.selecter.PredicateSelector;
import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import com.xy.xsql.entity.model.definition.RelationshipClass;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import com.xy.xsql.entity.model.lambda.RelationshipEntityTableMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2017/9/26
 */
public class RelationshipClassTableMetaBuilderSelector
        implements PredicateSelector<
            RelationshipClass,
            Supplier<EntityTableMetaBuilder<?,?,RelationshipClass,RelationshipEntityTableMeta>>> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<Supplier<EntityTableMetaBuilder<?, ?, RelationshipClass, RelationshipEntityTableMeta>>, Predicate<RelationshipClass>> cache;


    public RelationshipClassTableMetaBuilderSelector(){
        cache = new LinkedHashMap<>();
    }

    public void registered(Supplier<EntityTableMetaBuilder<?,?,RelationshipClass,RelationshipEntityTableMeta>> supplier, Predicate<RelationshipClass> predicate){
        cache.put(supplier,predicate);
    }

    public void registered(EntityTableMetaBuilder<?,?,RelationshipClass,RelationshipEntityTableMeta> builder, Predicate<RelationshipClass> predicate){
        cache.put(() -> builder,predicate);
    }

    public <B extends EntityTableMetaBuilder<?, ?, RelationshipClass, RelationshipEntityTableMeta>> void registered(Class<B> builderClass, Predicate<RelationshipClass> predicate){
//        try {
            //TODO
//            TableMetaBuilder<Class,ClassEntityTableMeta> builder = builderClass.newInstance();
            cache.put(() -> {
                try {
                    return builderClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    logger.error("Cant instance builder!",e);
                }
                return null;
            },predicate);
//        } catch (InstantiationException | IllegalAccessException e) {
//            logger.error("Cant instance builder!",e);
//        }
    }

    @Override
    public Map<Supplier<EntityTableMetaBuilder<?, ?, RelationshipClass, RelationshipEntityTableMeta>>, Predicate<RelationshipClass>> predicates() {
        return cache;
    }
}
