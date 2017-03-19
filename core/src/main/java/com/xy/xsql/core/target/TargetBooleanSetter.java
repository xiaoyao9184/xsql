package com.xy.xsql.core.target;

import com.xy.xsql.core.lambda.Setter;

/**
 * Set Target boolean field by Setter
 * Created by xiaoyao9184 on 2017/3/19.
 */
public abstract class TargetBooleanSetter<TargetType>
        implements TargetSetter<TargetType> {

    public abstract Setter<Boolean> getSetter(TargetType target);

    @Override
    public void set(TargetType target) {
        getSetter(target).set(true);
    }

}
