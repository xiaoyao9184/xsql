package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.ETable;
import com.xy.xsql.orm.data.sql.element.info.Table;

/**
 * SQL表
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlTable extends Table {
    private ETable eTable;
    private String prefix;

    public SqlTable(ETable eTable, String prefix){
        super(eTable.name(), eTable.otherName());
        this.eTable = eTable;
        this.prefix = prefix == null ? "" : prefix;
        super.setName(this.prefix + super.getName());
    }

    public SqlTable(String name, String prefix){
        super(prefix + name, name);
        this.prefix = prefix;
    }


    public ETable geteTable() {
        return eTable;
    }

    public void seteTable(ETable eTable) {
        this.eTable = eTable;
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
