package com.xy.xsql.spring.template.relationship;

import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.definition.RelationshipClass;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import com.xy.xsql.spring.template.simple.DefaultClassEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultRelationshipEntityManager implements RelationshipEntityManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Optional<EntityTableMeta> table(RelationshipClass<?, ?> relationshipClass) {
        return null;
    }

    @Override
    public <LeftEntity,RightEntity> EntityInfo getEntityInfo(RelationshipClass<LeftEntity,RightEntity> entity) {
        return null;
    }

    private <LeftEntity, RightEntity> EntityInfo getEntityInfo(RelationshipClass<LeftEntity, RightEntity> entity, Class<?> leftRightClass) {
        return null;
    }

    @Override
    public <LeftEntity, RightEntity> EntityColumnsArgsBuilder<RelationshipClass<LeftEntity, RightEntity>> getEntityColumnsArgsBuilder(RelationshipClass<LeftEntity, RightEntity> entity) {
        return null;
    }

    @Override
    public List<FieldRowNameHandler> getFieldRowNameHandlers() {
        return null;
    }

    @Override
    public <LeftEntity, RightEntity, Renderer> Renderer getRenderer(RelationshipClass<LeftEntity, RightEntity> entity, Class<Renderer> rendererClass) {
        return null;
    }


    @Override
    public <LeftEntity, RightEntity> RelationshipEntityManager.RelationshipEntityHolder<LeftEntity, RightEntity> by(RelationshipClass<LeftEntity, RightEntity> entity) {
        return new RelationshipEntityManager.RelationshipEntityHolder<LeftEntity, RightEntity>() {
            @Override
            public EntityInfo getEntityInfo() {
                return DefaultRelationshipEntityManager.this.getEntityInfo(entity);
            }

            @Override
            public EntityInfo getLeftEntityInfo() {
                return DefaultRelationshipEntityManager.this.getEntityInfo(entity,entity.leftClass);
            }

            @Override
            public EntityInfo getRightEntityInfo() {
                return DefaultRelationshipEntityManager.this.getEntityInfo(entity,entity.rightClass);
            }

            @Override
            public <Render> Render getRenderer(Class<Render> render) {
                return null;
            }

            @Override
            public RowMapper getRowMapper() {
                throw new UnsupportedOperationException("RelationshipClass not support RowMapper!");
            }
        };
    }



    private List<FieldRowNameHandler> fieldRowNameHandlerList;
    private Map<Class,DefaultClassEntityManager.ClassEntityCacheHelper> cache;



//    @PostConstruct
//    public void init() throws Exception {
//        this.fieldRowNameHandlerList = new ArrayList<>();
//        this.fieldRowNameHandlerList.add(new MappingFieldRowNameHandler());
//        this.cache = Collections.synchronizedMap(new HashMap<Class,ClassEntityCacheHelper>());
//
//        //TODO
//        List<Class> classList = new ArrayList<>();
//        this.cache = this.initClassEntity(classList);
//    }
//
//    public Map<Class,ClassEntityCacheHelper> initClassEntity(List<Class> classList) {
////        Map<Class<?>,ClassEntityCacheHelper<?>> result = new HashMap<>();
//        return classList.stream()
//                .map(clazz -> {
//                    ClassEntityCacheHelper<Object> cacheData = new ClassEntityCacheHelper<>();
//                    //TODO
//                    TypeMapper<Class<?>,String> typeMapper = null;
//
//                    EntityInfo entityInfo = new EAnnotationEntityInfoBuilder()
//                            .configStart()
//                                .withTypeMapper(typeMapper)
//                                .out()
//                            .build(clazz);
//
//                    RowMapper<Object> rowMapper = new BaseEntityRowMapper<Object>(clazz)
//                            .withRowNameHandlerList(this.fieldRowNameHandlerList);
//
//                    EntityColumnsArgsBuilder<Object> entityColumnsArgsBuilder = new EntityColumnsArgsBuilder<>()
//                            .withColumns(entityInfo.getColumns());
//
//                    EntityColumnsArgsBuilder<Object> entityIdsArgBuilder = new EntityColumnsArgsBuilder<>()
//                            .withColumns(entityInfo.getKeys());
//
//                    cacheData
//                            .withEntityData(entityInfo)
//                            .withRowMapper(rowMapper)
//                            .withEntityColumnArgsBuilder(entityColumnsArgsBuilder)
//                            .withEntityIdArgBuilder(entityIdsArgBuilder);
//
//                    return cacheData;
//                })
//                .collect(Collectors.toMap(
//                        cache -> cache.getEntityInfo().getClazz(),
//                        cache -> cache
//                ));
//    }



}
