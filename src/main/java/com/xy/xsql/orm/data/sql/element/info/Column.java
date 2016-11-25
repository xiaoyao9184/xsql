package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.Value;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.util.ListBuilder;

import java.util.List;

/**
 * 列
 * 符合元素
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Column
        extends Name<Column>
        implements Element, Expression, Cloneable {

    protected Table table;

    public Column() {
        super("*");
    }

    public Column(String name) {
        super(name);
    }

    public Column(String name, String aliasName) {
        super(name,aliasName);
    }

    public Column(Name name) {
        super(name);
    }

    public Column(Table table, String name) {
        super(name);
        this.table = table;
    }

    public Column(Table table, Name name) {
        super(name);
        this.table = table;
    }

    public Column(Name tableName, Name name) {
        super(name);
        this.table = new Table(tableName);
    }

    public Table getTable(){
        return table;
    }

    public void setTable(Table table){
        this.table = table;
    }


    private Column withTable(Table table) {
        this.table = table;
        return this;
    }



    /**
     * 变为 表字段名
     * @return table.column
     */
    @Override
    public String getFullName(){
        if(table == null){
            return this.getName();
        }
        return table.getFullName() + "." + this.getName();
    }


    /**
     * 克隆
     * @return Column
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Column clone() {
        return new Column()
                .withTable(this.table.clone())
                .withName(this.name)
                .withAliasName(this.aliasName);
    }

    @Override
    public List<Element> toElementList() {
        return new ListBuilder<Element>()
                .withItem(this)
                .build(null);
    }
}
