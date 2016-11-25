package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EntityColumn;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * SQL字段
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlColumn extends Column {
    protected EntityColumn entityColumn;
    protected Field field;

    public SqlColumn(EntityColumn entityColumn, Field field, Table table){
        super(entityColumn.name(),field.getName());
        super.table = table;
        this.entityColumn = entityColumn;
        this.field = field;
    }

    public SqlColumn(String realName, String otherName) {
        super(realName, otherName);
    }


    public EntityColumn getEntityColumn() {
        return entityColumn;
    }

    public void setEntityColumn(EntityColumn entityColumn) {
        this.entityColumn = entityColumn;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    /**
     * 变为 Column
     * @return Column
     */
    public Column toColumn(){
        return this;
    }
}
