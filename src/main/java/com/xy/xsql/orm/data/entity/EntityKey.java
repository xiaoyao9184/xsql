package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EColumn;
import com.xy.xsql.orm.annotation.EKey;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * 特殊字段：主键
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityKey extends EntityColumn {

    private EKey eKeys;

    public EntityKey(EKey eKeys, EColumn eColumn, Field field, Table table){
        super(eColumn, field, table);
        this.eKeys = eKeys;
    }


    public EKey geteKeys() {
        return eKeys;
    }

    public void seteKeys(EKey eKeys) {
        this.eKeys = eKeys;
    }
}
