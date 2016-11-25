package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EColumn;
import com.xy.xsql.orm.annotation.EStatus;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * 特殊字段：状态
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityStatus extends EntityColumn {
    private EStatus eStatus;

    public EntityStatus(EStatus eStatus, EColumn eColumn, Field field, Table table) {
        super(eColumn, field, table);
        this.eStatus = eStatus;
    }

    public EStatus geteStatus() {
        return eStatus;
    }

    public void seteStatus(EStatus eStatus) {
        this.eStatus = eStatus;
    }

}
