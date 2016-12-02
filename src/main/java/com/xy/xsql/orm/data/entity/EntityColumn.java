package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.data.sql.element.info.Column;

/**
 * SQL字段
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
     * 设置 类型
     * @param type 类型
     * @return This
     */
    public EntityColumn withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 设置 长度
     * @param length 长度
     * @return This
     */
    public EntityColumn withLength(Integer length) {
        this.length = length;
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
