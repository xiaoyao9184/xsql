package com.xy.xsql.spring.template.simple;

import com.xy.xsql.entity.api.dialect.config.DialectProperties;
import com.xy.xsql.entity.api.dialect.render.jsql.meta.*;
import com.xy.xsql.entity.api.dialect.render.jsql.TemplateDeleteArgRenderer;
import com.xy.xsql.entity.api.dialect.render.jsql.TemplateSelectArgRenderer;
import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.model.sql.PlaceholderJSql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultClassEntityCRUDTemplate implements ClassEntityCRUDTemplate {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassEntityManager classEntityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DialectProperties dialectProperties;

    @Override
    public <Entity> Entity getById(Class<Entity> entityClass, Object... id) {
        Objects.requireNonNull(entityClass);
        Stream.of(id).peek(Objects::requireNonNull);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        if(id.length != entityManagerHolder.columnId().size()){
            throw new UnsupportedOperationException("id param count not equal with id column count!");
        }

        RowMapper<Entity> rowMapper = entityManagerHolder.rowMapper();
        ClassEntityTableMeta meta = entityManagerHolder.table();

        PlaceholderJSql sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                .rendering(meta,entityManagerHolder.columnId());
        sql.withArgs(id);

        logger.debug("SQL create:\n{}", sql);

        if(dialectProperties.isNotExistThrowError()){
            return jdbcTemplate.queryForObject(sql.getSql(), rowMapper, sql.getArgs());
        }else{
            return jdbcTemplate.query(sql.getSql(), rowMapper, sql.getArgs()).stream()
                    .findFirst()
                    .orElse(null);
        }
    }

    @Override
    public <Entity> List<Entity> listByIds(Class<Entity> entityClass, Object... id) {
        Objects.requireNonNull(entityClass);
        Stream.of(id).peek(Objects::requireNonNull);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        if(id.length % entityManagerHolder.columnId().size() != 0){
            throw new UnsupportedOperationException("id param count not multiple equal with id column count!");
        }

        RowMapper<Entity> rowMapper = entityManagerHolder.rowMapper();
        ClassEntityTableMeta meta = entityManagerHolder.table();

        Integer count = id.length / entityManagerHolder.columnId().size();
        PlaceholderJSql sql = entityManagerHolder.getRenderer(BatchSelectByColumnTableMetaRender.class)
                    .rendering(meta,entityManagerHolder.columnId(),count);
        sql.withArgs(id);

        logger.debug("SQL create:\n{}", sql);
        return jdbcTemplate.query(sql.getSql(), rowMapper, sql.getArgs());
    }

    @Override
    public <Entity> void deleteById(Class<Entity> entityClass, Object... id) {
        Objects.requireNonNull(entityClass);
        Stream.of(id).peek(Objects::requireNonNull);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        if(id.length != entityManagerHolder.columnId().size() &&
                id.length % entityManagerHolder.columnId().size() != 0){
            throw new UnsupportedOperationException("id param count not [multiple] equal with id column count!");
        }

        ClassEntityTableMeta tableMeta = entityManagerHolder.table();
        PlaceholderJSql sql;
        if(id.length == entityManagerHolder.columnId().size()){
            sql = entityManagerHolder.getRenderer(DeleteByColumnTableMetaRender.class)
                    .rendering(tableMeta,entityManagerHolder.columnId());
        }else{
            Integer count = id.length / entityManagerHolder.columnId().size();
            sql = entityManagerHolder.getRenderer(BatchSelectByColumnTableMetaRender.class)
                    .rendering(tableMeta,entityManagerHolder.columnId(),count);
        }
        sql.withArgs(id);
        
        logger.debug("SQL create:\n{}", sql);
        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }

    @Override
    public <Entity> void saveByEntity(Class<Entity> entityClass, Entity... entity) {
        Objects.requireNonNull(entityClass);
        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);

        if(entity.length == 0){
            throw new IllegalArgumentException("entity cant empty!");
        }

        PlaceholderJSql sql;
        if(entity.length == 1){
            sql = entityManagerHolder.getRenderer(InsertTableMetaRender.class)
                    .rendering(entityManagerHolder.table());
        }else{
            sql = entityManagerHolder.getRenderer(BatchInsertTableMetaRender.class)
                    .rendering(entityManagerHolder.table(),entity.length);
        }
        EntityColumnsArgsBuilder<Entity> entityColumnsArgsBuilder = entityManagerHolder.getEntityColumnsArgsBuilder();
        Object[] params = Stream.of(entity)
                .map(entityColumnsArgsBuilder::build)
                .map(Collection::stream)
                .toArray();
        sql.withArgs(params);

        logger.debug("SQL create:\n{}", sql);
        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }

    @Override
    public <Entity> void updateByEntity(Class<Entity> entityClass, Entity... entity) {
        Objects.requireNonNull(entityClass);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);

        if(entity.length == 0){
            throw new IllegalArgumentException("entity cant empty!");
        }

        PlaceholderJSql sql;
        if(entity.length == 1){
            sql = entityManagerHolder.getRenderer(UpdateByColumnTableMetaRender.class)
                    .rendering(entityManagerHolder.table(),entityManagerHolder.columnId());
        }else{
            sql = entityManagerHolder.getRenderer(BatchUpdateTableMetaRender.class)
                    .rendering(entityManagerHolder.table(),entityManagerHolder.columnId(),entity.length);
        }

        EntityColumnsArgsBuilder<Entity> entityColumnsArgsBuilder = entityManagerHolder.getEntityColumnsArgsBuilder();
        Object[] params = Stream.of(entity)
                .flatMap(e -> entityColumnsArgsBuilder.build(e).stream())
                .toArray();
        sql.withArgs(params);

        logger.debug("SQL create:\n{}", sql);
        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }

    @Override
    public <Entity> Entity getByEntity(Class<Entity> entityClass, Entity entity) {
        Objects.requireNonNull(entityClass);
        Objects.requireNonNull(entity);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        ClassEntityTableMeta meta = entityManagerHolder.table();
        List<PropertyDescriptorEntityColumnMeta> columnMetas = entityManagerHolder.table().getColumns();
        RowMapper<Entity> rowMapper = entityManagerHolder.rowMapper();

        //get params form entity
        List<Object> params = columnMetas.stream()
                .map(c -> {
                    try {
                        return c.getEntity().getReadMethod().invoke(entity);
                    } catch (IllegalAccessException | InvocationTargetException e1) {
                        return null;
                    }
                })
                .collect(Collectors.toList());

        PlaceholderJSql sql;
        if(!dialectProperties.isIgnoreNullParam()){
            sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnMetas);
            sql.withArgs(params);
        }else{
            //mapping column and param
            Map<PropertyDescriptorEntityColumnMeta, Object> columnParamMap = IntStream.range(0, columnMetas.size())
                    .boxed()
                    .collect(Collectors.toMap(columnMetas::get, params::get));
            columnParamMap = columnParamMap.entrySet().stream()
                    .filter(kv -> kv.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

            sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnParamMap.keySet());
            sql.withArgs(columnParamMap.values().toArray());
        }

        logger.debug("SQL create:\n{}", sql);

        if(dialectProperties.isNotExistThrowError()){
            return jdbcTemplate.queryForObject(sql.getSql(), rowMapper, sql.getArgs());
        }else{
            return jdbcTemplate.query(sql.getSql(), rowMapper, sql.getArgs()).stream()
                    .findFirst()
                    .orElse(null);
        }
    }

    @Override
    public <Entity> List<Entity> listByEntity(Class<Entity> entityClass, Entity entity) {
        Objects.requireNonNull(entityClass);
        Objects.requireNonNull(entity);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        ClassEntityTableMeta meta = entityManagerHolder.table();
        List<PropertyDescriptorEntityColumnMeta> columnMetas = entityManagerHolder.table().getColumns();
        RowMapper<Entity> rowMapper = entityManagerHolder.rowMapper();

        //get params form entity
        List<Object> params = columnMetas.stream()
                .map(c -> {
                    try {
                        return c.getEntity().getReadMethod().invoke(entity);
                    } catch (IllegalAccessException | InvocationTargetException e1) {
                        return null;
                    }
                })
                .collect(Collectors.toList());

        PlaceholderJSql sql;
        if(!dialectProperties.isIgnoreNullParam()){
            sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnMetas);
            sql.withArgs(params);
        }else{
            //mapping column and param
            Map<PropertyDescriptorEntityColumnMeta, Object> columnParamMap = IntStream.range(0, columnMetas.size())
                    .boxed()
                    .collect(Collectors.toMap(columnMetas::get, params::get));
            columnParamMap = columnParamMap.entrySet().stream()
                    .filter(kv -> kv.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

            sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnParamMap.keySet());
            sql.withArgs(columnParamMap.values().toArray());
        }

        logger.debug("SQL create:\n{}", sql);

        return jdbcTemplate.query(sql.getSql(), rowMapper, sql.getArgs());
    }

    @Override
    public <Entity> void deleteByEntity(Class<Entity> entityClass, Entity entity) {
        Objects.requireNonNull(entityClass);
        Objects.requireNonNull(entity);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        ClassEntityTableMeta meta = entityManagerHolder.table();
        List<PropertyDescriptorEntityColumnMeta> columnMetas = entityManagerHolder.table().getColumns();

        //get params form entity
        List<Object> params = columnMetas.stream()
                .map(c -> {
                    try {
                        return c.getEntity().getReadMethod().invoke(entity);
                    } catch (IllegalAccessException | InvocationTargetException e1) {
                        return null;
                    }
                })
                .collect(Collectors.toList());

        PlaceholderJSql sql;
        if(!dialectProperties.isIgnoreNullParam()){
            sql = entityManagerHolder.getRender(DeleteByColumnTableMetaRender.class)
                    .rendering(meta,columnMetas);
            sql.withArgs(params);
        }else{
            //mapping column and param
            Map<PropertyDescriptorEntityColumnMeta, Object> columnParamMap = IntStream.range(0, columnMetas.size())
                    .boxed()
                    .collect(Collectors.toMap(columnMetas::get, params::get));
            columnParamMap = columnParamMap.entrySet().stream()
                    .filter(kv -> kv.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

            sql = entityManagerHolder.getRender(DeleteByColumnTableMetaRender.class)
                    .rendering(meta,columnParamMap.keySet());
            sql.withArgs(columnParamMap.values().toArray());
        }

        logger.debug("SQL create:\n{}", sql);

        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }

    @Override
    public <Entity> Entity getByColumn(Class<Entity> entityClass, Object... columns) {
        Objects.requireNonNull(entityClass);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        ClassEntityTableMeta meta = entityManagerHolder.table();
        List<PropertyDescriptorEntityColumnMeta> columnMetas = entityManagerHolder.table().getColumns();
        RowMapper<Entity> rowMapper = entityManagerHolder.rowMapper();

        //Agree not to specify the NULL params
        if(columns.length != columnMetas.size()){
            columns = Arrays.copyOf(columns,columnMetas.size());
        }

        PlaceholderJSql sql;
        if(!dialectProperties.isIgnoreNullParam()){
            sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnMetas);
            sql.withArgs(columns);
        }else{
            List<Object> columnParams = Arrays.asList(columns);
            Map<PropertyDescriptorEntityColumnMeta, Object> columnParamMap = IntStream.range(0, columnMetas.size())
                    .boxed()
                    .collect(Collectors.toMap(columnMetas::get, columnParams::get));
            columnParamMap = columnParamMap.entrySet().stream()
                    .filter(kv -> kv.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

            sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnParamMap.keySet());
            sql.withArgs(columnParamMap.values().toArray());
        }

        logger.debug("SQL create:\n{}", sql);

        if(dialectProperties.isNotExistThrowError()){
            return jdbcTemplate.queryForObject(sql.getSql(), rowMapper, sql.getArgs());
        }else{
            return jdbcTemplate.query(sql.getSql(), rowMapper, sql.getArgs()).stream()
                    .findFirst()
                    .orElse(null);
        }
    }

    @Override
    public <Entity> List<Entity> listByColumn(Class<Entity> entityClass, Object... columns) {
        Objects.requireNonNull(entityClass);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        List<PropertyDescriptorEntityColumnMeta> columnMetas = entityManagerHolder.table().getColumns();

        if(columns.length != columnMetas.size()){
            //Agree not to specify the NULL params
            columns = Arrays.copyOf(columns,columnMetas.size());
        }

        RowMapper<Entity> rowMapper = entityManagerHolder.rowMapper();
        ClassEntityTableMeta meta = entityManagerHolder.table();

        PlaceholderJSql sql;
        if(!dialectProperties.isIgnoreNullParam()){
            sql = entityManagerHolder.getRenderer(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnMetas);
            sql.withArgs(columns);
        }else{
            List<Object> columnParams = Arrays.asList(columns);
            Map<PropertyDescriptorEntityColumnMeta, Object> columnParamMap = IntStream.range(0, columnMetas.size())
                    .boxed()
                    .collect(Collectors.toMap(columnMetas::get, columnParams::get));
            columnParamMap = columnParamMap.entrySet().stream()
                    .filter(kv -> kv.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

            sql = entityManagerHolder.getRender(SelectByColumnTableMetaRender.class)
                    .rendering(meta,columnParamMap.keySet());
            sql.withArgs(columnParamMap.values().toArray());
        }

        logger.debug("SQL create:\n{}", sql);
        return jdbcTemplate.query(sql.getSql(), rowMapper, sql.getArgs());
    }

    @Override
    public <Entity> void deleteByColumn(Class<Entity> entityClass, Object... columns) {
        Objects.requireNonNull(entityClass);

        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        ClassEntityTableMeta meta = entityManagerHolder.table();
        List<PropertyDescriptorEntityColumnMeta> columnMetas = entityManagerHolder.table().getColumns();

        //Agree not to specify the NULL params
        if(columns.length != columnMetas.size()){
            columns = Arrays.copyOf(columns,columnMetas.size());
        }

        PlaceholderJSql sql;
        if(!dialectProperties.isIgnoreNullParam()){
            sql = entityManagerHolder.getRenderer(DeleteByColumnTableMetaRender.class)
                    .rendering(meta,columnMetas);
            sql.withArgs(columns);
        }else{
            List<Object> columnParams = Arrays.asList(columns);
            Map<PropertyDescriptorEntityColumnMeta, Object> columnParamMap = IntStream.range(0, columnMetas.size())
                    .boxed()
                    .collect(Collectors.toMap(columnMetas::get, columnParams::get));
            columnParamMap = columnParamMap.entrySet().stream()
                    .filter(kv -> kv.getValue() != null)
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

            sql = entityManagerHolder.getRender(DeleteByColumnTableMetaRender.class)
                    .rendering(meta,columnParamMap.keySet());
            sql.withArgs(columnParamMap.values().toArray());
        }

        logger.debug("SQL create:\n{}", sql);

        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }

}
