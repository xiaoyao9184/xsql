package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Sentence;

import java.util.List;

/**
 * 值
 * 泛型元素
 * Created by xiaoyao9184 on 2016/7/27.
 */
public class Value
        implements Element, com.xy.xsql.orm.data.sql.Value, Cloneable {

    protected String value;
    protected Sentence valueSentence;

    public Value(){
        this.value = "?";
    }

    public Value(String value){
        this.value = value;
    }

    public Value(Sentence valueSentence){
        this.value = null;
        this.valueSentence = valueSentence;
    }

    @Override
    public boolean isUnKnowValue() {
        return value.contains("?");
    }

    @Override
    public String toValueString(){
        if(isUnKnowValue()) {
            return value;
        }else if(this.valueSentence == null){
            return null;
        }else{
            return "'" + value + "'";
        }
    }

    @Override
    public String toString(){
        return this.value;
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

    public static Value valueOf(com.xy.xsql.orm.data.sql.Value value) {
        return new Value(value.toValueString());
    }

    @Override
    public List<Element> toElementList() {
        return null;
    }
}
