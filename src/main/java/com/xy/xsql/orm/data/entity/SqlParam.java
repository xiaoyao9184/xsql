package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EColumn;
import com.xy.xsql.orm.annotation.EParam;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Param;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.Value;

import java.lang.reflect.Field;

/**
 * SQL参数
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlParam extends Param {

    private SqlColumn sqlColumn;
    private EParam eParam;


    public SqlParam(EParam eParam, EColumn eColumn, Field field, Table table){
        this.sqlColumn = new SqlColumn(eColumn, field, table);
        this.eParam = eParam;

        super.and = eParam.and();
        super.column = this.sqlColumn;
        super.relationship = OperatorEnum.valueOf(eParam.relationship());
        super.value = new Value(eParam.value());
    }

    public SqlParam(Boolean and, Column column, String relationship){
        super.and = and;
        super.column = column;
        super.relationship = OperatorEnum.valueOf(relationship);
        super.value = new Value();
    }

    public SqlParam(Boolean and, Column column, String relationship, String value){
        super.and = and;
        super.column = column;
        super.relationship = OperatorEnum.valueOf(relationship);
        super.value = new Value(value);
    }

    public EParam geteParam() {
        return eParam;
    }

    public void seteParam(EParam eParam) {
        this.eParam = eParam;
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
        return this.value.toString().contains("?");
    }


    /**
     * 变为 Param
     * @return Param
     */
    public Param toParam(){
        return this;
    }

}
