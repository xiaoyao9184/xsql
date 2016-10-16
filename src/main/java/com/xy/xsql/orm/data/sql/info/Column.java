package com.xy.xsql.orm.data.sql.info;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Value;

/**
 * 列
 * 符合元素
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Column
        extends Name
        implements Element, Value, Cloneable {

    protected Table table;

    public Column() {
        super("*");
    }

    public Column(String realName) {
        super(realName);
    }

    public Column(String realName, String otherName) {
        super(realName,otherName);
    }

    public Column(Table table,Name name) {
        super(name.getRealName(),name.getOtherName());
        this.table = table;
    }

    public Column(Name tableName, Name columnName) {
        super(columnName.getRealName(),columnName.getOtherName());
        this.table = new Table(tableName.getRealName(),tableName.getOtherName());
    }

    /**
     * 获取Table
     * @return Table
     */
    public Table getTable(){
        return table;
    }

    /**
     * 设置Table
     * @param table Table
     */
    public void setTable(Table table){
        this.table = table;
    }

    /**
     * 变为 Name
     * @return Name
     */
    public Name toName() {
        return this;
    }

    /**
     * 变为 表字段名
     * @return table.column
     */
    public String toTableColumn(){
        if(table == null){
            return this.getRealName();
        }
        return table.toPrefixSql() + this.getRealName();
    }

    @Override
    public boolean isUnKnowValue() {
        return false;
    }

    @Override
    public String toValueString(){
        return this.toTableColumn();
    }

    /**
     * 克隆
     * @return Column
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Column clone() {
        return new Column(this.table.clone(),super.clone());
    }
}
