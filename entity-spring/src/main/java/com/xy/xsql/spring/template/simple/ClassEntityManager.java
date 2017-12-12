package com.xy.xsql.spring.template.simple;

import com.xy.xsql.entity.api.dialect.render.jsql.JSqlRender;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.core.meta.column.ColumnMethodIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnNameIndexer;
import com.xy.xsql.entity.core.meta.column.ColumnNumberIndexer;
import com.xy.xsql.entity.core.meta.table.TableClassIndexer;
import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.spring.config.DialectConfiguration;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Same like
 * @see com.xy.xsql.entity.core.meta.SimpleTableMetaManager
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface ClassEntityManager
        extends TableClassIndexer<EntityTableMeta> {

    EntityTableMeta getTableMeta(Class entity);

    EntityInfo getEntityInfo(Class entity);

    <Entity> RowMapper<Entity> getRowMapper(Class<Entity> entity);

    <Entity> EntityColumnsArgsBuilder<Entity> getEntityColumnsArgsBuilder(Class<Entity> entity);

    List<FieldRowNameHandler> getFieldRowNameHandlers();

    <Entity,Renderer> Renderer getRenderer(Class<Entity> entity, Class<Renderer> renderer);


    <Entity> ClassEntityHolder<Entity> by(Class<Entity> entity);


    interface ClassEntityHolder<Entity>
            extends ColumnNumberIndexer<PropertyDescriptorEntityColumnMeta>,
            ColumnNameIndexer<PropertyDescriptorEntityColumnMeta>,
            ColumnMethodIndexer<PropertyDescriptorEntityColumnMeta> {

        ClassEntityTableMeta table();

//        PropertyDescriptorEntityColumnMeta column(String name);
//
//        PropertyDescriptorEntityColumnMeta column(Method method);

        PropertyDescriptorEntityColumnMeta column(Function<Entity,?> getter);

        Collection<PropertyDescriptorEntityColumnMeta> columnId();

        <RelationshipEntity> ClassEntityHolder<RelationshipEntity> tableSub(Class<RelationshipEntity> subEntityClass);

//        <RelationshipEntity> ClassEntityHolder<RelationshipEntity> tableSub(String name);
//
//        <RelationshipEntity> ClassEntityHolder<RelationshipEntity> tableSub(Method method);
//
//        <RelationshipEntity> ClassEntityHolder<RelationshipEntity> tableSub(Function<Entity,?> method);


        RowMapper<Entity> rowMapper();

        <Render> JSqlRender render(Class<Render> render);

        @Deprecated
        <Render> Render getRenderer(Class<Render> render);

        <Render> Render getRender(Class<Render> render);

        EntityColumnsArgsBuilder<Entity> getEntityColumnsArgsBuilder();

        DialectConfiguration getDialectConfiguration();

        List<FieldRowNameHandler> getFieldRowNameHandlers();

        EntityInfo getEntityInfo();
    }
}
