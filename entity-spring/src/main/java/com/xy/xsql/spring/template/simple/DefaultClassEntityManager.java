package com.xy.xsql.spring.template.simple;

import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.entity.api.dialect.render.jsql.JSqlRender;
import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import com.xy.xsql.entity.core.dialect.type.JavaTypeMapperProvider;
import com.xy.xsql.entity.core.meta.table.ClassTableMetaBuilderSelector;
import com.xy.xsql.entity.core.meta.table.EntityTableMetaBuilder;
import com.xy.xsql.entity.core.meta.ProxyObjectMethodRecorder;
import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;
import com.xy.xsql.entity.model.template.EntityColumn;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.template.EntityTable;
import com.xy.xsql.spring.config.DialectConfiguration;
import com.xy.xsql.spring.mapping.BaseEntityRowMapper;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import com.xy.xsql.spring.mapping.MappingFieldRowNameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultClassEntityManager implements ClassEntityManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public DefaultClassEntityManager(){
    }


    @SuppressWarnings("unchecked")
    @Override
    public <Entity> ClassEntityHolder<Entity> by(Class<Entity> entity) {
        if(!this.cache.containsKey(entity)){
            logger.debug("This entities is not included in the manager {}, will include it now!",entity);
            initClassEntity(entity);
        }
        return cache.get(entity);
    }



    @Override
    public Optional<EntityTableMeta> table(Class<?> entity) {
        return Optional.of(by(entity)
                .table());
    }

    @Override
    public EntityTableMeta getTableMeta(Class entity) {
        return by(entity)
                .table();
    }

    @Override
    public EntityInfo getEntityInfo(Class entity) {
        return null;
    }

    @Override
    public <Entity> RowMapper<Entity> getRowMapper(Class<Entity> entity) {
        return by(entity)
                .rowMapper();
    }

    @Override
    public <Entity> EntityColumnsArgsBuilder<Entity> getEntityColumnsArgsBuilder(Class<Entity> entity) {
        return by(entity)
                .getEntityColumnsArgsBuilder();
    }

    @Override
    public List<FieldRowNameHandler> getFieldRowNameHandlers() {
        return this.fieldRowNameHandlerList;
    }

    @Override
    public <Entity, Renderer> Renderer getRenderer(Class<Entity> entity, Class<Renderer> renderer) {
        return by(entity)
                .getRenderer(renderer);
    }



    private List<FieldRowNameHandler> fieldRowNameHandlerList;
    private Map<Class,ClassEntityCacheHelper> cache;

    @Autowired
    private PackageClassStreamProvider packageClassStreamProvider;

    @Autowired
    private JavaTypeMapperProvider javaTypeMapperProvider;

    //TODO JDBCTypeMapperProvider

    //TODO TableMetaBuilderProvider
