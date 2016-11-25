package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EColumn;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * SQL字段
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityColumn extends Column {
    protected EColumn eColumn;
    protected Field field;

    public EntityColumn(EColumn eColumn, Field field, Table table){
        super(eColumn.name(),field.getName());
        super.table = table;
        this.eColumn = eColumn;
        this.field = field;
    }

    public EntityColumn(String realName, String otherName) {
        super(realName, otherName);
    }


    public EColumn geteColumn() {
        return eColumn;
    }

    public void seteColumn(EColumn eColumn) {
        this.eColumn = eColumn;
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
