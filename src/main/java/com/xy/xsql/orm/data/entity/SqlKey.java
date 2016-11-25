package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EntityColumn;
import com.xy.xsql.orm.annotation.EntityColumnKey;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * 特殊字段：主键
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlKey extends SqlColumn {

    private EntityColumnKey entityColumnKeys;

    public SqlKey(EntityColumnKey entityColumnKeys, EntityColumn entityColumn, Field field, Table table){
        super(entityColumn, field, table);
        this.entityColumnKeys = entityColumnKeys;
    }


    public EntityColumnKey getEntityColumnKeys() {
        return entityColumnKeys;
    }

    public void setEntityColumnKeys(EntityColumnKey entityColumnKeys) {
        this.entityColumnKeys = entityColumnKeys;
    }
}
