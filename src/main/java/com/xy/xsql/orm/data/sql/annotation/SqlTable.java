package com.xy.xsql.orm.data.sql.annotation;

import com.xy.xsql.orm.annotation.EntityTable;
import com.xy.xsql.orm.data.sql.base.Column;
import com.xy.xsql.orm.data.sql.base.Name;
import com.xy.xsql.orm.data.sql.base.Table;

/**
 * SQL表
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlTable extends Table {
    private EntityTable entityTable;
    private String prefix;

    public SqlTable(EntityTable entityTable, String prefix){
        super(entityTable.name(), entityTable.otherName());
        this.entityTable = entityTable;
        this.prefix = prefix == null ? "" : prefix;
        super.setRealName(this.prefix + super.getRealName());
    }

    public SqlTable(String name, String prefix){
        super(prefix + name, name);
        this.prefix = prefix;
    }


    public EntityTable getEntityTable() {
        return entityTable;
    }

    public void setEntityTable(EntityTable entityTable) {
        this.entityTable = entityTable;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    /**
     * 变为 Table
     * @return Table
     */
    public Table toTable(){
        return this;
    }
}
