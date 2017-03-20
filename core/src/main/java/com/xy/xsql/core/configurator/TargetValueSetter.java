package com.xy.xsql.core.configurator;

import com.xy.xsql.core.lambda.Setter;

/**
 * Set Target field by Setter
 * Created by xiaoyao9184 on 2017/3/19.
 */
public abstract class TargetValueSetter<TargetType,ValueType>
        implements TargetSetter<TargetType> {

    private ValueType value;

    public TargetValueSetter(ValueType value){
        this.value = value;
    }

    public ValueType getValue(){
        return value;
    }

    public abstract Setter<ValueType> getSetter(TargetType target);

    @Override
    public void set(TargetType target) {
        getSetter(target).set(value);
    }

}