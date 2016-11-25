package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EColumn;
import com.xy.xsql.orm.annotation.ELink;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * SQL实体
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityLink {
    private EntityColumn entityColumn;
    private ELink eLink;
    private Class clazz;

    public EntityLink(Class entityClass){
        this.entityColumn = null;
        this.clazz = entityClass;
    }

    public EntityLink(ELink eLink, EColumn eColumn, Field field, Table table){
        this.entityColumn = new EntityColumn(eColumn, field, table);
        this.eLink = eLink;

        this.clazz = eLink.main();
    }


    public ELink geteLink() {
        return eLink;
    }

    public void seteLink(ELink eLink) {
        this.eLink = eLink;
    }

    public EntityColumn getEntityColumn() {
        return entityColumn;
    }

    public void setEntityColumn(EntityColumn entityColumn) {
        this.entityColumn = entityColumn;
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
        return entityColumn == null;
    }

}
