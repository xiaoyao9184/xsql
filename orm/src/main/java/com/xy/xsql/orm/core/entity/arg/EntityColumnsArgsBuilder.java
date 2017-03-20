package com.xy.xsql.orm.core.entity.arg;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityColumn;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityColumnsArgsBuilder<T> implements BaseBuilder<T,List<Object>> {
    private List<EntityColumn> columns;

    /**
     * Set EntityColumn List
     * @param columnList EntityColumn
     * @return This
     */
    public EntityColumnsArgsBuilder<T> withColumns(List<EntityColumn> columnList) {
        this.columns = columnList;
        return this;
    }

    /**
     * Add EntityColumn
     * @param column EntityColumn
     * @return This
     */
    public EntityColumnsArgsBuilder<T> withColumn(EntityColumn... column) {
        if(this.columns == null){
            this.columns = new ArrayList<>();
        }
        this.columns.addAll(Arrays.asList(column));
        return this;
    }


    @Override
    public List<Object> build(T entity) {
        List<Object> result = new ArrayList<>();
        for (EntityColumn entityColumn : columns) {
            try {
                result.add(PropertyUtils.getProperty(entity,entityColumn.getAliasName()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }

}
