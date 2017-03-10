package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.data.sql.element.info.Column;

/**
 * EntityColumn
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityColumn extends Column {

    private String type;
    private Integer length;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }


    /**
     * set column name
     * @param name Name
     * @return This
     */
    @Override
    public EntityColumn withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * set column alias name
     * @param aliasName Alias Name
     * @return This
     */
    @Override
    public EntityColumn withAliasName(String aliasName) {
        this.aliasName = aliasName;
        return this;
    }

    /**
     * set table
     * @param table EntityTable
     * @return This
     */
    public EntityColumn withTable(EntityTable table) {
        this.table = table;
        return this;
    }

    /**
     * set column type
     * @param type Column Type
     * @return This
     */
    public EntityColumn withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * set column length
     * @param length Column Length
     * @return This
     */
    public EntityColumn withLength(Integer length) {
        this.length = length;
        return this;
    }

}
