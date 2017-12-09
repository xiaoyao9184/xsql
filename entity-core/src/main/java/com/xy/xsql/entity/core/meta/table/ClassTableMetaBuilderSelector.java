package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.core.selecter.PredicateSelector;
import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import com.xy.xsql.entity.core.meta.table.EntityTableMetaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2017/9/26
 */
public class ClassTableMetaBuilderSelector
        implements PredicateSelector<
            Class,
            Supplier<EntityTableMetaBuilder<?,JavaTypeMapper,Class,ClassEntityTableMeta>>> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<Supplier<EntityTableMetaBuilder<?, JavaTypeMapper, Class, ClassEntityTableMeta>>, Predicate<Class>> cache;


    public ClassTableMetaBuilderSelector(){
        cache = new LinkedHashMap<>();
    }

    public void registered(Supplier<EntityTableMetaBuilder<?,JavaTypeMapper,Class,ClassEntityTableMeta>> supplier, Predicate<Class> predicate){
        cache.put(supplier,predicate);
    }

    public void registered(EntityTableMetaBuilder<?,JavaTypeMapper,Class,ClassEntityTableMeta> builder, Predicate<Class> predicate){
        cache.put(() -> builder,predicate);
    }

    public <B extends EntityTableMetaBuilder<?, JavaTypeMapper, Class, ClassEntityTableMeta>> void registered(Class<B> builderClass, Predicate<Class> predicate){
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
    public Map<Supplier<EntityTableMetaBuilder<?, JavaTypeMapper, Class, ClassEntityTableMeta>>, Predicate<Class>> predicates() {
        return cache;
    }
}
