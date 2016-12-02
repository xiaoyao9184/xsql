package com.xy.xsql.orm.build.entity.arg;

import com.xy.xsql.orm.build.BaseBuilder;
import com.xy.xsql.orm.data.entity.EntityColumn;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Created by xiaoyao9184 on 2016/11/30.
 */
public class EntityIdArgBuilder<T> implements BaseBuilder<T,Object> {

    private EntityColumn key;

    public EntityIdArgBuilder<T> withKey(EntityColumn key) {
        this.key = key;
        return this;
    }

    @Override
    public Object build(T entity) {
        Object result;
        try {
            result = PropertyUtils.getProperty(entity,key.getAliasName());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

}
