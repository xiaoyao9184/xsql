package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.data.sql.element.info.Column;

/**
 * SQL字段
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityColumn extends Column {

    @Override
    public EntityColumn withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public EntityColumn withAliasName(String aliasName) {
        this.aliasName = aliasName;
        return this;
    }

    public EntityColumn withTable(EntityTable table) {
        this.table = table;
        return this;
    }

    /**
     * 变为 Column
     * @return Column
     */
    public Column toColumn(){
        return this;
    }
}
