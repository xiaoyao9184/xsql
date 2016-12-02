package com.xy.xsql.orm.build.entity.arg;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityColumn;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public class EntityColumnArgsBuilder<T> implements BaseBuilder<T,List<Object>> {


    private List<EntityColumn> sqlColumns;

    public EntityColumnArgsBuilder<T> withColumn(List<EntityColumn> columnList) {
        this.sqlColumns = columnList;
        return this;
    }

    @Override
    public List<Object> build(T entity) {
        List<Object> result = new ArrayList<>();
        for (EntityColumn entityColumn :sqlColumns) {
            try {
                result.add(PropertyUtils.getProperty(entity,entityColumn.getAliasName()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }


}
