package com.xy.xsql.spring.template;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface EntityHolderCRUDTemplate<Entity> {

    Entity getById(Object id);

    List<Entity> listByIds(Object... id);

    void deleteById(Object... id);

    void save(Entity... entity);

    void update(Entity... entity);

    Entity getByArg(Object... arg);

    List<Entity> listByArg(Object... args);

    void deleteByArg(Object... args);
}