//    @Autowired
//    private Set<TableMetaBuilder<Class,EntityInfo>> tableMetaBuilderSet;

    @Autowired
    private ClassTableMetaBuilderSelector tableMetaBuilderSelector;


    @PostConstruct
    public void init() throws Exception {
        this.fieldRowNameHandlerList = new ArrayList<>();
        this.fieldRowNameHandlerList.add(new MappingFieldRowNameHandler());
        this.cache = Collections.synchronizedMap(new HashMap<Class,ClassEntityCacheHelper>());
        this.packageClassStreamProvider.provide(this::initClassEntity);
    }

    /**
     * Init Class Entity
     * @param clazz Class of Entity
     * @param <Entity> Entity
     */
    private <Entity> void initClassEntity(Class<Entity> clazz){
        ClassEntityCacheHelper<Entity> classEntityCacheHelper = new ClassEntityCacheHelper<>();
        classEntityCacheHelper.setManager(this);
        //TypeMapper
        JavaTypeMapper entityTypeMapper = javaTypeMapperProvider.provide(clazz);

        //RowMapper
        RowMapper<Entity> rowMapper = new BaseEntityRowMapper<>(clazz)
                .withRowNameHandlerList(this.fieldRowNameHandlerList);

        //MetaBuilder
        EntityTableMetaBuilder<?,JavaTypeMapper,Class,ClassEntityTableMeta> builder =
                tableMetaBuilderSelector.select(clazz)
                        .orElseThrow(() -> new UnsupportedOperationException("Cant build Entity table meta by this Class " + clazz.getName()))
                        .get();
        builder.config(entityTypeMapper);

        //Meta
        ClassEntityTableMeta meta = builder.build(clazz);
        classEntityCacheHelper
                .cacheMeta(meta)
                .cacheRowMapper(rowMapper);

        cache.put(clazz, classEntityCacheHelper);
    }

    /**
     * Class Entity Holder & Helper
     * @param <Entity>
     */
    public static class ClassEntityCacheHelper<Entity>
            implements ClassEntityHolder<Entity> {
        private ClassEntityTableMeta meta;
        private SourceTargetMapper<String,PropertyDescriptorEntityColumnMeta> nameColumnIndex;
        private SourceTargetMapper<Method,PropertyDescriptorEntityColumnMeta> methodColumnIndex;
        private Set<PropertyDescriptorEntityColumnMeta> primaryColumnIndex;

        private SourceTargetMapper<Class<?>,ClassEntityCacheHelper<?>> subTableIndex;

        private ProxyObjectMethodRecorder<Entity> recorder;
        private RowMapper<Entity> rowMapper;

        private DefaultClassEntityManager manager;

        public void setManager(DefaultClassEntityManager manager) {
            this.manager = manager;
        }

        public ClassEntityCacheHelper<Entity> cacheMeta(ClassEntityTableMeta meta) {
            this.meta = meta;
            this.recorder = ProxyObjectMethodRecorder.create(meta.getEntity());
            buildNameIndex();
            buildMethodIndex();
            buildPrimaryIndex();
            buildRelationshipEntityIndex();
            return this;
        }

        public ClassEntityCacheHelper<Entity> cacheRowMapper(RowMapper<Entity> rowMapper) {
            this.rowMapper = rowMapper;
            return this;
        }

        private void buildNameIndex(){
            nameColumnIndex = new SourceTargetMapper<>();
            meta.getColumns()
                    .forEach(c -> {
                        nameColumnIndex.map(c.getEntity().getName(),c);
                    });
        }

        private void buildMethodIndex(){
            methodColumnIndex = new SourceTargetMapper<>();
            meta.getColumns()
                    .forEach(c -> {
                        methodColumnIndex.map(c.getEntity().getReadMethod(),c);
                        methodColumnIndex.map(c.getEntity().getWriteMethod(),c);
                    });
        }

        private void buildPrimaryIndex() {
            primaryColumnIndex = meta.getColumns()
                    .stream()
                    .filter(EntityColumnMeta::isPrimary)
                    .collect(Collectors.toSet());
        }

        private void buildRelationshipEntityIndex() {
            subTableIndex = new SourceTargetMapper<>();
            meta.getColumns()
                    .stream()
                    .map(EntityColumnMeta::getRelationshipEntity)
                    .forEach(e -> {
                        if(!manager.cache.keySet().contains(e)){
                            manager.initClassEntity(e);
                        }
                        subTableIndex.map(e,manager.cache.get(e));
                    });
        }


        /*

         */

        public PropertyDescriptorEntityColumnMeta index(Method method) {
            return methodColumnIndex.getByLeft(method);
        }

        public synchronized PropertyDescriptorEntityColumnMeta index(Function<Entity,?> getter) {
            getter.apply(recorder.getObject());
            return methodColumnIndex.getByLeft(recorder.getMethod());
        }

        @Override
        public ClassEntityTableMeta table() {
            return meta;
        }

        @Override
        public Optional<PropertyDescriptorEntityColumnMeta> column(Integer index) {
            List<PropertyDescriptorEntityColumnMeta> columnMetaList = new ArrayList<>(meta.getColumns());
            if(columnMetaList.size() > index) {
                return Optional.of(columnMetaList.get(index));
            }
            return Optional.empty();
        }

        @Override
        public Optional<PropertyDescriptorEntityColumnMeta> column(String name) {
            if(nameColumnIndex.checkLeft(name)){
                return Optional.of(nameColumnIndex.getByLeft(name));
            }
            return Optional.empty();
        }

        @Override
        public Optional<PropertyDescriptorEntityColumnMeta> column(Method method) {
            if(methodColumnIndex.checkLeft(method)){
                return Optional.of(methodColumnIndex.getByLeft(method));
            }
            return Optional.empty();
        }

        @Override
        public PropertyDescriptorEntityColumnMeta column(Function<Entity,?> getter) {
            getter.apply(recorder.getObject());
            return methodColumnIndex.getByLeft(recorder.getMethod());
        }

        @Override
        public Collection<PropertyDescriptorEntityColumnMeta> columnId() {
            return primaryColumnIndex;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <SubEntity> ClassEntityHolder<SubEntity> tableSub(Class<SubEntity> subEntityClass) {
            return (ClassEntityHolder<SubEntity>) subTableIndex.getByLeft(subEntityClass);
        }

        @Override
        public RowMapper<Entity> rowMapper() {
            return rowMapper;
        }

        @Override
        public <Render> JSqlRender render(Class<Render> render) {

//            getRender(render)

            //TODO
            return null;
        }

        @Override
        public <Render> Render getRenderer(Class<Render> render) {
            //TODO
            return null;
        }

        @Override
        public <Render> Render getRender(Class<Render> render) {
            //TODO
            return null;
        }

        @Override
        public EntityColumnsArgsBuilder<Entity> getEntityColumnsArgsBuilder() {
            return null;
        }

        @Override
        public DialectConfiguration getDialectConfiguration() {
            return null;
        }

        @Override
        public List<FieldRowNameHandler> getFieldRowNameHandlers() {
            return null;
        }

        @Override
        public EntityInfo getEntityInfo() {
            EntityInfo entityInfo = new EntityInfo();
            entityInfo.setClazz(meta.getEntity());
            EntityTable entityTable = new EntityTable();
            entityTable.setName(meta.getName());
            List<EntityColumn> cs = meta.getColumns().stream()
                    .map(c -> {
                        EntityColumn entityColumn = new EntityColumn();
                        entityColumn.setName(c.getName());
                        entityColumn.setType(c.getType());
                        entityColumn.setLength(c.getLength());
                        entityColumn.setTable(entityTable);
                        return entityColumn;
                    })
                    .collect(Collectors.toList());
            entityInfo.setColumns(cs);

            List<EntityColumn> ks = columnId().stream()
                    .map(c -> {
                        EntityColumn entityColumn = new EntityColumn();
                        entityColumn.setName(c.getName());
                        entityColumn.setType(c.getType());
                        entityColumn.setLength(c.getLength());
                        entityColumn.setTable(entityTable);
                        return entityColumn;
                    })
                    .collect(Collectors.toList());
            entityInfo.setKeys(ks);


            return entityInfo;
        }

    }

}
