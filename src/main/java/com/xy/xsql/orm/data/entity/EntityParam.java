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
public class EntityParam extends Param {

    private EntityColumn entityColumn;
    private EParam eParam;


    public EntityParam(EParam eParam, EColumn eColumn, Field field, Table table){
        this.entityColumn = new EntityColumn(eColumn, field, table);
        this.eParam = eParam;

        super.and = eParam.and();
        super.column = this.entityColumn;
        super.relationship = OperatorEnum.valueOf(eParam.relationship());
        super.value = new Value(eParam.value());
    }

    public EntityParam(Boolean and, Column column, String relationship){
        super.and = and;
        super.column = column;
        super.relationship = OperatorEnum.valueOf(relationship);
        super.value = new Value();
    }

    public EntityParam(Boolean and, Column column, String relationship, String value){
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

    public EntityColumn getEntityColumn() {
        return entityColumn;
    }

    public void setEntityColumn(EntityColumn entityColumn) {
        this.entityColumn = entityColumn;
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
