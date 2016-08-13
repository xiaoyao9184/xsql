package com.xy.xsql.orm.data.sql.annotation;

import com.xy.xsql.orm.annotation.EntityColumn;
import com.xy.xsql.orm.annotation.EntityColumnParam;
import com.xy.xsql.orm.core.ASql;
import com.xy.xsql.orm.data.sql.base.Column;
import com.xy.xsql.orm.data.sql.base.Param;
import com.xy.xsql.orm.data.sql.base.Table;
import com.xy.xsql.orm.data.sql.base.Value;

import java.lang.reflect.Field;

/**
 * SQL参数
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlParam extends Param {

    private SqlColumn sqlColumn;
    private EntityColumnParam entityColumnParam;


    public SqlParam(EntityColumnParam entityColumnParam, EntityColumn entityColumn, Field field, Table table){
        this.sqlColumn = new SqlColumn(entityColumn, field, table);
        this.entityColumnParam = entityColumnParam;

        super.and = entityColumnParam.and();
        super.column = this.sqlColumn;
        super.relationship = entityColumnParam.relationship();
        super.value = new Value(entityColumnParam.value());
    }

    public SqlParam(Boolean and, Column column, String relationship){
        super.and = and;
        super.column = column;
        super.relationship = relationship;
        super.value = new Value();
    }

    public SqlParam(Boolean and, Column column, String relationship, String value){
        super.and = and;
        super.column = column;
        super.relationship = relationship;
        super.value = new Value(value);
    }

    public EntityColumnParam getEntityColumnParam() {
        return entityColumnParam;
    }

    public void setEntityColumnParam(EntityColumnParam entityColumnParam) {
        this.entityColumnParam = entityColumnParam;
    }

    public SqlColumn getSqlColumn() {
        return sqlColumn;
    }

    public void setSqlColumn(SqlColumn sqlColumn) {
        this.sqlColumn = sqlColumn;
    }


    /**
     * 是否是关联参数（需要设置参数值，即包含 ? 号）
     * @return 是/否
     */
    public boolean isNeedValue(){
        return this.value.isUnKnowValue();
    }


    /**
     * 变为 Param
     * @return Param
     */
    public Param toParam(){
        return this;
    }

}
