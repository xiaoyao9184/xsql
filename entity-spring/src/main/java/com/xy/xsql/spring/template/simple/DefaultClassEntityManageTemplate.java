package com.xy.xsql.spring.template.simple;

import com.xy.xsql.entity.api.dialect.render.jsql.meta.CreateTableMetaRender;
import com.xy.xsql.entity.api.dialect.render.jsql.meta.DropTableMetaRender;
import com.xy.xsql.entity.api.dialect.render.jsql.meta.QueryCountTableMetaRender;
import com.xy.xsql.model.sql.PlaceholderJSql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultClassEntityManageTemplate implements ClassEntityManageTemplate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassEntityManager classEntityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean checkTable(Class<?> entityClass) {
        ClassEntityManager.ClassEntityHolder<?> holder = classEntityManager.by(entityClass);
        PlaceholderJSql sql = holder.getRender(QueryCountTableMetaRender.class)
                .rendering(holder.table());

        logger.debug("SQL create:\n{}", sql);
        Integer cnt = jdbcTemplate.queryForObject(sql.getSql(),Integer.class);
        return cnt != null && cnt > 0;
    }

    @Override
    public void createTable(Class<?> entityClass) {
        ClassEntityManager.ClassEntityHolder<?> holder = classEntityManager.by(entityClass);
        PlaceholderJSql sql = holder.getRender(CreateTableMetaRender.class)
                .rendering(holder.table());

        logger.debug("SQL create:\n{}", sql);
        jdbcTemplate.execute(sql.getSql());
    }

    @Override
    public void dropTable(Class<?> entityClass) {
        ClassEntityManager.ClassEntityHolder<?> holder = classEntityManager.by(entityClass);
        PlaceholderJSql sql = holder.getRender(DropTableMetaRender.class)
                .rendering(holder.table());

        logger.debug("SQL create:\n{}", sql);
        jdbcTemplate.execute(sql.getSql());
    }
}
