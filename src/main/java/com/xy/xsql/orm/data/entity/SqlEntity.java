package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EColumn;
import com.xy.xsql.orm.annotation.EBind;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * SQL实体
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlEntity {
    private SqlColumn sqlColumn;
    private EBind eBind;
    private Class clazz;

    public SqlEntity(Class entityClass){
        this.sqlColumn = null;
        this.clazz = entityClass;
    }

    public SqlEntity(EBind eBind, EColumn eColumn, Field field, Table table){
        this.sqlColumn = new SqlColumn(eColumn, field, table);
        this.eBind = eBind;

        this.clazz = eBind.main();
    }


    public EBind geteBind() {
        return eBind;
    }

    public void seteBind(EBind eBind) {
        this.eBind = eBind;
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
