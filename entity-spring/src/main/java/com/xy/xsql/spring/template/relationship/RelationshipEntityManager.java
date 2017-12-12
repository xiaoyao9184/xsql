package com.xy.xsql.spring.template.relationship;

import com.xy.xsql.entity.core.meta.table.TableClassIndexer;
import com.xy.xsql.entity.core.meta.table.TableRelationshipClassIndexer;
import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.definition.RelationshipClass;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Same like
 * @see com.xy.xsql.entity.core.meta.SimpleTableMetaManager
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface RelationshipEntityManager
        extends TableRelationshipClassIndexer<EntityTableMeta> {

    <LeftEntity,RightEntity> EntityInfo getEntityInfo(RelationshipClass<LeftEntity,RightEntity> entity);

//    <Entity> RowMapper<Entity> getRowMapper(RelationshipClass<Entity,Entity> entities);

    <LeftEntity,RightEntity> EntityColumnsArgsBuilder<RelationshipClass<LeftEntity,RightEntity>> getEntityColumnsArgsBuilder(RelationshipClass<LeftEntity,RightEntity> entity);

    List<FieldRowNameHandler> getFieldRowNameHandlers();

    <LeftEntity,RightEntity,Renderer> Renderer getRenderer(RelationshipClass<LeftEntity,RightEntity> entity, Class<Renderer> rendererClass);

    <LeftEntity,RightEntity> RelationshipEntityHolder<LeftEntity, RightEntity> by(RelationshipClass<LeftEntity,RightEntity> entity);


    interface RelationshipEntityHolder<LeftEntity, RightEntity> {

        EntityInfo getEntityInfo();

        RowMapper getRowMapper();


        EntityInfo getLeftEntityInfo();

        EntityInfo getRightEntityInfo();

        <Render> Render getRenderer(Class<Render> render);
    }
}
