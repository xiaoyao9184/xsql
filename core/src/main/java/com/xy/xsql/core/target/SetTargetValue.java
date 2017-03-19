package com.xy.xsql.core.target;

import com.xy.xsql.core.Setter;

/**
 * Created by xiaoyao9184 on 2017/3/19.
 */
public abstract class SetTargetValue<SetTargetType,ValueType> implements SetTarget<SetTargetType> {

    private ValueType value;

    public SetTargetValue(ValueType value){
        this.value = value;
    }

    public abstract Setter<ValueType> getSetter(SetTargetType target);

    public ValueType getValue(){
        return value;
    }

    @Override
    public void set(SetTargetType target) {
        getSetter(target).set(value);
    }

}