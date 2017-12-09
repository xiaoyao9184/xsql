package com.xy.xsql.entity.api.meta;

import java.util.Set;

/**
 * Created by xiaoyao9184 on 2017/9/24
 */
public interface EntityMeta<Entity> {

    Entity entity();

    <T extends TableMeta> T table();

    <C extends ColumnMeta> Set<C> column();

    <C extends ColumnMeta> C column(String name);

}
