package com.xy.xsql.spring.template.simple;

import com.xy.xsql.entity.api.dialect.render.jsql.SqlPageRenderer;
import com.xy.xsql.entity.api.dialect.render.jsql.TemplateSearchArgRenderer;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.spring.config.DialectConfiguration;
import com.xy.xsql.spring.mapping.BaseEntityRowMapper;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultClassEntitySearchTemplate implements ClassEntitySearchTemplate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassEntityManager classEntityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public <Entity, Result> List<Result> searchListByArg(Class<Entity> entityClass, Class<Result> resultClass, Object... args) {
        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        EntityInfo entityInfo = entityManagerHolder.getEntityInfo();
        PlaceholderJSql sql = entityManagerHolder.getRenderer(TemplateSearchArgRenderer.class)
                .getSelectJoinByArgsSql(entityInfo,args);

        logger.debug("SQL create:\n{}", sql.getSql());
        logger.debug("SQL params:\n{}", sql.getArgs());
        return jdbcTemplate.query(
                sql.getSql(),
                new BaseEntityRowMapper<>(resultClass),
                sql.getArgs());
    }

    @Override
    public <Entity, Result> PageResult<Result> searchPageByArg(Class<Entity> entityClass, PageQuery<Result> pageQuery, Object... args) {
        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        DialectConfiguration dialectConfiguration = entityManagerHolder.getDialectConfiguration();
        List<FieldRowNameHandler> fieldRowNameHandlerList = entityManagerHolder.getFieldRowNameHandlers();
        EntityInfo entityInfo = entityManagerHolder.getEntityInfo();
        PlaceholderJSql sql = entityManagerHolder.getRenderer(TemplateSearchArgRenderer.class)
                .getSelectJoinByArgsSql(entityInfo,args);

        SqlPageRenderer sqlPageRenderer = entityManagerHolder.getRenderer(SqlPageRenderer.class);
        String sqlCount = sqlPageRenderer.getCountSql(sql.getSql());
        String sqlPage = sqlPageRenderer.getPageSql(sql.getSql(),pageQuery.getPageStartWithOne(),pageQuery.getPageSize());

        logger.debug("SQL create:\n{}", sql.getSql());
        logger.debug("SQL params:\n{}", sql.getArgs());
        logger.debug("SQL count:\n{}", sqlCount);
        logger.debug("SQL page:\n{}", sqlPage);

        Integer count = jdbcTemplate.queryForObject(sqlCount, sql.getArgs(), Integer.class);
        List<Result> data = jdbcTemplate.query(
                sqlPage,
                new BaseEntityRowMapper<>(pageQuery.getTarClass())
                        .withRowNumberName(sqlPageRenderer.getPageSqlRowNumberName())
                        .withPageRowNumberName(dialectConfiguration.getOrmPageRowNumberName())
                        .withRowNameHandlerList(fieldRowNameHandlerList),
                sql.getArgs());
        return new PageResult<Result>()
                .withRowCount(count)
                .withPageCount(count / pageQuery.getPageSize())
                .withRowNumber((pageQuery.getPageStartWithOne()-1) * pageQuery.getPageSize() + 1)
                .withRowData(data);
    }

    @Override
    public <Entity, Result> List<Result> searchListByTreeArg(Class<Entity> entityClass, Class<Result> resultClass, EntityTemplateTreeArg entityTemplateTreeArg) {
        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        EntityInfo entityInfo = entityManagerHolder.getEntityInfo();
        PlaceholderJSql sql = entityManagerHolder.getRenderer(TemplateSearchArgRenderer.class)
                .getSelectJoinByArgsSql(entityInfo,entityTemplateTreeArg);

        logger.debug("SQL create:\n{}", sql.getSql());
        logger.debug("SQL params:\n{}", sql.getArgs());
        return jdbcTemplate.query(
                sql.getSql(),
                new BaseEntityRowMapper<>(resultClass),
                sql.getArgs());
    }

    @Override
    public <Entity, Result> PageResult<Result> searchPageByTreeArg(Class<Entity> entityClass, PageQuery<Result> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg) {
        ClassEntityManager.ClassEntityHolder<Entity> entityManagerHolder = classEntityManager.by(entityClass);
        DialectConfiguration dialectConfiguration = entityManagerHolder.getDialectConfiguration();
        List<FieldRowNameHandler> fieldRowNameHandlerList = entityManagerHolder.getFieldRowNameHandlers();
        EntityInfo entityInfo = entityManagerHolder.getEntityInfo();
        PlaceholderJSql sql = entityManagerHolder.getRenderer(TemplateSearchArgRenderer.class)
                .getSelectJoinByTreeArgSql(entityInfo, entityTemplateTreeArg);

        SqlPageRenderer sqlPageRenderer = entityManagerHolder.getRenderer(SqlPageRenderer.class);
        String sqlCount = sqlPageRenderer.getCountSql(sql.getSql());
        String sqlPage = sqlPageRenderer.getPageSql(sql.getSql(),pageQuery.getPageStartWithOne(),pageQuery.getPageSize());

        logger.debug("SQL create:\n{}", sql.getSql());
        logger.debug("SQL params:\n{}", sql.getArgs());
        logger.debug("SQL count:\n{}", sqlCount);
        logger.debug("SQL page:\n{}", sqlPage);

        Integer count = jdbcTemplate.queryForObject(sqlCount, sql.getArgs(), Integer.class);
        List<Result> data = jdbcTemplate.query(
                sqlPage,
                new BaseEntityRowMapper<>(pageQuery.getTarClass())
                        .withRowNumberName(sqlPageRenderer.getPageSqlRowNumberName())
                        .withPageRowNumberName(dialectConfiguration.getOrmPageRowNumberName())
                        .withRowNameHandlerList(fieldRowNameHandlerList),
                sql.getArgs());
        return new PageResult<Result>()
                .withRowCount(count)
                .withPageCount(count / pageQuery.getPageSize())
                .withRowNumber((pageQuery.getPageStartWithOne()-1) * pageQuery.getPageSize() + 1)
                .withRowData(data);
    }
}
