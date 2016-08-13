package com.xy.xsql.orm.data.sql.annotation;

import com.xy.xsql.orm.annotation.EntityColumn;
import com.xy.xsql.orm.annotation.EntityColumnStatus;
import com.xy.xsql.orm.data.sql.base.Table;

import java.lang.reflect.Field;

/**
 * 特殊字段：状态
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlStatus extends SqlColumn {
    private EntityColumnStatus entityColumnStatus;

    public SqlStatus(EntityColumnStatus entityColumnStatus, EntityColumn entityColumn, Field field, Table table) {
        super(entityColumn, field, table);
        this.entityColumnStatus = entityColumnStatus;
    }

    public EntityColumnStatus getEntityColumnStatus() {
        return entityColumnStatus;
    }

    public void setEntityColumnStatus(EntityColumnStatus entityColumnStatus) {
        this.entityColumnStatus = entityColumnStatus;
    }

}
