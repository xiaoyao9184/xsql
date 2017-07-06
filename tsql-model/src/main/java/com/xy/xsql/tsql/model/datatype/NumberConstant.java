package com.xy.xsql.tsql.model.datatype;

import com.xy.xsql.tsql.model.expression.Expression;

/**
 * bit constants
 * integer constants
 * decimal constants
 * float and real constants
 * money constants
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class NumberConstant implements Constant, Expression {

    private boolean flagMoney;
    private boolean unsigned;
    private boolean integer;
    private Number number;


    public NumberConstant(Number number){
        this.number = number;
    }


    public boolean isFlagMoney() {
        return flagMoney;
    }

    public void setFlagMoney(boolean flagMoney) {
        this.flagMoney = flagMoney;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public boolean isUnsigned() {
        return unsigned;
    }

    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    public boolean isInteger() {
        return integer;
    }

    public void setInteger(boolean integer) {
        this.integer = integer;
    }


    public NumberConstant withMoney(){
        this.flagMoney = true;
        return this;
    }

    public NumberConstant withUnsigned(){
        this.unsigned = true;
        return this;
    }

    public NumberConstant withInteger(){
        this.integer = true;
        return this;
    }

    @Override
    public String toString(){
        if(flagMoney){
            return "$" + number.toString();
        }else{
            if(unsigned && number.intValue() < 0){
                return String.valueOf(-number.intValue());
            }
            if(integer){
                return String.valueOf(number.intValue());
            }
        }
        return number.toString();
    }

}
