package com.xy.xsql.orm.data.sql.info;

import com.xy.xsql.orm.data.sql.Element;

/**
 * 值
 * 泛型元素
 * Created by xiaoyao9184 on 2016/7/27.
 */
public class Value
        implements Element, com.xy.xsql.orm.data.sql.Value, Cloneable {

    protected String value;

    public Value(){
        this.value = "?";
    }

    public Value(String value){
        this.value = value;
    }

    @Override
    public boolean isUnKnowValue() {
        return value.contains("?");
    }


    @Override
    public String toValueString(){
        if(isUnKnowValue()){
            return value;
        }else{
            return "'" + value + "'";
        }
    }

    /**
     * 克隆
     * @return Value
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Value clone() {
        return new Value(this.value);
    }
}
