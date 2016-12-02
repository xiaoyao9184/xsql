package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.data.sql.element.info.Table;

/**
 * EntityTable
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityTable extends Table {

    /**
     * set the name
     * @param name name
     * @return This
     */
    @Override
    public EntityTable withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * set the alias name
     * @param aliasName alias name
     * @return This
     */
    @Override
    public EntityTable withAliasName(String aliasName) {
        this.aliasName = aliasName;
        return this;
    }

}
