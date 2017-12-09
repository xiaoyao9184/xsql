package com.xy.xsql.spring.template;

import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.spring.config.DialectConfiguration;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
@Deprecated
public interface EntityHolderManager<Entity,Result> {

    EntityInfo getEntityInfo();

    RowMapper<Result> getRowMapper();

    <Render> Render getRenderer(Class<Render> render);

    EntityColumnsArgsBuilder<Entity> getEntityColumnsArgsBuilder();

    DialectConfiguration getDialectConfiguration();

    List<FieldRowNameHandler> getFieldRowNameHandlers();
}
