package com.xy.xsql.core.target;

import com.xy.xsql.core.Setter;

/**
 * Created by xiaoyao9184 on 2017/3/19.
 */
public abstract class SetTargetBoolean<SetTargetType> implements SetTarget<SetTargetType> {

    public abstract Setter<Boolean> getSetter(SetTargetType target);

    @Override
    public void set(SetTargetType target) {
        getSetter(target).set(true);
    }

}
