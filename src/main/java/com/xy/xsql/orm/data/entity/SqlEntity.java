package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EntityColumn;
import com.xy.xsql.orm.annotation.EntityBind;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * SQL实体
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlEntity {
    private SqlColumn sqlColumn;
    private EntityBind entityBind;
    private Class clazz;

    public SqlEntity(Class entityClass){
        this.sqlColumn = null;
        this.clazz = entityClass;
    }

    public SqlEntity(EntityBind entityBind, EntityColumn entityColumn, Field field, Table table){
        this.sqlColumn = new SqlColumn(entityColumn, field, table);
        this.entityBind = entityBind;

        this.clazz = entityBind.main();
    }


    public EntityBind getEntityBind() {
        return entityBind;
    }

    public void setEntityBind(EntityBind entityBind) {
        this.entityBind = entityBind;
    }

    public SqlColumn getSqlColumn() {
        return sqlColumn;
    }

    public void setSqlColumn(SqlColumn sqlColumn) {
        this.sqlColumn = sqlColumn;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }


    /**
     * 是否是主实体
     * @return 是/否
     */
    public boolean isCoreBean(){
        return sqlColumn == null;
    }

}
