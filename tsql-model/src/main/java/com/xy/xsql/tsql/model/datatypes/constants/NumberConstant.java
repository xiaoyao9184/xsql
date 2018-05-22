package com.xy.xsql.tsql.model.datatypes.constants;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * integer constants
 * decimal constants
 * float and real constants
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class NumberConstant
        implements Constant, NegativePositive, Expression {

    private Number number;
    private boolean usePositive;
    private boolean useNegative;


    public NumberConstant(){}

    protected NumberConstant(Number number){
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    @Override
    public boolean isUseNegative() {
        return this.useNegative;
    }

    public void setUseNegative(boolean useNegative) {
        this.useNegative = useNegative;
    }

    @Override
    public boolean isUsePositive() {
        return usePositive;
    }

    public void setUsePositive(boolean usePositive) {
        this.usePositive = usePositive;
    }


    public String toUnsignedString(){
        if(number instanceof Byte){
            return String.valueOf(Math.abs(number.byteValue()));
        }else if(number instanceof Integer){
            return String.valueOf(Math.abs(number.intValue()));
        }else if(number instanceof Short){
            return String.valueOf(Math.abs(number.shortValue()));
        }else if(number instanceof Long){
            return String.valueOf(Math.abs(number.longValue()));
        }else if(number instanceof Float){
            return String.valueOf(Math.abs(number.floatValue()));
        }else if(number instanceof Double){
            return String.valueOf(Math.abs(number.doubleValue()));
        }
        String r = number.toString();
        if(r.indexOf("-") == 0){
            return r.substring(1);
        }
        return r;
    }

    public String toUnsignedIntegerString(){
        String r = String.valueOf(Math.abs(number.intValue()));
        if(r.indexOf("-") == 0){
            return r.substring(1);
        }
        return r;
    }

}
