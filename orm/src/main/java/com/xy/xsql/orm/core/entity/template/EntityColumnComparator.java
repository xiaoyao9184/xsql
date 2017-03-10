package com.xy.xsql.orm.core.entity.template;

import com.xy.xsql.orm.data.entity.EntityColumn;

import java.util.Comparator;

/**
 * Created by xiaoyao9184 on 2016/12/6.
 */
public class EntityColumnComparator implements Comparator<EntityColumn> {

    @Override
    public int compare(EntityColumn entityColumn, EntityColumn entityColumn1) {
        if(entityColumn.getName().equals(
                entityColumn1.getName()
        )){
            return 0;
        }else{
            return entityColumn.getName().compareTo(entityColumn1.getName());
        }
    }
}
